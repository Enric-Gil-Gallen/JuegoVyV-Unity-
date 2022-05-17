package paquetejuego;

/**
 * Clase que se encarga de gestionar los Niveles de nuestro juego
 */


public class Niveles {
    /**
     * incrementEspecial se trata de una variable de clase que no vamos a modificar,
     * se encarga del incremento de Niveles para jugadores Premium
     */
    private final int incrementEspecial;
    /**
     * umralNivel se trata de una variable de clase que no vamos a modificar,
     * se encarga de limitar el incremento de Niveles una vez alcanzado el Umbral
     */
    private final int umbralNivel;

    /**
     * @param incE Incremento Especial para usuarios Premium
     * @param umbN Umbral para limitar el aumento de niveles de 1 en 1
     */
    public Niveles(final int incE, final int umbN) {
        incrementEspecial = incE;
        umbralNivel = umbN;
    }

    /**
     *Actualiza los niveles según la Tabla de Jugadores y la Tabla de Equipos dada
     * @param tablaJu Tabla de Jugadores a analizar
     * @param tablaEq Tabla de Equipos a analizar
     * @return devuelve el código de error: 0->sin incidencias,
     * 1->una tabla SIN entradas, 2->al menos un MEJOR equipo SIN deportistas
     */
    public int actualizarNiveles(final TablaJugadores tablaJu, final TablaEquipos tablaEq) {
        final int numJugadores = tablaJu.obtenerNumJugadores();  // obtiene cantidad de jugadores de la tabla
        final int numEquipos = tablaEq.obtenerNumEquipos();  // obtiene cantidad de equipos de la tabla

        if (numJugadores == 0 || numEquipos == 0){
            return 1;  // 1 == al menos una tabla SIN entrada
        }

        int puntosEquipo;
        String mejorEquipo;
        int encontrado;
        int maxPuntos;
        // busca mayor cantidad de puntos entre equipos
        maxPuntos = getMaxPuntos(tablaEq, 0, numEquipos);
        // busca equipos con mayor cantidad de puntos (mejores equipos)
        for (int i = 0; i < numEquipos; i++) {
            puntosEquipo = tablaEq.obtenerPuntos(i);
            if (puntosEquipo == maxPuntos) {
                mejorEquipo = tablaEq.obtenerNombre(i);
                // busca jugadores de mejor equipo
                encontrado = getEncontrado(tablaJu, mejorEquipo, numJugadores);
                if (encontrado == 0){
                    return 2;  // 2 == al menos un mejor equipo SIN deportistas
                }
            }
        }
        return 0;
    }

    private int getEncontrado(final TablaJugadores tablaJu, final String mejorEquipo, final int numJugadores) {
        String equipoJugador;
        boolean esPreJugador;
        int nivelJugador;
        int encontrado = 0;
        for (int j = 0; j < numJugadores; j++) {
            equipoJugador = tablaJu.obtenerEquipo(j);
            if (mejorEquipo.equals(equipoJugador)) {
                encontrado = 1;
                nivelJugador = tablaJu.obtenerNivel(j);
                esPreJugador = tablaJu.obtenerEsPre(j);
                if (esPreJugador && nivelJugador < umbralNivel){
                    tablaJu.actualizarNivel(j, incrementEspecial);
                }
                else{
                    tablaJu.actualizarNivel(j, 1);
                }
            }
        }

        return encontrado;
    }

    private int getMaxPuntos(final TablaEquipos tablaEq, final int maxPuntos, final int numEquipos) {
        int puntosEquipo;
        int maxPuntosLocal = maxPuntos;
        for (int i = 0; i < numEquipos; i++) {
            puntosEquipo = tablaEq.obtenerPuntos(i);  // puntos del equipo #i en la tabla
            if (puntosEquipo > maxPuntosLocal){
                maxPuntosLocal = puntosEquipo;
            }
        }
        return maxPuntosLocal;
    }

    public int getIncrementEspecial() {
        return incrementEspecial;
    }

    public int getUmbralNivel() {
        return umbralNivel;
    }
}
