package br.com.matheus.gestao_vagas.modules.company.controlador;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheus.gestao_vagas.modules.company.dto.CreatedJobDTO;
import br.com.matheus.gestao_vagas.modules.company.entitys.JobEntity;
import br.com.matheus.gestao_vagas.modules.company.useCases.CreateTrabalhoCasosUsar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
@Tag(name = "Vagas", description = "informacoes das vagas")
public class JobController {

    @Autowired
    private CreateTrabalhoCasosUsar createTrabalhoCasosUsar;

    @PostMapping("/")
    @PreAuthorize("hasRole('COMPANY')")

    @Operation(summary = "cadastro das vagas", description = "lista de vagas cadastradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = JobEntity.class))
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public JobEntity create(@Valid @RequestBody CreatedJobDTO createdJobDTO, HttpServletRequest request) {
        var companyId = request.getAttribute("company_id");

        var jobEntity = JobEntity.builder()
                .benefits(createdJobDTO.getBenefits())
                .companyId(UUID.fromString(companyId.toString()))
                .description(createdJobDTO.getDescription())
                .level(createdJobDTO.getLevel()).build();
        return this.createTrabalhoCasosUsar.execute(jobEntity);
    }

}
