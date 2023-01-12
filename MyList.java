
/**
 * Using an array as a way of maintaining a dynamic list of things (Strings in this case).
 * 
 * Usage:
 * 
 *      MyList list = new MyList(10) ; // initial number of elements you can add (optional)
 *      list.add("hello");            // adds a string to the end
 *      list.add(2, "hello");         // inserts a string at the specified location
 *      list.clear();                 // removes all elements
 *      list.contains("hello");       // does the list contain the string?
 *      list.get(1);                  // returns the element or null
 *      list.indexOf("hello");        // returns where the element lives in the list, or -1
 *      list.isEmpty();               // return true if list is empty
 *      list.remove(3);               // removes element at position
 *      list.remove("hello");         // removes a string
 *      list.set(3, "new");           // replaces item 3 with new value
 *      list.size();                  // return number of elements in the list
 *      S.o.p(list);                  // toString is implemented
 */
public class MyList
{
    private String[] list;
    private int size;
    
    public MyList()
    {
        list = new String[10];
        size = 0;
    }
    
    public void add(String value) {
        resize();
        list[size] = value;
        size++;
    }
    
    public void remove(int index) { }
    
    public void remove(String value) {
       
    }
    
    private void resize()
    {
         if (size == list.length) {
            String[] newList = new String[list.length + 1];
            for (int i=0; i < list.length; i++) {
                newList[i] = list[i];
            }
            list = newList;
        }
    }
    
    public void add(int pos, String value) {
        //if list isn't big enoguh, resize
        resize();
         
        // move elements pos through end down 1 spot
        for (int i=list.length-1; i > pos; i--) {
            list[i] = list[i-1];
        }
        list[pos] = value;
        size++;
    }

    public String get(int i)
    {
        return null;
    }
    
    public boolean isEmpty() { return size == 0; }
    
    public int size() { return size; }
    
    public static void test()
    {
        MyList list = new MyList();
        assert list.isEmpty() == true : "empty";
        assert list.size() == 0 : "size";
        assert list.get(0) == null : "asdf";
        list.add("A"); 
        assert list.size() == 1 : "size";
        assert list.get(0) == "A" : "A";
        System.out.println("Tests complete");
       
    }
}
