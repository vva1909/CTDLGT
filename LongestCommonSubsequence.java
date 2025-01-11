import java.util.Scanner;

public class LongestCommonSubsequence {

    public static String LCS(String X, String Y) {
        int m = X.length();
        int n = Y.length();

        // Tạo mảng trạng thái dp
        int[][] dp = new int[m + 1][n + 1];

        // Khởi tạo các giá trị cơ sở của bảng dp
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) { // Nếu ký tự cuối trùng
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else { // Nếu ký tự cuối không trùng
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Truy ngược để tìm chuỗi con chung dài nhất
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) { // Nếu ký tự cuối trùng
                lcs.append(X.charAt(i - 1)); // Thêm ký tự vào kết quả
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        // Đảo ngược chuỗi vì truy ngược từ cuối lên đầu
        lcs.reverse();

        // Trả về kết quả
        return lcs.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Nhập hai chuỗi X và Y
        System.out.println("Nhập chuỗi X:");
        String X = sc.nextLine();
        System.out.println("Nhập chuỗi Y:");
        String Y = sc.nextLine();

        // Tìm LCS và in kết quả
        String result = LCS(X, Y);
        System.out.println("đo dai LCS: " + result.length());
        System.out.println("LCS: " + result);
    }

