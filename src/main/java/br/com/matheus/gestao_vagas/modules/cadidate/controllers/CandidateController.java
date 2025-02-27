package br.com.matheus.gestao_vagas.modules.cadidate.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.matheus.gestao_vagas.modules.cadidate.CandidateEntity;
import br.com.matheus.gestao_vagas.modules.cadidate.dto.ProfileCandidateResponseDTO;
import br.com.matheus.gestao_vagas.modules.cadidate.useCases.CreateCandidateUseCases;
import br.com.matheus.gestao_vagas.modules.cadidate.useCases.ListAllJobsByFilterUseCase;
import br.com.matheus.gestao_vagas.modules.cadidate.useCases.ProfileCandidateUseCase;
import br.com.matheus.gestao_vagas.modules.company.entitys.JobEntity;
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
@RequestMapping("/candidate")
@Tag(name = "Candidato", description = "informacoes do candidato")

public class CandidateController {
  @Autowired
  private CreateCandidateUseCases createCandidateUseCase;

  @Autowired
  private ProfileCandidateUseCase profileCandidateUseCase;

  @Autowired
  private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

  @PostMapping("/")
  @Operation(summary = "Cadastro de candidato", description = "essa funcao cadastra candidato")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
    }),
    @ApiResponse(responseCode = "400", description = "Usuario ja existe")
})
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    try {
      var result = createCandidateUseCase.execute(candidateEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(summary = "Perfil do candidato", description = "essa funcao busca as infos do candidato")
  @ApiResponses({
    @ApiResponse(responseCode = "200", content = {
        @Content(schema = @Schema(implementation = ProfileCandidateResponseDTO.class))
    }),
    @ApiResponse(responseCode = "400", description = "user not found")
})
  public ResponseEntity<Object> get(HttpServletRequest request) {
    var idCandidate = request.getAttribute("candidate_id");

    try {
      var profile = this.profileCandidateUseCase
          .execute(UUID.fromString(idCandidate.toString()));
      return ResponseEntity.ok().body(profile);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/job")
  @PreAuthorize("hasRole('CANDIDATE')")
  @Operation(summary = "Listagem de vagas disponiveis para o candidato", description = "lista de vagas")
  @ApiResponses({
      @ApiResponse(responseCode = "200", content = {
          @Content(array = @ArraySchema(schema = @Schema(implementation = JobEntity.class)))
      })
  })
  @SecurityRequirement(name = "jwt_auth")
  public List<JobEntity> findJobByFilter(@RequestParam String filter) {
    return this.listAllJobsByFilterUseCase.execute(filter);
  }
}