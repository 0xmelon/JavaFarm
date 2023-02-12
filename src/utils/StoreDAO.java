package utils;

import zone.Store;

import java.util.List;

public class StoreDAO extends BasicDAO<Store> {
    public Store readDataBase() {
        List<Store> store = queryMulti("select * from stores", Store.class);
        return store.get(0);
    }

    public void writeDataBase(Store store) {
        int update = update("update stores set coin = ?,fruit = ?,flower = ? where id = 0",store.getCoin(),store.getFruit(),store.getFlower());
        if(update == 0) {
            System.out.print("商店数据保存失败");
        }

    }

}
