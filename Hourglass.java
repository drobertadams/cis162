public class Hourglass 
{
    // This is the sample input for you to try. The output should be "19".
    private String input =
        "1 1 1 0 0 0\n" +
        "0 1 0 0 0 0\n" +
        "1 1 1 0 0 0\n" +
        "0 0 2 4 4 0\n" +
        "0 0 0 2 0 0\n" +
        "0 0 1 2 4 0";
            
    // Complete the hourglassSum method.
    public int hourglassSum(int[][] arr) {
        // YOUR CODE GOES HERE
        return 0;
    }

    //========================================
    // YOU DON'T NEED TO CHANGE ANYTHING BELOW 
    //========================================
    public Hourglass() 
    {
        int[][] arr = new int[6][6];
        
        for (int i = 0; i < 6; i++) {
            
            String[] rows = input.split("\n");
            
            for (int j = 0; j < 6; j++) {
                String[] values = rows[i].split(" ");
                arr[i][j] = Integer.parseInt(values[j]);;
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println( hourglassSum(arr) );
    }
    
    public static void main(String[] args)
    {
        Hourglass h = new Hourglass();
    }
}
