
import java.util.Scanner;

public class Main {
    static Maze maze;
    final static int DEFAULT_HEIGHT = 2000;
    final static int DEFAULT_WIDTH = 2000;
    final static Maze.GraphSearchMode DEFAULT_GSM = Maze.GraphSearchMode.BFS;

    public static void main(String[] args) {
        int width = DEFAULT_WIDTH;
        int height = DEFAULT_HEIGHT;
        Maze.GraphSearchMode mode = DEFAULT_GSM;
        Scanner in = new Scanner(System.in);
        System.out.println("BFS or DFS?");
        String input = in.next().toLowerCase();
        switch(input) {
            case "bfs":
                mode = Maze.GraphSearchMode.BFS;
                break;
            case "dfs":
                mode = Maze.GraphSearchMode.DFS;
                break;
        }



        int[][] mazePlan = {{2,0,0,0,0,0},
                            {1,1,1,1,1,0},
                            {0,0,0,0,0,0},
                            {0,1,1,1,1,1},
                            {0,0,0,0,0,0},
                            {1,1,1,1,1,3}};

        maze = new Maze(height, width);
//        maze = new Maze(mazePlan);
        maze.printMaze();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        maze.printShortestPath(mode);
    }

    public static Maze.GraphSearchMode getGSM(String mode) {
        return Maze.GraphSearchMode.values()[Integer.parseInt(mode)];
    }
}