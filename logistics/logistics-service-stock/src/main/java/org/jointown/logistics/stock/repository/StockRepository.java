package org.jointown.logistics.stock.repository;

import org.jointown.logistics.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Henry on 2017/3/30.
 */
@Repository
public interface StockRepository extends JpaRepository<Stock, StockPk>, JpaSpecificationExecutor<Stock>, QueryDslPredicateExecutor<Stock> {
    @Query(value = "select concat(concat('{\"ownerNo\":\"',s.owner_no,'\",'),concat('\"goodsId\":\"',s.goods_id,'\",'),concat('\"productionDate\":\"',l.production_date,'\",'),concat('\"stockQuantity\":',ifnull(sum(s.stock_quantity-s.outbound_quantity-ifnull(i.stock_quantity,0)),0),'}')) f\n" +
            "from fd_stock as s\n" +
            "inner join fd_lot as l on s.lot_id = l.lot_id\n" +
            "left join rec_invoicing i on s.goods_id = i.goods_id and s.lot_id = i.lot_id and s.stock_status = i.stock_status\n" +
            "where s.goods_id = ?1 and l.lot_no = ?2\n" +
            "group by s.owner_no,s.goods_id,l.production_date",
            nativeQuery = true)
    List<String> getStock(String goodsId,
                          String lotNo);

//    @Query(value = "select s from Stock s where s.lotEntity.lotNo = :ownerNo")
//    List<Stock> findAllBy(@Param("ownerNo") String ownerNo);
}