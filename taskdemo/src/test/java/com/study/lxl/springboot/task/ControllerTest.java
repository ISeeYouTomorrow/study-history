package com.study.lxl.springboot.task;

import com.study.lxl.springboot.task.model.SysUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Lukas
 * @since 2019/6/17 10:33
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private MockHttpSession session;

    @Before
    public void setupMockMvc() {
        //初始化mockmvc对象
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
        session = new MockHttpSession();
        try {
            SysUser user = SysUser.class.newInstance();
            user.setCompanyNumber("Baidu");
            user.setRealName("李白");
            user.setUserId(1);
            user.setUserName("libai");
            session.setAttribute("sysUser", user);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryTest() {
        try {
            mvc.perform(MockMvcRequestBuilders.get("/user")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .session(session)
            )
            .andExpect(MockMvcResultMatchers.status().isOk())
//            .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("嘟嘟MD独立博客"))
//            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Spring Boot干货系列"))
            .andDo(MockMvcResultHandlers.print())
            ;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
