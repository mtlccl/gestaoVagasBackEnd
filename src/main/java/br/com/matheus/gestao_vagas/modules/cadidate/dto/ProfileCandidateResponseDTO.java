package br.com.matheus.gestao_vagas.modules.cadidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateResponseDTO {

    @Schema(example = "Dev java")
    private String description;

    @Schema(example = "matheus")
    private String username;

    @Schema(example = "mat@gmail.com")
    private String email;

    @Schema(example = "math lemes")
    private String name;
    private UUID id;
}
