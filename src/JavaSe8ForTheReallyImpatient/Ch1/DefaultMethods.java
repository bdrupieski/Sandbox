package JavaSe8ForTheReallyImpatient.Ch1;

public class DefaultMethods {

    public static void main(String[] args) {
        PersonThing pt = new PersonThing();
        System.out.println(pt.getName());
    }
}

interface Person {
    default String getName() {
        return "name from Person";
    }
}

interface Thing {
    default String getName() {
        return "name from Thing";
    }
}

class PersonThing implements Person, Thing {

    @Override
    public String getName() {
        return Person.super.getName();
    }

}