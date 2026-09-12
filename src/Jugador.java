import java.util.Random;

import javax.swing.JPanel;

public class Jugador {
    private final int TotalCartas = 10;
    private final int Margen = 10;
    private final int Distancia = 40;

    private Random r = new Random();
    private Carta[] cartas= new Carta[TotalCartas];

    public void repartir(){
        for(int i = 0; i < TotalCartas; i++){
            cartas[i] = new Carta(r);
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
}
