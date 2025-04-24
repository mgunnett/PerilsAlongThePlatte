package perilsalongtheplatte;

/**
 * This enum stores each name of the supplies used in the game and its associated shop price
 */
public enum SupplyType {
	//declaration of each kind of supply, with the amount and attached to each supply type (in $/lb)
	FLOUR       (0.02), //start with 0lbs of flour, cost $0.02 per unit
    BACON       (0.05),
    FRUIT       (0.06),
    VEGGIES     (0.06),
    MEAT        (0.10),
    COFFEE      (0.10),
    LARD        (0.05),
    WATER       (0.00), //assuming water is free or manually handled
    WHEELS      (15.0),
    AXELS       (15.0),
    TONGUES     (15.0),
    AMMO        (2.75),
    OXEN        (35.0),
    MEDICINE    (36.0),
    CLOTHES     (8.00),
    SOAP        (0.15),
    BUFFALOCHIPS(0.00), //buffalo chips come from the buffalo's butt, not a store
	CASH        (0.00); //you cannot buy cash, sadly :-(
	
	//declaration of the amount and price variable
	private final double price;
	
	//constructor to assign the price to each item when it is called
	SupplyType(double price) {
		this.price = price; 
	}
	
	/**
	 * A getter that retrieves the price of the specified item. 
	 * @return The price of the item, in double format. 
	 */
	public double getPrice() {
		return price; 
	}
	


}
