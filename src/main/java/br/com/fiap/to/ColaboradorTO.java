package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;

/**
 * Classe que representa um colaboraador do IMREA, responsável pelo gerenciamento de lembretes,
 * atendimentos e profissionais da saúde.
 * @version 3.0
 */
public class ColaboradorTO extends PessoaTO {
    private Long idColaborador;
    @NotBlank(message = "O atributo unidade é obrigatório")
    private String unidade;

    public ColaboradorTO() {
    }

    public Long getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Long idColaborador) {
        this.idColaborador = idColaborador;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }
}
