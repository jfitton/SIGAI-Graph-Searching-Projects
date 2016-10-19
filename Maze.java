
import java.util.ArrayList;
import java.util.List;

public class Maze {

    private Tile[][] maze;
    private int height, width, goalX, goalY, startX, startY;

    enum GraphSearchMode {
        BFS, DFS
    }

    public Maze(int height, int width) {
        this.height = height;
        this.width = width;
        maze = new Tile[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int traversable = Math.random() > .3 ? 1 : -1;
                // int cost = (int) (traversable * Math.random() * 10.0);
                int cost = traversable;
                maze[y][x] = new Tile(x, y, cost);
            }
        }
        while (startX == goalX && startY == goalY) {
            this.goalX = (int) (Math.random() * width);
            this.goalY = (int) (Math.random() * height);
            this.startX = (int) (Math.random() * width);
            this.startY = (int) (Math.random() * height);
        }
        maze[goalY][goalX] = new Tile(goalX, goalY, 1);
        maze[startY][startX] = new Tile(startX, startY, 1);
    }

    public Maze (int[][] mazePlan){
        maze = new  Tile[mazePlan.length][mazePlan[1].length];
        this.height = maze.length;
        this.width = maze.length;
        for(int i = 0; i < mazePlan.length; i++){
            for (int j = 0; j < mazePlan[i].length; j++){
                switch (mazePlan[i][j]){
                    case 0:
                        maze[i][j] = new Tile(i ,j, 1);
                        break;
                    case 1:
                        maze[i][j] = new Tile(i ,j, -1);
                        break;
                    case 2:
                        maze[i][j] = new Tile(i ,j, 1);
                        this.startX = i;
                        this.startY = j;
                        maze[startY][startX] = new Tile(startX, startY, 1);
                        break;
                    case 3:
                        maze[i][j] = new Tile(i ,j, 1);
                        this.goalX = i;
                        this.goalY = j;
                        maze[goalY][goalX] = new Tile(goalX, goalY, 1);
                        break;
                }

            }
            printMaze();
        }
    }

    public Tile getTile(int x, int y) {
        if (x < width && x >= 0 && y < height && y >= 0)
            return maze[y][x];
        return null;
    }

    public Tile getStartTile() {
        return getTile(startX, startY);
    }

    public Tile getGoalTile() {
        return getTile(goalX, goalY);
    }

    public void printMaze() {
        printMaze(getStringMaze());
    }

    private void printMaze(String[][] strMaze) {
        for (String[] arr : strMaze) {
            for (String str : arr) {
                System.out.print(str);
            }
            System.out.println();
        }
    }

    private String[][] getStringMaze() {
        String[][] strMaze = new String[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Tile tile = maze[y][x];
                String result;
                if (startX == tile.getX() && startY == tile.getY()) {
                    result = "S";
                } else if (tile.isGoal(goalX, goalY)) {
                    result = "E";
                }else if (tile.isVisited()){
                    result = "v";
                } else if (tile.isTraversable()) {
                    result = "\u25A1";
                } else {
                    result = "x";
                }
                strMaze[y][x] = result;
            }
        }
        return strMaze;
    }

    public void printShortestPath(GraphSearchMode mode) {
        List<Tile> path = calculateShortestPath(mode);
        if (path == null) {
            // No path, print maze
//            printMaze(getStringMaze());
            return;
        }
        String[][] strMaze = getStringMaze();
        for (Tile tile : path) {
            strMaze[tile.getY()][tile.getX()] = "\u25a0";
        }
//        printMaze(strMaze);
    }

    public List<Tile> getAdjacentTiles(Tile tile) {
        return getAdjacentTiles(tile.getX(), tile.getY());
    }

    public List<Tile> getAdjacentTiles(int x, int y) {
        ArrayList<Tile> adjTiles = new ArrayList<Tile>();
        Tile up = getTile(x, y + 1);
        if (up != null)
            adjTiles.add(up);
        Tile down = getTile(x, y - 1);
        if (down != null)
            adjTiles.add(down);
        Tile left = getTile(x - 1, y);
        if (left != null)
            adjTiles.add(left);
        Tile right = getTile(x + 1, y);
        if (right != null)
            adjTiles.add(right);
        return adjTiles;
    }

    public List<Tile> calculateShortestPath(GraphSearchMode mode) {
        switch (mode) {
            case DFS:
                return calculateDFS();
            default:
                return calculateBFS();
        }
    }

    private List<Tile> calculateBFS() {
        return Bfs.calculateBFS(this);
    }

    private List<Tile> calculateDFS() {
        return Dfs.calculateDFS(this);
    }
}
