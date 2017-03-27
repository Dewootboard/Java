/**
 * Created by jocke on 3/27/2017.
 */
public class Node {
    private Node left, right, up, down;
    private int x, y;

    public Node(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setLeft(Node left){
        this.left = left;
    }

    public void setRight(Node right){
        this.right = right;
    }

    public void setUp(Node up){
        this.up = up;
    }

    public void setDown(Node down){
        this.down = down;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getX(int x){
        if(this.x == x){
            return x;
        }
        if(up != null){
            return down.getX(x);
        }
        return -1;
    }

    public int getY(int y){
        if(this.y == y){
            return y;
        }
        if(left != null){
            return right.getY(y);
        }
        return -1;
    }
}
