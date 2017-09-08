package br.com.restful.dao;

import java.util.List;

import org.junit.Test;

import br.com.restful.model.Carro;
import br.com.restful.service.CarroService;
import br.com.restful.util.SpringUtil;
import junit.framework.TestCase;

public class CarroTest extends TestCase{
	
	private CarroService service;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//Cria o "bean" pelo Spring
		service = (CarroService) SpringUtil.getInstance().getBean(CarroService.class);
	}

	@Test
	public void testeListaDeCarros(){
		List<Carro> carros = service.getCarros();
		Carro carro = service.findByName("Tucker 1948").get(0);
		
		assertNotNull(carros);
		assertTrue(carros.size() > 0);
		assertEquals("Tucker 1948", carro.getNome());
		
	}
	
}
