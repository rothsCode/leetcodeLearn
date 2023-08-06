package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author lishuai
 * @Description:回溯處理
 * @date 2023/7/14 9:48
 */
public class BackTrackingTest {

  public static void main(String[] args) {
    List<Set<Integer>> combinations = new ArrayList<>();
    Set<Integer> nums = new HashSet<>();
    searchSumCombination(7, 3, combinations, nums, 1);
    int i = 0;
  }

  /**
   * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 示例: 输入: n = 4, k = 2 输出: [ [2,4], [3,4], [2,3], [1,2],
   * [1,3], [1,4], ]
   */
  public void searchCombination(Integer n, Integer k, List<List<Integer>> combinations,
      List<Integer> nums, Integer startIndex) {
    for (int i = startIndex; i < n + 1 - (k - nums.size()); i++) {
      if (nums.size() == k) {
        combinations.add(nums);
        return;
      }
      nums.add(i);
      searchCombination(n, k, combinations, nums, startIndex + 1);
      //回溯
      nums.remove(nums.size() - 1);
    }
  }

  /**
   * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。 所有数字都是正整数。 解集不能包含重复的组合。 示例 1: 输入: k
   * = 3, n = 7 输出: [[1,2,4]] 示例 2: 输入: k = 3, n = 9 输出: [[1,2,6], [1,3,5], [2,3,4]]
   */
  public static void searchSumCombination(Integer targetSum, Integer k,
      List<Set<Integer>> combinations, Set<Integer> nums, Integer startIndex) {

    for (int i = startIndex; i < 10 - (k - nums.size()); i++) {
      if (targetSum < 0) {
        return;
      }
      if (targetSum == 0 && nums.size() == k) {
        combinations.add(new HashSet<>(nums));
        return;
      }
      nums.add(i);
      targetSum -= i;
      searchSumCombination(targetSum, k, combinations, nums, startIndex + 1);
      //回溯
      nums.remove(i);
      targetSum += i;
    }
  }

  /**
   * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
   * <p>
   * candidates 中的每个数字在每个组合中只能使用一次。
   * <p>
   * 说明： 所有数字（包括目标数）都是正整数。解集不能包含重复的组合。
   */

  public static void searchCandidatesCombination(Integer targetSum, List<Integer> candidates,
      boolean[] booleansCandidates, List<Set<Integer>> combinations, List<Integer> nums,
      Integer startIndex) {

    for (int i = startIndex; i < candidates.size(); i++) {
      if (targetSum < 0) {
        return;
      }
      if (targetSum == 0) {
        combinations.add(new HashSet<>(nums));
        return;
      }
      //对树层重复跳过
      if (i > 0 && !booleansCandidates[i - 1] && candidates.get(i - 1).equals(candidates.get(i))) {
        continue;
      }
      nums.add(i);
      targetSum -= i;
      //重复标识
      booleansCandidates[i] = true;
      searchCandidatesCombination(targetSum, candidates, booleansCandidates, combinations, nums,
          startIndex + 1);
      //回溯
      nums.remove(i);
      booleansCandidates[i] = false;
      targetSum += i;
    }
  }

  /**
   * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。 返回 s 所有可能的分割方案。 示例: 输入: "aab" 输出: [ ["aa","b"], ["a","a","b"]
   * ] 回文串判断 如何分割子串
   */
  public void splitHwText(String s, List<List<String>> combinations, List<String> path,
      int startIndex) {
    for (int i = startIndex; i < s.length(); i++) {
      if (i >= s.length() - 1 && path.size() > 0) {
        combinations.add(path);
      }

      //判断回文-对称
      String t = null;
      if (validHwText(s, startIndex, i + 1)) {
        t = s.substring(startIndex, i + 1);
        path.add(t);
      } else {
        continue;
      }
      splitHwText(s, combinations, path, startIndex + 1);
      //回溯
      path.remove(t);
    }
  }

  private boolean validHwText(String s, int startIndex, int end) {
    for (int l = startIndex, r = end; l < r; l++, r--) {
      if (s.charAt(l) != s.charAt(r)) {
        return false;
      }
    }
    return true;
  }

  /**
   * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。 示例: 输入: [4, 6, 7, 7] 输出: [[4, 6], [4, 7], [4, 6, 7],
   * [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
   */
  public void searchIncreaseCombinations(int[] nums, List<List<Integer>> combinations,
      List<Integer> path, int startIndex, boolean[] bNums) {
    for (int i = startIndex; i < nums.length; i++) {
      //终止条件
      if (path.size() > 1) {
        combinations.add(path);
      }
      //去重
      if (nums[i] == nums[i - 1] && !bNums[i - 1]) {
        continue;
      }
      if (nums[i] >= nums[i - 1]) {
        path.add(nums[i]);
        bNums[i] = true;
      }
      searchIncreaseCombinations(nums, combinations, path, startIndex + 1, bNums);
      path.remove(nums[i]);
      bNums[i] = false;
    }
  }

