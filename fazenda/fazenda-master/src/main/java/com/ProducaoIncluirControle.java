package com;

import modelo.Producao;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modelo.Vaca;
import util.Dao;

/**
 *
 * @author Istefa
 */
public class ProducaoIncluirControle {

    @FXML
    private DatePicker data;

    @FXML
    private ComboBox<String> listar;

    @FXML
    private TextField litros;

    Dao<Vaca> dao;
    
    @FXML
    private void initialize(){
        data.setValue(LocalDate.now());

        dao = new Dao(Vaca.class);
        ArrayList<String> listaTemp = new ArrayList<>();
        for (Vaca v : dao.listarTodos()){
            listaTemp.add(v.getBrinco());
        }
        listar.getItems().setAll(listaTemp);
    }

    @FXML
    public void registrar(){

        if (litros.getText().isEmpty() || listar.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Ocorreu um erro!");
            alert.setContentText("Preencha os campos");
            alert.show();
            return;
        }

      
       
        Vaca vaca = dao.buscarPorChave("brinco", listar.getValue());
        
    
        //registra
        Producao producao = new Producao(data.getValue(), vaca,  Double.valueOf(litros.getText()));
        Dao<Producao> daoprod = new Dao(Producao.class);
        daoprod.inserir(producao);

        //aviso de sucesso
        Alert sucesso = new Alert(Alert.AlertType.INFORMATION);
        sucesso.setTitle("Sucesso");
        sucesso.setHeaderText("Sucesso");
        sucesso.setContentText("Produção registrada com sucesso!");
        sucesso.show();

        //deixa os campos em branco
        litros.setText("");
        listar.setValue(null);

    }

    @FXML
    public void voltar(){
        try{
            App.setRoot("menu");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}