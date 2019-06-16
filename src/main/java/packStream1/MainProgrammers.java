package packStream1;

import java.util.*;
import java.util.stream.Collectors;

public class MainProgrammers {
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
        List<String> languages1 = Arrays.asList("Java;Cobol;Cpp;Lisp".split(";"));
        List<String> languages2 = Arrays.asList("Java;Lisp".split(";"));
        List<String> languages3 = Arrays.asList("Java;Cobol;Cpp;Lisp;C#".split(";"));
        List<String> languages4 = Arrays.asList("C#;C;Cpp".split(";"));
        List<String> languages5 = Arrays.asList("Java;Assembler;Scala;Cobol".split(";"));
        List<String> languages6 = Arrays.asList("Java;Scala".split(";"));
        List<String> languages7 = Arrays.asList("C#;C".split(";"));
        List<String> languages8 = Collections.emptyList();
        List<String> languages9 = Arrays.asList("Java");
        Programmer programmer1 = new Programmer(person1, languages1);
        Programmer programmer2 = new Programmer(person2, languages2);
        Programmer programmer3 = new Programmer(person3, languages3);
        Programmer programmer4 = new Programmer(person4, languages4);
        Programmer programmer5 = new Programmer(person5, languages5);
        Programmer programmer6 = new Programmer(person6, languages6);
        Programmer programmer7 = new Programmer(person7, languages7);
        Programmer programmer8 = new Programmer(person8, languages8);
        Programmer programmer9 = new Programmer(person9, languages9);

        System.out.println("Lista programmers:");
        List<Programmer> programmers = Arrays.asList(programmer1, programmer2, programmer3, programmer4, programmer5, programmer6, programmer7, programmer8, programmer9);

        System.out.println("Lista programmers:");
        System.out.println(programmers);

        // a) uzyskaj listę programistów, którzy są mężczyznami
        System.out.println();
        List<Programmer> listaProgramistowMezczyzn = programmers.stream().filter(programmer -> programmer.getPerson().isMale())
                .collect(Collectors.toList());
        System.out.println(listaProgramistowMezczyzn);
        System.out.println();
        System.out.println("Lista programistów mężczyzn:");
        listaProgramistowMezczyzn.forEach(programmer -> System.out.println(programmer));

        // b) uzyskaj listę niepełnoletnich programistów (obydwóch płci), którzy piszą w Cobolu
        System.out.println();
        List<Programmer> listaNiepelnoletnichCobol = programmers.stream()
                .filter(programmer -> programmer.getPerson().getAge() < 18)
                .filter(programmer -> programmer.getLanguages().contains("Cobol")).collect(Collectors.toList());
        System.out.println("Lista niepełnoletnich, którzy piszą w Cobolu:");
        listaNiepelnoletnichCobol.forEach(programmer -> System.out.println(programmer));

        System.out.println();
        List<Programmer> listaNiepelnoletnichCobol2 = programmers.stream()
                .filter(programmer -> programmer.getPerson().getAge() < 18 && programmer.getLanguages().contains("Cobol"))
                .collect(Collectors.toList());
        System.out.println("Lista niepełnoletnich, którzy piszą w Cobolu(2):");
        listaNiepelnoletnichCobol2.forEach(programmer -> System.out.println(programmer));

        // c) uzyskaj listę programistów, którzy znają więcej, niż jeden język programowania
        System.out.println();
        List<Programmer> listaZnajacychKilkaJezykow = programmers.stream()
                .filter(programmer -> programmer.getLanguages().size() > 1).collect(Collectors.toList());
        System.out.println("Lista znających kilka języków programowania:");
        listaZnajacychKilkaJezykow.forEach(programmer -> System.out.println(programmer));

        // d) uzyskaj listę programistek, które piszą w Javie i Cpp
        System.out.println();
        // inna wersja zapisu: ... .filter(programmer -> !programmer.getPerson().isMale(). ...
        List<Programmer> listaProgramistekJavaCpp = programmers.stream().filter(programmer -> programmer.getPerson().isMale() == false)
                .filter(programmer -> programmer.getLanguages().contains("Java") && programmer.getLanguages().contains("Cpp"))
                .collect(Collectors.toList());
        System.out.println("Lista programistek. które piszą w Java i Cpp:");
        listaProgramistekJavaCpp.forEach(programmer -> System.out.println(programmer));

        // e) uzyskaj listę męskich imion
        System.out.println();
        Set<String> listaMeskichImion = programmers.stream().filter(programmer -> programmer.getPerson().isMale())
                .map(programmer -> programmer.getPerson().getFirstName()).collect(Collectors.toSet());
        System.out.println("Lista męskich imion:");
        listaMeskichImion.forEach(programmer -> System.out.println(programmer));

