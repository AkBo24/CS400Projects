
public class Test {
    public static void main(String[] args) {
        DS_My ds = new DS_My();

        try {
            String key = "1";
            String value = "one";
            ds.insert(key, value);
            ds.remove(key);
            System.out.println(ds.get(key));
        }
        catch (RuntimeException e) {
            assert(true);
        }
    }
}
