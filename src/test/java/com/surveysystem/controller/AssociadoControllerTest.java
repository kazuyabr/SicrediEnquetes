package com.surveysystem.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.surveysystem.dto.AssociadoDto;
import com.surveysystem.entity.Associado;
import com.surveysystem.repository.AssociadoRepository;
import com.surveysystem.service.AssociadoService;
import com.surveysystem.service.impl.AssociadoServiceImpl;
import com.surveysystem.util.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AssociadoControllerTest {

	@InjectMocks
	private AssociadoController associadoController;

	@Mock
	private BindingResult mockBindingResult;

	@TestConfiguration
	static class AssociadoControllerTestContextConfiguration {

		@Bean
		public AssociadoService associadoService() {
			return new AssociadoServiceImpl();
		}
	}

	@Mock
	private AssociadoServiceImpl associadoService;

	@Mock
	private AssociadoRepository associadoRepository;

	@Mock
	private ModelMapper modelMapper;

	@Test
	public void testStoreAssociado() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Associado associado = new Associado();
		associado.setId(999);
		associado.setCpf("36653064000");
		associado.setName("Lucas Silva");

		AssociadoDto associadoDto = new AssociadoDto();
		associado.setId(999);
		associado.setCpf("36653064000");
		associado.setName("Lucas Silva");

		when(associadoService.store(associado)).thenReturn(associadoDto);

		ResponseEntity<Response<AssociadoDto>> response = associadoController.store(associado, mockBindingResult);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getError()).isNull();

	}

	@Test
	public void testStoreAssociadoCPFError() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		Associado associado = new Associado();
		associado.setId(1);
		associado.setCpf("03387621088");
		associado.setName("Lucas Silva");

		Mockito.when(mockBindingResult.hasErrors()).thenReturn(true);

		ResponseEntity<Response<AssociadoDto>> response = associadoController.store(associado, mockBindingResult);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
		assertThat(response.getBody().getError()).isNotNull();

	}

}
