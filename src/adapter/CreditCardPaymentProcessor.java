package adapter;

public class CreditCardPaymentProcessor implements PaymentProcessor{
    @Override
    public void pay(double amount) {
        System.out.println("Pago procesado con Tarjeta de Crédito por: S/ " + amount);
    }
}
