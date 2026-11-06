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

package one.empty3.androidFeature;

import java.util.ArrayList;

import java.util.List;

public class Cluster {

    public List<DataPoint> points;
    public DataPoint centroid;
    public int id;

    public Cluster(int id) {
        this.id = id;
        this.points = new ArrayList<DataPoint>();
        this.centroid = null;
    }

    public List<DataPoint> getPoints() {
        return points;
    }

    public void addPoint(DataPoint point) {
        points.add(point);
        point.setCluster(id);
    }

    public void setPoints(List points) {
        this.points = points;
    }

    public DataPoint getCentroid() {
        return centroid;
    }

    public void setCentroid(DataPoint centroid) {
        this.centroid = centroid;
    }

    public int getId() {
        return id;
    }

    public void clear() {
        points.clear();
    }

    public void plotCluster() {
        System.out.println("[Cluster: " + id + "]");
        System.out.println("[Centroid: " + centroid + "]");
        System.out.println("[Points: \n");
        for (DataPoint p : points) {
            System.out.println(p);
        }
        System.out.println("]");
    }

}