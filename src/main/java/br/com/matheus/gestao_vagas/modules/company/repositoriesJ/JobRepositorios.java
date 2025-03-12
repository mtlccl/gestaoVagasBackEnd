package br.com.matheus.gestao_vagas.modules.company.repositoriesJ;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matheus.gestao_vagas.modules.company.entitys.JobEntity;

public interface JobRepositorios extends JpaRepository<JobEntity, UUID> {

    List<JobEntity> findByDescriptionContainingIgnoreCase(String filter);

    List<JobEntity> findByCompanyId(UUID companyId);

}
