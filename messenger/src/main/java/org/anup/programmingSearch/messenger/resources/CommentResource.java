package org.anup.programmingSearch.messenger.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**The class level @Path annotation is optional for sub resources
 **/

@Path("/comments")
public class CommentResource {

	
	/**
	 * http://localhost:8010/messenger/webapi/messages/1/comments/1
	 * */
	@Path("/{commentId}")
	@GET
	public String test(@PathParam("messageId") long messageId, @PathParam("commentId") long commentId){
		return "Sub Resource id comment Id "+commentId+" MessageId "+messageId;
		
	}
}
