package com.spartaglobal.sortproject.sorters;

import java.util.ArrayList;
import java.util.List;


public class TreeSorter<E extends Comparable> extends GenericSorter<E>{

    @Override
    public List<E> sort(List<E> list) {
        BinaryTree<E> tree = new BinaryTree<>();
        for(E item: list){
            tree.addNode(item);
        }
        return tree.inOrderTraversal();
    }

    @Override
    public E[] arraySort(E[] array) {
        BinaryTree<E> tree = new BinaryTree<>();
        for(E item: array){
            tree.addNode(item);
        }
        List<E> list = tree.inOrderTraversal();
        for(int i = 0; i<array.length; i++){
            array[i] = list.get(i);
        }
        return array;
    }

    class Node<E extends Comparable>{
        private E value;
        private Node<E> leftChild;
        private Node<E> rightChild;
        private Node<E> parent;
        private int quantity;

        Node(E value){
            this.value = value;
            this.leftChild = null;
            this.rightChild = null;
            this.quantity = 1;
            this.parent = null;
        }

        Node(E value, Node<E> parent){
            this.value = value;
            this.leftChild = null;
            this.rightChild = null;
            this.quantity = 1;
            this.parent = parent;
        }



        public Node<E> getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node<E> leftChild) {
            this.leftChild = leftChild;
        }

        public void setLeftChild(E leftChildVal) {
            this.leftChild = new Node(leftChildVal, this);
        }

        public Node<E> getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node<E> rightChild) {
            this.rightChild = rightChild;
        }

        public void setRightChild(E rightChildVal) {
            this.rightChild = new Node(rightChildVal, this);
        }

        public void incrementAmount(){
            this.quantity += 1;
        }

        public int getAmount(){
            return this.quantity;
        }

        public E getValue(){
            return value;
        }
    }

    class BinaryTree<E extends Comparable>{
        private Node<E> root;

        BinaryTree(){
            root = null;
        }

        BinaryTree(E val){
            root = new Node<>(val);
        }

        public void addNode(E val){
            if(root == null){
                root = new Node<>(val);
            }
            else{
                Node<E> currentNode = root;
                boolean insertionComplete = false;
                while(!insertionComplete){
                    int comparison = currentNode.getValue().compareTo(val);
                    if(comparison == 0){
                        currentNode.incrementAmount();
                        insertionComplete = true;
                    }
                    else if(comparison < 0){
                        if(currentNode.rightChild == null){
                            currentNode.setRightChild(val);
                            insertionComplete = true;
                        }
                        else{
                            currentNode = currentNode.rightChild;
                        }
                    }
                    else{
                        if(currentNode.leftChild == null){
                            currentNode.setLeftChild(val);
                            insertionComplete = true;
                        }
                        else{
                            currentNode = currentNode.leftChild;
                        }
                    }

                }
            }
        }

        public List<E> inOrderTraversal(){
            if(root == null){
                return null;
            }
            List<E> list = new ArrayList<>();
            inOrderTraversal(root, list);
            return list;
        }

        public void inOrderTraversal(Node<E> node, List<E> list){
            if(node.leftChild != null){
                inOrderTraversal(node.leftChild, list);
            }
            for(int i = 1; i<=node.getAmount(); i++){
                list.add(node.getValue());
            }
            if(node.rightChild != null){
                inOrderTraversal(node.rightChild, list);
            }
        }


    }
}
