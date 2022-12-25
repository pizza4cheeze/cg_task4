package ru.vsu.cs.Grushevskaya;

import ru.vsu.cs.Grushevskaya.math.Matrix4Factories;
import ru.vsu.cs.Grushevskaya.math.Vector3;
import ru.vsu.cs.Grushevskaya.math.Vector4;
import ru.vsu.cs.Grushevskaya.screen.ScreenConverter;
import ru.vsu.cs.Grushevskaya.screen.ScreenPoint;
import ru.vsu.cs.Grushevskaya.third.Camera;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

public class CameraController implements MouseListener, MouseMotionListener, MouseWheelListener {

    public static interface RepaintListener {
        void shouldRepaint();
    }

    private Set<RepaintListener> listeners = new HashSet<>();

    public void addRepaintListener(RepaintListener listener) {
        listeners.add(listener);
    }

    public void removeRepaintListener(RepaintListener listener) {
        listeners.remove(listener);
    }

    protected void onRepaint() {
        for (RepaintListener cl : listeners)
            cl.shouldRepaint();
    }
    
    private Camera camera;
    private ScreenConverter sc;

    public CameraController(Camera camera, ScreenConverter sc) {
        this.camera = camera;
        this.sc = sc;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public ScreenConverter getSc() {
        return sc;
    }

    public void setSc(ScreenConverter sc) {
        this.sc = sc;
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    private Point last;
    private boolean leftFlag = false;
    private boolean rightFlag = false;
    private boolean middleFlag = false;
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e))
            leftFlag = true;
        if (SwingUtilities.isRightMouseButton(e))
            rightFlag = true;
        if (SwingUtilities.isMiddleMouseButton(e))
            middleFlag = true;
        last = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e))
            leftFlag = false;
        if (SwingUtilities.isRightMouseButton(e))
            rightFlag = false;
        if (SwingUtilities.isMiddleMouseButton(e))
            middleFlag = false;
        
        if (!leftFlag && !rightFlag && !middleFlag)
            last = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point current = e.getPoint();
        if (last != null) {
            int dx = current.x - last.x;
            int dy = current.y - last.y;

            if (leftFlag) {
                double da = dx * Math.PI / 180;
                double db = dy * Math.PI / 280;
                camera.modifyRotate(
                        Matrix4Factories.rotationXYZ(da, Matrix4Factories.Axis.Y)
                    .mul(
                        Matrix4Factories.rotationXYZ(db, Matrix4Factories.Axis.X)
                    )
                );
            }

            if (rightFlag) {
                Vector4 zero = new Vector4(sc.s2r(new ScreenPoint(0, 0)), 0);
                Vector4 cur = new Vector4(sc.s2r(new ScreenPoint(dx, dy)), 0);
                
                Vector3 delta = cur.add(zero.mul(-1)).asVector3();
                camera.modifyTranslate(Matrix4Factories.translation(delta));
            }

            if (middleFlag && dy != 0) {
                Vector4 zero = new Vector4(sc.s2r(new ScreenPoint(0, 0)), 0);
                Vector4 cur = new Vector4(sc.s2r(new ScreenPoint(dx, dy)), 0);

                float length = cur.add(zero.mul(-1)).asVector3().length();
                if (dy < 0)
                    length = -length;
                System.out.println(length);
                camera.modifyTranslate(Matrix4Factories.translation(0, 0, length));
            }
        }
        last = current;
        onRepaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int delta = e.getWheelRotation();

        if (e.isControlDown()) {
            camera.modifyProjection(Matrix4Factories.centralProjection(delta*5f, Matrix4Factories.Axis.Z));
        } else {
            float factor = 1;
            float scale = delta < 0 ? 0.9f : 1.1f;
            int counter = delta < 0 ? -delta : delta;
            while (counter-- > 0)
                factor *= scale;
            camera.modifyScale(Matrix4Factories.scale(factor));
        }
        onRepaint();
    }
    
}
