# Práctica Calificada 1: Tienda Virtual con Patrones de Diseño

## 1. Descripción del problema
El proyecto consiste en una aplicación de consola en Java que simula el flujo de compra de una tienda virtual. El objetivo principal es resolver la falta de flexibilidad que suele ocurrir al gestionar reglas de negocio cambiantes. Específicamente, se busca que el sistema pueda aplicar distintos tipos de promociones o descuentos, integrarse con pasarelas de pago de terceros que tienen métodos incompatibles, y ejecutar múltiples tareas secundarias tras confirmar la compra (como actualizar stock o enviar correos) sin que estas dependencias saturen la lógica principal del negocio.

## 2. Implementación de patrones de diseño

### Patrón Strategy (Módulo de Descuentos)
Se utilizó para separar la lógica del cálculo de descuentos de la estructura del carrito de compras. A través de la interfaz `DiscountStrategy`, definimos un comportamiento común para todas las promociones. Las estrategias concretas (`PercentageDiscountStrategy`, `FixedAmountDiscountStrategy` y `NoDiscountStrategy`) encapsulan sus propias operaciones matemáticas. Esto permite cambiar el tipo de descuento asignado en tiempo de ejecución mediante composición, cumpliendo con el principio de Abierto/Cerrado (Open/Closed).

### Patrón Adapter (Pasarela de Pagos)
La tienda define internamente la interfaz `PaymentProcessor` con el método estándar `pay(double amount)`. Sin embargo, el servicio de terceros `ExternalPayPalService` utiliza una firma incompatible: `makePayment(String currency, double amount)`. Para solucionar esto sin alterar el código de la pasarela externa, se implementó `PayPalAdapter`. Esta clase intermedia implementa nuestra interfaz local y, por dentro, traduce los parámetros para llamar correctamente al servicio externo simulando un entorno real de integración.

### Patrón Observer (Sistema de Notificaciones)
Se aplicó para desacoplar el servicio de órdenes (`OrderService`) de los componentes encargados de las alertas y operaciones post-venta. `OrderService` actúa como el Sujeto observable, manteniendo una lista de interesados. Al concretarse la compra, se invoca el método de notificación que recorre y actualiza a cada observador registrado (`EmailNotificationObserver`, `InventoryObserver` y `AdminNotificationObserver`). Gracias a esto, si en el futuro se requiere añadir una nueva acción (como generar una factura electrónica), solo se crea un nuevo observador sin modificar la lógica de confirmación de la orden.

## 3. Ejemplo de ejecución en consola
<img width="1272" height="654" alt="image" src="https://github.com/user-attachments/assets/96e71c67-c8d0-4aff-9d4b-120e5d3064d7" />
