public class Coordinate {
    public int i;
    public int j;

    public Coordinate() {
    }
    public Coordinate(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public boolean equal(Coordinate other) {
        return i == other.i && j == other.j;
    }

    public static double distance(Coordinate a, Coordinate b) {
        return Math.sqrt(Math.pow((a.i-b.i),2) + Math.pow((a.j-b.j),2));
    }
}
