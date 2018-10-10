package test.com.h2rd.refactoring.integration;

import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.dao.UserDaoImpl;
import com.h2rd.refactoring.usermanagement.service.UserService;
import com.h2rd.refactoring.web.UserResource;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = {"classpath:application-config.xml"})
public class UserIntegrationTest {
	
	@Autowired
    private UserResource userResource;

	@Autowired
    private UserService userService;
	
	private User user;
	
	@Before
	public void setUp() {
		user = new User("integration", "initial@integration.com", 
				new ArrayList<String>(Arrays.asList("admin", "master")));
		
		UserDaoImpl.users.clear();
	}


	@Test
	public void testUserAction() {
		// User is not saved, the return value is null
		User u = userService.findUser(user.getEmail());
		Assert.assertNull(u);
		
		// Add user
		Response response = userResource.addUser(user.getName(), user.getEmail(), user.getRoles());
        Assert.assertEquals(200, response.getStatus());
        
        response = userResource.findUser(user.getEmail());
        Assert.assertEquals(200, response.getStatus());
        
        // User added, it should return the user object
        u = userService.findUser(user.getEmail());
        Assert.assertNotNull(u);
        
        response = userResource.updateUser(u.getName(), u.getEmail(), u.getRoles());
        Assert.assertEquals(200, response.getStatus());
		
        response = userResource.deleteUser(u.getName(), u.getEmail(), u.getRoles());
        Assert.assertEquals(200, response.getStatus());
        
        // the user is deleted, the return value should be null
        u = userService.findUser(user.getEmail());
		Assert.assertNull(u);
	}
	
    public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void setUserResource(UserResource userResource) {
		this.userResource = userResource;
	}
}
