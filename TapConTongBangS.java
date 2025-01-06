package ctdl_b24;

import java.util.Scanner;

public class TapConTongBangS {
    // hàm lập bài toán cơ sở
    private static int[] initDP(int s) {
        int[] dp = new int[s + 1];
        dp[0] = 1; // tập rỗng luôn có tổng bằng 0 lên dp[0] = 1
        for (int i = 1; i <= s; i++) {
            dp[i] = 0; // còn lại cho bằng 0
        }
        return dp;
    }

    // công thức quy hoạc động
    private static void solveSubsetSum(int[] dp, int[] a, int n, int s) {
        for (int i = 1; i <= n; i++) {
            for (int j = s; j >= a[i]; j--) {
                if (dp[j - a[i]] == 1) {
                    dp[j] = 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //  nhập n và s
        int n = sc.nextInt();
        int s = sc.nextInt();

        // tạp mảng dp
        int[] dp = initDP(s);

        // nhập các giá trị mảng
        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = sc.nextInt();
        }

        // gọi hàm quy hoạch động
        solveSubsetSum(dp, a, n, s);

        // kết quả
        System.out.println(dp[s]);
    }
}