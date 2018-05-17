package os_2;

import java.util.Scanner;

public class Main {
	static int num = 0;
	static boolean isExit = false;
	
	private static void menu() {
		System.out.println("*****************************************************");
		System.out.println("           1. new To ready");
		System.out.println("           2. ready To running");
		System.out.println("           3. running To exit");
		System.out.println("           4. running To blocked");
		System.out.println("           5. blocked To ready");
		System.out.println("           6. running To ready");
		System.out.println("           7. display process in ready");
		System.out.println("           8. display process in running");
		System.out.println("           9. display process in blocked");
		System.out.println("          10. consume cputime");
		System.out.println("          11. exit");
		System.out.println("*****************************************************");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Manage manage = new Manage();
		while(!isExit) {
			//menu
			menu();
			System.out.println("input the number: ");
			num = sc.nextInt();
			if(num<0 || num>11) {
				System.out.println("numbei isn't legal!");
				sc.nextLine();
				continue;
			}
			manage.manage();
		}
		sc.close();
	}
	
}
