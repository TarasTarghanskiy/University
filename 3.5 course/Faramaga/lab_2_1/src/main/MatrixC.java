package main;

public class MatrixC extends Thread{
    private int n;
    private double[][] matrixC;


    public MatrixC(int n) {
        this.n = n;
    }

    public synchronized double[][] getMatrixC() {
        while (matrixC == null){
            try {
                join(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return matrixC;
    }

    @Override
    public void run() {
        double [][] matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = 1.0/((i+1)+(2*(j+1)));
            }
        }
        Main.show(matrix, "матриця С3 готова");
        this.matrixC = matrix;
    }
}
