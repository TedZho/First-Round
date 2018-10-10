package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.dao.UserDaoImpl;
import com.h2rd.refactoring.usermanagement.service.UserService;
import com.h2rd.refactoring.web.UserResource;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import javax.ws.rs.core.Response;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = {"classpath:application-config.xml"})
public class UserResourceUnitTest {

	@Autowired
    private UserResource userResource;

	@Autowired
    private UserService userService;
	
	private User user;
	
	@Before
	public void setUp() {
		user = new User();
		user.setName("Fake Name");
		user.setEmail("fake@email.com");
		user.setRoles(Arrays.asList("admin", "master"));
		
		UserDaoImpl.users.clear();
	}

	@Test
    public void testGetUsers() {

        userService.saveUser(user);

        Response response = userResource.getUsers();
        Assert.assertEquals(200, response.getStatus());
    }
	
    public void setUserService(UserService userService) {
		this.userService = userService;
	}
    
    public void setUserResource(UserResource userResource) {
		this.userResource = userResource;
	}
}
