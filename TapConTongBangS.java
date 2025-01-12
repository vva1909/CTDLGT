import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Scanner;

public class TapConTongBangS {
    // hàm lập bài toán cơ sở

    static int[][] dp_mp;
    private static int[] initDP(int s) {
        int[] dp = new int[s + 1];
        dp[0] = 1; // tập rỗng luôn có tổng bằng 0 lên dp[0] = 1
        for (int i = 1; i <= s; i++) {
            dp[i] = 0; // còn lại cho bằng 0
        }
        return dp;
    }

    // công thức quy hoạc động
    private static void solveSubsetSum(int[] dp, int[] a, int n, int s) {
        dp_mp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = s; j >= 0; j--) {
                if (j >= a[i] && dp[j - a[i]] == 1) {
                    dp[j] = 1;
                }
                dp_mp[i][j] = dp[j];
            }
        }
    }

    // Hàm tạo bảng giao diện
    private static JScrollPane createTablePanel(int[][] dp_mp, int[] a, int s, int n) {
        // Tạo tiêu đề cột
        String[] columnNames = new String[s + 2]; // Cột đầu tiên là "a[i]"
        columnNames[0] = "a[i]";
        for (int i = 0; i <= s; i++) {
            columnNames[i + 1] = String.valueOf(i); // Tên cột từ 0 -> s
        }

        // Tạo dữ liệu bảng
        String[][] data = new String[n + 1][s + 2];
        for (int i = 0; i <= n; i++) {
            data[i][0] = String.valueOf(a[i]); // Tên hàng là các giá trị của mảng a[i]
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

        //  nhập n và s
        int n = 4;//sc.nextInt();
        int s = 5;//sc.nextInt();

        // tạp mảng dp
        int[] dp = initDP(s);

        // nhập các giá trị mảng
        int[] a = new int[]{0, 2, 3, 1, 4};
        dp_mp = new int[5][6];
//        for (int i = 1; i <= n; i++) {
//            a[i] = sc.nextInt();
//        }

        // gọi hàm quy hoạch động
        solveSubsetSum(dp, a, n, s);

        // kết quả
        System.out.println(dp[s]);
        // Tạo giao diện
        JFrame frame = new JFrame("Mô phỏng ma trận dp_mp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Thêm bảng vào giao diện
        frame.add(createTablePanel(dp_mp, a, s, n), BorderLayout.CENTER);

        // Hiển thị giao diện
        frame.setSize(600, 350);
        frame.setLocationRelativeTo(null); // Căn giữa màn hình
        frame.setVisible(true);
    }
}
