package org.jointown.logistics.stock.service;

import com.alibaba.fastjson.JSONObject;
import com.querydsl.core.types.Predicate;
import org.jointown.logistics.stock.client.DataValidityClient;
import org.jointown.logistics.stock.client.StreamComputingClient;
import org.jointown.logistics.stock.entity.StockEntity;
import org.jointown.logistics.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Henry on 2017/3/30.
 */
@Service
public class StockService {
    @Autowired
    private DataValidityClient dataValidityClient;

    @Autowired
    private StreamComputingClient streamComputingClient;

    @Autowired
    private StockRepository stockRepository;

    public StockEntity findOne(Predicate predicate) {
        return this.stockRepository.findOne(predicate);
    }

    public List<StockEntity> findAll(Predicate predicate) {
        return (List<StockEntity>) this.stockRepository.findAll(predicate);
    }

//    public List<StockEntity> findAllBy(String ownerNo) {
//        return this.stockRepository.findAllBy(ownerNo);
//    }

    public String getStock(String goodsId,
                           String lotNo) {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("[");
        stringBuffer.append(String.join(",", this.stockRepository.getStock(goodsId, lotNo)));
        stringBuffer.append("]");

        return stringBuffer.toString();
    }

    @Transactional(rollbackFor = Exception.class)
    public String save(String data) {
        List<StockEntity> stockEntities = JSONObject.parseArray(data, StockEntity.class);

        String errors = this.dataValidityClient.getErrorsForData("fd_stock", JSONObject.toJSONString(stockEntities));

        if (!errors.isEmpty()) {
            return this.streamComputingClient.getStreamComputingResult(false, errors, "");
        }

        try {
            this.stockRepository.save(stockEntities);

            return this.streamComputingClient.getStreamComputingResult(true, "", "");
        } catch (Exception ex) {
            return this.streamComputingClient.getStreamComputingResult(false, ex.getMessage(), "");
        }
    }
}