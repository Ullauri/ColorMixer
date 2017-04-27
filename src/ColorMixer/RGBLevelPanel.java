package ColorMixer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

public class RGBLevelPanel extends JPanel implements ChangeListener, ActionListener {
    private List<ColorControlListener> colorControlListeners = new ArrayList<>();
    private final RGB color;
    private JSlider colorSlider;
    private JTextField colorField;
    // Prevents Listeners from being called after setColorLevel()
    private boolean outsideCall = false;


    RGBLevelPanel(final RGB color) {
        this.color = color;

        colorSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 0);
        colorSlider.setMajorTickSpacing(51);
        colorSlider.setPaintTicks(true);
        colorSlider.setPaintLabels(true);
        colorSlider.addChangeListener(this);

        colorField = new JTextField(30);
        colorField.setBackground(Color.BLACK);
        colorField.setForeground(Color.WHITE);
        colorField.setHorizontalAlignment(JTextField.CENTER);
        colorField.setFont(new Font("SansSerif", Font.BOLD, 15));
        colorField.setText("0");
        colorField.addActionListener(this);
        colorField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {
                colorField.setText("");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                colorField.setText(String.valueOf(color.getColorLevel()));
            }
        });

        this.add(new JLabel(color.toString()));
        this.add(colorSlider);
        this.add(colorField);
    }

    public void addColorControlListener(ColorControlListener colorControlListener) {
        colorControlListeners.add(colorControlListener);
    }

    public RGB getColor() {
        return color;
    }

    public void setColorLevel(int level) {
        outsideCall = true;

        level = (level >= 0 && level <= 255) ? level : 0;
        colorSlider.setValue(level);
        colorField.setText(String.valueOf(level));
        colorField.setBackground(color.saturate(level));

        outsideCall = false;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (!outsideCall) {
            int level = colorSlider.getValue();
            colorField.setText(String.valueOf(level));
            colorField.setBackground(color.saturate(level));

            for (ColorControlListener colorControlListener : colorControlListeners)
                colorControlListener.colorChanged(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!outsideCall) {
            int level = 0;
            String colorFieldText = colorField.getText();

            if (colorFieldText.trim().length() > 0 && colorFieldText.matches("^[0-9]+$")) {
                level = Integer.valueOf(colorFieldText);
                level = (level >= 0 && level <= 255) ? level : 0;
            } else {
                colorField.setText("0");
            }

            colorField.setBackground(color.saturate(level));
            colorSlider.setValue(level);

            for (ColorControlListener colorControlListener : colorControlListeners)
                colorControlListener.colorChanged(this);
        }
    }
}