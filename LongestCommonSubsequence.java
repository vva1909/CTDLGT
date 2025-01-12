import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LongestCommonSubsequence {

    public static List<int[][]> dpSteps = new ArrayList<>(); // Lưu trạng thái dp qua các bước

    // Hàm tính LCS và lưu trạng thái dp
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
        dpSteps.add(dp.clone());

        // Tính toán bảng dp
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
            dpSteps.add(dp.clone()); // Lưu trạng thái dp sau mỗi bước tính toán
        }

        // Truy ngược để tìm chuỗi con chung dài nhất
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                lcs.append(X.charAt(i - 1));
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

        return lcs.toString();
    }

    // Hàm tạo bảng dp giao diện
    private static JScrollPane createTablePanel(String X, String Y, List<int[][]> dpSteps) {
        int m = X.length();
        int n = Y.length();

        // Tạo tiêu đề cột là các giá trị từ chuỗi X
        String[] columnNames = new String[n + 2];
        columnNames[0] = "X/Y";
        columnNames[1] = "''"; // Ô trống ở góc trên cùng
        for (int i = 1; i <= n; i++) {
            columnNames[i + 1] = String.valueOf(Y.charAt(i - 1)); // Tiêu đề cột là các ký tự trong Y
        }

        // Tạo dữ liệu bảng
        String[][] data = new String[m + 1][n + 2];
        for (int i = 0; i <= m; i++) {
            data[i][0] = i == 0 ? "''" : String.valueOf(X.charAt(i - 1)); // Tiêu đề hàng là các ký tự trong X
            for (int j = 0; j <= n; j++) {
                data[i][j + 1] = String.valueOf(dpSteps.get(i)[i][j]);
            }
        }

        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        table.setEnabled(false); // Chỉ hiển thị, không chỉnh sửa
        table.setRowHeight(50); // Chiều cao hàng
        table.getTableHeader().setReorderingAllowed(false); // Không thay đổi thứ tự cột

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < columnNames.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Quá trình tính toán LCS (dp array)"));
        return scrollPane;
    }

    public static void main(String[] args) {
        // Nhập hai chuỗi X và Y
        String Y = "ANHBINH";
        String X = "VANANH";

        // Tính LCS và lưu các bước
        String result = LCS(X, Y);

        // Tạo giao diện
        JFrame frame = new JFrame("Mô phỏng Longest Common Subsequence");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Hiển thị bảng trạng thái dp qua các bước
        frame.add(createTablePanel(X, Y, dpSteps), BorderLayout.CENTER);

        // Hiển thị kết quả LCS
        JLabel resultLabel = new JLabel("Độ dài của LCS là: " + result.length() + " | LCS: " + result, SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(resultLabel, BorderLayout.SOUTH);

        // Hiển thị giao diện
        frame.setSize(800, 455);
        frame.setLocationRelativeTo(null); // Căn giữa màn hình
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
