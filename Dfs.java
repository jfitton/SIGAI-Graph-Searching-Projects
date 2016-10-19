
import java.util.LinkedList;
import java.util.List;

/**
 * Finds a path, but goes one step further before returning path.
 */
public class Dfs {
    public  Tile nextMove(List<Tile> moves){
        for (Tile t : moves){
            if (t.isTraversable() && !t.isVisited()){
                return t;
            }
        }
        Tile x = new Tile(-1,-1, 100000);
        return x;
    }

    public static List<Tile> calculateDFS(Maze maze) {
        List<Tile> path = new LinkedList<>();
        Tile current = maze.getStartTile();
        List<Tile> moves = new LinkedList<>();
        Tile nextMove;
        int count = 1;
        Dfs d = new Dfs();
        path.add(current);
        while (current!=maze.getGoalTile()){
            moves.clear();
            if (path.size()>0){
                current = path.get(path.size()-1);
            }
            current.visit();
            moves.addAll(maze.getAdjacentTiles(current));
            nextMove = d.nextMove(moves);
            if (nextMove.getX() == -1){
                path.remove(current);
            }else {
                path.add(nextMove);
            }
            count++;
            if(path.isEmpty()){
                System.out.println("Maze is unsolvable.");
                return null;
            }

        }
        for (int i = 0; i < path.size(); i++){
            System.out.println("(" + path.get(i).getX() + "," + path.get(i).getY() + ")");
        }
        System.out.println("Moves taken: " + path.size());
        System.out.println();
        System.out.println("Moves checked: " + count);
        return path;
    }
}
