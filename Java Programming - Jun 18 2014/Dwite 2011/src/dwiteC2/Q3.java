package dwiteC2;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Q3 {
	public static void main(String[] args){
		BufferedReader reader;
		PrintWriter writer;
		try {
			reader = new BufferedReader(new FileReader("c2/DATA3.TXT"));
			writer = new PrintWriter(new FileWriter("c2/OUT3.TXT"));
			for(int i = 0; i < 5; i++) {
				int m = 0;
				int n = 0;
				int returnVal = 0;
				String line = reader.readLine();
				Scanner scan = new Scanner(line);
				m = scan.nextInt();
				n = scan.nextInt();
				ArrayList<StoreRecord> store = new ArrayList<StoreRecord>();
				ArrayList<SchoolRecord> school = new ArrayList<SchoolRecord>();
				for(int j = 0; j < m; j++) {
					store.add(new StoreRecord(reader.readLine()));
				}
				for(int j = 0; j < n; j++) {
					school.add(new SchoolRecord(reader.readLine()));
				}
				STUDENT:
				for(SchoolRecord student : school) {
					ArrayList<String> drinkList = new ArrayList<String>();
					for(StoreRecord customer : store) {
						if(student.getDOB() == customer.getDOB() &&
							student.getGENDER() == customer.getGENDER() &&
							student.getZIP() == customer.getZIP()) {
							drinkList.add(customer.getDRINK());
						}
					}
					if(drinkList.size() != 0) {
						String drinkName = drinkList.get(0);
						for(String str : drinkList) {
							if(!str.equals(drinkName)) {
								continue STUDENT;
							}
						}
						returnVal++;
					}
				}
				writer.println("" + returnVal);
				writer.flush();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


class StoreRecord
{
	private int ID;
	private int DOB;
	private boolean GENDER;
	private int ZIP;
	private String DRINK;
	StoreRecord(String str) {
		String[] temp = str.split(" ");
		ID = Integer.parseInt(temp[0]);
		DOB = Integer.parseInt(temp[1].substring(0, 7));
		GENDER = temp[1].charAt(8) == 'M';
		ZIP = Integer.parseInt(temp[1].substring(9));
		DRINK = temp[2];
	}
	int getID() {return ID;}
	int getDOB() {return DOB;}
	boolean getGENDER() {return GENDER;}
	int getZIP() {return ZIP;}
	String getDRINK() {return DRINK;}
}

class SchoolRecord
{
	private String NAME;
	private int DOB;
	private boolean GENDER;
	private int ZIP;
	SchoolRecord(String str) {
		String[] temp = str.split(" ");
		NAME = temp[0];
		DOB = Integer.parseInt(temp[1].substring(0, 7));
		GENDER = temp[1].charAt(8) == 'M';
		ZIP = Integer.parseInt(temp[1].substring(9));
	}
	String getNAME() {return NAME;}
	int getDOB() {return DOB;}
	boolean getGENDER() {return GENDER;}
	int getZIP() {return ZIP;}
}