package alda.tree;

/**
 *
 * Detta är den enda av de tre klasserna ni ska göra några ändringar i. (Om ni
 * inte vill lägga till fler testfall.) De ändringar som är tillåtna är dock
 * begränsade av följande:
 * <ul>
 * <li>Ni får INTE lägga till några fler instansvariabler.
 * <li>Ni får INTE lägga till några statiska variabler.
 * <li>Ni får INTE använda några loopar någonstans.
 * <li>Ni FÅR lägga till fler metoder, dessa ska då vara privata.
 * </ul>
 *
 * @author henrikbe
 *
 * @param <T>
 */


public class BinarySearchTreeNode<T extends Comparable<T>> {

    private T data;
    private BinarySearchTreeNode<T> left;
    private BinarySearchTreeNode<T> right;

    public BinarySearchTreeNode(T data) {
        this.data = data;
    }

    public boolean add(T data) {

        int compare = data.compareTo(this.data);

        if (compare < 0) {
            if(left == null) {
                left = new BinarySearchTreeNode<>(data);
                return true;
            }
            return left.add(data);
        }
        if (compare > 0) {
            if (right == null) {
                right = new BinarySearchTreeNode<>(data);
                return true;
            }
            return right.add(data);
        }
        return false;
    }

    private T findMin() {

        if(left != null && data.compareTo(left.data) > 0){
            return left.findMin();
        }
        return this.data;
    }

    private T findMax(){

        if(right != null && data.compareTo(right.data) < 0){
            return right.findMax();
        }
        return  this.data;
    }

    public BinarySearchTreeNode<T> remove(T data) {

        int compare = data.compareTo(this.data);

        if(compare == 0){
            return remove();
        }else if(compare < 0 && left != null){
            left = left.remove(data);
        }else if(compare > 0 && right != null){
            right = right.remove(data);
        }
        return this;
    }

    private BinarySearchTreeNode<T> remove(){

        if(left == null && right == null){
            return null;
        }
        if(right != null){
            T min = right.findMin();
            right = right.remove(min);
            data = min;
        }
        else{
            T max = left.findMax();
            left = left.remove(max);
            data = max;
        }
        return this;
    }

    public boolean contains(T data) {

        int compare = data.compareTo(this.data);

        if (compare == 0) {
            return true;
        }
        if (compare < 0 && left != null) {
            return left.contains(data);
        }else if (compare > 0 && right != null) {
            return right.contains(data);
        }
        return false;
    }

    public int size() {

        if(left != null && right == null) {
            return 1 + left.size();
        }else if(left != null){
            return 1 + left.size() + right.size();
        }else if(right != null){
            return 1 + right.size();
        }
        return 1;
    }

    public int depth() {

        int leftDepth = 0;
        int rightDepth = 0;

        if(left == null && right == null) {
            return 0;
        }

        if(left != null){
            leftDepth += left.depth() + 1;
        }
        if(right != null){
            rightDepth += right.depth() + 1;
        }
        if(leftDepth > rightDepth){
            return leftDepth;
        }else{
            return rightDepth;
        }
    }

    public String toString() {

        String str = "" + data;

        if(left == null && right == null){
            return str;
        }
        if(left != null && right == null){
            return left.toString() + ", " + str;
        }
        if(left != null){
            return left.toString() + ", " + str + ", " + right.toString();
        }
        return str + ", " + right.toString();
    }
}