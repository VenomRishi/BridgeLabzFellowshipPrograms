package com.bridgelabz.algorithm;

import java.util.Scanner;

import com.bridgelabz.utility.AlgorithmUtility;

public class P11DayOfWeek {

	public static void main(String[] args) {
		AlgorithmUtility utility = new AlgorithmUtility();
		Scanner scanner = new Scanner(System.in);
		int day, month, year, d1;
		
		System.out.println("Enter Day: ");
		day = scanner.nextInt();
		System.out.println("Enter Month: ");
		month = scanner.nextInt();
		System.out.println("Enter Year: ");
		year = scanner.nextInt();

		d1 = utility.calculateDayOfWeek(day, month, year);

		switch (d1) {
		case 0:
			System.out.println("Day is Sunday");
			break;
		case 1:
			System.out.println("Day is Monday");
			break;
		case 2:
			System.out.println("Day is Tuesday");
			break;
		case 3:
			System.out.println("Day is Wednesday");
			break;
		case 4:
			System.out.println("Day is Thursday");
			break;
		case 5:
			System.out.println("Day is Friday");
			break;
		case 6:
			System.out.println("Day is Saturday");
			break;
		}
		
		scanner.close();
	}

}