package utils;

import zone.Land;
import zone.Pond;

import java.util.List;

public class PondDAO extends BasicDAO<Pond> {
    public void writeDataBase(List<Pond> list) {
        update("truncate table ponds");
        for (int i = 0; i < list.size(); i++) {
            Pond pond = list.get(i);
            update("insert into ponds values(?,?,?,?,?,?)",i,pond.getName(),pond.getFlowerTime(),pond.getFruitTime(),pond.getPowerTime(),pond.getBugTime());
        }
    }

    public List<Pond> readDataBase() {
        List<Pond> ponds = queryMulti("select * from ponds where id >= ?", Pond.class, 0);
        return ponds;
    }
}
