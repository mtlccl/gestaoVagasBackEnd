package br.com.matheus.gestao_vagas.modules.company.repositoriesJ;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.matheus.gestao_vagas.modules.company.entitys.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
  Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);

  Optional<CompanyEntity> findByUsername(String username);
}