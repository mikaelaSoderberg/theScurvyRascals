import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Scenery {

    protected int xScenery;
    protected int yScenery;
    protected MountainSide mountainSide;
    protected SceneryType sceneryType;


    //MountainConstructor.
    public Scenery(int xScenery, int yScenery, SceneryType sceneryType, MountainSide mountainSide) {
        this.xScenery = xScenery;
        this.yScenery = yScenery;
        this.sceneryType = sceneryType;
        this.mountainSide = mountainSide;
    }

    //BirdConstructor.
    public Scenery(int xScenery, int yScenery, SceneryType sceneryType) {
        this.xScenery = xScenery;
        this.yScenery = yScenery;
        this.sceneryType = sceneryType;
    }

    public int getxScenery() {
        return xScenery;
    }

    public void setxScenery(int xScenery) {
        this.xScenery = xScenery;
    }

    public int getyScenery() {
        return yScenery;
    }

    public void setyScenery(int yScenery) {
        this.yScenery = yScenery;
    }

    public MountainSide getMountainSide() {
        return mountainSide;
    }

    public void setMountainSide(MountainSide mountainSide) {
        this.mountainSide = mountainSide;
    }

    public SceneryType getSceneryType() {
        return sceneryType;
    }

    public void setSceneryType(SceneryType sceneryType) {
        this.sceneryType = sceneryType;
    }

    public static void sceneryGenerator(Scenery scenery, int sceneryMover, int denominator, int increment, Terminal terminal) throws Exception {


        for (int xMountain = scenery.getxScenery() - (sceneryMover / denominator); xMountain < 80; xMountain += increment) {

            terminal.setCursorPosition(xMountain, scenery.getyScenery());
            if (xMountain < 1) {
                terminal.putCharacter(' ');
            } else {
                if (scenery.getSceneryType() == SceneryType.MOUNTAIN) {
                    if (scenery.getMountainSide() == MountainSide.LEFT) {
                        terminal.putCharacter('/');
                    } else if (scenery.getMountainSide() == MountainSide.RIGHT) {
                        terminal.putCharacter('\\');
                    }
                }

                else if (scenery.getSceneryType() == SceneryType.BIRD) {
                    terminal.putCharacter('~');
                }
            }

        }
    }
}