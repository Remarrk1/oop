
import java.io.*;

import java.util.Objects;


//класс Магистр реализует интерфейс Студент
public class Master implements Student, Serializable {

    private String Name;
    private String Surname;
    private int Age;
    private int Course;
    private String Specialization;
    private int Years_of_study;


    // Конструкторы
    public Master() {
        this.Name="Sasha";
        this.Surname="Galchenko";
        this.Age=20;
        this.Course=3;
        this.Specialization="MOAIS";
        this.Years_of_study=4;
    }

    public Master(String Name, String Surname,int Age, int Course, String Specialization, int Years_of_study){
        this.Name=Name;
        this.Surname=Surname;
        this.Age=Age;
        this.Course=Course;
        this.Specialization=Specialization;
        this.Years_of_study=Years_of_study;
    }

    @Override
    public String getName() {
        return Name;
    }
    @Override
    public String getSurname() {
        return Surname;
    }
    @Override
    public int getAge() {
        return Age;
    }
    @Override
    public int getCourse() {
        return Course;
    }
    @Override
    public String getSpecialization() {
        return Specialization;
    }

    @Override
    public int getYears_of_study() {
        return Years_of_study;
    }


    @Override
    public void setName(String Name) {
        this.Name=Name;
    }

    @Override
    public void setSurname(String Surname) {
        this.Surname=Surname;
    }

    @Override
    // Объявляемое исключение
    public void setAge(int Age) throws InvalidAgeException {
        if (Age < 0) {
            throw new InvalidAgeException(Age);
        }
        this.Age=Age;
    }
    // Необъявляемое исключение
    @Override
    public void setCourse(int Course){
        if (Course < 0) {
            throw new InvalidCourseException(Course);
        }
        this.Course=Course;
    }

    @Override
    public void setSpecialization(String Specialization) {
        this.Specialization=Specialization;
    }

    @Override
    public void setYears_of_study(int Years_of_study) {
        this.Years_of_study=Years_of_study;

    }

    // Функциональный метод
    public int Years_left() {
        return (int) Years_of_study-Course;
    }

    // реализация метода output из интерфейса Student
    @Override
    public void output(OutputStream out) throws IOException  {
        ObjectOutputStream oos=new ObjectOutputStream(out);
        oos.writeObject(this);
    }
    // реализация метода write из интерфейса Student
    @Override
    public void write(Writer out) {
        PrintWriter pw=new PrintWriter(out);
        pw.print(Name + " ");
        pw.print(Surname + " ");
        pw.print(Age + " ");
        pw.print(Specialization + " ");
        pw.print(Course + " ");
        pw.print(Years_of_study + " ");
        pw.flush();
    }

    @Override
    public String toString() {
        return "Master{" +
                "Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", Age=" + Age +
                ", Course=" + Course +
                ", Specialization='" + Specialization + '\'' +
                ", Years_of_study=" + Years_of_study +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Master master = (Master) o;
        return Age == master.Age && Course == master.Course && Double.compare(master.Years_of_study, Years_of_study) == 0 && Objects.equals(Name, master.Name) && Objects.equals(Surname, master.Surname) && Objects.equals(Specialization, master.Specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Name, Surname, Age, Course, Specialization, Years_of_study);
    }


    public static void outputST(Student student, OutputStream out) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(student);
        //student.output(out);
    }
    // Статический метод чтения из байтового потока
    public static Student inputST(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(in);
        return (Student) ois.readObject();
    }

    // Статический метод записи в символьный поток
    public static void writeST(Student student, Writer out) throws IOException {
        student.write(out);
    }
    public static Master readST(Reader in) throws IOException {
        StreamTokenizer st = new StreamTokenizer(in);
        st.nextToken();
        String name = st.sval;
        st.nextToken();
        String surname = st.sval;
        st.nextToken();
        int age = (int) st.nval;
        st.nextToken();
        int course = (int) st.nval;
        st.nextToken();
        String specialization = st.sval;
        st.nextToken();
        int yearsOfStudy = (int) st.nval;
        return new Master(name, surname, age, course, specialization, yearsOfStudy);
    }

}
