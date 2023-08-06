package study;

import java.util.Arrays;
import study.Struct.TreeNode;

/**
 * @author lishuai
 * @Description:
 * @date 2023/7/27 10:19
 */
public class DynamicProgram {

  /**
   * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 注意：给定 n 是一个正整数。
   */
  public int jumpFloor(int n) {
    if (n <= 1) {
      return 1;
    }
    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
      dp[i] = dp[i - 1] + dp[i - 2];
    }
    return dp[n];
  }

  /**
   * 空间优化
   */
  public int optimizeJumpFloor(int n) {
    if (n <= 1) {
      return 1;
    }
    int[] dp = new int[3];
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
      dp[2] = dp[0] + dp[1];
      dp[0] = dp[1];
      dp[1] = dp[2];
    }
    return dp[2];
  }

  /**
   * 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。
   * <p>
   * 每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。
   * <p>
   * 请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。 输入：cost = [10, 15, 20] 输出：15 解释：最低花费是从 cost[1]
   * 开始，然后走两步即可到阶梯顶，一共花费 15 。
   */
  public int minCostFloor(int[] cost) {
    int[] dp = new int[cost.length + 1];
    dp[0] = 0;
    dp[1] = 0;
    for (int i = 2; i <= cost.length; i++) {
      dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
    }
    return dp[cost.length];
  }

  public int optimizeMinCostFloor(int[] cost) {
    int[] dps = new int[3];
    for (int i = 2; i <= cost.length; i++) {
      dps[2] = Math.min(dps[0] + cost[i - 2], dps[1] + cost[i - 1]);
      dps[0] = dps[1];
      dps[1] = dps[2];
    }
    return dps[cost.length];
  }

  /**
   * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
   * <p>
   * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
   * <p>
   * 问总共有多少条不同的路径？ for (int i = 0; i < n; i++) dp[i] = 1; for (int j = 1; j < m; j++) { for (int i =
   * 1; i < n; i++) { dp[i] += dp[i - 1]; } } return dp[n - 1];
   */
  public int calMostPath(int m, int n) {
    int[][] dp = new int[m][n];

    for (int i = 0; i < m; i++) {
      dp[i][0] = 1;
    }
    for (int j = 0; j < n; j++) {
      dp[0][j] = 1;
    }
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
      }
    }
    return dp[m - 1][n - 1];
  }

  /**
   * 网格障碍物
   */
  public int calRobotMostPath(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    if (grid[0][0] == 1 || grid[m][n] == 1) {
      return 0;
    }
    int[][] dp = new int[m][n];
    for (int i = 0; i < m && grid[i][0] == 0; i++) {
      dp[i][0] = 1;
    }
    for (int j = 0; j < n && grid[0][j] == 0; j++) {
      dp[0][j] = 1;
    }
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {
        if (grid[i][j] == 0) {
          dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        } else {
          dp[i][j] = 0;
        }
      }
    }
    return dp[m - 1][n - 1];
  }

  /**
   * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
   */
  public int mostProduct(int n) {
    int[] dp = new int[n + 1];
    dp[2] = 1;
    for (int i = 3; i < n; i++) {
      for (int j = 1; j < i - j; j++) {
        dp[i] = Math.max(j * (i - j), j * dp[i - j]);
      }
    }
    return dp[n];
  }

  /**
   * 背包一维数组
   */
  public static int basicPackageWeight(int[] weight, int[] value, int packageWeight) {
    int[] dp = new int[packageWeight + 1];

    for (int i = 0; i < weight.length; i++) {
      for (int j = packageWeight; j >= weight[i]; j--) {
        dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
      }
    }
    return dp[weight.length];
  }

  /**
   * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
   * <p>
   * 注意: 每个数组中的元素不会超过 100 数组的大小不会超过 200
   *
   * @param
   */
  public boolean getEqualNums(int[] nums) {
    int sum = Arrays.stream(nums).sum();
    int target = sum / 2;
    int[] dp = new int[target + 1];
    for (int j = 0; j < nums.length; j++) {
      for (int i = target; i >= nums[i]; i--) {
        dp[i] = Math.max(dp[i], dp[i - nums[i]] + nums[i]);
      }
    }
    if (dp[target] == target) {
      return true;
    }
    return false;
  }

  /**
   * 有一堆石头，每块石头的重量都是正整数。
   * <p>
   * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
   * <p>
   * 如果 x == y，那么两块石头都会被完全粉碎；
   * <p>
   * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
   * <p>
   * 最后，最多只会剩下一块石头。返回此石头最小的可能重量。如果没有石头剩下，就返回 0。
   */
  public int minStockWeight(int[] weight) {
    int sum = Arrays.stream(weight).sum();
    int target = sum / 2;
    int[] dp = new int[target + 1];
    for (int j = 0; j < weight.length; j++) {
      for (int i = target; i >= weight[i]; i--) {
        dp[i] = Math.max(dp[i], dp[i - weight[i]] + weight[i]);
      }
    }
    return sum - 2 * dp[target];
  }

  /**
   * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。 对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
   * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数
   */
  public int targetSumCount(int[] nums, int target) {
    //add =target +sub  add+sub =sum
    //add =target +(sum-add)
    //add =(target+sum)/2
    int sum = Arrays.stream(nums).sum();
    if (target > sum) {
      return 0;
    }
    //奇数
    if ((sum + target) % 2 == 1) {
      return 0;
    }
    //相加部分的和
    int add = (sum + target) / 2;
    int[] dp = new int[add + 1];
    for (int i = 0; i < nums.length; i++) {
      for (int j = add; j >= nums[i]; j--) {
        //求组合？
        dp[j] += dp[j - nums[i]];
      }
    }
    return dp[add];
  }

  /**
   * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。 如果 x 的所有元素也是 y
   * 的元素，集合 x 是集合 y 的 子集 。 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3 输出：4
   */
  public int maxChildCount(String[] strings, int m, int n) {
    int[][] dp = new int[m + 1][n + 1];
    for (int i = 0; i < strings.length; i++) {
      //统计0和1的数量
      int zeroNum = 0;
      int oneNum = 0;
      for (char c : toString().toCharArray()) {
        if ('1' == c) {
          oneNum++;
        } else if ('0' == c) {
          zeroNum++;
        }
      }
      for (int a = m; a > 0; a--) {
        for (int b = n; b > 0; b--) {
          dp[a][b] = Math.max(dp[a][b], dp[a - zeroNum][b - oneNum] + 1);
        }
      }
    }
    return dp[m][n];
  }

  /**
   * 完全背包--不限制物品数量
   */
  public int completePackage(int[] weight, int[] value, int packages) {
    int[] dp = new int[packages + 1];
    for (int w = 0; w < weight.length; w++) {
      for (int p = weight[w]; w <= packages; p++) {
        dp[p] = Math.max(dp[p], dp[p - weight[w]] + value[w]);
      }
    }
    return dp[packages];
  }

  /**
   * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 输入: amount = 5, coins = [1, 2, 5] 输出: 4
   * 先遍历物品在遍历背包
   */
  public static int coinPackage(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    dp[0] = 1;
    for (int w = 0; w < coins.length; w++) {
      for (int p = coins[w]; p <= amount; p++) {
        dp[p] += dp[p - coins[w]];
      }
    }
    return dp[amount];
  }

  /**
   * 求硬币排列数 -先遍历背包再遍历物品
   *
   * @param coins
   * @param amount
   * @return 1 2 1 2 1 1 1 1 2
   */
  public static int coinPackageArrange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    dp[0] = 1;
    for (int j = 1; j <= amount; j++) {
      for (int i = 0; i <= coins.length; i++) {
        if ((j - coins[i]) >= 0) {
          dp[j] += dp[j - coins[i]];
        }
      }
    }
    return dp[amount];
  }

  /**
   * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
   * <p>
   * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量
   */
  public static int getMinSquare(int n) {
    int[] dp = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 1; i * i <= n; i++) {
      for (int j = i * i; j <= n; j++) {
        dp[j] = Math.min(dp[j - i * i] + 1, dp[j]);
      }
    }
    return dp[n];
  }

  /**
   * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
   * <p>
   * 说明：
   * <p>
   * 拆分时可以重复使用字典中的单词。
   * <p>
   * 你可以假设字典中没有重复的单词。
   * <p>
   * 示例 1：
   * <p>
   * 输入: s = "leetcode", wordDict = ["leet", "code"] 输出: true 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet
   * code"
   */
  public static boolean checkWord(String s, String[] words) {
    boolean[] dp = new boolean[s.length() + 1];
    dp[0] = true;
    for (int i = 0; i <= s.length(); i++) {
      for (int j = 0; j < words.length; j++) {
        String word = words[j];
        if ((i - word.length()) >= 0
            && s.substring(i - word.length(), i).equals(word)
            && dp[i - word.length()]) {
          dp[i] = true;
        }
      }
    }
    return dp[s.length()];
  }

  /**
   * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。 示例 1： 输入：[1,2,3,1] 输出：4
   */
  public static int stealMaxMoney(int[] money) {
    int[] dp = new int[money.length];
    dp[0] = money[0];
    dp[1] = Math.max(money[0], money[1]);
    for (int i = 2; i < money.length; i++) {
      dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
    }
    return dp[money.length - 1];
  }

  /**
   * 环形住宅
   */
  public int stealCycleMaxMoney(int[] money) {
    int leftValue = dfsStealCycleMaxMoney(money, 0);
    int rightValue = dfsStealCycleMaxMoney(money, 1);
    return Math.max(leftValue, rightValue);
  }

  private int dfsStealCycleMaxMoney(int[] money, int start) {
    int[] dp = new int[money.length - 1];
    dp[start] = money[start];
    dp[start + 1] = Math.max(money[start], money[start + 1]);
    for (int i = start; i < money.length - 1 + start; i++) {
      dp[i] = Math.max(dp[i - 1], dp[i - 2] + money[i]);
    }
    return dp[money.length - 1];
  }

  /**
   * 二叉樹住宅
   */
  public int treeMaxMoney(TreeNode root) {
    int[] result = dfsTreeMaxMoney(root);
    return Math.max(result[0], result[1]);
  }

  private int[] dfsTreeMaxMoney(TreeNode root) {
    //0不偷  1偷
    int[] dp = new int[2];
    if (root == null) {
      return dp;
    }
    //不偷，偷左右結點
    int[] leftResult = dfsTreeMaxMoney(root.getLeft());
    int[] rightResult = dfsTreeMaxMoney(root.getRight());
    dp[0] = Math.max(leftResult[0], leftResult[1]) + Math.max(rightResult[0], rightResult[1]);
    //偷  左右節點不偷
    dp[1] = root.getVal() + leftResult[0] + rightResult[0];
    return dp;
  }

  /**
   * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子
   * 卖出该股票。设计一个算法来计算你所能获取的最大利润。 [3,4,5,6]
   */
  public int maxProfit(int[] prices) {
    //dp[i][0] 持有股票最大现金 dp[i][1] 不持有股票最大现金
    int[][] dp = new int[prices.length][];
    dp[0][0] = -prices[0];
    dp[0][1] = 0;
    for (int i = 1; i < prices.length; i++) {
      dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
      dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
    }
    return dp[prices.length - 1][1];
  }

  /**
   * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
   * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 [2,4,7,9,5]
   */
  public int maxManyProfit(int[] prices) {
    //dp[i][0] 持有股票最大现金 dp[i][1] 不持有股票最大现金
    int[][] dp = new int[prices.length][];
    dp[0][0] = -prices[0];
    dp[0][1] = 0;
    for (int i = 1; i < prices.length; i++) {
      dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
      dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
    }
    return dp[prices.length - 1][1];
  }

  /**
   * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
   * <p>
   * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
   * <p>
   * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
   */
  public int maxTwoProfit(int[] prices) {
    //dp[i][0] 没有操作 dp[i][1] 持有第一次股票最大现金 dp[i][2] 不持有第一次股票最大现金  dp[i][3] 持有第二次股票最大现金 dp[i][4] 不持有第三次股票最大现金
    int[][] dp = new int[prices.length][];
    dp[0][1] = -prices[0];
    for (int i = 1; i < prices.length; i++) {
      dp[i][0] = dp[i - 1][0];
      dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
      dp[i][2] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][2]);
      dp[i][3] = Math.max(dp[i - 1][2] - prices[i], dp[i - 1][3]);
      dp[i][4] = Math.max(dp[i - 1][3] + prices[i], dp[i - 1][4]);
    }
    return dp[prices.length - 1][4];
  }

  /**
   * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
   *
   * @param args
   */
  public int maxKProfit(int[] prices, int k) {
    //dp[i][0] 没有操作 dp[i][1] 持有第一次股票最大现金 dp[i][2] 不持有第一次股票最大现金  dp[i][3] 持有第二次股票最大现金 dp[i][4] 不持有第三次股票最大现金
    int[][] dp = new int[prices.length][2 * k + 1];
    dp[0][1] = -prices[0];
    for (int i = 1; i < prices.length; i++) {
      dp[i][0] = dp[i - 1][0];
      for (int j = 2; j <= 2 * k; j = j + 2) {
        dp[i][j - 1] = Math.max(dp[i - 1][j - 2] - prices[i], dp[i - 1][j - 1]);
        dp[i][j] = Math.max(dp[i - 1][j - 1] + prices[i], dp[i - 1][j]);
      }
    }
    return dp[prices.length - 1][2 * k];
  }

  /**
   * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
   * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
   */
  public int maxManyFreezeProfit(int[] prices) {
    //dp[i][0] 持有股票最大现金 dp[i][1] 不持有股票最大现金
    int[][] dp = new int[prices.length][];
    dp[1][0] = -prices[0];
    dp[0][0] = -prices[0];
    for (int i = 2; i < prices.length; i++) {
      dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][1] - prices[i]);
      dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
    }
    return dp[prices.length - 1][1];
  }

  /**
   * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
   * 返回获得利润的最大值。
   */
  public int maxManyFeeProfit(int[] prices, int fee) {
    //dp[i][0] 持有股票最大现金 dp[i][1] 不持有股票最大现金
    int[][] dp = new int[prices.length][];
    dp[0][0] = -prices[0];
    dp[0][1] = 0;
    for (int i = 1; i < prices.length; i++) {
      dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
      dp[i][1] = Math.max(dp[i - 1][0] + prices[i] - fee, dp[i - 1][1]);
    }
    return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][1]);
  }

  /**
   * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
   * <p>
   * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
   * <p>
   * 示例 1：
   * <p>
   * 输入：nums = [10,9,2,5,3,7,101,18] 输出：4
   */
