import model.Cart;
import model.Product;
import strategy.DiscountStrategy;
import strategy.PercentageDiscountStrategy;
import adapter.ExternalPayPalService;
import adapter.PayPalAdapter;
import adapter.PaymentProcessor;
import observer.AdminNotificationObserver;
import observer.EmailNotificationObserver;
import observer.InventoryObserver;
import service.OrderService;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== BIENVENIDO A LA TIENDA VIRTUAL ===");

        Product p1 = new Product("Laptop Gamer", 3500.00);
        Product p2 = new Product("Mouse Inalambrico", 150.00);
        Product p3 = new Product("Teclado Mecanico", 250.00);

        Cart cart = new Cart();
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.addProduct(p3);

        double total = cart.calculateTotal();
        System.out.printf(java.util.Locale.US, "Total bruto en carrito: S/ %.2f%n", total);

        DiscountStrategy discount = new PercentageDiscountStrategy(10);
        double finalTotal = discount.applyDiscount(total);
        System.out.printf(java.util.Locale.US, "Total neto con descuento (10%%): S/ %.2f%n", finalTotal);

        System.out.println("\n------------------------------------------------");

        OrderService orderService = new OrderService();
        orderService.addObserver(new EmailNotificationObserver());
        orderService.addObserver(new InventoryObserver());
        orderService.addObserver(new AdminNotificationObserver());

        ExternalPayPalService externalPayPal = new ExternalPayPalService();
        PaymentProcessor paymentProcessor = new PayPalAdapter(externalPayPal);
        paymentProcessor.pay(finalTotal);

        orderService.confirmOrder(finalTotal);

        System.out.println("------------------------------------------------");
    }
}