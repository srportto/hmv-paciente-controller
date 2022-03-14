package br.com.hmv.services;

import br.com.hmv.dtos.request.PacienteInsertRequestDTO;
import br.com.hmv.dtos.responses.PacienteDefaultResponseDTO;
import br.com.hmv.dtos.responses.PacienteInsertResponseDTO;
import br.com.hmv.exceptions.ResourceNotFoundException;
import br.com.hmv.models.entities.Endereco;
import br.com.hmv.models.entities.Paciente;
import br.com.hmv.models.enums.CadastroPacienteEnum;
import br.com.hmv.models.mappers.PacienteMapper;
import br.com.hmv.repositories.PacienteRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PacienteService {
    private static Logger logger = LoggerFactory.getLogger(PacienteService.class);
    private PacienteRepository pacienteRepository;

    //    private EspecialidadeRepository especialidadeRepository;
    //

    @Transactional
    public PacienteInsertResponseDTO criacao(PacienteInsertRequestDTO dto) {
        String logCode = "criacao(PacienteInsertRequestDTO)";
        logger.info("{} - solicitacao de inclusao {}", logCode, dto);

        var entity = dtoToEntityOnCreate(dto);
        entity = pacienteRepository.save(entity);

        logger.info("{} - Convenio incluido com sucesso {}", logCode, entity);
        return entityToResponseDtoInsert(entity);
    }

    //
//    @Transactional
//    public FuncionarioDefaultResponseDTO updateStatus(String idFuncionario, FuncionarioAtualizaStatusRequestDTO dto) {
//        String logCode = "updateStatus(String, FuncionarioAtualizaStatusRequestDTO)";
//        logger.info("{} - solicitacao de atualizacao de status {}", logCode, dto);
//
//        try {
//            var objOptional = funcionarioRepository.findFuncionarioByIdFuncionario(idFuncionario);
//            Funcionario entity = objOptional.orElseThrow(() -> new ResourceNotFoundException("recurso nao encontrado id: " + idFuncionario));
//
//            //passa status novo
//            entity.setCodigoStatusFuncionario(dto.getStatusFuncionario().getCodigoStatusFuncionario());
//            entity = funcionarioRepository.save(entity);
//
//            logger.info("{} - atualizacao realizada com sucesso {}", logCode, entity);
//            return FuncionarioMapper.INSTANCE.deFuncionarioParaDto(entity);
//
//        } catch (EntityNotFoundException e) {
//            logger.warn("{} - recurso nao encontrado id: {} ", idFuncionario);
//            throw new ResourceNotFoundException("Recurso nao encontrado id: " + idFuncionario);
//        }
//    }
//
//    @Transactional
//    public FuncionarioDefaultResponseDTO addEspecialidade(String idFuncionario, FuncionarioAddEspecialidadeRequestDTO dto) {
//        String logCode = "addEspecialidade(String, HospitalAddEspecialidadeRequestDTO)";
//        logger.info("{} - solicitacao de adicao de especialidade {}", logCode, dto);
//
//        try {
//            var objOptional = funcionarioRepository.findFuncionarioByIdFuncionario(idFuncionario);
//            Funcionario entity = objOptional.orElseThrow(() -> new ResourceNotFoundException("recurso nao encontrado id: " + idFuncionario));
//
//            var entityEspecialidade = especialidadeRepository.getOne(dto.getIdEspecialidade());
//
//            //add especialidade
//            entity.getEspecialidades().add(entityEspecialidade);
//            entity = funcionarioRepository.save(entity);
//
//            logger.info("{} - adicao de especialidade realizada com sucesso {}", logCode, entity);
//            return FuncionarioMapper.INSTANCE.deFuncionarioParaDto(entity);
//
//        } catch (EntityNotFoundException e) {
//            logger.warn("{} - recurso nao encontrado id: {} ", logCode, idFuncionario);
//            throw new ResourceNotFoundException("Recurso nao encontrado id: " + idFuncionario);
//
//        } catch (Exception e) {
//            logger.warn("{} - erro ao adicionar especialidade: {} ", logCode, e);
//            throw new ResourceNotFoundException("Recurso nao encontrado id: " + idFuncionario);
//        }
//    }
//
//    @Transactional
//    public FuncionarioDefaultResponseDTO removeEspecialidade(String idFuncionario, FuncionarioRemoveEspecialidadeRequestDTO dto) {
//        String logCode = "removeEspecialidade(String, FuncionarioRemoveEspecialidadeRequestDTO)";
//        logger.info("{} - solicitacao de remocao de especialidade {}", logCode, dto);
//
//        try {
//            var objOptional = funcionarioRepository.findFuncionarioByIdFuncionario(idFuncionario);
//            Funcionario entity = objOptional.orElseThrow(() -> new ResourceNotFoundException("recurso nao encontrado id: " + idFuncionario));
//
//            var entityEspecialidade = especialidadeRepository.getOne(dto.getIdEspecialidade());
//
//            //remove especialidade
//            entity.getEspecialidades().remove(entityEspecialidade);
//            entity = funcionarioRepository.save(entity);
//
//            logger.info("{} - solicitacao de remocao de especialidade concluida com sucesso {}", logCode, entity);
//            return FuncionarioMapper.INSTANCE.deFuncionarioParaDto(entity);
//        } catch (EntityNotFoundException e) {
//            logger.warn("{} - recurso nao encontrado id: {} ", logCode, idFuncionario);
//            throw new ResourceNotFoundException("Recurso nao encontrado id: " + idFuncionario);
//
//        } catch (Exception e) {
//            logger.warn("{} - erro ao adicionar especialidade: {} ", logCode, e);
//            throw new ResourceNotFoundException("Recurso nao encontrado id: " + idFuncionario);
//        }
//    }
//
//    @Transactional
//    public void delete(String idFuncionario) {
//        String logCode = "delete(String)";
//        logger.info("{} - deletando recurso: {}", logCode, idFuncionario);
//
//        try {
//            funcionarioRepository.deleteByIdFuncionario(idFuncionario);
//            logger.info("{} - recurso deletado com sucesso: {}", logCode, idFuncionario);
//
//        } catch (EmptyResultDataAccessException e) {
//            logger.warn("{} - recurso nao encontrado: {}", logCode, idFuncionario);
//            throw new ResourceNotFoundException("Recurso nao encontrado id: " + idFuncionario);
//
//        } catch (DataIntegrityViolationException e) {
//            logger.warn("{} - erro de integridade de dados: {}", logCode, idFuncionario);
//            throw new DatabaseException("Integrity violation - Ao deletar convenio id: " + idFuncionario);
//        }
//    }
//
//    @Transactional(readOnly = true)
//    public Page<FuncionarioForListResponseDTO> findAllPaged(Pageable pageable) {
//        String logCode = "findAllPaged(Pageable)";
//        logger.info("{} - consulta paginada de recursos vide parametros {}", logCode, pageable);
//
//        Page<Funcionario> list = funcionarioRepository.findAll(pageable);
//        logger.info("{} - consulta paginada de recursos realizada com sucesso: {}", logCode, list);
//        return list.map(itemFuncionarioEntity -> FuncionarioMapper.INSTANCE.deEntityParaRespresentacaoEmLista(itemFuncionarioEntity));
//    }
//
//    @Transactional(readOnly = true)
//    public Page<FuncionarioForListResponseDTO> findAllPagedPorGrupoFuncao(GrupoFuncaoFuncionarioEnum grupoFuncao, Pageable pageable) {
//        String logCode = "findAllPagedPorGrupoFuncao(GrupoFuncaoFuncionarioEnum,Pageable)";
//        logger.info("{} - consulta paginada de recursos vide parametros {} e grupo funcao {}", logCode, pageable, grupoFuncao);
//
//        var grupoFuncaoId = grupoFuncao.getCodigoGrupoFuncaoFuncionario();
//        Page<Funcionario> list = funcionarioRepository.findFuncionarioByCodigoGrupoFuncao(grupoFuncaoId, pageable);
//        logger.info("{} - consulta paginada de recursos por grupo de funcao realizada com sucesso: {}", logCode, list);
//        return list.map(itemFuncionarioEntity -> FuncionarioMapper.INSTANCE.deEntityParaRespresentacaoEmLista(itemFuncionarioEntity));
//    }

    @Transactional(readOnly = true)
    public PacienteDefaultResponseDTO findByIdPaciente(String idPaciente) {
        String logCode = "findByIdPaciente(String)";
        logger.info("{} - buscando recurso pelo id: {}", logCode, idPaciente);

        Optional<Paciente> obj = pacienteRepository.findPacienteByIdPaciente(idPaciente);
        Paciente entity = obj.orElseThrow(() -> new ResourceNotFoundException("recurso nao encontrado id: " + idPaciente));

        logger.info("{} - recurso encontrado: {}", logCode, entity);
        return PacienteMapper.INSTANCE.deEntityParaDtoDefault(entity);
    }


    private Paciente dtoToEntityOnCreate(PacienteInsertRequestDTO dto) {
        String logCode = "dtoToEntityOnCreate(PacienteInsertRequestDTO)";
        logger.info("{} - convertendo dto de cricao para entity {}", logCode, dto);

        var entity = PacienteMapper.INSTANCE.deDtoParaEntity(dto);

        entity.setIdPaciente(UUID.randomUUID().toString());

        entity.setIndicadorTipoCadastroRealizado(CadastroPacienteEnum.SIMPLES.getCodigoStatusCadastroPaciente());

        Endereco endereco = new Endereco();
        endereco.setCodigoEndereco(UUID.randomUUID().toString());
        entity.setEndereco(endereco);

        logger.info("{} - conversao realizada com sucesso {}", logCode, entity);
        return entity;
    }

    private PacienteInsertResponseDTO entityToResponseDtoInsert(Paciente entity) {
        String logCode = "entityToResponseDefault(Paciente)";
        logger.info("{} - convertendo entity para response default {}", logCode, entity);

        var responseDto = PacienteMapper.INSTANCE.deEntityParaDtoInsert(entity);
        responseDto.setIndicadorCadastro(CadastroPacienteEnum.obterStatusCadastroPaciente(entity.getIndicadorTipoCadastroRealizado()));
        logger.info("{} - response default montado com sucesso {}", logCode, responseDto);
        return responseDto;
    }
}
