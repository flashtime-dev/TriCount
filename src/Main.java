import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
        usuario = teclado.nextLine();
        System.out.println("Introduzca su contraseña");
        passwd = teclado.nextLine();

        //#### METER SENTENCIA QUE COMPRUEBA SI EL USUARIO EXISTE AQUÍ ####
        //Datos del usuario a crear
        String[] datosUsuario = new String[3];

        //Lista de usuarios
        List<Usuario> usuarios = new ArrayList<Usuario>();

        //Primeros pasos de crear el archivo / y crear objetos cliente
        try {
            File archivo = new File("usuarios.csv");

            Scanner entrada = new Scanner(archivo);
            while (entrada.hasNextLine()) {
                datosUsuario = entrada.nextLine().split(",");
                usuarios.add(new Usuario(Integer.parseInt(datosUsuario[0]), datosUsuario[1],datosUsuario[2]));
            }
            entrada.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boolean logueado = false;
        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(usuario) && u.getPasswd().equals(passwd)) {
                logueado = true;
                System.out.println("Heyyy hay que hacer el siguiente menu");
            }
        }
        if (logueado == false){
            System.out.println("Logueo Incorrecto");
        }
    }

    public static void registro() {
        //Declaración de variables relacionadas con el inicio y registro de sesión
        String usuario = "";
        String passwd = ""; //(Contraseña)
        String passwd2 = ""; //(Para la típica verificación de "vuelva a escribir la contraseña para registrarse")

        //Declaración de Scanner
        Scanner teclado = new Scanner(System.in);

        //Datos del usuario a crear
        String[] datosUsuario = new String[3];

        //Lista de usuarios
        List<Usuario> usuarios = new ArrayList<Usuario>();

        //Solicitar datos de usuario
        System.out.println("Introduzca un nombre de usuario");
        usuario = teclado.nextLine();
        System.out.println("Introduzca una contraseña (Debe contener 6 caracteres, letras mayúsculas y minúsculas, y al menos un símbolo");
        passwd = teclado.nextLine();

        //Creacion del usuario
        Usuario nuevoUsuario = null;
        Usuario ultimoUsuario = null;
        int siguienteId = 0;
        File archivo = null;
        try {
            //Nombrar el archivo
            archivo = new File("usuarios.csv");

            Scanner entrada = new Scanner(archivo);

            while (entrada.hasNextLine()) {
                String linea = entrada.nextLine();
                if (!linea.isEmpty()) { // Verificar si la línea no está vacía
                    datosUsuario = linea.split(",");
                    if (datosUsuario.length > 0) {
                        ultimoUsuario = new Usuario(Integer.parseInt(datosUsuario[0]), datosUsuario[1], datosUsuario[2]);
                        siguienteId = ultimoUsuario.getIdUsuario() + 1;
                    }
                } else {
                    siguienteId = 0;
                }
            }
            entrada.close();
            nuevoUsuario = new Usuario(siguienteId, usuario, passwd);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        //Introducir el usuario en el fichero usuarios.csv
        if (nuevoUsuario!=null){
            File archivoUsuarios = new File("usuarios.csv");
            BufferedWriter bw = null;
            try{
                bw = new BufferedWriter(new FileWriter(archivoUsuarios, true));
                //Escribimos en un String los datos del usuario (que sera una linea con los datos separados por comas)
                String linea = nuevoUsuario.getIdUsuario()+ "," + usuario + "," + passwd;
                bw.write(linea);
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }



    public static void menuGrupos() {
        //Declaración de variables
        byte opcion;

        //Declaración de scanner
        Scanner teclado = new Scanner(System.in);

        do {
            System.out.println("Menú de Grupos");
            System.out.println("1. Añadir usuario a Grupo");
            System.out.println("2. Eliminar usuario de Grupo");
            System.out.println("3. Cambiar administrador de Grupo");
            System.out.println("4. Salir del menú de Grupos");

            opcion = teclado.nextByte();
            switch (opcion) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                default:
                    System.out.println("Opcion no valida");
            }

        } while (opcion != 4);

        teclado.close();
    }// Menú de Grupos

}