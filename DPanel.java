import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;


public class DPanel extends JPanel {

    private ArrayList<DrawableItem> drawableItems = new ArrayList();

    public DPanel() { }

    public void initializeEverything() {

        // Initialize items
        for (DrawableItem item : this.getDrawableItems()) {
            item.initialize();
        }
    }

    public void moveEverythingOneStep() {

        // Each item makes one step
        for (DrawableItem item : this.getDrawableItems()) {
            item.moveOneStep();
        }
    }

    public void paint(Graphics g) {

        super.paint(g);

        // Draw items
        for (DrawableItem item : this.getDrawableItems()) {
            item.draw(g);
        }
    }

    public void addDrawableItem(DrawableItem item) {
        this.getDrawableItems().add(item);
    }

    public ArrayList<DrawableItem> getDrawableItems() {
        return drawableItems;
    }


}
