package paquetejuego;

public class Niveles {
    private int incrementEspecial;
    private int umbralNivel;

    public Niveles(int incE, int umbN) {
        incrementEspecial = incE;
        umbralNivel = umbN;
    }

    public int actualizarNiveles(TablaJugadores tablaJu, TablaEquipos tablaEq) {
        int puntosEquipo;
        String mejorEquipo;
        int encontrado;
        int maxPuntos = 0;
        int codError = 0;  // valor que devolver (0 == sin incidencias)
        int numJugadores = tablaJu.obtenerNumJugadores();  // obtiene cantidad de jugadores de la tabla
        int numEquipos = tablaEq.obtenerNumEquipos();  // obtiene cantidad de equipos de la tabla

        if (numJugadores == 0 || numEquipos == 0)
            return 1;  // 1 == al menos una tabla SIN entrada
        // busca mayor cantidad de puntos entre equipos
        maxPuntos = getMaxPuntos(tablaEq, maxPuntos, numEquipos);
        // busca equipos con mayor cantidad de puntos (mejores equipos)
        for (int i = 0; i < numEquipos; i++) {
            puntosEquipo = tablaEq.obtenerPuntos(i);
            if (puntosEquipo == maxPuntos) {
                mejorEquipo = tablaEq.obtenerNombre(i);
                // busca jugadores de mejor equipo
                encontrado = getEncontrado(tablaJu, mejorEquipo, numJugadores);
                if (encontrado == 0)
                    codError = 2;  // 2 == al menos un mejor equipo SIN deportistas
            }
        }
        return codError;
    }

    private int getEncontrado(TablaJugadores tablaJu, String mejorEquipo, int numJugadores) {
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
                if (esPreJugador && nivelJugador < umbralNivel)
                    tablaJu.actualizarNivel(j, incrementEspecial);
                else
                    tablaJu.actualizarNivel(j, 1);
            }
        }
        return encontrado;
    }

    private int getMaxPuntos(TablaEquipos tablaEq, int maxPuntos, int numEquipos) {
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
}
