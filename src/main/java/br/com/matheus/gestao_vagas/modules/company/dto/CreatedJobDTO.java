package br.com.matheus.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.Data;

@Data
public class CreatedJobDTO {
    @Schema(example = "Vaga para devs JR", requiredMode = RequiredMode.REQUIRED)
    private String description;

    @Schema(example = "Plano de saude", requiredMode = RequiredMode.REQUIRED)
    private String benefits;

    @Schema(example = "JR", requiredMode = RequiredMode.REQUIRED)
    private String level;

}
