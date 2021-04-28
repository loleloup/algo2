import java.io.IOException;

public class coloration {

    public static void main(String[] args) throws IOException {
        Graph g = new Graph(args[0]);
        g.color(Integer.parseInt(args[1]));
        if (g.solved()) g.Print_color();
        else System.out.println("no solution found");
    }
}
