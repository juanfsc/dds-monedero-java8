package dds.monedero.model;

import dds.monedero.exceptions.MaximaCantidadDepositosException;
import dds.monedero.exceptions.MaximoExtraccionDiarioException;
import dds.monedero.exceptions.MontoNegativoException;
import dds.monedero.exceptions.SaldoMenorException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cuenta {

  private double saldo = 0;
  private double limiteExtraccionDiario = 1000;
  private List<Movimiento> movimientos = new ArrayList<>();

  public Cuenta(double montoInicial, double limiteExtraccionDiario) {
    this.saldo = montoInicial;
    this.limiteExtraccionDiario =  limiteExtraccionDiario;
  }

  public void setMovimientos(List<Movimiento> movimientos) {
    this.movimientos = movimientos;
  }

  public void depositar(double cuanto) {
    puedeRealizarMovimiento(cuanto);
    puedeRealizarDeposito();
    setSaldo(getSaldo()+cuanto);
    agregarMovimiento(new Deposito(LocalDate.now(), cuanto));
  }

  public void extraer(double cuanto) {
    puedeRealizarMovimiento(cuanto);
    puedeSacarNoExcedeSaldo(cuanto);
    puedeSacarLimiteDiario(cuanto);
    setSaldo(getSaldo()-cuanto);
    agregarMovimiento(new Extraccion(LocalDate.now(), cuanto));
  }

  public void agregarMovimiento(Movimiento movimiento) {
    movimientos.add(movimiento);
  }

  public double getMontoExtraidoA(LocalDate fecha) {
    return getMovimientos().stream()
        .filter(movimiento -> !movimiento.isDeposito() && movimiento.getFecha().equals(fecha))
        .mapToDouble(Movimiento::getMonto)
        .sum();
  }

  public List<Movimiento> getMovimientos() {
    return movimientos;
  }

  public double getSaldo() {
    return saldo;
  }

  private void setSaldo(double saldo) {
    this.saldo = saldo;
  }

  private void puedeSacarNoExcedeSaldo(double cuanto) {
    if (getSaldo() - cuanto < 0) {
      throw new SaldoMenorException("No puede sacar mas de " + getSaldo() + " $");
    }
  }

  private void puedeSacarLimiteDiario(double cuanto) {
    double montoExtraidoHoy = getMontoExtraidoA(LocalDate.now());
    double limiteRestante = this.limiteExtraccionDiario - montoExtraidoHoy;
    if (cuanto > limiteRestante) {
      throw new MaximoExtraccionDiarioException("No puede extraer mas de $ " + 1000
          + " diarios, límite: " + limiteRestante);
    }
  }

  private void puedeRealizarDeposito() {
    if (getMovimientos().stream().filter(movimiento -> movimiento.isDeposito()).count() >= 3) {
      throw new MaximaCantidadDepositosException("Ya excedio los " + 3 + " depositos diarios");
    }
  }

  private void puedeRealizarMovimiento(double cuanto) {
    if (cuanto <= 0) {
      throw new MontoNegativoException(cuanto + ": el monto del movimiento debe ser un valor positivo");
    }
  }

}
