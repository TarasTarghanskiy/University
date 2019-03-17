
public class First extends Thread {

    private double[] yFirstColumn;
    private boolean isReady;
    private int n;

    public First(int n ) {
        this.n = n;
        isReady = false;
    }

    synchronized double[] getyFirstColumn(){
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return yFirstColumn;
    }

    private void createYColumn() {
        int[][] aMatrix = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                aMatrix[i][j] = (int) ((Math.random() * 10) + 1);
        double[] bColumn = new double[n];
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) bColumn[i] = ((i + 1) * (i + 1)) / 12d;
            else bColumn[i] = i;
        }
        yFirstColumn = new double[n];
        for (int i = 0; i < n; ++i)
            for (int k = 0; k < n; ++k)
                yFirstColumn[i] += aMatrix[i][k] * bColumn[k];
        isReady = true;
    }

    @Override
    public void run() {
        createYColumn();
        System.out.println("first done");
    }
}
