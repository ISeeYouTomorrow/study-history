package com.lxl.javapattern.filter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName CriteriaSingle
 * @Author @lvxile
 * @Date 2019/7/16 20:04
 * @Description TODO
 */
public class CriteriaSingle implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        final List<Person> list = persons.stream()
                .filter(person -> person.getGender().equalsIgnoreCase("SINGLE"))
                .collect(Collectors.toList());
        return list;
    }
}
