import java.awt.Color;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FrmJuego extends JFrame {
    JPanel pnlJugador1;
    JPanel pnlJugador2;
    JTabbedPane tpJugadores;
    Jugador jugador1 = new Jugador();
    Jugador jugador2 = new Jugador();
    private Random r = new Random();

    public FrmJuego(){
        setSize(500, 300);
        setTitle("Juego de Cartas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton btnRepartir = new JButton("Repartir");
        btnRepartir.setBounds(10, 10, 100, 25);
        add(btnRepartir);

        JButton btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(120, 10, 100, 25);
        add(btnVerificar);

        //Definit una interfaz con varios paneles mediante pestañas
        tpJugadores = new JTabbedPane();
        tpJugadores.setBounds(10, 45, 470, 200);
        add(tpJugadores);

        pnlJugador1 = new JPanel();
        tpJugadores.add("Martin Estrada Contreras", pnlJugador1);
        pnlJugador1.setBackground(new Color(0, 255, 0));
        pnlJugador1.setLayout(null);

        pnlJugador2 = new JPanel();
        tpJugadores.add("Raul Vidal", pnlJugador2);
        pnlJugador2.setBackground(new Color(0, 255, 255));
        pnlJugador2.setLayout(null);

        //Eventos
        btnRepartir.addActionListener(evento -> {
            repartir();
        });

        btnVerificar.addActionListener(evento -> {
            verificar();
        });
    }

    private void repartir(){
        String entrada = JOptionPane.showInputDialog(this, "Con cuantos mazos deseas jugar");
        int numMazos;

        try {
            numMazos = Integer.parseInt(entrada);
            if (numMazos < 1) {
                numMazos = 1;
            }
        } catch (Exception e) {
            numMazos = 1;
        }

        int totalCartasMazo = numMazos * 52;
        int[] mazo = new int[totalCartasMazo];
        int pos = 0;
        for (int i = 0; i < numMazos; i++) {
            for ( int j = 1; j <= 52; j++) {
                mazo[pos] = j;
                pos++;
            }
        }

        for (int i = totalCartasMazo - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            int temp = mazo[i];
            mazo[i] = mazo[j];
            mazo[j] = temp;
        }

        int[] cartasJugador1 = new int[10];
        int[] cartasJugador2 = new int[10];
        for (int i = 0; i < 10; i++) {
            cartasJugador1[i] = mazo[i];
            cartasJugador2[i] = mazo[10 + i];
        }

        jugador1.repartir(cartasJugador1);;
        jugador2.repartir(cartasJugador2);
        jugador1.mostrar(pnlJugador1);
        jugador2.mostrar(pnlJugador2);
    }

    private void verificar(){
        String gruposEncontrados = "";
        String escalerasEncontradas = "";
        String cartasSobrantes = "";
        int totalCartas = 0;
        switch (tpJugadores.getSelectedIndex()) {
            case 0:
                gruposEncontrados = jugador1.getGrupos();
                escalerasEncontradas = jugador1.getEscaleras();
                cartasSobrantes = jugador1.getSobrantes();
                totalCartas = jugador1.getPuntaje();
                break;
            case 1:
                gruposEncontrados = jugador2.getGrupos();
                escalerasEncontradas = jugador2.getEscaleras();
                cartasSobrantes = jugador2.getSobrantes();
                totalCartas = jugador2.getPuntaje();
                break;
        }

        String resultado = gruposEncontrados + "\n" + escalerasEncontradas + "\n" + cartasSobrantes + "\n" + "Total puntaje:\n" + totalCartas;
        JOptionPane.showMessageDialog(null, resultado);
    }
}
