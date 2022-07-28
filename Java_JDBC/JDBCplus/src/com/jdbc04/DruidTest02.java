package com.jdbc04;

import com.jdbc.utils.DataSourceUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author KevinWilliams
 */
public class DruidTest02 {
    public static void main(String[] args) throws Exception{
        /*通过连接池工具类获取一个数据库连接*/
        Connection con = DataSourceUtils.getConnection();

        /*执行操作*/
        String sql = "SELECT * FROM student";
        PreparedStatement pst = con.prepareStatement(sql);

        ResultSet rs = pst.executeQuery();

        while (rs.next()){
            System.out.println(rs.getInt("sid")+"\t"+rs.getString("name")+"\t" + rs.getInt("age") + "\t" + rs.getDate("birthday"));
        }

        //释放资源
        DataSourceUtils.close(con,pst,rs);
    }
}
