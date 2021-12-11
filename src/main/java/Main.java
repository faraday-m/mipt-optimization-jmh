import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class Main {
    public static void main(String[] args) {

        try {
            new Runner(new OptionsBuilder().forks(1).build()).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}

