package cn.cenjp.platform.mvcTest;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class mqtest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public  void setup(){
        mockMvc= MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testMQ(){
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/testmq")
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLogin(){
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/testmq")
                    .contentType(MediaType.APPLICATION_JSON_UTF8))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
