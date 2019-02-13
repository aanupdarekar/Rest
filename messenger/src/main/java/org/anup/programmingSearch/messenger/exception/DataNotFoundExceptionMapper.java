package org.anup.programmingSearch.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.anup.programmingSearch.messenger.model.ErrorMessage;

@Provider //Provider registers the class in jaxrs
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		// TODO Auto-generated method stub
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),404,"http://DataNotFoundExceptionMapper");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}

}
