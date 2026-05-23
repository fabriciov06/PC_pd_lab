package strategy;

public class FixedAmountDiscountStrategy implements DiscountStrategy {
    private double discountAmount;

    public FixedAmountDiscountStrategy(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public double applyDiscount(double total) {
        return (total >= discountAmount) ? (total - discountAmount) : 0.0;
    }
}
