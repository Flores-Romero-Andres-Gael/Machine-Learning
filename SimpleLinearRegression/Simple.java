package SimpleLinearRegression;
// Flores Romero Andres Gael
// SimpleLinearRegression.Simple Linear Regression

import DataSets.DatosVariables;
import Correlation.Correlacion;
import Coefficient_of_Determination.COD;
import java.util.ArrayList;

public class Simple {

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

        /*--------------Valores Predicciones--------------*/
        int[] x = {102, 97, 120, 115, 100}; 

        System.out.println("\n--------Todo el DataSet:--------");
        double[] coefficients = OPERATIONS(ALLDatos);
        double B0 = coefficients[0]; 
        double B1 = coefficients[1];
        COD.COError(ALLDatos, new double[]{B0, B1});
        double CorrelacionTotal = Correlacion.COR(ALLDatos, coefficients); 
        System.out.println("Correlacion (R): " + CorrelacionTotal);
        double R2Total = COD.R2(ALLDatos, new double[]{B0, B1});
        System.out.println("Coeficiente de Determinacion (R^2): " + R2Total);
        System.out.println("\n--------Simulaciones:--------");
        Simulaciones(B0, B1, x);
       

        System.out.println("\n--------Primeros 70% - 30%:--------");
        double[] coefficients2 = OPERATIONS(Datos);
        double B02 = coefficients2[0]; 
        double B12 = coefficients2[1];
        COD.COError(Restante, new double[]{B02, B12});
        double Correlacion1 = Correlacion.COR(Datos, coefficients2);
        System.out.println("Correlacion (R): " + Correlacion1);
        double R2Set = COD.R2(Restante, new double[]{B02, B12});
        System.out.println("Coeficiente de Determinacion (R^2): " + R2Set);
        System.out.println("\n--------Simulaciones:--------");
        Simulaciones(B02, B12, x);
        
        System.out.println("\n--------Segundos 30% - 70%:--------");
        double[] coefficients3 = OPERATIONS(Datos2);
        double B03 = coefficients3[0];
        double B13 = coefficients3[1];
        COD.COError(Restante2, new double[]{B03, B13});
        double Correlacion2 = Correlacion.COR(Datos2, coefficients3);
        System.out.println("Correlacion (R): " + Correlacion2);
        double R2Set2 = COD.R2(Restante2, new double[]{B03, B13});
        System.out.println("Coeficiente de Determinacion (R^2): " + R2Set2); 
        System.out.println("\n--------Simulaciones:--------");
        Simulaciones(B03, B13, x);

        double mejorR2 = mejorR2(R2Total, R2Set, R2Set2);
        System.out.println("\n--------Mejor Coeficiente de Determinacion (R^2): " + mejorR2 + "--------\n");
    
    }

    public static void Simulaciones(double B0, double B1, int[] valoresX) {
        System.out.println("Ecuacion:\ny = " + B0 + " + " + B1 + "(x)");
        for (int x : valoresX) {
            double simulacion = B0 + B1 * x;
            System.out.println("X = " + x + " , Y: " + simulacion);
        }
    }

    public static double[] OPERATIONS(ArrayList<DatosVariables> Datos){

        double sumadorx = 0, sumadory = 0, multxy = 0, multx = 0, B0 = 0, B1 = 0, totalmultxy = 0 , totalmultx = 0;

        for (DatosVariables p : Datos){

            //Calcular los datos de la ecuacion
            sumadorx += p.x;
            sumadory += p.y;
            multxy = p.x * p.y;
            multx = p.x * p.x;
            totalmultxy += multxy;
            totalmultx += multx;
        }

        //Calular Pendiente y Intercepto
        System.out.println("Formula Pendiente (B1): ("+totalmultxy+"-(("+sumadorx+"*"+sumadory+")/"+Datos.size()+"))/(("+totalmultx+")-("+sumadorx+"*"+sumadorx+")/"+Datos.size()+")");
        B1 = (totalmultxy - ((sumadorx * sumadory)/ Datos.size())) / ((totalmultx) - (sumadorx*sumadorx)/ Datos.size());
        System.out.println("B1: " + B1);
        System.out.println("Forumla Intercepto (B0): (("+sumadory+")/"+Datos.size()+")-("+B1+"*("+sumadorx+"/"+Datos.size()+"))");
        B0 = ((sumadory)/ Datos.size())-(B1 * (sumadorx/ Datos.size()));
        System.out.println("B0: " + B0 + "\n");

        return new double[]{B0, B1};
    }

    public static double mejorR2(double R2Total, double R2Set1, double R2Set2) {
        return Math.max(R2Total, Math.max(R2Set1, R2Set2));
    }

}