/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import modelo.Usuario;
import util.Dao;

/**
 *
 * @author Istefa
 */
public class LoginControle {
    @FXML
    private TextField login;
    @FXML
    private TextField senha;
    
    private Dao<Usuario> dao;
    
    @FXML
    private void initialize() {
        dao = new Dao<>(Usuario.class);  
    }
    @FXML
    private void cadastrarNovo() throws IOException{
       App.setRoot("CadastroTelaLogin");
    }
    
    @FXML
    private void logar() {
        if (login.getText().isBlank() || senha.getText().isBlank()) {
            mostrarErro("Preencha os campos obrigatórios");
            return;
        }

        String loginText = login.getText().toUpperCase();
        Usuario usuario = dao.buscarPorChave("login", loginText);

        if (usuario == null || !usuario.getSenha().equals(senha.getText())) {
            mostrarErro("Login ou senha inválidos");
            return;
        }

        try {
            App.setRoot("menu");
        } catch (IOException e) {
            mostrarErro("Erro ao carregar o menu");
        }
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.show();
    }
}