package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

        boolean flag_replay , flag_invalid  ;


        do {
            flag_replay = false;
            // Reset these variables for each new graph
            HashMap<Integer, Character> map = new HashMap<>();
            BellmanFord bf = null;
            int counter = 0;  // Reset counter here
            do {
                flag_invalid = false;

                clearConsole();
                System.out.println("\n \t\t\t\t\t\t\t\t\t\t\t\t=====================================");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t|    BIENVENUE DANS BELL-FORD APP   |");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t=====================================");


                System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t=====================================");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t|       MENU DE BELL-FORD APP       |");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t=====================================");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t| 1. Saisie Manuelle                |");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t| 2. Importer un fichier            |");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t| 3. Quitter                        |");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t=====================================");
                System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\tEntrez votre choix : ");
                int choix = scanner.nextInt();


                switch (choix) {
                    case 1:
                        // Saisie du nombre de sommets et arcs
                        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\tLe nombre de sommets : ");
                        int nbSommets = scanner.nextInt();

                        bf = new BellmanFord(nbSommets);

                        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\tLe nombre d'arcs : ");
                        int nbArcs = scanner.nextInt();


                        // Saisie des arcs
                        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t===== SAISIE DES ARCS DU GRAPHE =====");

                        for (int i = 0; i < nbArcs; i++) {
                            System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\tArc " + (i + 1) + " : ");
                            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\tSommet de départ : ");
                            Character c1 = scanner.next().charAt(0);
                            if (!map.containsValue(c1)) {
                                map.put(counter, c1);
                                counter++;
                            }
                            int u = -1;
                            for (Map.Entry<Integer, Character> entry : map.entrySet()) {
                                if (entry.getValue().equals(c1)) {
                                    u = entry.getKey();

                                    break;
                                }
                            }


                            System.out.print("\t\t\t\t\t\t\t\t\t\t\t\tSommet de fin : ");
                            Character c2 = scanner.next().charAt(0);
                            if (!map.containsValue(c2)) {
                                map.put(counter, c2);
                                counter++;
                            }
                            int v = -1;
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
                        break;
                    case 2:

                        System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\tEntrez le chemin du fichier texte : \n\t\t\t\t\t\t\t\t\t\t\t\t");
                        String filePath = scanner.next();

                        // Lecture du fichier
                        try {
                            BufferedReader br2 = new BufferedReader(new FileReader(filePath));
                            int nbSommets2 = Integer.parseInt(br2.readLine());  // Lecture du nombre de sommets
                            bf = new BellmanFord(nbSommets2);

                            int nbArcs2 = Integer.parseInt(br2.readLine());  // Lecture du nombre d'arcs

                            clearConsole();

                            // Lecture des arcs depuis le fichier
                            System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t===== ARCS DU GRAPHE =====");

                            for (int i = 0; i < nbArcs2; i++) {
                                String[] arcDetails = br2.readLine().split(" ");
                                char sommetDepart = arcDetails[0].charAt(0);
                                char sommetArrive = arcDetails[1].charAt(0);
                                int poids = Integer.parseInt(arcDetails[2]);

                                if (!map.containsValue(sommetDepart)) {
                                    map.put(counter, sommetDepart);
                                    counter++;
                                }
                                int u = -1;
                                for (Map.Entry<Integer, Character> entry : map.entrySet()) {
                                    if (entry.getValue().equals(sommetDepart)) {
                                        u = entry.getKey();
                                        break;
                                    }
                                }

                                if (!map.containsValue(sommetArrive)) {
                                    map.put(counter, sommetArrive);
                                    counter++;
                                }
                                int v = -1;
                                for (Map.Entry<Integer, Character> entry : map.entrySet()) {
                                    if (entry.getValue().equals(sommetArrive)) {
                                        v = entry.getKey();
                                        break;
                                    }
                                }

                                bf.ajouterArc(u, v, poids);
                            }
                            bf.ajouterMap(map);
                            br2.close();  // Fermeture du fichier
                        } catch (IOException e) {
                            System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
                        }
                        break;
                    case 3:
                        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t=====================================");
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t|   MERCI D'UTILISER BELL-FORD APP  |");
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t=====================================");
                        return;

                    default:
                        flag_invalid = true;
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tChoix invalide !");
                        break;
                }

            }while (flag_invalid);






            do{
                flag_invalid=false;
                System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t=====================================");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t|       MENU DE BELL-FORD APP       |");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t=====================================");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t| 1. Calculer le plus court chemin  |");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t| 2. Recommencer                    |");
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t=====================================");
                System.out.print("\n\t\t\t\t\t\t\t\t\t\t\t\tEntrez votre choix : ");
                int choix2 = scanner.nextInt();



                switch (choix2) {
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
                        flag_invalid=true;
                        break;
                    case 2:
                        flag_replay = true;
                        bf=null;
                        break;
                    default:
                        flag_invalid=true;
                        System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tChoix invalide !");
                }
            }while(flag_invalid);
        }while (flag_replay);
    }


}
