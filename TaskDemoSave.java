package com.JavaStep.Homeworks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskDemoSave {
    public static void main(String[] args) {

        try {
            Task.createTask();
            Task.createTask();
            Task.createTask();

            List<Task> tasks = Task.getTaskList();

            System.out.println("\nЛист задач после инициализации\n\t=======================\n");
            tasks.forEach(System.out::println);

            // установим флажок isDone для 2 задачи в списке в положение true
            Task taskToRemove = tasks.get(1);
            taskToRemove.setDone(true);
            // удалим выполненную задачу из списка
            taskToRemove.closeTheTask();
            System.out.println("Лист задач после выполнения одной из задач\n\t=======================\n");
            tasks.forEach(System.out::println);

            Task.saveToFile("saveTask.ser");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
