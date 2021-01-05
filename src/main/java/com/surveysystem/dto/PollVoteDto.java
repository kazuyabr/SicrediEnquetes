package com.surveysystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.surveysystem.util.enums.PollOptions;

import lombok.Getter;
import lombok.Setter;

/**
 * Objeto de transferencia de dados do modelo de votacao
 * 
 * @author kazuyabr
 *
 */
@SuppressWarnings("rawtypes")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PollVoteDto implements Comparable {

	private AssociadoDto associado;
	private PollDto poll;
	private PollOptions vote;

	@Override
	public int compareTo(Object o) {
		return this.associado.getCpf().compareTo(((PollVoteDto) o).getAssociado().getCpf());
	}

}
