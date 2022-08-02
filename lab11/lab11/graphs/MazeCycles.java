package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private boolean isFound = false;
    private int[] nodeTo;


    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        nodeTo = new int[m.N() * m.N()];
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        dfs(0,0);
    }

    // Helper methods go here
    private void dfs(int v, int u) {
        marked[v] = true;
        announce();
        for (int w: maze.adj(v)) {
            if (!marked[w]) {
                nodeTo[w] = v;
                dfs(w, v);
            } else if (w != u) {
                // Found circle
                edgeTo[w] = v;
                announce();
                for (int x = v; x != w; x = nodeTo[x]) {
                    edgeTo[x] = nodeTo[x];
                    announce();
                }
                isFound = true;
            }
            if (isFound) {
                return;
            }
        }
    }
}

