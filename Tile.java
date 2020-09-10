import java.util.Arrays;

public class Tile {
    private String box;
    private boolean empty;

    public Tile() {
        box = "";
        empty = true;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setTile(String move) {
        this.box = move;
        empty = false;
    }

    public String getOwner() {
        return box;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Tile other = (Tile) o;
        return (!(this.empty) && !(other.empty)) && (this.box.equals(other.box));
    }

    @Override
    public String toString() {
        return box;
    }


    public static void main(String[] args) {
        Tile hello = new Tile();
        hello.setTile("yo");
        System.out.println(hello);
    }
}