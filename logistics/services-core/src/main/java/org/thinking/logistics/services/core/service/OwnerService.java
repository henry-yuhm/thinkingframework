package org.thinking.logistics.services.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.services.core.domain.CompositeException;
import org.thinking.logistics.services.core.domain.Owner;
import org.thinking.logistics.services.core.domain.QOwner;
import org.thinking.logistics.services.core.repository.DomainRepository;

import javax.persistence.EntityManager;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerService extends DomainService<QOwner, Owner, Long> {
    @Autowired
    public OwnerService(EntityManager entityManager, DomainRepository<Owner, Long> repository) {
        super(entityManager, repository, QOwner.owner);
    }

    public final Owner acquire(String no, boolean verifiable) throws Exception {
        Owner owner = this.getFactory().selectFrom(this.getPath())
            .where(this.getPath().no.eq(no))
            .fetchOne();

        if (verifiable) {
            if (owner == null) {
                throw CompositeException.getException("无此编号【" + no + "】的业主资料");
            }
        }

        return owner;
    }

    @Transactional(rollbackFor = Exception.class)
    public Owner save(Owner own) throws Exception {
        Optional.of(own.getNo()).orElseThrow(() -> CompositeException.getException("编号不能为空"));
        Optional.of(own.getName()).orElseThrow(() -> CompositeException.getException("名称不能为空"));

        Owner owner = this.getFactory().selectFrom(this.getPath())
            .where(this.getPath().no.eq(own.getNo()))
            .fetchOne();

        if (owner == null) {
            owner = own;
        } else {
            if (!Optional.ofNullable(own.getMnemonicCode()).isPresent()) {
                owner.setMnemonicCode(own.getMnemonicCode());
            }

            if (!Optional.ofNullable(own.getInventoryUpper()).isPresent()) {
                owner.setInventoryUpper(own.getInventoryUpper());
            }

            owner.setThirdpart(own.isThirdpart());
        }

        return this.getRepository().save(owner);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Owner> saveAll(List<Owner> owns) throws Exception {
        List<Owner> owners = new LinkedList<>();

        for (Owner owner : owns) {
            owners.add(this.save(owner));
        }

        return owners;
    }
}