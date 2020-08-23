import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author SQ
 * @Date 2020/8/14 0014 10:45
 * @Version 1.0
 */
public class test {
    public static void main(String[] args) throws Exception {
//        List<String> list1 = new ArrayList<>();
//        List<String> list2 = new ArrayList<>();
//        list1.add("1q");
//        list1.add("2b");
//        list1.add("3a");
//        list1.add("4g");
//        list2.add("1q");
//        list2.add("2b");
//        list2.add("3a");
//        list2.add("4g");
//        list2.add("5h");
//        list2.addAll(list1);
//
//        list2.removeAll(list1);
//
//        for(Object o: list2){
//            System.out.println(o);
//        }


        HashMap<String, Object> map = new HashMap<>();
        map.put("envisble",1);

        if (map.get("type")==null){
            System.out.println(map.get("envisble"));
        }

    }
}
