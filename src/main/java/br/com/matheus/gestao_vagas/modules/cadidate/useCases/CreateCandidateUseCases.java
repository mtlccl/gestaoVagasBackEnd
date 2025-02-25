package br.com.matheus.gestao_vagas.modules.cadidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.matheus.gestao_vagas.exceptions.UserFoundExcetion;
import br.com.matheus.gestao_vagas.modules.cadidate.CandidateEntity;
import br.com.matheus.gestao_vagas.modules.cadidate.CandidateRepository;

@Service
public class CreateCandidateUseCases {
  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(),
        candidateEntity.getEmail()).ifPresent(user -> {
          throw new UserFoundExcetion();
        });

    var password = passwordEncoder.encode(candidateEntity.getPassword());
    candidateEntity.setPassword(password);

    return this.candidateRepository.save(candidateEntity);
  }
}