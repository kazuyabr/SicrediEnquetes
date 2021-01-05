package com.surveysystem.service;

import java.util.Set;

import com.surveysystem.dto.PollDto;
import com.surveysystem.entity.Poll;
import com.surveysystem.util.enums.PollOptions;

public interface PollService {
	
	PollDto store(Poll poll);
	Set<PollDto> index();
	Set<PollDto> findOpen();
	Set<PollDto> findClosed();
	Set<PollDto> findByResult(PollOptions result);
}
