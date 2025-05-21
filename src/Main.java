import HwHashMap.HwHashMap;

public class Main {
    public static void main(String[] args) {
        HwHashMap<String, Integer> map = new HwHashMap<String, Integer>();

        System.out.println("put test1, expect 1 = " + map.put("test1", 1));
        System.out.println("put test2, expect 2 = " + map.put("test2", 2));
        System.out.println("put test3, expect 3 = " + map.put("test3", 3));
        System.out.println("put test4, expect 4 = " + map.put("test4", 4));
        System.out.println("put test5, expect 5 = " + map.put("test5", 5));

        System.out.println("\n---------------------------------\n");

        System.out.println("get test1, expect 1 = " + map.get("test1"));
        System.out.println("get test4, expect 4 = " + map.get("test4"));
        System.out.println("get test01, expect null = " + map.get("test01"));
        System.out.println("get test04, expect null = " + map.get("test04"));

        System.out.println("\n---------------------------------\n");

        System.out.println("remove test4, expect 4 = " + map.remove("test4"));
        System.out.println("remove test04, expect null = " + map.remove("test4"));

        System.out.println("\n---------------------------------\n");

        System.out.println("get after remove test4, expect null = " + map.get("test4"));

        System.out.println("\n---------------------------------\n");

        System.out.println("maxsize before resize expect 16 = " + map.getMaxSize());

        for (int i = 1; i < 30; i++) {
            map.put("resize" + i, i);
        }

        System.out.println("get after resize resize25 expect 25 = -> " + map.get("resize25"));
        System.out.println("maxsize after resize expect 64 = " + map.getMaxSize());
    }
}