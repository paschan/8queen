//Class for N-queens Problem
public class EightQueen {
    private int row;
    private int column;

    public EightQueen(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void move () {
        row++;
    }

    public boolean ifConflict(EightQueen q){
        //  Check rows and columns
        if(row == q.getRow() || column == q.getColumn())
            return true;
            //  Check diagonals
        else if(Math.abs(column-q.getColumn()) == Math.abs(row-q.getRow()))
            return true;
        return false;
    }
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}