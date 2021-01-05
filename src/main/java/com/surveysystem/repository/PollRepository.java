package com.surveysystem.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.surveysystem.entity.Poll;
import com.surveysystem.util.enums.PollOptions;

@Repository
public interface PollRepository extends JpaRepository<Poll, Integer> {

	Optional<Poll> findById(Integer id);

	Set<Poll> findByResult(PollOptions result);

	@Query("SELECT * from Poll p WHERE p.expireIn <= now() and p.result is null")
	Set<Poll> findByNotComputed();

	@Query("SELECT * from Poll p WHERE p.expireIn > now()")
	Set<Poll> findByOpenStatus();

	@Query("SELECT * from Poll p WHERE p.expireIn <= now()")
	Set<Poll> findByClosedStatus();

}
