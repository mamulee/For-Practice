package DTO_DAO;

public class test {

	public static void main(String[] args) {
		int i = 0;
		int sum =0;
		while(i<10) {
			i++;
			System.out.println("i :"+i+" || i%2 = "+(i%2));
			if(i%2==1) {
				continue;
			}
			sum += i;
		}
		System.out.println(sum);
	}

}