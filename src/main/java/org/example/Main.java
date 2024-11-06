package org.example;
import java.util.*;

public class Main {


    public static void clearConsole() {
        for (int i = 0; i < 8; i++) {
            System.out.println();
        }
    }

    public static void main(String[] args) {


            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);
            HashMap<Integer, Character> map = new HashMap<>();
            int counter=0;

            System.out.println("\n \t\t\t\t\t\t\t\t\t\t\t\t=====================================");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t|    BIENVENUE DANS BELL-FORD APP   |");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t=====================================");

            // Saisie du nombre de sommets et arcs
            System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\tLe nombre de sommets : ");
            int nbSommets = scanner.nextInt();

            BellmanFord bf = new BellmanFord(nbSommets);

            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\tLe nombre d'arcs : ");
            int nbArcs = scanner.nextInt();

            clearConsole();

            // Saisie des arcs
            System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t===== SAISIE DES ARCS DU GRAPHE =====");

            for (int i = 0; i < nbArcs; i++) {
                System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\tArc " + (i + 1) + " : ");
                System.out.print("\t\t\t\t\t\t\t\t\t\t\t\tSommet de départ : ");
                Character c1 = scanner.next().charAt(0);
                if (!map.containsValue(c1)) {
                    map.put(counter,c1);
                    counter++;
                }
                int u=-1;
                for (Map.Entry<Integer, Character> entry : map.entrySet()) {
                    if (entry.getValue().equals(c1)) {
                        u = entry.getKey();

                        break;
                    }
                }


                System.out.print("\t\t\t\t\t\t\t\t\t\t\t\tSommet de fin : ");
                Character c2 = scanner.next().charAt(0);
                if (!map.containsValue(c2)) {
                    map.put(counter,c2);
                    counter++;
                }
                int v=-1;
                for (Map.Entry<Integer, Character> entry : map.entrySet()) {
                    if (entry.getValue().equals(c2)) {
                        v = entry.getKey();

                        break;
                    }
                }

                System.out.print("\t\t\t\t\t\t\t\t\t\t\t\tPoids de l'arc : ");
                int poids = scanner.nextInt();

                bf.ajouterArc(u, v, poids);
            }
            bf.ajouterMap(map);
            while (true) {
                System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t=====================================");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t|       MENU DE BELL-FORD APP       |");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t=====================================");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t| 1. Calculer le plus court chemin  |");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t| 2. Quitter                        |");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t=====================================");
                System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\tEntrez votre choix : ");
                int choix = scanner.nextInt();

                if (choix == 2) {
                    break;
                }

                switch (choix) {
                    case 1:
                        // Saisie des sommets de départ et destination
                        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t===== SAISIE DU PLUS COURT CHEMIN =====");

                        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\tEntrez le sommet de départ : ");
                        Character c3 = scanner.next().charAt(0);
                        int source=-1;
                        for (Map.Entry<Integer, Character> entry : map.entrySet()) {
                            if (entry.getValue().equals(c3)) {
                                source = entry.getKey();

                                break;
                            }
                        }

                        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\tEntrez le sommet de destination : ");
                        Character c4 = scanner.next().charAt(0);
                        int destination=-1;
                        for (Map.Entry<Integer, Character> entry : map.entrySet()) {
                            if (entry.getValue().equals(c4)) {
                                destination = entry.getKey();

                                break;
                            }
                        }

                        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\tCalcul du plus court chemin...");
                        bf.bellmanFord(source, destination);
                        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t=====================================");
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t|   MERCI D'UTILISER BELL-FORD APP  |");
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t=====================================");
                        break;
                    default:
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tChoix invalide !");
                }
            }

        }


}