package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import study.Struct.TreeNode;

/**
 * @author lishuai
 * @Description:
 * @date 2023/7/24 13:53
 */
public class GreedyAlgorithm {

  /**
   * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。 对每个孩子 i，都有一个胃口值  g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，
   * 都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。 你的目标是尽可能满足越多数量的孩子，并输出这个最大数值
   */
  public int giveCookie(int[] g, int[] j) {
    Arrays.sort(g);
    Arrays.sort(j);
    int index = 0;
    //饼干
    for (int a = 0; a < j.length; a++) {
      if (j[a] >= g[index]) {
        index++;
      }
    }
    return index;
  }

  /**
   * [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3)  是正负交替出现的。 相反, [1,4,7,2,5]  和  [1,7,4,5,5] 不是摆动序列，
   * 第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。 给定一个整数序列，返回作为摆动序列的最长子序列的长度。
   * 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
   */
  public int getMaxBD(int[] nums) {
    if (nums.length <= 1) {
      return 1;
    }
    int result = 1;
    int preDiff = 0;
    int curDiff;
    for (int i = 0; i < nums.length; i++) {
      curDiff = nums[i + 1] - nums[i];
      if ((preDiff <= 0 && curDiff > 0) || (preDiff >= 0 && curDiff < 0)) {
        result++;
        preDiff = curDiff;
      }
    }
    return result;
  }

  public List<Integer> getMaxBDNums(Integer[] nums) {
    if (nums.length <= 1) {
      return Arrays.asList(nums);
    }
    List<Integer> resultList = new ArrayList<>();
    int preDiff = 0;
    int curDiff;
    for (int i = 0; i < nums.length; i++) {
      curDiff = nums[i + 1] - nums[i];
      if ((preDiff <= 0 && curDiff > 0) || preDiff >= 0 && curDiff < 0) {
        resultList.add(nums[i]);
        preDiff = curDiff;
      }
    }
    return resultList;
  }

  /**
   * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
   */
  public int getMaxSum(int[] nums) {
    int maxSum = 0;
    int sum = 0;
    for (int i = 1; i < nums.length; i++) {
      sum += nums[i];
      maxSum = Math.max(sum, maxSum);
      if (sum < 0) {
        sum = 0;
      }
    }
    return maxSum;
  }

