import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class SATParser {
	private File readFile;
	private Scanner UI;
	public ArrayList<String> fileInput;
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

	public int[][] parseInput() throws IOException {
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
		int[][] result = new int[nbclauses][nbvars];
		for (int i = startIndex; i < startIndex + nbclauses; i++) {

			String[] tempArr = fileInput.get(i).split(" ");
			result[i - startIndex] = new int[tempArr.length];
			for (int j = 0; j < result[i - startIndex].length; j++) {
				result[i - startIndex][j] = Integer.parseInt(tempArr[j]);
			}
		}

		for (int i = 0; i < result.length; i++) {
			if (result[i][result[i].length-1] == 0) {
				int[] temp = new int[result[i].length - 1];
				for (int j = 0; j < temp.length; j++) {
					temp[j] = result[i][j];
				}
				result[i] = temp;
			}
		}
		return result;
	}
	
	public int contains(int[][] values, int index, int value) {
		int result = -1;
		for (int i = 0; i < values[index].length; i++) {
			if (values[index][i] == value) {
				result = 1;
			}
			else if (Math.abs(values[index][i]) == Math.abs(value)) {
				result = 0;
			}
		}
		return result;
	}
	
	public int[][] countVals(int[][] input) {
		int[][] varCounts = new int[nbvars + 1][2];
		for (int i = 1; i < varCounts.length; i++) {
			for (int j = 0; j < input.length; j++) {
				if (contains(input, j, i) == 1) {
					varCounts[i][0]++;
				}
				else if (contains(input, j, i) == 0) {
					varCounts[i][1]++;
				}
			}
		}
		return varCounts;
	}

}
