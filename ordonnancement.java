import java.io.FileNotFoundException;

public class ordonnancement {

    public static void main(String[] args) throws FileNotFoundException {
        Ordonnanceur o = new Ordonnanceur(args[0], Integer.parseInt(args[1]));
        o.order();
        if (o.solved()) o.Print_color();
        else System.out.println("no coloration found");
    }

}
