
package w1;

public class LongestIncreasingSubsequence {
    
    public static int lengthOfLIS(int[] nums) {
        // nếu mảng đầu vào không có giá trị thì LIS = 0 
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // Mảng dp để lưu độ dài của LIS kết thúc tại mỗi chỉ số
        int[] dp = new int[nums.length];
        // Khởi tạo mọi phần tử trong dp bằng 1, vì mỗi số đơn lẻ là một chuỗi con tăng dài 1
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
        }

        int maxLength = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    // Nếu phần tử hiện tại lớn hơn phần tử trước đó, cập nhật dp[i]
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            // Cập nhật độ dài LIS lớn nhất
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Độ dài của chuỗi con tăng dài nhất là: " + lengthOfLIS(nums));
    }
}

