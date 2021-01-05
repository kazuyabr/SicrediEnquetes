package com.surveysystem.service.impl;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surveysystem.dto.PollDto;
import com.surveysystem.entity.Poll;
import com.surveysystem.repository.PollRepository;
import com.surveysystem.service.PollService;
import com.surveysystem.util.enums.PollOptions;

@Service
public class PollServiceImpl implements PollService {

	@Autowired
	private PollRepository pollRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PollDto store(Poll poll) {
		return modelMapper.map(pollRepository.save(poll), PollDto.class);
	}

	@Override
	public Set<PollDto> index() {
		return pollRepository
				.findAll()
				.stream()
				.map(poll -> modelMapper.map(poll, PollDto.class))
				.collect(Collectors.toCollection(TreeSet::new));
	}

	@Override
	public Set<PollDto> findOpen() {
		return pollRepository
				.findByOpenStatus()
				.stream()
				.map(poll -> modelMapper.map(poll, PollDto.class))
				.collect(Collectors.toCollection(TreeSet::new));
	}

	@Override
	public Set<PollDto> findClosed() {
		return pollRepository
				.findByClosedStatus()
				.stream()
				.map(poll -> modelMapper.map(poll, PollDto.class))
				.collect(Collectors.toCollection(TreeSet::new));
	}

	@Override
	public Set<PollDto> findByResult(PollOptions result) {
		return pollRepository
				.findByResult(result)
				.stream()
				.map(poll -> modelMapper.map(poll, PollDto.class))
				.collect(Collectors.toCollection(TreeSet::new));
	}

}
