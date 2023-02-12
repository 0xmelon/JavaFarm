package plants;

import zone.Pond;

public class Lotus extends Pond {
    public Lotus() {
        setName("莲花");
        setFruitTime(60);
        setFlowerTime(60);
        setPowerTime(120);
    }

    public Lotus(int flowerTime, int fruitTime, int powerTime, int bugTime) {
        setName("莲花");
        setFruitTime(fruitTime);
        setFlowerTime(flowerTime);
        setPowerTime(powerTime);
        setBugTime(bugTime);

    }
}
