package nl.cge.junit5;

import nl.cge.domein.Bankrekening;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;

import java.util.Arrays;

public class RepeatedTest {


    @org.junit.jupiter.api.RepeatedTest(10)
    void test(RepetitionInfo repetitionInfo) {
        System.out.println(repetitionInfo.getCurrentRepetition() + " of " + repetitionInfo.getTotalRepetitions());
    }

}
