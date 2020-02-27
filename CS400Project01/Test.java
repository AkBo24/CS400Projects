
public class Test {
    public static void main(String[] args) {
        BST ds = new BST();

//        String t = "";
//        for(int i = 0; i < 25; i++) {
//            t=""+i;
//            ds.insert(t, t);
////            System.out.println(ds.get(t));
//        }
//        for(int i = 0; i < 25; i++) {
//            t=""+i;
//            System.out.println(ds.get(t));
//        }
//        for(int i = 0; i < 25; i++) {
//            t=""+i;
//            System.out.println(ds.remove(t));
//        }
//        System.out.println(ds.remove("1"));
//        System.out.println(ds.contains("1"));
        ds.insert("1", "1");
        ds.insert("3", "3");
        ds.insert("0", "0");
//        System.out.println(ds.remove("1"));
//        System.out.println(ds.contains("1"));
//        System.out.println(ds.contains("3"));
        System.out.println(ds.remove("3"));
//        System.out.println(ds.contains("3"));
        
    }
}
