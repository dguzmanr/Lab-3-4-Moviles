package com.example.david.myapplication.AccesoDatos;
import com.example.david.myapplication.LogicaNegocio.Alumno;
import com.example.david.myapplication.LogicaNegocio.Carrera;
import com.example.david.myapplication.LogicaNegocio.Curso;
import com.example.david.myapplication.LogicaNegocio.Grupo;
import com.example.david.myapplication.LogicaNegocio.Profesor;
import com.example.david.myapplication.LogicaNegocio.Ciclo;

import java.util.ArrayList;
import java.util.List;

public class ModelData {

    private List<Carrera> carreraList;
    private List<Curso> cursoList;
   private List<Alumno> alumnoList;
    private List<Profesor> profesorList;
    private List<Ciclo> cicloList;
    private List<Grupo> grupoList;

    public ModelData() {
        carreraList = new ArrayList<>();
        cursoList = new ArrayList<>();
        alumnoList = new ArrayList<>();
        profesorList = new ArrayList<>();
        cicloList = new ArrayList<>();
        grupoList = new ArrayList<>();
        prepareCarreraData();
        prepareAlumnoData();
        prepareCursoData();
        prepareCicloData();
        prepareProfesorData();
        prepareGrupoData();
    }

    public void prepareGrupoData() {

        Carrera carrIng = new Carrera("EIF", "Ingenieria en sistemas", "Bachillerato");
        Carrera carraAdm= new Carrera("ADM", "Administracion", "Bachillerato");

        Ciclo ciclo1 = new Ciclo("C1-2018", "2018",'0','1', "12/02", "12/06");
        Ciclo ciclo2 = new Ciclo("C2-2018", "2018",'0','2', "12/02", "12/06");
        Ciclo ciclo3 = new Ciclo("C1-2019", "2019",'1','1', "12/02", "12/06");
        Ciclo ciclo4 = new Ciclo("C2-2019", "2019",'1','1', "12/02", "12/06");


        Curso cursoSop = new Curso("ST",ciclo3 ,carrIng,"Soporte", 3, 4);
        Curso cursoFun = new Curso("FD", ciclo3,carrIng,"Fundamentos", 3, 4);
        Curso cursoAdm = new Curso("AD", ciclo4,carraAdm,"Administracion 1", 3, 4);

        Profesor profesor1 = new Profesor("123", "Jose", "@jose", 678);
        Profesor profesor2 = new Profesor("234", "Juan", "@juan", 876);
        Profesor profesor3 = new Profesor("345", "Mario", "@mario", 789);
        Profesor profesor4 = new Profesor("456", "Jesus", "@Jesus", 978);

        Grupo grupo = new Grupo("G-01",cursoFun, profesor1,"Noche");
        grupoList.add(grupo);
        grupo = new Grupo("G-02",cursoSop, profesor2,"Noche");
        grupoList.add(grupo);
        grupo = new Grupo("G-03",cursoAdm, profesor4,"Mañana");
        grupoList.add(grupo);
        grupo = new Grupo("G-04",cursoSop, profesor2,"Tarde");
        grupoList.add(grupo);
    }

    public void prepareCarreraData() {
        Carrera carrera = new Carrera("EIF", "Ingenieria en sistemas", "Bachillerato");
        carreraList.add(carrera);

        carrera = new Carrera("ADM", "Administracion", "Bachillerato");
        carreraList.add(carrera);
    }
    public void prepareCursoData() {

        Carrera carrIng = new Carrera("EIF", "Ingenieria en sistemas", "Bachillerato");
        Carrera carraAdm= new Carrera("ADM", "Administracion", "Bachillerato");

        Ciclo ciclo1 = new Ciclo("C1-2018", "2018",'0','1', "12/02", "12/06");
        Ciclo ciclo2 = new Ciclo("C2-2018", "2018",'0','2', "12/02", "12/06");
        Ciclo ciclo3 = new Ciclo("C1-2019", "2019",'1','1', "12/02", "12/06");
        Ciclo ciclo4 = new Ciclo("C2-2019", "2019",'1','1', "12/02", "12/06");


        Curso curso = new Curso("ST",ciclo3 ,carrIng,"Soporte", 3, 4);
        cursoList.add(curso);
        curso = new Curso("FD", ciclo3,carrIng,"Fundamentos", 3, 4);
        cursoList.add(curso);
        curso = new Curso("AD", ciclo4,carraAdm,"Administracion 1", 3, 4);
        cursoList.add(curso);

   }

