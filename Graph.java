import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Graph {

    private ArrayList<Integer>[] adjacent;
    private int couleurs[];

    public Graph(String path) throws IOException {

        InputStream stream = new FileInputStream(path);
        Scanner scan = new Scanner(stream);

        int points;
        int p1, p2;

        points = scan.nextInt();
        adjacent = new ArrayList[points];
        couleurs = new int[points];
        for (int i=0; i<points; i++){
            adjacent[i] = new ArrayList<>();
            couleurs[i] = -1;
        }

        while (scan.hasNextLine()){
            p1 = scan.nextInt();
            p2 = scan.nextInt();
            adjacent[p1-1].add(p2-1);
            adjacent[p2-1].add(p1-1);
        }

        scan.close();
    }

    public Graph(ArrayList<Integer>[] ad){
        adjacent = ad;
        couleurs = new int[adjacent.length];
        Arrays.fill(couleurs, -1);
    }

    public boolean color(int k, int sommet){

        Integer[] avail_col = available_colors(k, sommet);  //O(avail_color)

        if (avail_col.length == 0) return false;    //pas de couleur possible

        ArrayList<Integer> liste_adj = adjacent[sommet];    //cst

        boolean res = true; //cst

        for (Integer color : avail_col) {   //O(k-1)
            couleurs[sommet] = color;   //cst
            for (int s_adj : liste_adj) {   //O(E)  max le nombre de vecteur
                if (couleurs[s_adj] == -1) res = color(k, s_adj);   //O(color)
                if (!res) break;    //cst
            }
            if (res) return true;   //cst
        }

        return false;   //cst
    }


    public boolean color(int k){
        return color(k, 0);
    }


    private Integer[] available_colors(int k, int sommet){

        ArrayList<Integer> avail = new ArrayList<>();
        ArrayList<Integer> taken = new ArrayList<>();

        for (int i : adjacent[sommet]){ //O(V)
            if (couleurs[i] != -1 && !taken.contains(couleurs[i])) taken.add(couleurs[i]);
        }

        for (int i = 0; i < k; i++){    //O(k)
            if (!taken.contains(i)) avail.add(i);
        }

        return avail.toArray(new Integer[0]);   //O(?)  cst?
    }


    public void Print_color(){
        System.out.println("coloration : ");
        for (int i = 0; i < couleurs.length; i++){
            System.out.println((i+1) + " " + couleurs[i]);
        }
    }

    public boolean solved(){
        for (int couleur : couleurs){
            if (couleur == -1) return false;
        }
        return true;
    }

    public void setAdjacent( ArrayList<Integer>[] adj){
        adjacent = adj;
    }

    public int[] getColors(){
        return couleurs;
    }

}
