package br.com.hmv.dtos.responses;

import br.com.hmv.dtos.general.TelefoneDTO;
import br.com.hmv.models.enums.GrupoFuncaoFuncionarioEnum;
import br.com.hmv.models.enums.StatusFuncionarioEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FuncionarioDefaultResponseDTO {

    @JsonProperty("id_funcionario")
    private String idFuncionario;

    @JsonProperty("email")
    private String email;

    @JsonIgnore
    private String senha;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("crm")
    private String crm;

    @JsonProperty("primeiro_nome")
    private String primeiroNome;

    @JsonProperty("nome_completo")
    private String nomeCompleto;

    @JsonProperty("data_nascimento")
    private LocalDate dataNascimento;

    private EnderecoDefaultResponseDTO endereco;

    @JsonProperty("grupo_funcao")
    private GrupoFuncaoFuncionarioEnum grupoFuncaoFuncionario;

    @JsonProperty("status")
    private StatusFuncionarioEnum statusFuncionario;

    private TelefoneDTO telefone;

    private Set<EspecialidadeDTO> especialidades = new HashSet<>();
}