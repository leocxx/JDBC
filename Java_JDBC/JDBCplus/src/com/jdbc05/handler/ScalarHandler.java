package com.jdbc05.handler;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ScalarHandler<T> implements ResultSetHandler<T>{
    @Override
    public Long handler(ResultSet rs) {
        //定义一个Long类型的变量
        Long value = null;
        try {
            //判断结果集中是否有对象
            if(rs.next()){
                //获取结果集源信息的对象
                ResultSetMetaData metaData = rs.getMetaData();
                //获取第一列的列名
                String columnName = metaData.getColumnName(1);
                //根据该列的列名，获取值
                value = rs.getLong(columnName);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        /*返回结果*/
        return value;
    }
}
