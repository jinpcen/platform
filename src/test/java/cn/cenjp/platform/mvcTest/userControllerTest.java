/**
 * 
 */
package cn.cenjp.platform.mvcTest;



import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.InputStream;

/**
 * @author zhailiang
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class userControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}



	@Test
	public void testLogin(){
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/Login")
					.contentType(MediaType.APPLICATION_JSON_UTF8).param("username","15913474490").param("password","1221312303"))
					.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testRegister(){
		try {
			String content = mockMvc.perform(MockMvcRequestBuilders.post("/doRegister")
					.contentType(MediaType.APPLICATION_JSON_UTF8).param("username", "cjp")
					.param("password", "1221312303").param("sex", "男").param("telephone", "15913474490")
					.param("address", "gdpu").param("email", "872066014@qq.com"))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
			System.out.println(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testChangePass(){
		try {
			String content = mockMvc.perform(MockMvcRequestBuilders.post("/EditPassword")
					.contentType(MediaType.APPLICATION_JSON_UTF8).param("password", "qq1221312303")
					.param("id", "45"))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
			System.out.println(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdateInfo(){
		try {
			String content = mockMvc.perform(MockMvcRequestBuilders.post("/EditInfo")
					.contentType(MediaType.APPLICATION_JSON_UTF8).param("username", "岑锦平")
					.param("sex", "女").param("phone", "15913474490")
					.param("addr", "广东药科大学中山校区").param("email", "872066014@qq.com")
			.param("id","45"))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
			System.out.println(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testOrderManager(){
		try {
			String content = mockMvc.perform(MockMvcRequestBuilders.post("/OrderManager")
					.contentType(MediaType.APPLICATION_JSON_UTF8).param("username", "岑锦平")
					.param("sex", "女").param("phone", "15913474490")
					.param("addr", "广东药科大学中山校区").param("email", "872066014@qq.com")
					.param("id","45"))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
			System.out.println(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testOrder(){
		try {
			String content = mockMvc.perform(MockMvcRequestBuilders.post("/getOder")
					.contentType(MediaType.APPLICATION_JSON_UTF8).param("userId", "45"))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
			System.out.println(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPicture(){

	}
}
