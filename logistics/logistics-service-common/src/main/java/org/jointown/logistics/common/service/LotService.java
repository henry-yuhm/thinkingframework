package org.jointown.logistics.common.service;

import com.alibaba.fastjson.JSONObject;
import com.querydsl.core.types.Predicate;
import org.jointown.logistics.common.entity.LotEntity;
import org.jointown.logistics.common.repository.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LotService {
    @Autowired
    private DataValidityService dataValidityService;

    @Autowired
    private StreamComputingService streamComputingService;

    @Autowired
    private LotRepository lotRepository;

    public LotEntity findOne(Predicate predicate) {
        return this.lotRepository.findOne(predicate);
    }

    public List<LotEntity> findAll(Predicate predicate) {
        return (List<LotEntity>) this.lotRepository.findAll(predicate);
    }

    @Transactional(rollbackFor = Exception.class)
    public String save(String data) {
        List<LotEntity> lotEntities = JSONObject.parseArray(data, LotEntity.class);

        String errors = this.dataValidityService.getErrorsForData("fd_lot", JSONObject.toJSONString(lotEntities));

        if (errors != null && !errors.isEmpty()) {
            return this.streamComputingService.getStreamComputingResult(false, errors, "");
        }

        try {
            this.lotRepository.save(lotEntities);

            return this.streamComputingService.getStreamComputingResult(true, "", "");
        } catch (Exception ex) {
            return this.streamComputingService.getStreamComputingResult(false, ex.getMessage(), "");
        }
    }
}