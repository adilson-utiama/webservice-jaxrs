package br.com.restful.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.restful.model.Carro;
import br.com.restful.model.Response;
import br.com.restful.service.CarroService;


@Path("/carros")
@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
@Component
public class CarrosResource {

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
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response postFoto(final FormDataMultiPart multiPart) {
		if(multiPart != null && multiPart.getFields() != null) {
			Set<String> keys = multiPart.getFields().keySet();
			for (String key : keys) {
				//Obtem a InputStream para ler o arquivo
				FormDataBodyPart field = multiPart.getField(key);
				InputStream inputStream = field.getValueAs(InputStream.class);
				try {
					//Salva o arquivo
					String fileName = field.getFormDataContentDisposition().getFileName();
					
					//Pasta temporaria da JVM
					File tmpDir = new File(System.getProperty("java.io.tmpDir"), "carros");
					if(!tmpDir.exists()) {
						//Cria a pasta carros se nao existir
						tmpDir.mkdir();
					}
					//Cria o arquivo
					File file = new File(tmpDir, fileName);
					FileOutputStream out = new FileOutputStream(file);
					IOUtils.copy(inputStream, out);
					IOUtils.closeQuietly(out);
					System.out.println("Arquivo: " + file.getAbsolutePath());
					return Response.OK("Arquivo recebido com sucesso!");
				} catch(IOException e) {
					e.printStackTrace();
					return Response.ERROR("Erro ao envia arquivo!");
				}
			}
		}
		return Response.OK("Requisicao inválida");
	}
	
	
	
}
