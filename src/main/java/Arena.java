import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;


public class Arena
{
    private int width;
    private int height;
    Hero hero = new Hero(10,10);
    private List<Wall> walls;


    public Arena(int width, int height) {
        this.height = height;
        this.width = width;
        this.walls = createWalls();
    }
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    void draw(TextGraphics graphics)
    {
        for (Wall wall : walls)
            wall.draw(graphics);
        hero.draw(graphics);
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }

        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }


    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    private boolean canHeroMove(Position position) {
        if(position.x < width-1 && position.x >=1 && position.y < height-1 && position.y>=1)
        {
            return true;
        }

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
