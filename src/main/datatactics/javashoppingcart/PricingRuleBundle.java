package datatactics.javashoppingcart;

import java.util.List;

public class PricingRuleBundle extends PricingRule {
    String bundledProductSku;

    public PricingRuleBundle(String sku, String bundledProductSku) {
        super(sku);
        this.bundledProductSku = bundledProductSku;
    }

    public void applyForItemAdded (CheckoutItem item) {
        // If appropriate, add another item (with this.bundledProductSku) to checkout with price free

        /*
        // Automatically add a vga adaptor - but this doesn't seem to meet spec
        if (item.product.getSku().equals(this.sku)) {
            this.checkout.addProductToCheckoutBySku(this.bundledProductSku, 0.00, false); // free, don't apply pricing rules
        }
        */

        // Handle main product added before bundled product (e.g. macbook added before vga)
        if (item.product.getSku().equals(this.sku)) {
            List<CheckoutItem> existingBundledCheckoutItems = checkout.checkoutItemsWithSku(this.bundledProductSku);
            // set one that's not already pricePaid = 0.00 to 0.00
            for (CheckoutItem anItem: existingBundledCheckoutItems) {
                if (! (anItem.getPricePaid() == 0.00)) {
                    anItem.setPricePaid(0.00);
                    break;
                }
            }
        }

        // Handle main product added after bundled product (e.g. macbook added after vga)
        if (item.product.getSku().equals(this.bundledProductSku)) {
            List<CheckoutItem> existingMainItems = checkout.checkoutItemsWithSku(this.sku);
            List<CheckoutItem> existingBundledCheckoutItems = checkout.checkoutItemsWithSku(this.bundledProductSku);
            if (existingMainItems.size() >= existingBundledCheckoutItems.size()) {
                item.setPricePaid(0.00);
            }
        }
    }

}
