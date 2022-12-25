package ru.vsu.cs.Grushevskaya.third;

import ru.vsu.cs.Grushevskaya.math.Vector3;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class PolyLine3D {
    private List<Vector3> points;
    private boolean closed;

    public PolyLine3D(Collection<Vector3> points, boolean closed) {
        this.points = new LinkedList<Vector3>(points);
        this.closed = closed;
    }

    public boolean isClosed() {
        return closed;
    }

    public List<Vector3> getPoints() {
        return points;
    }

    public float avgZ() {
        if (points == null || points.size() == 0)
            return 0;
        float sum = 0;
        for (Vector3 v : points)
            sum += v.getZ();
        return sum / points.size();
    }
    
}
