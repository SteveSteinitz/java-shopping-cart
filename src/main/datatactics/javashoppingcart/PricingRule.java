package datatactics.javashoppingcart;

import java.util.List;

public abstract class PricingRule {

    protected String sku; // the item to which the pricing rule applies
    protected Checkout checkout;  // set by the Checkout itself.  Avoids a checkout singleton.

    public PricingRule(String sku) {
        this.sku = sku;
    }

    public void setCheckout(Checkout checkout) {
        this.checkout = checkout;
    }

    public void addToCheckout(String sku, Double price) {
        // add item with sku to checkout at price
    }

    public List checkoutItems(String sku) {
        // return array of CheckoutItems matching sku
        List <CheckoutItem> items = this.checkout.checkoutItemsWithSku(sku);
        return items;
    }

    public abstract void applyForItemAdded(CheckoutItem item);

    // not part of test specification
    public void applyForItemDeleted() {

    };

}
