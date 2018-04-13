package org.thinking.logistics.upgrade.service;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thinking.logistics.upgrade.entity.UpgradeConfig;
import org.thinking.logistics.upgrade.repository.UpgradeRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Henry on 2017/3/30.
 */
@Service
@Transactional(readOnly = true)
public class UpgradeService {
    private JdbcTemplate jdbcTemplate;

    private UpgradeRepository upgradeRepository;

    @Autowired
    public UpgradeService(JdbcTemplate jdbcTemplate, UpgradeRepository upgradeRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.upgradeRepository = upgradeRepository;
    }

    public UpgradeConfig findOne(Predicate predicate) {
        return this.upgradeRepository.findOne(predicate).get();
    }

    public List<UpgradeConfig> findAll(Predicate predicate) {
        return (List<UpgradeConfig>) this.upgradeRepository.findAll(predicate);
    }

    public List<UpgradeConfig> findAllByIsAvailableAndIsUpgradedOrderByUpgradeOrder(String isAvailable,
                                                                                    String isUpgraded) {
        return this.upgradeRepository.findAllByIsAvailableAndIsUpgradedOrderByUpgradeOrder(isAvailable, isUpgraded);
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(UpgradeConfig upgradeConfig) {
        this.upgradeRepository.saveAndFlush(upgradeConfig);
    }

    @Transactional(rollbackFor = Exception.class)
    public void executeUpgrade(UpgradeConfig upgradeConfig) throws Exception {
        if (upgradeConfig.getPreUpgrade().equalsIgnoreCase("D")) {
//			this.jdbcTemplate.call(connection -> connection.prepareCall(connection.nativeSQL("DELETE " + upgradeConfig.getUpgradeObject())),new ArrayList());
            this.jdbcTemplate.execute("DELETE " + upgradeConfig.getUpgradeObject());
        } else {
//			this.jdbcTemplate.call(connection -> connection.prepareCall(connection.nativeSQL("TRUNCATE TABLE " + upgradeConfig.getUpgradeObject())),new ArrayList());
            this.jdbcTemplate.execute("TRUNCATE TABLE " + upgradeConfig.getUpgradeObject());
        }

//		this.jdbcTemplate.call(connection -> connection.prepareCall(connection.nativeSQL(upgradeConfig.getUpgradeScript())), new ArrayList());
        this.jdbcTemplate.execute(upgradeConfig.getUpgradeScript());

        upgradeConfig.setUpgraded(true);
        if (this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER_TABLES@WMS_OLD WHERE TABLE_NAME = '" + upgradeConfig.getUpgradeObject() + "'", int.class) > 0) {
            upgradeConfig.setWantedRows(new BigDecimal(this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + upgradeConfig.getUpgradeObject().toString() + "@WMS_OLD", int.class)));
        } else {
            upgradeConfig.setWantedRows(new BigDecimal(0));
        }
        upgradeConfig.setUpgradedRows(new BigDecimal(this.jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + upgradeConfig.getUpgradeObject().toString(), int.class)));
        upgradeConfig.setErrors("");

        this.save(upgradeConfig);
    }

    @Transactional(rollbackFor = Exception.class)
    public void setUpgradeErrors(UpgradeConfig upgradeConfig, Exception ex) {
        upgradeConfig.setUpgraded(false);
        upgradeConfig.setWantedRows(new BigDecimal(0));
        upgradeConfig.setUpgradedRows(new BigDecimal(0));
        upgradeConfig.setErrors(ex.getMessage());

        this.save(upgradeConfig);
    }
}