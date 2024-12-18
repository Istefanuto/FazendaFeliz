/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;
import modelo.Vaca;
import org.bson.codecs.pojo.annotations.BsonProperty;

/**
 *
 * @author Istefa
 */
public class Producao {
    
    @BsonProperty(value="data")
    private LocalDate data;

    @BsonProperty(value="vaca")
    private Vaca vaca;

    @BsonProperty(value="quantidadeLitros")
    private Double quantidadeLitros;

    public Producao(){}
    
     public Producao(LocalDate data, Vaca vaca, Double quantidadeLitros) {
        this.data = data;
        this.vaca = vaca;
        this.quantidadeLitros = quantidadeLitros;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setVaca(Vaca vaca) {
        this.vaca = vaca;
    }

    public void setQuantidadeLitros(Double quantidadeLitros) {
        this.quantidadeLitros = quantidadeLitros;
    }

    public LocalDate getData(){
        return data;
    }

    public Vaca getVaca() {
        return vaca;
    }

    public Double getQuantidadeLitros() {
        return quantidadeLitros;
    }

}