  /**
   * 给定一个没有重复数字的序列，返回其所有可能的全排列。 示例: 输入: [1,2,3] 输出: [ [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2],
   * [3,2,1] ]
   */
  public void allCombinations(int[] nums, List<List<Integer>> combinations, List<Integer> path,
      List<Integer> tempNums) {
    for (int i = 0; i < nums.length; i++) {
      if (path.size() == nums.length) {
        combinations.add(path);
      }
      path.add(nums[i]);
      tempNums.remove(nums[i]);
      allCombinations(nums, combinations, path, tempNums);
      //回溯
      path.remove(nums[i]);
      tempNums.add(nums[i]);
    }
  }

  /**
   * 输入：[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
   * 输出：["JFK","ATL","JFK","SFO","ATL","SFO"] 解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
   */

  public boolean findAirLine(List<List<String>> tickets, LinkedList<String> airLines,
      LinkedList<String> result) {
    //先对行程按照自然排序
    Collections.sort(tickets, Comparator.comparing(a -> a.get(1)));
    //每张机票是否使用
    boolean[] usedTickets = new boolean[tickets.size()];
    airLines.add("JFK");
    if (airLines.size() == tickets.size()) {
      result = new LinkedList<>(airLines);
      return true;
    }
    for (int i = 0; i < tickets.size(); i++) {
      if (!usedTickets[i] && airLines.getLast().equals(tickets.get(i).get(0))) {
        airLines.add(tickets.get(i).get(1));
        usedTickets[i] = true;
      }
      findAirLine(tickets, airLines, result);
      airLines.removeLast();
      usedTickets[i] = false;
    }
    return false;
  }

  /**
   * 输入：n = 4 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]] 解释：如上图所示，4
   * 皇后问题存在两个不同的解法。
   */
  public void findQueen(int n, List<String> path, List<List<String>> combinations) {
    char[][] chessboard = new char[n][n];
    for (char[] chars : chessboard) {
      Arrays.fill(chars, '.');
    }
    backFindQueen(n, chessboard, path, combinations, 0);
  }

  public void backFindQueen(int n, char[][] chessboard, List<String> path,
      List<List<String>> combinations, int startIndex) {
    if (path.size() == n) {
      combinations.add(new ArrayList<>(path));
      return;
    }
    //行遍历
    for (int i = 0; i <= n; i++) {
      if (validChessboard(chessboard, startIndex, n)) {
        chessboard[startIndex][i] = 'Q';
        path.add(String.valueOf(chessboard[startIndex]));
        backFindQueen(n, chessboard, path, combinations, startIndex + 1);
        chessboard[startIndex][i] = '.';
      }
    }
  }

  private boolean validChessboard(char[][] chessboard, int startIndex, int n) {
    //横行校验
    for (int i = 0; i < n; i++) {
      if (chessboard[startIndex][i] == 'Q') {
        return false;
      }
    }
    // 检查45度对角线
    for (int i = startIndex - 1, j = n - 1; i >= 0 && j >= 0; i--, j--) {
      if (chessboard[i][j] == 'Q') {
        return false;
      }
    }
    return false;
  }

  /**
   * 一个数独的解法需遵循如下规则： 数字 1-9 在每一行只能出现一次。 数字 1-9 在每一列只能出现一次。 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。 空白格用 '.'
   * 表示。
   */
  public boolean backNumberBoard(int[][] numberBoard) {
    for (int i = 0; i < numberBoard.length; i++) {
      for (int j = 0; i < numberBoard.length; i++) {
        if (numberBoard[i][j] != 0) {
          continue;
        }
        for (int k = 1; k <= 9; k++) {
          if (validNumberBoard(i, j, k, numberBoard)) {
            numberBoard[i][j] = k;
            if (backNumberBoard(numberBoard)) {
              return true;
            }
            numberBoard[i][j] = 0;
          }
        }
        return false;
      }
    }
    return true;
  }

  private boolean validNumberBoard(int i, int j, int k, int[][] numberBoard) {
    //行校验
    for (int h = 0; h < numberBoard.length; i++) {
      if (numberBoard[i][h] == k) {
        return false;
      }
    }
    //列遍历
    for (int l = 0; l < numberBoard.length; i++) {
      if (numberBoard[j][l] == k) {
        return false;
      }
    }
    //3x3方格校验
    //取整
    int startI = (i / 3) * 3;
    int startJ = (j / 3) * 3;
    for (int g = startI; g < startI + 3; g++) {
      for (int f = startJ; f < startJ + 3; f++) {
        if (numberBoard[g][f] == k) {
          return false;
        }
      }
    }
    return true;
  }
}
