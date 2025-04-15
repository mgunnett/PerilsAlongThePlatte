package perilsalongtheplatte;
import java.util.Scanner;


/**
 * Contains and controls elements relating to the option screen when the player stops moving in the Perils Along the Platte game.
 * <p>
 * The OptionMenu class contains methods controlling all the various options in the menu while the player has stopped along the trail.
 * The purpose of this class is to provide essential gameplay mechanics that will be used consistently throughout the game, providing the
 * player with options that allow them to take as much control as they can in their situation.
 * 
 * @version 0.1 
 * @author Landon Bollenbacher
 * @since 03/26/2025
 */

public class OptionMenu {
	
	/**
	 * Allows the play to play the hunting mini-game for a period of time to gain food when the player chooses.
	 * */
	public int hunt() {
		return 0;
	}
	
	/**
	 * Returns the game to the traveling display when the player chooses.
	 * */
	public boolean proceed(int n) {
		return true;
	
	}
	
	/**
	 * Displays the player's current supply inventory when the player chooses and closes when player chooses.
	 * */
	public boolean supplyCheck(int n) {
		return true;
	}
	
	/**
	 * Displays the player's map when the player chooses and closes when player chooses.
	 * */
	public boolean map(int n) {
		Scanner in = new Scanner(System.in);
		String mapSelect = in.next();
		in.close();
		if (mapSelect == "yes")
		{
			return true;
		}
		else
		return false;
	}
	
	/**
	 * Allows the player to choose between a slow, fair and fast moving pace for when the party moves again.
	 * */
	public int pace(int n) {
		int pace = 0;
		int paceSelect = 0;
		Scanner in = new Scanner(System.in);
		paceSelect = in.nextInt();
		in.close();
		if (paceSelect == 1)
		{
			pace = 5;
		}
		else if (paceSelect == 2)
		{
			pace = 10;
		}
		else if (paceSelect == 3)
		{
			pace = 15;
		}
		return pace;
	}
	
	/**
	 * Allows the player to choose between a meager, substantial, or filling portion of rations each day for the party.
	 * */
	public int rations(int n) {
		int rations = 0;
		return rations;
	}
	
	/**
	 * Allows the player to choose a set number of days for the party to rest at their current location.
	 * */
	public int rest(int n) {
		int restDays = 0;
		Scanner in = new Scanner(System.in);
		restDays = in.nextInt();
		in.close();
		return restDays;
	}
	
	/**
	 * Allows the player to get a chance to trade with an npc for randomly generated items (has a chance of no trade offers).
	 * */
	public boolean trade(int n) {
		Scanner in = new Scanner(System.in);
		String tradeSelect = in.next();
		in.close();
		if (tradeSelect == "yes")
		{
			return true;
		}
		else
		return false;
	}
	
	/**
	 * Allows the player to have conversations with the various members of their party and stop when the player chooses.
	 * */
	public boolean interact(int n) {
		return true;
	}
}

