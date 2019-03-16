package test;

public class Process implements Runnable{
    private Exercise exercise;
    private Thread thread;
    private int name;
    private int number;

    public Process(Exercise exercise, int name, int number) {
        this.name = name;
        this.number = number;
        this.exercise = exercise;
        thread = new Thread(this);
        thread.start();
    }

    public boolean ExerciseIsAvailable(){
        return exercise.isAviable();
    }

    @Override
    public void run() {
        exercise.doExercise(name, number);
    }
}
