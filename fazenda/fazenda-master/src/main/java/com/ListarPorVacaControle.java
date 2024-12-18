/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com;

import java.io.IOException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Producao;
import modelo.Vaca;
import util.Dao;

/**
 *
 * @author Istefa
 */
public class ListarPorVacaControle {
    @FXML
    private ComboBox<String> listar;

    @FXML
    private TableView<Producao> tabelaProducao;

    @FXML
    private TableColumn<Producao, String> colunaData;
    
    @FXML
    private TableColumn<Producao, String> colunaQuantidade;
    
    private Dao<Vaca> dao;
    
    ObservableList<Producao> listaProducao ;
    
    @FXML
    private void initialize() {
        dao = new Dao<>(Vaca.class);
        ArrayList<String> listaTemp = new ArrayList<>();
        for (Vaca v : dao.listarTodos()){
            listaTemp.add(v.getBrinco());
        }
        listar.getItems().setAll(listaTemp);
        listaProducao = FXCollections.observableArrayList();
    
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidadeLitros"));

        Dao<Producao> daoProducao= new Dao(Producao.class);
        tabelaProducao.setItems(listaProducao);
    
        
    }

    private boolean novaBuscaAtiva = false;

    
    @FXML
private void procurar() {
    // Obtém o brinco selecionado no ComboBox
    String brinco = listar.getValue();

    // Verifica se algum brinco foi selecionado
    if (brinco == null || brinco.isBlank()) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Nenhum Brinco Selecionado");
        alert.setHeaderText(null);
        alert.setContentText("Por favor, selecione um brinco no ComboBox.");
        alert.show();
        return;
    }

    // Busca a vaca com o brinco selecionado
    Vaca vacaEncontrada = dao.buscarPorChave("brinco", brinco);
    if (vacaEncontrada == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Vaca Não Encontrada");
        alert.setHeaderText(null);
        alert.setContentText("Não foi possível encontrar uma vaca com o brinco " + brinco);
        alert.show();
        return;
    }

    // Exibe mensagem de sucesso
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Vaca Encontrada");
    alert.setHeaderText(null);
    alert.setContentText("A vaca com brinco " + brinco + " foi encontrada. Carregando todas as produções.");
    alert.show();

    // Carrega todas as produções associadas a essa vaca
    Dao<Producao> daoProducao = new Dao<>(Producao.class);
    List<Producao> producoes = daoProducao.listarTodos();

    // Limpa a lista anterior de produções
    listaProducao.clear();

    // Adiciona todas as produções dessa vaca à lista
    for (Producao p : producoes) {
        if (p.getVaca().getBrinco().equals(brinco)) {
            listaProducao.add(p);
        }
    }

    // Atualiza a tabela com a lista de produções
    tabelaProducao.setItems(listaProducao);
}
    
    @FXML
    public void voltarMenu() throws IOException {
        App.setRoot("menu");
    }
}
