/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import modelo.Vaca;
import javafx.scene.control.ComboBox;
import java.util.ArrayList;
import util.Dao;

/**
 *
 * @author Istefa
 */
public class AlterarVacaControle {
    @FXML
    private TextField nome;
    
    @FXML
    private TextField raca;
    
    @FXML
    private ComboBox<String> listar;
     
    @FXML
    private void initialize() {
        Dao<Vaca> dao = new Dao(Vaca.class);
        ArrayList<String> listaTemp = new ArrayList<>();
        for (Vaca v : dao.listarTodos()){
            listaTemp.add(v.getBrinco());
        }
        listar.getItems().setAll(listaTemp);
    }
    @FXML
    public void alterar(){
        if (nome.getText().isEmpty() || raca.getText().isEmpty() || listar.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro!");
            alert.setContentText("Preencha os campos");
            alert.show();
            return;
        }
        Dao<Vaca> dao = new Dao(Vaca.class);
        Vaca vaca = new Vaca(listar.getValue(), nome.getText(), raca.getText());
        dao.alterar("brinco", listar.getValue(), vaca);

        //aviso de sucesso
        Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
        sucesso.setTitle("Sucesso");
        sucesso.setHeaderText("Sucesso");
        sucesso.setContentText("Alteração realizada com sucesso!");
        sucesso.show();

        //deixa os campos em branco
        nome.setText("");
        listar.setValue("");
        raca.setText("");
        listar.setValue(null);
    }

    @FXML
    public void voltarMenu() throws IOException {
        App.setRoot("menu");
    }

}

