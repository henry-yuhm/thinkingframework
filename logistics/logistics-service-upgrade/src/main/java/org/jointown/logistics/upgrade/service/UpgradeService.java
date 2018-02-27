package org.jointown.logistics.upgrade.service;

import com.querydsl.core.types.Predicate;
import org.jointown.logistics.upgrade.entity.UpgradeConfigEntity;
import org.jointown.logistics.upgrade.repository.UpgradeConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Henry on 2017/3/30.
 */
@Service
@Transactional(readOnly = true)
public class UpgradeService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UpgradeConfigRepository upgradeConfigRepository;

    public UpgradeConfigEntity findOne(Predicate predicate) {
        return this.upgradeConfigRepository.findOne(predicate);
    }

    public List<UpgradeConfigEntity> findAll(Predicate predicate) {
        return (List<UpgradeConfigEntity>) this.upgradeConfigRepository.findAll(predicate);
    }

    public List<UpgradeConfigEntity> findAllByIsAvailableAndIsUpgradedOrderByUpgradeOrder(String isAvailable,
                                                                                          String isUpgraded) {
        return this.upgradeConfigRepository.findAllByIsAvailableAndIsUpgradedOrderByUpgradeOrder(isAvailable, isUpgraded);
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(UpgradeConfigEntity upgradeConfigEntity) {
        this.upgradeConfigRepository.saveAndFlush(upgradeConfigEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void executeUpgrade(UpgradeConfigEntity upgradeConfigEntity) throws Exception {
        if (upgradeConfigEntity.getPreUpgrade().equalsIgnoreCase("D")) {
//			this.jdbcTemplate.call(connection -> connection.prepareCall(connection.nativeSQL("DELETE " + upgradeConfigEntity.getUpgradeObject())),new ArrayList());
            this.jdbcTemplate.execute("DELETE " + upgradeConfigEntity.getUpgradeObject());
        } else {
//			this.jdbcTemplate.call(connection -> connection.prepareCall(connection.nativeSQL("TRUNCATE TABLE " + upgradeConfigEntity.getUpgradeObject())),new ArrayList());
            this.jdbcTemplate.execute("TRUNCATE TABLE " + upgradeConfigEntity.getUpgradeObject());
        }

//		this.jdbcTemplate.call(connection -> connection.prepareCall(connection.nativeSQL(upgradeConfigEntity.getUpgradeScript())), new ArrayList());
        this.jdbcTemplate.execute(upgradeConfigEntity.getUpgradeScript());

        upgradeConfigEntity.setIsUpgraded("Y");
        if (this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER_TABLES@WMS_OLD WHERE TABLE_NAME = '" + upgradeConfigEntity.getUpgradeObject() + "'", int.class) > 0) {
            upgradeConfigEntity.setWantedRows(new BigDecimal(this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + upgradeConfigEntity.getUpgradeObject().toString() + "@WMS_OLD", int.class)));
        } else {
            upgradeConfigEntity.setWantedRows(new BigDecimal(0));
        }
        upgradeConfigEntity.setUpgradedRows(new BigDecimal(this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + upgradeConfigEntity.getUpgradeObject().toString(), int.class)));
        upgradeConfigEntity.setErrors("");

        this.save(upgradeConfigEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void setUpgradeErrors(UpgradeConfigEntity upgradeConfigEntity,
                                 Exception ex) {
        upgradeConfigEntity.setIsUpgraded("E");
        upgradeConfigEntity.setWantedRows(new BigDecimal(0));
        upgradeConfigEntity.setUpgradedRows(new BigDecimal(0));
        upgradeConfigEntity.setErrors(ex.getMessage());

        this.save(upgradeConfigEntity);
    }
}