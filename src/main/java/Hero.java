import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.AbstractTextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private int hx;
    private int hy;
    public Hero(int x, int y)
    {
        this.hx = x;
        this.hy = y;
    }
    public int getX() {return hx;}
    public void setX(int x) {this.hx = x;}
    public int getY() {return hy;}
    public void setY(int y) {this.hy = y;}

    public void moveUp() {this.hy -= 1;}
    public void moveDown() {this.hy += 1;}
    public void moveRight() {this.hx += 1;}
    public void moveLeft() {this.hx -= 1;}


    public void draw(Screen screen) {
        screen.setCharacter(hx, hy, TextCharacter.fromCharacter('X')[0]);
    }
}
