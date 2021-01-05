package com.surveysystem.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.surveysystem.util.enums.PollOptions;
import com.surveysystem.util.enums.PollStatus;

import lombok.Getter;
import lombok.Setter;

/**
 * Objeto de transferencia de dados do modelo de enquete
 * 
 * @author kazuyabr
 *
 */
@SuppressWarnings("rawtypes")
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PollDto implements Comparable {

	private Integer id;
	private String subject;
	private Date expireIn;
	private PollStatus status;
	private PollOptions result;

	public PollDto() {
	}

	public PollDto(Integer id, String subject, Date expireIn, PollStatus status, PollOptions result) {
		super();
		this.id = id;
		this.subject = subject;
		this.expireIn = expireIn;
		this.status = status;
		this.result = result;
	}

	@Override
	public int compareTo(Object o) {
		return this.id.compareTo(((PollDto) o).getId());
	}

}
