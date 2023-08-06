package study;

/**
 * @author lishuai
 * @Description:
 * @date 2023/6/29 17:05
 */
public class Char {

  public static void main(StringTest[] args) {
    // CirCulPrint(3);
    int[] nums = {1, 2, 4, 5, 4, 6};
    //  removeChar(nums,2);

    shortChar(nums, 10);
  }

  /**
   * 螺旋打印
   *
   * @param n
   * @return
   */
  public static int[][] CirCulPrint(int n) {
    int[][] printChars = new int[n][n];
    //游标,i横向 j纵向
    //打印最大值
    int count = 1;
    //循环次数
    int loop = 0;
    int i = 0, j;
    while (loop++ < n / 2) {
      //从左到右
      for (j = loop - 1; j < n - loop; j++) {
        printChars[i][j] = count++;
      }
      //从右到下
      for (i = loop - 1; i < n - loop; i++) {
        printChars[i][j] = count++;
      }
      //从右到左
      for (j = n - loop; j > loop - 1; j--) {
        printChars[i][j] = count++;
      }
      //从左到上
      for (i = n - loop; i > loop - 1; i--) {
        printChars[i][j] = count++;
      }

    }
    //中间位置单独处理
    //奇数中间一个空格
    if (n % 2 == 1) {
      printChars[loop - 1][loop - 1] = count++;
    }
    return printChars;


  }

  /**
   * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。 [1,2,3,2,]
   */
  public static int removeChar(int[] nums, int val) {
    int slow = 0;
    for (int fast = 0; fast < nums.length; fast++) {
      if (nums[fast] != val) {
        nums[slow++] = nums[fast];
      }
    }
    return slow;
  }

  /**
   * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。 {-3,1,2,4}
   */
  public static int[] square(int[] nums) {
    int length = nums.length;
    int left = 0;
    int right = length;
    int[] result = new int[length];
    while (left <= right) {
      if (nums[left] * nums[left] > nums[right] * nums[right]) {
        result[length--] = nums[left] * nums[left];
        //動態左右游標
        left++;
      } else {
        result[length--] = nums[right] * nums[right];
        right++;
      }
    }
    return result;
  }

  /**
   * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
   * <p>
   * 示例：
   * <p>
   * 输入：s = 7, nums = [2,3,1,2,4,3] 输出：2 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
   */
  public static int shortChar(int[] nums, int val) {
    int shortLength = nums.length;
    int slow = 0;
    int sum = 0;
    for (int fast = 0; fast < nums.length; fast++) {
      sum = sum + nums[fast];
      while (sum >= val) {
        int length = fast - slow + 1;
        shortLength = Math.min(length, shortLength);
        sum = sum - nums[slow];
        slow++;
      }
    }
    return shortLength;
  }

  /**
   * 双向指针移除元素
   *
   * @param nums
   * @param val
   * @return
   */
  public static int removeElement(int[] nums, int val) {
    int left = 0;
    int right = nums.length;
    while (right>=0&&nums[right--] != val) {
      break;
    }
    while (left < right) {
      if (nums[left++] == val) {
        nums[left] = nums[right];
      }
      while (right>=0&&nums[right--] != val) {
        break;
      }
    }
    return nums.length;
  }

}
