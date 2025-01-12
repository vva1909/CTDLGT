package palindrome;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

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

    private static JScrollPane createTablePanel(boolean[][] dp_mp, String s, int n) {
        // Tạo tiêu đề cột
        String[] columnNames = new String[n + 1]; // Cột đầu tiên là "a[i]"
        columnNames[0] = "S";
        for (int i = 0; i < n; i++) {
            columnNames[i + 1] = String.valueOf(s.charAt(i)); // Tên cột từ 0 -> s
        }

        // Tạo dữ liệu bảng
        String[][] data = new String[n][n + 1];
        for (int i = 0; i < n; i++) {
            data[i][0] = String.valueOf(s.charAt(i)); // Tên hàng là các giá trị của mảng a[i]
            for (int j = 0; j < n; j++) {
                data[i][j + 1] = String.valueOf(dp_mp[i][j]);
            }
        }

        // Tạo JTable
        JTable table = new JTable(new DefaultTableModel(data, columnNames)){
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (column > 0) {
                    // Bỏ qua cột đầu tiên (cột "a[i]")
                    if (dp_mp[row][column - 1]) {
                        c.setBackground(Color.green);
                    } else {
                        c.setBackground(Color.red);
                    }
                } else {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        };

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
        scrollPane.setBorder(BorderFactory.createTitledBorder("Ma trận F"));
        return scrollPane;
    }

    // Hàm main
    public static void main(String[] args) {
       /* Scanner scanner = new Scanner(System.in);
        String S = scanner.nextLine();
        scanner.close()*/;
        String S = "syuduyc";
        int n = S.length();
        boolean[][] F = checkPalindrome(S, n);
        printMatrix(F, n);
        int maxLength = findMaxLength(F, n);
        System.out.println("Max Palindrome Length: " + maxLength);

        // Tạo giao diện
        JFrame frame = new JFrame("Mô phỏng Palindrome");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Thêm bảng vào giao diện
        frame.add(createTablePanel(F, S, n), BorderLayout.CENTER);

        // Hiển thị giao diện
        frame.setSize(600, 435);
        frame.setLocationRelativeTo(null); // Căn giữa màn hình
        frame.setVisible(true);
    }
}
