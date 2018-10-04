import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class SATParser {
	private File readFile;
	private Scanner UI;
	public ArrayList<String> fileInput;
	public ArrayList<ArrayList<Integer>> formula;
	private int nbvars;
	private int nbclauses;

	public SATParser() {
		UI = null;
		readFile = null;
	}

	public SATParser(String filePath) {
		try {
			readFile = new File(filePath);
			fileFetch();
		}
		catch (Exception ex) {
			System.out.println("File Not Found!");
			return;
		}
		UI = null;
	}

	public void setFile(String filePath) {
		this.readFile = new File(filePath);
		try {
			fileFetch();
		}
		catch (Exception ex) {
			System.out.println("File Not Found");
			ex.printStackTrace();
		}
	}

	public void getFile() {
		UI = new Scanner(System.in);
		System.out.println("Please enter file path: ");
		String userFilePath = UI.nextLine();
		this.readFile = new File(userFilePath);
		try {
			fileFetch();
		}
		catch (Exception ex) {
			System.out.println("File Not Found");
			ex.printStackTrace();
		}
	}

	public void fileFetch() throws IOException {
		fileInput = new ArrayList<String>();
		FileReader inputRead = new FileReader(readFile);
		BufferedReader buffRead = new BufferedReader(inputRead);
		String line;
		while ((line = buffRead.readLine()) != null) {
			fileInput.add(line);
		}
		inputRead.close();
	}

	public void parseInput() throws IOException {
		int startIndex = 0;
		nbvars = 0;
		nbclauses = 0;
		for (int i = 0; i < fileInput.size(); i++) {
			if (fileInput.get(i).charAt(0) == 'c') {
				startIndex++;
			}
			else if (fileInput.get(i).charAt(0) == 'p') {
				String[] temp = fileInput.get(i).split(" ");
				if (temp[1].compareTo("cnf") == 0) {
					nbvars = Integer.parseInt(temp[2]);
					nbclauses = Integer.parseInt(temp[3]);
					startIndex = i + 1;
				}
				else {
					throw (new IOException("Incorrect File Input"));
				}
				break;
			}
		}
		formula = new ArrayList<ArrayList<Integer>>();
		for (int i = startIndex; i < startIndex + nbclauses; i++) {
			String[] tempArr = fileInput.get(i).split(" ");
			ArrayList<Integer> temp = new ArrayList<Integer>();
			formula.add(temp);
			for (int j = 0; j < tempArr.length; j++) {
				if (Integer.parseInt(tempArr[j]) != 0) {
					formula.get(i - startIndex).add(Integer.parseInt(tempArr[j]));
				}
			}
		}
	}

	public int contains(ArrayList<ArrayList<Integer>> values, int index, int value) {
		int result = -1;
		for (int i = 0; i < values.get(index).size(); i++) {
			if (values.get(index).get(i) == value) {
				result = 1;
			}
			else if (Math.abs(values.get(index).get(i)) == Math.abs(value)) {
				result = 0;
			}
		}
		return result;
	}

	public ArrayList<ArrayList<Integer>> countVals(ArrayList<ArrayList<Integer>> input) {
		ArrayList<ArrayList<Integer>> varCounts = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < nbvars; i++) {
			varCounts.add(new ArrayList<Integer>());
			varCounts.get(i).add(0);
			varCounts.get(i).add(0);
		}
		for (int i = 1; i < varCounts.size(); i++) {
			for (int j = 0; j < input.size(); j++) {
				if (contains(input, j, i) == 1) {
					varCounts.get(i).set(0, varCounts.get(i).get(0) + 1);
				}
				else if (contains(input, j, i) == 0) {
					varCounts.get(i).set(0, varCounts.get(i).get(1) + 1);
				}
			}
		}
		return varCounts;
	}

	public void printFormula(ArrayList<ArrayList<Integer>>formula) {
		for (int i = 0; i < formula.size(); i++) {
			System.out.print("Clause: " + i + ": (");
			for (int j = 0; j < formula.get(i).size(); j++) {
				if (j != formula.get(i).size() - 1) {
					System.out.print(formula.get(i).get(j) + " ^ ");
				}
				else {
					System.out.print(formula.get(i).get(j) + "");
				}
			}
			System.out.println(")");
		}
	}
	
	public void printCounts(ArrayList<ArrayList<Integer>> counts) {
		for (int i = 0; i < nbvars; i++) {
			System.out.println(i + ": " + counts.get(i).get(0) + " | " + counts.get(i).get(1));
		}
	}

	

}
