
public class Character {
	private static int STR, DEX, CON, INT, WIS, CHA;
	private static int FORT, REF, WILL;
	private static int INIT, BaseAttackBonus1, BaseAttackBonus2;
	private static String name, type, race;
	private boolean gender;
	private int STRmod, DEXmod, CONmod, INTmod, WISmod, CHAmod;
	
	public Character() {
		STR = 10;
		DEX = 10;
		CON = 10;
		INT = 10;
		CHA = 10;
		WIS = 10;
		STRmod = 0;
		DEXmod = 0;
		CONmod = 0;
		INTmod = 0;
		WISmod = 0;
		CHAmod = 0;
		FORT = 0;
		REF = 0;
		WILL = 0;
		INIT = 0;
		BaseAttackBonus1 = 1;
		BaseAttackBonus2 = 0;
		name = null;
		type = null;
		gender = true;
		race = null;
	}
	
	public Character(String Name, String Type, String Race, Boolean Gender) {
		this.name = Name;
		this.type = Type;
		this.race = Race;
		this.gender = Gender;
	}
	
	public int getSTR() {
		return STR;
	}

	public void setSTR(int sTR) {
		STR = sTR;
	}

	public int getDEX() {
		return DEX;
	}

	public void setDEX(int dEX) {
		DEX = dEX;
	}

	public int getCON() {
		return CON;
	}

	public void setCON(int cON) {
		CON = cON;
	}

	public int getINT() {
		return INT;
	}

	public void setINT(int iNT) {
		INT = iNT;
	}
	
	public int getWIS() {
		return WIS;
	}
	
	public void setWIS(int WIS) {
		this.WIS = WIS;
	}
	
	public int getCHA() {
		return CHA;
	}

	public void setCHA(int cHA) {
		CHA = cHA;
	}

	public int getFORT() {
		return FORT;
	}

	public void setFORT(int fORT) {
		FORT = fORT;
	}

	public int getREF() {
		return REF;
	}

	public void setREF(int rEF) {
		REF = rEF;
	}

	public int getWILL() {
		return WILL;
	}

	public void setWILL(int wILL) {
		WILL = wILL;
	}

	public int getINIT() {
		return INIT;
	}

	public void setINIT(int iNIT) {
		INIT = iNIT;
	}

	public int getBaseAttackBonus1() {
		return BaseAttackBonus1;
	}

	public void setBaseAttackBonus1(int baseAttackBonus) {
		BaseAttackBonus1 = baseAttackBonus;
	}
	
	public int getBaseAttackBonus2() {
		return BaseAttackBonus2;
	}

	public void setBaseAttackBonus2(int baseAttackBonus) {
		BaseAttackBonus2 = baseAttackBonus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGender() {
		if (gender == true){
			return " Male";
		}
		else {
			return " Female";
		}
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}
	
	public int getSTRmod() {
		return STRmod;
	}

	public void setSTRmod(int sTRmod) {
		STRmod = sTRmod;
	}

	public int getDEXmod() {
		return DEXmod;
	}

	public void setDEXmod(int dEXmod) {
		DEXmod = dEXmod;
	}

	public int getCONmod() {
		return CONmod;
	}

	public void setCONmod(int cONmod) {
		CONmod = cONmod;
	}

	public int getINTmod() {
		return INTmod;
	}

	public void setINTmod(int iNTmod) {
		INTmod = iNTmod;
	}

	public int getWISmod() {
		return WISmod;
	}

	public void setWISmod(int wISmod) {
		WISmod = wISmod;
	}

	public int getCHAmod() {
		return CHAmod;
	}

	public void setCHAmod(int cHAmod) {
		CHAmod = cHAmod;
	}

	public void calculateModifiers() {
		STRmod = (STR - 10)/2;
		DEXmod = (DEX - 10)/2;
		CONmod = (CON - 10)/2;
		INTmod = (INT - 10)/2;
		WISmod = (WIS - 10)/2;
		CHAmod = (CHA - 10)/2;
	}
	
	public void print() {
		System.out.println(name + " is a" + getGender() + " " + race + " " + type + ". " + "\n");
	}

	public void printStats() {
		System.out.println("     Base  Mod");
		System.out.println("STR:  " + STR + "   " + STRmod);
		System.out.println("DEX:  " + DEX + "   " + DEXmod);
		System.out.println("CON:  " + CON + "   " + CONmod);
		System.out.println("INT:  " + INT + "   " + INTmod);
		System.out.println("WIS:  " + WIS + "   " + WISmod);
		System.out.println("CHA:  " + CHA + "   " + CHAmod);
	}
	
}
