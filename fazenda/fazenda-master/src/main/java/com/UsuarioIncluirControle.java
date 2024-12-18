package com;

import modelo.Usuario;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.Dao;


public class UsuarioIncluirControle {

    @FXML
    private TextField campoLogin;
    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoSenha;

    private Dao<Usuario> dao;
    
    @FXML
    private ImageView imagemFundo;
    
    @FXML
    private void initialize() {
        dao = new Dao(Usuario.class);     
    }

    @FXML
    private void gravar() {
        if (campoLogin.getText().isBlank() || campoNome.getText().isBlank() || campoSenha.getText().isBlank()) {
            mostrarErro("Preencha os campos obrigatórios");
            return;
        }

        String nome = campoNome.getText().toUpperCase();  
        Usuario temp = dao.buscarPorChave("login", campoLogin.getText()); 
        if (temp != null) {
            mostrarErro("Já existe este usuário");
            return;
        }

        // Criação do usuário com os campos corretos
        Usuario usuario = new Usuario(campoLogin.getText(), nome, campoSenha.getText()); 
        dao.inserir(usuario);

        limparCampos();
        mostrarSucesso("Usuário cadastrado com sucesso");
    }

    @FXML
    public void voltarMenu() throws IOException {
        App.setRoot("menu");
    }

    private void limparCampos() {
        this.campoLogin.setText("");
        this.campoNome.setText("");
        this.campoSenha.setText("");
    }

    private void mostrarSucesso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.show();
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.show();
    }
    
}
