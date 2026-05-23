package adapter;

public class YapePaymentProcessor implements PaymentProcessor{
    @Override
    public void pay(double amount) {
        System.out.println("Pago procesado con Yape por: S/ " + amount);
    }
}
