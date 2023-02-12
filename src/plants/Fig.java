package plants;

import zone.Land;

public class Fig extends Land {

    public Fig() {
        setName("无花果");
        setFruitTime(120);
        setFlowerTime(999);
        setPowerTime(240);
    }

    public Fig(int waterContain, int flowerTime, int fruitTime, int powerTime, int bugTime) {
        setName("无花果");
        setWaterContainer(waterContain);
        setFruitTime(fruitTime);
        setFlowerTime(flowerTime);
        setPowerTime(powerTime);
        setBugTime(bugTime);
    }
}
