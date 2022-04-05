package PaqueteJuego;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class NivelesTest {


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{
                        {"es_eq_1.txt", "es_ju_1.txt", 0},
                        {"es_eq_2.txt", "es_ju_2.txt", 1},
                        {"es_eq_3.txt", "es_ju_3.txt", 1},
                        {"es_eq_4.txt", "es_ju_4.txt", 1},
                        {"es_eq_5.txt", "es_ju_5.txt", 2}

                }
        );
    }

    // guessEsNULL
    @ParameterizedTest
    @MethodSource("datos")
    void actualizarNiveles() {

    }
}