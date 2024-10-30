package Polynomial;
// Flores Romero Andres Gael
// PolynomialRegression.Polynomial Regression

import DataSets.DatosVariables;
import java.util.ArrayList;
import Algebra.Ecuaciones;
import Coefficient_of_Determination.COD;
import Correlation.Correlacion;

public class Polynomial {

    public static void main(String[] args) {
        ArrayList<DatosVariables> ALLDatos = new ArrayList<>(DatosVariables.DataSets());

        int size = ALLDatos.size();
        int subSize = (int) (size * 0.7);
        ArrayList<DatosVariables> Datos = new ArrayList<>(ALLDatos.subList(0, subSize));
        ArrayList<DatosVariables> Restante = new ArrayList<>(ALLDatos.subList(subSize, size));

        int[] valoresX = {100, 221, 98, 115, 130};

        System.out.println("\n--------Todo el DataSet:--------");
        double[] coefficients = OPERATIONS(ALLDatos);
        displayResults(coefficients, ALLDatos, valoresX);

        System.out.println("\n--------Primeros 70% - 30%:--------");
        double[] coefficients70_30 = OPERATIONS(Datos);
        displayResults(coefficients70_30, Restante, valoresX);

        System.out.println("\n--------Segundos 30% - 70%:--------");
        ArrayList<DatosVariables> Datos2 = new ArrayList<>(ALLDatos.subList((int)(size * 0.3), size));
        ArrayList<DatosVariables> Restante2 = new ArrayList<>(ALLDatos.subList(0, (int)(size * 0.3)));
        double[] coefficients30_70 = OPERATIONS(Datos2);
        displayResults(coefficients30_70, Restante2, valoresX);

        double mejorR2 = mejorR2(
            COD.R2(ALLDatos, coefficients),
            COD.R2(Restante, coefficients70_30),
            COD.R2(Restante2, coefficients30_70)
        );
        System.out.println("\n--------Mejor Coeficiente de Determinacion (R^2): " + mejorR2 + "--------\n");
    }

    public static void displayResults(double[] coefficients, ArrayList<DatosVariables> dataset, int[] valoresX) {
        System.out.println("Ecuacion:");
        for (int i = 0; i < coefficients.length; i++) {
            System.out.print((i == 0 ? "" : " + ") + coefficients[i] + "(x^" + i + ")");
        }
        System.out.println();
        
        double correlacion = Correlacion.COR(dataset, coefficients);
        System.out.println("Correlacion (R): " + correlacion);
        System.out.println("Coeficiente de Error: " + COD.COError(dataset, coefficients));
        System.out.println("Coeficiente de Determinacion (R^2): " + COD.R2(dataset, coefficients));

        System.out.println("\n--------Simulaciones:--------");
        for (int x : valoresX) {
            double simulacion = 0;
            for (int i = 0; i < coefficients.length; i++) {
                simulacion += coefficients[i] * Math.pow(x, i);
            }
            System.out.println("X = " + x + " , Y: " + simulacion);
        }
    }

    public static double[] OPERATIONS(ArrayList<DatosVariables> Datos){
        int degree = 3; // Ajusta este valor al grado del polinomio deseado
        double[][] matrizX = Ecuaciones.OperacionesBasicas(Datos, degree + 1, degree + 2);
        
        System.out.println("Matriz X:");
        Ecuaciones.ImprimirMatriz(matrizX);
        System.out.println("Matriz Resuelta por Gauss Jordan: ");
        Ecuaciones.GussJordan(matrizX);
        Ecuaciones.ImprimirMatriz(matrizX);

        double[] coefficients = new double[degree + 1];
        for (int i = 0; i <= degree; i++) {
            coefficients[i] = matrizX[i][degree + 1];
            System.out.println("B" + i + ": " + coefficients[i]);
        }
        return coefficients;
    }

    public static double mejorR2(double R2Total, double R2Set1, double R2Set2) {
        return Math.max(R2Total, Math.max(R2Set1, R2Set2));
    }
}
