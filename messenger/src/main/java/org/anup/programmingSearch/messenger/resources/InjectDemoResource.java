package org.anup.programmingSearch.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	//http://localhost:8010/messenger/webapi/injectdemo/annotations;param=test
	/**
	 * Below check matrix param and custom header param
	 * For header param add header i.e key value pair
	 * HeaderName and HeaderValue
	 * 
	 * also it has cookie param
	 * You can access it from youbute 23rd class
	 * **/
	@GET
	@Path("/annotations")
	public String getMatrixParam(@MatrixParam("param") String matrixParam,
			@HeaderParam("customHeaderValue") String header) {
		return "Matrix Param " + matrixParam + " HeaderParam " + header;
	}

	@GET
	@Path("test")
	public String test() {
		return "test";
	}

	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo,
			@Context HttpHeaders header) {
		String path = uriInfo.getAbsolutePath().toString();
		String cookies = header.getCookies().toString();
		return "Path :" + path+" Cookies"+cookies;
	}
}
