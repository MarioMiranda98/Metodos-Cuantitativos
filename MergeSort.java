/*
    Clase donde se realiza el ordenamiento por mezcla.
    Este al ser divide y venceras tiene un complejidad
    O(nlog(n)) en los tres casos, la cual es buena para 
    ordenar en poco tiempo las poblaciones que mandemos
*/
public class MergeSort {
    public MergeSort() { }

    protected void ordenamiento(Integrante[] integrantes, int izquierda, int derecha) {
        if(izquierda < derecha) {
            int mitad = (izquierda + derecha) / 2;

            ordenamiento(integrantes, izquierda, mitad);
            ordenamiento(integrantes, (mitad + 1), derecha);

            mezcla(integrantes, izquierda, mitad, derecha);
        }
    }

    private void mezcla(Integrante[] integrantes, int izquierda, int mitad, int derecha) {
        int n1 = mitad - izquierda + 1;
        int n2 = derecha - mitad;

        double l[] = new double[n1];
        double r[] = new double[n2];

        for(int i = 0; i < n1; i++)
            l[i] = integrantes[izquierda + i].getFitness();

        for(int i = 0; i < n2; i++)
            r[i] = integrantes[mitad + 1 + i].getFitness();

        int i = 0;
        int j = 0;
        int k = izquierda;

        while(i < n1 && j < n2) {
            if(l[i] <= r[j]) {
                integrantes[k].setFitness(l[i]);
                i++;
            } else {
                integrantes[k].setFitness(r[j]);
                j++;
            }

            k++;
        }

        while(i < n1) {
            integrantes[k].setFitness(l[i]);
            i++;
            k++;
        }

        while(j < n2) {
            integrantes[k].setFitness(r[j]);
            j++;
            k++;
        }
    }
}