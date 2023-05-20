import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/*import java.io.StringReader;
import java.io.StringWriter;*/

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        try (//интерфейс пользователя (заполнение базы элементов с консоли, выбор типа элемента пользователем).
        Scanner scanner = new Scanner(System.in)) {
            ArrayList<Student> students = new ArrayList<>();
            while (true) {
                System.out.println("Выберите действие (1 - Ввод данных, 0 - Выход):");
                int choice = scanner.nextInt();
                if (choice == 0) {
                    break;
                } else {
                    // Запрашиваем данные у пользователя
                    System.out.print("Enter student first name: ");
                    String firstName = scanner.next();

                    System.out.print("Enter student last name: ");
                    String lastName = scanner.next();

                    System.out.print("Enter student age: ");
                    int age = scanner.nextInt();

                    System.out.print("Enter student course: ");
                    int course = scanner.nextInt();

                    System.out.print("Enter student specialty: ");
                    String specialty = scanner.next();

                    System.out.print("Enter student years left: ");
                    int yearsLeft = scanner.nextInt();

                    // Запрашиваем тип студента
                    System.out.print("Enter student type (B for Bachelor, M for Master): ");
                    String studentType = scanner.next();

                    // Создаем объект нужного класса и добавляем его в список студентов
                    if (studentType.equals("B")) {
                        students.add(new Bachelor(firstName, lastName, age, course, specialty, yearsLeft));
                    } else if (studentType.equals("M")) {
                        students.add(new Master(firstName, lastName, age, course, specialty, yearsLeft));
                    } else {
                        System.out.println("Invalid student type");
                    }
                }
            }
            // Выводим информацию о всех студентах
            System.out.println("All students:");
            for (Student student : students) {
                System.out.println(student);
            }

            // Создаем два массива для хранения объектов
            ArrayList<Bachelor> bachelors = new ArrayList<>();
            ArrayList<Master> masters = new ArrayList<>();

            // Разбиваем исходный массив на два массива, в которых будут храниться однотипные элементы
            for (Student student : students) {//перебор всех объектов
                //Оператор instanceof позволяет проверить, принадлежит ли объект указанному классу, с учётом наследования.
                // Является ли класс реализацией интерфейса
                if (student instanceof Bachelor) {
                    bachelors.add((Bachelor) student );
                } else {
                    masters.add((Master) student);
                }
            }

            // Находим объекты которым осталось учится одинаковое количество лет
            ArrayList<Student> same_years = new ArrayList<>();
            for (int i = 0; i < students.size(); i++) {
                for (int j = i + 1; j < students.size(); j++) {

                    if (students.get(i).Years_left() == students.get(j).Years_left()) {
                        if (!same_years.contains(students.get(i))) {
                            same_years.add(students.get(i));
                        }
                        if (!same_years.contains(students.get(j))) {
                            same_years.add(students.get(j));
                        }
                    }
                }
                System.out.println("Осталось учиться "+ students.get(i).Years_left()+" год(а)");
            }

            // Выводим полную информацию обо всех объектах
            for (Student student : same_years) {
                System.out.println(student.toString());
            }

            // Вывод бакалавров
            System.out.println("Bacherols");
            for (Bachelor bachelor : bachelors) {
                System.out.println(bachelor);
            }

            // Выводим магистров
            System.out.println("Maters");
            for (Master master : masters) {
                System.out.println(master);
            }
        }
        Bachelor bachelor = new Bachelor();
        try {
            bachelor.setAge(-2);
        } catch (InvalidAgeException error) {
            System.out.println("Error: " + error.getMessage());
        }

        ArrayList<Student> stud = new ArrayList<>();
        stud.add(new Bachelor("Yulia","Finoshina",21,3,"Moais",4));
        stud.add(new Bachelor("Larisa","Somova",20,1,"Applied mechanics",2));

        //Сериализация
        /*
        создается объект типа Bachelor, сериализуется в байтовый поток, затем из этого
        потока десериализуется новый объект.
        В конце выводятся размер сериализованного объекта и строковое
        представление десериализованного объекта.
         */
        Bachelor b = new Bachelor("John", "Doe", 20, 3, "Computer Science", 4);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Serialize.serialize(b, baos);
        byte[] bytes = baos.toByteArray();
        System.out.println(bytes);
        // Десериализация объекта
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Student st = Serialize.deserialize(bais);
        System.out.println("Deserialized object: " + st.toString());
        //Bachelor newB = (Bachelor) Serialize.deserialize(new ByteArrayInputStream(bytes));
        //System.out.println("Deserialized object: " + newB.toString());

        // Запись в байтовый поток
        OutputStream outputStream = new FileOutputStream("student.txt");
        Bachelor.outputST(stud.get(0), outputStream);
        outputStream.close();
        
         // Чтение из байтового потока
        InputStream inputStream = new FileInputStream("student.txt");
        Student studentFromFile = Bachelor.inputST(inputStream);
        inputStream.close();
        System.out.println("inputST: ");
        System.out.println(studentFromFile.toString());

        // Запись в символьный поток
        Writer writer = new FileWriter("student1.txt");
        Bachelor.writeST(stud.get(1), writer);
        writer.close();

        //Чтение из символьного потока
        Reader reader = new FileReader("student1.txt");
        Bachelor bach = Bachelor.readST(reader);
        reader.close();
        System.out.println("readST: ");
        System.out.println(bach.toString());

    }
}
