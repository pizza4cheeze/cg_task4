package ru.vsu.cs.Grushevskaya.screen;

import ru.vsu.cs.Grushevskaya.math.Vector3;

public class ScreenConverter {
    double xr, yr, wr, hr;
    int ws, hs;

    public ScreenConverter(double xr, double yr, double wr, double hr, int ws, int hs) {
        this.xr = xr;
        this.yr = yr;
        this.wr = wr;
        this.hr = hr;
        this.ws = ws;
        this.hs = hs;
    }

    public ScreenPoint r2s(Vector3 v) {
        int i = (int)((v.getX() - xr) * ws / wr);
        int j = (int)((yr - v.getY()) * hs / hr);
        return new ScreenPoint(i, j);
    }

    public Vector3 s2r(ScreenPoint p, float z) {
        double x = xr + p.getI() * wr / ws;
        double y = yr - p.getJ() * hr / hs;
        return new Vector3((float)x, (float)y, z);
    }

    public Vector3 s2r(ScreenPoint p) {
        return s2r(p, 0);
    }

    public void setScreenSize(int w, int h) {
        setWs(w);
        setHs(h);
    }

    public double getHr() {
        return hr;
    }

    public void setHr(double hr) {
        this.hr = hr;
    }

    public int getHs() {
        return hs;
    }

    public void setHs(int hs) {
        this.hs = hs;
    }

    public double getWr() {
        return wr;
    }

    public void setWr(double wr) {
        this.wr = wr;
    }

    public int getWs() {
        return ws;
    }

    public void setWs(int ws) {
        this.ws = ws;
    }

    public double getXr() {
        return xr;
    }

    public void setXr(double xr) {
        this.xr = xr;
    }

    public double getYr() {
        return yr;
    }

    public void setYr(double yr) {
        this.yr = yr;
    }
    
}
