package com.lxl.javapattern.filter;

import java.util.List;

/**
 * 为标准创建一个接口
 */
public interface Criteria {
    /**
     * 具体标准实现方法
     * @param persons
     * @return
     */
    List<Person> meetCriteria(List<Person> persons);
}
