/**
 * Creado por Carlos el 03/06/2017.
 */
public class Tree {
    Integer value = null;
    Tree right = null;
    Tree left = null;

    public Tree() {
    }

    public Tree(Integer value, Tree right, Tree left) {
        this.value = value;
        this.right = right;
        this.left = left;
    }

    public Tree(Tree right, Tree left) {
        this.right = right;
        this.left = left;
    }

    public Tree(Integer value) {
        this.value = value;
    }

    public int profundidad(){
        return calprof(this);
    }

    private int calprof(Tree tree){
        if (tree.getRight() == null){
            return 1;
        }
        return calprof(tree.getRight()) + 1;
    }

    public boolean isLeaf(){
        return this.right == null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Tree getRight() {
        return right;
    }

    public void setRight(Tree right) {
        this.right = right;
    }

    public Tree getLeft() {
        return left;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public String toString(){
        if (value!=null){
            return value.toString();
        }
        return getRight().toString() + " " + getLeft().toString();
    }
}
