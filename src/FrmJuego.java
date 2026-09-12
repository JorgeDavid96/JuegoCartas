import java.awt.Color;

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
        jugador1.repartir();
        jugador2.repartir();
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
