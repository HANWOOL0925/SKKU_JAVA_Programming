//사용자에게 input 받은 만큼(1~4) thread를 생성한 후,
//두 수를 입력받아(차가 20000 이상) 그 사이에 있는 소수의 개수를
//생성한 thread끼리 나누어 계산하여 출력하는 프로그램을 구현하라.

import java.util.Scanner;

public class LA8_PrimeThread {
	int PrimeSum = 0;
	int num;
	public synchronized void add(int Primenum){ //amount
		num = Primenum;
		PrimeSum = PrimeSum + num;
	}
	
	public void inquiry(int StartNum, int EndNum){
		System.out.println("There are " + num + " primes between " + (StartNum + 1) + " and " + EndNum);
		System.out.println( PrimeSum + " primes found so far.");
	}
}

class addThread implements Runnable{
	LA8_PrimeThread myThread;
	int stnum;
	int ennum;
	
	addThread(LA8_PrimeThread PrimeThread, int StartNum, int EndNum){
		myThread = PrimeThread;
		stnum = StartNum;
		ennum = EndNum;
	}
	
	@Override
	public void run() {
		synchronized(myThread){
			int Primenum = 0;
			int i;
			int j;
			for(i = stnum + 1; i <= ennum; i++) {
				int count = 0;
				for(j = 1; j <= i/2; j++) {
					if (i % j == 0){
						count++;
					}
				}
				if (count == 1){
					Primenum++;
				}
			}
			myThread.add(Primenum);
		}
		myThread.inquiry(stnum, ennum);
	}
}

class WithSynchronized extends Thread{
	public static void main(String[] args) {
		LA8_PrimeThread myThread = new LA8_PrimeThread();
		
		System.out.print("How many threads do you want? : ");
        Scanner scn = new Scanner(System.in);
        int test = 0;

        int thread = scn.nextInt();
        while(test == 0){
            if(thread<=4 && thread>=1)
                test = 1;
            
            else{
                System.out.print("Thread 개수는 1~4 사이의 자연수여야 합니다. 다시 입력해주세요. : ");
                thread = scn.nextInt();
                if(thread<=4 && thread>=1)
                    test = 1;
            }
        }
        
        System.out.print("Start Number : ");
        int StartNum = scn.nextInt();
        System.out.print("End Number : ");
        int EndNum = scn.nextInt();
        while(test == 1){
            if((EndNum - StartNum) >= 20000)
                test = 0;
            else{
                System.out.print("EndNum의 수는 StartNum보다 20000 이상 커야합니다. 다시 입력해주세요. : ");
                EndNum = scn.nextInt();
                if(EndNum<=4 && EndNum>=1)
                    test = 0;
            }
        }
        scn.close();

        System.out.println("Counting primes between " + (StartNum + 1) + " ~ " + EndNum + " using " + thread + " threads");
		
		
		for(int i=0; i<thread ; i++){
			int IntervalStartNum = StartNum + i*((EndNum-StartNum)/thread);
			int IntervalEndNum = StartNum + (i+1)*((EndNum-StartNum)/thread);
			if (i == thread - 1)
				IntervalEndNum = EndNum;
			Thread t = new Thread(new addThread(myThread, IntervalStartNum, IntervalEndNum));
			t.start();
		}
	}

}