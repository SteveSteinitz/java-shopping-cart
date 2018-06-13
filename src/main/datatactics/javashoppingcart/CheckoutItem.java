package datatactics.javashoppingcart;

// This class would more clearly be named 'CartItem'
// See naming notes in Cart.java

public class CheckoutItem {
    Product product;
    double pricePaid;

    CheckoutItem(Product product, double pricePaid) {
        this.product = product;
        this.pricePaid = pricePaid;
    }

    public String getName () {
        return product.getName();
    }

    public String getSku () {
        return product.getSku();
    }

    public void setPricePaid (double price) {
        this.pricePaid = price;
    }

    public double getPricePaid () {
        double result = this.pricePaid;
        if (result < 0.00) {
            result = this.product.getPrice();
        }
        return result;
    }
}
