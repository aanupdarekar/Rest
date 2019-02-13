package org.anup.programmingSearch.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.anup.programmingSearch.messenger.database.DatabaseClass;
import org.anup.programmingSearch.messenger.exception.DataNotFoundException;
import org.anup.programmingSearch.messenger.model.ErrorMessage;
import org.anup.programmingSearch.messenger.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	/*
	 * public List<Message> getAllMessages() { Message m1 = new Message(1L,
	 * "Hello Dude", "Anup"); Message m2 = new Message(2L, "Hello World",
	 * "Jaga"); List<Message> list = new ArrayList<>(); list.add(m1);
	 * list.add(m2); return list; }
	 */

	public MessageService() {
		messages.put(1L, new Message(1L, "Hello World", "Anup"));
		messages.put(2L, new Message(2L, "Hello World", "Prer"));
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}

	//Throwing Exception
	public Message getMessage(long id) {
		Message message= messages.get(id);
		if(message == null){
			/**We have mapped exceptionMapper to this class
			 * i.e DataNotFoundExceptionMapper
			 * **/
			if(id == 200){
				throw new DataNotFoundException("Message with id "+id+" not found");
			}
			
			
			/**Example of WebApplicationException
			 * **/
			
			ErrorMessage errorMessage = new ErrorMessage("Not Found",404,"http://WebApplicationException");
			Response response=  Response.status(Status.NOT_FOUND)
					.entity(errorMessage)
					.build();
			throw new WebApplicationException(response);
		}
		return message;
	}

	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}

	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if (start + size > list.size())
			return new ArrayList<Message>();
		return list.subList(start, start + size);
	}

	public Message updateMessage(Message message) {

		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public Message removeMessage(long id) {
		return messages.remove(id);
	}

	public List<Message> getAllMessagesForYear(int year) {

		List<Message> messageForYear = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()) {

			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messageForYear.add(message);
			}

		}
		return messageForYear;
	}
}
