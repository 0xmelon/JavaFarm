package plants;

import zone.Pond;

public class Chestnut extends Pond {
    public Chestnut() {
        setName("菱角");
        setFlowerTime(60);
        setFruitTime(120);
        setPowerTime(180);
    }

    public Chestnut(int flowerTime, int fruitTime, int powerTime, int bugTime) {
        setName("菱角");

        setFruitTime(fruitTime);
        setFlowerTime(flowerTime);
        setPowerTime(powerTime);
        setBugTime(bugTime);

    }
}
