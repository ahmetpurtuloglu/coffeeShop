package tr.com.priper.coffeeshop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Tree<T> implements Serializable {
    private T data = null; // data stored on lowest tree (products or categories)
    private List<Tree> children = new ArrayList<>(); // child trees
    private Tree parent = null; // parent tree

    public Tree(T data) {
        this.data = data;
    }

    private void setParent(Tree parent) {
        this.parent = parent;
    }

    public Tree getParent() {
        return parent;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean hasParent() {
        if (parent == null) {
            return false;
        }
        return true;
    }

    public boolean hasSubcategories() {
        if (children.isEmpty()) {
            return false;
        }
        return true;
    }

    public void addChild(Tree child) {
        child.setParent(this);
        this.children.add(child);
    }

    public Tree<T> addChild(T data) {
        Tree<T> newChild = new Tree<>(data);
        this.addChild(newChild);
        return newChild;
    }

    public Tree<Category> findChild(String childName) {
        Tree result = null;
        for (Tree tree : children) {
            if (((Category) tree.getData()).getName().equals(childName)) {
                result = tree;
            }
        }
        return result;
    }

    public void deleteChild(String childName) {
        Tree toDelete = findChild(childName);
        deleteChild(toDelete);
    }

    public void deleteChild(Tree child) {
        Tree toDelete = null;
        for (Tree tree : children) {
            if (tree.equals(child)) {
                toDelete = child;
            }
        }
        children.remove(toDelete);
    }

    public int numberOfChildren(boolean includeProducts) {
        if (includeProducts) {
            if (this.hasSubcategories() == false) {
                Category category = (Category) data;
                return category.numberOfProducts();
            }
        }
        return children.size();
    }

}