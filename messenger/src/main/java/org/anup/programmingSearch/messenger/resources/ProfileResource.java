package org.anup.programmingSearch.messenger.resources;

import javax.ws.rs.Path;

import org.anup.programmingSearch.messenger.service.ProfileService;

@Path("/profiles")
public class ProfileResource {

	ProfileService profileservice = new ProfileService();
	
}
