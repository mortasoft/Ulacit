package cliente_soap_taller;

import api.Estudiantes_Service;
import javax.swing.JOptionPane;

public class Cliente_SOAP_Taller {

    public static void main(String[] args) {

        Estudiantes_Service estudiantesAPI = new Estudiantes_Service();
        

        int opt = 0;
        boolean salir = false;
        String menu = "1. Agregar Estudiante\n"
                + "2. Crear Materias \n"
                + "3. Agregar Materias a Estudiante\n"
                + "4. Mostrar Materias Asignadas\n"
                + "5. Mostrar Estudiantes\n"
                + "6. Salir del programa\n"
                + "\nDigite su opcion para agregar a la orden";

        while (!salir) {

            opt = Integer.parseInt(JOptionPane.showInputDialog(menu));
            String resultado = "";

            switch (opt) {

                case 1:
                    String nombre = JOptionPane.showInputDialog("Digite el nombre");
                    String cedula = JOptionPane.showInputDialog("Digite la cedula");
                    String telefono = JOptionPane.showInputDialog("Digite el telefono");
                    resultado = estudiantesAPI.getEstudiantesPort().agregarEstudiante(nombre, telefono, cedula);
                    break;

                case 2:
                    String nombreMateria = JOptionPane.showInputDialog("Digite el nombre de la materia");
                    resultado = estudiantesAPI.getEstudiantesPort().agregarMateria(nombreMateria);
                    break;

                case 3:
                    String cedulaEstudiante = JOptionPane.showInputDialog("Digite la cedula");
                    String nombreMateria2 = JOptionPane.showInputDialog("Digite el nombre de la materia");
                    resultado = estudiantesAPI.getEstudiantesPort().agregarMateriaAEstudiante(nombreMateria2, cedulaEstudiante);
                    break;
                case 4:
                    String cedulaEstudiante2 = JOptionPane.showInputDialog("Digite la cedula");
                    resultado = estudiantesAPI.getEstudiantesPort().materiasDelEstudiante(cedulaEstudiante2);
                    break;

                case 5:
                    resultado = estudiantesAPI.getEstudiantesPort().listaEstudiantes();
                    break;

                case 6:
                    salir = true;
                    break;

                default:
                    resultado = "Debe digitar una opcion válida";

            }

            if (!salir) {
                JOptionPane.showMessageDialog(null, resultado);
            }

        }

    }

}
