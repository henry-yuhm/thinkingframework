package org.thinking.logistics.core.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.thinking.logistics.core.entity.Owner;
import org.thinking.logistics.core.entity.bill.OutboundHeader;
import org.thinking.logistics.core.repository.OutboundHeaderRepository;
import org.thinking.logistics.core.repository.OwnerRepository;

import javax.annotation.Resource;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BillAdapter extends BusinessAdapter {
    @Resource
    private OwnerRepository ownerRepository;

    @Resource
    private OutboundHeaderRepository headerRepository;

    protected final Owner getOwner(String number) {
        return this.ownerRepository.findByNumber(number);
    }

    protected final OutboundHeader getOutboundHeader(Owner owner, String number) {
        return this.headerRepository.findByOwnerAndNumber(owner, number);
    }

    protected final List<OutboundHeader> getOutboundHeaders(String wave) {
        return this.headerRepository.findAllByWave(wave);
    }
}