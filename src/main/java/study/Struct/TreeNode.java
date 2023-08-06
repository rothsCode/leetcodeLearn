package study.Struct;

/**
 * @author lishuai
 * @Description:
 * @date 2023/6/30 17:09
 */

public class TreeNode {

  private TreeNode left;
  private TreeNode right;
  private Integer val;
  public TreeNode(TreeNode left,TreeNode right,int val){
    this.left =left;
    this.right =right;
    this.val =val;
  }

  public Integer getVal() {
    return val;
  }

  public void setVal(Integer val) {
   this.val =val;
  }

  public TreeNode getLeft(){
    return left;
  }
  public void setLeft(TreeNode left){
    this.left =left;
  }
  public void setRight(TreeNode right){
    this.right =right;
  }
  public TreeNode getRight(){
    return right;
  }
}
