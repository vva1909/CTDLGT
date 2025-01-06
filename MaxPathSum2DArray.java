package mang2chieu;

import java.util.Scanner;

public class MaxPathSum2DArray {
    public static final int N = 1010;
    public static long[][] a = new long[N][N];
    public static long[][] f = new long[N][N];
    public static int m, n;
    
    public static void calculateMaxPathSum() {
        // Tính toán đường đi lớn nhất
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]) + a[i][j];
            }
        }
    }
    
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
        System.out.println(f[m][n]);

        scanner.close();
    }

}