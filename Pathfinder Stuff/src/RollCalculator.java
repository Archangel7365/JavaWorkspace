import java.util.*;
public class RollCalculator {
	static Scanner ui;
	
	public static void main(String[] args) {
		ui = new Scanner(System.in);
		Character Roshe = new Character("Roshe", "Paladin", "Half-Orc", true);
		Roshe.setSTR(16);
		Roshe.setDEX(12);
		Roshe.setCON(14);
		Roshe.setINT(8);
		Roshe.setWIS(10);
		Roshe.setCHA(16);
		Roshe.setBaseAttackBonus1(8);
		Roshe.setBaseAttackBonus2(3);
		Roshe.setFORT(10);
		Roshe.setREF(5);
		Roshe.setWILL(8);
		Roshe.calculateModifiers();
		Roshe.print();
		Roshe.printStats();
		RollCalculator RC = new RollCalculator();
		boolean done = false;
		int input = 0;		
		System.out.println("\nWhat kind of Roll would you like to make?");
		System.out.println("1: Attack");
		System.out.println("2: Damage");
		System.out.println("3: Quit");
		input = RC.askInput();
		if (input == 1) {
			RC.attackRoll(Roshe);
		}
		if (input == 2) {
			RC.damageRoll(Roshe);
		}
		else if (input == 3) {
			done = true;
		}	
		System.out.println("Thanks for using Roll Calculator!");
		ui.close();
		return;
	}
	
	public RollCalculator() {
		
	}
	
	public int askInput() {
		int input = ui.nextInt();
		return input;
	}
	
	public void attackRoll(Character c) {
		Scanner reader = new Scanner(System.in);
		int result = 0;
		int rollnum = 0;
		int roll = 0;
		int modifiers = 0;
		Boolean hasmods = false;
		Boolean BS = false;
		Boolean ES = false;
		Boolean SE = false;
		Boolean PA = false;
		Boolean Charge = false;
		
		System.out.println("\nWhat was the base roll?");
		boolean validresponse = false;
		
		while (validresponse == false) {
			
			System.out.println("Please input a number between 1 and 20: ");
			if (roll > 1 || roll < 20) {
				roll = reader.nextInt();
				validresponse = true;
			}
		}
		
		System.out.println("\nWhich attack was this?");
		validresponse = false;
		while (validresponse == false) {
			System.out.println("Please input either a 1 or 2: ");
			rollnum = reader.nextInt();
			if (rollnum == 1 || rollnum == 2) {
				validresponse = true;
			}
		}
		if (rollnum == 1) {
			roll += c.getBaseAttackBonus1();
		}
		else if (rollnum == 2) {
			roll += c.getBaseAttackBonus2();
		}
		
		
		System.out.println("\nDo you have any additional modifiers?");
		String input = reader.next();
		if (input.equalsIgnoreCase("y")) {
			hasmods = true;
		}
		else {
			hasmods = false;
		}
			
		if (hasmods == true) {
			System.out.println("Do you have Bull's Strength?");
			input = reader.next(); 
			if (input.equalsIgnoreCase("y")) {
				BS = true;
			}
			else {
				BS = false;
			}
			System.out.println("Do you have Eagle's Splendor?");
			input = reader.next();
			if (input.equalsIgnoreCase("y")) {
				ES = true;
			}
			else {
				ES = false;
			}
			System.out.println("Are you using Smite Evil on this enemy?");
			input = reader.next();
			if (input.equalsIgnoreCase("y")) {
				SE = true;
			}
			else {
				SE = false;
			}
			System.out.println("Are you using Power Attack?");
			input = reader.next();
			if (input.equalsIgnoreCase("y")) {
				PA = true;
			}
			else {
				PA = false;
			}
			System.out.println("Are you Charging?");
			input = reader.next();
			if (input.equalsIgnoreCase("y")) {
				Charge = true;
			}
			else {
				Charge = false;
			}	
		}
		if (BS == true) {
			modifiers += bullsStrength(c);
		}
		else {
			modifiers += c.getSTRmod();
		}
		
		if (SE == true) {
			modifiers += smiteEvil(c, ES);
		}
		
		if (Charge == true) {
			modifiers += 2;
		}
		
		if (PA == true) {
			modifiers -= 3;
		}
		
		result = roll + modifiers;
		
		reader.close();
		
		System.out.println("Your final attack roll is: " + result);
		return;
		
	}
	
	public void damageRoll(Character c) {
		Scanner damage = new Scanner(System.in);
		int result = 0;
		int roll = 0;
		int modifiers = 0;
		int nHands = 0;
		Boolean hasmods = false;
		Boolean BS = false;
		Boolean ES = false;
		Boolean SE = false;
		Boolean PA = false;
		
		System.out.println("\nWhat was the base roll?");
		roll = damage.nextInt();
		
		System.out.println("\nIs your weapon 1 or 2 handed?");
		nHands = damage.nextInt();
		
		System.out.println("\nDo you have any buffs on?");
		String input = damage.next();
		if (input.equalsIgnoreCase("y")) {
			hasmods = true;
		}
		else {
			hasmods = false;
		}
			
		if (hasmods == true) {
			System.out.println("Do you have Bull's Strength?");
			input = damage.next(); 
			if (input.equalsIgnoreCase("y")) {
				BS = true;
			}
			else {
				BS = false;
			}
			System.out.println("Do you have Eagle's Splendor?");
			input = damage.next();
			if (input.equalsIgnoreCase("y")) {
				ES = true;
			}
			else {
				ES = false;
			}
			System.out.println("Are you using Power Attack?");
			input = damage.next();
			if (input.equalsIgnoreCase("y")) {
				PA = true;
			}
			else {
				PA = false;
			}
			System.out.println("Is this a critical hit?");
			input = damage.next();
			System.out.println("Are you using Smite Evil on this enemy?");
			input = damage.next();
			if (input.equalsIgnoreCase("y")) {
				SE = true;
			}
			else {
				SE = false;
			}
		}
		if (BS == true) {
			if (nHands == 2) {
				modifiers += 1.5 * bullsStrength(c);
			}
			else {
				modifiers += bullsStrength(c);
			}
		}
		else {
			if (nHands == 2) {
				modifiers += c.getSTRmod() * 1.5;
			}
			else {
				modifiers += c.getSTRmod();
			}
			
		}
		
		if (SE == true) {
			modifiers += smiteEvil(c, ES);
		}
		
		if (PA == true) {
			modifiers += 9;
		}
		
		result = roll + modifiers;
		damage.close();
		
		System.out.println("Your final Damage roll is: " + result);
		return;
		
	}
	
	public int bullsStrength(Character c) {
		int tempStrength = c.getSTRmod() + 2;
		return tempStrength;
	}
	
	public int eaglesSplendor(Character c) {
		int tempCharisma = c.getCHAmod() + 2;
		return tempCharisma;
	}
	
	public int smiteEvil(Character c, Boolean ES) {
		int mod;
		if (ES) {
			mod = eaglesSplendor(c);
		}	
		else {
			mod = c.getCHAmod();
		}
		return mod;
	}
}
