package com.yidusoft.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by you on 2017/7/18.
 */
public class IndexTreeBuilder {

    /**
     * 两层循环实现建树
     * @param treeNodes 传入的树节点列表
     * @return
     */
    public static List<IndexTreeNode> bulid(List<IndexTreeNode> treeNodes) {

        List<IndexTreeNode> trees = new ArrayList<IndexTreeNode>();

        for (IndexTreeNode treeNode : treeNodes) {

            if ("0".equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }

            for (IndexTreeNode it : treeNodes) {
                if (it.getParentId() == treeNode.getId()) {
                    if (treeNode.getChilds() == null) {
                        treeNode.setChilds(new ArrayList<IndexTreeNode>());
                    }
                    treeNode.getChilds().add(it);
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
    public static List<IndexTreeNode> buildByRecursive(List<IndexTreeNode> treeNodes) {
        List<IndexTreeNode> trees = new ArrayList<IndexTreeNode>();
        for (IndexTreeNode treeNode : treeNodes) {
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
    public static IndexTreeNode findChildren(IndexTreeNode treeNode, List<IndexTreeNode> treeNodes) {
        for (IndexTreeNode it : treeNodes) {
            if(treeNode.getId().equals(it.getParentId())) {
                if (treeNode.getChilds() == null) {
                    treeNode.setChilds(new ArrayList<IndexTreeNode>());
                }
                treeNode.getChilds().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }

    public static void main(String[] args) {

//        TreeNode treeNode1 = new TreeNode("1","组织列表","0");

        IndexTreeNode treeNode3 = new IndexTreeNode("3","广州市易度","0","/","/");
        IndexTreeNode treeNode4 = new IndexTreeNode("4","信息部","3","/","/");

        IndexTreeNode treeNode8 = new IndexTreeNode("88","沃尔玛","0","/","/");
        IndexTreeNode treeNode9 = new IndexTreeNode("77","采购部","88","/","/");

        List<IndexTreeNode> list = new ArrayList<IndexTreeNode>();

        list.add(treeNode3);
        list.add(treeNode4);

        list.add(treeNode8);
        list.add(treeNode9);

        List<IndexTreeNode> trees = IndexTreeBuilder.buildByRecursive(list);


    }
}
