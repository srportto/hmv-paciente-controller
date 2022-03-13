package br.com.hmv.services.validation.funcionario.add_especialidade;

import br.com.hmv.dtos.request.FuncionarioAddEspecialidadeRequestDTO;
import br.com.hmv.exceptions.FieldMessage;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Log4j2
public class FuncionarioAddEspecialidadeValidator implements ConstraintValidator<FuncionarioAddEspecialidadeValid, FuncionarioAddEspecialidadeRequestDTO> {


    @Override
    public void initialize(FuncionarioAddEspecialidadeValid ann) {
    }

    @Override
    public boolean isValid(FuncionarioAddEspecialidadeRequestDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        //Retorno booleano (true/false) de acordo com a resposta da pergunta se a lista esta preenchida ou nao
        return list.isEmpty();
    }
}
