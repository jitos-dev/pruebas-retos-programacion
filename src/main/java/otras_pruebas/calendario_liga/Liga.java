package otras_pruebas.calendario_liga;

public class Liga {

    static public class Partido {
        public int local = -1, visitante = -1;
    }

    private static Partido[][] calcularLigaNumEquiposPar(int numEquipos) {
        int numRondas = numEquipos - 1;
        int numPartidosPorRonda = numEquipos / 2;

        Partido[][] rondas = new Partido[numRondas][numPartidosPorRonda];

        //Primero asignamos el equipo local
        for (int i = 0, k = 0; i < numRondas; i ++) {
            for (int j = 0; j < numPartidosPorRonda; j ++) {
                rondas[i][j] = new Partido();

                rondas[i][j].local = k;
                k ++;

                if (k == numRondas)
                    k = 0;
            }
        }

        for (int i = 0; i < numRondas; i ++) {
            if (i % 2 == 0) {
                rondas[i][0].visitante = numEquipos - 1;
            } else {
                rondas[i][0].visitante = rondas[i][0].local;
                rondas[i][0].local = numEquipos - 1;
            }
        }

        int equipoMasAlto = numEquipos - 1;
        int equipoImparMasAlto = equipoMasAlto - 1;

        for (int i = 0, k = equipoImparMasAlto; i < numRondas; i ++) {
            for (int j = 1; j < numPartidosPorRonda; j ++) {
                rondas[i][j].visitante = k;

                k --;

                if (k == -1)
                    k = equipoImparMasAlto;
            }
        }

        return rondas;
    }

    private static Partido[][] calcularLigaNumEquiposImpar(int numEquipos) {
        int numRondas = numEquipos;
        int numPartidosPorRonda = numEquipos / 2;

        Partido[][] rondas = new Partido[numRondas][numPartidosPorRonda];

        for (int i = 0, k = 0; i < numRondas; i ++)
        {
            for (int j = -1; j < numPartidosPorRonda; j ++)
            {
                if (j >= 0)
                {
                    rondas[i][j] = new Partido();

                    rondas[i][j].local = k;
                }

                k ++;

                if (k == numRondas)
                    k = 0;
            }
        }

        int equipoMasAlto = numEquipos - 1;

        for (int i = 0, k = equipoMasAlto; i < numRondas; i ++)
        {
            for (int j = 0; j < numPartidosPorRonda; j ++)
            {
                rondas[i][j].visitante = k;

                k --;

                if (k == -1)
                    k = equipoMasAlto;
            }
        }

        return rondas;
    }

    static public Partido[][] calcularLiga(int numEquipos) {
        if (numEquipos % 2 == 0)
            return calcularLigaNumEquiposPar(numEquipos);
        else
            return calcularLigaNumEquiposImpar(numEquipos);
    }

    static public void mostrarPartidos(Partido[][] rondas) {
        System.out.println("IDA");

        for (int i = 0; i < rondas.length; i ++)
        {
            System.out.print("Ronda " + (i + 1) + ": ");

            for (int j = 0; j < rondas[i].length; j ++)
            {
                System.out.print("   " + (1 + rondas[i][j].local) + "-" + (1 + rondas[i][j].visitante));
            }

            System.out.println();
        }

        System.out.println("VUELTA");

        for (int i = 0; i < rondas.length; i ++)
        {
            System.out.print("Ronda " + (i + 1) + ": ");

            for (int j = 0; j < rondas[i].length; j ++)
            {
                System.out.print("   " + (1 + rondas[i][j].visitante) + "-" + (1 + rondas[i][j].local));
            }

            System.out.println();
        }
    }

    static public void main(String[] args) {
        System.out.println("Liga con 10 equipos:");

        mostrarPartidos(calcularLiga(6));

        System.out.println();

        System.out.println("Liga con 7 equipos:");

        mostrarPartidos(calcularLiga(7));
    }
}
