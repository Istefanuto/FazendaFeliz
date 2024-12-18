package com;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Producao;
import util.Dao;

/**
 * Controlador para listar a produção por mês e dia.
 */
public class ListarPeriodoControle {

    @FXML
    private ComboBox<Month> comboMeses; // ComboBox para selecionar o mês

    @FXML
    private DatePicker datePickerDia; // DatePicker para selecionar o dia

    @FXML
    private TableView<Producao> tabelaProducao;

    @FXML
    private TableColumn<Producao, String> colunaBrinco;

    @FXML
    private TableColumn<Producao, LocalDate> colunaData;

    @FXML
    private TableColumn<Producao, Double> colunaQuantidade;

    private Dao<Producao> daoProducao;

    private ObservableList<Producao> listaProducao;

    @FXML
    private void initialize() {
        daoProducao = new Dao<>(Producao.class);
        listaProducao = FXCollections.observableArrayList();

        // Popula o ComboBox com os meses do ano
        comboMeses.getItems().setAll(Month.values());
        
    colunaBrinco.setCellValueFactory(data -> {
        if (data.getValue().getVaca() != null) {
        return new SimpleStringProperty(data.getValue().getVaca().getBrinco());
        }
        return new SimpleStringProperty(""); // Caso a vaca seja null, retorna uma string vazia
        });
    
        // Configura as colunas da tabela
        colunaData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidadeLitros"));

        // Associa a lista à tabela
        tabelaProducao.setItems(listaProducao);
    }

    @FXML
    private void procurar() {
        // Limpa a lista anterior
        listaProducao.clear();

        // Obtém o mês selecionado e o dia selecionado
        Month mesSelecionado = comboMeses.getValue();
        LocalDate diaSelecionado = datePickerDia.getValue();

        // Verifica se pelo menos um dos filtros foi selecionado
        if (mesSelecionado == null && diaSelecionado == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nenhum Filtro Selecionado");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, selecione um mês ou um dia para filtrar a produção.");
            alert.show();
            return;
        }

        // Busca todas as produções
        List<Producao> producoes = daoProducao.listarTodos();

        for (Producao producao : producoes) {
            LocalDate dataProducao = producao.getData();

            // Filtra por mês ou dia
            boolean filtroMes = mesSelecionado != null && dataProducao.getMonth() == mesSelecionado;
            boolean filtroDia = diaSelecionado != null && dataProducao.equals(diaSelecionado);

            if (filtroMes || filtroDia) {
                listaProducao.add(producao);
            }
        }

        // Verifica se a lista está vazia após o filtro
        if (listaProducao.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Nenhum Resultado");
            alert.setHeaderText(null);
            alert.setContentText("Nenhuma produção encontrada para o período selecionado.");
            alert.show();
        }
    }

    @FXML
    public void voltarMenu() throws IOException {
        App.setRoot("menu");
    }
}
