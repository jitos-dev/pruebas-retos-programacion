package prueba_php_futbol;

/*
* 3.1. Según el .sql adjunto, desarrollar un Script en PHP que se conecte a la base de datos y muestre un
* listado con los nombres de todos los equipos de fútbol y su imagen en caso de tenerla.
* 3.2. Con el listado de equipos del punto anterior, desarrollar un Script en PHP que realice un calendario de fútbol
* con sus respectivas jornadas. Dos equipos no se puedan enfrentar 2 veces entre ellos hasta haber completado
* la primera vuelta. Si el partido anterior se jugó en casa el siguiente se deberá jugar fuera, y viceversa.
* El script debe ser compatible con cualquier número de equipos definidos en la base de datos. Se valorará que
* también sea válido con un número de equipos impar, en ese caso un equipo descansaría cada jornada. Se ofrece
* ejemplo de lo que se debería mostrar por pantalla en un ejemplo con 8 equipos.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Calendario {
    public static void main(String[] args) {
        List<Team> teams = getTeams();
        List<FootballJourney> calendar = generateCalendar(teams);
        printFootballJourneys(calendar);
    }

    public static void printFootballJourneys(List<FootballJourney> journeyList) {
        journeyList.forEach(footballJourney -> {
            System.out.println(footballJourney);
            System.out.println("--------------------------");
        });
    }

    /**
     * Método para ordenar los equipos en jornadas de liga y crear el calendario
     * @param teamsList Lista de equipos que juegan la liga
     * @return Lista de jornadas de liga que forman el calendario
     */
    private static List<FootballJourney> generateCalendar(List<Team> teamsList) {
        /*Estos son los equipos de la primera jornada. Desordenamos la lista para que sea
        * más real y aleatorio el sorteo*/
        List<Team> teamsUnordered = new ArrayList<>(teamsList);
        Collections.shuffle(teamsUnordered); //shuffle desordena los valores del ArrayList

        /*La lógica de esta ordenación es ir desplazando en uno la posición de los equipos en el array. Con
        * esto conseguimos que se vaya cambiando y la jornada que juega en casa luego la siguiente juega fuera.
        * Para esto utilizamos rotate donde pasamos el array y -1 para que desplace en 1 cada posición. Si le pasamo
        * -2 pues desplaza dos posiciones y así sucesivamente. Podemos ver un ejemplo en:
        * https://www.flipandroid.com/cmo-mover-el-primer-elemento-del-arraylist-a-la-ltima-posicin.html*/
        List<Team> allTeams = new ArrayList<>();
        for (int i = 0; i < (teamsList.size() / 2) ; i++) {
            List<Team> teams = new ArrayList<>(teamsUnordered);
            //Le vamos cambiando el valor con la i
            Collections.rotate(teams, -(i + 1));
            allTeams.addAll(teams);
        }

        //Ahora ya podemos crear las jornadas y añadirlas al calendario
        //Lista para guardar las jornadas de futbol
        List<FootballJourney> calendar = new ArrayList<>();

        FootballJourney journey = null;
        for (int i = 0; i < allTeams.size(); i++) {
            //Si es par lo metemos como Local y si no pues como visitante
            if (i % 2 == 0) {
                journey = new FootballJourney();
                journey.setTeam1(allTeams.get(i));
            } else {
                journey.setTeam2(allTeams.get(i));
                calendar.add(journey);
            }
        }

        //TODO si son impares hay que quitar uno que descansa cada jornada
        //TODO tiene que ser compatible con cualquier número de equipos
        //TODO hay que hacer la segunda vuelta. Le tenemos que dar la vuelta a todos los FootballJourney
        return calendar;
    }

    public static List<Team> getTeams() {
//        return List.of(new Team("Real Madrid"), new Team("FC Barcelona"), new Team("Real Betis"), new Team("Sevilla FC"),
//                new Team("Atlético de Madrid"), new Team("Real Jaén"), new Team("Sabiote FC"));
        return List.of(new Team("Real Madrid"), new Team("FC Barcelona"), new Team("Real Betis"),
                new Team("Atlético de Madrid"), new Team("Real Jaén"), new Team("Sabiote FC"));
    }
}



/**
 * Clase para representar una jornada de liga. Esta compuesta por dos equipos que se enfrentan entre sí
 *
 */
class FootballJourney {
    private Team team1;
    private Team team2;

    public FootballJourney() {}

    public FootballJourney(Team team1) {
        this.team1 = team1;
    }

    public FootballJourney(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FootballJourney journery = (FootballJourney) obj;
        return team1.equals(journery.team1) && team2.equals(journery.team2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team1, team2);
    }

    @Override
    public String toString(){
        return "Local: " + this.team1 + "\nVisitante: " + this.team2;
    }
}


/**
 * Clase para representar los equipos del calendario
 */
class Team {
    private String name;
    private String imageURL;

    public Team(){
        this.name = "";
        this.imageURL = "";
    }

    public Team(String name){
        this.name = name;
        this.imageURL = "";
    }

    public Team(String name, String imageURL){
        this.name = name;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Team team = (Team) obj;
        return name.equals(team.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString(){
        return this.name;
    }
}