package br.com.matheus.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheus.gestao_vagas.exceptions.CompanyNotFoundException;
import br.com.matheus.gestao_vagas.modules.company.entitys.JobEntity;
import br.com.matheus.gestao_vagas.modules.company.repositoriesJ.CompanyRepository;
import br.com.matheus.gestao_vagas.modules.company.repositoriesJ.JobRepositorios;

@Service
public class CreateTrabalhoCasosUsar {

    @Autowired
    private JobRepositorios jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public JobEntity execute(JobEntity jobEntity) {
        companyRepository.findById(jobEntity.getCompanyId()).orElseThrow(() -> {
            throw new CompanyNotFoundException();
        });
        return this.jobRepository.save(jobEntity);
    }
}
