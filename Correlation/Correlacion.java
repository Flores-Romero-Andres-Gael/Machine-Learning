package Correlation;

import DataSets.DatosVariables;
import java.util.ArrayList;

public class Correlacion {

    public static double COR(ArrayList<DatosVariables> Datos, double[] coefficients) {
        int n = Datos.size();
        double sumY = 0, sumPredictedY = 0, sumY_PredictedY = 0, sumY2 = 0, sumPredictedY2 = 0;
    
        for (DatosVariables p : Datos) {
            // Calcular el valor predicho para el polinomio
            double predictedY = 0;
            for (int i = 0; i < coefficients.length; i++) {
                predictedY += coefficients[i] * Math.pow(p.x, i);
            }
    
            // Sumar los valores necesarios para la fórmula de correlación
            sumY += p.y;
            sumPredictedY += predictedY;
            sumY_PredictedY += p.y * predictedY;
            sumY2 += p.y * p.y;
            sumPredictedY2 += predictedY * predictedY;
        }
    
        // Numerador y denominador de la fórmula de correlación
        double numerator = (n * sumY_PredictedY) - (sumY * sumPredictedY);
        double denominator = Math.sqrt((n * sumY2 - sumY * sumY) * (n * sumPredictedY2 - sumPredictedY * sumPredictedY));
    
        return (denominator == 0) ? 0 : numerator / denominator;
    }

    



}
