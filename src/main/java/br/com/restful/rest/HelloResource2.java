package br.com.restful.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.restful.model.Response;


@Path("/hello")
public class HelloResource2 {

	@GET
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.TEXT_HTML+";charset=utf-8")
	public String helloHTML() {
		return "<b>Ola Mundo HTML!</b>";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String helloTextPlain() {
		return "Ola Mundo Texto!";
	}
	
	@GET
	@Consumes({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
	@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML})
	public Response helloXML() {
		return Response.OK("Ola Mundo XML!");
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloJSON() {
		return Response.OK("Ola Mundo JSON!");
	}
}