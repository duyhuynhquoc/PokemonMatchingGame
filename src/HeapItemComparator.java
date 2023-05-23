import java.util.Comparator;

public class HeapItemComparator implements Comparator<HeapItem> {
    @Override
    public int compare(HeapItem a, HeapItem b) {
        if (a.d < b.d) {
            return -1;
        } else if (a.d > b.d) {
            return 1;
        } else {
            if (a.turn < b.turn) {
                return -1;
            } else if (a.turn > b.turn) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}