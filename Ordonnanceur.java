import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Ordonnanceur {

    private int computers;
    private Graph g;
    private float taches[][];


    Ordonnanceur(String path, int computer) throws FileNotFoundException {

        computers = computer;

        InputStream stream = new FileInputStream(path);
        Scanner scan = new Scanner(stream);

        int total;
        float start, end;
        int task;

        total = scan.nextInt();
        scan.nextLine();
        ArrayList<Integer>[] adjacent = new ArrayList[total];
        taches = new float[total][2];
        for (int i=0; i<total; i++){
            adjacent[i] = new ArrayList<>();
        }
        String line;
        while (scan.hasNextLine()){
            line = scan.nextLine();

            String[] split = line.split("\s");  //split autour des espaces
            task = Integer.parseInt(split[0]);
            start = Float.parseFloat(split[1]);
            end = Float.parseFloat(split[2]);


            for (int i = 0; i < taches.length; i++){    //vérifie les collisions entre taches deja ajoutées

                if ((start < taches[i][1] & start > taches[i][0] ) | (end > taches[i][0] & end < taches[i][1])){    //si collision, on ajoute une arete entre les deux taches
                    adjacent[i].add(task-1);
                    adjacent[task-1].add(i);
                }

            }

            taches[task-1][0] = start;
            taches[task-1][1] = end;
        }

        g = new Graph(adjacent);

        scan.close();
    }

    public void order(){
        g.color(computers, 0);
    }

    public boolean solved(){
        return g.solved();
    }

    public void Print_color(){
        g.Print_color();
    }

}
