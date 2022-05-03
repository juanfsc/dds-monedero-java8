package dds.monedero.model;

import dds.monedero.exceptions.MaximaCantidadDepositosException;
import dds.monedero.exceptions.MaximoExtraccionDiarioException;
import dds.monedero.exceptions.MontoNegativoException;
import dds.monedero.exceptions.SaldoMenorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CuentaTest {
  private Cuenta cuenta;
  private double limiteExtraccionDiario = 1000;
  @BeforeEach
  void init() {
    double montoInicial = 0;

    cuenta = new Cuenta(montoInicial, limiteExtraccionDiario);
  }

  @Test
  void PuedeDepositar() {
    double saldo = 1500;
    cuenta.depositar(saldo);
    assertEquals(cuenta.getSaldo(), saldo);
  }

  @Test
  void NoPuedeDepositarMontoNegativo() {
    assertThrows(MontoNegativoException.class, () -> cuenta.depositar(-1500));
  }

  @Test
  void PuedeRealizarTresDepositos() {
    cuenta.depositar(1500);
    cuenta.depositar(456);
    cuenta.depositar(1900);
    assertEquals(cuenta.getSaldo(), 1500 + 456 + 1900);

  }

  @Test
  void NoPuedeRealizarMasDeTresDepositos() {
    assertThrows(MaximaCantidadDepositosException.class, () -> {
          cuenta.depositar(1500);
          cuenta.depositar(456);
          cuenta.depositar(1900);
          cuenta.depositar(245);
    });
  }

  @Test
  void NoPuedeExtraerMasQueElSaldo() {
    double saldo = 90;
    assertThrows(SaldoMenorException.class, () -> {
          cuenta.setSaldo(90);
          cuenta.extraer(saldo + 1);
    });
  }

  @Test
  void NoPuedeExtraerMasDelLimiteDiario() {
    assertThrows(MaximoExtraccionDiarioException.class, () -> {
      cuenta.setSaldo(5000);
      cuenta.extraer(limiteExtraccionDiario + 1);
    });
  }

  @Test
  void NoPuedeExtraerMontoNegativo() {
    assertThrows(MontoNegativoException.class, () -> cuenta.extraer(-500));
  }

}