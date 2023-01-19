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

import java.util.List;
import java.util.Objects;

public class Calendario {
    public static void main(String[] args) {

    }

    /**
     * Método para ordenar los equipos en jornadas de liga y crear el calendario
     * @param teams Lista de equipos que juegan la liga
     * @return Lista de jornadas de liga que forman el calendario
     */
    private static List<List<FootballJournery>> generateCalendar(List<Team> teams) {

        return null;
    }
}


/**
 * Clase para representar una jornada de liga. Esta compuesta por dos equipos que se enfrentan entre sí
 *
 */
class FootballJournery {
    private Team team1;
    private Team team2;

    public FootballJournery(Team team1, Team team2) {
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
        FootballJournery journery = (FootballJournery) obj;
        return team1.equals(journery.team1) && team2.equals(journery.team2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team1, team2);
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
}
