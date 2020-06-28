package com.study.lxl.annotation;

import com.study.lxl.cache.CacheClear;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * @author xile.lv
 * @since 2020/6/28 15:01
 **/
@Service
public class SearchService {

    @CacheClear(name = "table_oracle")
    public String testCache(String name) {
        return "hello,"+name+"，time= "+ Calendar.getInstance().getTimeInMillis();
    }
}
