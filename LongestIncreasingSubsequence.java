package w1;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;

public class LongestIncreasingSubsequence {

    public static List<int[]> dpSteps = new ArrayList<>();

    // Hàm tính LIS và lưu trạng thái các bước vào dpSteps
    public static int lis(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        int Max = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        dpSteps.add(dp.clone()); // Lưu trạng thái ban đầu

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = max(dp[i], dp[j] + 1);
                }
            }
            dpSteps.add(dp.clone()); // Lưu trạng thái sau mỗi bước cập nhật dp[i]
        }

        for (int i = 0; i < n; i++) {
            Max = max(Max, dp[i]);
        }

        return Max;
    }

    // Hàm tạo bảng giao diện
    // Hàm tạo bảng giao diện
    private static JScrollPane createTablePanel(int[] arr, int [] a, List<int[]> dpSteps) {
        // Tạo tiêu đề cột là các chỉ số từ 0 đến n-1
        String[] columnNames = new String[arr.length + 1];
        columnNames[0] = "a[i]";
        for (int i = 1; i <= arr.length; i++) {
            columnNames[i] = String.valueOf(i); // Tiêu đề cột là các chỉ số của mảng
        }

        // Tạo dữ liệu bảng
        String[][] data = new String[dpSteps.size()][arr.length + 1];
        for (int i = 0; i < dpSteps.size(); i++) {
            data[i][0] = String.valueOf(a[i]);
            for (int j = 1; j <= arr.length; j++) {
                data[i][j] = String.valueOf(dpSteps.get(i)[j - 1]);
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
        scrollPane.setBorder(BorderFactory.createTitledBorder("Quá trình tính toán LIS (dp array)"));
        return scrollPane;
    }

    public static void main(String[] args) {
        // Mảng đầu vào
        int[] arr = {3, 1, 4, 2, 5};

        // Tính LIS và lưu các bước
        int lengthOfLIS = lis(arr);

        // Tạo giao diện
        JFrame frame = new JFrame("Mô phỏng Longest Increasing Subsequence");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Hiển thị bảng trạng thái dp qua các bước
        frame.add(createTablePanel(arr, arr, dpSteps), BorderLayout.CENTER);

        // Hiển thị kết quả LIS
        JLabel resultLabel = new JLabel("Độ dài của LIS là: " + lengthOfLIS, SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(resultLabel, BorderLayout.SOUTH);

        // Hiển thị giao diện
        frame.setSize(800, 360);
        frame.setLocationRelativeTo(null); // Căn giữa màn hình
        frame.setVisible(true);
    }
}
