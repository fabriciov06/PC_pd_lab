package service;
import observer.OrderObserver;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<OrderObserver> observers = new ArrayList<>();

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void confirmOrder(double finalAmount) {
        System.out.printf("Compra confirmada por S/ %.2f%n", finalAmount);
        notifyObservers("Orden procesada.");
    }

    private void notifyObservers(String message) {
        for (OrderObserver observer : observers) {
            observer.update(message);
        }
    }
}
