package zone;

public class Land {
    private String name;
    //结果时间
    private int fruitTime;
    //开花时间
    private int flowerTime;
    //枯萎时间
    private int powerTime;
    //土地含水量
    private int waterContainer = 90;
    //随机长虫时间
    private int bugTime = (int) (Math.random() * 1000);

    public Land() {
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getFruitTime() {
        return fruitTime;
    }

    public void setFruitTime(int fruitTime) {
        this.fruitTime = fruitTime;
    }

    public int getFlowerTime() {
        return flowerTime;
    }

    public void setFlowerTime(int flowerTime) {
        this.flowerTime = flowerTime;
    }

    public int getPowerTime() {
        return powerTime;
    }

    public void setPowerTime(int powerTime) {
        this.powerTime = powerTime;
    }

    public int getWaterContainer() {
        return waterContainer;
    }

    public void setWaterContainer(int waterContainer) {
        this.waterContainer = waterContainer;
    }

    public int getBugTime() {
        return bugTime;
    }

    public void setBugTime(int bugTime) {
        this.bugTime = bugTime;
    }
}
