package com.JavaStep.Homeworks;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Task implements Serializable {

    private static List<Task> taskList = new ArrayList<>();
    private static BufferedReader reader;

    private String name;
    private String description;
    private Priority priority;
    private LocalDate startDate;
    private LocalDate endDate;
    private String executor;
    private boolean isDone;

    public static Task createTask() throws IOException {
        Task task = new Task();
        reader = new BufferedReader(new InputStreamReader(System.in));

        task.setName();
        task.setDescription();
        task.setPriority();
        task.setStartDate();
        task.setEndDate();
        task.setExecutor();
        task.isDone = false;

        taskList.add(task);

        return task;

    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void closeTheTask() {
        if (isDone) {
            taskList.remove(this);
        }
    }

    public static List<Task> getTaskList() {
        return taskList;
    }

    public static void saveToFile(String name) throws IOException {
        String dirName = System.getProperty("user.dir");
        String fullName = dirName + "\\" + name;
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fullName));
        out.writeObject(taskList);
        out.close();
    }

    public static void loadFromFile(String name) throws IOException, ClassNotFoundException {
        String dirName = System.getProperty("user.dir");
        String fullName = dirName + "\\" + name;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fullName));
        taskList = (List) in.readObject();
    }

    private void setName() throws IOException {
        System.out.print("Введите название для задачи: ");
        name = reader.readLine();
    }

    private void setDescription() throws IOException {
        System.out.print("Введите описание для задачи: ");
        description = reader.readLine();
    }

    private void setPriority() throws IOException {
        System.out.print("Введите приоритет для задачи (0 - низкий, 1 - средний, 2 - высокий):  ");
        String str = reader.readLine();
        if (str.equals("0")) priority = Priority.LOW_PRIORITY;
        else if (str.equals("1")) priority = Priority.MEDIUM_PRIORITY;
        else priority = Priority.HIGH_PRIORITY;
    }

    private void setStartDate() throws IOException {
        System.out.print("Введите дату создания задачи в формате год, месяц, день (Пример: 2022, 1, 17): ");
        String str = reader.readLine();
        String[] date = str.split(", ");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);
        startDate = LocalDate.of(year, month, day);
    }

    private void setEndDate() throws IOException {
        System.out.print("Введите количество дней необходимых для выполнения задачи: ");
        String str = reader.readLine();
        endDate = startDate.plusDays(Integer.parseInt(str));
    }

    private void setExecutor() throws IOException {
        System.out.print("Введите имя исполнителя: ");
        executor = reader.readLine();
    }


    public enum Priority {
        LOW_PRIORITY,
        MEDIUM_PRIORITY,
        HIGH_PRIORITY
    }

    @Override
    public String toString() {
        return "Task{" +
                "\nЗадача: '" + name + '\'' +
                "\nОписание задачи: '" + description + '\'' +
                "\nПриоритет: " + priority +
                "\nДата создания: " + dateFormat(startDate) +
                "\nДата окончания: " + dateFormat(endDate) +
                "\nИсполнитель: '" + executor + '\'' +
                "\nОтметка о выполнении: " + isDone + '\n' +
                '}';
    }

    private String dateFormat(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String formattedString = date.format(formatter);
        return formattedString;
    }
}
