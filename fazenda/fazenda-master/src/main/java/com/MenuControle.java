package com;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 *
 * @author isabela
 */
public class MenuControle {
    
    @FXML 
    private void listagemVacas()throws IOException{
        App.setRoot("listagemVacas");
    } 
    @FXML 
    private void listarPeriodo()throws IOException{
        App.setRoot("listarPeriodo");
    }
    @FXML
    private void vacaExcluir()throws IOException{
        App.setRoot("vacaExcluir");
    }
    
    @FXML 
    private void usuarioExcluir() throws IOException{
        App.setRoot("usuarioExcluir");
    }
    @FXML
    private void producaoIncluir() throws IOException{
        App.setRoot("cadastrarProducao");
    }
    @FXML
    private void usuarioIncluir() throws IOException{
        App.setRoot("usuarioIncluir");
    }
    @FXML 
    private void alterarUsuario() throws IOException{
        App.setRoot("alterarUsuario");
    }
    
    @FXML
    private void listarPorVaca() throws IOException{
        App.setRoot("listarVaca");
    }
    
    @FXML
    private void sair(){
        Platform.exit();
        System.exit(0);
    }
    
    @FXML
    private void vacaAlterar() throws IOException{
        App.setRoot("vacaAlterar");
    }
    
    @FXML
    private void vacaIncluir() throws IOException{
        App.setRoot("vacaIncluir");
    }
     
    
}
