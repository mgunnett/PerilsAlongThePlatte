package perilsalongtheplatte;

import java.util.Random;

public boolean pregnancyStatus();

public class MaleAndFemale {

	public int FemaleStats() {
		return 0;
	}
	
	public int MaleStats() {
		return 0;
	}
	
	public boolean pregnancyStatus() {
	Random random = new Random();
	
		char playergender = 'O';
		int pregnancychance = random.nextInt(5);
		boolean pregnancystatus = false;
		
		if (playergender == 'F') {
			if (pregnancychance == 4) {
				pregnancystatus = true;
				return true;
			}
			else {
				pregnancystatus = false;
				return false;
			}
		}
	}
}

