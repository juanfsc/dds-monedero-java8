package dds.monedero.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;


class MovimientoTest {
  Movimiento deposito;
  Movimiento extraccion;

  @BeforeEach
  void init(){
    deposito = new Deposito(LocalDate.now(), 1000);
    extraccion = new Extraccion(LocalDate.now(), 1000);
  }

  @Test
  void getMonto() {
    Assertions.assertEquals(1000, deposito.getMonto());
  }

  @Test
  void fueDepositado() {
    Assertions.assertTrue(deposito.fueDepositado(extraccion.getFecha()));
  }

  @Test
  void noFueDepositado() {
    LocalDate datePrevia = LocalDate.parse("2000-01-01");
    Assertions.assertFalse(deposito.fueDepositado(datePrevia));
    Assertions.assertFalse(extraccion.fueDepositado(extraccion.getFecha()));
  }

  @Test
  void fueExtraido() {
    Assertions.assertTrue(extraccion.fueExtraido(extraccion.getFecha()));
  }

  @Test
  void noFueExtraido() {
    LocalDate datePrevia = LocalDate.parse("2000-01-01");
    Assertions.assertFalse(extraccion.fueExtraido(datePrevia));
    Assertions.assertFalse(deposito.fueExtraido(deposito.getFecha()));
  }

  @Test
  void esDeLaFecha() {
    Assertions.assertTrue(extraccion.esDeLaFecha(extraccion.getFecha()));
    Assertions.assertTrue(deposito.esDeLaFecha(deposito.getFecha()));
  }

  @Test
  void noEsDeLaFecha() {
    LocalDate datePrevia = LocalDate.parse("2000-01-01");
    Assertions.assertFalse(extraccion.esDeLaFecha(datePrevia));
    Assertions.assertFalse(deposito.esDeLaFecha(datePrevia));
  }

  @Test
  void isDeposito() {
    Assertions.assertTrue(deposito.isDeposito());
  }

  @Test
  void isExtraccion() {
    Assertions.assertTrue(extraccion.isExtraccion());
  }
}