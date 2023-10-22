import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Coins extends Element {
    public Coins(int x, int y) {
        super(x, y);
    }


    public void draw (TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#008000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(),getPosition().getY()), "O");
    }



}