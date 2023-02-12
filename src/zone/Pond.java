package zone;

public class Pond {
    private String name;
    private int plantTime;
    private int flowerTime;
    private int powerTime;
    private int fruitTime;
    //长虫时间
    private int bugTime = (int) (Math.random() * 300);

    public Pond() {
    }

    public int getPowerTime() {
        return powerTime;
    }

    public void setPowerTime(int powerTime) {
        this.powerTime = powerTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlantTime() {
        return plantTime;
    }

    public void setPlantTime(int plantTime) {
        this.plantTime = plantTime;
    }

    public int getFlowerTime() {
        return flowerTime;
    }

    public void setFlowerTime(int flowerTime) {
        this.flowerTime = flowerTime;
    }

    public int getFruitTime() {
        return fruitTime;
    }

    public void setFruitTime(int fruitTime) {
        this.fruitTime = fruitTime;
    }

    public int getBugTime() {
        return bugTime;
    }

    public void setBugTime(int bugTime) {
        this.bugTime = bugTime;
    }
}
