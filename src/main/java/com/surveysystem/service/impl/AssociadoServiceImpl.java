package com.surveysystem.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surveysystem.dto.AssociadoDto;
import com.surveysystem.entity.Associado;
import com.surveysystem.repository.AssociadoRepository;
import com.surveysystem.service.AssociadoService;

@Service
public class AssociadoServiceImpl implements AssociadoService{

	@Autowired
	private AssociadoRepository associadoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public AssociadoDto store(Associado associado) {
		return modelMapper.map(associadoRepository.save(associado), AssociadoDto.class);
	}

}
