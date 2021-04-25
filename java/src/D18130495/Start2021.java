package D18130495;

public class Start2021 {
    private int x;
    private int y;
    private int radius = 1;
    private boolean updateing = true;

    public Start2021(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        if(updateing) {
            radius = radius + 1;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public boolean isUpdateing() {
        return updateing;
    }

    public void setUpdateing(boolean updateing) {
        this.updateing = updateing;
    }
}