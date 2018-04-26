package org.thinking.logistics.data.exchange.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.RestTemplate;
import org.thinking.logistics.data.exchange.domain.Request;
import org.thinking.logistics.data.exchange.domain.Response;
import org.thinking.logistics.data.exchange.domain.Result;
import org.thinking.logistics.data.exchange.domain.support.DownloadState;
import org.thinking.logistics.data.exchange.domain.support.ResponseCode;
import org.thinking.logistics.data.exchange.entity.PrescriptionHeader;
import org.thinking.logistics.data.exchange.repository.PrescriptionRepository;
import org.thinking.logistics.services.core.domain.CompositeException;

import java.net.InetAddress;
import java.util.List;

@Service
public class PrescriptionService {
    private RestTemplate restTemplate;

    private PrescriptionRepository repository;

    @Value("${business.baseUrl}")
    private String baseUrl;

    @Autowired
    public PrescriptionService(RestTemplate restTemplate, PrescriptionRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    @Async
    @Transactional(rollbackFor = Exception.class)
    public void download() throws Exception {
        Request request = new Request();
        Request.Criteria criteria = new Request.Criteria();
        Response response;

        request.setBusinessType("YY110");
        request.setIp(InetAddress.getLocalHost().getHostAddress());
        request.setHostName(InetAddress.getLocalHost().getHostName());
        criteria.setDownloadState(DownloadState.UNDOWNLOADED);
        criteria.setPage(10);
        request.setCriteria(criteria);

        int features = SerializerFeature.config(JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.WriteEnumUsingName, false);
        String body = JSONObject.toJSONString(request, features, SerializerFeature.WriteEnumUsingToString);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Code", "B028");
        httpHeaders.add("Token", DigestUtils.md5DigestAsHex((body + "123456").getBytes("UTF-8")));
        httpHeaders.add("Cache-Control", "no-cache");
        HttpEntity<String> httpEntity = new HttpEntity<>(body, httpHeaders);

        RestTemplate template = new RestTemplate();
        body = template.exchange(this.baseUrl + "/business/get", HttpMethod.POST, httpEntity, String.class).getBody();
        response = JSONObject.parseObject(body, Response.class);
//        response = this.restTemplate.postForObject(this.baseUrl + "/business/get", JSONObject.toJSONString(request, SerializerFeature.WriteEnumUsingName), Response.class);
        if (response == null) {
            throw CompositeException.getException("未下载到满足条件的处方单据");
        }

        List<PrescriptionHeader> headers = JSONObject.parseArray(response.getData(), PrescriptionHeader.class);

        this.repository.saveAll(headers);

        for (PrescriptionHeader header : headers) {
            criteria = new Request.Criteria();
            criteria.setPlatformId(header.getPlatformId());
            request.setCriteria(criteria);

            response = this.restTemplate.postForObject(this.baseUrl + "/business/updateDownloadState", request, Response.class);
            if (response == null) {
                throw CompositeException.getException("调用更新下载状态服务失败");
            }
            if (response.getCode() == ResponseCode.valueOf("")) {
                Result result = JSONObject.parseObject(response.getData(), Result.class);
                throw CompositeException.getException("单据【" + result.getOrderNo() + "】更新下载状态出错，错误信息为【" + result.getMessage() + "】");
            }
        }
    }

    public void test(String body) {
        int features = SerializerFeature.config(JSON.DEFAULT_GENERATE_FEATURE, SerializerFeature.WriteEnumUsingName, false);
//        features=SerializerFeature.config(features, SerializerFeature.WriteEnumUsingToString, true);
        Request.Criteria criteria = JSONObject.parseObject(body, Request.Criteria.class);
        body = JSONObject.toJSONString(criteria, features);
    }
}