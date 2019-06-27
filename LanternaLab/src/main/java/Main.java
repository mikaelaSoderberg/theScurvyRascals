import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.ThemeStyle;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.graphics.ThemeStyle;
import com.googlecode.lanterna.graphics.TextGraphics;


import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();

        String welcome = "Welcome to our JumpingGame!";
        for (int i = 0; i < welcome.length(); i++) {
            terminal.setCursorPosition((24 + i), 6);
            terminal.putCharacter(welcome.charAt((i)));
        }
        String instruction = "Use the ArrowUp-key to jump over your enemies";
        for (int i = 0; i < instruction.length(); i++) {
            terminal.setCursorPosition((14 + i), 7);
            terminal.putCharacter(instruction.charAt((i)));
        }

        String lives = " or to pick up extra lives.";
        for (int i = 0; i < lives.length(); i++) {
            terminal.setCursorPosition((24 + i), 8);
            terminal.putCharacter(lives.charAt((i)));
        }

        String develop = "This game was developed by theScurvyRascals.";
        for (int i = 0; i < develop.length(); i++) {
            terminal.setCursorPosition((16 + i), 18);
            terminal.putCharacter(develop.charAt((i)));
        }

        String difficulty = "But first, choose your level 1 (easy) or 2 (hard): ";
        for (int i = 0; i < difficulty.length(); i++) {
            terminal.setCursorPosition((12 + i), 10);
            terminal.putCharacter(difficulty.charAt((i)));
        }


        int levelChoice = 0;

        KeyStroke keyStroke1 = null;
        do {
            Thread.sleep(5); // might throw InterruptedException
            keyStroke1 = terminal.pollInput();
        } while (keyStroke1 == null);

        char c1 = keyStroke1.getCharacter(); // used Character, not char because it might be null

        switch (c1) {
            case '1':
                levelChoice = 50;
                break;
            case '2':
                levelChoice = 100;
                break;
            default:
                break;
        }

        terminal.clearScreen();

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

        int birdx0 = -240;
        int birdy0 = 4;

        int birdx1 = -280;
        int birdy1 = 3;

        final char mountainLeft = '/';
        final char mountainRight = '\\'; //mÃ¥ste vara sÃ¥ fÃ¶r att escapea frÃ¥n kommandot \.
        final char bird = '~';

        int sceneryMover = 0;

        int x = 17;
        int y = 12;
        final char player = 0x263B;
        terminal.setForegroundColor(TextColor.ANSI.YELLOW);
        terminal.setCursorPosition(x, y);
        terminal.putCharacter(player);
        terminal.setCursorVisible(false);

//      Hinder start
        int xMonster1 = 80;
        int yMonster1 = 12;

        int xMonster2 = 110;
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

                monsterSpeed += levelChoice;

                xMonterOld1 = xMonster1;
                yMonsterOld1 = yMonster1;

                xMonterOld2 = xMonster2;
                yMonsterOld2 = yMonster2;

                xOld = x;
                yOld = y;

                char overground = '^';
                for (int i = 1; i <= 80; i++) {
                    terminal.setCursorPosition((-1 + i), 13);
                    terminal.putCharacter(overground);
                }

                char underground = 0x2591;
                for (int i = 1; i <= 80; i++) {
                    terminal.setCursorPosition((-1 + i), 14);
                    terminal.putCharacter(underground);
                }

                // Save its old position
                xLifeOld = xLife;
                yLifeOld = yLife;

