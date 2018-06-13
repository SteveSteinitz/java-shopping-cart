package datatactics.javashoppingcart;

import java.util.ArrayList;
import java.util.List;

public enum PricingRules {
    INSTANCE; // Best practise for Singletons, along with the enum type, above
    private List pricingRulesInit () {
        List<PricingRule> pricingRules = new ArrayList<PricingRule>();

        // Create the rules
        PricingRule bulk = new PricingRuleBulk ("ipd", 5, 499.99d);
        PricingRule bundle = new PricingRuleBundle ("mbp", "vga");
        PricingRule forPriceOf = new PricingRuleForPriceOf ("atv", 3, 2);

        // Add the rules to the result List
        pricingRules.add(bulk);
        pricingRules.add(bundle);
        pricingRules.add(forPriceOf);
        return pricingRules;
    }
    private final List pricingRules = pricingRulesInit ();

    // access like so: PricingRules.INSTANCE.rules()
    public List rules() {
        return pricingRules;
    }
}