//  public int maxIncreaseLength(int[]nums){
//    int[] dp =new int[nums.length];
//    int curMax =nums[0];
//    for(int i=1;i<nums.length;i++){
//       if(nums[i]>curMax) {
//         dp[i] = dp[i - 1] + 1;
//         curMax = nums[i];
//       }else {
//         dp[i] = dp[i - 1];
//       }
//    }
//    return dp[nums.length];
//  }
  //[4,10,4,3,8,9]
  public int maxIncreaseLength1(int[] nums) {
    int[] dp = new int[nums.length];
    Arrays.fill(dp, 1);
    int result = 0;
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      if (dp[i] >= result) {
        result = dp[i];
      }
    }
    return result;
  }

  /**
   * 给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定， 如果对于每个 l <= i < r，都有
   * nums[i] < nums[i + 1] ， 那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
   */
  public int maxIncreaseLength(int[] nums) {
    int[] dp = new int[nums.length];
    Arrays.fill(nums,1);
    int result = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > nums[i-1]) {
        dp[i] = dp[i - 1] + 1;
      }
      if (dp[i] > result) {
        result = dp[i];
      }
    }
    return result;
  }

  /**
   * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
   * A: [1,2,3,2,1]
   * B: [3,2,1,4,7]
   * 输出：3
   * 解释：长度最长的公共子数组是 [3, 2, 1] 。
   */
   public int diffNums(int[]a,int[]b){
     int[][] dp = new int[a.length][b.length];
     int result =0;
     for(int i=1;i<=a.length;i++){
       for(int j=1;j<=b.length;j++){
         if(a[i-1]==b[j-1]){
           dp[i][j] = dp[i-1][j-1]+1;
         }
         if(dp[i][j]>result){
           result =dp[i][j];
         }
       }
     }
    return  result;
   }

  /**
   * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
   *
   * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
   *
   * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
   *
   * 若这两个字符串没有公共子序列，则返回 0。
   *
   * 示例 1:
   *
   * 输入：text1 = "abcde", text2 = "ace"
   * 输出：3
   * 解释：最长公共子序列是 "ace"，它的长度为 3。
   * @param args
   */
  public int  maxCommonLength(String a,String b){
    int aLength = a.length();
    int bLength = b.length();
    int[][] dp = new int[aLength][bLength];
    for(int i=1;i<=aLength;i++) {
      for (int j = 1; j <= bLength; j++) {
        if(a.charAt(i-1)==b.charAt(j-1)){
          dp[i][j] = dp[i-1][j-1]+1;
        }else{
          dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
        }
      }
    }
    return dp[aLength-1][bLength-1];
  }

  /**
   * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
   * 输入: [-2,1,-3,4,-1,2,1,-5,4]
   * 输出: 6
   * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
   */
  public static int maxSum(int[] nums){
    int result =0;
    int [] dp = new int[nums.length];
    dp[0] = nums[0];
     for(int i=1;i<nums.length;i++) {
       dp[i] = Math.max(nums[i], dp[i-1]+nums[i]);
      if(dp[i] >result){
        result =dp[i];
      }
    }
    return result;
  }

  /**
   * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
   *
   * 字符串的一个子序列是原始字符串删除一些（也可以不删除）
   * 字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
   *
   * 示例 1：
   *
   * 输入：s = "abc", t = "ahbgdc"
   * 输出：true
   * @param args
   */
   public boolean isChildText(String s,String t){
     int aLength = s.length();
     int bLength = t.length();
     int[][] dp = new int[aLength+1][bLength+1];
     for(int i=1;i<=aLength;i++) {
       for (int j = 1; j <= bLength; j++) {
         if(s.charAt(i-1)==t.charAt(j-1)){
           dp[i][j] = dp[i-1][j-1]+1;
         }else{
           //子串不可回退
           dp[i][j] =dp[i][j-1];
         }
       }
     }
     return dp[aLength][bLength]==aLength;
   }

  /**
   * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
   * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
   * （例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
   */
  public static int maxChildCount(String s,String t){
    int aLength = s.length();
    int bLength = t.length();
    int[][] dp = new int[aLength+1][bLength+1];
    //初始化出现的次数
    for(int i=0;i<=aLength;i++) {
      dp[i][0] =1;
    }
    for(int j=1;j<=bLength;j++) {
      dp[0][j] =0;
    }
    for(int i=1;i<=aLength;i++) {
      for (int j = 1; j <= bLength; j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          //尾值相等 ,用s.charAt(i - 1)匹配，以及用之前的值匹配
          dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
        } else {
          dp[i][j] = dp[i - 1][j];
        }
      }
    }
    return dp[aLength][bLength];
  }

  /**
   * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
   *
   * 示例：
   *
   * 输入: "sea", "eat"
   * 输出: 2
   * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
   * 删除时机
   */
    public static int minEqualStep(String a,String b){
      int aLength = a.length();
      int bLength = b.length();
      int[][] dp = new int[aLength+1][bLength+1];
      //初始化出现的次数
      for(int i=0;i<=aLength;i++) {
        dp[i][0] =i;
      }
      for(int j=1;j<=bLength;j++) {
        dp[0][j] =j;
      }
      for(int i=1;i<=aLength;i++) {
        for (int j = 1; j <= bLength; j++) {
          if(a.charAt(i-1)==b.charAt(j-1)){
            dp[i][j] = dp[i-1][j-1];
          }else{
            dp[i][j] = Math.min(dp[i][j-1],dp[i-1][j])+1;
          }
        }
      }
      return dp[aLength][bLength];
    }

  /**
   * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
   *
   * 你可以对一个单词进行如下三种操作：
   *
   * 插入一个字符
   *
   * 删除一个字符
   *
   * 替换一个字符
   * word1 = "rose", word2 = "ros"
   */
  public static int minEqualUpdateStep(String a,String b){
    int aLength = a.length();
    int bLength = b.length();
    int[][] dp = new int[aLength+1][bLength+1];
    //初始化出现的次数
    for(int i=0;i<=aLength;i++) {
      dp[i][0] =i;
    }
    for(int j=1;j<=bLength;j++) {
      dp[0][j] =j;
    }
    for(int i=1;i<=aLength;i++) {
      for (int j = 1; j <= bLength; j++) {
        if(a.charAt(i-1)==b.charAt(j-1)){
          dp[i][j] = dp[i-1][j-1];
        }else{
          dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
        }
      }
    }
    return dp[aLength][bLength];
  }

  /**
   * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
   * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
   * 示例 1：
   *
   * 输入："abc"
   * 输出：3
   * 解释：三个回文子串: "a", "b", "c"
   */
  public int countHwText(String a){
    boolean[][] hw = new boolean[a.length()][a.length()];
    int count =0;
    for(int i=a.length()-1;i>=0;i--){
      for(int j=i;j<=a.length()-1;j++){
        if(a.charAt(i)==a.charAt(j)){
           if(j-i<=1){
             count++;
             hw[i][j] =true;
           }else if(hw[i+1][j-1]){
             count++;
             hw[i][j] =true;
           }
        }
      }
    }
    return count;
  }

  /**
   * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
   * 示例 1: 输入: "bbbab" 输出: 4 一个可能的最长回文子序列为 "bbbb"。
   * 示例 2: 输入:"cbbd" 输出: 2 一个可能的最长回文子序列为 "bb"。
   */
   public int maxLengthHw(String a){
     int[][] dp = new int[a.length()][a.length()];
     for(int i=a.length()-1;i>=0;i--){
       for(int j=i+1;j<=a.length()-1;j++){
         if(a.charAt(i)==a.charAt(j)){
           dp[i][j] =dp[i-1][j-1]+2;
           }else {
           dp[i][j] =Math.max(dp[i-1][j],dp[i][j-1]);
         }
       }
     }
     return dp[0][a.length()-1];
   }






  public static void main(String[] args) {
//    int[] weight = {1, 3, 4};
//    int[] value = {15, 20, 30};
//    int bagWight = 4;
//    basicPackageWeight(weight, value, bagWight);
//    int[] coins = {1, 2, 5};
//    coinPackage(coins,5);
//    getMinSquare(12);
//    String[] words = {"leet", "code", "ab", "abee"};
//    checkWord("leetababcode", words);
    //int[] money = {1, 2, 3, 1};
    //stealMaxMoney(money);
//    int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
//    maxSum(nums);
   // maxChildCount("babgbag","bag");
    minEqualStep("sea","eat");
  }


}
