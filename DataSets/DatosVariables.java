package DataSets;
// Flores Romero Andres Gael
// Datos de las Clases

import java.util.ArrayList;

public class DatosVariables {
    
    public static ArrayList<DatosVariables> Datos = new ArrayList<>();
    public double x, x1, x2, x3, y;

    public DatosVariables(double x){
        this.x = x;
    }

    public DatosVariables(double x, double y){
        this.x = x;
        this.y = y;
    }

    public DatosVariables(double x, double x1,double y){
        this.x = x;
        this.x1 = x1;
        this.y = y;
    }

    public static ArrayList<DatosVariables> DataSets(){

        Datos.add(new DatosVariables(108,95));
        Datos.add(new DatosVariables(115,96));
        Datos.add(new DatosVariables(106,95));
        Datos.add(new DatosVariables(97,97));
        Datos.add(new DatosVariables(95,93));
        Datos.add(new DatosVariables(91,94));
        Datos.add(new DatosVariables(97,95));
        Datos.add(new DatosVariables(83,93));
        Datos.add(new DatosVariables(83,92));
        Datos.add(new DatosVariables(78,86));
        Datos.add(new DatosVariables(54,73));
        Datos.add(new DatosVariables(67,80));
        Datos.add(new DatosVariables(56,65));
        Datos.add(new DatosVariables(53,69));
        Datos.add(new DatosVariables(61,77));
        Datos.add(new DatosVariables(115,96));
        Datos.add(new DatosVariables(81,87));
        Datos.add(new DatosVariables(78,89));
        Datos.add(new DatosVariables(30,60));
        Datos.add(new DatosVariables(45,63));
        Datos.add(new DatosVariables(99,95));
        Datos.add(new DatosVariables(32,61));
        Datos.add(new DatosVariables(25,55));
        Datos.add(new DatosVariables(28,56));
        Datos.add(new DatosVariables(90,94));
        Datos.add(new DatosVariables(89,93));
        
        return Datos;
    }

    public static ArrayList<DatosVariables> DataSetsQ(){

        Datos.add(new DatosVariables(1, 108, 95));
        Datos.add(new DatosVariables(1, 115, 96));
        Datos.add(new DatosVariables(1, 106, 95));
        Datos.add(new DatosVariables(1, 97, 97));
        Datos.add(new DatosVariables(1, 95, 93));
        Datos.add(new DatosVariables(1, 91, 94));
        Datos.add(new DatosVariables(1, 97, 95));
        Datos.add(new DatosVariables(1, 83, 93));
        Datos.add(new DatosVariables(1, 83, 92));
        Datos.add(new DatosVariables(1, 78, 86));
        Datos.add(new DatosVariables(1, 54, 73));
        Datos.add(new DatosVariables(1, 67, 80));
        Datos.add(new DatosVariables(1, 56, 65));
        Datos.add(new DatosVariables(1, 53, 69));
        Datos.add(new DatosVariables(1, 61, 77));
        Datos.add(new DatosVariables(1, 115, 96));
        Datos.add(new DatosVariables(1, 81, 87));
        Datos.add(new DatosVariables(1, 78, 89));
        Datos.add(new DatosVariables(1, 30, 60));
        Datos.add(new DatosVariables(1, 45, 63));
        Datos.add(new DatosVariables(1, 99, 95));
        Datos.add(new DatosVariables(1, 32, 61));
        Datos.add(new DatosVariables(1, 25, 55));
        Datos.add(new DatosVariables(1, 28, 56));
        Datos.add(new DatosVariables(1, 90, 94));
        Datos.add(new DatosVariables(1, 89, 93));
        return Datos;

    }

}
   