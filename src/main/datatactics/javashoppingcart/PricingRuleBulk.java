package datatactics.javashoppingcart;

import java.util.List;

// bulk discount - reduces prices once the bulk quantity is hit

public class PricingRuleBulk extends PricingRule {
    int bulkQuantity;
    double bulkPrice;

    public PricingRuleBulk(String sku, int bulkQuantity, double bulkPrice) {
        super(sku);
        this.bulkQuantity = bulkQuantity;
        this.bulkPrice = bulkPrice;
    }

    public void applyForItemAdded (CheckoutItem item) {
        if (item.product.getSku().equals(this.sku)) {
            // get items with sku = this.sku
            List<CheckoutItem> sisterItems = checkout.checkoutItemsWithSku(this.sku);
            // if at or above the bulk threshold reduce the price of all of them
            if (sisterItems.size() >= this.bulkQuantity) {
                for (CheckoutItem sister: sisterItems) {
                    sister.setPricePaid(this.bulkPrice);
                }
            }
        }
    }

}
