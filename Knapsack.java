import java.util.Scanner;
import java.util.Arrays;

public class Knapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Nhập số lượng vật phẩm và trọng lượng tối đa của túi
        int n = sc.nextInt();  // Số lượng vật phẩm
        int W = sc.nextInt();  // Trọng lượng tối đa

        int[] w = new int[n + 1]; // Trọng lượng của các vật phẩm
        int[] v = new int[n + 1]; // Giá trị của các vật phẩm

        // Nhập trọng lượng và giá trị của từng vật phẩm
        for (int i = 1; i <= n; i++) {
            w[i] = sc.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            v[i] = sc.nextInt();
        }

        // Khởi tạo bảng DP
        int[][] dp = new int[n + 1][W + 1];
        for (int[] row : dp) {
            Arrays.fill(row, 0);
        }

        // Quy hoạch động để tính toán giá trị tối đa
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                // Không chọn vật phẩm thứ i
                dp[i][j] = dp[i - 1][j];
                
                // Có thể chọn vật phẩm thứ i
                if (j >= w[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - w[i]] + v[i]);
                }
            }
        }

        // Xuất bảng DP (tùy chọn, để kiểm tra kết quả)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        // Kết quả: Giá trị lớn nhất đạt được
        System.out.println(dp[n][W]);
    }
}
