//text file을 읽어 한 줄씩 저장한 후 line을 반대 순서로 저장하라.

import java.util.Vector;
import java.io.*;
import java.util.Scanner;

public class LA5_FileVector {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		try {
			FileOutputStream fos = new FileOutputStream("C:\\Users\\한울\\Desktop\\input.txt");
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter bw = new BufferedWriter(osw);

			System.out.println("Enter \"exit\" if you want to finish entering");
			int i = 0;
			for(i=0; i<30;i++){
				String sentence = scn.next();
				if (sentence.equals("exit")){
					i = 31;
					break;
				}
				bw.write(sentence);
				bw.newLine();
			}
			bw.flush();
            bw.close();
            fos.close();
            osw.close();
			scn.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		Vector<String> word = new Vector<String>(30);

		try{
			File file = new File("C:\\Users\\한울\\Desktop\\input.txt");
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);

			String line ="";
			int j = 0;
			while ( (line = bufReader.readLine())!=null){
				word.add(j, line);
				j++;
			}
			bufReader.close();
			try{
				FileOutputStream fos1 = new FileOutputStream("C:\\Users\\한울\\Desktop\\output.txt");
				OutputStreamWriter osw1 = new OutputStreamWriter(fos1);
				BufferedWriter bw1 = new BufferedWriter(osw1);

				for(j = word.size() - 1; j >= 0; j--){
					bw1.write(word.size()-j + " : " + word.get(j));
					bw1.newLine();
				}
				bw1.flush();
				bw1.close();fos1.close();osw1.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
			
		
		catch (FileNotFoundException e){
			System.out.println("error");
		}
		catch(IOException e){
			System.out.println(e);
		}
	}
}