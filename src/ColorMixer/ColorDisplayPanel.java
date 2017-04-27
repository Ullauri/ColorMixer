package ColorMixer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

public class ColorDisplayPanel extends JPanel implements MouseListener, ItemListener {
    private final int[] triangleX = {200, 250, 300}, triangleY = {175, 75, 175};
    private List<ColorControlListener> colorControlListeners = new ArrayList<>();
    private JCheckBox colorShapesOnlyBox;
    private Color shapesColor;


    ColorDisplayPanel(Color background) {
        this.setBackground(background);
        this.addMouseListener(this);
        shapesColor = Color.WHITE;
        colorShapesOnlyBox = new JCheckBox("Color Shapes Only");
        colorShapesOnlyBox.setOpaque(false);
        colorShapesOnlyBox.setForeground(Color.LIGHT_GRAY);
        colorShapesOnlyBox.addItemListener(this);
        this.add(colorShapesOnlyBox);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(shapesColor);
        g.fillPolygon(triangleX, triangleY, 3);
        g.fillOval(350, 75, 100, 100);
        g.setFont(new Font("SansSerif", Font.PLAIN, 130));
        g.drawString("X", 500, 175);
        g.fillRect(625, 75, 100, 100);
    }

    public void update(Color color) {
        if (colorShapesOnlyBox.isSelected())
            shapesColor = color;
        else
            setBackground(color);
        repaint();
    }

    public void addColorControlListener(ColorControlListener colorControlListener) {
        colorControlListeners.add(colorControlListener);
    }

    public Color getCurrentColor() {
        return (colorShapesOnlyBox.isSelected()) ? shapesColor : getBackground();
    }

    private String rgbToString(Color color) {
        String rgbString = color.toString();
        return rgbString.substring(rgbString.indexOf('['));
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        for (ColorControlListener colorControlListener : colorControlListeners)
            colorControlListener.colorModeChanged(getCurrentColor());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JOptionPane.showMessageDialog(getParent(),
                "Background Color: " + rgbToString(getBackground()) + "\nShapes Color: " + rgbToString(shapesColor));
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}