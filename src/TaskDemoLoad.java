import java.io.IOException;
import java.util.List;

public class TaskDemoLoad {
    public static void main(String[] args) {
        try {
            Task.loadFromFile("saveTask.ser");
            List<Task> list = Task.getTaskList();
            list.forEach(System.out::println);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
