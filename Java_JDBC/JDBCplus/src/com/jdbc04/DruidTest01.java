package com.jdbc04;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author KevinWilliams
 */
public class DruidTest01 {
    public static void main(String[] args) throws Exception{
        InputStream is = DruidTest01.class.getClassLoader().getResourceAsStream("druid.properties");
        /*通过properties集合加载配置文件*/
        Properties prop = new Properties();
        prop.load(is);

        /*通过Druid连接池工厂类获取数据库连接池对象*/
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        /*获取数据库连接进行使用*/
        Connection con = dataSource.getConnection();

        /*执行操作*/
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
