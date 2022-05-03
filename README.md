## Monedero
### Code smells
- Cuenta.setSaldo() no deberíamos querer cambiar el *Saldo* de un *Cuenta* sin realizar un movimiento.
- Dos constructores de *Cuenta*, uno no era utilizado.
- Cuenta.setSaldo(), ¿está bien poder cambiar la lista de *Movimientos* de una *Cuenta* ?
- Cuenta.sacar() no decrementa el valor del atributo *Saldo* de la Clase *Cuenta*.
- Cuenta.poner() no incrementa el valor del atributo *Saldo* de la Clase *Cuenta*.
- el Atributo *Saldo* sería una especie de *Cache*, este valor podría calcularse haciendo una Sumatoria de los montos de
todos los movimientos, teniendo en cuenta que las extracciones tendrian monto negativo, de ser numerosos *Movimientos*
podría justificarse el guardado del Saldo en cada extraccion/depósito 
- Cuenta.sacar() y Cuenta.poner() vs Cuenta.extraer() y Cuenta.depositar(), los segundos nombres parecen ser mas acordes
al dominio.
- Las validaciones en Cuenta.sacar() y Cuenta.poner() incomodan la lectura de los metodos, se abstraen las validaciones 
a metodos separados.
- ¿ Tiene sentido Separar la idea de un Movimiento en clases Extraccion y Deposito en lugar de manejar un boolean 
isDeposito dentro de Movimiento.
### Contexto

Este repositorio contiene el código de un _monedero virtual_, al que podemos agregarle y quitarle dinero, a través 
de los métodos `Monedero.sacar` y `Monedero.poner`, respectivamente. 
Pero hay algunos problemas: por un lado el código no está muy bien testeado, y por el otro, hay numerosos _code smells_. 

### Consigna

Tienes seis tareas: 

 1. :fork_and_knife: Haz un _fork_ de este repositorio (presionando desde Github el botón Fork)
 2. :arrow_down: Descargalo y construye el proyecto, utilizando `maven`
 2. :nose: Identifica y anota todos los _code smells_ que encuentres 
 3. :test_tube: Agrega los tests faltantes y mejora los existentes. 
     * :eyes: Ojo: ¡un test sin ningún tipo de aserción está incompleto!
 4. :rescue_worker_helmet: Corrige smells, de a un commit por vez. 
 5. :arrow_up: Sube todos los cambios a tu _fork_
 6. :bug: Activa los issues de Github desde https://github.com/TU_GITHUB_USERNAME/dds-monedero-java8/settings así podemos darte nuestras devoluciones

### Tecnologías usadas

* Java 8.
* JUnit 5. :warning: La versión 5 de JUnit es la más nueva del framework y presenta algunas diferencias respecto a la versión "clásica" (JUnit 4). Para mayores detalles, ver:
    *  [Apunte de herramientas](https://docs.google.com/document/d/1VYBey56M0UU6C0689hAClAvF9ILE6E7nKIuOqrRJnWQ/edit#heading=h.dnwhvummp994)
    *  [Entrada de Blog (en inglés)](https://www.baeldung.com/junit-5-migration)
    *  [Entrada de Blog (en español)](https://www.paradigmadigital.com/dev/nos-espera-junit-5/)
* Maven 3.3 o superior
 

  


