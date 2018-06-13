# Simple Java Shopping Checkout

I started with a sample shopping checkout

https://www.bemyaficionado.com/shopping-checkout-java-collections/

Modified it to suit and added Pricing Rule classes and a CheckoutItem class for overriding price.

# Test Specification

*made original spec more concise*

Start with the following products


|SKU  |Name         |Price    |
| :---| :----------| :--------|
| ipd | Super iPad | $549.99  |
| mbp | MacBook Pro| $1399.99 |
| atv | Apple TV   | $109.50  |
| vga | VGA adapter| $30.00   |


Opening day specials:

-	3 for 2 deal on Apple TVs i.e. if you buy 3 Apple TVs, you will pay the price of 2 only
-	Super iPad bulk discount - for more than four (i.e. five), the price drops to $499.99 each
-	Free VGA adapter with every MacBook Pro sold

Pricing rules to be as flexible as possible to allow change with little notice.

Checkout system can scan items in any order.

Interface to our checkout looks like this (shown in java):

```java
  Checkout co = new Checkout(pricingRules);
  co.scan(item1);
  co.scan(item2);
  co.total();
```

Implement a checkout system that fulfils the requirements described above.

*See note at end about a slight deviation from the spec*

### Examples

SKUs Scanned: atv, atv, atv, vga
Total expected: $249.00

SKUs Scanned: atv, ipd, ipd, atv, ipd, ipd, ipd
Total expected: $2718.95

SKUs Scanned: mbp, vga, ipd
Total expected: $1949.98

#Pricing Rules Implementation

##Special Types

- **bundle** bundledProductSku
- **bulk** bulkQuantity, bulkPrice
- **forPriceOf** quantityTrigger, quantityPaid

## Current Specials

- atv forPriceOf 3 2
- ipd bulk 5 $499.99
- mbp bundle vga

# Notes

## Extraneous Variables for Debugging

For debugging, I often write two lines where one would do:

```java
String foo = bar.foo();
return foo;
```

I've left those in the code for now.

## Checkout Dependancy Injection

The checkout interface specified in the Java task statement precludes a Checkout singleton.  So, I injected the checkout into each PricingRule at constuction time.

## Non-automatic adding of bundled products (and running total)

I originally implemented bundled products by automatically adding the bundled product, e.g. adding the vga adaptor when the MacBook was added.
During testing I noticed that the example scenarios added the bundled product explicitly.  I also noticed the call to co.total() in the interface spec (which simplifies things).

I decided to keep the running total I had implemented, like a real shopping cart, rather than calculating the total at the end.
That made bundled products more complicated.  I needed to modify the bundled product price whether the main product (mpb) or the bundled product (vga) was added first.
As a result, the logic in PricingRuleBundle.applyForItemAdded feels slightly awkward and might need to be rethought for production.  Or, the whole flow could be changed back to caclulating the total at the end, per the spec.

For now, I added an extra unit test scenario where the mbp is added after the vga to ensure that it works for either sequence.

## IntelliJ

I developed this using JetBrains IntelliJ.  I had to fiddle with classpaths, directory types (source, test...), Maven settings... to get the project and tests to run successfully.  You may have to do similar in your development environment.
