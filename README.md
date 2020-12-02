##简单使用
以springboot项目为例通过demo简单介绍一下junit测试
#### web
```
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
```


#### 非web
直接使用org.junit.Assert
```
    @RunWith(SpringJUnit4ClassRunner.class)
    @SpringBootTest(classes = JunitSpringRunner.class)
    public class UserServiceTest {
        @Resource
        UserService userService;
        @Test
        public void test() {
            Assert.assertEquals(new Integer(10), userService.queryById(10).getId());
        }
    }
```

#### web也可以使用assert
```
  @Test
    public void testSave2() throws Exception {
        UserPo userPo = new UserPo();
        userPo.setId(100);
        userPo.setName("zhangjf");
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/user/save")
                .content(JSONObject.toJSONString(userPo)).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                //拿到请求返回值
                .andReturn().getResponse().getContentAsString();
        Assert.assertEquals(new Integer(0), JSONObject.parseObject(result).getInteger("code"));
        Assert.assertEquals(userPo.getId(), ((JSONObject) JSONObject.parseObject(result).get("data")).getInteger("id"));
    }
```

**参考**
源码:https://github.com/nine-free/java-junit.git
