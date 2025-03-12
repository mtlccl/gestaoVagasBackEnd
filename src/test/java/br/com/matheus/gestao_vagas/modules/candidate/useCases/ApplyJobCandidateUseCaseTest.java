package br.com.matheus.gestao_vagas.modules.candidate.useCases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;

import br.com.matheus.gestao_vagas.exceptions.JobNotFoundException;
import br.com.matheus.gestao_vagas.exceptions.UserNotFoundException;
import br.com.matheus.gestao_vagas.modules.cadidate.CandidateEntity;
import br.com.matheus.gestao_vagas.modules.cadidate.CandidateRepository;
import br.com.matheus.gestao_vagas.modules.cadidate.entity.ApplyJobEntity;
import br.com.matheus.gestao_vagas.modules.cadidate.repository.ApplyJobRepository;
import br.com.matheus.gestao_vagas.modules.cadidate.useCases.ApplyJobCandidateUseCase;
import br.com.matheus.gestao_vagas.modules.company.entitys.JobEntity;
import br.com.matheus.gestao_vagas.modules.company.repositoriesJ.JobRepositorios;
import lombok.experimental.var;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepositorios jobRepositorios;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("should not be able to apply job with candidate not found")
    public void should_not_be_able_to_apply_job_with_candidate_not_found() {

        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    public void should_not_be_able_to_apply_job_with_job_not_found() {

        var idCandidate = UUID.randomUUID();

        var cadidate = new CandidateEntity();
        cadidate.setId(idCandidate);
        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(cadidate));

        try {
            applyJobCandidateUseCase.execute(idCandidate, null);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    public void should_be_able_to_create_a_job_new_apply_job() {

        var idCandidate = UUID.randomUUID();
        var idJob = UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder().candidateId(idCandidate)
                .jobId(idJob).build();

        var applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

        when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepositorios.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(idCandidate, idJob);

        Assertions.assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());

    }

}
