
public class Tile {

    private int x, y, cost;
    boolean visited, current;

    public Tile(int x, int y, int cost) {
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.visited = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCost() {
        return cost;
    }

    public boolean isTraversable() {
        return cost > 0;
    }

    public boolean isGoal(int x, int y) {
        return this.x == x && this.y == y;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void visit() {
        visited = true;
    }

    @Override
    public boolean equals(Object object) {
        Tile tile = (Tile) object;
        if (tile.getX() == this.x && tile.getY() == this.y)
            return true;
        else
            return false;
    }
}
