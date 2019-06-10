package com.study.lxl.springboot.task.test;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Lukas
 * @since 2019/6/5 20:52
 **/
public class CollectionTest {

    static Random random = new Random();
    private static List<Integer> list = new ArrayList<>();

    private static List<Student> students = new ArrayList<>();

    @BeforeClass
    public static void testBefore() {}
    {
        for (int i=0;i<10;i++){
            list.add(random.nextInt(100));

            students.add(new Student(i+12,"name"+i, i%3));

        }


    }

    @Test
    public void test1() {
//        list.forEach(i -> System.out.print(i+" "));
        System.out.println();
        list.forEach((Integer e) -> System.out.print(e+" "));
        System.out.println();
        list.stream().sorted(((o1, o2) -> o1.compareTo(o2))).forEach(integer -> System.out.print(integer+" "));

        System.out.println();
        List<Integer> notOddList = list.stream().filter(integer -> integer%2==1).collect(Collectors.toList());
        notOddList.forEach((i) -> System.out.print("odd "+i+" "));


        final int result = list.stream().filter(t -> t%2 == 0).mapToInt(Integer::intValue).sum();

        System.out.println("\nresult = "+result+"\n");

        list.stream().map(t -> t*t).collect(Collectors.toList()).forEach(System.out::println);
    }

    @Test
    public void test2() {
        students.stream().map(student -> student.classId).distinct().collect(Collectors.toList()).forEach(System.out::println);

        final Collection<List<Student>> lists = students.stream().collect(Collectors.groupingBy(Student::getClassId)).values();

        for (List<Student> studentList : lists) {
            System.out.println("---------------------\n");
            studentList.stream().forEach(student -> System.out.print(student.toString()));
        }

        final int minAge = students.stream().mapToInt(Student::getAge).min().getAsInt();
        System.out.println("\nmin age ="+minAge);

        final int maxAge = students.stream().mapToInt(Student::getAge).max().getAsInt();
        System.out.println("max age ="+maxAge);

        Stream.of("a,b,c").map(String::toUpperCase).forEach((System.out::println));
        Stream.of("a,b,d", "a,b,c", "z,x,y").sorted(((o1, o2) -> o2.compareTo(o1))).forEach(System.out::println);
    }

    class Student implements Serializable {
        private Integer age;
        private String name ;
        private Integer classId;

        public Student(Integer age, String name, Integer classId) {
            this.age = age;
            this.name = name;
            this.classId = classId;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getClassId() {
            return classId;
        }

        public void setClassId(Integer classId) {
            this.classId = classId;
        }

        @Override
        public String toString() {
            return classId + " ===> age="+age+"name="+name;
        }
    }

}

/**
 * 函数式接口：只有一个函数的接口
 */
@FunctionalInterface
interface MyFunction{
    void run();

    default void test() {
        System.out.println("default static 方法不影响函数式接口的定义");
    }

    static void trace() {
        System.out.println("default static 方法不影响函数式接口的定义");
    }

}
