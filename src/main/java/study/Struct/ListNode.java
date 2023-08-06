package study.Struct;

/**
 * @author lishuai
 * @Description:
 * @date 2023/6/30 17:09
 */

public class ListNode {

   public ListNode next;
  private int val;
  public ListNode(ListNode next,int val){
    this.next =next;
    this.val =val;
  }

  public int getVal() {
    return val;
  }


}
