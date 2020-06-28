package com.study.lxl.cache;

import java.lang.annotation.*;

/**
 * 缓存清除注解
 * @author 18515
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheClear {
    String name();
}
