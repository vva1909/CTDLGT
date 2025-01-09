
package w1;

public class LongestIncreasingSubsequence {
    
   public static int lis(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int max = 0;

        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] ) {
                    dp[i] = max( dp[i], dp[j] + 1);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (max < dp[i]) {
                max = dp[i];
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = {10, 22, 9, 33, 21, 50, 41, 60, 80};
        System.out.println("Length of LIS is: " + lis(arr));
    }
}

