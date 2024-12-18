package com;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import modelo.Usuario;
import util.Dao;

public class AlterarUsuarioControle {
    
    @FXML
    private TextField nome;
    
    @FXML
    private TextField senha;
    
    @FXML 
    private ComboBox<String> listar;
    
    @FXML
    private void initialize() {
        Dao<Usuario> dao = new Dao<>(Usuario.class);
        ArrayList<String> listaTemp = new ArrayList<>();
        
        for (Usuario user : dao.listarTodos()) {
            listaTemp.add(user.getNome());
        }
        
        listar.getItems().setAll(listaTemp);
    }

    @FXML
    public void gravar() {
        if (nome.getText().isEmpty() || senha.getText().isEmpty() || listar.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro!");
            alert.setContentText("Preencha todos os campos.");
            alert.show();
            return; 
        }

        
        Dao<Usuario> dao = new Dao<>(Usuario.class);
        Usuario usuario = new Usuario(listar.getValue(),nome.getText(), senha.getText());

        dao.alterar("nome", listar.getValue(), usuario);

        Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
        sucesso.setTitle("Sucesso");
        sucesso.setHeaderText("Alteração realizada com sucesso!");
        sucesso.setContentText("As informações foram alteradas com sucesso.");
        sucesso.show();

        nome.setText("");
        senha.setText("");
        listar.setValue(null);
    }
    
    @FXML
    public void voltarMenu() throws IOException {
        App.setRoot("menu");
    }

}