  /**
   * 给定一个数组，它的第  i 个元素是一支给定股票第 i 天的价格。 [1,3,4,5,3,6] 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
   */
  private int calMaxProfit(int[] nums) {
    int maxProfit = 0;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] - nums[i - 1] > 0) {
        maxProfit += nums[i] - nums[i - 1];
      }
    }
    return maxProfit;
  }

  /**
   * 给定一个非负整数数组，你最初位于数组的第一个位置。
   * <p>
   * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
   * <p>
   * 判断你是否能够到达最后一个位置。
   */
  public boolean jumpArrive(int[] nums) {
    if (nums.length == 1) {
      return true;
    }
    int maxArrive = 0;
    for (int i = 0; i <= maxArrive; i++) {
      maxArrive = Math.max(nums[i] + i, maxArrive);
    }
    if (maxArrive >= nums.length) {
      return true;
    }
    return false;
  }

  public boolean skipArrive(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      int k = nums[i];
      while (k >= 0) {
        if (k == 0) {
          return false;
        }
        if (nums[i + nums[k]] > 0) {
          break;
        }
        k--;
      }
    }
    return true;
  }

  /**
   * 给定一个非负整数数组，你最初位于数组的第一个位置。 数组中的每个元素代表你在该位置可以跳跃的最大长度。 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
   */
  public int minJump(int[] nums) {
    if (nums.length == 1) {
      return 0;
    }
    int minCount = 0;
    int curDistance = 0;
    int nextDistance = 0;
    for (int i = 0; i < nums.length; i++) {
      //更新下一步最远距离
      nextDistance = Math.max(nums[i] + i, nextDistance);
      if (i == curDistance) {
        minCount++;
        curDistance = nextDistance;
        if (nextDistance > nums.length - 1) {
          break;
        }
      }
    }
    return minCount;
  }

  /**
   * 给定一个整数数组 A，我们只能用以下方法修改该数组：我们选择某个索引 i 并将 A[i] 替换为 -A[i]，然后总共重复这个过程 K 次。（我们可以多次选择同一个索引 i。）
   * 以这种方式修改数组后，返回数组可能的最大和。
   */
  public int maxSum(int[] nums, int k) {
    int sum = 0;
    //最小非负整数
    int minValue = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] < 0) {
        nums[i] = -nums[i];
        k--;
      } else {
        minValue = Math.min(minValue, nums[i]);
      }
      sum += nums[i];
      if (k > 0 && i == nums.length - 1) {
        k = k % 2;
        sum = sum - minValue;
      }
    }
    return sum;
  }

  /**
   * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i]
   * 升。你从其中的一个加油站出发，开始时油箱为空。 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
   */
  public int getGasIndex(int[] gas, int[] cost) {
    int maxRemain = 0;
    int maxRemainIndex = 0;
    int sumRemain = 0;
    for (int i = 0; i < gas.length; i++) {
      int curRemain = gas[i] - cost[i];
      if (curRemain > maxRemain) {
        maxRemain = curRemain;
        maxRemainIndex = i;
      }
      sumRemain += gas[i] - cost[i];
    }
    if (sumRemain < 0) {
      return -1;
    }
    return maxRemainIndex;
  }

  /**
   * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。 你需要按照以下要求，帮助老师给这些孩子分发糖果： 每个孩子至少分配到 1 个糖果。
   * 相邻的孩子中，评分高的孩子必须获得更多的糖果。 那么这样下来，老师至少需要准备多少颗糖果呢？ [4,3,2,5,4,3,2] [2,3,4,5,4,3,2]
   */

  //针对左右维度，先保证左维度，再用左维度的数据去处理右维度
  public int getMinLeftRightCandy(int[] nums) {
    int[] candyNums = new int[nums.length];
    Arrays.fill(candyNums, 1);
    for (int i = 0; i < nums.length; i++) {
      if (nums[i + 1] > nums[i]) {
        candyNums[i + 1] = candyNums[i] + 1;
      }
    }
    int sum = nums[nums.length - 1];
    for (int i = nums.length - 1; i > 0; i--) {
      if (nums[i] < nums[i - 1]) {
        candyNums[i - 1] = candyNums[i] + 1;
        sum += candyNums[i - 1];
      }
    }
    return sum;
  }

  /**
   * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好
   * 有 ki 个身高大于或等于 hi 的人。 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ， 其中 queue[j] = [hj, kj]
   * 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
   * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
   */
  public int[][] getHeightQueue(int[][] people) {
    //先排序身高降序k值升序
    Arrays.sort(people, (a, b) -> {
      if (a[0] == b[0]) {
        return b[1] - a[1];
      }
      return a[0] - b[0];
    });
    LinkedList<int[]> queue = new LinkedList<>();
    //根据k作为index
    for (int[] p : people) {
      queue.add(p[1], p);
    }
    return queue.toArray(new int[people.length][]);
  }

  /**
   * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。 输入：points =
   * [[10,16],[2,8],[1,6],[7,12]] 输出：2 **** ***** ******* [[3,9],[7,12],[3,8],[6,8],[9,10],[2,9],[0,9],[3,9],[0,6],[2,8]]
   * 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球 [[-2147483646,-2147483645],[2147483646,2147483647]]
   */
  public static int getMinBalloon(int[][] points) {
    Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
    int count = 1;  // points 不为空至少需要一支箭
    for (int i = 1; i < points.length; i++) {
      if (points[i][0] > points[i - 1][1]) {  // 气球i和气球i-1不挨着，注意这里不是>=
        count++; // 需要一支箭
      } else {  // 气球i和气球i-1挨着
        points[i][1] = Math.min(points[i][1], points[i - 1][1]); // 更新重叠气球最小右边界
      }
    }
    return count;
  }

  /**
   * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
   * <p>
   * 注意: 可以认为区间的终点总是大于它的起点。 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
   * <p>
   * 示例 1:
   * <p>
   * 输入: [ [1,2], [2,3], [3,4], [1,3] ] 输出: 1 解释: 移除 [1,3] 后，剩下的区间没有重叠。
   */

  public int overLap(int[][] nums) {
    Arrays.sort(nums, Comparator.comparingInt(a -> a[0]));
    int count = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i - 1][1] < nums[i][0]) {
        count++;
      } else {
        nums[i][1] = Math.min(nums[i][1], nums[i - 1][1]);
      }
    }
    return nums.length - count;
  }

  /**
   * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。 输入：S =
   * "ababcbacadefegdehijhklij" 输出：[9,7,8] 解释： 划分结果为 "ababcbaca", "defegde", "hijhklij"。
   * 每个字母最多出现在一个片段中。 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。 提示： S的长度在[1, 500]之间。
   * S只包含小写字母 'a' 到 'z'
   *
   * @param s
   */
  public List<Integer> splitMax(String s) {
    //最远距离
    int[] letterMaxIndex = new int[]{};
    for (int i = 0; i < s.length(); i++) {
      letterMaxIndex[s.charAt(i) - 'a'] = i;
    }
    List<Integer> splitNums = new ArrayList<>();
    int last = 0;
    int idx = 0;
    for (int i = 0; i < s.length(); i++) {
      //最大距离
      idx = Math.max(idx, letterMaxIndex[s.charAt(i) - 'a']);
      if (idx == i) {
        splitNums.add(i - last);
        last = i;
      }
    }
    return splitNums;
  }

  /**
   * 给出一个区间的集合，请合并所有重叠的区间。 输入: intervals = [[1,3],[2,6],[8,10],[15,18]] 输出: [[1,6],[8,10],[15,18]]
   * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
   */
  public List<int[]> getCombinationRegion(int[][] nums) {
    Arrays.sort(nums, Comparator.comparingInt(a -> a[0]));
    List<int[]> result = new ArrayList<>();
    int start = nums[0][0];
    int end = nums[0][1];
    for (int i = 1; i < nums.length; i++) {
      if (nums[i][0] < nums[i - 1][1]) {
        end = Math.max(nums[i][1], end);
      } else {
        result.add(new int[]{start, end});
        start = nums[i][0];
        end = nums[i][1];
      }
    }
    result.add(new int[]{start, end});
    return result;
  }

  /**
   * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。 （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y
   * 时，我们称这个整数是单调递增的。） 示例 1: 输入: N = 10 输出: 9 示例 2: 输入: N = 1234 输出: 1234 343 299 1343 1299
   */

  public static int getMaxIncreaseNumber(int n) {
    String[] stringN = String.valueOf(n).split("");
    int start =0;
    for (int i = stringN.length-1; i > 0; i--) {
      if (Integer.parseInt(stringN[i - 1]) > Integer.parseInt(stringN[i])) {
        stringN[i - 1] = String.valueOf(Integer.parseInt(stringN[i - 1]) - 1);
        start =i;
      }
    }
    for (int i = start; i <stringN.length; i++) {
        stringN[i] ="9";
    }
    int result = Integer.parseInt(String.join("", stringN));
    return result;
  }

  /**
   * 给定一个二叉树，我们在树的节点上安装摄像头。
   * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
   * 计算监控树的所有节点所需的最小摄像头数量。
   * @param root
   * 后续遍历
   * 隔两个节点放置
   *
   */
  int monitorCount =0;
  public int calMinMonitor(TreeNode root){
    if(minMonitor(root,monitorCount)==0){
      monitorCount++;
    }
    return monitorCount;
  }

    private int minMonitor(TreeNode root,int count){
      //节点值 0无覆盖 1覆盖 2 有摄像头
      //叶子节点左右节点设置覆盖，避免叶子节点设置头
      if(root==null){
        return 1;
      }
       int left = minMonitor(root.getLeft(),count);
       int right = minMonitor(root.getRight(),count);
       //左右节点一个为未覆盖,则设置摄像头
       if(left==0||right==0){
         count++;
         return 2;
         //左右为覆盖则设置非覆盖
       }else if(left==1&&right==1){
         return 0;
       }else {
         //左右节点为摄像头则设置为覆盖
         return 1;
       }
    }




  public static void main(String[] args) {
    getMaxIncreaseNumber(855832);
  }

}
