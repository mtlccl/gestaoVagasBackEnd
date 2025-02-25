package br.com.matheus.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.matheus.gestao_vagas.modules.company.entitys.JobEntity;
import br.com.matheus.gestao_vagas.modules.company.repositoriesJ.JobRepositorios;

@Service
public class CreateTrabalhoCasosUsar {

    @Autowired
    private JobRepositorios jobRepositorios;

    public JobEntity execute(JobEntity jobEntity) {
        return this.jobRepositorios.save(jobEntity);
    }
}
