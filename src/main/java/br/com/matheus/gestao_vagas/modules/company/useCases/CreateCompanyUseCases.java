package br.com.matheus.gestao_vagas.modules.company.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.matheus.gestao_vagas.exceptions.UserFoundExcetion;
import br.com.matheus.gestao_vagas.modules.company.entitys.CompanyEntity;
import br.com.matheus.gestao_vagas.modules.company.repositoriesJ.CompanyRepository;

@Service
public class CreateCompanyUseCases {

    @Autowired
    private CompanyRepository companyRepositorios;

    @Autowired
    public PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity) {

        this.companyRepositorios.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundExcetion();
                });
        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);
        return this.companyRepositorios.save(companyEntity);
    }
}
