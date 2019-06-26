import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();

        //Building scenery
        int mountainLeft0x = 16;
        int mountainLeft0y = 9;

        int mountainLeft1x = 17;
        int mountainLeft1y = 8;

        int mountainLeft2x = 18;
        int mountainLeft2y = 7;

        int mountainLeft3x = 19;
        int mountainLeft3y = 6;

        int mountainRight0x = 23;
        int mountainRight0y = 9;

        int mountainRight1x = 22;
        int mountainRight1y = 8;

        int mountainRight2x = 21;
        int mountainRight2y = 7;

        int mountainRight3x = 20;
        int mountainRight3y = 6;

        int birdx0 = 21;
        int birdy0 = 4;

        int birdx1 = 24;
        int birdy1 = 3;

        final char mountainLeft = '/';
        final char mountainRight = '\\'; //mÃ¥ste vara sÃ¥ fÃ¶r att escapea frÃ¥n kommandot \.
        final char bird = '~';

        //terminal.setCursorPosition(mountainLeft,mountain1y);
        //terminal.putCharacter(mountain);
        //terminal.setCursorVisible(false);

        int x = 17;
        int y = 12;
        final char player = '\u2117';
        terminal.setCursorPosition(x, y);
        terminal.putCharacter(player);
        terminal.setCursorVisible(false);

//      Hinder start
        int xMonster = 80;
        int yMonster = 12;
//        final char monster = '-';

        char[] monsters = {0x2665, 0x257E, 0x23F4, 0x2593};

        Random randomNumber = new Random();
        int randomMonster = randomNumber.nextInt(4);

//      Poängräknare
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
                terminal.putCharacter(monsters[randomMonster]);

                //miiljÃ¶n byggs
                for(int xMountain = mountainLeft0x;xMountain<70;xMountain+=12){
                    terminal.setCursorPosition(xMountain,mountainLeft0y);
                    terminal.putCharacter(mountainLeft);
                }
                for(int xMountain = mountainLeft1x;xMountain<70;xMountain+=6){
                    terminal.setCursorPosition(xMountain,mountainLeft1y);
                    terminal.putCharacter(mountainLeft);
                }
                for(int xMountain = mountainLeft2x;xMountain<70;xMountain+=6){
                    terminal.setCursorPosition(xMountain,mountainLeft2y);
                    terminal.putCharacter(mountainLeft);
                }
                for(int xMountain = mountainLeft3x;xMountain<70;xMountain+=6){
                    terminal.setCursorPosition(xMountain,mountainLeft3y);
                    terminal.putCharacter(mountainLeft);
                }
                for(int xMountain = mountainRight0x;xMountain<77;xMountain+=12){
                    terminal.setCursorPosition(xMountain,mountainRight0y);
                    terminal.putCharacter(mountainRight);
                }
                for(int xMountain = mountainRight1x;xMountain<75;xMountain+=6){
                    terminal.setCursorPosition(xMountain,mountainRight1y);
                    terminal.putCharacter(mountainRight);
                }
                for(int xMountain = mountainRight2x;xMountain<70;xMountain+=6){
                    terminal.setCursorPosition(xMountain,mountainRight2y);
                    terminal.putCharacter(mountainRight);
                }
                for(int xMountain = mountainRight3x;xMountain<70;xMountain+=6){
                    terminal.setCursorPosition(xMountain,mountainRight3y);
                    terminal.putCharacter(mountainRight);
                }
                for(int xbird = birdx0;xbird<70;xbird+=13){
                    terminal.setCursorPosition(xbird,birdy0);
                    terminal.putCharacter(bird);
                }
                for(int xbird = birdx1;xbird<70;xbird+=15){
                    terminal.setCursorPosition(xbird,birdy1);
                    terminal.putCharacter(bird);
                }

                if (monsterSpeed % 100 == 0) {

                    terminal.setCursorPosition(x, y);
                    terminal.putCharacter(player); //fÃ¶rsvinner annars

                    xMonster--;

                    terminal.setCursorPosition(xMonster, yMonster);
                    terminal.putCharacter(monsters[randomMonster]);

                    terminal.setCursorPosition(xMonterOld, yMonsterOld);
                    terminal.putCharacter(' ');

//                  Nya monster
                    if (xMonster == -1) {
                        randomMonster = randomNumber.nextInt(4);
                        xMonster = 80;
                    }

//                  Ger poäng
                    if (xMonster == 16) {
                        points += 1;
                    }


//                  Spelares död
                    if (xMonster == x && yMonster == y) {
                        terminal.setCursorPosition(x, y);
                        terminal.putCharacter(monsters[randomMonster]);

                        continueReadingInput = false;
                        System.out.println("quit");

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