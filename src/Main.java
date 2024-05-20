import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

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

    public static void comprobarArchivos() {
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

    public static void menuPrincipal() {
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
                case 1:
                    try {
                        logueo();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                //Crear usuario (registrarse)
                case 2:
                    try {
                        registro();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                //Salir
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 3);
        teclado.close();
    }

    //Lista de usuarios

    public static List<Usuario> listaUsuariosArchivo() {
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
                usuarios.add(new Usuario(Integer.parseInt(datosUsuario[0]), datosUsuario[1], datosUsuario[2]));
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
                System.out.println("Logueo correcto\n");
                int idUsuarioLogueado = u.getIdUsuario();
                menuGrupos(idUsuarioLogueado);
            }
        }
        if (logueado == false) {
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
        if (nuevoUsuario != null) {
            File archivoUsuarios = new File("usuarios.csv");
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(archivoUsuarios, true));
                //Escribimos en un String los datos del usuario (que sera una linea con los datos separados por comas)
                String linea = nuevoUsuario.getIdUsuario() + "," + usuario + "," + passwd;
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
                case 1:
                    verGrupos(idUsuarioLogueado);
                    break;
                case 2:
                    crearGrupo(idUsuarioLogueado);
                    break;
                case 3:
                    eliminarGrupo(idUsuarioLogueado);
                    break;
                case 4:
                    entrarGrupo(idUsuarioLogueado);
                    break;
                case 5:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }

        } while (opcion != 5);
        //No cerrar teclado para no crear conflicto con el scanner teclado del menu anterior
    }

    //Lista de grupos

    public static List<Grupo> listaGruposArchivo() {
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
                datos2 = datos2.substring(1, datos2.length() - 1); //le quitamos los corchetes
                datosGrupo2 = datos2.split(",");
                for (String usuario : datosGrupo2) {
                    usuariosGrupo.add(Integer.parseInt(usuario.trim()));

                }

                Scanner entrada2 = new Scanner(datos1);
                datosGrupo = entrada2.nextLine().split(",");
                grupos.add(new Grupo(Integer.parseInt(datosGrupo[0]), datosGrupo[1], getUsuarioID(Integer.parseInt(datosGrupo[2])), usuariosGrupo));
            }
            entrada.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return grupos;
    }

    public static void verGrupos(int idUsuarioLogueado) {
        System.out.println("ID  Nombre");
        for (Grupo grupo : listaGruposArchivo()) {
            TreeSet<Integer> usuariosGrupo = new TreeSet(grupo.getUsuarios());
            if (usuariosGrupo.contains(idUsuarioLogueado)) {
                System.out.println(grupo.getIdGrupo() + " - " + grupo.getNombreGrupo());

            }
        }
    }

    public static void eliminarGrupo(int idUsuarioLogueado) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("ID  Nombre");
        for (Grupo grupo : listaGruposArchivo()) {
            if (grupo.getUsuarioAdmin().getIdUsuario() == idUsuarioLogueado) {
                System.out.println(grupo.getIdGrupo() + " - " + grupo.getNombreGrupo());
            }
        }
        System.out.println("Introduce el ID del grupo que quieres borrar:");
        int id = teclado.nextInt();
        if (getGrupoID(id) == null) {
            System.out.println("El grupo con el ID " + id + " no existe.");
            return;
        }
        Grupo grupo = getGrupoID(id);
        if (idUsuarioLogueado == grupo.getUsuarioAdmin().getIdUsuario()) {
            System.out.println(grupo.getIdGrupo() + " - " + grupo.getNombreGrupo());
            File archivoTemp = new File("gruposTemp.csv");
            for (Grupo g : listaGruposArchivo()) {
                if (g != null && g.getIdGrupo() != id) {
                    BufferedWriter bw = null;
                    try {
                        TreeSet<Integer> usuariosGrupo = new TreeSet(grupo.getUsuarios());
                        bw = new BufferedWriter(new FileWriter(archivoTemp, true));
                        String linea = g.getIdGrupo() + "," + g.getNombreGrupo() + "," + g.getUsuarioAdmin().getIdUsuario() + ";" + usuariosGrupo;
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
        } else {
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
        for (Usuario u : listaUsuariosArchivo()) {
            System.out.println(u);
        }
        String respuesta = "";
        System.out.println("Introduce el ID de un usuario al grupo:");
        int idUsuarioGrupo = teclado.nextInt();

        //Añadir usuario al grupo
        if (listaUsuariosArchivo().contains(getUsuarioID(idUsuarioGrupo))) {
            usuariosGrupo.add(idUsuarioGrupo);
        } else {
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
                if (listaUsuariosArchivo().contains(getUsuarioID(idUsuarioGrupo))) {
                    usuariosGrupo.add(idUsuarioGrupo);
                } else {
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
        if (nuevoGrupo != null) {
            File archivoGrupos = new File("grupos.csv");
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(archivoGrupos, true));
                //Escribimos en un String los datos del usuario (que sera una linea con los datos separados por comas)
                String linea = nuevoGrupo.getIdGrupo() + "," + nombreGrupo + "," + admin.getIdUsuario() + ";" + usuariosGrupo;
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

    public static void entrarGrupo(int idUsuarioLogueado) {
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
                menuGastos(idUsuarioLogueado, id);
            } else {
                // El usuario no forma parte de este grupo
                System.out.println("El usuario no forma parte de este grupo.");
            }
        } else {
            // El grupo con el ID especificado no existe
            System.out.println("El grupo con ID " + id + " no existe.");
        }

    }

    public static Usuario getUsuarioID(int idUsuario) {
        List<Usuario> usuarios = listaUsuariosArchivo();
        for (Usuario usuario : usuarios) {
            if (usuario.getIdUsuario() == idUsuario) {
                return usuario;
            }
        }
        return null;
    }

    public static Grupo getGrupoID(int idGrupo) {
        List<Grupo> grupos = listaGruposArchivo();
        for (Grupo grupo : grupos) {
            if (grupo.getIdGrupo() == idGrupo) {
                return grupo;
            }
        }
        return null;
    }

    public static void menuGastos(int idUsuarioLogueado, int idGrupo) {
        //Declaración de variables
        byte opcion;

        //Declaración de scanner
        Scanner teclado = new Scanner(System.in);

        do {
            System.out.println("\nMenú de Gastos");
            System.out.println("1. Ver gastos");
            System.out.println("2. Añadir gasto");
            System.out.println("3. Eliminar gasto");
            System.out.println("4. Añadir usuario al grupo");
            System.out.println("5. Ver saldo");
            System.out.println("6. Cierre de gastos");
            System.out.println("7. Mostrar usuarios del grupo");
            System.out.println("8. Volver al menu de grupos");

            opcion = teclado.nextByte();
            switch (opcion) {
                case 1: verGastos(idGrupo); break;
                case 2: addGasto(idUsuarioLogueado, idGrupo); break;
                case 3: eliminarGasto(idUsuarioLogueado, idGrupo); break;
                case 4: addUsuarioGrupo(idGrupo); break;
                case 5: verSaldo(idGrupo); break;
                case 6: cierreGastos(idGrupo); break;
                case 7: mostrarUsuarios(idGrupo); break;
                case 8: System.out.println("Volviendo al menú principal..."); break;
                default:
                    System.out.println("Opcion no valida");
            }

        } while (opcion != 8);
        //No cerrar teclado para no crear conflicto con el scanner teclado del menu anterior
    }

    public static void mostrarUsuarios(int idGrupo) {
        Grupo grupo = getGrupoID(idGrupo);
        TreeSet<Integer> usuariosGrupo = grupo.getUsuarios();
        System.out.println("ID  Nombre");
        for (Integer idUsuario : usuariosGrupo) {
            Usuario usuario = getUsuarioID(idUsuario);
            System.out.println(usuario.getIdUsuario() + " - " + usuario.getNombreUsuario());
        }
    }

    public static List<Gasto> listaGastosArchivo() {
        String[] datosGasto = new String[6]; //ID, IDUsuario, IDGrupo, Concepto, Fecha, Cantidad

        //Lista de gastos
        List<Gasto> gastos = new ArrayList<>();

        //Primeros pasos de crear el archivo / y crear objetos cliente
        try {
            File archivo = new File("gastos.csv");

            Scanner entrada = new Scanner(archivo);
            while (entrada.hasNextLine()) {
                datosGasto = entrada.nextLine().split(",");
                gastos.add(new Gasto(Integer.parseInt(datosGasto[0]), getUsuarioID(Integer.parseInt(datosGasto[1])), getGrupoID(Integer.parseInt(datosGasto[2])), datosGasto[3], LocalDateTime.parse(datosGasto[4]), Double.parseDouble(datosGasto[5])));
            }
            entrada.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return gastos;
    }

    public static void addGasto(int idUsuarioLogueado, int idGrupo) {
        //Declaración de variables relacionadas
        String concepto;
        double cantidad;
        LocalDateTime fecha = LocalDateTime.now();

        //Declaración de Scanner
        Scanner teclado = new Scanner(System.in);

        //Crear Usuario
        Usuario usuario = getUsuarioID(idUsuarioLogueado);

        //Datos del gasto a crear
        String[] datosGasto = new String[4];

        //Lista de gastos
        List<Gasto> gastos = new ArrayList<Gasto>();

        //Solicitar datos de gasto
        System.out.println("Introduzca un concepto para el gasto");
        concepto = teclado.nextLine();
        System.out.println("Introduzca una cantidad para el gasto");
        cantidad = teclado.nextDouble();
        System.out.println("Introduce el ID del usuario que ha pagado el gasto:");
        int idUsuarioPagador = teclado.nextInt();

        // Verificar si el usuario pertenece al grupo
        if (!getGrupoID(idGrupo).getUsuarios().contains(idUsuarioPagador)) {
            System.out.println("El usuario con el ID " + idUsuarioPagador + " no pertenece al grupo.");
            return;
        }



        //Crear Grupo
        Grupo grupo = getGrupoID(idGrupo);

        //Creacion del gasto
        Gasto nuevoGasto = null;
        Gasto ultimoGasto = null;
        int siguienteId = 0;
        File archivo = null;
        try {
            //Nombrar el archivo
            archivo = new File("gastos.csv");

            Scanner entrada = new Scanner(archivo);

            while (entrada.hasNextLine()) {
                String linea = entrada.nextLine();
                if (!linea.isEmpty()) { // Verificar si la línea no está vacía
                    datosGasto = linea.split(",");
                    if (datosGasto.length > 0) {
                        ultimoGasto = new Gasto(Integer.parseInt(datosGasto[0]), getUsuarioID(idUsuarioPagador), getGrupoID(Integer.parseInt(datosGasto[2])), datosGasto[3], LocalDateTime.parse(datosGasto[4]), Double.parseDouble(datosGasto[5]));
                        siguienteId = ultimoGasto.getIdGasto() + 1;
                    }
                } else {
                    siguienteId = 0;
                }
            }
            nuevoGasto = new Gasto(siguienteId, usuario, grupo, concepto, fecha, cantidad);
            entrada.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Introducir el gasto en el fichero gastos.csv
        if (nuevoGasto != null) {
            File archivoGastos = new File("gastos.csv");
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(archivoGastos, true));
                //Escribimos en un String los datos del usuario (que sera una linea con los datos separados por comas)
                String linea = nuevoGasto.getIdGasto() + "," + usuario.getIdUsuario() + "," + grupo.getIdGrupo() + "," + concepto + "," + fecha + "," + cantidad;
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
            dividirGastos(idUsuarioLogueado, nuevoGasto);
        }

        //listaGastosArchivo();
    }

    private static Division_Gastos getDivisionID(int id) {
        List<Division_Gastos> divisiones = listaDivisionArchivo();
        for (Division_Gastos division : divisiones) {
            if (division.getIdDivision() == id) {
                return division;
            }
        }
        return null;
    }

    public static List<Division_Gastos> listaDivisionArchivo() {
        String[] datosDivision = new String[6];

        //Lista de gastos
        List<Division_Gastos> division = new ArrayList<>();

        //Primeros pasos de crear el archivo / y crear objetos cliente
        try {
            File archivo = new File("divisiones.csv");

            Scanner entrada = new Scanner(archivo);
            while (entrada.hasNextLine()) {
                datosDivision = entrada.nextLine().split(",");
                division.add(new Division_Gastos(Integer.parseInt(datosDivision[0]), getGastoID(Integer.parseInt(datosDivision[1])), getUsuarioID(Integer.parseInt(datosDivision[2])), getUsuarioID(Integer.parseInt(datosDivision[3])), Double.parseDouble(datosDivision[4]) ,Double.parseDouble(datosDivision[5])));
            }
            entrada.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return division;
    }

    public static void dividirGastos(int idUsuarioLogueado, Gasto nuevoGasto) {
        // Variables
        AtomicInteger idDivision = new AtomicInteger();
        int idGasto = nuevoGasto.getIdGasto();
        int idUsuarioPagador = nuevoGasto.getUsuarioPagador().getIdUsuario();
        int nUsuarios = (int) nuevoGasto.getGrupo().getUsuarios().stream().count();
        double monto = nuevoGasto.getMonto();
        double divisionMonto = monto / nUsuarios;

        List<Division_Gastos> listaDivision = listaDivisionArchivo();

        // Obtiene el último elemento de la lista si existe
        if (!listaDivision.isEmpty()) {
            idDivision.set(listaDivision.get(listaDivision.size() - 1).getIdDivision() + 1);
        }

        // Datos de la division a crear
        String[] datosDivision = new String[4];

        // Calcula el nuevo idDivision basado en el último idDivision encontrado, incrementándolo en 1

        File archivo = new File("divisiones.csv");
        List<Integer> usuarios = new ArrayList<>(nuevoGasto.getGrupo().getUsuarios());

        usuarios.forEach(usuarioDebedor -> {
            int currentIdDivision = idDivision.getAndIncrement(); // Obtener el valor actual y luego incrementarlo

            String linea = currentIdDivision + "," + idGasto + "," + idUsuarioPagador + "," + usuarioDebedor + ",";

            if (usuarioDebedor == idUsuarioPagador) {
                linea += (monto - divisionMonto) + ",0.0";
            } else {
                linea += "0.0," + divisionMonto;
            }

            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(archivo, true));
                bw.write(linea);
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (bw != null) {
                        bw.close();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    public static void verGastos(int idGrupo){
        System.out.println("ID  Concepto  Cantidad  Fecha");
        for (Gasto gasto : listaGastosArchivo()) {
            if (gasto.getGrupo().getIdGrupo() == idGrupo) {
                System.out.println(gasto.getIdGasto() + " - " + gasto.getDescripcion() + " - " + gasto.getMonto() + " - " + gasto.getFecha());
            }
        }
    }

    public static void eliminarGasto(int idUsuarioLogueado, int idGrupo) {
        Scanner teclado = new Scanner(System.in);
        verGastos(idGrupo);

        System.out.println("Introduce el ID del gasto que quieres borrar:");
        int id = teclado.nextInt();

        if (getGastoID(id) == null) {
            System.out.println("El gasto con el ID " + id + " no existe.");
            return;
        }
        Gasto gasto = getGastoID(id);
        if (idUsuarioLogueado == gasto.getUsuarioPagador().getIdUsuario()) {
            System.out.println(gasto.getIdGasto() + " - " + gasto.getDescripcion() + " - " + gasto.getMonto() + " - " + gasto.getFecha());
            File archivo = new File("gastos.csv");
            File archivoTemp = new File("gastosTemp.csv");

            try (BufferedReader br = new BufferedReader(new FileReader(archivo));
                 BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemp))) {

                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(",");
                    int idGasto = Integer.parseInt(partes[0]); // Suponiendo que el ID del gasto está en la primera columna
                    if (idGasto != id) {
                        bw.write(linea);
                        bw.newLine();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Eliminar el archivo original y renombrar el archivo temporal
            if (!archivo.delete()) {
                System.out.println("No se pudo eliminar el archivo original");
            }
            if (!archivoTemp.renameTo(archivo)) {
                System.out.println("No se pudo renombrar el archivo temporal");
            }
        }

        //AQUI HAY QUE BORRAR AL IGUAL QUE EL GASTO LAS LINEAS DE DIVISION DE GASTO QUE COINCIDAN CON EL ID DE GASTO

        // Actualizar lista de gastos
        //listaGastosArchivo();

    }

    private static Gasto getGastoID(int id) {
        List<Gasto> gastos = listaGastosArchivo();
        for (Gasto gasto : gastos) {
            if (gasto.getIdGasto() == id) {
                return gasto;
            }
        }
        return null;
    }

    public static void addUsuarioGrupo(int idGrupo) {
        Scanner teclado = new Scanner(System.in);
        Grupo grupo = getGrupoID(idGrupo);

        // Obtener los usuarios que forman parte del grupo
        TreeSet<Integer> usuariosGrupo = grupo.getUsuarios();

        // Verificar si el usuario está en el grupo
        System.out.println("Estos son los usuarios que puedes añadir al grupo:");
        System.out.println("ID  Nombre");
        for (Usuario u : listaUsuariosArchivo()) {
            if (!usuariosGrupo.contains(u.getIdUsuario())) {
                System.out.println(u.getIdUsuario() + "  " + u.getNombreUsuario());
            }
        }

        System.out.println("Introduce el ID de un usuario para añadir al grupo:");
        int idUsuarioGrupo = teclado.nextInt();

        // Añadir usuario al grupo
        if (listaUsuariosArchivo().contains(getUsuarioID(idUsuarioGrupo)) && !usuariosGrupo.contains(idUsuarioGrupo)) {
            usuariosGrupo.add(idUsuarioGrupo);
            grupo.setUsuarios(usuariosGrupo); // Actualizar el conjunto de usuarios del grupo


            File archivoTemp = new File("gruposTemp.csv");
            File archivoOriginal = new File("grupos.csv");

            for (Grupo g : listaGruposArchivo()) {
                if (g != null && g.getIdGrupo() != idGrupo) {
                    BufferedWriter bw = null;
                    try {
                        TreeSet<Integer> usuarios = new TreeSet<>(g.getUsuarios());
                        bw = new BufferedWriter(new FileWriter(archivoTemp, true));
                        String linea = g.getIdGrupo() + "," + g.getNombreGrupo() + "," + g.getUsuarioAdmin().getIdUsuario() + ";" + usuarios;
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
                } else {
                    BufferedWriter bw = null;
                    try {
                        bw = new BufferedWriter(new FileWriter(archivoTemp, true));
                        String linea = grupo.getIdGrupo() + "," + grupo.getNombreGrupo() + "," + grupo.getUsuarioAdmin().getIdUsuario() + ";" + grupo.getUsuarios();
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
            if (archivoOriginal.delete()) {
                if (archivoTemp.renameTo(archivoOriginal)) {
                    System.out.println("Usuario añadido al grupo correctamente.");
                } else {
                    System.out.println("Error al renombrar el archivo temporal.");
                }
            } else {
                System.out.println("Error al eliminar el archivo original.");
            }
        } else {
            System.out.println("El ID del usuario no existe o ya está en el grupo");
        }



    } //ARREGLAR, NO FUNCIONA CORRECTAMENTE

    public static void verSaldo(int idGrupo){
        System.out.println("La media de gastos es:" + mediaGastosGrupo(idGrupo));

        List<Integer> usuariosGrupo = getGrupoID(idGrupo).getUsuarios().stream().toList();
        usuariosGrupo.forEach(usuario -> {
            System.out.printf("%-10s %.2f €%n", getUsuarioID(usuario).getNombreUsuario() , saldoIDUsuario(idGrupo, usuario));
        });

    }

    public static double saldoIDUsuario(int idGrupo, int idUsuario){
        List<Division_Gastos> divisionesGrupoUsuario = listaDivisionArchivo().stream()
                .filter(divisionGastos -> divisionGastos.getGasto().getGrupo().getIdGrupo() == idGrupo)
                .filter(divisionGastos -> divisionGastos.getUsuarioDebedor().getIdUsuario() == idUsuario)
                .collect(Collectors.toList());

        double sumaDebido = divisionesGrupoUsuario.stream()
                .mapToDouble(Division_Gastos::getMontoDebido)
                .sum();

        double sumaPagado = divisionesGrupoUsuario.stream()
                .mapToDouble(Division_Gastos::getMontoPagado)
                .sum();

        return -(sumaDebido)+sumaPagado;

    }

    public static double mediaGastosGrupo(int idGrupo){
        List<Gasto> gastosGrupo = listaGastosArchivo().stream()
                .filter(gasto -> gasto.getGrupo().getIdGrupo() == idGrupo)
                .collect(Collectors.toList());

        double media = gastosGrupo.stream()
                .mapToDouble(Gasto::getMonto)
                .average()
                .orElse(0.0);

        return media;
    }

    public static void cierreGastos(int idGrupo){
        Map<Usuario,Double> cierre = saldoUsuarios(idGrupo);

        // Calcular la suma de los valores absolutos del mapa
        //lo hacemos con valores absolutos para quitar el signo y asi coja los ultimos valores en el bucle
        double sumaValores = cierre.values().stream().mapToDouble(Math::abs).sum();

        Optional<Map.Entry<Usuario, Double>> max = cierre.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        Optional<Map.Entry<Usuario, Double>> min = cierre.entrySet().stream()
                .min(Map.Entry.comparingByValue());

        // Ejecutar el código mientras la suma de los valores no sea cero
        while (sumaValores != 0) {
            String nombreMin = min.get().getKey().getNombreUsuario();
            String nombreMax = max.get().getKey().getNombreUsuario();
            double saldoMin = min.get().getValue();
            double saldoMax = max.get().getValue();
            // Calcular la cantidad a transferir
            double montoTransferido = Math.min(Math.abs(saldoMin), Math.abs(saldoMax));

            // Determinar las actualizaciones de saldo dependiendo de los signos de los saldos
            if (saldoMin < 0 && saldoMax > 0) {
                cierre.put(min.get().getKey(), saldoMin + montoTransferido); // Saldo mínimo incrementado
                cierre.put(max.get().getKey(), saldoMax - montoTransferido); // Saldo máximo decrementado
            } else if (saldoMin > 0 && saldoMax < 0) {
                cierre.put(min.get().getKey(), saldoMin - montoTransferido); // Saldo mínimo decrementado
                cierre.put(max.get().getKey(), saldoMax + montoTransferido); // Saldo máximo incrementado
            }

            System.out.printf("%s le debe %.2f a %s%n", nombreMin, montoTransferido, nombreMax);

            // Actualizar la suma de los valores del mapa después de ejecutar tu código
            sumaValores = cierre.values().stream().mapToDouble(Math::abs).sum();
            min = cierre.entrySet().stream().min(Map.Entry.comparingByValue());
            max = cierre.entrySet().stream().max(Map.Entry.comparingByValue());

            // Salir del bucle si la suma de los valores es cero o muy cercana a cero
            if (Math.abs(sumaValores) < 0.000001) {
                break;
            }
        }


    }

    public static Map<Usuario, Double> saldoUsuarios(int idGrupo){
        List<Integer> usuariosGrupo = getGrupoID(idGrupo).getUsuarios().stream().toList();
        Map<Usuario, Double> saldoUsuarios = usuariosGrupo.stream()
                .collect(Collectors.toMap(
                        usuario -> getUsuarioID(usuario),
                        usuario -> saldoIDUsuario(idGrupo, usuario)
                ));
        return saldoUsuarios;
    }


}