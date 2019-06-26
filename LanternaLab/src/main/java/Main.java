import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();

        int x = 17;
        int y = 12;
        final char player = '¤';
        terminal.setCursorPosition(x, y);
        terminal.putCharacter(player);
        terminal.setCursorVisible(false);

        int xMonster = 80;
        int yMonster = 12;
        final char monster = '-';

        int count = 0;
        int points = 0;
        int monsterSpeed = 0;

        boolean continueReadingInput = true;

        KeyType type;
        Character c;

        while (continueReadingInput) {

            int jumpCount = 0;

            int xOld = x;
            int yOld = y;

            int xMonterOld = xMonster;
            int yMonsterOld = yMonster;

            KeyStroke keyStroke = null;

            do {
                Thread.sleep(5); // might throw InterruptedException
                keyStroke = terminal.pollInput();

                monsterSpeed += 10;

                xMonterOld = xMonster;
                yMonsterOld = yMonster;

                xOld = x;
                yOld = y;

                terminal.setCursorPosition(x, y);
                terminal.putCharacter(player);

                terminal.setCursorPosition(xMonster, yMonster);
                terminal.putCharacter(monster);

                if (monsterSpeed % 100 == 0) {

                    terminal.setCursorPosition(x, y);
                    terminal.putCharacter(player);

                    xMonster--;

                    terminal.setCursorPosition(xMonster, yMonster);
                    terminal.putCharacter(monster);

                    terminal.setCursorPosition(xMonterOld, yMonsterOld);
                    terminal.putCharacter(' ');

                    if (xMonster == -1) {
                        xMonster = 80;
                        points += 1;
                    }

                    if (xMonster == x && yMonster == y) {
                        terminal.setCursorPosition(x, y);
                        terminal.putCharacter(monster);
                        continueReadingInput = false;
                        System.out.println("quit");

                        String gameOver = "Game over";
                        String score = "Score: ";
                        String hej = Integer.toString(points);

                        for (int i = 0; i < gameOver.length(); i++) {
                            terminal.setCursorPosition((35 + i), 10);
                            terminal.putCharacter(gameOver.charAt((i)));
                        }
                        for (int i = 0; i < score.length(); i++) {
                            terminal.setCursorPosition((37 + i), 11);
                            terminal.putCharacter(score.charAt((i)));
                        }
                        for (int i = 0; i < hej.length(); i++) {
                            terminal.setCursorPosition((39 + i), 12);
                            terminal.putCharacter(hej.charAt(i));
                        }
                    }
                    if (y == 10) {
                        jumpCount++;
                        if (jumpCount == 4) {
                            y = 12;

                            terminal.setCursorPosition(x, y);
                            terminal.putCharacter(player);

                            terminal.setCursorPosition(xOld, yOld);
                            terminal.putCharacter(' ');
                        }
                    }
                    terminal.flush();
                }
                if (!continueReadingInput) {
                    break;
                }
            } while (keyStroke == null);

            if (continueReadingInput) {
                type = keyStroke.getKeyType();
                c = keyStroke.getCharacter(); // used Character, not char because it might be null

                switch (type) {
                    case ArrowUp:
                        y -= 2;
                        terminal.setCursorPosition(xOld, yOld);
                        terminal.putCharacter(' ');
                        break;
                    case ArrowDown:
                        count = 0;
                        y = 12;
                        terminal.setCursorPosition(xOld, yOld);
                        terminal.putCharacter(' ');
                        break;
                    case ArrowLeft:
                        count = 0;
                        y = 12;
                        terminal.setCursorPosition(xOld, yOld);
                        terminal.putCharacter(' ');
                        break;
                    case ArrowRight:
                        count = 0;
                        y = 12;
                        terminal.setCursorPosition(xOld, yOld);
                        terminal.putCharacter(' ');
                        break;
                }

                terminal.setCursorPosition(x, y);
                terminal.putCharacter(player);

                if (c == Character.valueOf('q')) {
                    continueReadingInput = false;
                    System.out.println("quit" + points);
                }
            }
            terminal.flush();
        }
    }
}
