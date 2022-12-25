package ru.vsu.cs.Grushevskaya.math;

public final class Matrix4Factories {

    public static Matrix4 zero() {
        return Matrix4.zero();
    }

    public static Matrix4 one() {
        return Matrix4.one();
    }

    public static Matrix4 rotationXYZ(double alpha, int axisIndex) {
        Matrix4 m = one();
        if (axisIndex < 0 || axisIndex > 2)
            return m;
        int a1 = (axisIndex + 1) % 3;
        int a2 = (axisIndex + 2) % 3;
        
        m.setAt(a1, a1, (float)Math.cos(alpha));
        m.setAt(a1, a2, (float)Math.sin(alpha));
        m.setAt(a2, a1, -(float)Math.sin(alpha));
        m.setAt(a2, a2, (float)Math.cos(alpha));
        
        return m;
    }
    
    public static enum Axis {X, Y, Z};

    public static Matrix4 rotationXYZ(double alpha, Axis axis) {
        return rotationXYZ(alpha, axis == Axis.X ? 0 : axis == Axis.Y ? 1 : 2);
    }

    public static Matrix4 translation(float x, float y, float z) {
        Matrix4 m = one();
        m.setAt(0, 3, x);
        m.setAt(1, 3, y);
        m.setAt(2, 3, z);
        return m;
    }

    public static Matrix4 translation(Vector3 v) {
        return translation(v.getX(), v.getY(), v.getZ());
    }

    public static Matrix4 scale(float factorX, float factorY, float factorZ) {
        Matrix4 m = one();
        m.setAt(0, 0, factorX);
        m.setAt(1, 1, factorY);
        m.setAt(2, 2, factorZ);
        return m;
    }

    public static Matrix4 scale(float factor) {
        return scale(factor, factor, factor);
    }

    public static Matrix4 centralProjection(float point, int axisIndex) {
        Matrix4 m = one();
        if (axisIndex < 0 || axisIndex > 2)
            return m;
        m.setAt(3, axisIndex, 1/point);
        return m;
    }

    public static Matrix4 centralProjection(float point, Axis axis) {
        return centralProjection(point, axis == Axis.X ? 0 : axis == Axis.Y ? 1 : 2);
    }
}
