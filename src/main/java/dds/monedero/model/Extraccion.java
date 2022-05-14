package dds.monedero.model;

import java.time.LocalDate;

public class Extraccion extends Movimiento {
  public Extraccion(LocalDate fecha, double monto) {
    super(fecha, monto);
  }
  
  @Override
  public boolean isDeposito(){
    return false;
  }
  
  @Override
  public boolean isExtraccion(){
    return true;
  }

  @Override
  public double calcularValor(){
    return (-1)*super.getMonto();
  }

  @Override
  public boolean extraidoEn(LocalDate date){
    return date.equals(super.getFecha());
  }
}
