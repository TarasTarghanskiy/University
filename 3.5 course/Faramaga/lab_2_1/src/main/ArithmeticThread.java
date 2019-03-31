package main;

public class ArithmeticThread extends Thread {
    private int n;
    private int number;
    private double[][] resultMatrix;
    private double[] resultVector;
    private double[][] matrix2;
    private double[][] matrix1;
    private double[][] matrix0;
    private double[] vector1;
    private double[] vector0;

    public ArithmeticThread(int n) {
        this.n = n;
    }

    public synchronized void exit(){
        number = 9;
    }

    public synchronized void matrixMultiplyVector(double[][] matrix0, double[] vector0) {
        resultVector = null;
        this.matrix0 = matrix0;
        this.vector0 = vector0;
        number = 2;
    }


    public synchronized void vectorPlusVector(double[] vector0, double[] vector1) {
        resultVector = null;
        this.vector0 = vector0;
        this.vector1 = vector1;
        number = 4;
    }

    public synchronized void matrixMultiplyMatrix(double[][] matrix0, double[][] matrix1) {
        resultMatrix = null;
        this.matrix0 = matrix0;
        this.matrix1 = matrix1;
        number = 1;
    }

    public synchronized void matrixMinusMatrix(double[][] matrix0, double[][] matrix1) {
        resultMatrix = null;
        this.matrix0 = matrix0;
        this.matrix1 = matrix1;
        number = 3;
    }

    public synchronized double[][] getResultMatrix() {
        while (resultMatrix == null) {
            try {
                join(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return resultMatrix;
    }

    public synchronized double[] getResultVector() {
        while (resultVector == null) {
            try {
                join(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return resultVector;
    }

    @Override
    public void run() {
        while (true) {
            switch (number) {
                case 1: // A*B
                    matrix2 = new double[n][n];
                    for (int i = 0; i < n; i++)
                        for (int j = 0; j < n; j++)
                            for (int k = 0; k < n; k++)
                                matrix2[i][j] += matrix0[i][k] * matrix1[k][j];
                    Main.show(matrix2, "множу матрицю на матрицю" );
                    resultMatrix = matrix2;
                    number = 0;
                    break;
                case 2: // A*b
                    vector1 = new double[n];
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            vector1[i] += matrix0[i][j] * vector0[j];
                        }
                    }
                    Main.show(vector1, "множу матрицю на вектор");
                    resultVector = vector1;
                    number = 0;
                    break;
                case 3: // A-B
                    for (int i = 0; i < n; i++)
                        for (int j = 0; j < n; j++)
                            matrix0[i][j] = matrix0[i][j] - matrix1[i][j];
                    number = 0;
                    Main.show(matrix0, "віднімаю матриці");
                    resultMatrix = matrix0;
                    break;
                case 4:
                    for (int i = 0; i < n; i++)
                        vector0[i] = vector0[i] + vector1[i];
                    resultVector = vector0;
                    number = 0;
                    Main.show(vector0, "додаю вектори");
                    break;
                case 9:
                    return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


