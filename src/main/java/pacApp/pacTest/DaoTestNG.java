package pacApp.pacTest;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pacApp.TodoListApplicatoinStartup;
import pacApp.pacDao.IBenutzerDao;
import pacApp.pacModel.Benutzer;

@SpringBootTest (classes = TodoListApplicatoinStartup.class)
@RunWith(SpringRunner.class)
public class DaoTestNG extends AbstractTestNGSpringContextTests{

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private IBenutzerDao benutzerDao;
	
	private MockMvc mockMvc;

	@BeforeClass
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testBenutzerDao() {
		
		Benutzer ben = new Benutzer(); 
		ben.setName("Eren Erdogan");
		benutzerDao.save(ben);
		
	}
}
