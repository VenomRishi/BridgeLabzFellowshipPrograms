/******************************************************************************
 *  Purpose: Program is for insertion sort of integer.
 *  		 
 *
 *  @author  Rishikesh Mhatre
 *  @version 1.0
 *  @since   10-09-2019
 *
 ******************************************************************************/

package com.bridgelabz.algorithmPrograms;

import com.bridgelabz.utility.AlgorithmUtility;

public class P7InsertionSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] intArr = { 1, 5, 2, 6, 7, 9, 3 };
		System.out.println("Before sorting");
		AlgorithmUtility.printIntArr(intArr);
		AlgorithmUtility.intInsertionSort(intArr);
		System.out.println("After sorting");
		AlgorithmUtility.printIntArr(intArr);
		

	}

}