package com.surveysystem.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracoes do componente modelMapper, de conversao de beans
 * 
 * @author kazuyabr
 * @see http://modelmapper.org/user-manual/
 */
@Configuration
public class ApplicationConfiguration {

	@Bean
	public ModelMapper modelMapper() {

		return new ModelMapper();
	}

}
