package test;

public class Exercise {
    private boolean isAviable;

    public Exercise() {
        this.isAviable = true;
    }

    synchronized public void doExercise(int name, int number) {
        if (isAviable) {
            Main.count();
            isAviable = false;
            System.out.println("process number " + name + "(" + number + ")" + " start");
            for (int i = 0; i < number; i++) {
                try {
                    Thread.sleep(1000);
                    wait(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("process number " + name + "(" + number + ")" + " finish");
            isAviable = true;
        } else
            System.out.println("method is not available for process number " + name + "(" + number + ")");
    }

    public boolean isAviable() {
        return isAviable;
    }
}
