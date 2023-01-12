
/**
 * Codingbat > Array-3 > countClumps
 */
import java.util.ArrayList;

public class CountClumps
{
    public static void main(String[] args) {
        int[] clumps = { 1, 2, 2, 3, 4, 4 };
        ArrayList<Integer> al = new ArrayList<Integer>();
        for (int i=0; i < clumps.length; i++) {
            al.add(clumps[i]);
        }

        CountClumps c = new CountClumps();
        System.out.println( c.countClumps(al) );

    }

    /*
     * Say that a "clump" in an array is a series of 2 or more adjacent
     * elements of the same value. Return the number of clumps in the given
     * array.
     */
    public int countClumps(ArrayList<Integer> nums) {
        int count = 0;
        for (int i=0; i<nums.size(); ) {
            if (countClumpAt(nums, i) > 0) {
                count++;
                i += countClumpAt(nums, i);
            }
            else {
                i++;
            }
        }
        return count;
    }
    
    private int countClumpAt(ArrayList<Integer> nums, int x) {
        int count = 0;
        int clumpNum = nums.get(x);
        for (int i=x; i < nums.size() && nums.get(i) == clumpNum; i++) {
               count++;
        }
        
        // Clumps of size 1 are not clumps.
        if (count == 1) { return 0; }
        
        return count;
    }
    
    
    
    
    
    /* ************************* */
    public int countClumpsArray(int[] nums) {
        int clumpCount=0, clumpSize;

        int clumpStart = 0; // where to start looking for clumps
        while (clumpStart < nums.length) {

            int clumpNum = nums[clumpStart]; // the clump number
            clumpSize = 0;

            // Count the number of clumpNums starting at clumpStart.
            for (int j = clumpStart; j < nums.length && nums[j] == clumpNum; j++) {
                clumpSize++;
            }

            // Clumps only count if they are 2 or larger.
            if (clumpSize >= 2) {
                clumpCount++;
            }

            // Move past the current clump to the next one.
            clumpStart += clumpSize;
        }

        return clumpCount;
    }
    
    public int countClumps2(ArrayList<Integer> nums) {
        int clumpCount=0, clumpSize;

        int clumpStart = 0; // where to start looking for clumps
        while (clumpStart < nums.size()) {

            int clumpNum = nums.get(clumpStart); // the clump number
            clumpSize = 0;

            // Count the number of clumpNums starting at clumpStart.
            for (int j = clumpStart; j < nums.size() && nums.get(j) == clumpNum; j++) {
                clumpSize++;
            }

            // Clumps only count if they are 2 or larger.
            if (clumpSize >= 2) {
                clumpCount++;
            }

            // Move past the current clump to the next one.
            clumpStart += clumpSize;
        }

        return clumpCount;
    }
}
