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
        final char mountainRight = '\\'; //m√•ste vara s√• f√∂r att escapea fr√•n kommandot \.
        final char bird = '~';

        //terminal.setCursorPosition(mountainLeft,mountain1y);
        //terminal.putCharacter(mountain);
        //terminal.setCursorVisible(false);

        int x = 17;
        int y = 12;
        final char player = 0x263B;
        terminal.setCursorPosition(x, y);
        terminal.putCharacter(player);
        terminal.setCursorVisible(false);

//      Hinder start
        int xMonster1 = 80;
        int yMonster1 = 12;

        int xMonster2 = 120;
        int yMonster2 = 12;

        char[] monsters = {0x25d8, 0x257E, 0x23F4, 0x2593};

        // Life variables
        int xLife = 100;
        int yLife = 10;
        final char lifeChar = 0x2665;
        int maxLife = 4;
        int life = 1;

        Random randomNumber = new Random();
        int randomMonster = randomNumber.nextInt(4);

        Random randomNumber2 = new Random();
        int randomMonster2 = randomNumber.nextInt(4);

        int randomLife;

//      Po‰ngr‰knare
        int points = 0;
        int monsterSpeed = 0;

        boolean continueReadingInput = true;

        KeyType type;
        Character c;

        while (continueReadingInput) {

            int jumpCount = 0;

            int xOld = x;
            int yOld = y;

            int xMonterOld1 = xMonster1;
            int yMonsterOld1 = yMonster1;

            int xMonterOld2 = xMonster2;
            int yMonsterOld2 = yMonster2;

            // Initiate old positions of extra-life
            int xLifeOld;
            int yLifeOld;

            KeyStroke keyStroke = null;

            do {
                Thread.sleep(5); // might throw InterruptedException
                keyStroke = terminal.pollInput();

                monsterSpeed += 10;

                xMonterOld1 = xMonster1;
                yMonsterOld1 = yMonster1;

                xMonterOld2 = xMonster2;
                yMonsterOld2 = yMonster2;

                xOld = x;
                yOld = y;

                // Save its old position
                xLifeOld = xLife;
                yLifeOld = yLife;

                terminal.setCursorPosition(x, y);
                terminal.putCharacter(player);

                terminal.setCursorPosition(xMonster1, yMonster1);
                terminal.putCharacter(monsters[randomMonster]);

                terminal.setCursorPosition(xMonster2, yMonster2);
                terminal.putCharacter(monsters[randomMonster2]);

                String pointcount = "Points: " + points;
                for (int i = 0; i < pointcount.length(); i++) {
                    terminal.setCursorPosition((10 + i), 20);
                    terminal.putCharacter(pointcount.charAt((i)));
                }

                // Show lives
                String lifeCount = "Health: ";
                for (int i = 0; i < lifeCount.length(); i++) {
                    terminal.setCursorPosition((10 + i), 21);
                    terminal.putCharacter(lifeCount.charAt((i)));
                }
                for (int i = 1; i <= life; i++) {
                    terminal.setCursorPosition((18 + i), 21);
                    terminal.putCharacter(lifeChar);
                }

//                String lifePrintOut = "Lives: ";
//                for (int i = 0; i < life.length(); i++) {
//                    terminal.setCursorPosition((10 + i), 21);
//                    terminal.putCharacter(life.charAt((i)));
//                }

                //miilj√∂n byggs
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

                // Print out extra-life
                terminal.setCursorPosition(xLife, yLife);
                terminal.putCharacter(lifeChar);

                if (monsterSpeed % 100 == 0) {

                    terminal.setCursorPosition(x, y);
                    terminal.putCharacter(player);

                    xMonster1--;

                    xMonster2--;

                    xLife--;

                    terminal.setCursorPosition(xMonster1, yMonster1);
                    terminal.putCharacter(monsters[randomMonster]);

                    terminal.setCursorPosition(xMonterOld1, yMonsterOld1);
                    terminal.putCharacter(' ');

                    terminal.setCursorPosition(xMonster2, yMonster2);
                    terminal.putCharacter(monsters[randomMonster2]);

                    terminal.setCursorPosition(xMonterOld2, yMonsterOld2);
                    terminal.putCharacter(' ');

                    // Print out position of extra-life
                    terminal.setCursorPosition(xLife, yLife);
                    terminal.putCharacter(lifeChar);
                    // Remove the old positions of extra-life
                    terminal.setCursorPosition(xLifeOld, yLifeOld);
                    terminal.putCharacter(' ');

//                  Nya monster
                    if (xMonster1 == -1) {
                        randomMonster = randomNumber.nextInt(4);
                        xMonster1 = 80;
                    }
                    else if (xMonster2 == -1) {
                        randomMonster2 = randomNumber.nextInt(4);
                        xMonster2 = 120;
                    }

                    // Catching an extra-life
                    if (xLife == x && yLife == y) {
                        life++;
                        randomLife = randomNumber.nextInt((200 + 100) + 100);
                        xLife = randomLife;
                    }
                    // Making sure that number of lives <= max number of lives
                    if (life > maxLife) {
                        life = maxLife;
                    }
                    // Reset position of extra-life
                    if (xLife == -1) {
                        randomLife = randomNumber.nextInt((200 + 100) + 100);
                        xLife = randomLife;
                    }

//                  Ger po‰ng
                    if (xMonster1 == 16|| xMonster2 == 16) {
                        points += 1;
                    }


//                  Spelarens dˆd
                    if ((xMonster1 == x && yMonster1 == y) || (xMonster2 == x && yMonster2 == y)) {
                        // Subtract one life
                        life--;
                        points--;

                        // Remove one life from counter
                        for (int i = 1; i <= life; i++) {
                            terminal.setCursorPosition((18 + life + i), 21);
                            terminal.putCharacter(' ');
                        }

                        if (life == 0) {

                            continueReadingInput = false;
                            System.out.println("quit");

                            terminal.clearScreen();

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
                    }

                    if (y <= 6) {
                        y = 12;

                        terminal.setCursorPosition(x, y);
                        terminal.putCharacter(player);

                        terminal.setCursorPosition(xOld, yOld);
                        terminal.putCharacter(' ');
                    }

//                  Tvinga ner spelare efter hopp
                    else if (y == 10 || y == 8) {
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

//              Avsluta efter dˆd
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
