package com.surveysystem.scheduler;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.surveysystem.entity.Poll;
import com.surveysystem.entity.PollVote;
import com.surveysystem.jobs.PollVoteJob;
import com.surveysystem.repository.PollRepository;
import com.surveysystem.repository.PollVoteRepository;
import com.surveysystem.util.enums.PollOptions;

/**
 * Agendador de contagem de votos, computa os todos os votos de hora em hora -
 * Conta votos e atualiza enquetes encerradas - Em empate, o resultado e
 * negativo
 * 
 * @author kazuyabr
 *
 */
@Component
@EnableScheduling
public class PollVoteComputeScheduler {
	private final long SECOND = 1000;
	private final long MINUTE = SECOND * 60;
	private final long HOUR = MINUTE * 60;

	@Autowired
	private PollRepository pollRepository;

	@Autowired
	private PollVoteRepository pollVoteRepository;

	@Autowired
	private PollVoteJob pollVoteJob;

	@Scheduled(fixedDelay = HOUR)
	public void computeVotes() {

		Set<Poll> polls = pollRepository.findByNotComputed();

		if (polls != null && polls.size() > 0) {

			System.out.println("Computing " + polls.size() + " polls.");

			polls.stream().forEach(poll -> compute(poll));
		}

	}

	public void compute(Poll poll) {

		synchronized (poll) {

			Set<PollVote> votes = pollVoteRepository.findByPollId(poll.getId());

			long positive = votes.stream().filter(vote -> vote.getVote() == PollOptions.YES).count();
			long negative = votes.stream().filter(vote -> vote.getVote() == PollOptions.NO).count();

			poll.setResult(positive > negative ? PollOptions.YES : PollOptions.NO);

			pollRepository.save(poll);

			pollVoteJob.sendMessage(poll.toString());

		}

	}

}
