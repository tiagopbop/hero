import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Arena
{
    private static int width;
    private static int height;
    Hero hero = new Hero(10,10);
    private List<Wall> walls;
    private List<Coins> coins;
    private List<Monster> monsters;


    public Arena(int width, int height) {
        this.height = height;
        this.width = width;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }
    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

    void draw(TextGraphics graphics)
    {
        for (Coins coins : coins)
            coins.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
        for(Monster monster : monsters)
            monster.draw(graphics);
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

    private List<Coins> createCoins() {
        Random random = new Random();
        ArrayList<Coins> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coins(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1));
        return coins;
    }
    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            monsters.add(new Monster(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1));
        return monsters;
    }

    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    private boolean canHeroMove(Position position) {
        boolean a = false;
        if(position.x < width-1 && position.x >=1 && position.y < height-1 && position.y>=1)
        {
            int c = 0;
            for(Coins coin: coins)
            {
                if (coin.getPosition().equals(position))
                {
                    a=true;
                    break;
                }
                c++;
            }
            if(a){retrieveCoins(c);}
            return true;
        }

        return false;
    }
    private void retrieveCoins(int count)
    {
        coins.remove(count);
    }

    void processKey(KeyStroke key) {
        System.out.println(key);
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){System.exit(0);}

        switch (key.getKeyType())
        {
            case ArrowUp -> {
                moveHero(hero.moveUp());
                moveMonsters();
                verifyMonsterCollisions();
                break;
            }
            case ArrowDown -> {
                moveHero(hero.moveDown());
                moveMonsters();
                verifyMonsterCollisions();
                break;
            }
            case ArrowLeft -> {
                moveHero(hero.moveLeft());
                moveMonsters();
                verifyMonsterCollisions();
                break;
            }
            case ArrowRight -> {
                moveHero(hero.moveRight());
                moveMonsters();
                verifyMonsterCollisions();
                break;
            }
        }

    }

    void moveMonsters()
    {
        for(Monster monster:monsters)
        {monster.setPosition(monster.move(monster.getPosition()));}
    }

    void verifyMonsterCollisions()
    {
        for(Monster monster:monsters)
        {
            if(monster.getPosition().equals(hero.getPosition()))
            {
                System.out.print("DEATH:DEATH");
                {System.exit(0);}
            }
        }
    }

}
