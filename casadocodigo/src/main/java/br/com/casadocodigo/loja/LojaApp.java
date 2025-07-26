package br.com.casadocodigo.loja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class LojaApp {
	public static void main(String[] args) {
		SpringApplication.run(LojaApp.class, args);
	}

	@Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

	@Bean
	InternalResourceViewResolver internalResourceViewResolver() {
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".jsp");
    return resolver;
}


}
