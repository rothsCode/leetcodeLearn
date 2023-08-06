package study;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author lishuai
 * @Description:
 * @date 2023/8/2 9:45
 */
public class MonotonicStack {

  /**
   * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
   *
   * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
   */
   public static int [] higherTemperature(int[] t){
     int[] h = new int[t.length];
     //记录游标
     Deque<Integer> stack = new LinkedList<>();
     stack.push(0);
     for(int i=1;i<t.length;i++){
      while (!stack.isEmpty()&&t[i]>t[stack.peek()]){
        h[stack.peek()] = i-stack.peek();
        stack.pop();
      }
      stack.push(i);
     }
     return h;
   }

  /**
   * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
   * 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
   * @param args
   */

  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    Stack<Integer> temp = new Stack<>();
    int[] res = new int[nums1.length];
    Arrays.fill(res,-1);
    HashMap<Integer, Integer> hashMap = new HashMap<>();
    for (int i = 0 ; i< nums1.length ; i++){
      hashMap.put(nums1[i],i);
    }
    temp.add(0);
    for (int i = 1; i < nums2.length; i++) {
      if (nums2[i] <= nums2[temp.peek()]) {
        temp.add(i);
      } else {
        while (!temp.isEmpty() && nums2[temp.peek()] < nums2[i]) {
          if (hashMap.containsKey(nums2[temp.peek()])){
            Integer index = hashMap.get(nums2[temp.peek()]);
            res[index] = nums2[i];
          }
          temp.pop();
        }
        temp.add(i);
      }
    }
    return res;
  }

  /**
   * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），
   * 输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，
   * 这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
   * @param args
   */
   public int[] cycleNums(int[]nums){
     int [] h = new int[nums.length];
     Arrays.fill(h,-1);
     Deque<Integer> stack = new LinkedList<>();
     stack.push(0);
     for(int i=1;i<2*nums.length;i++){
       while (!stack.isEmpty()&&nums[i%nums.length]>nums[stack.peek()]){
         h[stack.peek()] =nums[i%nums.length];
         stack.pop();
       }
       stack.push(i%nums.length);
     }
     return h;
   }

  /**
   * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
   */
   public static int maxWater(int[] water){
     //s =l*h
     int waterSum = 0;
     int maxLeft =0;
     int maxRight =0;
     for(int i=2;i<water.length;i++){
       maxRight =Math.max(maxRight,water[i]);
     }
     for(int i=1;i<water.length-1;i++){
       maxLeft =Math.max(maxLeft,water[i-1]);
      if(maxRight==water[i]){
        maxRight=0;
        for(int j=i+2;j<water.length;j++){
          maxRight =Math.max(maxRight,water[j]);
        }
      }
       int minHeight =Math.min(maxLeft,maxRight);
       waterSum+= Math.max(minHeight-water[i],0);
     }
     return waterSum;
   }

  /**
   * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
   * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
   * @param nums
   * @return
   */
  public static  int largestRectangleArea(int[] heights) {
    Stack<Integer> st = new Stack<Integer>();

    // 数组扩容，在头和尾各加入一个元素
    int [] newHeights = new int[heights.length + 2];
    newHeights[0] = 0;
    newHeights[newHeights.length - 1] = 0;
    for (int index = 0; index < heights.length; index++){
      newHeights[index + 1] = heights[index];
    }
    heights = newHeights;

    st.push(0);
    int result = 0;
    // 第一个元素已经入栈，从下标1开始
    for (int i = 1; i < heights.length; i++) {
      //中间高左右两边低 02310 3-1-1
      while (heights[i] < heights[st.peek()]) { // 注意是while
        int mid = st.peek();
        st.pop();
        int left = st.peek();
        int right = i;
        int w = right - left - 1;
        int h = heights[mid];
        result = Math.max(result, w * h);
      }
      st.push(i);
    }
    return result;
  }
   public static int dynamicMaxWater(int[] water){
     int waterSum = 0;
     int [] leftDp = new int[water.length];
     leftDp[0] =water[0];
     int [] rightDp = new int[water.length];
     rightDp[water.length-1] =water[water.length-1];
     for(int l =1;l<water.length-1;l++){
       leftDp[l] =Math.max(leftDp[l-1],water[l]);
     }
     for(int r =water.length-2;r>=1;r--){
       rightDp[r] =Math.max(rightDp[r+1],water[r]);
     }
     for(int i=1;i<water.length-1;i++){
       int minHeight =Math.min(leftDp[i],rightDp[i]);
       waterSum+= Math.max(minHeight-water[i],0);
     }
     return waterSum;
   }





  public static void main(String[] args) {
//     int[] t ={73, 74, 75, 71, 69, 72, 76, 73};
//     higherTemperature(t);
//    int[] water ={0,1,0,2,1,0,1,3,2,1,2,1};
//    dynamicMaxWater(water);
    largestRectangleArea(new int[]{2,3,4,5,6,7,6,5,4,3,2});

  }



}
