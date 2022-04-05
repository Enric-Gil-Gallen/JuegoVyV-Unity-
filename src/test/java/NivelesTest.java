import PaqueteJuego.Niveles;
import PaqueteJuego.TablaEquipos;
import PaqueteJuego.TablaJugadores;
import PaqueteJuego.pruebaNiveles;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.URLDecoder;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class NivelesTest {
    private static Niveles niveles;

    @BeforeAll
    static void prepara(){
        niveles = new Niveles(2, 55);
    }

    static Stream<Arguments> datos(){
        return Stream.of(
                Arguments.of("es_ju_1.txt", "es_eq_1.txt", 0),
                Arguments.of("es_ju_2.txt", "es_eq_2.txt",1),
                Arguments.of("es_ju_3.txt", "es_eq_3.txt",1),
                Arguments.of("es_ju_4.txt", "es_eq_4.txt",1),
                Arguments.of("es_ju_5.txt", "es_eq_5.txt",2)
        );
    }

    @ParameterizedTest
    @MethodSource("datos")
    void actualizarNiveles(String tablaJugadores, String tablaEquipos, int solucion){
        String resourcePathFJ = URLDecoder.decode(pruebaNiveles.class.getResource("/"+tablaJugadores).getPath());
        String resourcePathFE = URLDecoder.decode(pruebaNiveles.class.getResource("/"+tablaEquipos).getPath());
        TablaEquipos te = new TablaEquipos(resourcePathFE);
        TablaJugadores tj = new TablaJugadores(resourcePathFJ);
        assertThat(niveles.actualizarNiveles(tj, te), is(solucion));
    }
}
