package test;

class Main{
    private static int n = 0;

    public static void main(String[] args) {
        Exercise exercise = new Exercise();
        for (int i = 0; i < 10; i++) {
            Process process = new Process(exercise, i, (int)(Math.random()*10));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("percent of successfully process = " + (n*10) + "%");
    }

    public static void count(){
        n++;
    }
}