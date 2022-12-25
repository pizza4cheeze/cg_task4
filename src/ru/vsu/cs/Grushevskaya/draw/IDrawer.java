package ru.vsu.cs.Grushevskaya.draw;

import ru.vsu.cs.Grushevskaya.third.PolyLine3D;

import java.util.Collection;

public interface IDrawer {
    public void clear(int color);

    public void draw(Collection<PolyLine3D> polyline);
}
