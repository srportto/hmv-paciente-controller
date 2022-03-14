package br.com.hmv.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConvenioRequestDTO {
    private static final long serialVersionUID = 1L;

    @JsonProperty("codigo_convenio")
    private String codigoConvenio;

    @JsonProperty("descricao_convenio")
    private String descricaoConvenio;

    @JsonProperty("numero_carteira_convenio")
    private String numeroCarteiraConvenio;

    @JsonProperty("data_validade_convenio")
    private LocalDate dataValidadeConvenio;
}
