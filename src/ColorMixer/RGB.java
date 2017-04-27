package ColorMixer;

import java.awt.*;

public enum RGB {
    RED() {
        public Color saturate(int level) {
            setColorLevel(level);
            return new Color(level, 0, 0);
        }
    },
    GREEN() {
        public Color saturate(int level) {
            setColorLevel(level);
            return new Color(0, level, 0);
        }
    },
    BLUE() {
        public Color saturate(int level) {
            setColorLevel(level);
            return new Color(0, 0, level);
        }
    };

    private int colorLevel = 0;

    public void setColorLevel(int level) {
        colorLevel = level;
    }

    public int getColorLevel() {
        return colorLevel;
    }

    public abstract Color saturate(int level);
}