        // f) uzyskaj set wszystkich języków opanowanych przez programistów
        System.out.println();
        Set<String> zbiorJezykowProgramowania = programmers.stream().map(programmer -> programmer.getLanguages())
                .flatMap(languages -> languages.stream()).collect(Collectors.toSet());
        System.out.println("Lista jezyków programowania:");
        zbiorJezykowProgramowania.forEach(s -> System.out.println(s));

        // g) uzyskaj listę nazwisk programistów, którzy znają więcej, niż dwa języki
        List<String> listaNazwiskWiecejNiz2Jezyki = programmers.stream()
                .filter(programmer -> programmer.getLanguages().size() > 2)
                .map(programmer -> programmer.getPerson().getLastName()).collect(Collectors.toList());
        System.out.println("Lista znających więcej niż 2 języki:");
        listaNazwiskWiecejNiz2Jezyki.forEach(s -> System.out.println(s));

        // h) sprawdź, czy istnieje chociaż jedna osoba, która nie zna żadnego języka
        System.out.println();
        boolean czyIstniejeOsobaBezJezyka = programmers.stream()
                .anyMatch(programmer -> programmer.getLanguages().size() == 0);
        System.out.println("Czy istnieje osoba, która nie zna żadnego języka programowania: " + czyIstniejeOsobaBezJezyka);

        // i)* uzyskaj ilość wszystkich języków opanowanych przez programistki
        System.out.println();
        long iloscJezykowOpanowanychPrzezProgramistki = programmers.stream()
                .filter(programmer -> !programmer.getPerson().isMale())
                .map(programmer -> programmer.getLanguages())
                .flatMap(languages -> languages.stream())
                .distinct()
                .count();
        System.out.println("Ilość języków opanowanych przez programistki: " + iloscJezykowOpanowanychPrzezProgramistki);

        // j) wypisz informację czy jest programista w Javie który ma na nazwisko Brawo (matchAny)
        System.out.println();
        boolean czyJestBrawo = programmers.stream()
                .anyMatch(programmer -> programmer.getPerson().getLastName().equals("Brawo") && programmer.getLanguages().contains("Java"));
        System.out.println("Czy jest osoba o nazwisku Brawo?: " + czyJestBrawo);

        // k) wypisz najrzadziej występujący język/języki (listę, bo mogą być "tak samo mało popularne")
        // znajduję ilość wszystkich języków / set wszystkich języków
//        long iloscWszystkicjJezProg = programmers.stream()
//                .map(programmer -> programmer.getLanguages())
//                .flatMap(languages -> languages.stream())
//                .distinct().count();
//        int iloscWJP = (int) iloscWszystkicjJezProg;
        System.out.println();
        List<String > listaJezykow = programmers.stream()
                .map(programmer -> programmer.getLanguages())
                .flatMap(languages -> languages.stream())
                .distinct().collect(Collectors.toList());
        System.out.println("Lista języków programowania:");
        listaJezykow.forEach(languages -> System.out.println(languages));

        // znajduję ile razy wystąpił dany język
//        Long[] tab = new Long[listaJezykow.size()];
//        for (int i = 0; i < listaJezykow.size(); i++) {
//            tab[i] = programmers.stream()
//                    .map(programmer -> programmer.getLanguages())
//                    .flatMap(languages -> languages.stream())
//                    .filter(languages -> languages.equals(listaJezykow.get(i)))
//                    .count();
        // ilość wystąpień języka Java
        System.out.println();
        long long1 = programmers.stream()
                    .map(programmer -> programmer.getLanguages())
                    .flatMap(languages -> languages.stream())
                    .filter(languages -> languages.equals("Java"))
                    .count();
        System.out.println(long1);

        // ...wersjaPawła
        List<String> languagesList = programmers.stream()
                .map(programmer -> programmer.getLanguages())
                .flatMap(lang -> lang.stream())
                .collect(Collectors.toList());
        System.out.println("Lista języków Paweł");
        languagesList.forEach(s -> System.out.println(s));

        OptionalLong min = programmers.stream()
                .map(programmer -> programmer.getLanguages())
                .flatMap(lang -> lang.stream())
                .mapToLong(jezyk -> languagesList.stream().filter(lang -> lang.equalsIgnoreCase(jezyk)).count())
                .min();

        if(min.isPresent()){
            Optional<LangCountPair> langCountPair = programmers.stream()
                    .map(programmer -> programmer.getLanguages())
                    .flatMap(lang -> lang.stream())
                    .map(jezyk -> new LangCountPair(jezyk, languagesList.stream().filter(lang -> lang.equalsIgnoreCase(jezyk)).count()))
                    .filter(jezyk-> jezyk.count == min.getAsLong())
                    .findFirst();

            if (langCountPair.isPresent()) {
                System.out.println(langCountPair.get());
            }
        }
    }
}
