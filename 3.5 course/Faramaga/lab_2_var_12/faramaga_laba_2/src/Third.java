public class Third extends Thread {
    private double[][] yThirdMatrix;
    private int n;
    private First first;
    private Second second;

    public Third(int n, First first, Second second) {
        this.n = n;
        this.first = first;
        this.second = second;
    }

    private void createYMatrix() {
        double[][] aMatrix = new double[n][n];
        double[][] bMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                aMatrix[i][j] = ((Math.random() * 10) + 1);
                bMatrix[i][j] = ((Math.random() * 10) + 1);
            }
        }
        double[][] cMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cMatrix[i][j] = 1.0 / ((i + 1) * ((j + 1) * (j + 1)));
            }
        }

        yThirdMatrix = new double[n][n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    yThirdMatrix[i][j] += aMatrix[i][k] * (bMatrix[k][j] - cMatrix[k][j]);
    }

    private void stageTwo() {
        double[][] helpMatrixOne = new double[n][n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    helpMatrixOne[i][j] += yThirdMatrix[i][k] * yThirdMatrix[k][j]; //Y^2

        double[] helpColumn = new double[n];
        double[] yFirstColumn = first.getyFirstColumn();
        for (int i = 0; i < n; ++i)
            for (int k = 0; k < n; ++k)
                helpColumn[i] += helpMatrixOne[i][k] * yFirstColumn[k]; // (Y^2)*y1

        double[] ySecondColumn = second.getySecondColumn();
        for (int i = 0; i < n; i++) {
            helpColumn[i] += ySecondColumn[i]; // (Y^2)*y1 + y2
        }


        int helpNumber = 0;
        for (int k = 0; k < n; ++k)
            helpNumber += yFirstColumn[k] * helpColumn[k]; // y1' * ((Y^2)*y1 + y2)

        helpMatrixOne = new double[n][n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                    helpMatrixOne[i][j] += helpNumber * yThirdMatrix[i][j]; // (y1' * ((Y^2)*y1 + y2))*Y3

        double[][] helpMatrixTwo = new double[n][n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                helpMatrixTwo[i][j] += yFirstColumn[i] * ySecondColumn[j]; // y'y

        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                helpMatrixOne[i][j] += helpMatrixTwo[i][j]; // ((y1' * ((Y^2)*y1 + y2))*Y3) + y'y

        helpColumn = new double[n];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < n; ++j)
                helpColumn[i] += helpMatrixOne[i][j] * ySecondColumn[j]; // (((y1' * ((Y^2)*y1 + y2))*Y3) + y'y) * y2

        for (int i = 0; i < n; ++i)
            helpColumn[i] += ySecondColumn[i];

        System.out.println("result: ");
        for (int i = 0; i < n; i++) {
            if (n - i == 1) break;
            System.out.print(helpColumn[i] + ", ");
        }
    }

    @Override
    public void run() {
        createYMatrix();
        stageTwo();
        System.out.println("\nthird thread done");
    }
}

