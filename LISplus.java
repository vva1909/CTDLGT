/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package w1;

import java.util.Scanner;

public class LISplus {

    public static final int N = 1010;
    public static int[] a = new int[N];
    public static int[] b = new int[N];
    public static int n,j;
    private static int ma;

    public static int cnp(int x) {
        int ans = 0;
        int l = 1, r = ma;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (b[mid] < x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    public static int LIS(int [] a) {
        for (int i = 1; i <= n; i++) {
            int j = cnp(a[i]);
            b[j + 1] = a[i];
            ma = Integer.max(ma, j + 1);
        }
        return ma ;
    }
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        n = sc.nextInt();
        // Mảng đầu vào
        for (int i = 1 ; i <= n ; i++){
            a[i] = sc.nextInt();
        }
        // Tính LIS và lưu các bước
        System.err.println(LIS(a));
    }
    
}
