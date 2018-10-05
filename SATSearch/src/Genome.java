import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Genome { //I AM JUST TRYING TO GET THIS SHIT TO PUSH!!!
	public ArrayList<Integer> chromosomes;
	private int FitnessScore;
	private int chromosomeSize;
	private double mutationFactor;
	private Random rng;
	private boolean elite;

	public Genome() {
		FitnessScore = 0;
		chromosomes = new ArrayList<Integer>();
		rng = new Random();
	}

	public Genome(int maxChroSize) {
		rng = new Random();
		mutationFactor = 0.15;
		chromosomeSize = maxChroSize;
		FitnessScore = 0;
		this.chromosomes = new ArrayList<Integer>();
		elite = false;
	}

	public void randomizeGenes() {
		chromosomes.clear();
		for (int i = 0; i < chromosomeSize; i++) {
			int temp = rng.nextInt(chromosomeSize) + 1;
			double flip = rng.nextDouble();
			if (flip < 0.5) {
				temp = -temp;
			}
			chromosomes.add(temp);
		}
	}

	public void setFitnessScore(Formula f) {
		Formula temp = f.copy();
		if (temp.solvedBy(chromosomes)) {
			ArrayList<Integer> collisions = temp.getCollisions(this.chromosomes);
			if (collisions.size() > 0) {
				for (Integer in: collisions) {
					ArrayList<Integer> temp1 = chromosomes;
					temp1 = temp.removeCollisions(temp1, in);
					if (temp.solvedBy(temp1)) {
						this.chromosomes = temp1;
						break;
					}

					ArrayList<Integer> temp2 = chromosomes;
					temp2 = temp.removeCollisions(temp2, -in);
					if (temp.solvedBy(temp2)) {
						this.chromosomes = temp2;
						break;
					}
				}
			}
			this.FitnessScore = 0;
			this.elite = true;
		}
		else {
			for (Integer mem: chromosomes) {
				temp.removeClausesContaining(mem);
			}
			this.FitnessScore = temp.clauses.size();
		}
	}

	public int getFitScore() {
		return this.FitnessScore;
	}

	public void mutate() {
		for (Integer mem: chromosomes) {
			Double mut = rng.nextDouble();
			if (mut < mutationFactor) {
				int newVal = rng.nextInt(chromosomeSize) + 1;
				Double flip = rng.nextDouble();
				if (flip > 0.5) {
					newVal = -newVal;
				}
				mem = newVal;
			}
		}
	}

	public boolean isElite() {
		return this.elite;
	}

}
