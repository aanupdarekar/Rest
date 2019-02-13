package org.anup.programmingSearch.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.anup.programmingSearch.messenger.bean.MessageFilterBean;
import org.anup.programmingSearch.messenger.model.Message;
import org.anup.programmingSearch.messenger.service.MessageService;
import org.glassfish.jersey.server.Uri;

@Path("/messages")

//what return format is decided by Produces annotation
@Produces(MediaType.APPLICATION_JSON)


public class MessageResource {

	MessageService messageService = new MessageService();

	@GET
	public List<Message> getMessages(@QueryParam("year") int year,
			@QueryParam("start") int start, @QueryParam("size") int size) {
		System.out.println("Year "+year);
		if (year > 0) {
			System.out.println("In getMessages method");
			return messageService.getAllMessagesForYear(year);
		}
		return messageService.getAllMessages();
	}

	/**
	 * Example of BeanParam instead of using above method u can se below method
	 **/
	@GET
	@Path("/beanParam")
	public List<Message> getMessagesBeanParam(
			@BeanParam MessageFilterBean filterbean) {
		System.out.println("Bean Param is getting called");
		if (filterbean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterbean.getYear());
		}
		if (filterbean.getStart() >= 0 && filterbean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(
					filterbean.getStart(), filterbean.getSize());
		}
		return messageService.getAllMessages();
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id) {
		return messageService.getMessage(id);
	}

	/*
	 * @POST public Message addMessage(Message message) { return
	 * messageService.addMessage(message);
	 * 
	 * }
	 */

	/**
	 * 26th 
	 * */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message, @Context UriInfo uriInfo) throws URISyntaxException {
		Message newMessage = messageService.addMessage(message);
		//to send the status i.e 201 created
		/*return Response.status(Status.CREATED)
				.entity(newMessage)
				.build();*/
		
		//to send the Header
	/*	return Response.created(new URI("/messenger/webapi/messages/" + newMessage.getId()))
				.entity(newMessage)
				.build();*/
		String newId= String.valueOf(newMessage.getId());
		URI uri  =  uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).entity(newMessage).build();
		

	}

	/**
	 * PUT is used for updating 
	 **/
	@PUT
	@Path("/{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id,
			Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public void removeMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}

	/**
	 * Its an example of Subresource. Since its not good practice to write the
	 * comments in same class. It should be written in new calss. Also we will
	 * not provide any GET, PUT,POST here as any method can access it
	 **/
	@Path("/{messageId}/comments")
	public CommentResource getResourceComments() {
		return new CommentResource();
	}
}
