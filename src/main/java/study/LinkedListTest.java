package study;

import study.Struct.ListNode;

/**
 * @author lishuai
 * @Description:
 * @date 2023/6/30 17:00
 */
public class LinkedListTest<T> {

  public static void main(StringTest[] args) {

  }

  public ListNode removeElement(ListNode headNode, int val) {
    if (headNode == null) {
      return null;
    }
    //构造虚拟头结点
    ListNode dumpNode = new ListNode(headNode, 0);
    ListNode pre = dumpNode;
    ListNode cur = headNode;
    while (cur.next != null) {
      if (cur.getVal() == val) {
        pre.next = cur.next;
      } else {
        pre = cur;
      }
      cur = cur.next;
    }
    return dumpNode.next;

  }

  /**
   * 链表翻转
   */
  public static ListNode reverseList(ListNode listNode) {
    if (listNode == null) {
      return null;
    }
    ListNode pre = null;
    ListNode cur = listNode;
    ListNode temp = null;
    while (cur != null) {
      temp = cur.next;

      cur.next = pre;
      pre = cur;

      cur = temp;
    }
    return pre;
  }

  public static ListNode reverseList1(ListNode head) {
    ListNode headNode = new ListNode(null,0);
    ListNode cur = head;
    ListNode next = null;
    while (cur != null) {
      next = cur.next;

      cur.next = headNode.next;
      headNode.next = cur;

      cur = next;
    }
    return headNode.next;
  }

  public static void main(String[] args) {
    ListNode headNode3 = new ListNode(null,3);
    ListNode headNode2 = new ListNode(headNode3,2);
    ListNode headNode1 = new ListNode(headNode2,1);
    ListNode headNode = new ListNode(headNode1,0);
    reverseList(headNode);
  }

  /**
   * 链表节点交换--递归调用
   */
  public ListNode swapList(ListNode listNode) {
    if (listNode == null || listNode.next == null) {
      return listNode;
    }
    //第二个节点
    ListNode next = listNode.next;
    //交换后续节点
    ListNode nextNode = swapList(next.next);
    next.next = listNode;
    listNode.next = nextNode;
    return next;
  }

  /**
   * 删除链表的第N个节点
   */
  public ListNode removeNode(ListNode head, int n) {
    if (head == null || head.next == null) {
      return head;
    }
    ListNode cur = head;
    ListNode pre = new ListNode(head, -1);
    ;
    int index = 0;
    while (cur.next != null) {
      if (index == n) {
        pre.next = cur.next;
      }
      index++;
      pre = cur;
      cur = cur.next;
    }
    return head;
  }

  /**
   * 删除链表的倒数第N个节点 快慢指针
   */
  public ListNode removeLastNode(ListNode head, int n) {
    ListNode dumpNode = new ListNode(head, -1);
    ListNode fastNode = dumpNode;
    ListNode slowNode = dumpNode;
    int count = 0;
    while (count < n) {
      fastNode = fastNode.next;
      count++;
    }
    while (fastNode.next != null) {
      fastNode = fastNode.next;
      slowNode = slowNode.next;
    }
    slowNode.next = slowNode.next.next;
    return dumpNode.next;
  }

  /**
   * 环形链表
   */
  public ListNode CycleList(ListNode head) {
    ListNode slowNode = head, fastNode = head;
    while (fastNode != null && fastNode.next != null) {
      //前进两步
      fastNode = fastNode.next.next;
      slowNode = slowNode.next;
      //相遇点
      if (fastNode == slowNode) {
        ListNode temFast = fastNode;
        ListNode temHead = head;
        while (temFast != temHead) {
          temFast = temFast.next;
          temHead = temHead.next;
        }
        return temHead;
      }
    }
    return null;
  }

  /**
   * L0-Ln-L1-Ln-1
   */
  public ListNode reverseRuleNode(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }
    //右边节点
    //反转右链表
    ListNode right = reverseList(slow.next);
    //左边节点
    slow.next=null;
    ListNode left = head;
    ListNode sortHead = left;
    //组合链表
    while (left != null || right != null) {
     ListNode nextLeft = left.next;
     left.next =right;
     left=nextLeft;
      //拼接左节点
     ListNode nextRight = right.next;
     right.next =left;
     right =nextRight;
    }
    return sortHead;
  }


}
