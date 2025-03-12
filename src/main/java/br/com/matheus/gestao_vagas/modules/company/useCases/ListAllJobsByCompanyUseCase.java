package br.com.matheus.gestao_vagas.modules.company.useCases;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheus.gestao_vagas.modules.company.entitys.JobEntity;
import br.com.matheus.gestao_vagas.modules.company.repositoriesJ.JobRepositorios;


@Service
public class ListAllJobsByCompanyUseCase {

    @Autowired
    private JobRepositorios jobRepository;

    public List<JobEntity> execute(UUID companyId){
        return this.jobRepository.findByCompanyId(companyId);
    }

}
