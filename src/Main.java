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
        Product p1 = new Product("Laptop Gamer", 3500.00);
        Product p2 = new Product("Mouse Inalambrico", 150.00);

        Cart cart = new Cart();
        cart.addProduct(p1);
        cart.addProduct(p2);

        double total = cart.calculateTotal();

        DiscountStrategy discount = new PercentageDiscountStrategy(10);
        double finalTotal = discount.applyDiscount(total);

        OrderService orderService = new OrderService();
        orderService.addObserver(new EmailNotificationObserver());
        orderService.addObserver(new InventoryObserver());
        orderService.addObserver(new AdminNotificationObserver());

        ExternalPayPalService externalPayPal = new ExternalPayPalService();
        PaymentProcessor paymentProcessor = new PayPalAdapter(externalPayPal);

        paymentProcessor.pay(finalTotal);
        orderService.confirmOrder(finalTotal);
    }
}