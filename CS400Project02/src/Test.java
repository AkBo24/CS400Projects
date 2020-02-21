
public class Test {
    
    public static void main(String[] args) {
        BST<String, String> bst = new BST();
        try {
            bst.insert("2", "2");
            bst.insert("3", "3");
            bst.insert("1", "1");
            bst.insert("4", "4");
            System.out.println(bst.getKeyOfLeftChildOf("2"));
            System.out.println(bst.contains("3"));
            System.out.println(bst.numKeys());
        } catch (IllegalNullKeyException | DuplicateKeyException | KeyNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
