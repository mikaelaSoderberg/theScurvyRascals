import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();


//      Player
        int x = 17;
        int y = 12;
        final char player = 'O';
        terminal.setCursorPosition(x, y);
        terminal.putCharacter(player);
        terminal.setCursorVisible(false);


//      Hinder start
        int xMonster = 80;
        int yMonster = 12;
//        final char monster = '-';

        List<String> monsters = new ArrayList<String>();
        monsters.add("<0>");
        monsters.add("^-^");
        monsters.add("~Z~");
        monsters.add("^¤^");

//      Poängräknare
        int points = 0;

//      Hastighet fiender
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
                terminal.putCharacter(monsters);

//              Monsters framåtrörelse
                if (monsterSpeed % 100 == 0) {

                    terminal.setCursorPosition(x, y);
                    terminal.putCharacter(player);

                    xMonster--;

                    terminal.setCursorPosition(xMonster, yMonster);
                    terminal.putCharacter(monster);

                    terminal.setCursorPosition(xMonterOld, yMonsterOld);
                    terminal.putCharacter(' ');

//                  Nya monster och uppdatering poäng
                    if (xMonster == -1) {
                        xMonster = 80;
                        points += 1;
                    }


//                  Spelares död
                    if (xMonster == x && yMonster == y) {
                        terminal.setCursorPosition(x, y);
                        terminal.putCharacter(monster);

                        continueReadingInput = false;

                        String gameOver = "Game over";
                        String score = "Score: ";
                        String totalPoints = Integer.toString(points);

                        for (int i = 0; i < gameOver.length(); i++) {
                            terminal.setCursorPosition((35 + i), 10);
                            terminal.putCharacter(gameOver.charAt((i)));
                        }
                        for (int i = 0; i < score.length(); i++) {
                            terminal.setCursorPosition((37 + i), 11);
                            terminal.putCharacter(score.charAt((i)));
                        }
                        for (int i = 0; i < totalPoints.length(); i++) {
                            terminal.setCursorPosition((39 + i), 12);
                            terminal.putCharacter(totalPoints.charAt(i));
                        }
                    }

//                  Tvinga ner spelare efter hopp
                    if (y == 10) {
                        jumpCount++;
                        if (jumpCount == 3) {
                            y = 12;

                            terminal.setCursorPosition(x, y);
                            terminal.putCharacter(player);

                            terminal.setCursorPosition(xOld, yOld);
                            terminal.putCharacter(' ');
                        }
                    }
                    terminal.flush();
                }

//              Avsluta efter död
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
                        y = 12;
                        terminal.setCursorPosition(xOld, yOld);
                        terminal.putCharacter(' ');
                        break;
                    case ArrowLeft:
                        y = 12;
                        terminal.setCursorPosition(xOld, yOld);
                        terminal.putCharacter(' ');
                        break;
                    case ArrowRight:
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
