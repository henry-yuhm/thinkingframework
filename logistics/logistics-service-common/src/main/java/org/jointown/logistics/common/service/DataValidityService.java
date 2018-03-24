package org.jointown.logistics.common.service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.jointown.logistics.common.configurer.DataSourceConfigurer;
import org.jointown.logistics.common.entity.support.DataValidityConfig;
import org.jointown.logistics.common.repository.DataValidityConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by Henry on 2017/3/10.
 */
@Service
public class DataValidityService {
    @Autowired
    private DataSourceConfigurer dataSourceConfigurer;

    @Autowired
    private DataValidityConfigRepository dataValidityConfigRepository;

    public String getErrorsForZero(String name,
                                   BigDecimal data) {
        return (data.equals(BigDecimal.ZERO)) ? "【" + name + "】" + "必须大于零！" : "";
    }

    public String getErrorsForData(String tableName,
                                   String data) {
        JSONArray objects = JSONObject.parseArray(data);
        JSONArray errors = new JSONArray();

        for (Object e : objects) {
            StringBuilder rowMessage = new StringBuilder();
            String error;

            JSONObject jsonObject = JSONObject.parseObject(e.toString());

            for (DataValidityConfig dataValidityConfig : this.dataValidityConfigRepository.findAllByTableSchemaAndTableNameOrderByColumnName(this.dataSourceConfigurer.getUsername(), tableName)) {
                if (!jsonObject.containsKey(dataValidityConfig.getColumnName())) {
                    continue;
                }

                if (dataValidityConfig.getIsNullable().equalsIgnoreCase("Y")) {
                    error = this.getErrorsForEmpty(dataValidityConfig.getColumnName(), jsonObject.getString(dataValidityConfig.getColumnName()));
                    if (!error.isEmpty()) {
                        rowMessage.append(error);
                    }
                }

                if (dataValidityConfig.getIsNumeric().equalsIgnoreCase("Y")) {
                    error = this.getErrorsForNumeric(dataValidityConfig.getColumnName(), jsonObject.getString(dataValidityConfig.getColumnName()));
                    if (!error.isEmpty()) {
                        rowMessage.append(error);
                    }
                }

                if (dataValidityConfig.getDataLength() > 0) {
                    error = this.getErrorsForLength(dataValidityConfig.getColumnName(), jsonObject.getString(dataValidityConfig.getColumnName()), dataValidityConfig.getDataLength());
                    if (!error.isEmpty()) {
                        rowMessage.append(error);
                    }
                }

                if (dataValidityConfig.getDateFormat() != null && !dataValidityConfig.getDateFormat().isEmpty()) {
                    error = this.getErrorsForDateFormat(dataValidityConfig.getColumnName(), jsonObject.getString(dataValidityConfig.getColumnName()), dataValidityConfig.getDateFormat());
                    if (!error.isEmpty()) {
                        rowMessage.append(error);
                    }
                }
            }

            if (rowMessage.length() > 0) {
                rowMessage.insert(0, "第【" + objects.indexOf(e) + "】行数据错误-->");
            }

            if (rowMessage.length() > 0) {
                errors.add(rowMessage.toString());
            }
        }

        return !errors.isEmpty() ? errors.toJSONString() : "";
    }

    public String getErrorsForEmpty(String name,
                                    String data) {
        return (data.isEmpty()) ? "【" + name + "】" + "不能为空！" : "";
    }

    public String getErrorsForNumeric(String name,
                                      String data) {
        return (!StringUtils.isNumber(data)) ? "【" + name + "】" + "不是数值！" : "";
    }

    public String getErrorsForLength(String name,
                                     String data,
                                     int length) {
        return (data.length() != length) ? "【" + name + "】" + "长度应为" + length + "！" : "";
    }

    public String getErrorsForDateFormat(String name,
                                         String data,
                                         String dateFormat) {
        return (!data.equalsIgnoreCase(String.format(data, dateFormat))) ? "【" + name + "】" + "日期格式【" + String.format(data, dateFormat) + "】无效！" : "";
    }
}