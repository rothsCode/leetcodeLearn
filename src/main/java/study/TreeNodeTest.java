package study;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import study.Struct.TreeNode;

/**
 * @author lishuai
 * @Description:
 * @date 2023/7/6 17:01
 */
public class TreeNodeTest {

  int maxLength = 0;


  public static void main(String[] args) {

  }


  /**
   * 递归层序遍历
   *
   * @param root
   * @return
   */
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> restList = new ArrayList<>();
    orderTree(restList, root, 0);
    return restList;
  }

  private void orderTree(List<List<Integer>> restList, TreeNode root, int level) {
    if (root == null) {
      return;
    }
    level++;
    if (restList.size() < level) {
      restList.add(new ArrayList<>());
    }
    restList.get(level - 1).add(root.getVal());
    orderTree(restList, root.getLeft(), level);
    orderTree(restList, root.getRight(), level);
  }


  private void dfsTree(List<List<Integer>> restList, TreeNode node) {
    if (node == null) {
      return;
    }
    Queue<TreeNode> que = (Queue<TreeNode>) new LinkedListTest<TreeNode>();
    que.offer(node);

    while (!que.isEmpty()) {
      List<Integer> itemList = new ArrayList<>();
      int len = que.size();

      while (len > 0) {
        TreeNode tmpNode = que.poll();
        itemList.add(tmpNode.getVal());

        if (tmpNode.getLeft() != null) {
          que.offer(tmpNode.getLeft());
        }
        if (tmpNode.getRight() != null) {
          que.offer(tmpNode.getRight());
        }
        len--;
      }

      restList.add(itemList);
    }

  }

  /**
   * 前序遍历
   */
  public List<Integer> preOrderRoot(TreeNode root) {
    List<Integer> valList = new ArrayList<>();
    preTree(root, valList);
    return valList;
  }

  private void preTree(TreeNode root, List<Integer> valList) {
    if (root == null) {
      return;
    }
    valList.add(root.getVal());
    preTree(root.getLeft(), valList);
    preTree(root.getRight(), valList);
  }

  /**
   * 中序遍历
   */
  public List<Integer> midOrderRoot(TreeNode root) {
    List<Integer> valList = new ArrayList<>();
    midTree(root, valList);
    return valList;
  }

  private void midTree(TreeNode root, List<Integer> valList) {
    if (root == null) {
      return;
    }
    midTree(root.getLeft(), valList);
    valList.add(root.getVal());
    midTree(root.getRight(), valList);
  }

  /**
   * 后序遍历
   */
  public List<Integer> backOrderRoot(TreeNode root) {
    List<Integer> valList = new ArrayList<>();
    backTree(root, valList);
    return valList;
  }

  private void backTree(TreeNode root, List<Integer> valList) {
    if (root == null) {
      return;
    }
    backTree(root.getLeft(), valList);
    backTree(root.getRight(), valList);
    valList.add(root.getVal());
  }

  /**
   * 翻转二叉树--左右子节点翻转
   */
  public TreeNode reverseTree(TreeNode root) {
    if (root == null) {
      return null;
    }
    reverseTree(root.getLeft());
    reverseTree(root.getRight());
    TreeNode temp = root.getLeft();
    root.setLeft(root.getRight());
    root.setRight(temp);
    return root;
  }

  /**
   * 层序迭代
   */
  public TreeNode reverseTreeByIterator(TreeNode root) {
    if (root == null) {
      return null;
    }
    ArrayDeque<TreeNode> deque = new ArrayDeque<>();
    deque.add(root);
    while (!deque.isEmpty()) {
      int size = deque.size();
      while (size-- > 0) {
        TreeNode node = deque.poll();
        TreeNode temp = node.getLeft();
        node.setLeft(node.getRight());
        node.setRight(temp);
        if (node.getLeft() != null) {
          deque.offer(node.getLeft());
        }
        if (node.getRight() != null) {
          deque.offer(node.getRight());
        }
      }
    }
    return root;
  }

  /**
   * 对称树校验
   *
   * @return
   */
  public boolean validSymmetry(TreeNode root) {
    if (root == null) {
      return false;
    }
    return validSymmetryTree(root.getLeft(), root.getRight());
  }


  boolean validSymmetryTree(TreeNode left, TreeNode right) {

    if (left == null && right != null) {
      return false;
    }
    if (left != null && right == null) {
      return false;
    }
    if (left.getVal() != left.getVal()) {
      return false;
    }
    boolean leftFlag = validSymmetryTree(left.getLeft(), right.getRight());
    boolean rightFlag = validSymmetryTree(left.getRight(), right.getLeft());
    return leftFlag && rightFlag;
  }

  /**
   * 二叉树的深度 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
   */
  public int calMaxLength(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftLength = calMaxLength(root.getLeft());
    int rightLength = calMaxLength(root.getRight());
    return Math.max(leftLength, rightLength) + 1;
  }

  public int calMaxLengthIterator(TreeNode root) {

    ans(root, 0);
    return maxLength;
  }

  private void ans(TreeNode root, int tempLength) {
    if (root == null) {
      return;
    }
    tempLength++;
    maxLength = Math.max(tempLength, maxLength);
    ans(root.getLeft(), tempLength);
    ans(root.getRight(), tempLength);
    tempLength--;
  }

  /**
   * 给定一个二叉树，找出其最小深度。 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 左右子节点都为空才行
   */
  public int minLength(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftLength = minLength(root.getLeft());
    int rightLength = minLength(root.getRight());
    if (root.getLeft() == null) {
      return rightLength;
    }
    if (root.getRight() == null) {
      return leftLength;
    }
    return Math.min(leftLength, rightLength) + 1;

  }

  /**
   * 节点数
   *
   * @param root
   * @return
   */
  public int countNode(TreeNode root) {
    if (root == null) {
      return 0;
    }
    return countNode(root.getLeft()) + countNode(root.getRight()) + 1;
  }

  /**
   * 高度平衡二叉树 分别求出其左右子树的高度，然后如果差值小于等于1，则返回当前二叉树的高度，否则返回-1，表示已经不是二叉平衡树了。
   */
  public int validTreeBalance(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftLength = validTreeBalance(root.getLeft());
    int rightLength = validTreeBalance(root.getRight());
    if (leftLength == -1 || rightLength == -1) {
      return -1;
    }
    return Math.abs(leftLength - rightLength) <= 1 ? Math.max(leftLength, rightLength) + 1 : -1;
  }

  /**
   * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
   */
  public List<List<Integer>> allNodePath(TreeNode root) {
    List<List<Integer>> paths = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    searchAllPath(root, paths, path);
    return paths;
  }

  void searchAllPath(TreeNode root, List<List<Integer>> paths, List<Integer> path) {
    if (root == null) {
      return;
    }
    path.add(root.getVal());
    if (root.getLeft() == null && root.getRight() == null) {
      paths.add(path);
      return;
    }
    if (root.getLeft() != null) {
      searchAllPath(root.getLeft(), paths, path);
      path.remove(path.size() - 1);//此处回溯
    }
    if (root.getRight() != null) {
      searchAllPath(root.getRight(), paths, path);
      path.remove(path.size() - 1);
    }
  }

  /**
   * 计算所有左叶子之和
   */
  public int calLeftNode(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int midVal = 0;
    if (root.getLeft() != null && root.getLeft().getLeft() == null
        && root.getLeft().getRight() == null) {
      midVal = root.getVal();
    }
    int leftVal = calLeftNode(root.getLeft());
    int rightVal = calLeftNode(root.getRight());
    return leftVal + rightVal + midVal;
  }

  /**
   * 给定一个二叉树，在树的最后一行找到最左边的值。
   */
  public int searchLeftVal(TreeNode root) {
    if (root == null) {
      return -1;
    }
    Integer maxDepth = 0;
    Integer result = 0;
    searchLeftestVal(root, 0, maxDepth, result);
    return result;
  }

  private void searchLeftestVal(TreeNode root, Integer depth, Integer maxDepth, Integer result) {
    if (root == null) {
      return;
    }
    //叶子节点时停止
    if (root.getLeft() == null && root.getRight() == null && depth > maxDepth) {
      maxDepth = depth;
      result = root.getVal();
    }
    //前序遍历找到最左值
    depth++;
    searchLeftestVal(root.getLeft(), depth, maxDepth, result);
    searchLeftestVal(root.getRight(), depth, maxDepth, result);
  }

  /**
   * 层序遍历处理
   */
  public int searchLeftValByLine(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    int result = 0;
    while (queue.size() > 0) {
      for (int i = 0; i < queue.size(); i++) {
        TreeNode node = queue.poll();
        if (i == 0) {
          result = node.getVal();
        }
        if (node.getLeft() != null) {
          queue.offer(node.getLeft());
        }
        if (node.getRight() != null) {
          queue.offer(node.getRight());
        }
      }
    }
    return result;
  }

  /**
   * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和
   */
  public boolean ValidTreeNodeSum(TreeNode root, Integer target, Integer pathResult) {
    if (root == null) {
      return false;
    }
    pathResult += root.getVal();
    if (root.getLeft() == null && root.getRight() == null) {
      return target.equals(pathResult);
    }
    boolean leftFlag = ValidTreeNodeSum(root.getLeft(), target, pathResult);
    boolean rightFlag = ValidTreeNodeSum(root.getRight(), target, pathResult);
    return leftFlag || rightFlag;
  }

  /**
   * 做减法，最后判断是否等于0
   *
   * @param root
   * @param target
   * @return
   */
  public boolean ValidTreeNodeSum(TreeNode root, Integer target) {
    if (root == null) {
      return false;
    }
    if (root.getLeft() == null && root.getRight() == null) {
      return target.equals(root.getVal());
    }
    boolean leftFlag = ValidTreeNodeSum(root.getLeft(), target - root.getVal());
    boolean rightFlag = ValidTreeNodeSum(root.getRight(), target - root.getVal());
    return leftFlag || rightFlag;
  }

  /**
   * 最大二叉树--构建最大二叉树
   */
  public TreeNode maxTreeNode(List<Integer> nums) {
    return null;
  }

  /**
   * 合并合并二叉树
   */
  public TreeNode combineTree(TreeNode left, TreeNode right) {
    if (left == null && right == null) {
      return null;
    }
    if (left == null) {
      return right;
    }
    if (right == null) {
      return left;
    }
    left.setVal(left.getVal() + right.getVal());
    left.setLeft(combineTree(left.getLeft(), right.getLeft()));
    left.setRight(combineTree(left.getRight(), right.getRight()));
    return left;
  }

  /**
   * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL
   */

  public TreeNode searchTreeNode(TreeNode root, Integer val) {
    if (root == null || root.getVal().equals(val)) {
      return root;
    }
    TreeNode left = searchTreeNode(root.getLeft(), val);
    if (left != null) {
      return left;
    }
    return searchTreeNode(root.getRight(), val);
  }

  /**
   * 节点的左子树只包含小于当前节点的数。 节点的右子树只包含大于当前节点的数。 所有左子树和右子树自身必须也是二叉搜索树
   */
  public Boolean validSearchTree(TreeNode root) {
    if (root == null) {
      return false;
    }
    if (root.getLeft() != null && root.getLeft().getVal() <= root.getVal()) {
      return true;
    }
    if (root.getRight() != null && root.getRight().getVal() <= root.getVal()) {
      return true;
    }
    Boolean leftFlag = validSearchTree(root.getLeft());
    if (!leftFlag) {
      return false;
    }
    Boolean rightFlag = validSearchTree(root.getRight());
    return rightFlag;
  }

  /**
   * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值
   */
  public void calTreeNodeMinAbs(TreeNode root, Integer minAbsVal, TreeNode pre) {
    if (root == null) {
      return;
    }
    if (pre != null) {
      Integer temValue = Math.abs(pre.getVal() - root.getVal());
      minAbsVal = Math.min(minAbsVal, temValue);
    }
    calTreeNodeMinAbs(root.getLeft(), minAbsVal, root);
    calTreeNodeMinAbs(root.getRight(), minAbsVal, root);
  }

  /**
   * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）
   */
  public void calTreeMaxCount(TreeNode node, TreeNode pre, Set<Integer> maxCounts, Integer max,
      Integer curMax) {
    if (node == null) {
      return;
    }
    calTreeMaxCount(node.getLeft(), node, maxCounts, max, curMax);//左
    //中
    if (pre != null) {
      if (pre.getVal().equals(node.getVal())) {
        curMax = curMax + 1;
        if (curMax > max) {
          maxCounts.clear();
          maxCounts.add(node.getVal());
          max = curMax;
        } else if (curMax.equals(max)) {
          maxCounts.add(node.getVal());
        }
      } else {
        curMax = 0;
      }
    } else {
      curMax = 1;
      maxCounts.add(node.getVal());
    }
    //右
    calTreeMaxCount(node.getRight(), node, maxCounts, max, curMax);
  }

  /**
   * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先 --节点有序
   */
  public TreeNode findNearestParentSearchTree(TreeNode root, Integer p, Integer q) {
    if (root == null) {
      return null;
    }
    if (root.getLeft() != null) {
      if (root.getLeft().getVal() > p && root.getLeft().getVal() > q) {
        return findNearestParentSearchTree(root.getLeft(), p, q);

      }
    }
    if (root.getRight() != null) {
      if (root.getRight().getVal() > p && root.getRight().getVal() > q) {
        return findNearestParentSearchTree(root.getRight(), p, q);
      }
    }
    return root;
  }

  /**
   * 给定一个普通二叉树, 找到该树中两个指定节点的最近公共祖先
   */
  public TreeNode findNearestParentTree(TreeNode root, Integer p, Integer q) {
    if (root == null) {
      return null;
    }
    //p或者q为公共父节点
    if (root.getVal().equals(p) || root.getVal().equals(q)) {
      return root;
    }
    //左子树均未找到则一定在右子树上
    TreeNode leftParentTree = findNearestParentTree(root.getLeft(), p, q);
    if (leftParentTree == null) {
      return root.getRight();
    }
    //右子树均未找到则一定在左子树上
    TreeNode rightParentTree = findNearestParentTree(root.getRight(), p, q);
    if (rightParentTree == null) {
      return root.getLeft();
    }
    //节点在左右两侧
    return root;
  }

  /**
   * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不同。
   */
  public TreeNode insertNewTree(TreeNode root, Integer val) {
    if (root == null) {
      return new TreeNode(null, null, val);
    }
    if (root.getVal() > val) {
      root.setLeft(insertNewTree(root.getLeft(), val));
    }
    if (root.getVal() < val) {
      root.setRight(insertNewTree(root.getRight(), val));
    }
    return root;
  }

  /**
   * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。 返回二叉搜索树（有可能被更新）的根节点的引用。 删除调整的情况
   * -删除节点为当前节点 左右子树都存在--右节点填充，左节点放在右节点最左侧位置 左子树存在，右子树不存在,左节点填充 右子树存在，左子树不存在,右节点填充 左右节点都不存在,则直接删除
   */
  public TreeNode deleteTreeNode(TreeNode root, Integer val) {
    if (root == null) {
      return null;
    }
    if (root.getVal().equals(val)) {
      if (root.getLeft() == null && root.getRight() == null) {
        return null;
      }
      if (root.getLeft() != null && root.getRight() == null) {
        root = root.getLeft();
      }
      if (root.getRight() != null && root.getLeft() == null) {
        root = root.getRight();
      }
      //查询右节点最左侧
      TreeNode rightLeftNode = root.getRight();
      while (rightLeftNode.getLeft() != null) {
        rightLeftNode = rightLeftNode.getLeft();
      }
      rightLeftNode.setLeft(root.getLeft());
      root = root.getRight();
    } else if (root.getVal() > val) {
      root.setLeft(deleteTreeNode(root.getLeft(), val));
    } else {
      root.setRight(deleteTreeNode(root.getRight(), val));
    }
    return root;
  }

  /**
   * 给定一个二叉搜索树，同时给定最小边界L 和最大边界 R。 通过修剪二叉搜索树，使得所有节点的值在[L, R]中 (R>=L) 。
   * 你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。
   */
  public TreeNode trimTreeNode(TreeNode root, Integer l, Integer r) {
    if (root == null) {
      return null;
    }
    if (root.getVal() < l) {
      return trimTreeNode(root.getRight(), l, r);
    }
    if (root.getVal() > l) {
      return trimTreeNode(root.getLeft(), l, r);
    }
    root.setLeft(trimTreeNode(root.getLeft(), l, r));
    root.setRight(trimTreeNode(root.getRight(), l, r));
    return root;
  }

  /**
   * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1
   */
  public TreeNode sortNumsCoverTree(int[] nums) {
    if (nums == null) {
      return null;
    }
    if (nums.length == 1) {
      return new TreeNode(null, null, nums[0]);
    }
    return sortNumsLeftRightIndex(nums, 0, nums.length);
  }

  private TreeNode sortNumsLeftRightIndex(int[] nums, int left, int right) {
    int mid =left +(right-left)/2;
    TreeNode midTree = new TreeNode(null,null,nums[mid]);
    midTree.setLeft(sortNumsLeftRightIndex(nums, left, mid-1));
    midTree.setRight(sortNumsLeftRightIndex(nums, mid+1, right));
    return midTree;
  }

  /**
   * 给出二叉搜索树的根节点，该树的节点值各不相同，
   * 请你将其转换为累加树（Greater Sum Tree），
   * 使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。
   */
  public TreeNode coverSumTree(TreeNode root,int sum){
    if(root==null){
      return null;
    }
    if(root.getRight()==null){
      return root;
    }
    root.setRight(coverSumTree(root.getRight(),sum));
    sum+=root.getRight().getVal();
    root.setVal(sum);
    root.setLeft(coverSumTree(root.getLeft(),sum));

    return root;
  }


}
