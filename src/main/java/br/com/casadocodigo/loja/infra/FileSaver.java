package br.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;

@Component

public class FileSaver {
	
    private HttpServletRequest request;
    
    private final String SLASH = "/";
    
    @Autowired
    public FileSaver(HttpServletRequest request) {
		this.request = request;
	}
	
    public String write(String baseFolder, MultipartFile file) {
        try {
            String realPath = request.getServletContext().getRealPath(SLASH +baseFolder);
            String cleaaName = Paths.get(file.getOriginalFilename()).getFileName().toString();
        	String path = realPath + SLASH + cleaaName;
            file.transferTo(new File(path));
            return baseFolder + SLASH + cleaaName;
        } catch (IllegalStateException | IOException e) {
        	 throw new RuntimeException("Falha ao salvar o arquivo '" + file.getOriginalFilename() + "'", e);
        }
    }
}