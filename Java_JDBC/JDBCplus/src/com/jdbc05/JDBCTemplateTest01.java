package com.jdbc05;

import com.jdbc.utils.DataSourceUtils;
import com.jdbc05.domain.Student;
import com.jdbc05.handler.BeanHandler;
import com.jdbc05.handler.BeanListHandler;
import com.jdbc05.handler.ResultSetHandler;
import com.jdbc05.handler.ScalarHandler;
import org.junit.Test;

import java.util.List;

/**
 * @author KevinWilliams
 * 模拟dao层，测试自定义框架
 */
public class JDBCTemplateTest01 {
    private JDBCTemplate template = new JDBCTemplate(DataSourceUtils.getDataSource());

    /**查询多条测试*/
    @Test
    public void queryForScalar(){
        String sql = "SELECT COUNT(*) FROM student";
        Long value = template.queryForScalar(sql,new ScalarHandler<Long>());
        System.out.println(value);
    }

    /**查询多条测试*/
    @Test
    public void queryForList(){
        String sql = "SELECT * FROM student";
        List<Student> list = template.queryForList(sql,new BeanListHandler<>(Student.class));
        for (Student stu : list) {
            System.out.println(stu);
        }
    }

    /**查询一条测试*/
    @Test
    public void queryForObject(){
        String sql = "SELECT * FROM student WHERE sid=?";
        Student stu = template.queryForObject(sql,new BeanHandler<>(Student.class),1);
        System.out.println(stu);
    }
    /**删除数据的测试*/
    @Test
    public  void delete(){
        String sql = "DELETE FROM student WHERE sid=?";
        int result = template.update(sql, 5);
        if(result != 0){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }

    /**修改数据的测试*/
    @Test
    public void update(){
        String sql = "UPDATE student SET age=? WHERE name=?";
        Object[] params = {47,"周七"};
        int result = template.update(sql, params);
        if(result != 0){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
    }
    /**新增数据的测试*/
    @Test
    public void insert() {
        String sql = "INSERT INTO student VALUES (?,?,?,?)";
        Object[] params = {5,"周七",27,"1997-07-07"};
        int result = template.update(sql, params);
        if(result != 0){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
    }
}
