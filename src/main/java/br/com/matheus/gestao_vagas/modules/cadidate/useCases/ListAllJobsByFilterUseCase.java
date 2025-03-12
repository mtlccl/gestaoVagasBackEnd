package br.com.matheus.gestao_vagas.modules.cadidate.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheus.gestao_vagas.modules.company.entitys.JobEntity;
import br.com.matheus.gestao_vagas.modules.company.repositoriesJ.JobRepositorios;

@Service
public class ListAllJobsByFilterUseCase {
    
    @Autowired
    private JobRepositorios jobRepositorios;

    public List<JobEntity> execute(String filter) {
        return this.jobRepositorios.findByDescriptionContainingIgnoreCase(filter);
    }
}
