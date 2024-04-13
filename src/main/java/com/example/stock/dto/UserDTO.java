package com.example.stock.dto;

import com.example.stock.domain.User;
import com.example.stock.domain.enums.NivelAcesso;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;
    @NotEmpty(message = "Campo Email é requerido")
    private String email;
    @NotEmpty(message = "Campo Senha é requerido")
    private String senha;
    public UserDTO() {
        super();
    }
    public UserDTO(User obj) {
        super();
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}