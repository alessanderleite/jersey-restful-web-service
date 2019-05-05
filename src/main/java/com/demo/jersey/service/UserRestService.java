package com.demo.jersey.service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.demo.jersey.model.User;
import com.demo.jersey.model.Users;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "users")
@Path("/users")
public class UserRestService {
	private static Map<Integer, User> INMEMORYDB = new HashMap<Integer, User>();
	static {
		User user1 = new User();
		user1.setId(1);
		user1.setFirstName("Alessander");
		user1.setLastName("Leite");
		
		User user2 = new User();
		user2.setId(2);
		user2.setFirstName("Alexandre");
		user2.setLastName("Valente");
		
		INMEMORYDB.put(user1.getId(), user1);
		INMEMORYDB.put(user2.getId(), user2);
	}
	
	@GET
	@Produces("application/json")
	public Users getAllUsers() {
		Users users = new Users();
		users.setUsers(new ArrayList<User>(INMEMORYDB.values()));
		return users;
	}
	
	@GET
	@Path("/user/{userId}")
	@Produces("application/json")
	public Response getUserById(@PathParam("userId") int userId) throws URISyntaxException {
		User user = INMEMORYDB.get(userId);
		if (user == null) {
			return Response.status(404).build();			
		}
		return Response.status(200).entity(user).build();
	}
	
}
