package br.com.restful.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class UploadService {

	public String upload(String fileName, InputStream input) throws IOException {
		if(fileName == null || input == null) {
			throw new IllegalArgumentException("Parâmetros inválidos ");
		}
		
		//Pasta temporaria da JVM
		File tmpDir = new File(System.getProperty("java.io.tmpDir"), "carros");
		if(!tmpDir.exists()) {
			//Cria a pasta carros se nao existir
			tmpDir.mkdir();
		}
		
		//Cria o arquivo
		File file = new File(tmpDir, fileName);
		
		//Abre a OutputStream para escrever no arquivo
		FileOutputStream output = new FileOutputStream(file);
		
		//Escreve no arquivo
		IOUtils.copy(input, output);
		IOUtils.closeQuietly(output);
		
		//Retorna o caminho do arquivo
		String path = file.getAbsolutePath();
		return path;
	}
}
