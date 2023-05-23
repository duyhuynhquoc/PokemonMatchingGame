import java.util.PriorityQueue;
import java.util.Scanner;

public class PokemonMatchingGame {
    private static int[][] board = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 3, 0},
            {0, 4, 0, 0, 0, 2, 0},
            {0, 1, 0, 2, 0, 4, 0},
            {0, 5, 3, 4, 5, 1, 0},
            {0, 0, 0, 0, 0, 0, 0},
    };

    private static int N = 5;
    private static int M = 5;

    private static boolean[][] visited = new boolean[10][10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x1 = -1;
        int y1 = -1;
        int x2 = -1;
        int y2 = -1;

        printBoard();

        while (true) {
            System.out.print("Start row: ");
            sc = new Scanner(System.in);
            x1 = sc.nextInt();

            System.out.print("Start Column: ");
            sc = new Scanner(System.in);
            y1 = sc.nextInt();

            System.out.print("End row: ");
            sc = new Scanner(System.in);
            x2 = sc.nextInt();

            System.out.print("End column: ");
            sc = new Scanner(System.in);
            y2 = sc.nextInt();

            visited = new boolean[10][10];
            IntPair res = dijkstra(x1, y1, x2, y2);
            int result = res.x;
            int turn = res.y;
            System.out.println("Path length: " + result + ", Turn: " + turn);
            if (result < 1000000000) {
                board[x1][y1] = 0;
                board[x2][y2] = 0;
            }
            printBoard();
        }
    }

    private static void printBoard() {
        for (int i = 1; i <= 5; ++i) {
            for (int j = 1; j <= 5; ++j) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static IntPair dijkstra(int x1, int y1, int x2, int y2) {
        PriorityQueue<HeapItem> pq = new PriorityQueue<>(new HeapItemComparator());

        int[][] d = new int[105][105];


        for (int i = 0; i <= N + 1; ++i) {
            for (int j = 0; j <= M + 1; ++j) {
                d[i][j] = 1000000000;
            }
        }

        d[x1][y1] = 0;

        pq.add(new HeapItem(d[x1][y1], null, 0, new IntPair(x1, y1)));

        while (!pq.isEmpty()) {
            IntPair u = pq.element().node;
            Direction currentDirection = pq.element().currentDirection;
            int turn = pq.element().turn;
            int du = pq.element().d;

            pq.remove();

//            if (turn > 2) continue;
            if (du != d[u.x][u.y]) continue;

//            System.out.println(u.x + " " + u.y + " " + currentDirection + " " + turn);
            if (u.x == x2 && u.y == y2) return new IntPair(d[x2][y2], turn);


            for (int i = -1; i <= 1; ++i) {
                for (int j = -1; j <= 1; ++j) {
                    if (i * j != 0 || i + j == 0) continue;

                    int x = u.x + i;
                    int y = u.y + j;

                    if (x < 0 || y < 0 || x > M + 1 || y > N + 1) continue;

                    if (!(x == x2 && y == y2) && board[x][y] > 0) continue;

                    Direction nextDirection = direction(u.x, u.y, x, y);
                    if (nextDirection != currentDirection && turn >= 2) continue;

                    if (d[x][y] > du + 1) {
                        d[x][y] = du + 1;

                        if (currentDirection != null && nextDirection != currentDirection) {
                            pq.add(new HeapItem(d[x][y], nextDirection, turn + 1, new IntPair(x, y)));
                        } else {
                            pq.add(new HeapItem(d[x][y], nextDirection, turn, new IntPair(x, y)));
                        }
                    }
                }
            }
        }

        return new IntPair(d[x2][y2], 1000);
    }

    private static Direction direction(int x1, int y1, int x2, int y2) {
        if (x2 == x1 - 1) return Direction.UP;
        if (x2 == x1 + 1) return Direction.DOWN;
        if (y2 == y1 - 1) return Direction.LEFT;
        if (y2 == y1 + 1) return Direction.RIGHT;
        return null;
    }


}