//              Moves player 10 steps for every 20 points
                if (points >= 10 && points < 19) {
                    x = 28;
                }
                else if (points >= 20 && points < 29) {
                    x = 38;
                }
                else if (points >=30) {
                    x = 48;
                }

                terminal.setCursorPosition(xOld, yOld);
                terminal.putCharacter(' ');

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
                String lifeCount = "Health:";
                for (int i = 0; i < lifeCount.length(); i++) {
                    terminal.setCursorPosition((10 + i), 21);
                    terminal.putCharacter(lifeCount.charAt((i)));
                }
                for (int i = 1; i <= life; i++) {
                    terminal.setCursorPosition((18 + i), 21);
                    terminal.putCharacter(lifeChar);
                }

                //miljön byggs
                for(int xMountain = mountainLeft0x-(sceneryMover/50);xMountain<80;xMountain+=12){
                    terminal.setCursorPosition(xMountain,mountainLeft0y);
                    if(xMountain<1){
                        terminal.putCharacter(' ');
                    }
                    else{
                        terminal.putCharacter(mountainLeft);
                    }

                }
                for(int xMountain = mountainLeft1x-(sceneryMover/50);xMountain<80;xMountain+=6){
                    terminal.setCursorPosition(xMountain,mountainLeft1y);
                    if(xMountain<1){
                        terminal.putCharacter(' ');
                    }
                    else{
                        terminal.putCharacter(mountainLeft);
                    }
                }
                for(int xMountain = mountainLeft2x-(sceneryMover/50);xMountain<80;xMountain+=6){
                    terminal.setCursorPosition(xMountain,mountainLeft2y);
                    if(xMountain<1){
                        terminal.putCharacter(' ');
                    }
                    else{
                        terminal.putCharacter(mountainLeft);
                    }
                }
                for(int xMountain = mountainLeft3x-(sceneryMover/50);xMountain<80;xMountain+=6){
                    terminal.setCursorPosition(xMountain,mountainLeft3y);
                    if(xMountain<1){
                        terminal.putCharacter(' ');
                    }
                    else{
                        terminal.putCharacter(mountainLeft);
                    }

                }
                for(int xMountain = mountainRight0x-(sceneryMover/50);xMountain<80;xMountain+=12){
                    terminal.setCursorPosition(xMountain,mountainRight0y);
                    if(xMountain<1){
                        terminal.putCharacter(' ');
                    }
                    else{
                        terminal.putCharacter(mountainRight);
                    }
                    //terminal.putCharacter(mountainRight);
                }
                for(int xMountain = mountainRight1x-(sceneryMover/50);xMountain<80;xMountain+=6){
                    terminal.setCursorPosition(xMountain,mountainRight1y);
                    if(xMountain<1){
                        terminal.putCharacter(' ');
                    }
                    else{
                        terminal.putCharacter(mountainRight);
                    }
                    //terminal.putCharacter(mountainRight);
                }
                for(int xMountain = mountainRight2x-(sceneryMover/50);xMountain<80;xMountain+=6){
                    terminal.setCursorPosition(xMountain,mountainRight2y);
                    if(xMountain<1){
                        terminal.putCharacter(' ');
                    }
                    else{
                        terminal.putCharacter(mountainRight);
                    }
                    //terminal.putCharacter(mountainRight);
                }
                for(int xMountain = mountainRight3x-(sceneryMover/50);xMountain<80;xMountain+=6){
                    terminal.setCursorPosition(xMountain,mountainRight3y);
                    if(xMountain<1){
                        terminal.putCharacter(' ');
                    }
                    else{
                        terminal.putCharacter(mountainRight);
                    }
                    //terminal.putCharacter(mountainRight);
                }
                for(int xbird = birdx0+(sceneryMover/25);xbird<80;xbird+=13){
                    terminal.setCursorPosition(xbird,birdy0);
                    if(xbird<1){
                        terminal.putCharacter(' ');
                    }
                    else{
                        terminal.putCharacter(bird);
                    }
                }
                for(int xbird = birdx1+(sceneryMover/25);xbird<80;xbird+=15){
                    terminal.setCursorPosition(xbird,birdy1);
                    if(xbird<1){
                        terminal.putCharacter(' ');
                    }
                    else{
                        terminal.putCharacter(bird);
                    }
                    //terminal.putCharacter(bird);
                }

                if(sceneryMover%50==0) {
                    for (int xMountain2 = mountainLeft0x - (sceneryMover / 50) + 1; xMountain2 < 80; xMountain2 += 12) {
                        terminal.setCursorPosition(xMountain2, mountainLeft0y);
                        terminal.putCharacter(' ');
                    }
                    for (int xMountain2 = mountainLeft1x - (sceneryMover / 50) + 1; xMountain2 < 80; xMountain2 += 6) {
                        terminal.setCursorPosition(xMountain2, mountainLeft1y);
                        terminal.putCharacter(' ');
                    }
                    for (int xMountain2 = mountainLeft2x - (sceneryMover / 50) + 1; xMountain2 < 80; xMountain2 += 6) {
                        terminal.setCursorPosition(xMountain2, mountainLeft2y);
                        terminal.putCharacter(' ');
                    }
                    for (int xMountain2 = mountainLeft3x - (sceneryMover / 50) + 1; xMountain2 < 80; xMountain2 += 6) {
                        terminal.setCursorPosition(xMountain2, mountainLeft3y);
                        terminal.putCharacter(' ');
                    }
                    for (int xMountain2 = mountainRight0x - (sceneryMover / 50) + 1; xMountain2 < 80; xMountain2 += 12) {
                        terminal.setCursorPosition(xMountain2, mountainRight0y);
                        terminal.putCharacter(' ');
                    }
                    for (int xMountain2 = mountainRight1x - (sceneryMover / 50) + 1; xMountain2 < 80; xMountain2 += 6) {
                        terminal.setCursorPosition(xMountain2, mountainRight1y);
                        terminal.putCharacter(' ');
                    }
                    for (int xMountain2 = mountainRight2x - (sceneryMover / 50) + 1; xMountain2 < 80; xMountain2 += 6) {
                        terminal.setCursorPosition(xMountain2, mountainRight2y);
                        terminal.putCharacter(' ');
                    }
                    for (int xMountain2 = mountainRight3x - (sceneryMover / 50) + 1; xMountain2 < 80; xMountain2 += 6) {
                        terminal.setCursorPosition(xMountain2, mountainRight3y);
                        terminal.putCharacter(' ');
                    }
                }
                if (sceneryMover%25==0){
                    for(int xbird = birdx0+(sceneryMover/25)-1;xbird<80;xbird+=13){
                        terminal.setCursorPosition(xbird,birdy0);
                        terminal.putCharacter(' ');
                    }
                    for(int xbird = birdx1+(sceneryMover/25)-1;xbird<80;xbird+=15){
                        terminal.setCursorPosition(xbird,birdy1);
                        terminal.putCharacter(' ');
                    }
                }

                // Print out extra-life
                terminal.setCursorPosition(xLife, yLife);
                terminal.putCharacter(lifeChar);

                if (monsterSpeed % 500 == 0) {

                    terminal.setCursorPosition(x, y);
                    terminal.putCharacter(player);

                    xMonster1--;

                    xMonster2--;

                    xLife--;

                    sceneryMover++;

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
                        randomLife = randomNumber.nextInt(400) + 100;
                        xLife = randomLife;
                    }
                    // Making sure that number of lives <= max number of lives
                    if (life > maxLife) {
                        life = maxLife;
                    }
                    // Reset position of extra-life
                    if (xLife == -1) {
                        randomLife = randomNumber.nextInt(400) + 100;
                        xLife = randomLife;
                    }

//                  Ger poäng
                    if (xMonster1 == (x-1)|| xMonster2 == (x-1)) {
                        points += 1;
                    }


//                  Spelarens död
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

                    if (y <= 8) {
                        y = 12;

                        terminal.setCursorPosition(x, y);
                        terminal.putCharacter(player);

                        terminal.setCursorPosition(xOld, yOld);
                        terminal.putCharacter(' ');
                    }

//                  Tvinga ner spelare efter hopp
                    else if (y == 10) {
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
