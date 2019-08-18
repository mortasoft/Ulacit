package API;

import DataBase.Estudiante;
import DataBase.Materia;
import DataBase.UtilsUniversidad;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "Estudiantes")
public class Estudiantes {

    @WebMethod(operationName = "AgregarEstudiante")
    public String AgregarEstudiante(@WebParam(name = "Nombre") String nombre, @WebParam(name = "Telefono") String telefono, @WebParam(name = "Cedula") String cedula) {
        try {

            Estudiante e = new Estudiante(nombre, telefono, cedula);
            UtilsUniversidad.listaEstudiantes.add(e);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Se agregó el estudiante";
    }

    @WebMethod(operationName = "AgregarMateria")
    public String AgregarMateria(@WebParam(name = "Nombre") String nombreMateria) {
        try {

            Materia m = new Materia(nombreMateria);
            UtilsUniversidad.listaMaterias.add(m);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "Se agregó la materia";
    }

    @WebMethod(operationName = "AgregarMateriaAEstudiante")
    public String AgregarMateriaAEstudiante(@WebParam(name = "NombreMateria") String nombreMateria, @WebParam(name = "CedulaEstudiante") String cedulaEstudiante) {
        try {

            Materia m = null;

            for (Materia materia : UtilsUniversidad.listaMaterias) {
                if (materia.getNombreMateria().equals(nombreMateria)) {
                    m = materia;
                }

            }

            if (m == null) {
                return "La materia no existe";
            }

            for (Estudiante estudiante : UtilsUniversidad.listaEstudiantes) {
                if (estudiante.getCedula().equals(cedulaEstudiante)) {
                    estudiante.getListaMaterias().add(m);
                    return "La materia se agrego correctamente al estudiante";
                }
            }

            return "El estudiante no existe";

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @WebMethod(operationName = "MateriasDelEstudiante")
    public String MateriasDelEstudiante(@WebParam(name = "CedulaEstudiante") String cedulaEstudiante) {
        try {

            String salida = "";
            boolean encontrado = false;

            for (Estudiante estudiante : UtilsUniversidad.listaEstudiantes) {
                if (estudiante.getCedula().equals(cedulaEstudiante)) {
                    for (Materia materia : estudiante.getListaMaterias()) {
                        salida += materia.getNombreMateria() + "\n";
                        encontrado = true;
                    }
                }
            }
            if (encontrado) {
                return "Lista de Materias:\n\n" + salida;
            } else {
                return "El estudiante no tiene materias";
            }

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @WebMethod(operationName = "ListaEstudiantes")
    public String ListaEstudiantes() {
        try {
            String salida = "";

            for (Estudiante estudiante : UtilsUniversidad.listaEstudiantes) {
                salida += estudiante.getCedula() + ": " + estudiante.getNombre() + "\n";
            }

            return "Lista de estudiantes: \n\n" + salida;
        } catch (Exception e) {
            return e.getMessage();
        }

    }
}
