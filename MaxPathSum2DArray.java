package mang2chieu;

import java.util.Scanner;

public class MaxPathSum2DArray {
    public static final int N = 1010;
    public static long[][] a = new long[N][N];
    public static long[][] dp = new long[N][N];
    public static int m, n;
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập kích thước mảng M x N
        n = scanner.nextInt();
        m = scanner.nextInt();

        // Nhập mảng A
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                a[i][j] = scanner.nextLong();
            }
        }

        // Gọi hàm để tính đường đi lớn nhất
        calculateMaxPathSum();

        // In ra kết quả
        System.out.println(dp[m][n]);

        scanner.close();
    }
    
    public static void calculateMaxPathSum() {
        // Tính toán đường đi lớn nhất
        for (int i = 1 ; i <= m ; i++){
            dp[i][1] = a[i][1];
            for (int j = 1 ; j <= n ; j++){
                dp[1][j] = a[1][j];
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 2; j <= n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + a[i][j];
            }
        }
    }

}
