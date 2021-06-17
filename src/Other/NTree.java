package Other;

import Interfaces.NTreeInterface;

import java.util.ArrayList;
import java.util.Scanner;

public class NTree implements NTreeInterface {

    public TreeNode head = null;
    int level = 0;

    public NTree() {
        head = new TreeNode("Categories");
    }

    @Override
    public String getBase(){
        StringBuilder sb = new StringBuilder();
        //return this.getChilds("Categories");
        return head.getChildsStringWithNumber(sb);
    }

    @Override
    public String getChilds(String elements){
        StringBuilder sb = new StringBuilder();

        Scanner sc = new Scanner(elements);
        sc.useDelimiter(" >> ");
        TreeNode iter = head;

        while (sc.hasNext()) {
            String cat = sc.next();
            for (int i = 0 ; i< iter.children.size() ; i++){
                if (iter.children.get(i).value.equals(cat)){

                    iter = iter.children.get(i);
                    break;
                }
            }
        }

        iter.getChildsString(sb);

        return sb.toString();
    }

    @Override
    public String getChildsForReading(String elements){
        StringBuilder sb = new StringBuilder();

        Scanner sc = new Scanner(elements);
        sc.useDelimiter(" >> ");
        TreeNode iter = head;

        while (sc.hasNext()) {
            String cat = sc.next();
            for (int i = 0 ; i< iter.children.size() ; i++){
                if (iter.children.get(i).value.equals(cat)){

                    iter = iter.children.get(i);
                    break;
                }
            }
        }

        iter.getChildsStringForReading(sb);

        return sb.toString();
    }

    @Override
    public String getChildsWithNumbers(String elements){
        StringBuilder sb = new StringBuilder();

        Scanner sc = new Scanner(elements);
        sc.useDelimiter(" >> ");
        TreeNode iter = head;

        while (sc.hasNext()) {
            String cat = sc.next();
            for (int i = 0 ; i< iter.children.size() ; i++){
                if (iter.children.get(i).value.equals(cat)){

                    iter = iter.children.get(i);
                    break;
                }
            }
        }

        iter.getChildsStringWithNumber(sb);

        return sb.toString();
    }

    @Override
    public String add(String elements){
        boolean flag= false;

        Scanner sc = new Scanner(elements);
        sc.useDelimiter(" >> ");

        TreeNode iter = head;

        String previtem;

        while (sc.hasNext()){
            String cat = sc.next();
            previtem = cat;
            flag = false;
            iter.addChild(cat);

            for (int i = 0 ; i< iter.children.size() ; i++){
                if (iter.children.get(i).value.equals(cat)){
                    flag = true;

                    iter = iter.children.get(i);
                    break;
                }
            }

        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(head,sb);

        return sb.toString();
    }

    public String toString(TreeNode temp, StringBuilder sb) {
        sb.append(temp.value + "\n");

        for (int i = 0; i < temp.children.size() ; i++){
            toString(temp.children.get(i), sb);
        }

        return null;
    }

    public static class TreeNode{
        String value;
        ArrayList<TreeNode> children = new ArrayList<>();

        TreeNode(){
            value = null;
        }

        TreeNode(String value){
            this.value = value;
        }

        TreeNode(String value, ArrayList<TreeNode> children){
            this.value = value;
            this.children = children;
        }

        boolean hasChild(){
            return children.size() > 0;
        }

        void addChild(String value){
            for (int i = 0 ; i< children.size() ; i++){
                if (children.get(i).value.equals(value))
                    return;
            }

            children.add(new TreeNode(value));

        }

        public String getChildsString(StringBuilder sb) {

            if (hasChild()){
                for (int i = 0 ; i< children.size() ; i++){
                    sb.append(children.get(i).value +"\n");
                }
            }

            return sb.toString();
        }

        public String getChildsStringForReading(StringBuilder sb) {

            if (hasChild()){
                for (int i = 0 ; i< children.size() ; i++){
                    sb.append(children.get(i).value +";");
                }
            }

            return sb.toString();
        }

        public String getChildsStringWithNumber(StringBuilder sb) {

            if (hasChild()){
                for (int i = 0 ; i< children.size() ; i++){
                    sb.append((i+1)+"-> "+children.get(i).value +"\n");
                }
            }

            return sb.toString();
        }
    }

}
