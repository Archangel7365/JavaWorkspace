import java.util.*;
import java.io.*;

public class SATDataGet {

	public static void main(String[] args) {
		
		File testFiles = new File("src/testFiles/HARD CNF Formulas");
		File[] directoryListing = testFiles.listFiles();
		boolean[] solutions = new boolean[directoryListing.length];
		int counter = 0;
		for (int i = 0; i < directoryListing.length; i++) {
			DPLL temp = new DPLL(directoryListing[i].getPath());
			solutions[i] = temp.DPLLSearch(temp.getStartNode());
			if (solutions[i]) {
				temp.printSolution();
				System.out.println(directoryListing[i].getPath());
				System.out.println();
			}
		}
		
		int satCounter = 0;
		for (int j = 0; j < directoryListing.length; j++) {
			if (solutions[j]) {
				satCounter++;
			}
		}
		
		System.out.println("Of: " + counter + " formulas,");
		System.out.println(satCounter + " were satisfiable.");
	}
	

}
