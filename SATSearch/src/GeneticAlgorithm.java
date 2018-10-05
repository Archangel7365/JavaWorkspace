import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithm {
	public List<Genome> genomes;
	private Formula startFormula;
	private int populationSize;
	private int chromosomeSize;
	private int generationFitness;
	private int bestFitnessScore;
	private int fittestGenome;
	private double birthControl;
	private Random rng;

	public GeneticAlgorithm(Formula startForm, int popSize) {//straight forward constructor
		this.startFormula = startForm;
		chromosomeSize = startForm.nbvars;
		genomes = new ArrayList<Genome>();
		this.populationSize = popSize;
		birthControl = 0.7;
	}

	public void initializePopulation() { //randomly generate the initial population
		for (int i = 0; i < populationSize; i++) {
			Genome newBaby = new Genome(chromosomeSize);
			newBaby.randomizeGenes();
		}
	}

	public void updateFitnessScores() { //loop through all of the genomes and
		generationFitness = 0;	//update the Fitness score of each one
		bestFitnessScore = startFormula.clauses.size();
		fittestGenome = 0;
		
		for (int i = 0; i < genomes.size(); i++) {
			genomes.get(i).setFitnessScore(startFormula);
			generationFitness += genomes.get(i).getFitScore();
			if (genomes.get(i).getFitScore() < bestFitnessScore) {
				fittestGenome = i;
				bestFitnessScore = genomes.get(i).getFitScore();
			}
		}
	}

	public void mate(List<Integer> mom, List<Integer> dad, List<Integer> baby1, List<Integer> baby2) {
		double lucky = rng.nextDouble();
		if (lucky > birthControl || mom == dad) {
			baby1.addAll(mom);
			baby2.addAll(dad);
			return;
		}
		
		int mergePoint = rng.nextInt(chromosomeSize - 1);
		for (int i = 0; i < chromosomeSize; i++) {
			if (i < mergePoint) {
				baby1.add(mom.get(i));
				baby2.add(dad.get(i));
			}
			else {
				baby1.add(dad.get(i));
				baby2.add(mom.get(i));
			}
		}
	}

	public Genome matchmaker() { //find a parent that's "fit enough," but not the fittest
		double threshold = rng.nextDouble() * generationFitness; //that way the fittest parents aren't the only ones mating
		double total = 0;
		int selectedMate = 0;

		for (int i = 0; i < populationSize; i++) {
			total += genomes.get(i).getFitScore();

			if (total > threshold) {
				selectedMate = i;
				break;
			}
		}
		return genomes.get(selectedMate);
	}
	
	public int checkElite() { //check if a genome has a solution!
		for (int i = 0; i < populationSize; i++) {
			if (genomes.get(i).isElite()) {
				return i;
			}
		}
		return 0;
	}

	public void makeBabies() { //Look away! They's makin babies
		updateFitnessScores(); //IT'S NOT THAT HARD
		
		int newBirths = 0;
		List<Genome> newBrood = new ArrayList<Genome>();
		while (newBirths < populationSize) {
			Genome mom = matchmaker();
			Genome dad = matchmaker();
			Genome firstBorn = new Genome();
			Genome secondBorn = new Genome();
			mate(mom.chromosomes, dad.chromosomes, firstBorn.chromosomes, secondBorn.chromosomes);
			firstBorn.mutate();
			secondBorn.mutate();
			newBrood.add(firstBorn);
			newBrood.add(secondBorn);
			
			newBirths += 2;
		}
		genomes.clear();
		genomes.addAll(newBrood);
	}
}
