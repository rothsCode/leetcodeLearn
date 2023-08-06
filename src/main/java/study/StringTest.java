package study;

/**
 * @author lishuai
 * @Description:
 * @date 2023/7/4 9:29
 */
public class StringTest {

  public static void main(String[] args) {
    reverseChar("  hello world!   ");
  }



  /**
   * 字符反转
   */
  public static  void  reverseString(char[]s){
    int left =0;
    int right =s.length-1;
    while (left<right){
      char temp = s[left];
      s[left] =s[right];
      s[right] =temp;
      left++;
      right--;
    }
  }

  /**
   * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
   *
   * 示例 1： 输入：s = "We are happy."
   * 输出："We%20are%20happy."
   */
  public static String replaceTrim(String text1){
    //扩容到替换后的大小
    if(text1==null){
      return null;
    }

    StringBuilder sb = new StringBuilder();
    for(int i=0;i<text1.length();i++){
      if(text1.charAt(i) ==' '){
        sb.append("%20");
      }else {
        sb.append(text1.charAt(i));
      }
    }

    return sb.toString();

  }

  /**
   * 单词反转
   */
  public static String  reverseChar(String text){
    //去除空格
    int start =0;
    int end = text.length()-1;
    while (text.charAt(start)==' '){
      start++;
    }
    while (text.charAt(end)==' '){
      end--;
    }
    StringBuilder sb = new StringBuilder();
    for(int i=start;i<=end;i++){
      sb.append(text.charAt(i));
    }
    //全部反转
    int left =0;
    int right = sb.length()-1;
    while (left< right){
     char temp = sb.charAt(left);
     sb.setCharAt(left,sb.charAt(right));
     sb.setCharAt(right,temp);
     left++;
     right--;
    }
    //单词局部反转
    int lastStart =0;
    for(int i=0;i<sb.length();i++){
      if(sb.charAt(i)==' '){
        reversePart(sb,lastStart,i);
        lastStart =i+1;
      }else if(i==sb.length()-1){
        reversePart(sb,lastStart,i);
      }
    }
    return sb.toString();
   }

  private static void reversePart(StringBuilder sb, int lastStart, int cur) {
    while (lastStart< cur){
      char temp = sb.charAt(lastStart);
      sb.setCharAt(lastStart,sb.charAt(cur));
      sb.setCharAt(cur,temp);
      lastStart++;
      cur--;
    }
  }


}
