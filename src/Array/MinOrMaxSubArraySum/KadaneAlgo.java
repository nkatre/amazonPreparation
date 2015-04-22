/*
 * Question: Find min subarray sum
 * Question Source: http://www.careercup.com/question?id=5117551707160576
 * Answer: Min or max subarray sum can be found using kadane algorithm
 * Answer Source: http://www.algorithmist.com/index.php/Kadane's_Algorithm
 * https://gist.github.com/arunma/3624849
 */

package Array.MinOrMaxSubArraySum;

import java.util.Scanner;

public class KadaneAlgo {
	public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        try{
            System.out.println("Enter the number of array elements");
            int n = in.nextInt();
            int[] a = new int[n];
            System.out.println("Enter the array elements");
            for(int i=0;i<n;i++)
                a[i]=in.nextInt();
            System.out.println("The min sum in the array is: "+variationOfKadaneAlgo(a));   // Handles negative numbers (DP technique)
            System.out.println("The min sum in the array is: "+usingKadanesAlgorithm(a));  // Does not handle negative numbers
            System.out.println("The max sum in the array is: ");
            findMaxSubArray(a);
        }
        finally{
        in.close();
        }
}
	
	/*
	   Example:
			1. Array = -2 -3 -4 -8 -2 -6 -5 -3  (Extreme Case: All negative numbers)
			Expected Output: The max sum in the array is: -2
	  		Actual Output using this method: The max sum in the array is: -2
	  	
    VERY IMP NOTE: This method handles the Extreme Case: All negative numbers
	   This Solution uses Dynamic Programming
	   Source: http://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
		*/  
	private static int variationOfKadaneAlgo(int[] a) {
		int currentSum = a[0];
		int minSum = a[0];
		for(int i=0;i<a.length;i++){
			currentSum = Math.min(a[i], a[i]+currentSum);
			minSum = Math.min(minSum, currentSum);
		}
		return minSum;
	}
	// NOTE: This method DOES NOT handle the Extreme Case: All negative numbers
		/*
	 		Example:
			1. Array = -2 -3 -4 -8 -2 -6 -5 -3  (Extreme Case: All negative numbers)
			Expected Output: The max sum in the array is: -2
			Actual Output using this method: The max sum in the array is: 0
			*/
		private static int usingKadanesAlgorithm(int[] a) {
			if(a==null||a.length==0)
				return Integer.MAX_VALUE;
			
			int currentSum=0;
			int minSum=0;
			
			for(int i=0;i<a.length;i++){
				currentSum = currentSum + a[i];
				if(currentSum<0)
					currentSum=0;
				minSum = Math.min(currentSum, minSum);
			}
			return minSum;
		}
		
		// Algorithm Source: http://www.algorithmist.com/index.php/Kadane's_Algorithm
		// Answer Source: https://gist.github.com/arunma/3624849
		public static void findMaxSubArray(int[] inputArray){
			 
	        int maxStartIndex=0;
	        int maxEndIndex=0;
	        int maxSum = Integer.MIN_VALUE; 
	 
	        int cumulativeSum= 0;
	        int maxStartIndexUntilNow=0;
	        		
	        for (int currentIndex = 0; currentIndex < inputArray.length; currentIndex++) {
	        	
	            int eachArrayItem = inputArray[currentIndex];
	            
	            cumulativeSum+=eachArrayItem;
	 
	            if(cumulativeSum>maxSum){
	                maxSum = cumulativeSum;
	                maxStartIndex=maxStartIndexUntilNow;
	                maxEndIndex = currentIndex;
	            }
	            if (cumulativeSum<0){          // IMP NOTE: Do not put else, it should be if
	            	maxStartIndexUntilNow=currentIndex+1;
	            	cumulativeSum=0;
	            }
	        }
	 
	        System.out.println("Max sum         : "+maxSum);
	        System.out.println("Max start index : "+maxStartIndex);
	        System.out.println("Max end index   : "+maxEndIndex);
	    }
}
/*
Analysis:
Time Complexity = O(n)
Space Complexity = O(1)
*/