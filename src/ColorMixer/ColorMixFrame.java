package ColorMixer;

import javax.swing.*;
import java.awt.*;

import static ColorMixer.RGB.*;

public class ColorMixFrame extends JFrame implements ColorControlListener {
    private static final int WINDOW_WIDTH = 1000, WINDOW_HEIGHT = 650;
    private RGBLevelPanel redLevelPanel, greenLevelPanel, blueLevelPanel;
    private ColorSelectionList colorSelectionList;
    private ColorDisplayPanel colorDisplayPanel;


    ColorMixFrame() {
        this.setTitle("RGB Color Mixer");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(2, 1));

        colorDisplayPanel = new ColorDisplayPanel(Color.BLACK);
        colorDisplayPanel.addColorControlListener(this);

        JPanel colorControlsPanel = new JPanel();
        colorControlsPanel.setLayout(new GridLayout(1, 2));

        JPanel RGBColorSelectionPanel = new JPanel();
        RGBColorSelectionPanel.setLayout(new GridLayout(3, 1));
        redLevelPanel = new RGBLevelPanel(RED);
        redLevelPanel.addColorControlListener(this);
        greenLevelPanel = new RGBLevelPanel(RGB.GREEN);
        greenLevelPanel.addColorControlListener(this);
        blueLevelPanel = new RGBLevelPanel(RGB.BLUE);
        blueLevelPanel.addColorControlListener(this);
        RGBColorSelectionPanel.add(redLevelPanel);
        RGBColorSelectionPanel.add(greenLevelPanel);
        RGBColorSelectionPanel.add(blueLevelPanel);

        colorSelectionList = new ColorSelectionList();
        colorSelectionList.addColorControlListener(this);
        JScrollPane colorListScrollPane = new JScrollPane(colorSelectionList);

        colorControlsPanel.add(RGBColorSelectionPanel);
        colorControlsPanel.add(colorListScrollPane);

        this.add(colorDisplayPanel);
        this.add(colorControlsPanel);
        this.setVisible(true);
    }

    @Override
    public void colorModeChanged(Color currentColor) {
        redLevelPanel.setColorLevel(currentColor.getRed());
        greenLevelPanel.setColorLevel(currentColor.getGreen());
        blueLevelPanel.setColorLevel(currentColor.getBlue());
    }

    @Override
    public void colorSelected(Color selectedColor) {
        redLevelPanel.setColorLevel(selectedColor.getRed());
        greenLevelPanel.setColorLevel(selectedColor.getGreen());
        blueLevelPanel.setColorLevel(selectedColor.getBlue());

        colorDisplayPanel.update(selectedColor);
    }

    @Override
    public void colorChanged(RGBLevelPanel selectedPanel) {
        Color currentColor = colorDisplayPanel.getCurrentColor();
        int r = currentColor.getRed(), g = currentColor.getGreen(), b = currentColor.getBlue();
        RGB changedValue = selectedPanel.getColor();

        if (changedValue == RED)
            r = changedValue.getColorLevel();
        else if (changedValue == GREEN)
            g = changedValue.getColorLevel();
        else
            b = changedValue.getColorLevel();

        colorDisplayPanel.update(new Color(r, g, b));

        if (!colorSelectionList.isSelectionEmpty())
            colorSelectionList.clearSelection();
    }

    public static void main(String[] args) {
        new ColorMixFrame();
    }
}