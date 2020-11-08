//Hotel object와 10개의 Room object를 만들어 check in, check out 기능을 구현하라.
// 각 room의 capacity를 출력한 후 check in/out, room number, 예약 인원을 받아라.

import java.util.Scanner;
import java.util.Random;

public class LA3_Hotel {
   static void checkin(int n, int j, Room[] r) {
      if (r[n].State == false)
         System.out.println("Already check in!");
      else if (r[n].Cap < j)
         System.out.println("Too many people");
      else {
         System.out.println("Check in Finish");
         r[n].State = false;
      }

   }
   static void checkout(int n, Room[] r){
      if(r[n].State == true)
         System.out.println("Already check out!");
      else {
         System.out.println("Check out Finish");
         r[n].State = true;
      }      
   } 
   public static void main(String[] args) {
      Room[] room = new Room[10];
      for (int i=0;i<10;i++)
      {
         Random random = new Random();
         room[i]= new Room(random.nextInt(4) + 1);
      }
      System.out.println("Each Room's Capacity");
      for (int i=0;i < 10;i++)
      {
    	  System.out.print(room[i].Cap + " ");
      }
      System.out.println();
    		  
      for(int j = 0; j < 10; j++) {
         System.out.println("Enter number (1 : check in), (2 : check out), (3 : Finish)");
         Scanner scn = new Scanner(System.in);
         int i = scn.nextInt();
         scn.close();
         if (i == 1) {
            System.out.println("Enter room number");
            int rn = scn.nextInt();
            System.out.println("How many people?");
            int pn = scn.nextInt();
            LA3_Hotel.checkin(rn-1, pn, room);
         }
         else if (i == 2) {
            System.out.println("Enter room number");
            int rn = scn.nextInt();
            LA3_Hotel.checkout(rn-1, room);
         }
         else
            j = 10;
      }      
      System.out.println();
      System.out.println();
   }
}

class Room {
    Boolean State = true; // true�� Vacancy
    int Cap;

	public Room(int capacity){
		this.Cap = capacity;
	}
}