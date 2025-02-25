package br.com.matheus.gestao_vagas.modules.company.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheus.gestao_vagas.modules.company.entitys.CompanyEntity;
import br.com.matheus.gestao_vagas.modules.company.useCases.CreateCompanyUseCases;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyControlador {

    @Autowired
    private CreateCompanyUseCases createCompanyUseCases;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {
        try {
            var result = this.createCompanyUseCases.execute(companyEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
