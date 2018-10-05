import java.util.ArrayList;

public class GeneticTest {

	public static void main(String[] args) {
		SATParser test1 = new SATParser("src/yo.txt");
		try {
			Formula testForm  = test1.getFormula();
			GeneticAlgorithm test = new GeneticAlgorithm(testForm, 150);
			test.initializePopulation();
			boolean found = false;
			int NTries = 3000;
			int index = 0;
			int answer = 0;
			while (!found && index < NTries) {
				answer = test.makeBabies();
				if (answer != -1) {
					found = true;
					System.out.println("Solution Found at " + answer + " after " + test.generation + " generations.");
					System.out.println(test.genomes.get(answer).chromosomes.toString());
				}
				index++;
			}
			if (!found) {
				System.out.println("Solution not found.");
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
