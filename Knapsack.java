import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Scanner;
import java.util.Arrays;

public class Knapsack {

    private static JScrollPane createTablePanel(int[][] dp_mp, int[] w, int[]v, int s, int n) {
        // Tạo tiêu đề cột
        String[] columnNames = new String[s + 2]; // Cột đầu tiên là "a[i]"
        columnNames[0] = "w[i], v[i]";
        for (int i = 0; i <= s; i++) {
            columnNames[i + 1] = String.valueOf(i); // Tên cột từ 0 -> s
        }

        // Tạo dữ liệu bảng
        String[][] data = new String[n + 1][s + 2];
        for (int i = 0; i <= n; i++) {
            data[i][0] = w[i] +","+v[i]; // Tên hàng là các giá trị của mảng a[i]
            for (int j = 0; j <= s; j++) {
                data[i][j + 1] = String.valueOf(dp_mp[i][j]);
            }
        }

        // Tạo JTable
        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        table.setEnabled(false); // Chỉ hiển thị, không chỉnh sửa
        table.setRowHeight(50); // Chiều cao hàng
        table.getTableHeader().setReorderingAllowed(false); // Không thay đổi thứ tự cột

        // Căn giữa nội dung trong ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < columnNames.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Đặt JTable vào JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Ma trận dp_mp (Hàng là a[i], Cột là tổng)"));
        return scrollPane;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Nhập số lượng vật phẩm và trọng lượng tối đa của túi
        int n = 4; //sc.nextInt();  // Số lượng vật phẩm
        int W = 7; //sc.nextInt();  // Trọng lượng tối đa

        int[] w = new int[]{0, 3, 2, 4, 1}; // Trọng lượng của các vật phẩm
        int[] v = new int[]{0, 4, 3, 2, 5}; // Giá trị của các vật phẩm

        // Nhập trọng lượng và giá trị của từng vật phẩm
//        for (int i = 1; i <= n; i++) {
//            w[i] = sc.nextInt();
//        }
//        for (int i = 1; i <= n; i++) {
//            v[i] = sc.nextInt();
//        }

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
        // Tạo giao diện
        JFrame frame = new JFrame("Mô phỏng ma trận dp_mp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Thêm bảng vào giao diện
        frame.add(createTablePanel(dp, w, v, W, n), BorderLayout.CENTER);

        // Hiển thị giao diện
        frame.setSize(600, 350);
        frame.setLocationRelativeTo(null); // Căn giữa màn hình
        frame.setVisible(true);
    }
}
