/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacion;

/**
 *
 * @author kevin_000
 * 
 * Link: http://www.cs.wm.edu/~esmirni/Teaching/cs526/section1.2.pdf
 * 
 */
public class Simulacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /********
        The arrival time is ai
        The delay in the queue is di
        The time that service begins is bi = ai + di
        The service time is si
        The wait in the node is wi = di + si
        The departure time is ci = ai + wi
        *********/
        
        int i = 0;

        int[] a = {0, 15, 47, 71, 111, 123, 152, 166, 226, 310, 320}; // Tiempos de llegada //
        int[] s = {0, 43, 36, 34, 30, 38, 40, 31, 29, 36, 30};// Tiempos de servicio //
        int[] c = new int[a.length]; // Almacena los tiempos de partida //
        int[] d = new int[a.length]; // Almacena los tiempos de espera //

        while (i < a.length - 1) {
            i++;

            if (a[i] < c[i - 1]) {

                d[i] = c[i - 1] - a[i]; //<-- Calcula el tiempo de espera.

            } else {

                d[i] = 0;
            }

            c[i] = a[i] + d[i] + s[i]; //<-- Calcula el tiempo de partida.

        }
        for (int n = i; n > 0; n--) {

            System.out.println("punto en " + (n - (i - 1)) + " : " + d[n - (i - 1)]);
            i -= 2;

        }
        double dPlus = 0.0;
        double sPlus = 0.0;
        double aPlus = 0.0;

        for (int j = 0; j < a.length; j++) {

            dPlus += d[j]; //<-- Suma los datos de d y almacena el resultado.
            sPlus += s[j]; //<-- Suma los datos de s y almacena el resultado.

        }
        for (int y = 1; y < a.length; y++) {

            aPlus += a[y] - a[y - 1]; //<-- Calcula el intervalo de trabajo y los suma.

        }

        System.out.println("-----------------------------");

        double n = (i * -1);

        double promedio_tiemp_entre_llegada = aPlus / n;

        double promedio_retraso = dPlus / n; // d-barra.

        double promedio_tiempo_ser = sPlus / n; // S-barra.

        double tasa_llegada = 1 / promedio_tiemp_entre_llegada;

        double tasa_servicio = 1 / promedio_tiempo_ser;

        double w = promedio_retraso + promedio_tiempo_ser; // W-barra.

        double I, Q, X, an, sn, dn, cn;

        an = a[a.length - 1];//<-- El ultimo elemento de a.
        sn = s[s.length - 1];//<-- El ultimo elemento de s.
        dn = d[d.length - 1];//<-- El ultimo elemento de d.

        cn = an + sn + dn; //<-- Es el ultimo tiempo.

        I = (n / cn) * w;
        Q = (n / cn) * promedio_retraso;
        X = (n / cn) * promedio_tiempo_ser;

        System.out.println("-----------------------------");
        System.out.println("Tiempo promedio entre llegadas: " + promedio_tiemp_entre_llegada + " segundos por trabajo.");
        System.out.println("Promedio Tiempo de Servicio: " + promedio_tiempo_ser + " segundos por trabajo.");
        System.out.println("Tasa de llegada: " + String.format("%.3f", (tasa_llegada)) + " trabajos por segundo.");
        System.out.println("Tasa de servicio: " + String.format("%.3f", (tasa_servicio)) + " trabajos por segundo.");
        System.out.println("Promedio de Retraso: " + promedio_retraso);
        System.out.println("Promedio de espera: " + w);
        System.out.println("-----------------------------");

        System.out.println("número promedio de tiempo en el nodo(I): " + String.format("%.3f", (I)));
        System.out.println("número promedio de tiempo en la cola(Q): " + String.format("%.3f", (Q)));
        System.out.println("Número promediado en el tiempo de servicio(X): " + String.format("%.3f", (X)));
        
        
    }
}
