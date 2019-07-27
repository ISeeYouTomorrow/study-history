package com.lxl.javapattern.filter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName CriteriaFemale
 * @Author @lvxile
 * @Date 2019/7/16 19:29
 * @Description 创建过滤器的实现-女性
 */
public class CriteriaFemale implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        final List<Person> list = persons.stream()
              .filter(person -> person.getGender().equalsIgnoreCase("FEMALE"))
              .collect(Collectors.toList());
        return list;
    }
}
