package zone;

import com.alibaba.druid.support.json.JSONUtils;

public class Store {
    private int coin = 100;
    private int fruit = 3;
    private int flower = 11;

    public void sell() {
        int count = 0;
        if (fruit != 0) {
            while (--fruit > 0) {
                count += 8;
            }
        }
        if (flower != 0) {
            while (--flower > 0) {
                count += 5;
            }
        }
        coin += count;
        System.out.println("成功兑换" + count + "个金币！");
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getFruit() {
        return fruit;
    }

    public void setFruit(int fruit) {
        this.fruit = fruit;
    }

    public int getFlower() {
        return flower;
    }

    public void setFlower(int flower) {
        this.flower = flower;
    }

}
