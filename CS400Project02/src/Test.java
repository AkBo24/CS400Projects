
public class Test {
    
    public static void main(String[] args){
        BST<Integer, Integer> bst = new BST<Integer, Integer>();
//        RBT<Integer, Integer> bst = new RBT<Integer, Integer>();
    }
}

/*
//USE AND DO NOT EDIT THESE CONSTANTS
final int RED = 0;
final int BLACK = 1;

Integer one = 12;
Integer two = 2;
Integer three = 1;
Integer four = 300;
Integer five = 20;

bst.insert(one, one);
bst.insert(two, two);
bst.insert(three, three);
bst.insert(four, four);
bst.insert(five, five);

System.out.println(bst.getKeyOfLeftChildOf(12));
System.out.println(bst.getKeyOfRightChildOf(12));

bst.print();

bst.remove(12);
bst.print();
*/

/*        int[] insert = {64, 21, 53, 32, 28,
            6, 44,  55,  77,  54,
            3,   60,  90,  85,  68,
            8,   69,  67,  42,  61,
            33,  79,  91,  18};

    try {
        for(int i = 0; i < insert.length; i++) {
            bst.insert(insert[i], insert[i]);
        }
        bst.print();
        
        for(int i = 0; i < insert.length; i++) {
//            bst.get(insert[i]);
//            System.out.println("get: " +  insert[i]);
                System.out.println(bst.get(insert[i]));
        }
        
    } catch (IllegalNullKeyException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (DuplicateKeyException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch(KeyNotFoundException e) {
        e.printStackTrace();
    }
}*/