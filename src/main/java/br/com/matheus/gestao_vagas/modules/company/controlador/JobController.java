package br.com.matheus.gestao_vagas.modules.company.controlador;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheus.gestao_vagas.modules.company.dto.CreatedJobDTO;
import br.com.matheus.gestao_vagas.modules.company.entitys.JobEntity;
import br.com.matheus.gestao_vagas.modules.company.useCases.CreateTrabalhoCasosUsar;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateTrabalhoCasosUsar createTrabalhoCasosUsar;

    @PostMapping("/")
    public JobEntity create(@Valid @RequestBody CreatedJobDTO createdJobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");

        var jobEntity = JobEntity.builder().benefits(createdJobDTO.getBenefits())
        .companyId(UUID.fromString(companyId.toString())).description(createdJobDTO.getDescription())
        .level(createdJobDTO.getLevel()).build();
        return this.createTrabalhoCasosUsar.execute(jobEntity);
    }

}
