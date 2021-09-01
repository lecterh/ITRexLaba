import java.util.LinkedList;
import java.util.Queue;

public class AlgorithmLee {

    private int m;
    private int n;

    public AlgorithmLee(int m, int n) {
        this.m = m;
        this.n = n;
    }

    private boolean isValid(char[][] mat, boolean[][] visited, int row, int col) {
        return ((row >= 0) && (row < m)) && ((col >= 0) && (col < n)) &&
                (mat[row][col] == '.' || mat[row][col] == '2') && (!visited[row][col]);
    }

    public int bfs(char[][] matrix, int i, int j, int x, int y) {
        int[] row = {-1, 0, 0, 1};
        int[] col = {0, -1, 1, 0};

        boolean[][] visited = new boolean[m][n];
        Queue<Node> q = new LinkedList<>();
        visited[i][j] = true;
        q.add(new Node(i, j, 0));
        int minimum_distance = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Node node = q.poll();
            i = node.x;
            j = node.y;
            int dist = node.distance;
            if (i == x && j == y) {
                minimum_distance = dist;
                break;
            }

            for (int k = 0; k < 4; k++) {
                if (isValid(matrix, visited, i + row[k], j + col[k])) {
                    visited[i + row[k]][j + col[k]] = true;
                    Node n = new Node(i + row[k], j + col[k], dist + 1);
                    q.add(n);
                }
            }
        }

        if (minimum_distance == Integer.MAX_VALUE) {
            throw new RuntimeException("Destination cannot be reached");
        }
        return minimum_distance;
    }

    private class Node {
        private int x;
        private int y;
        private int distance;

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.distance = dist;
        }
    }


}
