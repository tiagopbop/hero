import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private int x = 10;
    private int y = 10;
    Hero hero = new Hero(10,10);

    public Game(){


        try {
            TerminalSize terminalSize = new TerminalSize(20, 20);
            DefaultTerminalFactory terminalFactory = new
                    DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private void moveHero(Position position) {
        hero.setPosition(position);
    }
    private void processKey(KeyStroke key) {
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


    private void draw() throws IOException
    {
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }

    public void run() throws IOException {
        while (true)
        {
            try
            {
                draw();
                KeyStroke key = screen.readInput();
                processKey(key);
        }   catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }
}

