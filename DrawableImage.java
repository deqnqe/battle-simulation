
import java.awt.*;


public abstract class DrawableImage extends DrawableItem {

    private Image image;

    private int height;

    private int width;

    public DrawableImage(DPanel drawArea, String imageFile, int width, int height) {

        // Set the drawing panel
        super(drawArea);

        // Set the image
        Image image = Toolkit.getDefaultToolkit().getImage(imageFile);
        this.setImage(image);

        // Set the height and width of element
        this.setHeight(height);
        this.setWidth(width);
    }

    public void draw(Graphics g) {
        int dx1 = this.getX();
        int dy1 = this.getY();
        int dx2 = this.getX() + this.getWidth();
        int dy2 = this.getY() + this.getHeight();

        g.drawImage(getImage(),
                    dx1, dy1, dx2, dy2,
                0 , 0, this.getImage().getWidth(null), this.getImage().getHeight(null), null);
    }


    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return this.image;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


}
