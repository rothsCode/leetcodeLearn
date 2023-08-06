package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lishuai
 * @Description:
 * @date 2023/7/3 14:55
 */
public class Hash {

  public static void main(StringTest[] args) {



  }

  /**
   * 词语异位法
   */
//   public boolean similarText(StringTest s, StringTest l){
//     int [] records = new int[26];
//     for(int i=0;i<s.length();i++){
//       records[s.charAt(i)-'a']++;
//     }
//     //做抵消处理
//     for(int i=0;i<l.length();i++){
//       records[l.charAt(i)-'a']--;
//     }
//     for(int i:records){
//       if(i!=0){
//         return false;
//       }
//     }
//     return false;
//   }

  /**
   * 四数相加
   */
   public int[] getSum(int[] nums,int n){
     return null;
   }

  /**
   * 三数之和
   * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组
   */
  public List<int[]> threeSum(int[] nums){
    List<int[]> sumList = new ArrayList<>();
    //先排序方便去重判断
    Arrays.sort(nums);
    for(int i=0;i<nums.length-2;i++){
      if(nums[i]>0){
        break;
      }
      if(i>0&&nums[i-1]==nums[i]){
        continue;
      }
      //双指针处理另外两个数
      int left = i+1;
      int right =nums.length-1;
      while (right>left){
        int sum = nums[i] +nums[left] +nums[right];
        //说明right大了
        if(sum>0){
          right--;
        }else if(sum<0){
          left++;
        }else {
          sumList.add(new int[]{nums[i], nums[left], nums[right]});
          right--;
          left++;
          //去重处理
          while (nums[right]==nums[right-1]){
            right--;
          }
          while (nums[left]==nums[left+1]){
            left++;
          }
        }
      }
    }
    return sumList;
  }

  /**
   * 四数之和
   * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
   * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
   */
  public List<int[]> fourSum(int[] nums){
    List<int[]> sumList = new ArrayList<>();
    //先排序方便去重判断
    Arrays.sort(nums);
    for(int i=0;i<nums.length;i++){
      if(nums[i]>0){
        break;
      }
      if(i>0&&nums[i-1]==nums[i]){
        continue;
      }
      for(int j=i+1;j<nums.length;j++) {
        //减枝去重
        if((nums[i]+nums[j])>0){
          break;
        }
        if(j>i+1&&nums[j-1]==nums[j]){
          continue;
        }
        //双指针处理另外两个数
        int left = i + 1;
        int right = nums.length - 1;
        while (right > left) {
          int sum = nums[i] +nums[j]+ nums[left] + nums[right];
          //说明right大了
          if (sum > 0) {
            right--;
          } else if (sum < 0) {
            left++;
          } else {
            sumList.add(new int[]{nums[i], nums[left], nums[right]});
            right--;
            left++;
            //去重处理
            while (nums[right] == nums[right - 1]) {
              right--;
            }
            while (nums[left] == nums[left + 1]) {
              left++;
            }
          }
        }
      }
    }
    return sumList;




  }



}
