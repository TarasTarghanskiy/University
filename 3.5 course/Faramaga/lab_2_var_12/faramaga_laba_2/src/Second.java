public class Second extends Thread {
    private int n;
    private double[] ySecondColumn;
    private boolean isReady;

    public Second(int n) {
        this.n = n;
        isReady = false;
    }

    synchronized double[] getySecondColumn(){
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return ySecondColumn;
    }

    private void createYColumn() {
        int[][] aMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                aMatrix[i][j] = (int)((Math.random()*10)+1);
            }
        }
        double[] bColumn = new double[n];
        double[] cColumn = new double[n];
        for (int i = 0; i < n; i++) {
            bColumn[i] = (int) ((Math.random() * 10) + 1);
            cColumn[i] = (int) ((Math.random() * 10) + 1);
        }

        ySecondColumn = new double[n];
        double[] helpColumn = new double[n];

        for (int i = 0; i < n; i++)
            helpColumn[i] = (12 * bColumn[i]) - cColumn[i];

        for (int i = 0; i < n; ++i)
            for (int k = 0; k < n; ++k)
                ySecondColumn[i] += aMatrix[i][k] * helpColumn[k];
        isReady = true;
    }


    @Override
    public void run() {
        createYColumn();
        System.out.println("second done");
    }
}
