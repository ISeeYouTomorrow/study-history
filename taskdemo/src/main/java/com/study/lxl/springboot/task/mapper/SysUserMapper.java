package com.study.lxl.springboot.task.mapper;

import com.study.lxl.springboot.task.model.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lukas
 * @since 2019/5/24 11:08
 **/
@Repository
public interface SysUserMapper {

    List<SysUser> queryList();

    SysUser queryById(Integer id);
}
