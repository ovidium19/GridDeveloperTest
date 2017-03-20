/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package griddeveloper;

/**
 *
 * @author mitroio
 */
public class BSTEvents {
    class Node{
        Event val;
        Node left,right;
        public Node(Event value){
            val=value;
            left=null;
            right=null;
        }
    }
    private Node root;
    public BSTEvents(){
        root=null;
    }
    
    public Node getRoot(){
        return root;
    }
    public Node find(Event toFind){
        Node iter=root;
        while(iter.val!=toFind){
            if (iter==null) return null;
            if (iter.val==toFind) return iter;
            if (toFind.getxCord()<iter.val.getxCord())
                iter=iter.left;
            else iter=iter.right;
            
        }
    }
    private Node maxLeftSubtreeParent(Node start){
        Node iter=start;
        Node parent=null;
        if (iter.left==null){
            return null;
        }
        else {
            parent=iter;
            iter=iter.left;
            while(iter.right!=null){
                parent=iter;
                iter=iter.right;
            }
            return parent;  
        }
    }
    private Node minRightSubtreeParent(Node start){
        Node iter=start;
        Node parent=null;
        if (iter.right==null){
            return null;
        }
        else {
            parent=iter;
            iter=iter.right;
            while(iter.left!=null){
                parent=iter;
                iter=iter.left;
            }
            return parent;  
        }
    }
    public boolean delete(Node toDelete){
        Node parent=null;
        Node iter=root;
        boolean isLeftChild=false;
        while(iter.val.getxCord()!=toDelete.val.getxCord()){
			parent = iter;
			if(iter.val.getxCord()>toDelete.val.getxCord()){
				isLeftChild = true;
				iter = iter.left;
			}else{
				isLeftChild = false;
				iter = iter.right;
			}
			if(iter ==null){
				return false;
			}
                        
		}
        //CASE 1
        if (iter.left==null && iter.right==null){
            if (iter==root) root=null;
            else if (isLeftChild) parent.left=null;
            else parent.right=null;
        }
        
        //CASE 2
        else if (iter.right==null){
           if (iter==root){
               root=iter.left;
               iter.left=null;
           }
           else if (isLeftChild) {
               parent.left=iter.left;
               iter.left=null;
           }
           else{
               parent.right=iter.left;
               iter.left=null;
           }
        }
        else if (iter.left==null){
            if (iter==root){
                root=iter.right;
                iter.right=null;
            }
            else if (isLeftChild){
                parent.left=iter.right;
                iter.right=null;
            }
            else{
                parent.right=iter.right;
                iter.right=null;
            }
        }
        
        //CASE 3
        else{
           if (iter.left.right==null){
               parent.left=iter.left;
               iter.left=null;
           }
           else{
            Node maxLeft = maxLeftSubtreeParent(iter);
            Node toReplace = maxLeft.right;
            iter.val=toReplace.val;
            maxLeft.right=toReplace.left;
            toReplace.left=null;
        }
           
    }
    return true;
    }    
    public void insert(Event value){
        Node toInsert = new Node(value);
        Node iter = root;
        if (iter==null){
            root=toInsert;
            return;
        }
        else{
            Node parent=iter;
            while (true){
                if (toInsert.val.getxCord()<=iter.val.getxCord()){
                    iter=iter.left;
                    if (iter==null){
                        parent.left=toInsert;
                        return;
                    }
                }
                else{
                    iter=iter.right;
                    if (iter==null){
                        parent.right=toInsert;
                        return;
                    }
                }
                parent=iter;
            }
        }  
    }
    public void inOrder(Node root){
        if(root!=null){
            inOrder(root.left);
            System.out.println(root.val);
            inOrder(root.right);
        }
    }
}
