package br.com.casadocodigo.loja.conf;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import jakarta.servlet.Filter;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration.Dynamic;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {
							AppWebConfiguration.class, 
							JPAConfiguration.class,
							SecurityConfiguration.class
							};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
        return new Filter[] {encodingFilter};
    }
	
	//veja tbm https://cursos.alura.com.br/forum/topico-atualizacao-resources-nao-sao-carregados-na-aula-10-58813
	@Override
	protected void customizeRegistration(Dynamic registration) {
			registration.setMultipartConfig(new MultipartConfigElement(""));
	}
	
}