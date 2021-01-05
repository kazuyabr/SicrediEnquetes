package com.surveysystem.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.surveysystem.util.enums.PollOptions;

import lombok.Data;

@Entity
@Data
public class PollVote implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3545189809117884333L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@MapsId("poll_id")
	@JoinColumn(name = "poll_id")
	private Poll poll;

	@ManyToOne
	@MapsId("associado_id")
	@JoinColumn(name = "associado_id")
	private Associado associado;

	@Enumerated(value = EnumType.STRING)
	private PollOptions vote;

	public PollVote() {
	}

	public PollVote(Integer id, Poll poll, Associado associado, PollOptions vote) {
		super();
		this.id = id;
		this.poll = poll;
		this.associado = associado;
		this.vote = vote;
	}

}
