package org.example;

import java.util.HashMap;

public class BellmanFord {

    private int[][] graph;   // Matrice d'adjacence pour représenter le graphe. Un tableau deux dimensions permettant de mieux représenter le graphe
    private int nbSommets;   // Nombre de sommets du graphe
    private HashMap<Integer, Character> map ;
    private static final int INFINI = 99999; // Valeur pour représenter l'infini,


    public BellmanFord(int nbSommets) {
        this.nbSommets = nbSommets;
        graph = new int[nbSommets][nbSommets];
        map = new HashMap<>();

        // Initialisation de la matrice d'adjacence
        for (int i = 0; i < nbSommets; i++) {
            for (int j = 0; j < nbSommets; j++) {
                graph[i][j] = (i == j) ? 0 : INFINI; // Pas de coût pour aller à soi-même, infini sinon
            }
        }
    }

    public void ajouterArc(int u, int v, int poids) {
        graph[u][v] = poids; // Ajout d'un arc orienté avec un poids
    }

    public void ajouterMap(HashMap<Integer, Character> map) {
        this.map = map;
    }

    public String afficherChemin(int[] predecesseur, int j) {
        String chemin = "";
        if (predecesseur[j] == -1) {
            chemin += map.get(j);
            return chemin;
        }
        chemin += afficherChemin(predecesseur, predecesseur[j]) + " -> " + map.get(j);
        return chemin;
    }

    public void bellmanFord(int source, int destination) {
        int[] distance = new int[nbSommets];
        int[] predecesseur = new int[nbSommets];

        // Initialisation des distances
        for (int i = 0; i < nbSommets; i++) {
            distance[i] = INFINI;
            predecesseur[i] = -1;
        }
        distance[source] = 0;

        // Relaxation des arêtes (nbSommets - 1) fois
        for (int k = 0; k < nbSommets - 1; k++) {
            for (int u = 0; u < nbSommets; u++) {
                for (int v = 0; v < nbSommets; v++) {
                    if (graph[u][v] != INFINI && distance[u] + graph[u][v] < distance[v]) {
                        distance[v] = distance[u] + graph[u][v];
                        predecesseur[v] = u;
                    }
                }
            }
        }

        // Détection des cycles négatifs
        for (int u = 0; u < nbSommets; u++) {
            for (int v = 0; v < nbSommets; v++) {
                if (graph[u][v] != INFINI && distance[u] + graph[u][v] < distance[v]) {
                    System.out.println("Le graphe contient un cycle absorbant.");
                    return;
                }
            }
        }

        // Affichage du plus court chemin
        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\tLe plus court chemin de " + source  + " à " + destination + " est : ");
        if (distance[destination] == INFINI) {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\tAucun chemin n'existe entre " + source  + " et " + destination );
        } else {
            System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t" + afficherChemin(predecesseur, destination));
            System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\tLa distance totale est : " + distance[destination]);
        }
    }




}
