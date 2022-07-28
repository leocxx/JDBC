package com.itheima.service.impl;

import com.itheima.dao.StudentDao;
import com.itheima.dao.impl.StudentDaoImpl;
import com.itheima.domain.Student;
import com.itheima.service.StudentService;

import java.util.List;

/**
 * 学生的业务层实现类
 * @author 黑马程序员
 * @Company http://www.itheima.com
 */
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }

    @Override
    public Student findById(Integer sid) {
        return studentDao.findById(sid);
    }

    @Override
    public void save(Student student) {
        //保存
        studentDao.insert(student);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public void delete(Integer sid) {
        studentDao.delete(sid);
    }
}
