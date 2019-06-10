package com.study.lxl.springboot.task.mybatis;

import com.study.lxl.springboot.task.mapper.SysUserMapper;
import com.study.lxl.springboot.task.model.SysUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Lukas
 * @since 2019/5/27 20:09
 **/
public class MybatisTest {

    @Test
    public void testMybatis() {
        String resource = "E:\\Users\\18515\\git\\study-history\\taskdemo\\src\\test\\java\\com\\study\\lxl\\springboot\\task\\mybatis\\mybatis-config-test.xml";
        SqlSession session = null;
        try {
            InputStream inputStream = new FileInputStream(resource);
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
            session = factory.openSession();
            SysUserMapper sysUserMapper = session.getMapper(SysUserMapper.class);
            List<SysUser> list = sysUserMapper.queryList();
            list.forEach(sysUser -> System.out.println(sysUser.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }


    }
}
