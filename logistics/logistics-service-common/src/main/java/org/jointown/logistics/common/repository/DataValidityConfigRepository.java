package org.jointown.logistics.common.repository;

import org.jointown.logistics.common.entity.DataValidityConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataValidityConfigRepository extends JpaRepository<DataValidityConfig, DataValidityConfig.DataValidityConfigEntityPk>, JpaSpecificationExecutor<DataValidityConfig>, QueryDslPredicateExecutor<DataValidityConfig> {
    List<DataValidityConfig> findAllByTableSchemaAndTableNameOrderByColumnName(String tableSchema,
                                                                               String tableName);
}