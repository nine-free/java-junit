import cn.soft1010.java.junit.JunitSpringRunner;
import cn.soft1010.java.junit.vo.Result;
import cn.soft1010.java.junit.vo.UserPo;
import com.alibaba.fastjson.JSONObject;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Created by zhangjifu on 2020/12/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JunitSpringRunner.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    Logger logger = LoggerFactory.getLogger(UserControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testDetail() throws Exception {
        Integer id = 11;
        mockMvc.perform(MockMvcRequestBuilders
                .request(HttpMethod.POST, "/user/detail/{id}", id)
                .header("token", "123456").content("").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id", Matchers.is(id)));
    }

    @Test
    public void testDetail2() throws Exception {
        Integer id = 11;
        mockMvc.perform(MockMvcRequestBuilders
                .request(HttpMethod.POST, "/user/detail2").param("id", id.toString())
                .header("token", "123456").content("").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id", Matchers.is(id)));
    }

    @Test
    public void testSave() throws Exception {
        UserPo userPo = new UserPo();
        userPo.setId(100);
        userPo.setName("zhangjf");
        mockMvc.perform(MockMvcRequestBuilders.post("/user/save")
                .content(JSONObject.toJSONString(userPo)).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.id", Matchers.is(userPo.getId())));
    }

    @Test
    public void testSave2() throws Exception {
        UserPo userPo = new UserPo();
        userPo.setId(100);
        userPo.setName("zhangjf");
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/user/save")
                .content(JSONObject.toJSONString(userPo)).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn().getResponse().getContentAsString();
        Assert.assertEquals(new Integer(0), JSONObject.parseObject(result).getInteger("code"));
        Assert.assertEquals(userPo.getId(), ((JSONObject) JSONObject.parseObject(result).get("data")).getInteger("id"));
    }

}
