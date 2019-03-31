package main;

import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        int n = enterNumber("введіть N");
        VectorB vectorB = new VectorB(n);
        vectorB.start();
        MatrixC matrixC = new MatrixC(n);
        matrixC.start();
        ArithmeticThread arithmeticThread0 = new ArithmeticThread(n);
        arithmeticThread0.start();
        ArithmeticThread arithmeticThread1 = new ArithmeticThread(n);
        arithmeticThread1.start();
        ArithmeticThread arithmeticThread2 = new ArithmeticThread(n);
        arithmeticThread2.start();
        CreateMatrix createMatrix0 = new CreateMatrix(n, "A0");
        createMatrix0.start();
        CreateMatrix createMatrix1 = new CreateMatrix(n, "A1");
        createMatrix1.start();
        CreateMatrix createMatrix2 = new CreateMatrix(n, "A2");
        createMatrix2.start();
        CreateMatrix createMatrix3 = new CreateMatrix(n, "B2");
        createMatrix3.start();
        CreateVector createVector0 = new CreateVector(n, "b1");
        createVector0.start();
        CreateVector createVector1 = new CreateVector(n, "c1");
        createVector1.start();

        arithmeticThread0.matrixMultiplyVector(createMatrix0.getMatrix(), vectorB.getVectorB()); //A0*b

        arithmeticThread1.vectorPlusVector(createVector0.getVector(), createVector1.getVector()); //b1+c1

        arithmeticThread2.matrixMinusMatrix(createMatrix3.getMatrix(), matrixC.getMatrixC());//B2-C2

        arithmeticThread1.matrixMultiplyVector(createMatrix1.getMatrix(), arithmeticThread1.getResultVector());//A1*(b1+c1)

        arithmeticThread2.matrixMultiplyMatrix(createMatrix2.getMatrix(), arithmeticThread2.getResultMatrix());//A2*(B2-C2)


        double[] vectorY1 = arithmeticThread0.getResultVector();
        double[] vectorY2 = arithmeticThread1.getResultVector();
        double[][] matrixY3 = arithmeticThread2.getResultMatrix();

        arithmeticThread0.vectorPlusVector(vectorY1, vectorY2);//y1+y2

        arithmeticThread1.matrixMultiplyMatrix(matrixY3, matrixY3);//Y3*Y3

        arithmeticThread0.matrixMultiplyVector(matrixY3, arithmeticThread0.getResultVector()); //Y3*(y1+y2)

        arithmeticThread1.matrixMultiplyVector(arithmeticThread1.getResultMatrix(), vectorY2); // (Y3*Y3)*y2

        arithmeticThread2.vectorPlusVector(arithmeticThread0.getResultVector(), arithmeticThread1.getResultVector());

        double[] x = arithmeticThread2.getResultVector();

        show(x, "результат");
        vectorWriter(x);
        arithmeticThread0.exit();
        arithmeticThread1.exit();
        arithmeticThread2.exit();
    }

    public static synchronized int enterNumber(String string) {
        try {
            System.out.print(string + ":  ");
            int num = Integer.parseInt(br.readLine());
            System.out.println();
            return num;
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
            enterNumber(string);
        }
        return 0;
    }


    public static synchronized String enterString(String string) {
        try {
            System.out.print(string + ":  ");
            String str = br.readLine();
            System.out.println();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            enterString(string);
        }
        return null;
    }

    public static synchronized void mairixWriter(double[][] matrix) {
        String string = enterString("введіть назву файла");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(string))) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    String s = matrix[i][j] + " ";
                    stringBuilder.append(s);
                }
                stringBuilder.append("\r\n");
            }
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void vectorWriter(double[] vector) {
        String string = enterString("введіть назву файла");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(string))) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < vector.length; i++)
                stringBuilder.append(vector[i]).append(" \r\n");
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void show(double[][] matrix, String string) {
        System.out.println(string + ": ");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static synchronized void show(double[] vector, String string) {
        System.out.print(string + ": ");
        for (double aVector : vector) {
            System.out.print(aVector + "  ");
        }
        System.out.println();
    }
}
