
import java.util.LinkedList;
import java.util.List;

public class Bfs {

    public String current(Tile tile){
        String current = "(" + tile.getX() + "," + tile.getY() + ")";
        return current;
    }
    public static List<Tile> calculateBFS(Maze maze) {
        Bfs a = new Bfs();
        int count = 0;
        Tile currentTile = maze.getStartTile();
        List<Tile> currentPath = new LinkedList();

        List<Tile> tileQueue = new LinkedList<>();
        List<List<Tile>> pathQueue = new LinkedList<>();
        List<Tile> moves = new LinkedList<>();
        currentPath.add(currentTile);
        pathQueue.add(currentPath);
        tileQueue.add(currentTile);

        while (!currentTile.equals(maze.getGoalTile())) {
            if (moves.size() > 0) {
                moves.clear();
            }
            currentTile = tileQueue.get(0);
            currentPath = pathQueue.get(0);
            tileQueue.remove(0);
            pathQueue.remove(0);
            currentTile.visit();
            moves.addAll(maze.getAdjacentTiles(currentTile));

            for (int i = 0; i < moves.size(); i++) {
                if (moves.get(i).isTraversable() && !moves.get(i).isVisited() && !tileQueue.contains(moves.get(i))) {
                    tileQueue.add(moves.get(i));
                    List<Tile> tempPath = new LinkedList<>();
                    tempPath.addAll(currentPath);
                    tempPath.add(moves.get(i));
                    pathQueue.add(tempPath);
                }
            }
            count++;
            if(pathQueue.isEmpty()){
                System.out.println("Maze is unsolvable.");
                return null;
            }
        }
        for (Tile t : currentPath){
            System.out.println(a.current(t));
        }
        System.out.println("Moves taken: " + currentPath.size());
        System.out.println();
        System.out.println("Moves checked: " + count);
        return currentPath;
    }
}