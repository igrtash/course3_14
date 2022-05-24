/** Урок 12 Stream API - основы
 * Практическое упражнение 14 - Stream API
 * Создать коллекцию и заполнить её.
 * Продемонстрировать умение пользоваться потоками.
 * • Вывести отсортированную коллекцию. (по возрастанию и
 * убыванию)
 * • Отфильтровать коллекцию по полю.
 * • Отсортировать коллекцию и сгруппировать получившийся
 * результат.
 * • Выбрать минимальный и максимальный объект.
 * • Использовать AllMatch, AnyMatch, NoneMatch.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Student> students = null;
        try {
            students = setStudents("data/students.csv"); //загрузка данных из csv файла
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        //students.forEach(System.out::println);

        //отсортировать коллекцию по возрастанию
        Comparator<Student> comparator = Comparator.comparing(Student::getName);
        System.out.println("\nСортировка по возрастанию ФИО\n");
        students.stream()
                .sorted(comparator)
                .forEach(System.out::println);

        //отсортировать коллекцию по убыванию
        System.out.println("\nСортировка по убыванию ФИО\n");
        students.stream()
                .sorted(comparator.reversed())
                .forEach(System.out::println);

        // Отфильтровать коллекцию по полю.
        System.out.println("\nФильтруем по 9 классу\n");
        students.stream()
                .filter(student -> student.getNclass() == 9)
                .forEach(System.out::println);

        System.out.println("\nФильтруем по району Лужский\n");
        students.stream()
                .filter(student -> student.getDistrict().equalsIgnoreCase("Лужский"))
                .forEach(System.out::println);

        // Отсортировать коллекцию и сгруппировать получившийся результат.
        System.out.println("\nОтсортировать коллекцию по ФИО и сгруппировать по классу получившийся результат\n");
        Map<Integer, List<Student>> groupingByNclass =
                students.stream().sorted(Comparator.comparing(Student::getName))
                    .collect(Collectors.groupingBy(Student::getNclass));
        groupingByNclass.forEach((nclass, student)-> {
            System.out.println("nClass : " + nclass);
            student.forEach(s -> System.out.println("\t" + s));
        });

        //Optional<List<Student>> optionalStudents = Optional.of(setStudents("data/students.csv"));
        //if (optionalStudents.isPresent()) {
        //    System.out.println("isPresent=true\n");
        //}
        //optionalStudents.get().forEach(System.out::println);

        //Optional<List<Student>> optionalStudents = Optional.ofNullable(null);
        //List<Student> list = optionalStudents.orElse(setStudents("data/students.csv"));
        //list.forEach(System.out::println);

        // Выбрать минимальный и максимальный объект.
        System.out.println("\nВыбрать минимальный и максимальный объект\n");
        System.out.println("count : " + students.stream().count());
        System.out.println("nclass min : " + students.stream().min(Comparator.comparing(Student::getNclass)));
        System.out.println("nclass max : " + students.stream().max(Comparator.comparing(Student::getNclass)));
        // Использовать AllMatch, AnyMatch, NoneMatch.
        System.out.println("\nИспользование AllMatch, AnyMatch, NoneMatch\n");
        System.out.println(students.stream().allMatch(student -> !student.getLesson().equals("Итальянский язык")));
        System.out.println(students.stream()
                .filter(student -> student.getDistrict().equalsIgnoreCase("Лужский"))
                .allMatch(student -> student.getDistrict().equals("Лужский")));
        System.out.println(students.stream().anyMatch(student -> student.getDistrict().equals("Гатчинский")));
        System.out.println(students.stream().noneMatch(student -> student.getLesson().equals("Информатика")));
        System.out.println(students.stream().noneMatch(student -> student.getLesson().equals("Итальянский язык")));
    }

    public static ArrayList<Student> setStudents(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        // считываем построчно
        String line = null;
        Scanner sc = null;
        int index = 0;
        List<Student> students = new ArrayList<>();
        // считываем заголовок
        line = reader.readLine();
        // считываем данные
        while ((line = reader.readLine()) != null) {
            Student student = new Student();
            sc = new Scanner(line);
            sc.useDelimiter(";");
            while (sc.hasNext()) {
                String data = sc.next();
                if (index == 0)
                    student.setNrow(Integer.parseInt(data));
                else if (index == 1)
                    student.setLesson(data);
                else if (index == 2)
                    student.setName(data);
                else if (index == 3)
                    student.setStatus(data);
                else if (index == 4)
                    student.setSchool(data);
                else if (index == 5)
                    student.setNclass(Integer.parseInt(data));
                else if (index == 6)
                    student.setDistrict(data);
                else
                    System.out.println("Некорректные данные::" + data);
                index++;
            }
            index = 0;
            students.add(student);
        }
        reader.close();
        return (ArrayList<Student>) students;
    }

}
