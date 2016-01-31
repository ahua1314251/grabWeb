package grab;

public class CodeUtil {

	public static long getNextCode(String code){
		Long a = Long.parseLong(code.substring(0, code.length()-1));
		a++;
		Integer last = getCheckCode(a.toString());
		return Long.parseLong(a.toString()+last.toString());
	}
	
	public static Integer getCheckCode(String code){
		int NUM = 16;
		int[] a = new int[NUM];
		int[] p = new int[NUM];
		int temp;

		if(code.length() != NUM-2){
		return null;
		}

		for(int i= 0; i < code.length(); i++){
			
		a[i+1] = Integer.parseInt(code.substring(i,i+1));
		}

		p[1] = 10;
		for(int i = 2; i < NUM; i++){
		temp = (p[i-1]+a[i-1])%10;
		if(temp == 0){
		p[i] = (10*2)%11;
		}else{
		p[i] =  (temp*2 %11);
		}
		}
		return (11 - p[NUM-1])%10;
		}
}
