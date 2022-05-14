package dds.monedero.model;

import java.time.LocalDate;

public class Deposito extends Movimiento {
  public Deposito(LocalDate fecha, double monto) {
    super(fecha, monto);
  }
  
  @Override
  public boolean isDeposito(){
    return true;
  }

  @Override
  public boolean isExtraccion(){
    return false;
  }

  @Override
  public double calcularValor(){
    return super.getMonto();
  }

  @Override
  public boolean extraidoEn(LocalDate date){
    return false;
  }
}
