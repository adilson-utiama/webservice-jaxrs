package br.com.restful;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.jettison.JettisonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

//import org.glassfish.jersey.jettison.JettisonFeature;


//Classe carregada a partir do web.xml
public class MyApplication extends Application {

	 @Override
	 public Set<Object> getSingletons() {
	 Set<Object> singletons = new HashSet<>();
	 //Driver do jettison para gerar JSON
	 //singletons.add(new JettisonFeature());
	 singletons.add(new MultiPartFeature());
	 return singletons;
	 }

	// Usando GsonMessageBodyHandler, consumindo e retornando JSON formatado

	@Override
	public Map<String, Object> getProperties() {
		System.out.println("###Acessando getProperties");
		Map<String, Object> properties = new HashMap<>();
		// Configura o pacote para fazer scan das classes com anotacao REST
		properties.put("jersey.config.server.provider.packages", "br.com.restful");
		return properties;
	}
}
