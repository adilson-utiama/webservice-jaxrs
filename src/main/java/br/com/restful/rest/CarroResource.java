package br.com.restful.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.restful.model.Carro;
import br.com.restful.model.Response;
import br.com.restful.service.CarroService;


@Path("/carros")
@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
@Component
public class CarroResource {

	@Autowired
	private CarroService carroService;
	
	@GET
	public List<Carro> get() {
		List<Carro> carros = carroService.getCarros();
		return carros;
	}
	
	@GET
	@Path("{id}")
	public Carro get(@PathParam("id") long id) {
		Carro carro = carroService.getCarro(id);
		return carro;
	}
	
	@GET
	@Path("/tipo/{tipo}")
	public List<Carro> getByTipo(@PathParam("tipo") String tipo) {
		List<Carro> carros = carroService.findByTipo(tipo);
		return carros;
	}
	
	@GET
	@Path("/nome/{nome}")
	public List<Carro> getByNome(@PathParam("nome") String nome) {
		List<Carro> carro = carroService.findByName(nome);
		return carro;
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id) {
		carroService.delete(id);
		return Response.OK("Carro deletado com sucesso!");
	}
	
	@POST
	public Response post(Carro carro) {
		carroService.save(carro);
		return Response.OK("Carro salvo com sucesso!");
	}
	
	@PUT
	public Response put(Carro carro) {
		carroService.save(carro);
		return Response.OK("Carro atualizado com sucesso!");
	}
	
}
