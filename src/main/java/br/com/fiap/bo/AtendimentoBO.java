package br.com.fiap.bo;

import br.com.fiap.dao.AtendimentoDAO;
import br.com.fiap.dao.LembreteDAO;
import br.com.fiap.dao.PacienteDAO;
import br.com.fiap.dao.ProfissionalSaudeDAO;
import br.com.fiap.enums.StatusAtendimento;
import br.com.fiap.exception.AtendimentoException;
import br.com.fiap.exception.PacienteException;
import br.com.fiap.exception.ProfissionalSaudeException;
import br.com.fiap.to.AtendimentoTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class AtendimentoBO {
    public AtendimentoTO save(AtendimentoTO atendimentoTO) throws ProfissionalSaudeException, PacienteException, AtendimentoException {

        PacienteDAO pacienteDAO = new PacienteDAO();

        if (pacienteDAO.findById(atendimentoTO.getIdPaciente()) == null) {
            throw new PacienteException("Não existe nenhum paciente com o ID informado");
        }

        ProfissionalSaudeDAO profissionalSaudeDAO = new ProfissionalSaudeDAO();

        if (profissionalSaudeDAO.findById(atendimentoTO.getIdProfissionalSaude()) == null) {
            throw new ProfissionalSaudeException("Não existe nenhum profissional da saúde com o ID informado");
        }

        if (atendimentoTO.getData().isBefore(LocalDate.now())) {
            throw new AtendimentoException("Não é possível agendar um atendimento em uma data passada.");
        }

        atendimentoTO.setStatus(StatusAtendimento.MARCADO);

        AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
        return atendimentoDAO.save(atendimentoTO);
    }

    public AtendimentoTO update(AtendimentoTO atendimentoTO) throws ProfissionalSaudeException, PacienteException, AtendimentoException {

        AtendimentoDAO atendimentoDAO = new AtendimentoDAO();

        AtendimentoTO atendimentoEncontrado = atendimentoDAO.findById(atendimentoTO.getIdAtendimento());
        if (atendimentoEncontrado == null) {
            throw new AtendimentoException("Não existe nenhum atendimento com o ID informado.");
        }

        if (atendimentoEncontrado.getStatus() == StatusAtendimento.CANCELADO ||
                atendimentoEncontrado.getStatus() == StatusAtendimento.CONCLUIDO) {
            throw new AtendimentoException("Não é permitido alterar um atendimento que já foi Cancelado ou Concluído.");
        }

        if (!atendimentoEncontrado.getIdPaciente().equals(atendimentoTO.getIdPaciente())) {
            throw new AtendimentoException("Não é permitido alterar o paciente de um atendimento já existente.");
        }

        if (atendimentoTO.getData().isBefore(LocalDate.now())) {
            throw new AtendimentoException("Não é possível agendar um atendimento em uma data passada.");
        }

        ProfissionalSaudeDAO profissionalSaudeDAO = new ProfissionalSaudeDAO();
        if (profissionalSaudeDAO.findById(atendimentoTO.getIdProfissionalSaude()) == null) {
            throw new ProfissionalSaudeException("Não existe nenhum profissional da saúde com o ID informado");
        }

        return atendimentoDAO.update(atendimentoTO);
    }

    public AtendimentoTO concluir(Long id) throws AtendimentoException {

        AtendimentoDAO atendimentoDAO = new AtendimentoDAO();

        AtendimentoTO atendimento = atendimentoDAO.findById(id);
        if (atendimento == null) {
            throw new AtendimentoException("Não existe nenhum atendimento com o ID informado.");
        }

        if (atendimento.getStatus() != StatusAtendimento.MARCADO && atendimento.getStatus() != StatusAtendimento.REMARCADO) {
            throw new AtendimentoException("Só é possível concluir atendimentos Marcados ou Remarcados.");
        }

        atendimento.setStatus(StatusAtendimento.CONCLUIDO);

        return atendimentoDAO.update(atendimento);
    }

    public boolean delete(Long id) {
        AtendimentoDAO atendimentoDAO = new AtendimentoDAO();

        if (atendimentoDAO.findById(id) == null) {
            return false;
        }

        LembreteDAO lembreteDAO = new LembreteDAO();
        lembreteDAO.deleteByAtendimento(id);

        return atendimentoDAO.delete(id);
    }

    public ArrayList<AtendimentoTO> findAll() {
        AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
        return atendimentoDAO.findAll();
    }

    public AtendimentoTO findById(Long id) {
        AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
        return atendimentoDAO.findById(id);
    }

    public ArrayList<AtendimentoTO> findAllByPaciente(Long idPaciente) throws PacienteException {
        PacienteDAO pacienteDAO = new PacienteDAO();
        if (pacienteDAO.findById(idPaciente) == null) {
            throw new PacienteException("Não existe nenhum paciente com o ID informado");
        }

        AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
        return atendimentoDAO.findAllByPaciente(idPaciente);
    }

}
