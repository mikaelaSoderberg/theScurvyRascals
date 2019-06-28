public class Player {

    protected int xPlayer;
    protected int yPlayer;
    protected char playerChar;


    public Player(int xPlayer, int yPlayer, char playerChar) {
        this.xPlayer = xPlayer;
        this.yPlayer = yPlayer;
        this.playerChar = playerChar;
    }

    public char getPlayerChar() {
        return playerChar;
    }

    public void setPlayerChar(char playerChar) {
        this.playerChar = playerChar;
    }

    public int getyPlayer() {
        return yPlayer;
    }

    public void setyPlayer(int yPlayer) {
        this.yPlayer = yPlayer;
    }


    public int getxPlayer() {
        return xPlayer;
    }

    public void setxPlayer(int xPlayer) {
        this.xPlayer = xPlayer;
    }
}
