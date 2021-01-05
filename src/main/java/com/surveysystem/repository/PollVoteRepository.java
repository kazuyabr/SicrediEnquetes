package com.surveysystem.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.surveysystem.entity.PollVote;

public interface PollVoteRepository extends JpaRepository<PollVote, Integer> {

	Set<PollVote> findByPollId(Integer pollId);

	Set<PollVote> findByAssociado(Integer associadoId);
}
