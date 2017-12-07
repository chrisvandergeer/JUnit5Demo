package nl.cge;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static nl.cge.ParameterizedTest.Persoon.create;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParameterizedTest {

    static enum Enum4Test {
        AAP, NOOT, MIES;
    }

    static class Persoon {
        public String naam;
        public Integer leeftijd;
        public static Persoon create(String naam, Integer leeftijd) {
            Persoon p = new Persoon();
            p.leeftijd = leeftijd;
            p.naam = naam;
            return p;
        }

        @Override
        public String toString() {
            return "Persoon{" +
                    "naam='" + naam + '\'' +
                    ", leeftijd=" + leeftijd +
                    '}';
        }
    }

    static class DatumConverter implements ArgumentConverter {

        @Override
        public Object convert(Object o, ParameterContext parameterContext) throws ArgumentConversionException {
            String datum = (String) o;
            return LocalDate.parse(datum, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }
    }

    @org.junit.jupiter.params.ParameterizedTest
    @ValueSource(strings = { "aap", "noot", "mies" })
    void test(String testValue) {
        assertTrue(testValue != null);
    }

    @org.junit.jupiter.params.ParameterizedTest
    @EnumSource(Enum4Test.class)
    public void testWithEnumSource(Enum4Test value) {
        assertTrue(value != null);
    }

    @org.junit.jupiter.params.ParameterizedTest
    @MethodSource("personen")
    public void testWithTestMethodSource(Persoon persoon) {
        assertTrue(persoon.leeftijd > 18);
    }

    @org.junit.jupiter.params.ParameterizedTest
    @MethodSource("personenArgs")
    public void testWithTestMethodSourceWithArguments(String naam, Integer leeftijd) {
        assertTrue(leeftijd > 18);
    }

    @org.junit.jupiter.params.ParameterizedTest
    @CsvSource({"Piet, 47", "Jan, 30"})
    public void testWithCsvSource(String naam, Integer leeftijd) {
        assertTrue(leeftijd > 18);
    }

    @org.junit.jupiter.params.ParameterizedTest
    @CsvFileSource(resources = "/csvResource.csv")
    public void testWithCsvFileResource(String naam, Integer leeftijd, @ConvertWith(DatumConverter.class) LocalDate eenDatum) {
        assertTrue(Arrays.asList("Klaas", "Karel", "Kees").contains(naam));
        assertTrue(leeftijd > 18);
    }

    public static Stream<Arguments> personenArgs() {
        return Arrays.asList(
                Arguments.of("Piet", 44),
                Arguments.of("Jan", 30),
                Arguments.of("Klaas", 22)
        ).stream();
    }

    public static List<Persoon> personen() {
        return Arrays.asList(
                create("Piet", 44),
                create("Jan", 30),
                create("Karel", 44),
                create("Josje", 33)
        );
    }

}

