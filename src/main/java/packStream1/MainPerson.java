package packStream1;

import java.util.*;
import java.util.stream.Collectors;

public class MainPerson {
    public static void main(String[] args) {
        System.out.println();

        Person person1 = new Person("Jacek", "Kowalski", 18, true);
        Person person2 = new Person("Jacek", "Górski", 15, true);
        Person person3 = new Person("Andżelika", "Dżoli", 25, false);
        Person person4 = new Person("Wanda", "Ibanda", 12, false);
        Person person5 = new Person("Marek", "Marecki", 17, true);
        Person person6 = new Person("Johny", "Brawo", 25, true);
        Person person7 = new Person("Stary", "Pan", 80, true);
        Person person8 = new Person("Newbie", "Noob", 12, true);
        Person person9 = new Person("Newbies", "Sister", 19, false);
        List<Person> list = new ArrayList<Person>(Arrays.asList(
                person1,
                person2,
                person3,
                person4,
                person5,
                person6,
                person7,
                person8,
                person9
        ));

        // - wypisz listę mężczyzn
        List<Person> listaMezczyzn = list.stream()
                .filter(person -> person.isMale()).collect(Collectors.toList());
        System.out.println("Lista mężczyzn:");
        listaMezczyzn.forEach(person -> System.out.println(person));

        // - uzyskaj listę dorosłych kobiet (filter)
        System.out.println();
        List<Person> listaDoroslychKobiet = list.stream().filter(person -> person.isMale() == false && person.getAge() >= 18)
                .collect(Collectors.toList());
        System.out.println("Lista dorosłych kobiet:");
        listaDoroslychKobiet.forEach(person -> System.out.println(person));

        // - uzyskaj Optional<Person> z dorosłym Jackiem findAny/findfirst
        System.out.println();
        Optional<Person> doroslyJacekOP = list.stream().filter(person -> person.getAge() >= 18 && person.getFirstName().equals("Jacek"))
                .findFirst();
        if (doroslyJacekOP.isPresent()) {
            System.out.println("Dorosły Jacek to: " + doroslyJacekOP.get());
        }

        // uzyskaj kolekcję wszystkich unikalnych imion z listy
        System.out.println();
        Set<String > kolekcjaImion = list.stream().map(name -> name.getFirstName()).collect(Collectors.toSet());
        System.out.println("Kolekcja unikalnych imion:");
        kolekcjaImion.forEach(name -> System.out.println(name));

        // uzyskaj kolekcję wszystkich unikalnych nazwisk z listy
        System.out.println();
        Set<String> kolekcjaNazwisk = list.stream().map(person -> person.getLastName()).collect(Collectors.toSet());
        System.out.println("Kolekcja unikalnych nazwisk:");
        kolekcjaNazwisk.forEach(naz -> System.out.println(naz));

        //- uzyskaj listę wieków wszystkich osób
        System.out.println();
        List<Integer> listaWiekow = list.stream().map(person -> person.getAge()).collect(Collectors.toList());
        System.out.println("Lista wieków osób z listy:");
        listaWiekow.forEach(System.out::println);

        // - uzyskaj listę wszystkich nazwisk osób, które są w przedziale wiekowym: 15-19 (filter)
        System.out.println();
        List<Person> listaPersonWWieku15_19 = list.stream().filter(person -> person.getAge() >= 15 && person.getAge() <= 19)
                .collect(Collectors.toList());
        List<String> listaNazwiskWWieku15_19 = listaPersonWWieku15_19.stream().map(person -> person.getLastName()).collect(Collectors.toList());
        System.out.println("Lista nazwisk osób w wieku 15-19:");
        listaNazwiskWWieku15_19.forEach(nazwisko -> System.out.println(nazwisko));

        // - * uzyskaj sumę wieku wszystkich osób (sum)
        System.out.println();
        int sumaWiekow = list.stream().mapToInt(person -> person.getAge()).sum();
        System.out.println("Suma wieków osób z listy: " + sumaWiekow);

        // - * uzyskaj średnią wieku wszystkich mężczyzn (average)
        System.out.println();
        OptionalDouble sredniaWiekuOD = list.stream().filter(person -> person.isMale()).mapToInt(person -> person.getAge())
                .average();
        if(sredniaWiekuOD.isPresent()) {
            double sredniaWieku = sredniaWiekuOD.getAsDouble();
            System.out.println("Srednia wieku mężczyzn: " + sredniaWieku);
        }

        // czy jest osoba, która ma 100 lat?
        System.out.println();
        boolean czyJestOsoba100;
        Optional<Person> czyJestOsoba100Lat = list.stream().filter(person -> person.getAge() == 100).findFirst();
        if (czyJestOsoba100Lat.isPresent()) {
            czyJestOsoba100 = true;
        } else {
            czyJestOsoba100 = false;
        }
        System.out.println("Czy jest osoba w wieku 100 lat: " + czyJestOsoba100);

        // czy jest osoba w wieku 25 lat?
        System.out.println();
        boolean czyJestOsobaWWieku25 = list.stream().anyMatch(person -> person.getAge() == 25);
        System.out.println("Czy jest osoba w wieku 25 lat: " + czyJestOsobaWWieku25);

        // - ** znajdź nastarszą osobę w liście (findFirst)
        System.out.println();
        OptionalInt najstarszaOsobaOptInt = list.stream().mapToInt(person -> person.getAge()).max();
        if (najstarszaOsobaOptInt.isPresent()) {
            int najstarszaOsobaInt = najstarszaOsobaOptInt.getAsInt();

            Optional<Person> najstarszaOsoba = list.stream().filter(person -> person.getAge() == najstarszaOsobaInt).findFirst();
            if (najstarszaOsoba.isPresent()) {
                Person najstarszaNaLiscie = najstarszaOsoba.get();
                System.out.println("Najstarsza osoba to: " + najstarszaNaLiscie.getFirstName() + " " + najstarszaNaLiscie.getLastName());
            }
        }
    }
}
