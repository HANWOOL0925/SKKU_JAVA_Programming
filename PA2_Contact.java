//add, find, show, delete 기능이 구현된 contactbook을 구현하라.

import java.util.HashMap;
import java.util.Scanner;

public class PA2_Contact {
    public static void main(String[] args) {
        HashMap<String, String> ContactB = new HashMap<String, String>();

        int a = 0;
        while (a == 0) {
            Scanner scn = new Scanner(System.in);
            String command = scn.next();
            if (command.equals("add")) {
                String name = scn.next();
                String number = scn.next();
                if (!number.substring(3, 4).contentEquals("-") || !number.substring(8, 9).contentEquals("-"))
                    System.out.println("wrong number format (number format : 010-XXXX-YYYY)");
                else {
                    ContactB.put(name, number);
                    System.out.println("contact list "+ name + " add success");
                }
            }
            else if (command.equals("find")) {
                String name = scn.next();
                if (ContactB.containsKey(name)) {
                    System.out.println(name + ContactB.get(name));
                }
                else {
                    System.out.println("cannot find "+ name);
                }
            }
            else if (command.equals("delete")) {
                String name = scn.next();
                if (ContactB.containsKey(name)) {
                    System.out.println("delete " + name + " success");
                    ContactB.remove(name);
                }
                else {
                    System.out.println("cannot find "+ name);
                }
            }
            else if (command.equals("show")) {
                for (String key : ContactB.keySet()) {
                    String value = ContactB.get(key);
                    System.out.println(String.format(key));
                    System.out.println(String.format(value));
                }
            }
            else if (command.equals("end")) {
                a = 1;
            }
            else {
                System.out.println("error. There is no "+ command + " command");
			}
			scn.close();
        }
    }
}