package br.com.hmv.models.mappers;

import br.com.hmv.dtos.request.PacienteInsertRequestDTO;
import br.com.hmv.dtos.responses.PacienteInsertResponseDTO;
import br.com.hmv.models.entities.Paciente;
import br.com.hmv.models.enums.CadastroPacienteEnum;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapperConfig.class, componentModel = "spring")
public abstract class PacienteMapper {
    public static final PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

    public abstract Paciente deDtoParaEntity(PacienteInsertRequestDTO dto);

    @BeforeMapping
    protected void preparaAntesDeMapearEntityParaDtoInsert(Paciente entity, @MappingTarget PacienteInsertResponseDTO dto) {
        dto.setIndicadorCadastro(CadastroPacienteEnum.obterStatusCadastroPaciente(entity.getIndicadorCadastroCompleto()));
    }

    public abstract PacienteInsertResponseDTO deEntityParaDtoInsert(Paciente entity);


//    @AfterMapping
//    protected void ajustaDepoisDeMapearEntityParaDtoDefault(Paciente entity, @MappingTarget PacienteInsertResponseDTO dto) {
//        dto.getEspecialidades().clear();
//        var especialidades = entity.getEspecialidades();
//        especialidades.forEach(especialidadeItem -> dto.getEspecialidades().add(EspecialidadeMapper.INSTANCE.deEspecialidadeParaDto(especialidadeItem)));
//    }


//    @BeforeMapping
//    protected void preparaAntesDeMapearEntityParaListDto(Paciente entity, @MappingTarget FuncionarioForListResponseDTO dto) {
//        dto.setStatusFuncionario(StatusFuncionarioEnum.obterStatusFuncionario(entity.getCodigoStatusFuncionario()));
//        dto.setGrupoFuncaoFuncionario(GrupoFuncaoFuncionarioEnum.obterGrupoFuncaoFuncionario(entity.getCodigoGrupoFuncao()));
//    }
//
//    public abstract FuncionarioForListResponseDTO deEntityParaRespresentacaoEmLista(Paciente entity);
}
