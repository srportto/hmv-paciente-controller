package br.com.hmv.dtos.responses;

import br.com.hmv.dtos.general.TelefoneDTO;
import br.com.hmv.models.entities.Telefone;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneDefaultResponseDTO extends TelefoneDTO{

    @JsonProperty("codigo_pais")
    private Integer codigoPais;

    @JsonProperty("codigo_area")
    private Integer codigoArea;

    @JsonProperty("numero")
    private Integer numero;

    @JsonProperty("descricao")
    private String descricao;

    //? construtor diferenciado - de entity para DTO
    public TelefoneDefaultResponseDTO(Telefone entity) {
      super(entity);
    }
}
