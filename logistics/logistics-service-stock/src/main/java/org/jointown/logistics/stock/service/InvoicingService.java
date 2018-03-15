package org.jointown.logistics.stock.service;

import com.alibaba.fastjson.JSONObject;
import com.querydsl.core.types.Predicate;
import org.jointown.logistics.stock.client.DataValidityClient;
import org.jointown.logistics.stock.client.StreamComputingClient;
import org.jointown.logistics.stock.entity.Invoice;
import org.jointown.logistics.stock.repository.InvoicingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvoicingService {
    @Autowired
    private DataValidityClient dataValidityClient;

    @Autowired
    private StreamComputingClient streamComputingClient;

    @Autowired
    private InvoicingRepository invoicingRepository;

    public Invoice findOne(Predicate predicate) {
        return this.invoicingRepository.findOne(predicate);
    }

    public List<Invoice> findAll(Predicate predicate) {
        return (List<Invoice>) this.invoicingRepository.findAll(predicate);
    }

    @Transactional(rollbackFor = Exception.class)
    public String save(String data) {
        List<Invoice> invoices = JSONObject.parseArray(data, Invoice.class);

        String errors = this.dataValidityClient.getErrorsForData("fd_stock", JSONObject.toJSONString(invoices));

        if (!errors.isEmpty()) {
            return this.streamComputingClient.getStreamComputingResult(false, errors, "");
        }

        try {
            this.invoicingRepository.save(invoices);

            return this.streamComputingClient.getStreamComputingResult(true, "", "");
        } catch (Exception ex) {
            return this.streamComputingClient.getStreamComputingResult(false, ex.getMessage(), "");
        }
    }
}