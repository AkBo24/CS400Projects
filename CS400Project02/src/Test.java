
public class Test {
    
    public static void main(String[] args) throws KeyNotFoundException {
        RBT<String, String> bst = new RBT<String, String>();
        try {
            bst.insert("5", "5");
            bst.insert("3", "3");
            bst.insert("4", "4");            
            System.out.println(bst.getKeyAtRoot());
            System.out.println(bst.rootIsBlack());         
            System.out.println( bst.getKeyOfRightChildOf("4"));
            

//            bst.getInOrderTraversal();
        } catch (IllegalNullKeyException | DuplicateKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
