package com.surveysystem.service;

import java.util.Set;

import com.surveysystem.dto.PollVoteDto;
import com.surveysystem.entity.PollVote;
import com.surveysystem.util.CPFValidationStatusResponse;

public interface PollVoteService {

	PollVoteDto store(PollVote pollVote);
	Set<PollVoteDto> index();
	CPFValidationStatusResponse checkCPF(String cpf);
	
}
