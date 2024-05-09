import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
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

        //Comprobamos que los archivos existen
        comprobarArchivos();

        menuPrincipal();
        System.out.println("Programa cerrado.");

    }

    public static void comprobarArchivos(){
        File archivoUsuarios = null;
        File archivoGrupos = null;
        File archivoGastos = null;
        File archivoDivisiones = null;

        //Comprobacion de los archivos
        try {
            archivoUsuarios = new File("usuarios.csv");
            archivoGrupos = new File("grupos.csv");
            archivoGastos = new File("gastos.csv");
            archivoDivisiones = new File("divisiones.csv");

            //Crear archivo en caso de que no exista
            if (!archivoUsuarios.exists()) {
                archivoUsuarios.createNewFile();
            }
            if (!archivoGrupos.exists()) {
                archivoGrupos.createNewFile();
            }
            if (!archivoGastos.exists()) {
                archivoGastos.createNewFile();
            }
            if (!archivoDivisiones.exists()) {
                archivoDivisiones.createNewFile();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void menuPrincipal(){
        //Selector de menuPrincipal
        byte opcion = 0;

        //Declaración de Scanner
        Scanner teclado = new Scanner(System.in);

        do {
            System.out.println();
            System.out.println("Bienvenido:");
            System.out.println("1.- Iniciar Sesión");
            System.out.println("2.- Registrarse");
            System.out.println("3.- Salir");
            opcion = teclado.nextByte();

            switch (opcion) {
                //Iniciar sesión
                case 1: logueo(); break;
                case 2: registro(); break;
                case 3:
                    System.out.println("Saliendo..."); break;
                default:
                    System.out.println("Opcion no valida");
            }
        }while (opcion!=3);

        teclado.close();
    }
    //Menú de loggeo
    public static void logueo() {
        //Declaración de variables relacionadas con el inicio y registro de sesión
        String usuario = "";
        String passwd = ""; //(Contraseña)

        //Declaración de Scanner
        Scanner teclado = new Scanner(System.in);

        //Recoger datos de Usuario
        System.out.println("Introduzca su nombre de usuario");
        usuario = teclado.next();
        System.out.println("Introduzca su contraseña");
        passwd = teclado.next();

        //#### METER SENTENCIA QUE VERIFICA QUE EL USUARIO EXISTE (Requiere el archivo) ####



        teclado.close();
    }


    public static void registro() {
        //Declaración de variables relacionadas con el inicio y registro de sesión
        String usuario = "";
        String passwd = ""; //(Contraseña)
        String passwd2 = ""; //(Para la tipica verificación de "vuelva a escribir la contraseña para registrarse")

        //Declaración de Scanner
        Scanner teclado = new Scanner(System.in);

        System.out.println("Introduzca un nombre de usuario");
        usuario = teclado.next();
        System.out.println("Introduzca una contraseña (Debe contener 6 caracteres, letras mayúsculas y minúsculas, y al menos un símbolo");
        passwd = teclado.next();

        try {
            Usuario nuevoUsuario = new Usuario(usuario, passwd);

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

        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println("Error al crear el Usuario");
        }

    }


}