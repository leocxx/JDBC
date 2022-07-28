package com.jdbc01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author KevinWilliams
 */
public class MyDataSourceTest {
    public static void main(String[] args) throws Exception{
        MyDataSource dataSource = new MyDataSource();

        System.out.println("使用之前数量:" +dataSource.getSize());

        Connection con = dataSource.getConnection();
        System.out.println(con.getClass());

        String sql = "SELECT * FROM student";
        PreparedStatement pst = con.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        while (rs.next()){
            System.out.println(rs.getInt("sid")+"\t"+rs.getString("name")+"\t" + rs.getInt("age") + "\t" + rs.getDate("birthday"));
        }

        con.close();
        pst.close();
        rs.close();

        System.out.println("使用之后数量:" +dataSource.getSize());
    }
}
