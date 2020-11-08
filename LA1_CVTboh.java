//decimal number를 받은 후 binary, octal, hexadecimal로 변환하여라.

import java.util.Scanner;
public class LA1_CVTboh {
    public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int i = scn.nextInt();
		scn.close();
		int bi = i;
		int oc = i;
		int hex = i;
		int a = i;
		int b = i;
		System.out.print("b ");
		int count = 1;
		while(i >= 2) {
			i = i / 2;
			count++;
		}
		int[] arr_bi = new int[count];
		arr_bi[count - 1] = 1;
		for(int j = 0; j < count - 1; j++) {
			arr_bi[j] = bi % 2;
			bi = bi / 2;
		}
		for(int k = count - 1; k >= 0; k--) {
			System.out.print(arr_bi[k]);
		}
		System.out.println();
		
		System.out.print("o ");
		int count_oc = 1;
		while(a >= 8) {
			a = a / 8;
			count_oc++;
		}
		int[] arr_oc = new int[count_oc];
		for(int j = 0; j < count_oc - 1; j++) {
			arr_oc[j] = oc % 8;
			oc = oc / 8;
		}
		arr_oc[count_oc - 1] = oc;
		for(int k = count_oc - 1; k >= 0; k--) {
			System.out.print(arr_oc[k]);
		}
		System.out.println();

		System.out.print("h ");
		int count_h = 1;
		while(b >= 16) {
			b = b / 16;
			count_h++;
		}
		char[] arr_hex = new char[count_h];
		for(int j = 0; j < count_h - 1; j++) {
			if(hex % 16 < 10) {
				arr_hex[j] = (char) (hex % 16 + 48);
			}
			else
			arr_hex[j] = (char)(hex % 16 + 87);
			hex = hex / 16;
		}
		if(hex < 10) {
			arr_hex[count_h - 1] = (char) (hex % 16 + 48);
		}
		else
		arr_hex[count_h - 1] = (char)(hex % 16 + 87);
		for(int k = count_h - 1; k >= 0; k--) {
			System.out.print(arr_hex[k]);
		}
	}
}