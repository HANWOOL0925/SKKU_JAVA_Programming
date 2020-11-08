//name, phone number를 HashMap에 저장하라.
//이미 저장된 이름 입력시 전화번호를 출력하라.

import java.util.HashMap;
import java.util.Scanner;

public class LA4_ContactBook {
	public static void main(String[] args) {
		HashMap<String, String> ContactBook = new HashMap<String, String>();
		int a = 0;
		while (a == 0) {
			System.out.print("Please enter a name (Exit:0) : ");
			Scanner scn = new Scanner(System.in);
            String name = scn.next();
			if (name.equals("0")) {
				System.out.println("There is " + ContactBook.size() + " member in Contact Book Currently, Bye!");
				a = 1;
			}
			else if (ContactBook.containsKey(name)) {
				System.out.println("Find Member, " + name + "'s phone number : " + ContactBook.get(name));
			}
			else {
				System.out.print("New Member, Please enter a number (Cancel:0) : ");
				String number = scn.next();
				if (number.equals("0")) {
					System.out.println("Cancel Success.");
				}
				else {
					ContactBook.put(name, number);
					System.out.println("Save Success.");
				}
            }
            scn.close();
		}
	}
}