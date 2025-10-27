package br.com.fiap.to;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

/**
 * Classe abstrata que representa os dados básicos de uma pessoa no sistema
 * @version 2.0
 */
public abstract class PessoaTO {
    @NotBlank (message = "O atributo nome completo é obrigatório")
    private String nomeCompleto;
    @NotNull (message = "O atributo Data de Nascimento é obrigatório")
    @PastOrPresent (message = "A data de nascimento obrigatoriamente tem que ser no passado")
    private LocalDate dataNascimento;
    @NotBlank (message = "O atributo CPF é obrigatório")
    private String cpf;
    @NotBlank (message = "O atributo telefone é obrigatório")
    private String telefone;
    @Email (message = "O atributo e-mail deve seguir um formato válido")
    @NotNull (message = "O atributo e-mail é obrigatório")
    private String email;

    public PessoaTO() {
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
