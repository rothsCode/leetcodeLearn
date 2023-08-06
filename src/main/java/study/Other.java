package study;

import java.util.Arrays;

/**
 * @author lishuai
 * @Description:
 * @date 2023/8/3 16:54
 */
public class Other {

  /**
   * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
   */
  public int[] moveZeroNums(int[] nums) {
    int slowIndex = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 0) {
        nums[slowIndex] = nums[i];
        slowIndex++;
      }
    }
    for (int i = slowIndex; i < nums.length; i++) {
      nums[slowIndex] = 0;
    }
    return nums;
  }

  /**
   * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。 找出给定目标值在数组中的开始位置和结束位置。
   */
  public int[] findTarget(int[] nums, int target) {
    int[] startNums = new int[2];
    if (nums[0] > target || nums[nums.length - 1] < target) {
      return new int[]{-1, -1};
    }
    int mid = nums.length / 2;
    int left = 0;
    int right = nums.length;
    while (left < right) {
      mid = mid + (right - left) / 2;
      if (target > nums[mid]) {
        left = mid + 1;
      } else if (target < nums[mid]) {
        right = mid - 1;
      }
    }
    return new int[]{-1, -1};
  }

  /**
   * 奇偶数数组
   */

  public int[] qNums(int[] nums) {
    int qIndex = 1;
    int oIndex = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] % 2 == 0) {
        nums[oIndex] = nums[i];
        oIndex = oIndex + 2;
      } else {
        nums[qIndex] = nums[i];
        qIndex = qIndex + 2;
      }
    }
    return nums;
  }

  /**
   * 在一个 平衡字符串 中，'L' 和 'R' 字符的数量是相同的。
   * <p>
   * 给你一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
   * <p>
   * 注意：分割得到的每个字符串都必须是平衡字符串。
   * <p>
   * 返回可以通过分割得到的平衡字符串的 最大数量
   */
  public int getBalanceCount(String s) {
    int result = 0;
    int RCount = 0;
    for (char c : s.toCharArray()) {
      if ('R' == c) {
        RCount++;
      } else {
        RCount--;
      }
      if (RCount == 0) {
        result++;
      }
    }
    return result;
  }

  /**
   * 给你一个字符串 s，找到 s 中最长的回文子串。
   */
  public String maxHwText(String s) {
    int left = 0;
    int right = 0;
    int maxLength = 0;
    boolean[][] dp = new boolean[s.length()][s.length()];
    for (int i = s.length() - 1; i >= 0; i--) {
      for (int j = i; j <= s.length() - 1; j++) {
        if (s.charAt(i) == s.charAt(j)) {
          if (i - j <= 1) {
            dp[i][j] = true;
          } else if (dp[i + 1][j - 1]) {
            dp[i][j] = true;
          }
        }
        int curLength = i - j + 1;
        if (dp[i][j] && curLength > maxLength) {
          maxLength = curLength;
          left = i;
          right = j;
        }
      }
    }
    return s.substring(left, right);
  }

  /**
   * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。 返回符合要求的 最少分割次数 。
   */
  public int minSplitHw(String s) {
    int[] minDp = new int[s.length()];
    boolean[][] dp = new boolean[s.length()][s.length()];
    for (int i = s.length() - 1; i >= 0; i--) {
      for (int j = i; j <= s.length() - 1; j++) {
        if (s.charAt(i) == s.charAt(j)) {
          if (i - j <= 1) {
            dp[i][j] = true;
          } else if (dp[i + 1][j - 1]) {
            dp[i][j] = true;
          }
        }
      }
    }
    //初始化
    for (int i = 0; i < s.length(); i++) {
      minDp[i] = i;
    }
    for (int i = 1; i < s.length(); i++) {
      if (dp[0][i]) {
        minDp[i] = 0;
        continue;
      }
      for (int j = 0; j < i; j++) {
        if (dp[j + 1][i]) {
          minDp[i] = Math.min(minDp[i], minDp[j] + 1);
        }
      }
    }
    return minDp[s.length() - 1];
  }

  /**
   * 给定一个未排序的整数数组，找到最长递增子序列的个数。 1 3 5 4 7
   */
  //先找到最大长度在统计数量
  public int maxIncreaseCount(int[] nums) {
    if (nums.length <= 1) {
      return nums.length;
    }
    //最大长度
    int[] dp = new int[nums.length];
    //最大长度数量
    int[] count = new int[nums.length];
    int maxLength = 1;
    int maxCount = 1;
    //初始化
    Arrays.fill(dp, 1);
    Arrays.fill(count, 1);
    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          //存在更大长度,最大数量不增加
          if (dp[j] + 1 > dp[i]) {
            count[i] = count[j];
            //最大长度相等则数量加
          } else if (dp[j] + 1 == dp[i]) {
            count[i] = count[j] + 1;
          }
          dp[i] =Math.max(dp[i],dp[j]+1);
          //记录最大长度
          maxLength = Math.max(maxLength, dp[i]);
        }
      }
    }
    //统计最大长度maxLength 的数量
    for (int i = 1; i < nums.length; i++) {
      if (maxLength == dp[i]) {
        maxCount += count[i];
      }
    }
    return maxCount;
  }
}
