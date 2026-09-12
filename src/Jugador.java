import java.util.Random;

import javax.swing.JPanel;

public class Jugador {
    private final int TotalCartas = 10;
    private final int Margen = 10;
    private final int Distancia = 40;

    private Random r = new Random();
    private Carta[] cartas= new Carta[TotalCartas];
    private boolean[] cartasUsadas = new boolean[TotalCartas];

    public void repartir(){
        for(int i = 0; i < TotalCartas; i++){
            cartas[i] = new Carta(r);
            cartasUsadas[i] = false;
        }
    }

    public void mostrar(JPanel pnl){
        pnl.removeAll();
        int PosicionX = Margen + TotalCartas * Distancia;
        for(Carta carta: cartas){
            PosicionX -= Distancia;
            carta.mostrar(pnl, PosicionX, Margen);
        }
        pnl.repaint();
    }

    public String getGrupos(){
        String respuesta = "No encontraro grupos";

        //Arreglo de contadores de cartas por el nombre
        int[] contadores = new int[NombreCarta.values().length];
        boolean hayGrupos = false;
        for(Carta carta : cartas){
            int posicion = carta.getNombre().ordinal();
            contadores[posicion]++;
            if (!hayGrupos && contadores[posicion] >= 2) {
                hayGrupos = true;
            }
        }

        if (hayGrupos) {
            respuesta = "Se encontraron los siguientes grupos;\n";
            for(int i = 0; i < contadores.length; i++){
                if (contadores[i] >= 2) {
                    respuesta += Grupo.values()[contadores[i]] + " de " + NombreCarta.values()[i] + "\n";
                }
            }
        }

        return respuesta;
    }

    public String buscarEscalera(Pinta pinta, String nombrePinta) {
        String resultado = "";
        int[] posiciones = new int[13];
        for (int i = 0; i < 13; i++) {
            posiciones[i] = -1;
        }

        for (int i = 0; i < cartas.length; i++) {
            if (cartas[i].getPinta() == pinta) {
                posiciones[cartas[i].getNombre().ordinal()] = i;
            }
        }

        int consecutivos = 0;
        int inicio = -1;

        for (int i = 0; i <= 13; i++) {
            if (i < 13 && posiciones[i] != -1) {
                if (consecutivos == 0) {
                    inicio = i;
                }
                consecutivos++;
            } else {
                if (consecutivos >= 2) {
                    NombreCarta nombreInicio = NombreCarta.values()[inicio];
                    NombreCarta nombreFin = NombreCarta.values()[inicio + consecutivos - 1];
                    resultado += Grupo.values()[consecutivos] + " de " + nombrePinta + " desde " + nombreInicio + " hasta " + nombreFin + "\n";
                    for (int j = inicio; j < inicio + consecutivos; j++) {
                        cartasUsadas[posiciones[j]] = true;
                    }
                }
                consecutivos = 0;
                }
            }
        return resultado;
    }

    public String getEscaleras(){
        String resultado = "";
        
        resultado += buscarEscalera(Pinta.PICA, "Picas");
        resultado += buscarEscalera(Pinta.CORAZON, "Corazones");
        resultado += buscarEscalera(Pinta.DIAMANTE, "Diamante");
        resultado += buscarEscalera(Pinta.TREBOL, "Trebol");

        if (resultado.equals("")) {
            resultado = "No se encontraron escaleras. \n";
        }

        return resultado;
    }

    public String getSobrantes(){
        String resultado = "Cartas Sobrantes.\n";
        boolean haySobrantes = false;

        for (int i = 0; i < cartas.length; i++) {
            if (!cartasUsadas[i]) {
                resultado += cartas[i].getNombre() + " de " + cartas[i].getPinta() + "\n";
                haySobrantes = true;
            }
        }

        if (!haySobrantes) {
            resultado += "No hay cartas sobrantes";
        }

        return resultado;
    }

    public int getPuntaje() {
        int total = 0;
        for (int i = 0; i < cartas.length; i++) {
            if (!cartasUsadas[i]) {
                NombreCarta n = cartas[i].getNombre();

                if (n == NombreCarta.AS || n == NombreCarta.JACK || n == NombreCarta.QUEEN || n == NombreCarta.KING) {
                    total += 10;
                } else {
                    total += n.ordinal() + 1;
                }
            }
        }
        return total;
    }
}
