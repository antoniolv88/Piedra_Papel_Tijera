package piedrapapeltijera;

import java.util.Scanner;

/**
 *
 * @author Antonio
 */
public class PiedraPapelTijera {

    private static final String Piedra = "P";
    private static final String Papel = "L";
    private static final String Tijera = "T";
    private static final String Salir = "S";

    //Genera juada aleatoria
    private static final String[] Juego = {Piedra, Papel, Tijera};

    private static final int Empate = 0;
    private static final int Gana = 1;
    private static final int Pierde = 2;

    private static final int ERROR_NO_ENCONTRADA = -1;

    //Mensajes al usuario
    private static final String Bienvenida = "Bienvenido al juego de Piedra-Papel-Tijera";
    private static final String Opciones = Piedra + "(piedra)" + Papel + "(papel)" + Tijera + "(tijera) o " + Salir + "(salir)";
    private static final String Pedir_Jugada = "¿Cúal es tu jugada?" + Opciones;
    private static final String Pedir_Nueva_jugada = "¿Cúal es tu nueva jugada?" + Opciones;
    private static final String Fin = "Fin de la partida";

    private static final String MSJ_ERROR_NO_ENCONTRADA = "No entiendo tu jugada";

    public static void main(String[] args) {
        //Abro scanner para leer al usuario
        Scanner s = new Scanner(System.in);

        //Instrucciones
        System.out.println(Bienvenida);
        System.out.println(Pedir_Jugada);

        while (true) {//Iteramos para siempre

            //Jugada del PC
            int eleccionPC = (int) (Math.random() * Juego.length);

            //Jugada del usuario
            String EleccionUsuario = s.next();
            if (EleccionUsuario.equalsIgnoreCase(Salir)) {
                break;//S para salir del bucle
            }

            //Interpretación de la jugada
            int eleccionUsuario = convertir(EleccionUsuario);
            if (eleccionUsuario == ERROR_NO_ENCONTRADA) {
                System.err.println(MSJ_ERROR_NO_ENCONTRADA);
                continue;//Seguimos en el bucle
            }

            //Calcular ganador jugada
            int resultado = usuarioGana(eleccionPC, eleccionUsuario);


            //Mostrar resultado jugada
            switch (resultado) {
                case Gana:
                    System.out.println("Enhorabuena!! Tu " + Juego[eleccionUsuario] + " gana a " + Juego[eleccionPC]);
                    break;
                case Pierde:
                    System.out.println("Lo siento!! Tu " + Juego[eleccionUsuario] + " pierde ante " + Juego[eleccionPC]);
                    break;
                case Empate:
                    System.out.println("Empate a " + Juego[eleccionPC] + "!!");
                    break;

            }

            System.out.println("¿Cúal es tu nueva jugada? P (piedra), L (papel), T (tijera) o S (salir)");
        }
        System.out.println(Fin);
        //cerramos
        s.close();

    }

    private static int convertir(String EleccionUsuario) {
        for (int i = 0; i < Juego.length; i++) {
            if (Juego[i].equalsIgnoreCase(EleccionUsuario)) {
                return i;
            }
        }
        return ERROR_NO_ENCONTRADA;
    }

    private static int usuarioGana(int eleccionPC, int eleccionUsuario) {
        int res = eleccionUsuario - eleccionPC;
        if (res < 0) {
            res += Juego.length;
        }
        return res;
    }
}
