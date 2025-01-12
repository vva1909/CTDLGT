package mang2chieu;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Scanner;

public class MaxPathSum2DArray {
    public static final int N = 1010;
    public static int[][] a = new int[N][N];
    public static int[][] dp = new int[N][N];
    public static int m, n;

    public static void calculateMaxPathSum() {
        // Tính toán đường đi lớn nhất
        for (int i = 1; i <= m; i++) {
            dp[i][1] = a[i][1];
        }
        for (int j = 1; j <= n; j++) {
            dp[1][j] = a[1][j];
        }
        for (int i = 2; i <= m; i++) {
            for (int j = 2; j <= n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + a[i][j];
            }
        }
    }

    public static JScrollPane createTablePanel(int[][] matrix, int rows, int cols, String title) {
        // Tạo dữ liệu bảng
        String[] columnNames = new String[cols];
        for (int i = 0; i < cols; i++) {
            columnNames[i] = ""; // Không cần tiêu đề cột cho bảng chính
        }

        String[][] data = new String[rows][cols];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                data[i - 1][j - 1] = String.valueOf(matrix[i][j]);
            }
        }

        // Tạo bảng JTable
        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        table.setEnabled(false); // Chỉ hiển thị, không chỉnh sửa
        table.setRowHeight(50); // Chiều cao hàng (hình vuông)
        table.getTableHeader().setReorderingAllowed(false); // Không cho phép thay đổi thứ tự cột

        // Căn giữa nội dung trong ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Căn ngang giữa
        centerRenderer.setVerticalAlignment(SwingConstants.CENTER);   // Căn dọc giữa
        for (int i = 0; i < cols; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            table.getColumnModel().getColumn(i).setPreferredWidth(50); // Chiều rộng cột (hình vuông)
        }

        // Đặt bảng vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder(title));
        return scrollPane;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập kích thước mảng M x N
        n = 3;//scanner.nextInt();
        m = 3;//scanner.nextInt();

        a = new int[][]{
                {0, 0, 0, 0},
                {0, 3, 2, 5},
                {0, 1, 5, 7},
                {0, 9, 1, 8}
        };

        // Nhập mảng A
//        for (int i = 1; i <= m; i++) {
//            for (int j = 1; j <= n; j++) {
//                a[i][j] = scanner.nextLong();
//            }
//        }

        // Gọi hàm để tính đường đi lớn nhất
        calculateMaxPathSum();

        // In ra kết quả
        System.out.println(dp[m][n]);

        scanner.close();
        // Tạo giao diện
        JFrame frame = new JFrame("Hiển thị ma trận");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 2, 10, 10)); // Bố trí 2 bảng cạnh nhau

        // Thêm bảng ma trận vào cửa sổ
        frame.add(createTablePanel(a, m, n, "Ma trận ban đầu"));
        frame.add(createTablePanel(dp, m, n, "Ma trận DP"));

        // Hiển thị giao diện
        frame.setSize(800, 400);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
