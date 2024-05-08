import java.util.Objects;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("▀▀█▀▀ ▒█▀▀█ ▀█▀ ▒█▀▀█ ▒█▀▀▀█ ▒█░▒█ ▒█▄░▒█ ▀▀█▀▀ TM");
        System.out.println("░▒█░░ ▒█▄▄▀ ▒█░ ▒█░░░ ▒█░░▒█ ▒█░▒█ ▒█▒█▒█ ░▒█░░");
        System.out.println("░▒█░░ ▒█░▒█ ▄█▄ ▒█▄▄█ ▒█▄▄▄█ ░▀▄▄▀ ▒█░░▀█ ░▒█░░");
        System.out.println("Todos los derechos reservados");

        logueo();
        System.out.println("Programa cerrado.");

    }

    //Menú de loggeo
    public static void logueo() {
        //Menú logueo
        byte opcion = 0;
        //Declaración de Scanner
        Scanner teclado = new Scanner(System.in);
        //Declaración de variables relacionadas con el inicio y registro de sesión
        String usuario;
        String passwd; //(Contraseña)
        String passwd2; //(Para la tipica verificación de "vuelva a escribir la contraseña para registrarse")

        while (opcion != 3) {
            System.out.println();
            System.out.println("Vienvenido:");
            System.out.println("1.- Iniciar Sesión");
            System.out.println("2.- Registrarse");
            System.out.println("3.- Salir");
            opcion = teclado.nextByte();
            switch (opcion) {

                //Iniciar sesión
                case 1:
                    System.out.println("Introduzca su nombre de usuario");
                    usuario = teclado.next();
                    System.out.println("Introduzca su contraseña");
                    passwd = teclado.next();

                    //#### METER SENTENCIA QUE VERIFICA QUE EL USUARIO EXISTE (Requiere el archivo) ####

                    break;

                //Registrar nuevo usuario
                case 2:
                    System.out.println("Introduzca un nombre de usuario");
                    usuario = teclado.next();
                    System.out.println("Introduzca una contraseña");
                    passwd = teclado.next();

                    //Sistema de verificación de contraseña (repetirla dos veces)
                    System.out.println("Vuelva a escribir la contraseña para registrarse");
                    for (int i = 3; i > 0; i--) {   //El programa vuelve al principio una vez se agotan los intentos
                        passwd2 = teclado.next();
                        if (Objects.equals(passwd2, passwd)) {
                            //#### METER SENTENCIA QUE CREA A UN USUARIO AQUÍ ####
                            System.out.println("Ha creado la cuenta " + usuario);
                            //Usuario uno = new Usuario(IDUSUARIO ,usuario, passwd);
                            break;
                        }
                        else {
                            System.out.println("La contraseña no coincide, le quedan estos intentos: " + (i-1));
                            if ((i-1) == 0) {
                                System.out.println("Se ha agotado el número de intentos posibles para introducir la contraseña.");
                                System.out.println("Por favor, regístrese de nuevo.");
                            }
                        }
                    }
                    break;
            }
        }
    }
}