package D18130495;

public class Circle {
    private int x;
    private int y;
    private int radius;

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void update1() {
        int min = -20;
        int max = 20;
        x = x + min + (int)(Math.random() * (max - min +1));
        y = y + 10;
    }

    public void update2() {
        int min = -20;
        int max = 20;
        x = x + min + (int)(Math.random() * (max - min +1));
        y = y - 10;
    }

    public void update3() {
        int min = -20;
        int max = 20;
        x = x + 10;
        y = y + min + (int)(Math.random() * (max - min +1));
    }

    public void update4() {
        int min = -20;
        int max = 20;
        x = x - 10;
        y = y + min + (int)(Math.random() * (max - min +1));
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
}