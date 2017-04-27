package ColorMixer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ColorSelectionList extends JList<String> implements ListSelectionListener {
    private List<ColorControlListener> colorControlListeners = new ArrayList<>();
    private static final Map<String, Color> colors = mapColors();


    ColorSelectionList() {
        String[] keys = colors.keySet().toArray(new String[colors.keySet().size()]);
        Arrays.sort(keys);

        DefaultListCellRenderer listCellRenderer = (DefaultListCellRenderer) this.getCellRenderer();
        listCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        this.setListData(keys);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.addListSelectionListener(this);
    }

    private static Map<String, Color> mapColors() {
        Map<String, Color> colors = new HashMap<>();

        colors.put("Aqua", new Color(0, 255, 255));
        colors.put("Burgundy", new Color(122, 18, 48));
        colors.put("Bronze", new Color(173, 143, 26));
        colors.put("Brown", new Color(121, 85, 72));
        colors.put("Beige", new Color(245, 245, 220));
        colors.put("Copper", new Color(189, 186, 16));
        colors.put("Crimson", new Color(220, 20, 60));
        colors.put("Cyan", Color.CYAN);
        colors.put("Diamond", new Color(185, 242, 255));
        colors.put("Dollar Green", new Color(133, 187, 101));
        colors.put("Gold", new Color(255, 215, 0));
        colors.put("Gray", Color.GRAY);
        colors.put("Indigo", new Color(63, 81, 181));
        colors.put("Lime", new Color(205, 220, 57));
        colors.put("Magenta", Color.MAGENTA);
        colors.put("Orange", Color.ORANGE);
        colors.put("Pink", Color.PINK);
        colors.put("Purple", new Color(128, 0, 128));
        colors.put("Salmon", new Color(251, 101, 68));
        colors.put("Silver", new Color(166, 169, 170));
        colors.put("Teal", new Color(0, 128, 128));
        colors.put("Turquoise", new Color(64, 224, 208));
        colors.put("Yellow", Color.YELLOW);

        return colors;
    }


    public void addColorControlListener(ColorControlListener colorControlListener) {
        colorControlListeners.add(colorControlListener);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!this.isSelectionEmpty()) {
            Color selectedColor = colors.get(this.getSelectedValue());

            for (ColorControlListener colorControlListener : colorControlListeners)
                colorControlListener.colorSelected(selectedColor);
        }
    }
}