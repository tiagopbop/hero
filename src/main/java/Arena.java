import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;


public class Arena
{
    private int width;
    private int height;
    Hero hero = new Hero(10,10);



    public Arena(int width, int height) {
        this.height = height;
        this.width = width;
    }
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    void draw(TextGraphics graphics)
    {
        hero.draw(graphics);
    }

    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    private boolean canHeroMove(Position position) {
        if(position.x < width && position.x >=0 && position.y < height && position.y>=0)
        {return  true;}
        return false;
    }

    void processKey(KeyStroke key) {
        System.out.println(key);
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){System.exit(0);}

        switch (key.getKeyType())
        {
            case ArrowUp -> {
                moveHero(hero.moveUp());
                break;
            }
            case ArrowDown -> {
                moveHero(hero.moveDown());
                break;
            }
            case ArrowLeft -> {
                moveHero(hero.moveLeft());
                break;
            }
            case ArrowRight -> {
                moveHero(hero.moveRight());
                break;
            }
        }

    }

}
