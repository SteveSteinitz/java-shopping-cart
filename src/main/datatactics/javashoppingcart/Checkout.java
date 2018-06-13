package datatactics.javashoppingcart;

import java.util.ArrayList;
import java.util.List;

// Original version by Varun Shrivastava
// Modifications Steve Steinitz Mon 26 Mar 18

// This class was originally called 'Cart'.
// The creator of the test (from Qantas) referred to it as a 'Checkout', less logical and a verb but I followed suit.

class Checkout {

    List<CheckoutItem> checkoutItems = new ArrayList<CheckoutItem>();
    List<PricingRule> pricingRules;

    public Checkout(List pricingRules) {
        this.pricingRules = pricingRules;
        // Set the checkout for all the pricing rules - didn't want a singleton Checkout
        // Also, the task statement's Checkout instantation spec precluded a singleton
        for (PricingRule pricingRule: this.pricingRules) {
            pricingRule.setCheckout(this);
        }
    }

    public CheckoutItem addProductToCheckoutBySku(
            String sku,
            double pricePaid, // ignore if less than zero - java doesn't have optional arguments
            boolean shouldApplyPricingRules
    ) {
        CheckoutItem checkoutItem = null;
        Product product = getProductBySku(sku);
        if (product != null) {
            double pricePaid_ = pricePaid >= 0.00 ? pricePaid : product.getPrice();
            checkoutItem = new CheckoutItem(product, pricePaid);
            addToCheckout(checkoutItem);

            // Apply pricing rules
            if (shouldApplyPricingRules) {
                for (PricingRule pricingRule: this.pricingRules) {
                    pricingRule.applyForItemAdded(checkoutItem);
                }
            }
        }
        return checkoutItem;
    }

    private Product getProductBySku(String sku) {
        Product product = null;
        List<Product> products = new Products().getProducts();
        for (Product prod: products) {
            String prodSku = prod.getSku();
            if (prodSku.equals(sku)) {
                product = prod;
                break;
            }
        }
        return product;
    }

    private void addToCheckout(CheckoutItem checkoutItem) {
        checkoutItems.add(checkoutItem);
    }

    // Todo: This would need to potentially undo the pricing rules.  Not part of the task spec.
    public void removeCheckoutItemBySku(String sku) {
        Product prod = getProductBySku(sku);
        checkoutItems.remove(prod);
    }

    public double total () {
        double result = 0.00;
        for (CheckoutItem checkoutItem: this.checkoutItems) {
            result += checkoutItem.getPricePaid();
        }
        return result;
    }

    public void printCheckoutItems() {
        if (checkoutItems.size() > 0) {
            for (CheckoutItem checkoutItem: checkoutItems) {
                System.out.println(
                    checkoutItem.getName() + " - $" +
                    checkoutItem.getPricePaid()
                );
            }
            System.out.println("Total $" + this.total());
        } else
        {
            System.out.println("Empty - Total $0.00");
        }
    }

    public List checkoutItemsWithSku (String sku) {
        List<CheckoutItem> matchingCheckoutItems = new ArrayList<CheckoutItem>();
        for (CheckoutItem checkoutItem: checkoutItems) {
            String checkoutItemSku = checkoutItem.getSku();
            if (checkoutItemSku.equals(sku)) {
                 matchingCheckoutItems.add(checkoutItem);
            }
        }
        return matchingCheckoutItems;
    }

}
