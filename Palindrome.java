/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package palindrome;
import java.util.Scanner;
import java.util.Scanner;

public class Palindrome {

    // Hàm kiểm tra xâu con đối xứng và trả về ma trận F
    public static boolean[][] checkPalindrome(String S, int n) {
        boolean[][] F = new boolean[n][n];

        // Mọi xâu con có độ dài 1 đều đối xứng
        for (int i = 0; i < n; i++) {
            F[i][i] = true;
        }

        // Xét các xâu con có độ dài 2
        for (int i = 0; i < n - 1; i++) {
            if (S.charAt(i) == S.charAt(i + 1)) {
                F[i][i + 1] = true;
            }
        }

        // Xét các xâu con có độ dài lớn hơn 2
        for (int length = 3; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1; // Vị trí cuối của xâu con

                if (S.charAt(i) == S.charAt(j) && F[i + 1][j - 1]) {
                    F[i][j] = true;
                }
            }
        }

        return F;
    }

    // Hàm tìm độ dài của xâu con đối xứng dài nhất
    public static int findMaxLength(boolean[][] F, int n) {
        int maxLength = 1;

        // Tìm độ dài xâu con đối xứng dài nhất
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (F[i][j]) {
                    maxLength = Math.max(maxLength, j - i + 1);
                }
            }
        }

        return maxLength;
    }

    // Hàm in ma trận F
    public static void printMatrix(boolean[][] F, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(F[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Hàm main
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.nextLine();
        scanner.close();
        int n = S.length();
        boolean[][] F = checkPalindrome(S, n);
        printMatrix(F, n);
        int maxLength = findMaxLength(F, n);
        System.out.println("Max Palindrome Length: " + maxLength);
    }
}
maxLength);
    }
}
