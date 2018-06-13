package datatactics.javashoppingcart;

import java.util.List;

public class PricingRuleForPriceOf extends PricingRule {
    int quantityTrigger;
    int quantityPaid;

    public PricingRuleForPriceOf(String sku, int quantityTrigger, int quantityPaid) {
        super(sku);
        this.quantityTrigger = quantityTrigger;
        this.quantityPaid = quantityPaid;
    }

    public void applyForItemAdded (CheckoutItem item) {
        if (item.product.getSku().equals(this.sku)) {
            // get items with sku = this.sku
            List<CheckoutItem> sisterItems = checkout.checkoutItemsWithSku(this.sku);
            int sisterItemsSize = sisterItems.size();
            // if we have more than zero sisters and the number is divisible by quantityTrigger, make a free one
            if (sisterItemsSize > 0 && (sisterItems.size() % this.quantityTrigger) == 0) {
                item.setPricePaid(0.00);
            }
        }
     }

}
