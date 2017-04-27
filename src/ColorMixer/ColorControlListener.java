package ColorMixer;

import java.awt.*;

public interface ColorControlListener {
    void colorChanged(RGBLevelPanel levelPanel);

    void colorModeChanged(Color currentColor);

    void colorSelected(Color selectedColor);
}
