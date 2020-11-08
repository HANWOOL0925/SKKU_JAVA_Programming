// addString(String s1, int index, String s2)
// s1의 index position 뒤에 s2를 붙이는 함수
// reverse(String s)
// s 문자열의 순서를 거꾸로 출력해주는 함수
// removeString(String s1, String s2)
// s1에서 s2 문자열을 제거한 후 출력

public class LA2_StringMethod {
	static String addString(String s1, int index, String s2) {
		String s = s1.substring(0, index + 1);
		String st = s.concat(s2);
		String str = st.concat(s1.substring(index + 1, s1.length()));
		return str;
	}
	static String reverse(String s) {
		String stri = s;
		for(int i = s.length() - 1; i >= 0 ; i--) {
			stri = stri.concat(s.substring(i, i + 1));
		}
		String strin = stri.substring(s.length(),stri.length());
		return strin;
	}

	static String removeString (String s1, String s2) {
		for(int i = 0; i <= s1.length() - s2.length(); i++) {
			String string = s1.substring(i, i + s2.length());
			if(string.equals(s2)) {
				s1 = s1.replace(s2, "");
			}
		}
		String string1 = s1;
		return string1;
	}
	public static void main(String[] args) {
		System.out.println(addString("0123456",3,"-"));
		System.out.println(reverse("abc"));
		System.out.println(removeString("01001000","00"));
	}
}