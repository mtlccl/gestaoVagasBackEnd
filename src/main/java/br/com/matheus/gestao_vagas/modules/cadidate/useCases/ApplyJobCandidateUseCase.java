package br.com.matheus.gestao_vagas.modules.cadidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheus.gestao_vagas.exceptions.JobNotFoundException;
import br.com.matheus.gestao_vagas.exceptions.UserNotFoundException;
import br.com.matheus.gestao_vagas.modules.cadidate.CandidateRepository;
import br.com.matheus.gestao_vagas.modules.cadidate.entity.ApplyJobEntity;
import br.com.matheus.gestao_vagas.modules.cadidate.repository.ApplyJobRepository;
import br.com.matheus.gestao_vagas.modules.company.repositoriesJ.JobRepositorios;

@Service
public class ApplyJobCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepositorios jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;
    
    // ID do candidato
    // ID da vaga
    public ApplyJobEntity execute(UUID idCandidate, UUID idJob){
        // Validar se o candidato existe
        this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
            throw new UserNotFoundException();
        });

        // Validar se a vaga existe
        this.jobRepository.findById(idJob)
        .orElseThrow(() -> {
            throw new JobNotFoundException();
        });

        // Candidato se inscrever na vaga
        var applyJob = ApplyJobEntity.builder()
        .candidateId(idCandidate)
        .jobId(idJob).build();

        applyJob = applyJobRepository.save(applyJob);
        return applyJob;
    }
}
