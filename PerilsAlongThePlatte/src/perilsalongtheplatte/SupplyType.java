package perilsalongtheplatte;

/**
 * This enum stores each name of the supplies used in the game and its associated shop price. 
 * @see https://historicoregoncity.org/2019/04/03/outfitting-for-the-journey/ for list and prices. 
 */
public enum SupplyType {
	//declaration of each kind of supply, with the usage amount (in lbs/day) and price attached to each supply type (in $/lb)
	//usage amount may become random in the future
	FLOUR       (0.02, 1.00), //cost $0.02 per unit, 1lb per day used
    BACON       (0.05, 0.50),
    FRUIT       (0.06, 0.25),
    VEGGIES     (0.06, 0.25),
    MEAT        (0.10, 1.00),
    COFFEE      (0.10, 0.25),
    TEA         (0.60, 0.25),
    LARD        (0.05, 0.10),
    WATER       (0.00, 5.0), //assuming water is free or manually handled (yes people get thirsty)
    WHEELS      (15.0, 0.00), //used on certain events 
    AXELS       (15.0, 0.00),
    TONGUES     (15.0, 0.00),
    AMMO        (2.75, 0.00),
    OXEN        (35.0, 0.00),
    MEDICINE    (36.0, 0.00),
    CLOTHES     (8.00, 0.05), //clothes degrade overtime 
    SOAP        (0.15, 0.10),
    BUFFALOCHIPS(0.00, 0.20), //buffalo chips come from the buffalo's butt, not a store
	CASH        (0.00, 0.00); //you cannot buy cash, sadly :-(
	
	//declaration of the amount and price variable
	private final double price, amount;
	
	//constructor to assign the price to each item when it is called
	SupplyType(double price, double amount) {
		this.price = price; 
		this.amount = amount;
	}
	
	/**
	 * A getter that retrieves the price of the specified item. 
	 * @return The price of the item, in double format. 
	 */
	public double getPrice() {
		return price; 
	}
	
	/**
	 * A getter that retrieves the default usage of the specified item.
	 * @return The daily usage amount. 
	 */
	public double getUsageAmount() {
		return amount; 
	}
	


}
