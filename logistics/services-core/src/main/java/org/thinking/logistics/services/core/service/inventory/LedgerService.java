package org.thinking.logistics.services.core.service.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.services.core.domain.documents.OutboundOrderHeader;
import org.thinking.logistics.services.core.domain.inventory.Inventory;
import org.thinking.logistics.services.core.domain.inventory.Ledger;
import org.thinking.logistics.services.core.domain.inventory.OutboundOrderLedger;
import org.thinking.logistics.services.core.domain.inventory.QLedger;
import org.thinking.logistics.services.core.domain.support.LedgerCategory;
import org.thinking.logistics.services.core.domain.support.LedgerSummary;
import org.thinking.logistics.services.core.domain.support.LedgerType;
import org.thinking.logistics.services.core.repository.DomainRepository;
import org.thinking.logistics.services.core.service.DomainService;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

@Service
public class LedgerService extends DomainService<QLedger, Ledger, Long> {
    private InventoryService inventoryService;

    @Autowired
    public LedgerService(EntityManager entityManager, DomainRepository<Ledger, Long> repository, InventoryService inventoryService) {
        super(entityManager, repository, QLedger.ledger);
        this.inventoryService = inventoryService;
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(Ledger ledger, Inventory inventory, LedgerSummary summary, LedgerType type, LedgerCategory category, BigDecimal quantity) throws Exception {
        this.inventoryService.save(inventory, type, category, quantity);

        //region 设置帐页属性
        ledger.setWarehouse(inventory.getWarehouse());
        ledger.setSummary(summary);
        ledger.setType(type);
        ledger.setCategory(category);
        ledger.setOwner(inventory.getOwner());
        ledger.setGoods(inventory.getGoods());
        ledger.setBatches(inventory.getBatches());
        ledger.setLocation(inventory.getLocation());
        ledger.setInventoryState(inventory.getInventoryState());
        ledger.setPallet(inventory.getPallet());
        ledger.setQuantity(inventory.getAvailableOutboundQuantity());
        ledger.setInboundQuantity(inventory.getInboundQuantity());
        ledger.setOutboundQuantity(inventory.getOutboundQuantity());
        ledger.setReplenishedFromQuantity(inventory.getReplenishedFromQuantity());
        ledger.setReplenishedToQuantity(inventory.getReplenishedToQuantity());
        ledger.setTransferredFromQuantity(inventory.getTransferredFromQuantity());
        ledger.setTransferredToQuantity(inventory.getTransferredToQuantity());
        ledger.setTransitionalQuantity(inventory.getTransitionalQuantity());
        ledger.setLockingQuantity(inventory.getLockingQuantity());
        ledger.setBalance(inventory.getQuantity());
        ledger.setGrossBalance(this.inventoryService.acquire(inventory.getWarehouse(), inventory.getGoods()));
        //endregion

        this.getRepository().save(ledger);
    }

    public void save(Inventory inventory, LedgerSummary summary, LedgerType type, LedgerCategory category, OutboundOrderHeader header, BigDecimal quantity) throws Exception {
        OutboundOrderLedger ledger = new OutboundOrderLedger();
        ledger.setHeader(header);
        this.save(ledger, inventory, summary, type, category, quantity);
    }
}