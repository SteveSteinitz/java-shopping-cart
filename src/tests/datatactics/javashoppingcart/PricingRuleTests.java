package datatactics.javashoppingcart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingRuleTests {

    @Test
    public void ForPriceOfTest() {
        // Test as Specified
        // SKUs Scanned: atv, atv, atv, vga
        // Total expected: $249.00

        Checkout checkout = new Checkout(PricingRules.INSTANCE.rules());
        checkout.addProductToCheckoutBySku("atv", -1, true);
        checkout.addProductToCheckoutBySku("atv", -1, true);
        checkout.addProductToCheckoutBySku("atv", -1, true);
        checkout.addProductToCheckoutBySku("vga", -1, true);

        assertEquals(249.00, checkout.total());
    }

    @Test
    public void BulkTest() {
        // Test as Specified
        // SKUs Scanned: atv, ipd, ipd, atv, ipd, ipd, ipd
        // Total expected: $2718.95

        Checkout checkout = new Checkout(PricingRules.INSTANCE.rules());
        checkout.addProductToCheckoutBySku("atv", -1, true);
        checkout.addProductToCheckoutBySku("ipd", -1, true);
        checkout.addProductToCheckoutBySku("ipd", -1, true);
        checkout.addProductToCheckoutBySku("atv", -1, true);
        checkout.addProductToCheckoutBySku("ipd", -1, true);
        checkout.addProductToCheckoutBySku("ipd", -1, true);
        checkout.addProductToCheckoutBySku("ipd", -1, true);

        assertEquals(2718.95, checkout.total());
    }

    @Test
    public void BundleMainBeforeTest() {
        // Test as Specified
        // SKUs Scanned: mbp, vga, ipd
        // Total expected: $1949.98

        Checkout checkout = new Checkout(PricingRules.INSTANCE.rules());
        checkout.addProductToCheckoutBySku("mbp", -1, true);
        checkout.addProductToCheckoutBySku("vga", -1, true);
        checkout.addProductToCheckoutBySku("ipd", -1, true);

        assertEquals(1949.98, checkout.total());
    }

    @Test
    public void BundleMainAfterTest() {
        // Test as Specified
        // SKUs Scanned: mbp, vga, ipd
        // Total expected: $1949.98

        Checkout checkout = new Checkout(PricingRules.INSTANCE.rules());
        checkout.addProductToCheckoutBySku("vga", -1, true);
        checkout.addProductToCheckoutBySku("mbp", -1, true);
        checkout.addProductToCheckoutBySku("ipd", -1, true);

        assertEquals(1949.98, checkout.total());
    }

}
