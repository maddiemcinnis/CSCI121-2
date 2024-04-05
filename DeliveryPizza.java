public class DeliveryPizza extends Pizza {
    private int deliveryFee;
    private String deliveryAddress;

    public DeliveryPizza(String[] toppings, String deliveryAddress, int numToppings) {
        super(toppings, numToppings);

        if (price > 18) {
            this.deliveryFee = 3;
        } else {
            this.deliveryFee = 5;
        }

        this.deliveryAddress = deliveryAddress;
    }


    public String toString() {
        StringBuilder description = new StringBuilder(super.toString());
        description.append("\nDelivery Address: ").append(deliveryAddress);
        description.append("\nDelivery Fee: $").append(deliveryFee);
        return description.toString();
    }

}
