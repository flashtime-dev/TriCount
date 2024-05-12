import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Cabecera del programa
        System.out.println("▀▀█▀▀ ▒█▀▀█ ▀█▀ ▒█▀▀█ ▒█▀▀▀█ ▒█░▒█ ▒█▄░▒█ ▀▀█▀▀ TM");
        System.out.println("░▒█░░ ▒█▄▄▀ ▒█░ ▒█░░░ ▒█░░▒█ ▒█░▒█ ▒█▒█▒█ ░▒█░░");
        System.out.println("░▒█░░ ▒█░▒█ ▄█▄ ▒█▄▄█ ▒█▄▄▄█ ░▀▄▄▀ ▒█░░▀█ ░▒█░░");
        System.out.println("Todos los derechos reservados");

        //Comprobamos que los archivos existen
        comprobarArchivos();

        //Inicio del programa
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
            System.out.println("\nMenú principal:");
            System.out.println("1.- Iniciar Sesión");
            System.out.println("2.- Registrarse");
            System.out.println("3.- Salir");
            opcion = teclado.nextByte();

            switch (opcion) {
                //Iniciar sesión
                case 1: logueo(); break;
                //Crear usuario (registrarse)
                case 2: registro(); break;
                //Salir
                case 3: System.out.println("Saliendo..."); break;
                default: System.out.println("Opcion no valida");
            }
        }while (opcion!=3);
        teclado.close();
    }

    //Lista de usuarios

    public static List<Usuario> listaUsuariosArchivo(){
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

        return usuarios;
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
        List<Usuario> usuarios = new ArrayList<Usuario>(listaUsuariosArchivo());

        boolean logueado = false;
        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(usuario) && u.getPasswd().equals(passwd)) {
                logueado = true;
                System.out.println("Logueo correcto\n\n");
                int idUsuarioLogueado = u.getIdUsuario();
                menuGrupos(idUsuarioLogueado);
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

    public static void menuGrupos(int idUsuarioLogueado) {
        //Declaración de variables
        byte opcion;

        //Declaración de scanner
        Scanner teclado = new Scanner(System.in);

        do {
            System.out.println("\nMenú de Grupos");
            System.out.println("1. Ver grupos");
            System.out.println("2. Crear un grupo");
            System.out.println("3. Eliminar un grupo");
            System.out.println("4. Entrar a un grupo");
            System.out.println("5. Volver al menu de principal");

            opcion = teclado.nextByte();
            switch (opcion) {
                case 1: verGrupos(idUsuarioLogueado); break;
                case 2: crearGrupo(idUsuarioLogueado); break;
                case 3: eliminarGrupo(idUsuarioLogueado); break;
                case 4: entrarGrupo(idUsuarioLogueado); break;
                case 5: System.out.println("Volviendo al menú principal..."); break;
                default:
                    System.out.println("Opcion no valida");
            }

        } while (opcion != 5);
        //No cerrar teclado para no crear conflicto con el scanner teclado del menu anterior
    }

    //Lista de grupos

    public static List<Grupo> listaGruposArchivo(){
        String[] datosGrupo = new String[4];
        String[] datosGrupo2;

        String datos1 = "";
        String datos2 = "";

        //Lista de grupos
        List<Grupo> grupos = new ArrayList<>();

        //Primeros pasos de crear el archivo / y crear objetos cliente
        try {
            File archivo = new File("grupos.csv");

            Scanner entrada = new Scanner(archivo);
            while (entrada.hasNextLine()) {
                TreeSet usuariosGrupo = new TreeSet();

                //Separamos los datos por ; para separar la lista de usuarios del grupo
                datosGrupo = entrada.nextLine().split(";");
                datos1 = datosGrupo[0]; //Metesmos los datos en un string para separarlos por comas;
                datos2 = datosGrupo[1]; //metemos los datos en un string para meterlos en un treeset
                datos2 = datos2.substring(1, datos2.length()-1); //le quitamos los corchetes
                datosGrupo2 = datos2.split(",");
                for (String usuario : datosGrupo2) {
                    usuariosGrupo.add(Integer.parseInt(usuario.trim()));

                }

                Scanner entrada2 = new Scanner(datos1);
                datosGrupo = entrada2.nextLine().split(",");
                grupos.add(new Grupo(Integer.parseInt(datosGrupo[0]), datosGrupo[1],getUsuarioID(Integer.parseInt(datosGrupo[2])), usuariosGrupo));
            }
            entrada.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return grupos;
    }

    public static void verGrupos(int idUsuarioLogueado){
        System.out.println("ID  Nombre");
        for (Grupo grupo : listaGruposArchivo()){
            TreeSet<Integer> usuariosGrupo = new TreeSet(grupo.getUsuarios());
            if (usuariosGrupo.contains(idUsuarioLogueado)){
                System.out.println(grupo.getIdGrupo() + " - " + grupo.getNombreGrupo());

            }
        }
    }

    public static void eliminarGrupo(int idUsuarioLogueado) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("ID  Nombre");
        for (Grupo grupo : listaGruposArchivo()){
            if (grupo.getUsuarioAdmin().getIdUsuario() == idUsuarioLogueado) {
                System.out.println(grupo.getIdGrupo() + " - " + grupo.getNombreGrupo());
            }
        }
        System.out.println("Introduce el ID del grupo que quieres borrar:");
        int id = teclado.nextInt();
        Grupo grupo = getGrupoID(id);
        if (idUsuarioLogueado == grupo.getUsuarioAdmin().getIdUsuario()) {
            System.out.println(grupo.getIdGrupo() + " - " + grupo.getNombreGrupo());
            File archivoTemp = new File("gruposTemp.csv");
            for (Grupo g : listaGruposArchivo()){
                if (g!=null && g.getIdGrupo()!=id){
                    BufferedWriter bw = null;
                    try{
                        TreeSet<Integer> usuariosGrupo = new TreeSet(grupo.getUsuarios());
                        bw = new BufferedWriter(new FileWriter(archivoTemp, true));
                        String linea = g.getIdGrupo()+ "," + g.getNombreGrupo() + "," + g.getUsuarioAdmin().getIdUsuario() + ";" + usuariosGrupo;
                        bw.write(linea);
                        bw.newLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } finally {
                        try {
                            bw.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
            File archivoGrupos = new File("grupos.csv");
            // Renombrar el archivo temporal al nombre del archivo original
            if (!archivoGrupos.delete()) {
                System.out.println("No se pudo eliminar el grupo");
            }

            // Renombrar el archivo temporal al nombre del archivo original
            if (!archivoTemp.renameTo(archivoGrupos)) {
                System.out.println("No se borrar el grupo");
            }
        }else {
            System.out.println("No tienes permisos para borrar este grupo");
        }
    }

    public static void crearGrupo(int idUsuarioLogueado) {
        //Declaración de variables relacionadas
        String nombreGrupo = "";
        int usuarioAdmin = idUsuarioLogueado;


        //Declaración de Scanner
        Scanner teclado = new Scanner(System.in);

        //Datos del usuario a crear
        String[] datosGrupo = new String[4];

        //Lista de grupos
        List<Grupo> grupos = new ArrayList<Grupo>();

        //Solicitar datos de grupo
        System.out.println("Introduzca un nombre para el grupo");
        nombreGrupo = teclado.nextLine();

        //Crear Usuario Admin
        Usuario admin = getUsuarioID(usuarioAdmin);

        //Crear TreeSet con los usuarios correspondientes
        TreeSet usuariosGrupo = new TreeSet();
        usuariosGrupo.add(admin.getIdUsuario());

        //Agregar usuarios
        System.out.println("Estos son los usuarios que existen:");
        System.out.println("ID  Nombre");
        for (Usuario u : listaUsuariosArchivo()){
            System.out.println(u);
        }
        String respuesta = "";
        System.out.println("Introduce el ID de un usuario al grupo:");
        int idUsuarioGrupo = teclado.nextInt();

        //Añadir usuario al grupo
        if (listaUsuariosArchivo().contains(getUsuarioID(idUsuarioGrupo))){
            usuariosGrupo.add(idUsuarioGrupo);
        }else {
            System.out.println("El ID del usuario no existe");
        }


        do {
            System.out.println("Quieres introducir otro usuario?");
            teclado.nextLine();
            respuesta = teclado.nextLine();
            respuesta = respuesta.toLowerCase();

            if (respuesta.equals("si")) {
                System.out.println("Introduce el ID de un usuario al grupo:");
                idUsuarioGrupo = teclado.nextInt();

                //Añadir usuario al grupo
                if (listaUsuariosArchivo().contains(getUsuarioID(idUsuarioGrupo))){
                    usuariosGrupo.add(idUsuarioGrupo);
                }else {
                    System.out.println("El ID del usuario no existe");
                }
            }

            if (!respuesta.equals("si") && !respuesta.equals("no")) {
                System.out.println("Respuesta incorrecta (escriba si o no)");
                respuesta = "si";
            }
        } while (respuesta.equals("si"));


        //Creacion del grupo
        Grupo nuevoGrupo = null;
        Grupo ultimoGrupo = null;
        int siguienteId = 0;
        File archivo = null;
        try {
            //Nombrar el archivo
            archivo = new File("grupos.csv");

            Scanner entrada = new Scanner(archivo);

            while (entrada.hasNextLine()) {
                String linea = entrada.nextLine();
                if (!linea.isEmpty()) { // Verificar si la línea no está vacía
                    datosGrupo = linea.split(",");
                    if (datosGrupo.length > 0) {
                        ultimoGrupo = new Grupo(Integer.parseInt(datosGrupo[0]), datosGrupo[1], admin, usuariosGrupo);
                        siguienteId = ultimoGrupo.getIdGrupo() + 1;
                    }
                } else {
                    siguienteId = 0;
                }
            }
            entrada.close();
            nuevoGrupo = new Grupo(siguienteId, nombreGrupo, admin, usuariosGrupo);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


        //Introducir el grupo en el fichero grupos.csv
        if (nuevoGrupo!=null){
            File archivoGrupos = new File("grupos.csv");
            BufferedWriter bw = null;
            try{
                bw = new BufferedWriter(new FileWriter(archivoGrupos, true));
                //Escribimos en un String los datos del usuario (que sera una linea con los datos separados por comas)
                String linea = nuevoGrupo.getIdGrupo()+ "," + nombreGrupo + "," + admin.getIdUsuario() + ";" + usuariosGrupo;
                bw.write(linea);
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public static void entrarGrupo(int idUsuarioLogueado){
        Scanner teclado = new Scanner(System.in);
        // Imprimir grupos de los que formas parte
        verGrupos(idUsuarioLogueado);

        // Introducir id
        System.out.println("Introduzca el ID del grupo al que quieres entrar");
        int id = teclado.nextInt();

        // Obtener el grupo a través del id
        Grupo grupo = getGrupoID(id);

        // Verificar si el grupo existe
        if (grupo != null) {
            // Obtener los usuarios que forman parte del grupo
            TreeSet<Integer> usuariosGrupo = grupo.getUsuarios();

            // Verificar si el usuario está en el grupo
            if (usuariosGrupo.contains(idUsuarioLogueado)) {
                // Mostrar el menú de gastos
                menuGastos(idUsuarioLogueado);
            } else {
                // El usuario no forma parte de este grupo
                System.out.println("El usuario no forma parte de este grupo.");
            }
        } else {
            // El grupo con el ID especificado no existe
            System.out.println("El grupo con ID " + id + " no existe.");
        }

    }

    public static Usuario getUsuarioID(int idUsuario){
        List<Usuario> usuarios = listaUsuariosArchivo();
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario() == idUsuario) {
                return usuario;
            }
        }
        return null;
    }

    public static Grupo getGrupoID(int idGrupo){
        List<Grupo> grupos = listaGruposArchivo();
        for (Grupo grupo : grupos) {
            if (grupo.getIdGrupo() == idGrupo) {
                return grupo;
            }
        }
        return null;
    }

    public static void menuGastos(int idUsuarioLogueado){
        //Declaración de variables
        byte opcion;

        //Declaración de scanner
        Scanner teclado = new Scanner(System.in);

        do {
            System.out.println("\nMenú de Gastos");
            System.out.println("1. Añadir gasto");
            System.out.println("2. Eliminar gasto");
            System.out.println("3. Añadir usuario al grupo");
            System.out.println("4. Eliminar usuario del grupo");
            System.out.println("5. Ver saldo");
            System.out.println("6. Dividir gastos");
            System.out.println("7. Volver al menu de grupos");

            opcion = teclado.nextByte();
            switch (opcion) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7: System.out.println("Volviendo al menú principal..."); break;
                default:
                    System.out.println("Opcion no valida");
            }

        } while (opcion != 7);
        //No cerrar teclado para no crear conflicto con el scanner teclado del menu anterior
    }

    public static void addGasto(){}
    public static void eliminarGasto(){}
    public static void verSaldo(){}
    public static void dividirGastos(){}


}