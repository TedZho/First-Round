package com.h2rd.refactoring.web;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.service.UserService;
import com.h2rd.refactoring.usermanagement.util.ErrorStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Path("/users")
@Service
public class UserResource {

	@Autowired
    private UserService userService;
	
	static Logger log = Logger.getLogger(UserResource.class.getName());

	@POST
    @Path("/add")
	@Produces("application/xml")
    public Response addUser(@QueryParam("name") String name,
                            @QueryParam("email") String email,
                            @QueryParam("role") List<String> roles) {
		log.debug("---start to add user---" + name);
        User user = new User(name, email, roles);
        int status = userService.saveUser(user);
        
        if (status == ErrorStatus.NO_ERROR.getStatus()) {
        	return Response.ok().entity(user).build();
        } else {
        	return Response.status(Status.BAD_REQUEST).entity(ErrorStatus.getMessage(status)).build();
        }
        
    }

    @PUT
    @Path("/update")
    @Produces("application/xml")
    public Response updateUser(@QueryParam("name") String name,
                               @QueryParam("email") String email,
                               @QueryParam("role") List<String> roles) {
    	log.debug("---start to update user---" + name);
    	User user = new User(name, email, roles);  
        int status = userService.updateUser(user);
        
        if (status == ErrorStatus.NO_ERROR.getStatus()) {
        	return Response.ok().entity(user).build();
        } else {
        	return Response.status(Status.BAD_REQUEST).entity(ErrorStatus.getMessage(status)).build();
        }
    }

    @DELETE
    @Path("/delete")
    @Produces("application/xml")
    public Response deleteUser(@QueryParam("name") String name,
                               @QueryParam("email") String email,
                               @QueryParam("role") List<String> roles) {
    	log.debug("---start to delete user---" + name);
    	
    	User user = new User(name, email, roles);
    	int status = userService.deleteUser(user);
    	
        if (status == ErrorStatus.NO_ERROR.getStatus()) {
        	return Response.ok().entity(user).build();
        } else {
        	return Response.status(Status.BAD_REQUEST).entity(ErrorStatus.getMessage(status)).build();
        }
    }

    @GET
    @Path("/find")
    @Produces("application/xml")
    public Response getUsers() {

    	List<User> users = userService.getUsers();
    	
    	if (users == null) {
    		users = new ArrayList<User>();
    	}

    	GenericEntity<List<User>> entity = new GenericEntity<List<User>>(users){};
        return Response.status(Status.OK).entity(entity).build();
    }

    @GET
    @Path("/search")
    @Produces("application/xml")
    public Response findUser(@QueryParam("email") String email) {
    	log.debug("---start to get user by email---" + email);
        User user = userService.findUser(email);
        
        return Response.ok().entity(user).build();
    }
    
    public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
