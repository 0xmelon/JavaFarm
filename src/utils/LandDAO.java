package utils;


import zone.Land;

import java.util.List;

public class LandDAO extends BasicDAO<Land> {
    // 用来写入数据库
    public void writeDataBase(List<Land> list) {
        update("truncate table lands");
        for (int i = 0; i < list.size(); i++) {
            Land land = list.get(i);
            update("insert into lands values(?,?,?,?,?,?,?)", i, land.getName(), land.getWaterContainer(), land.getFruitTime(), land.getFlowerTime(), land.getPowerTime(), land.getBugTime());
        }
    }

    // 用来读取数据库
    public List<Land> readDataBase() {
        List<Land> lands = queryMulti("select * from lands where id >= ?", Land.class, 0);
        return lands;
    }

}
