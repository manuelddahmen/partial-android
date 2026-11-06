/*
 * Licensed to End Users from Google Play Store and Apple App Store under the following terms:
 *
 * This app is provided "as is" without any warranties or guarantees of any kind, either express or implied. The developers and contributors of this app are not responsible for any damages, losses, or issues that may arise from the use of this app.
 * By downloading, installing, or using this app, you agree to the following terms:
 * 1. You may use this app for personal, or non-commercial purposes only or commercial purposes.
 * 2. You may not modify, distribute, or create derivative works of this app without
 *     prior written permission from the developers.
 * 3. You may not reverse engineer, decompile, or disassemble this app.
 *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 *
 * Copyright 2026-2026 Empty3 Benelux Trademark.
 */

/*
DBSCAN(D, eps, MinPts)
   C = 0
   pour chaque point P non visité des données D
      marquer P comme visité
      PtsVoisins = epsilonVoisinage(D, P, eps)
      si tailleDe(PtsVoisins) &lt; MinPts
         marquer P comme BRUIT
      sinon
         C++
         etendreCluster(D, P, PtsVoisins, C, eps, MinPts)
          
etendreCluster(D, P, PtsVoisins, C, eps, MinPts)
   ajouter P au cluster C
   pour chaque point P' de PtsVoisins 
      si P' n'a pas été visité
         marquer P' comme visité
         PtsVoisins' = epsilonVoisinage(D, P', eps)
         si tailleDe(PtsVoisins') >= MinPts
            PtsVoisins = PtsVoisins U PtsVoisins'
      si P' n'est membre d'aucun cluster
         ajouter P' au cluster C
          
epsilonVoisinage(D, P, eps)
 */
package one.empty3.androidFeature;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Vector;
import java.io.File;

public class Clusters extends one.empty3.androidFeature.ProcessFiles {
    public boolean process(File in, File out) {
        return false;

    }

    public static List<Vector> Data;
    public static List<Clusters> Clusters;
    public static List<Boolean> Pvisited;
    public static List<Boolean> clustered;
    public static List neighborpts;
    public static List neighbors;
    public static List noise;


    public static void read(int String) {

        String[] values;
        //System.out.println("Working Directory = " +
        // System.getProperty("user.dir"));

        Data = new ArrayList();
        Pvisited = new ArrayList();
        Double ve;

        try {
            BufferedReader in = new BufferedReader(new FileReader("wholesale.csv"));
            String line;
            Vector<Double> v = null;
            int j = 0;
            while ((line = in.readLine()) != null) {
                values = line.split(",");
                v = new Vector();
                for (int i = 0; i < values.length; i++) {
                    ve = Double.parseDouble(values[i]);

                    v.add(ve);

                }
                Data.add(v);
                //System.out.println(Data);
                Pvisited.add(false);
                //System.out.println(Pvisited.get(j));
                v = null;
                j++;
            }

            in.close();

        } catch (IOException ioException) {
        }
    }

    public static void DBSCAN(int esp, int minPts) {
        int c = 0;
        //System.out.println(c);
        Clusters = new <Clusters>ArrayList();
        neighborpts = new ArrayList();
        for (int i = 0; i < Data.size(); i++) {
            neighborpts.add(null);
        }
        noise = new ArrayList();
        for (int i = 0; i < Data.size(); i++) {
            //System.out.println("The size of the file is: "+ Data.size());
            if (!Pvisited.get(i)) {
                Pvisited.set(i, true);
                neighborpts.set(i, regionQuery(Data.get(i), esp));
                //System.out.println(neighborpts);
                int size = neighborpts.size();
                //System.out.println(minPts);
                if (size < minPts)
                    //System.out.println(noise);
                    noise.add(i);
                    //System.out.println(noise.get(i));
                else {
                    //System.out.println("HEy");
                    Clusters.addAll(Data.get(i));
                    //System.out.println(c);
                    c++;
                    //System.out.println("This is c" + c);
//            Clusters C = new Clusters(c);
//            C.setPoint(Data.get(c));
//            Clusters.add(C);
                    //System.out.println(size);
                    //C.printC().toString();//System.out.print(C.printC());
                    for (int j = 0; j < size; j++) {
                        //if P' is not visited
                        if (!Pvisited.get(neighborpts.indexOf(j))) {
                            Pvisited.set(j, true);
                            neighbors.add(regionQuery((Vector) neighborpts.get(j), esp));
                            //System.out.println(neighbors);
                            int nSize = neighbors.size();
                            //System.out.println(nSize);
                            if (nSize >= minPts) {
                                neighborpts.add(neighbors);
                            }
                        }
                        // if P' is not yet a member of any cluster
                        // add P' to cluster c
                        if (!clustered.get(neighborpts.indexOf(j))) {
                            int x = (int) neighborpts.get(j);
                            Clusters f = Clusters.get(c);
                            ((List<Integer>) f).add(x);
                        }
                    }
                }
            }
        }//end of the main for loop
    }


    public static double ecluediean(Vector center, Vector L) {
        Double result = (double) 0;
        for (int i = 0; i < center.size(); i++) {
            result += Math.pow(((double) center.get(i)) - (double) (L.get(i)), 2);
        }

        return Math.sqrt(result);
    }

    public static List regionQuery(Vector p, int eps) {
        List<Vector> n = new ArrayList();
        for (int i = 0; i < Data.size(); i++) {
            n.add(null);
        }
        double dis = 0;
        for (int i = 0; i < Data.size(); i++) {
            dis = ecluediean(p, Data.get(i));

            if (dis <= eps) {

                n.set(i, Data.get(i));
            }
        }
        //System.out.println(n);
        return n;
    }

    public static void main(String[] args) {
        int N = 3;
        read(1);

        DBSCAN(3, 5);


        //System.out.println(((List<Vector>) Clusters.get(0)).get(1));
    }
}
