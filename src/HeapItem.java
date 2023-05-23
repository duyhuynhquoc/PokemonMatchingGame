public class HeapItem {
    int d;
    Direction currentDirection;
    int turn;
    IntPair node;

    public HeapItem(int d, Direction currentDirection, int turn, IntPair node) {
        this.d = d;
        this.currentDirection = currentDirection;
        this.turn = turn;
        this.node = node;
    }
}
