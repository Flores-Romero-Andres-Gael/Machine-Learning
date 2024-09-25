package QuadraticRegression;
// Flores Romero Andres Gael
// QuadraticRegression.Quadratic Regression

import DataSets.DatosVariables;
import java.util.ArrayList;
import Algebra.Ecuaciones;
import Coefficient_of_Determination.COD;
import Correlation.Correlacion;


public class Quadratic {

    public static void main(String[] args) {
        ArrayList<DatosVariables> ALLDatos = new ArrayList<>(DatosVariables.DataSets());

        int size = ALLDatos.size();
        int subSize = (int) (size * 0.7);
        ArrayList<DatosVariables> Datos = new ArrayList<>(ALLDatos.subList(0, subSize));
        ArrayList<DatosVariables> Restante = new ArrayList<>(ALLDatos.subList(subSize, size));

        int size2 = ALLDatos.size();
        int subSize2 = (int) (size * 0.3);
        ArrayList<DatosVariables> Restante2 = new ArrayList<>(ALLDatos.subList(0, subSize2));
        ArrayList<DatosVariables> Datos2 = new ArrayList<>(ALLDatos.subList(subSize2, size2));
        
        int[] valoresX = {100, 221, 98, 115, 130};

        System.out.println("\n--------Todo el DataSet:--------");
        double[] coefficients = OPERATIONS(ALLDatos);
        double B0 = coefficients[0];
        double B1 = coefficients[1];
        double B2 = coefficients[2];
        double CorrelacionTotal = Correlacion.COR(ALLDatos, new double[]{B0, B1, B2});
        System.out.println("Correlacion (R): " + CorrelacionTotal);
        System.out.println("Coeficiente de Error: " + COD.COError(ALLDatos, new double[]{B0, B1, B2}));
        double R2Total = COD.R2(ALLDatos, new double[]{B0, B1, B2});
        System.out.println("Coeficiente de Determinacion (R^2): " + R2Total);
        System.out.println("\n--------Simulaciones:--------");
        Simulaciones(B0, B1, B2, valoresX);

        System.out.println("\n--------Primeros 70% - 30%:--------");
        double[] coefficients2 = OPERATIONS(Datos);
        double B02 = coefficients2[0];
        double B12 = coefficients2[1];
        double B22 = coefficients2[2];
        double Correlacion1 = Correlacion.COR(Datos, coefficients2);
        System.out.println("Correlacion (R): " + Correlacion1);
        System.out.println("Coeficiente de Error: " + COD.COError(Restante, new double[]{B02, B12, B22}));
        double R2Set = COD.R2(Restante, new double[]{B02, B12, B22});
        System.out.println("Coeficiente de Determinacion (R^2): " + R2Set);
        System.out.println("\n--------Simulaciones:--------");
        Simulaciones(B02, B12, B22, valoresX);

        System.out.println("\n--------Segundos 30% - 70%:--------");
        double[] coefficients3 = OPERATIONS(Datos2);
        double B03 = coefficients3[0];
        double B13 = coefficients3[1];
        double B23 = coefficients3[2];
        double Correlacion2 = Correlacion.COR(Datos2, coefficients3);
        System.out.println("Correlacion (R): " + Correlacion2);
        System.out.println("Coeficiente de Error: " + COD.COError(Restante2, new double[]{B03, B13, B23}));
        double R2Set2 = COD.R2(Restante2, new double[]{B03, B13, B23});
        System.out.println("Coeficiente de Determinacion (R^2): " + R2Set2);
        System.out.println("\n--------Simulaciones:--------");
        Simulaciones(B03, B13, B23, valoresX);

        double mejorR2 = mejorR2(R2Total, R2Set, R2Set2);
        System.out.println("\n--------Mejor Coeficiente de Determinacion (R^2): " + mejorR2 + "--------\n");

    }

    public static void Simulaciones(double B0, double B1, double B2, int[] valoresX) {
        System.out.println("Ecuacion:\ny = " + B0 + " + " + B1 + "(x) + " + B2 + "(x^2)");
        for (int x : valoresX) {
            double simulacion = B0 + B1 * x + B2 * Math.pow(x, 2);
            System.out.println("X = " + x + " , Y: " + simulacion);
        }
    }

    public static double[] OPERATIONS(ArrayList<DatosVariables> Datos){

        double[][] MatrizX = Ecuaciones.OperacionesBasicas(Datos,3,4);
        System.out.println("Matriz X:");
        Ecuaciones.ImprimirMatriz(MatrizX);

        System.out.println("Matriz Resuelta por Gauss Jordan: ");
        Ecuaciones.GussJordan(MatrizX);
        Ecuaciones.ImprimirMatriz(MatrizX);

        double B2 = MatrizX[0][3];
        double B1 = MatrizX[1][3];
        double B0 = MatrizX[2][3];

        System.out.println("B0: " + B0 + ", B1: " + B1 + ", B2: " + B2);

        return new double[]{B0, B1, B2};
    }

    public static double mejorR2(double R2Total, double R2Set1, double R2Set2) {
        return Math.max(R2Total, Math.max(R2Set1, R2Set2));
    }

}
