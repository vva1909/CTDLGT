import java.util.Scanner;

public class LongestCommonSubsequence {

    public static Result findLCS(String X, String Y) {
        int m = X.length();
        int n = Y.length();

        // Tạo mảng trạng thái dp
        int[][] dp = new int[m + 1][n + 1];

        // Điền bảng dp theo công thức truy hồi
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
            } else if (dp[i - 1][j] > dp[i][j - 1]) { // Di chuyển lên trên
                i--;
            } else { // Di chuyển sang trái
                j--;
            }
        }

        // Đảo ngược chuỗi vì truy ngược từ cuối lên đầu
        lcs.reverse();

        // Trả về kết quả
        return new Result(dp[m][n], lcs.toString());
    }

    // Lớp kết quả để lưu độ dài LCS và chuỗi LCS
    static class Result {
        int length;
        String lcs;

        Result(int length, String lcs) {
            this.length = length;
            this.lcs = lcs;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Nhập hai chuỗi X và Y
        System.out.println("Nhập chuỗi X:");
        String X = sc.nextLine();
        System.out.println("Nhập chuỗi Y:");
        String Y = sc.nextLine();

        // Tìm LCS và in kết quả
        Result result = findLCS(X, Y);
        System.out.println("đo dai LCS: " + result.length);
        System.out.println("LCS: " + result.lcs);
    }
}
