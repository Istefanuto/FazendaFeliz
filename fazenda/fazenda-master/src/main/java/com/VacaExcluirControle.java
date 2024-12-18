/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com;

import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import modelo.Vaca;
import util.Dao;

/**
 *
 * @author Istefa
 */
public class VacaExcluirControle {
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
    public void excluir() {
        if (listar.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro!");
            alert.setContentText("Preencha os campos");
            alert.show();
            return;
        }
        Dao<Vaca> dao = new Dao(Vaca.class);
        dao.excluir("brinco", listar.getValue());

        //aviso de sucesso
        Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
        sucesso.setTitle("Sucesso");
        sucesso.setHeaderText("Sucesso");
        sucesso.setContentText("Exclusão realizada com sucesso!");
        sucesso.show();

        //deixa os campos em branco
        listar.setValue(null);
    }
    
    @FXML
    public void voltarMenu() throws IOException {
        App.setRoot("menu");
    }
    
}
