package br.com.casadocodigo.loja.infra;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;


@Component
public class FileSaver {
	
    private HttpServletRequest request;
    
    private static final String SLASH = "/";
    
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
        } catch (IllegalStateException e) {
             throw new ArquivoJaProcessadoException("Arquivo já foi processado: " + file.getOriginalFilename(), e);
        } catch (IOException e) {
             throw new ErroAoSalvarArquivoException("Falha ao salvar o arquivo '" + file.getOriginalFilename() + "'", e);
        }
    }
}