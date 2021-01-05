package com.surveysystem.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Entity
@Data
public class Associado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 62712821983200682L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@CPF
	@NotEmpty(message = "CPF não pode estar vazio")
	private String cpf;

	@NotEmpty(message = "Nome não pode estar vazio")
	private String name;

	@OneToMany(mappedBy = "associado", fetch = FetchType.LAZY)
	private Set<PollVote> polls;

}
