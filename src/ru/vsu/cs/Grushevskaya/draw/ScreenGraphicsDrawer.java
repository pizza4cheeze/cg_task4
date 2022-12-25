package ru.vsu.cs.Grushevskaya.draw;

import ru.vsu.cs.Grushevskaya.screen.ScreenConverter;
import ru.vsu.cs.Grushevskaya.third.PolyLine3D;

import java.awt.*;
import java.util.List;
import java.util.*;

public abstract class ScreenGraphicsDrawer implements IDrawer {
    private ScreenConverter sc;
    private Graphics2D gr;

    public ScreenGraphicsDrawer(ScreenConverter sc, Graphics2D gr) {
        this.sc = sc;
        this.gr = gr;
    }

    public Graphics2D getGraphics() {
        return gr;
    }

    public ScreenConverter getScreenConverter() {
        return sc;
    }
    
    @Override
    public void draw(Collection<PolyLine3D> polylines) {
        List<PolyLine3D> lines = new LinkedList<>();
        IFilter<PolyLine3D> filter = getFilter();
        for (PolyLine3D pl : polylines) {
            if (filter.permit(pl))
                lines.add(pl);
        }
        PolyLine3D[] arr = lines.toArray(new PolyLine3D[0]);
        Arrays.sort(arr, getComparator());
        for (PolyLine3D pl : arr) {
            oneDraw(pl);
        }
    }
    
    @Override
    public void clear(int color) {
        Graphics2D g = getGraphics();
        Color c = g.getColor();
        g.setColor(new Color(color));
        g.fillRect(0, 0, sc.getWs(), sc.getHs());
        g.setColor(c);
    }

    protected abstract void oneDraw(PolyLine3D polyline);

    protected abstract IFilter<PolyLine3D> getFilter();

    protected abstract Comparator<PolyLine3D> getComparator();
}
