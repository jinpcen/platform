/**
 * 
 */
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

/**
 * @author zhailiang
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class spikeControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testTurnToPay(){
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/turnToPay")
					.contentType(MediaType.APPLICATION_JSON_UTF8).param("myspike_Id", "14")
					.param("goodId", "5"))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDoPay(){
		try {
			String content = mockMvc.perform(MockMvcRequestBuilders.get("/doPay")
					.contentType(MediaType.APPLICATION_JSON_UTF8).param("spikeId", "13")
					)
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
			System.out.println(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testLookCar(){
            try {
                String content = mockMvc.perform(MockMvcRequestBuilders.get("/getphone")
                        .contentType(MediaType.APPLICATION_JSON_UTF8).param("user_", "15913474490")
                )
                        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
                System.out.println(content);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Test
	public void testPageHelper(){
		try {
			String content = mockMvc.perform(MockMvcRequestBuilders.get("/page/2")
					.contentType(MediaType.APPLICATION_JSON_UTF8).param("kind","1"))
					.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();

			System.out.println(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
