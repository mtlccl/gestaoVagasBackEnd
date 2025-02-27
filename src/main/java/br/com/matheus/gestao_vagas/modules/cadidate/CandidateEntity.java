package br.com.matheus.gestao_vagas.modules.cadidate;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Schema(example = "mat lemes", requiredMode = RequiredMode.REQUIRED, description = "senha do candidato")
  private String name;

  @NotBlank
  @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
  @Schema(example = "matheus", requiredMode = RequiredMode.REQUIRED, description = "username do candidato")
  private String username;

  @Email(message = "O campo [email] deve conter um e-mail válido")
  @Schema(example = "mat@gmail.com", requiredMode = RequiredMode.REQUIRED, description = "email do candidato")
  private String email;

  @Length(min = 10, max = 100, message = "A senha deve conter entre (10) e (100) caracteres")
  @Schema(example = "admin@12345", minLength = 10, maxLength = 100, requiredMode = RequiredMode.REQUIRED, description = "senha do candidato")
  private String password;

  @Schema(example = "dev java", requiredMode = RequiredMode.REQUIRED, description = "descricao candidato")
  private String description;
  private String curriculum;

  @CreationTimestamp
  private LocalDateTime createdAt;
}