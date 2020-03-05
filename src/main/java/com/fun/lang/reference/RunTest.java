package com.fun.lang.reference;

/**
 * Created by Administrator
 *
 * @date 2017/4/27.
 */
public class RunTest {

    public static void main(String[] args) {

        ClassRoom room = new ClassRoom("wl1001");
        Student s1 = new Student("fun",room);
        System.out.println(s1);

        changeStudent(s1);
        System.out.println(s1);

        changeStudentNew(s1);
        System.out.println(s1);

        changeStudentFinal(s1);
        System.out.println(s1);

        changeStudentFinalNew(s1);
        System.out.println(s1);

        deepCopyChange(s1);
        System.out.println(s1);

    }

    public static void changeStudent(Student s){
        s.getRoom().setName("WL1001");
        s.setName("funge");
    }

    public static void changeStudentNew(Student s){
        Student student = s;
        student.getRoom().setName("new");
        student.setName("fun new");
    }

    public static void changeStudentFinal(final Student s){
        s.getRoom().setName("final");
        s.setName("fun final");
    }

    public static void changeStudentFinalNew(final Student s){
        Student student = s;
        student.getRoom().setName("final and new");
        student.setName("fun final new");
    }

    public static void changeStudentFinalChange(final Student s){
//        s = new Student("fun",new ClassRoom("WL1002")); // error
    }

    public static void deepCopyChange(Student s){
        Student student = (Student) DeepCopyUtil.copy(s);
        student.getRoom().setName("deep copy classroom");
        student.setName("deep copy name");
    }

}
