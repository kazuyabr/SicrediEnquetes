package com.surveysystem.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.surveysystem.util.enums.PollOptions;
import com.surveysystem.util.enums.PollStatus;

import lombok.Data;

@Entity
@Data
public class Poll implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1248240673587326728L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "O assunto do tópico não pode estar vazio.")
	private String subject;

	@CreatedDate
	@CreationTimestamp
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date expireIn;

	@Transient
	private PollStatus status;

	@Enumerated(value = EnumType.STRING)
	private PollOptions result;

	@OneToMany(mappedBy = "poll", fetch = FetchType.LAZY)
	private Set<PollVote> polls;

	@PrePersist
	private void prePersist() {

		if (this.expireIn != null && this.expireIn.after(new Date())) {
			return;
		}

		LocalDateTime expireInDefault = LocalDateTime.now();
		expireInDefault = expireInDefault.plusMinutes(1);
		this.expireIn = Date.from(expireInDefault.atZone(ZoneId.systemDefault()).toInstant());

	}

	@PostLoad
	private void onLoad() {

		PollStatus status = PollStatus.CLOSED;

		if (this.expireIn.after(new Date())) {
			status = PollStatus.OPEN;
		}

		this.status = status;
	}

	@Override
	public String toString() {
		return "Poll [id=" + id + ", subject=" + subject + ", createdAt=" + createdAt + ", expireIn=" + expireIn
				+ ", status=" + status + ", result=" + result + "]";
	}

}
