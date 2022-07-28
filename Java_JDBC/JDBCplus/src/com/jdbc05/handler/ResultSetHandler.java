package com.jdbc05.handler;

import java.sql.ResultSet;

/**
 * @author KevinWilliams
 * 用于处理结果集对象规范的一个接口
 */
public interface ResultSetHandler<T> {
    <T> T handler(ResultSet rs);
}
