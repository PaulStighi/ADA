import java.util.ArrayList;

public class SuccessfulParty {
    private static Integer K = 3;
    private static ArrayList<ArrayList<Integer>> ps = new ArrayList<>();
    private static ArrayList<Integer> ind = new ArrayList<>();

    private static void party(ArrayList<ArrayList<Integer>> ps) {
        int N = ps.size(); // number of current nodes

        while(N > K) {
            boolean ok = true;
            int i = 0;

            for(; i < ps.size() ; ++i) { 
                if(ps.get(i).size() < K) { // checking if the crt node is having enough neighbours
                    ok = false;
                    break;
                }
            }

            if(ok) { // all the current nodes have at least K neighbours so that's our answer
                for(int j = 0 ; j < ind.size() ; ++j)
                    System.out.print(ind.get(j) + " ");
                System.out.println();
                return;
            }
            else { // otherwise, before moving to the next step of the loop, remove the first unusable node and its conections
                int rmv = ind.get(i);
                ps.remove(i);
                ind.remove(i);
                for(int j = 0 ; j < ps.size() ; ++j) {
                    for(int k = 0 ; k < ps.get(j).size() ; ++k) {
                        if(ps.get(j).get(k).equals(rmv)) {
                            ps.get(j).remove(k);
                        }
                    }
                }
            }
        }

        return;
    }

    // i know i could have used a matrix but i wanted lists for having the .size() for the number of neighbours
    public static void main(String[] args) {
        // setup for the first example (toggle comment on arr1.add(5) and arr5.add(1) to have the 2nd example) from the course slides
        ind.add(1); ind.add(2); ind.add(3); ind.add(4); ind.add(5); ind.add(6);

        ArrayList<Integer> arr1 = new ArrayList(); arr1.add(2); arr1.add(4); arr1.add(5); arr1.add(6);
        ArrayList<Integer> arr2 = new ArrayList(); arr2.add(1); arr2.add(3); arr2.add(4); arr2.add(5);
        ArrayList<Integer> arr3 = new ArrayList(); arr3.add(2); arr3.add(6);
        ArrayList<Integer> arr4 = new ArrayList(); arr4.add(1); arr4.add(2); arr4.add(5);
        ArrayList<Integer> arr5 = new ArrayList(); arr5.add(1); arr5.add(2); arr5.add(4); arr5.add(6);
        ArrayList<Integer> arr6 = new ArrayList(); arr6.add(1); arr6.add(3); arr6.add(5);

        ps.add(arr1); ps.add(arr2); ps.add(arr3); ps.add(arr4); ps.add(arr5); ps.add(arr6);

        System.out.println("The selected nodes are:");
        SuccessfulParty.party(ps);
    }
}