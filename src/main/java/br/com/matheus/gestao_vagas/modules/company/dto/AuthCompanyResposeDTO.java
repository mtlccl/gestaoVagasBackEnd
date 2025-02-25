package br.com.matheus.gestao_vagas.modules.company.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AuthCompanyResposeDTO {

    private String access_token;
    private Long expires_in;
    private List<String> roles;
}