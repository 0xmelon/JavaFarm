package plants;

import zone.Land;

public class Apple extends Land {
    public Apple() {
        setName("苹果");
        setFruitTime(120);
        setFlowerTime(60);
        setPowerTime(240);
    }

    public Apple(int waterContain, int flowerTime, int fruitTime, int powerTime, int bugTime) {
        setName("苹果");
        setWaterContainer(waterContain);
        setFruitTime(fruitTime);
        setFlowerTime(flowerTime);
        setPowerTime(powerTime);
        setBugTime(bugTime);

    }
}
