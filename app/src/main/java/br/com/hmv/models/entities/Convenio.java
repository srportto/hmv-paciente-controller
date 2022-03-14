package br.com.hmv.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Convenio {

    @JoinColumn(name = "codigo_convenio", nullable = true)
    private String codigoConvenio;

    @JoinColumn(name = "descricao_convenio", nullable = true)
    private String descricaoConvenio;

    @JoinColumn(name = "numero_carteira_convenio", nullable = true)
    private String numeCarteiraConvenio;

    @JoinColumn(name = "data_validade_convenio", nullable = true)
    private LocalDate dataValidadeConvenio;

}
