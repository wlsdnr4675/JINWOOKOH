package shop.jinwookoh.api.common.dummyGenerator;

import java.util.Comparator;

import java.util.stream.Stream;
import static shop.jinwookoh.api.common.util.proxy.*;

class Artist {
    private String name;
    private int grade;
    private int score;

    private String username;

    public Artist(String name, int grade, int score, String username) {
        this.name = name;
        this.grade = grade;
        this.score = score;
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getGrade() {
        return grade;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}

public class ArtistMain {
    public static void main(String... args) {

        // new StreamUtil().arrayToList(new String[] { "a", "b", "v"
        // }).forEach(System.out::println);
        // System.out.println(RefUtil.strToInt.apply("1"));
    }

    public static Stream<Student> makeStream() {

        DummyGenerator dum = new DummyGenerator();

        return Stream.of(
                new Student(dum.makeName(), rangeBelowRandom.apply(1, 3), rangeBelowRandom.apply(100, 300),
                        dum.makeUsername()),
                new Student(dum.makeName(), rangeBelowRandom.apply(1, 3), rangeBelowRandom.apply(100, 300),
                        dum.makeUsername()),
                new Student(dum.makeName(), rangeBelowRandom.apply(1, 3), rangeBelowRandom.apply(100, 300),
                        dum.makeUsername()),
                new Student(dum.makeName(), rangeBelowRandom.apply(1, 3), rangeBelowRandom.apply(100, 300),
                        dum.makeUsername()),
                new Student(dum.makeName(), rangeBelowRandom.apply(1, 3), rangeBelowRandom.apply(100, 300),
                        dum.makeUsername()),
                new Student(dum.makeName(), rangeBelowRandom.apply(1, 3), rangeBelowRandom.apply(100, 300),
                        dum.makeUsername()));
    }

    public static Stream<Student> ascGrade() {
        return makeStream().sorted(Comparator.comparing(Student::getGrade).thenComparing(Comparator.reverseOrder()));
    }

    public static Stream<Student> ascScore() {
        return makeStream().sorted(Comparator.comparing(Student::getScore).thenComparing(Comparator.reverseOrder()));
    }

    public static Stream<Student> ascName() {

        return makeStream().sorted(Comparator.comparing(Student::getName).thenComparing(Comparator.reverseOrder()));
    }

    public static Stream<Student> ascUsername() {
        return makeStream().sorted(Comparator.comparing(Student::getUsername).thenComparing(Comparator.reverseOrder()));
    }

    public static Stream<Student> descScore() {

        return makeStream().sorted(Comparator.comparing(Student::getScore).reversed());
    }

    // public static Stream<Student> upperCase() {
    // }

}
