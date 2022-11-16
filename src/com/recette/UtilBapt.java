package com.recette;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UtilBapt {

    public static int compterOccurrences(ArrayList<String> maChaine, String recherche)
    {
        int nb = 0;
        for (int i=0; i < maChaine.size()-1; i++)
        {
            if (maChaine.get(i).equals(recherche))
                nb++;
        }
        return nb;
    }

    public static ArrayList<String> removeDuplicates(List<String> list)
    {
        // Create a new ArrayList
        ArrayList<String> newList = new ArrayList<String>();

        // Traverse through the first list
        for (String element : list) {

            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }
        // return the new list
        return newList;
    }

    public static String askDataString(String question){
        System.out.println(question);
        Scanner sc = new Scanner(System.in);
//        String data = " ";
//        data = sc.nextLine();
        return sc.nextLine();
    }
    public static int askDataInt(String question, Integer min, Integer max) {
        Scanner sc = new Scanner(System.in);
        boolean dataOutOfRange;
        int data;
        do {
            dataOutOfRange = false;
            // Si min ou max renseigné => Ajouter l'intervalle possible a la fin de la question
            StringBuilder questionImproved = new StringBuilder(question);
            if (min != null) {
                if (max != null) {
                    questionImproved.append(" (entre ").append(min).append(" et ").append(max).append(")");
                } else {
                    questionImproved.append(" (minimum ").append(min).append(")");
                }
            } else if (max != null) {
                questionImproved.append(" (maximum ").append(max).append(")");
            }
            System.out.println(questionImproved);
            data = sc.nextInt();
            if (min != null && data < min) {
                dataOutOfRange = true;
                System.out.println("");
            } else if (max != null && data > max) {
                dataOutOfRange = true;
            }
        } while (dataOutOfRange);
        return data;
    }

    public static int[][] RemplirTableauInt2D(int[][] tab2D, int size1, int size2, int max) {
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                tab2D[i][j] = (int) (max * Math.random());
            }
        }
        return tab2D;
    }

    public static int[][][] RemplirTableauInt3D(int [][][] tab3D, int size1, int size2, int size3, int max)
    {
        for (int i = 0; i < size1; i++) {
            for (int j = 0; j < size2; j++) {
                for (int k = 0; k < size3; k++){
                    tab3D[i][j][k] = (int) (max * Math.random());
                }
            }
        }
        return tab3D;
    }

    public static void AfficherTableauInt1Dim(int[] tab1D){
        for (int i = 0; i < tab1D.length; i++) {
            if(i==0){
                System.out.printf("[");
                System.out.printf(tab1D[i]+" ,");
            }
            else if(i>0 && i<tab1D.length-1) System.out.printf(tab1D[i]+" ,");

            else{
                System.out.printf(String.valueOf(tab1D[i]));
                System.out.printf("]");
            }
        }
        System.out.println();
    }

    public static void AfficherArraylistInt(ArrayList<Integer> ListInt){
        for (int elem : ListInt){
            if(elem==ListInt.get(0)){
                System.out.printf("[");
                System.out.printf(elem+" ,");
            }
            else if(elem!=ListInt.get(0) && elem!=ListInt.get(ListInt.size()-1)){
                System.out.printf(elem+" ,");
            }
            else{
                System.out.printf(String.valueOf(elem));
                System.out.printf("]");
            }
        }
    }

    public static int[] CalculerMoyTabInt3DtoTab(int[][][] tab3D, int[] tabMoyFinal,  int size1, int size2,int size3){
        for (int i = 0; i < size1; i++) {
            int somme = 0;
            for (int j = 0; j < size2; j++) {
                for (int k = 0; k < size3; k++) {
                    somme += tab3D[i][j][k];
                }

            }
            tabMoyFinal[i] = somme / (size2 * size3);
        }
        return tabMoyFinal;
    }

    public static int CalculerMoyTabInt3DtoInt(int[][][] tab3D,  int size1, int size2,int size3){
        int moyFinal = 0;
        int[] tabMoyFinal = new int[size2*size3];
        int somme=0;
        for (int i = 0; i < size1; i++) {
            int somme1 = 0;
            for (int j = 0; j < size2; j++) {
                for (int k = 0; k < size3; k++) {
                    somme1 += tab3D[i][j][k];
                }

            }
            tabMoyFinal[i] = somme1 / (size2 * size3);
        }
        for (int i = 0; i < size1; i++) {
            somme += tabMoyFinal[i];
        }
        moyFinal = (int)(somme/tabMoyFinal.length);
        return moyFinal;
    }



}