    public void prepareAlumnoData() {
        Alumno alumno = new Alumno("123", "Jose", 321, "@Jose", "23/06/1985");
        alumnoList.add(alumno);

        alumno = new Alumno("321", "Juan", 213, "@Juan", "24/06/1986");
        alumnoList.add(alumno);

        alumno = new Alumno("234", "Miguel", 3241, "@Miguel", "25/06/1987");
        alumnoList.add(alumno);

        alumno = new Alumno("345", "Manuel", 3251, "@Manuel", "26/06/1988");
        alumnoList.add(alumno);

        alumno = new Alumno("456", "Luis", 567, "@Luis", "27/06/1989");
        alumnoList.add(alumno);

        alumno = new Alumno("567", "Alberto", 8765, "@Alberto", "28/06/1990");
        alumnoList.add(alumno);
    }

    public void prepareProfesorData() {
        Profesor profesor = new Profesor("123", "Jose", "@jose", 678);
        profesorList.add(profesor);

        profesor = new Profesor("234", "Juan", "@juan", 876);
        profesorList.add(profesor);

        profesor = new Profesor("345", "Mario", "@mario", 789);
        profesorList.add(profesor);

        profesor = new Profesor("456", "Jesus", "@Jesus", 978);
        profesorList.add(profesor);
    }

    public void prepareCicloData() {
        Ciclo ciclo = new Ciclo("C1-2018", "2018",'0','1', "12/02", "12/06");
        cicloList.add(ciclo);

        ciclo = new Ciclo("C2-2018", "2018",'0','2', "12/02", "12/06");
        cicloList.add(ciclo);

        ciclo = new Ciclo("C1-2019", "2019",'1','1', "12/02", "12/06");
        cicloList.add(ciclo);

        ciclo = new Ciclo("C2-2019", "2019",'1','1', "12/02", "12/06");
        cicloList.add(ciclo);

    }

    public List<Carrera> getCarreraList() {
        return carreraList;
    }

    public List<Curso> getCursoList() {
        return cursoList;
    }

    public List<Alumno> getAlumnoList() {
        return alumnoList;
    }

    public List<Profesor> getProfesorList() {
        return profesorList;
    }

    public List<Ciclo> getCicloList() {
        return cicloList;
    }
//
    public List<Grupo> getGrupoList() {
        return grupoList;
    }

    public void setGrupoList(List<Grupo> grupoList) {
        this.grupoList = grupoList;
    }

    public void setCarreraList(List<Carrera> carreraList) {
        this.carreraList = carreraList;
    }

    public void setCursoList(List<Curso> cursoList) {
        this.cursoList = cursoList;
    }

    public void setAlumnoList(List<Alumno> alumnoList) {
        this.alumnoList = alumnoList;
    }

    public void setProfesorList(List<Profesor> profesorList) {
        this.profesorList = profesorList;
    }

    public void setCicloList(List<Ciclo> cicloList) {
        this.cicloList = cicloList;
    }
//
//    public List<Usuario> getUsuariosList() {
//        List<Usuario> users = new ArrayList<>();
//        users.add(new Usuario("@admin", "admin", "administrador", "111"));
//        users.add(new Usuario("@admin2", "admin", "administrador", "222"));
//        users.add(new Usuario("@matric", "matric", "matriculador", "333"));
//        users.add(new Usuario("@matric1", "matric", "matriculador", "444"));
//        users.add(new Usuario("@matric2", "matric", "matriculador", "555"));
//        users.add(new Usuario("@matric3", "matric", "matriculador", "555"));
//        return users;
//    }
}
