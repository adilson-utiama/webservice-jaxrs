package br.com.restful.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import br.com.restful.model.Carro;
import br.com.restful.service.CarroService;

public class CarroTest {
	
	private CarroService service = new CarroService();

	@Test
	public void testeListaDeCarros(){
		List<Carro> carros = service.getCarros();
		Carro carro = service.findByName("Tucker 1948").get(0);
		
		assertNotNull(carros);
		assertTrue(carros.size() > 0);
		assertEquals("Tucker 1948", carro.getNome());
		
	}
	
}
