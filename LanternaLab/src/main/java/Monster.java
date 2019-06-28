public class Monster {
    protected int xMonster;
    protected int yMonster;
    protected char[] monsterChar;
    protected int monsterSpeed;

    public Monster(int xMonster, int yMonster, char[] monsterChar, int monsterSpeed) {
        this.xMonster = xMonster;
        this.yMonster = yMonster;
        this.monsterChar = monsterChar;
        this.monsterSpeed = monsterSpeed;
    }


    public int getMonsterSpeed() {
        return monsterSpeed;
    }

    public void setMonsterSpeed(int monsterSpeed) {
        this.monsterSpeed = monsterSpeed;
    }

    public int getxMonster() {
        return xMonster;
    }

    public void setxMonster(int xMonster) {
        this.xMonster = xMonster;
    }

    public int getyMonster() {
        return yMonster;
    }

    public void setyMonster(int yMonster) {
        this.yMonster = yMonster;
    }

    public char[] getMonsterChar() {
        return monsterChar;
    }

    public void setMonsterChar(char[] monsterChar) {
        this.monsterChar = monsterChar;
    }
}
