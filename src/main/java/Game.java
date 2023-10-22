import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
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
    private Arena arena = new Arena(80,24);


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

    private void processKey(KeyStroke key) {
        arena.processKey(key);

    }


    private void draw() throws IOException
    {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#CBC3E3"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new
                TerminalSize((arena.getWidth()), arena.getHeight()), ' ');
        arena.draw(screen.newTextGraphics());
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