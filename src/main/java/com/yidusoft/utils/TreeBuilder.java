package com.yidusoft.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by you on 2017/7/18.
 */
public class TreeBuilder {

    /**
     * 两层循环实现建树
     * @param treeNodes 传入的树节点列表
     * @return
     */
    public static List<TreeNode> bulid(List<TreeNode> treeNodes) {

        List<TreeNode> trees = new ArrayList<TreeNode>();

        for (TreeNode treeNode : treeNodes) {

            if ("0".equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }

            for (TreeNode it : treeNodes) {
                if (it.getParentId() == treeNode.getId()) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<TreeNode>());
                    }
                    treeNode.getChildren().add(it);
                }
            }
        }
        return trees;
    }

    /**
     * 使用递归方法建树
     * @param treeNodes
     * @return
     */
    public static List<TreeNode> buildByRecursive(List<TreeNode> treeNodes) {
        List<TreeNode> trees = new ArrayList<TreeNode>();
        for (TreeNode treeNode : treeNodes) {
            if ("0".equals(treeNode.getParentId())) {
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static TreeNode findChildren(TreeNode treeNode, List<TreeNode> treeNodes) {
        for (TreeNode it : treeNodes) {
            if(treeNode.getId().equals(it.getParentId())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<TreeNode>());
                }
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }

    public static void main(String[] args) {

//        TreeNode treeNode1 = new TreeNode("1","组织列表","0");

        TreeNode treeNode3 = new TreeNode("1","广州市易度","bb");
        TreeNode treeNode4 = new TreeNode("3","信息部","1");

        TreeNode treeNode8 = new TreeNode("4","沃尔玛","3");
//        TreeNode treeNode9 = new TreeNode("77","采购部","88");

        List<TreeNode> list = new ArrayList<TreeNode>();

        list.add(treeNode3);
        list.add(treeNode4);

        list.add(treeNode8);
//        list.add(treeNode9);

//        List<TreeNode> trees = TreeBuilder.buildByRecursive(list,"bb");

//        System.out.println(JSON.toJSON(trees));

    }
}
