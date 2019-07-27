package com.lxl.javapattern.filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CriteriaMale
 * @Author @lvxile
 * @Date 2019/7/16 19:25
 * @Description 创建实现了 Criteria 接口的实体类 - 过滤男性人员
 */
public class CriteriaMale implements Criteria {
    @Override
    public List<Person> meetCriteria(List<Person> persons) {
        List<Person> malePersons = new ArrayList<Person>();
        for (Person person : persons) {
            if(person.getGender().equalsIgnoreCase("MALE")){
                malePersons.add(person);
            }
        }
        return malePersons;
    }
}
