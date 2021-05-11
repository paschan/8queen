import java.util.Random;
public class EightQueenHillClimbing {
    private static int n ;
    private static int steps =0;
    private static int heuristic = 0;
    private static int randomRestarts = 0;
    public static EightQueen[] generateBoard() {
        EightQueen[] startBoard = new EightQueen[n];
        Random rndm = new Random();
        for(int i=0; i<n; i++){
            startBoard[i] = new EightQueen(rndm.nextInt(n), i);
        }
        return startBoard;
    }
    private static void printState (EightQueen[] state) {
        int[][] tempBoard = new int[n][n];
        for (int i=0; i<n; i++) {
            tempBoard[state[i].getRow()][state[i].getColumn()]=1;
        }
        System.out.println();
        for (int i=0; i<n; i++) {
            for (int j= 0; j < n; j++) {
                System.out.print(tempBoard[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static int findHeuristic (EightQueen[] state) {
        int heuristic = 0;
        for (int i = 0; i< state.length; i++) {
            for (int j=i+1; j<state.length; j++ ) {
                if (state[i].ifConflict(state[j])) {
                    heuristic++;
                }
            }
        }
        return heuristic;
    }
    public static EightQueen[] nextBoard (EightQueen[] presentBoard) {
        EightQueen[] nextBoard = new EightQueen[n];
        EightQueen[] tmpBoard = new EightQueen[n];
        int presentHeuristic = findHeuristic(presentBoard);
        int bestHeuristic = presentHeuristic;
        int tempH;

        for (int i=0; i<n; i++) {
            nextBoard[i] = new EightQueen(presentBoard[i].getRow(), presentBoard[i].getColumn());
            tmpBoard[i] = nextBoard[i];
        }
        for (int i=0; i<n; i++) {
            if (i>0)
                tmpBoard[i-1] = new EightQueen(presentBoard[i-1].getRow(), presentBoard[i-1].getColumn());
            tmpBoard[i] = new EightQueen(0, tmpBoard[i].getColumn());
            for (int j=0; j<n; j++) {
                tempH = findHeuristic(tmpBoard);
                if (tempH < bestHeuristic) {
                    bestHeuristic = tempH;
                    for (int k=0; k<n; k++) {
                        nextBoard[k] = new EightQueen(tmpBoard[k].getRow(), tmpBoard[k].getColumn());
                    }
                }
                if (tmpBoard[i].getRow()!=n-1)
                    tmpBoard[i].move();
            }
        }
        if (bestHeuristic == presentHeuristic) {
            randomRestarts++;

            nextBoard = generateBoard();
            heuristic = findHeuristic(nextBoard);
        } else
            heuristic = bestHeuristic;
        steps++;

        return nextBoard;
    }

    public static void main(String[] args) {
        System.out.println("Total number of Steps         Total Number Of Reset         Time Of Process");
        long[][] table = new long[26][3];
        int totalsteps=0;
        int totalreset=0;
        int totaltimer=0;
        long totaltime=0;
        for(int i=0;i<25;i++){
            long startTime = System.nanoTime();
            int presentHeuristic;
            n=8;
            EightQueen[] presentBoard = generateBoard();
            presentHeuristic = findHeuristic(presentBoard);
            while (presentHeuristic != 0) {
                presentBoard = nextBoard(presentBoard);
                presentHeuristic  = heuristic;

            }
            long endTime   = System.nanoTime();
            totaltime = endTime - startTime;
            totalreset+=randomRestarts;
            totaltimer+=totaltime;
            totalsteps+= steps;
            table[i][0]= steps;
            table[i][1]=randomRestarts;
            table[i][2]=totaltime;


            System.out.println(table[i][0]
                    +"                            "+table[i][1]
                    +"                            "+table[i][2]);

            steps =0;
            heuristic = 0;
            randomRestarts = 0;

        }
        table[25][0]=totalsteps/25;
        table[25][1]=totalreset/25;
        table[25][2]=totaltimer/25;
        System.out.println(table[25][0]
                +"                            "+table[25][1]
                +"                            "+table[25][2]);


    }
}
