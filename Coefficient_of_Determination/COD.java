package Coefficient_of_Determination;

import DataSets.DatosVariables;
import java.util.ArrayList;

public class COD {
    
    public static double COError(ArrayList<DatosVariables> Restante, double[] coefficients) {
        double Error = 0;
    
        for (DatosVariables p : Restante) {
            double predictedY = 0;
            for (int i = 0; i < coefficients.length; i++) {
                predictedY += coefficients[i] * Math.pow(p.x, i);
            }
    
            Error += Math.pow((p.y - predictedY), 2);
        }
    
        double ErrorPromedio = Error / Restante.size();
        System.out.println("Coeficiente de Error: " + ErrorPromedio);
        
        return ErrorPromedio;
    }

    public static double R2(ArrayList<DatosVariables> Restante, double[] coefficients) {
        double SST = 0;
        double SSE = 0;
        double meanY = 0;
    
        for (DatosVariables p : Restante) {
            meanY += p.y;
        }
        meanY /= Restante.size();
    
        for (DatosVariables p : Restante) {
            double predictedY = 0;
            for (int i = 0; i < coefficients.length; i++) {
                predictedY += coefficients[i] * Math.pow(p.x, i);
            }
    
            SST += Math.pow(p.y - meanY, 2);
            SSE += Math.pow(p.y - predictedY, 2);
        }

        return 1 - (SSE / SST);
    }

}
