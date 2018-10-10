package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.dao.UserDaoImpl;
import com.h2rd.refactoring.usermanagement.service.UserService;
import com.h2rd.refactoring.usermanagement.util.ErrorStatus;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = {"classpath:application-config.xml"})
public class UserDaoUnitTest {

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
    public void testSaveUser() {

       int status = userService.saveUser(user);
        
       Assert.assertEquals("User creation.", ErrorStatus.NO_ERROR.getStatus(), status); 
    }
	
	@Test
    public void testSaveUserWithDuplicatedEmail() {

       userService.saveUser(user);
       
       int status = userService.saveUser(user);
        
       Assert.assertEquals("The email has been registered.", ErrorStatus.EMAIL_UNIQUE_ERROR.getStatus(), status); 
    }
	
	@Test
    public void testSaveUserWithNoRoles() {

	   user.setRoles(null);
       int status = userService.saveUser(user);
        
       Assert.assertEquals("A user should have at least 1 role..", ErrorStatus.USER_ROLE_MIN_ERROR.getStatus(), status); 
    }

    @Test
    public void testDeleteUser() {
    	userService.saveUser(user);
        int status = userService.deleteUser(user);
        
        Assert.assertEquals("User deletion.", ErrorStatus.NO_ERROR.getStatus(), status); 
    }
    
    @Test
    public void testUpdateUser() {
    	userService.saveUser(user);
    	
        User updated = new User();
        updated.setName("Update Name");
        updated.setEmail("fake@email.com");
        updated.setRoles(Arrays.asList("admin", "user"));

        int status = userService.updateUser(updated);
        
        Assert.assertEquals("User update.", ErrorStatus.NO_ERROR.getStatus(), status); 
    }
    
    public void setUserService(UserService userService) {
		this.userService = userService;
	}
}