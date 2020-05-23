public class KnapsackSimple {
    private static final int GMax = 10005;
    private static int[][] DP;

    private static void sw(int G) {
        for(int i = 0 ; i <= G ; ++i) {
            DP[1][i] = DP[2][i];
        }
    }

    public static void main(String[] args) {
        int N = 6;
        int G = 10;
        int[] W = {3, 3, 1, 1, 2, 1};
        int[] P = {7, 4, 2, 9, 4, 5};
        DP = new int[5][GMax];
        
        for(int cw = 0 ; cw < G ; ++cw) {
            DP[2][cw] = DP[1][cw];

            if(W[0] <= cw) {
                DP[2][cw] = Math.max(DP[2][cw], DP[1][cw - W[0]] + P[0]);
            }
        }

        sw(G);

        for(int i = 1 ; i < N ; ++i) {
            for(int cw = 0 ; cw <= G ; ++cw) {
                if(W[i] <= cw) {
                    DP[2][cw] = Math.max(DP[2][cw], DP[1][cw - W[i]] + P[i]);
                }
            }
            sw(G);
        }
        
        System.out.println(DP[1][G]);
    }
}