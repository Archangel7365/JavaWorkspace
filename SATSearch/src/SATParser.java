import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class SATParser {
	private File readFile;
	private Scanner UI;
	public ArrayList<String> fileInput;
	public int nbvars;
	public int nbclauses;

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

	public ArrayList<ArrayList<Integer>> parseInput() throws IOException {
		int startIndex = 0;
		nbvars = 0;
		nbclauses = 0;
		for (int i = 0; i < fileInput.size(); i++) {
			if (fileInput.get(i).charAt(0) == 'c') {
				startIndex++;
			}
			else if (fileInput.get(i).charAt(0) == 'p') {
				String[] temp = fileInput.get(i).split("\\s+");
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
		ArrayList<ArrayList<Integer>> formula = new ArrayList<ArrayList<Integer>>();
		for (int i = startIndex; i < startIndex + nbclauses; i++) {
			String[] tempArr = fileInput.get(i).split("\\s+");
			if (tempArr[0].compareTo("") == 0) {
				tempArr = Arrays.copyOfRange(tempArr, 1, tempArr.length);
			}
			ArrayList<Integer> temp = new ArrayList<Integer>();
			formula.add(temp);
			for (int j = 0; j < tempArr.length; j++) {
				if (Integer.parseInt(tempArr[j]) != 0) {
					formula.get(i - startIndex).add(Integer.parseInt(tempArr[j]));
				}
			}
		}
		return formula;
	}

	
	
	
	
	

}
