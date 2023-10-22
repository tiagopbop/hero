import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.concurrent.ThreadLocalRandom;

public class Monster extends Element {
    public Monster(int x, int y) {
        super(x, y);
    }


    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFA300"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "M");
    }


    public Position move(Position position) {
        int a = 0;
        int b = 0;

        while (true)
        {
            int randomNum = ThreadLocalRandom.current().nextInt(1, 4 + 1);
            switch (randomNum) {
                case 1 -> a++;
                case 2 -> a--;
                case 3 -> b++;
                case 4 -> b--;
            }

            if (position.x +a < Arena.getWidth() - 1 && position.x+a >=1 && position.y +b < Arena.getHeight() - 1 && position.y +b  >= 1)
            {
                return new Position(getPosition().getX() + a, getPosition().getY() + b);
            }

        }

    }

}




