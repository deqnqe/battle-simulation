
import java.awt.*;
import java.util.Random;

public class Element extends DrawableImage {

    private double speed;
    private boolean movingRight;
    private boolean movingUp;

    private int obj;

    public Element(DPanel drawArea, String imageFile, double speed, int obj) {

        // Set area, image and element's dimensions
        super(drawArea, imageFile, 40, 40);

        // Set elements' speed and type(rock, paper, scissors)
        this.setSpeed(speed);
        this.setObj(obj);

    }

    public void initialize() {

        int xPos, yPos;

        // Find random x and y coordinates
        Random rand1 = new Random();
        yPos = rand1.nextInt(this.getWindowHeight() - this.getHeight());
        Random rand2 = new Random();
        xPos = rand2.nextInt(this.getWindowHeight() - this.getHeight());

        // Set coordinates
        this.setX(xPos);
        this.setY(yPos);
    }

    public void moveOneStep() {

        int nextX, nextY;

        // Regarding speed and direction update coordinates
        if (isMovingRight()) {
            nextX = this.getX() + (int) Math.round(this.getSpeed());
        } else {
            nextX = this.getX() - (int) Math.round(this.getSpeed());
        }

        if (isMovingUp()) {
            nextY = this.getY() - (int) Math.round(this.getSpeed());
        } else {
            nextY = this.getY() + (int) Math.round(this.getSpeed());
        }

        // If the element reaches the borders of the scene reverse its direction
        if (nextX >= this.getWindowWidth() - this.getWidth()) {
            this.setMovingRight(false);
        } else if(nextX <= 0) {
            this.setMovingRight(true);
        }
        if (nextY >= this.getWindowHeight() - this.getHeight()) {
            this.setMovingUp(true);
        } else if (nextY <= 0) {
            this.setMovingUp(false);
        }

        //Check if elements collide
        boolean collision = false;
        for (DrawableItem item : getDrawArea().getDrawableItems()) {
            if (item instanceof Element && item != this) {
                Element other = (Element) item;
                if (collidesWith(other)) {
                    collision = true;
                    //If collision takes place change the object type and image to the superior one
                    if(this.obj == 1 && other.obj == 2){
                        this.setImageFile("Battle/paper.png");
                        this.obj = 2;
                    }
                    else if(this.obj == 1 && other.obj == 3){
                        other.setImageFile("Battle/rock.png");
                        other.obj = 1;
                    }else if(this.obj == 2 && other.obj == 3){
                        this.setImageFile("Battle/scissors.png");
                        this.obj = 3;
                    }

                    //Reverse elements' directions according to their movement
                    if (this.getX() <= other.getX()) {
                        this.setMovingRight(false);
                        other.setMovingRight(true);
                    } else {
                        this.setMovingRight(true);
                        other.setMovingRight(false);
                    }
                    if (this.getY() <= other.getY()) {
                        this.setMovingUp(true);
                        other.setMovingUp(false);
                    } else {
                        this.setMovingUp(false);
                        other.setMovingUp(true);
                    }

                    //If the collision takes place, objects accelerate
                    if(this.speed == other.speed){
                        this.setSpeed(this.getSpeed() + 0.5);
                        other.setSpeed(this.getSpeed() + 0.5);
                    } else{
                        this.setSpeed(this.getSpeed() + 0.5);
                        other.setSpeed(this.getSpeed() + 0.5);
                    }
                    break;
                }
            }
        }

        if (!collision) {
            // If there is no collision, reduce the speed
            this.setSpeed(this.getSpeed()*0.99);
        }
        // Update the position
        this.setX(nextX);
        this.setY(nextY);
    }

    //Identify if borders of one image intersect borders of another one

    public boolean collidesWith(Element other) {
        Rectangle thisBoundingBox = this.getBoundingBox();
        Rectangle otherBoundingBox = other.getBoundingBox();
        if (thisBoundingBox.x + thisBoundingBox.width < otherBoundingBox.x ||
                thisBoundingBox.x > otherBoundingBox.x + otherBoundingBox.width ||
                thisBoundingBox.y + thisBoundingBox.height < otherBoundingBox.y ||
                thisBoundingBox.y > otherBoundingBox.y + otherBoundingBox.height) {
            return false;
        }
        return true;
    }

    //Confine the image in a box
    public Rectangle getBoundingBox() {
        int x = this.getX();
        int y = this.getY();
        int width = 35;
        int height = 35;
        return new Rectangle(x, y, width, height);
    }

    //Change the image
    public void setImageFile(String imageFile) {

        Image image = Toolkit.getDefaultToolkit().getImage(imageFile);
        this.setImage(image);

    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        if(speed >= 3.0){
            this.speed = 3.0;
        }else if (speed < 1.0){
            this.speed = 1.0;
        }else{
            this.speed = speed;
        }
    }


    public void setObj(int obj){
        this.obj = obj;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public boolean isMovingUp(){
        return movingUp;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

}
