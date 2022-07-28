package com.jdbc03;

import com.mchange.v2.c3p0.ComboPooledDataSource;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author KevinWilliams
 */
public class C3P0Test01 {
    public static void main(String[] args) throws Exception{
        /*1.创建c3p0的数据库连接对象*/
        ComboPooledDataSource dataSource;
        dataSource = new ComboPooledDataSource();
        /*2.通过连接池对象获取数据库连接*/
        Connection con = dataSource.getConnection();
        /*3.执行操作*/
        String sql = "SELECT * FROM student";
        PreparedStatement pst = con.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        while (rs.next()){
            System.out.println(rs.getInt("sid")+"\t"+rs.getString("name")+"\t" + rs.getInt("age") + "\t" + rs.getDate("birthday"));
        }

        con.close();
        pst.close();
        rs.close();
    }
}
