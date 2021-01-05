package com.surveysystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

/**
 * Objeto de transferencia de dados do modelo de associado
 * 
 * @author kazuyabr
 *
 */
@SuppressWarnings("rawtypes")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class AssociadoDto implements Comparable {

	private String cpf;
	private String name;

	public AssociadoDto() {
	}

	public AssociadoDto(String cpf, String name) {
		super();
		this.cpf = cpf;
		this.name = name;
	}

	@Override
	public int compareTo(Object o) {
		return this.name.compareTo(((AssociadoDto) o).getName());
	}

}
