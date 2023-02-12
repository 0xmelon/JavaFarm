import plants.Apple;
import plants.Chestnut;
import plants.Fig;
import plants.Lotus;
import utils.LandDAO;
import utils.PondDAO;
import utils.StoreDAO;
import utils.UserDAO;
import zone.Land;
import zone.Pond;
import zone.Store;
import zone.User;

import java.util.*;

public class Farm implements Runnable {
    Scanner in = new Scanner(System.in);
    // 用来存储陆地植物的信息
    List<Land> landsList = new ArrayList<>();
    // 用来存储池塘植物的信息
    List<Pond> pondsList = new ArrayList<>();
    // 用来存储仓库的信息
    Store store = new Store();
    // 用来加载陆地植物
    LandDAO landDAO = new LandDAO();
    // 用来加载池塘植物
    PondDAO pondDAO = new PondDAO();
    // 用来加载仓库信息
    StoreDAO storeDAO = new StoreDAO();

    // 定时器的作用，用来更新植物的状态
    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(2000);
                updateData();
                resaveState();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Farm farm = new Farm();
        farm.login();
        Thread farmRun = new Thread(farm);
        farmRun.start();
        farm.operate();
    }

    // 验证数据库是否存在此用户
    public void login() {
        while (true) {
            UserDAO dao = new UserDAO();
            System.out.println("请输入账号：");
            int user = in.nextInt();
            System.out.println("请输入密码：");
            int password = in.nextInt();
            User use = dao.querySingle("select password from users where user = ?", User.class, user);
            if (password == use.getPassword()) {
                System.out.println("登录成功！数据库连接中...");
                getData();
                System.out.println("欢迎来到Java农场~");
                break;
            } else {
                System.out.println("信息有误，重新输入");
            }
        }
    }

    // 获取数据库中的数据
    public void getData() {
        pondsList = pondDAO.readDataBase();
        landsList = landDAO.readDataBase();
        store = storeDAO.readDataBase();
    }

    // 更新数据库的数据
    public void updateData() {
        landDAO.writeDataBase(landsList);
        pondDAO.writeDataBase(pondsList);
        storeDAO.writeDataBase(store);
    }

    //更新植物状态
    public void resaveState() {
        for (Land list : landsList) {
            int waterContainer = list.getWaterContainer();
            int flowerTime = list.getFlowerTime();
            int fruitTime = list.getFruitTime();
            int bugTime = list.getBugTime();
            int powerTime = list.getPowerTime();
            list.setWaterContainer(waterContainer - 2);
            list.setBugTime(bugTime - 2);
            list.setPowerTime(powerTime - 2);
            if (waterContainer < 0 || powerTime < 0 || bugTime < 0) {
                continue;
            }
            list.setFlowerTime(flowerTime - 2);
            list.setFruitTime(fruitTime - 2);
            //判断含水量是否足够或者是否长虫,速度减半
            if (waterContainer < 90 || bugTime < 50) {
                list.setFlowerTime(1 + flowerTime);
                list.setFruitTime(1 + fruitTime);
            }
        }
        for (Pond list : pondsList) {
            int flowerTime = list.getFlowerTime();
            int fruitTime = list.getFruitTime();
            int bugTime = list.getBugTime();
            int powerTime = list.getPowerTime();
            list.setBugTime(bugTime - 2);
            list.setPowerTime(powerTime - 2);
            if (powerTime == 0 || bugTime == 0) {
                continue;
            }
            list.setFlowerTime(flowerTime - 2);
            list.setFruitTime(fruitTime - 2);
            //判断是否长虫,速度减半
            if (bugTime < 90) {
                list.setFlowerTime(1 + flowerTime);
                list.setFruitTime(1 + fruitTime);
            }
        }
    }

    // 农场的操作
    public void operate() {
        while (true) {
            System.out.println("1.作物操作 2.进入仓库 3.农场状态 4.保存退出");
            System.out.print("请输入：");
            switch (in.nextInt()) {
                case 1:
                    plantOperation();
                    break;
                case 2:
                    storeOperation();
                    break;
                case 3:
                    printFarm();
                    break;
                case 4:
                    updateData();
                    System.exit(0);
                default:
                    System.out.println("无此选项！请确认");
            }

        }

    }
    // 仓库的操作
    public void storeOperation() {
        while (true) {
            System.out.println("仓库剩余:果实" + store.getFruit() + "个,花" + store.getFlower() + "朵,目前金币余额为：" + store.getCoin());
            System.out.println("1.出售 2.退出");
            System.out.print("请输入：");
            switch (in.nextInt()) {
                case 1:
                    store.sell();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("无此选项！请确认");
            }
        }
    }


    // 农场的操作
    public void plantOperation() {
        while (true) {
            System.out.println("1.种植 2.浇水 3.收成 4.除虫 5.退出");
            System.out.print("请输入：");
            switch (in.nextInt()) {
                case 1:
                    plant();
                    break;
                case 2:
                    water();
                    break;
                case 3:
                    harvest();
                    break;
                case 4:
                    fixBug();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("无此选项！请确认");
            }
        }

    }

    // 种植的操作
    public void plant() {
        System.out.println("种植作物：1.苹果(5r) 2.无花果(3r) 3.莲花(4r) 4.菱角(3r) 5.取消");
        System.out.print("请输入：");
        int index = in.nextInt();
        switch (index) {
            case 1:
                if (store.getCoin() > 5) {
                    store.setCoin(store.getCoin() - 5);
                    Land apple = new Apple();
                    landsList.add(apple);
                } else {
                    System.out.println("金币不足，请到仓库兑换");
                    return;
                }
                break;
            case 2:
                if (store.getCoin() > 3) {
                    store.setCoin(store.getCoin() - 3);
                    Land fig = new Fig();
                    landsList.add(fig);
                } else {
                    System.out.println("金币不足，请到仓库兑换");
                }
                break;
            case 3:
                if (store.getCoin() > 4) {
                    store.setCoin(store.getCoin() - 4);
                    Pond lotus = new Lotus();
                    pondsList.add(lotus);
                } else {
                    System.out.println("金币不足，请到仓库兑换");
                }
                break;
            case 4:
                if (store.getCoin() > 3) {
                    store.setCoin(store.getCoin() - 3);
                    Pond chestnut = new Chestnut();
                    pondsList.add(chestnut);
                } else {
                    System.out.println("金币不足，请到仓库兑换");
                }
                break;
            case 5:
                return;
            default:
                System.out.println("无此选项！请确认");
        }
        if (store.getCoin() >= 0) {
            System.out.println("种植成功，当前剩余金币" + store.getCoin() + "个");
        }
        printFarm();

    }

    // 进行浇水操作
    public void water() {
        for (int i = 0; i < landsList.size(); i++) {
            landsList.get(i).setWaterContainer(999);
        }
        System.out.println("浇水成功");
        printFarm();
    }

    //进行收成操作
    public void harvest() {
        int fruit = 0, flower = 0;
        for (int i = 0; i < landsList.size(); i++) {
            if (landsList.get(i).getPowerTime() < 0 || landsList.get(i).getBugTime() < 0 || landsList.get(i).getWaterContainer() < 0) {
                landsList.remove(i);
                continue;
            }
            if (landsList.get(i).getFlowerTime() < 0) {
                flower++;
                store.setFlower(store.getFlower() + 1);
            }
            if (landsList.get(i).getFruitTime() < 0) {
                fruit++;
                store.setFruit(store.getFruit() + 1);
            }
            landsList.remove(i);
        }
        for (int i = 0; i < pondsList.size(); i++) {
            if (pondsList.get(i).getPowerTime() < 0 || pondsList.get(i).getBugTime() < 0) {
                pondsList.remove(i);
                continue;
            }
            if (pondsList.get(i).getFlowerTime() < 0) {
                flower++;
                store.setFlower(store.getFlower() + 1);

            }
            if (pondsList.get(i).getFruitTime() < 0) {
                fruit++;
                store.setFruit(store.getFruit() + 1);
            }

        }
        System.out.println("成功收获" + fruit + "个果实，" + flower + "朵花");
        printFarm();
    }

    // 进行杀虫操作
    public void fixBug() {
        int time = 999;
        int num = store.getCoin();
        if (num >= 4) {
            store.setCoin(num - 4);
            for (int i = 0; i < landsList.size(); i++) {
                landsList.get(i).setBugTime(time);
            }
            for (int i = 0; i < pondsList.size(); i++) {
                pondsList.get(i).setBugTime(time);
            }
            System.out.println("杀虫成功");
            printFarm();
        } else {
            System.out.println("金币数量不足，请到仓库兑换");
        }
    }

    // 打印农场状态
    public void printFarm() {
        System.out.println("当前农场状况");
        String state = "|";
        int fruit = 0, flower = 0;
        for (int i = 0; i < landsList.size(); i++) {
            Land land = landsList.get(i);
            state += land.getName();
            if (land.getWaterContainer() < 0 || land.getBugTime() < 0 || land.getPowerTime() < 0) {
                state += "(死亡)|";
                continue;
            }
            if (land.getWaterContainer() < 100 && land.getWaterContainer() > 0) {
                state += "(缺水)";
            }
            if (land.getBugTime() < 100) {
                state += "(长虫)";
            }
            if (land.getFruitTime() <= 0) {
                state += "(成熟)";
                fruit++;
            }
            if (land.getFlowerTime() <= 0) {
                state += "(开花)";
                flower++;
            } else {
                state += "(生长)";
            }
            state += "|";
        }
        if (landsList.size() > 0) {
            System.out.println(state);
        } else {
            System.out.println("土地为空");
        }
        String statepond = "|";
        for (int i = 0; i < pondsList.size(); i++) {
            Pond pond = pondsList.get(i);
            statepond += pond.getName();
            if (pond.getPowerTime() <= 0 || pond.getBugTime() <= 0) {
                statepond += "(死亡)|";
                continue;
            }
            if (pond.getFruitTime() <= 0) {
                statepond += "(成熟)";
                fruit++;
            }
            if (pond.getBugTime() < 100) {
                statepond += "(长虫)";
            }
            if (pond.getFlowerTime() <= 0) {
                statepond += "(开花)";
                flower++;
            } else {
                statepond += "(生长)";
            }
            statepond += "|";
        }
        if (pondsList.size() > 0) {
            System.out.println(statepond);
        } else {
            System.out.println("池塘为空");
        }
        System.out.println("目前可以收获" + fruit + "个果实，" + flower + "朵花");
    }
}