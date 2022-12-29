import exception.IncorrectTaskParameterException;
import exception.TaskNotFoundException;
import servise.TaskServise;
import task.*;
import util.Constant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;

import static servise.TaskServise.add;

public class Main {
    private static final Pattern DATE_TIME_PATTERN=Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}");
    private static final Pattern DATE_PATTERN=Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            removeTask(scanner);
                            break;
                        case 3:
                            printTaskByDay(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }

    }

    private static void printTaskByDay(Scanner scanner) {
        do {
            System.out.println("Введите дату ");
            if (scanner.hasNext(DATE_PATTERN)) {
                LocalDate day = parseDate(scanner.next(DATE_PATTERN));
                if( day==null){
                    System.out.println(" Не корректныйформат даты! ");
                    continue;
                }
                Collection<Task1> taskByDay =TaskServise.getTaskByDay(day);
                if (taskByDay.isEmpty()){
                    System.out.println("Задачи на "+day.format(Constant.DATE_FORMATTER)+" не найдены");
                }else {
                    System.out.println("Задачи на "+day.format(Constant.DATE_FORMATTER)+" : ");
                    for (Task1 task1: taskByDay){
                        System.out.println(task1);
                    }
                    break;
                }
            } else {
                scanner.next();
            }
        }
        while (true);
    }
    private static void removeTask(Scanner scanner) {
        try {
            do {
                System.out.println("Введите id задачи ");
                if (scanner.hasNextInt()) {
                    int id = scanner.nextInt();
                    TaskServise.removeById(id);
                    System.out.println("Задача удалена");
                    break;
                    } else {
                        scanner.next();
                    }
                }
                while (true);
            } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String titel = scanner.next();
        System.out.print("Введите описание задачи: ");
        String deskription = scanner.next();
        Type type= inputType(scanner);
        LocalDateTime dateTime=inputDateTime(scanner);
        Repiatability repiatability=inputRepiatability(scanner);
        Task1 task1=new Task1(titel, deskription, type,dateTime,repiatability);
        add(task1);
        System.out.println("Задача"+task1+ "добавлена. ");
    }

    private static Type inputType(Scanner scanner) {

        Type type;
        do {
            System.out.println("Введите тип задачи: 1 личная\n 2 рабочая\n Тип задачи: ");
            if (scanner.hasNextInt()) {
                int namber = scanner.nextInt();
                if (namber != 1 && namber != 2) {
                    continue;
                }
                if (namber == 1) {
                    type = Type.PERSONAL;
                } else {
                    type = Type.WORK;
                }
                break;
            } else {
                scanner.next();
            }
        }
        while (true);
        return type;
    }

    private static LocalDateTime inputDateTime(Scanner scanner) {
        LocalDateTime dateTime;
        do {
            System.out.println("Введите дату и время задачи в формате \"01.01.2020 12:00 \" ");
            if (scanner.hasNext(DATE_TIME_PATTERN)) {
                dateTime= parseDateTame(scanner.next(DATE_TIME_PATTERN));
                if (dateTime==null){
                    System.out.println(" Не корректныйформат даты и времени ");
                    continue;
                }
                break;
            } else {
                scanner.next();
            }
        }
        while (true);
        return dateTime;
    }
    private static LocalDateTime parseDateTame(String dateTime){
        try {
            return LocalDateTime.parse(dateTime, Constant.DATE_TIME_FORMATTER);
        }catch (DateTimeParseException e){
            return null;
        }
    }

    private static LocalDate parseDate(String date){
        try {
            return LocalDate.parse(date, Constant.DATE_FORMATTER);
        }catch (DateTimeParseException e){
            return null;
        }
    }

    private static Repiatability inputRepiatability(Scanner scanner) {

        Repiatability repiatability;
        do {
            System.out.println("Введите тип повторяемости задачи: 1 Однократная\n 2 Ежедневная\n3 Еженедельная\n " +
                    "4 Ежемесячная\n5 Ежегодная ");
            if (scanner.hasNextInt()) {
                int namber = scanner.nextInt();
                if (namber < 1 || namber > 5) {
                    System.out.println("Введите цифру от 1 до 5!");
                    continue;
                }
                switch (namber){
                    default:
                    case 1:
                        repiatability=new Once();
                        break;
                    case 2:
                        repiatability=new Daily();
                        break;
                    case 3:
                        repiatability=new Weekly();
                        break;
                    case 4:
                        repiatability=new Monthly();
                        break;
                    case 5:
                        repiatability=new Annually();
                        break;
                }
            } else {
                scanner.next();
            }
        }
        while (true);
    }

    private static void printMenu() {
        System.out.println(
                """
                        1. Добавить задачу
                        2. Удалить задачу
                        3. Получить задачу на указанный день
                        0. Выход
                        """
        );
    }


}