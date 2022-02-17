package Assignmets;

import java.util.Scanner;

public class Assignment3 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("When do you go to bed? (10:30)");
		String sleep = input.next();
		String[] sleepTime = sleep.split(":");
		System.out.println("When do you get up? (6:30)");
		String getUp = input.next();
		input.close();
		String[] getUpTime = getUp.split(":");
		
		int sleepTimeHours[] = new int[2];
		sleepTimeHours[0] = (12+(Integer.parseInt(getUpTime[0]))) - Integer.parseInt(sleepTime[0]);
		sleepTimeHours[1] = Integer.parseInt(getUpTime[1])-Integer.parseInt(sleepTime[1]);
		
		
		System.out.println("SleepHours: " + sleepTimeHours[0] + ":" + sleepTimeHours[1]);
		
		if (sleepTimeHours[0] < 5)
			System.out.println("You are hardworking");
		else if (sleepTimeHours[0] >= 5 && sleepTimeHours[0] <= 8)
			System.out.println("You take care of your health well");
		else
			System.out.println("You are abnormal");
		
	}

}
