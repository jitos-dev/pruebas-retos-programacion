package otras_pruebas.calendario_liga;

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

/*
* Desarrollar un programa que realice un calendario de fútbol con sus respectivas jornadas. Dos equipos no se podrán
* enfrentar 2 veces entre ellos hasta haber completado la primera vuelta. Si el partido anterior se jugó en casa el
* siguiente se deberá jugar fuera, y viceversa. El programa debe ser compatible con cualquier número de equipos. Se
* valorará que también sea válido con un número de equipos impar, en ese caso un equipo descansaría cada jornada.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class Calendario {
    public static void main(String[] args) {
        List<Team> teams = getTeams();
        List<List<FootballGame>> calendar = generateCalendar(teams);
        printFootballJourneys(calendar);
        List<FootballJourney> j = order(teams);
        int algo = 0;
    }

    public static void printFootballJourneys(List<List<FootballGame>> journeyList1) {
        journeyList1.forEach( journeyList -> {


            System.out.println("--------------------------");
        });
    }

    public static List<FootballJourney> order(List<Team> teams) {
        List<FootballJourney> list = new ArrayList<>();
        FootballJourney footballJourney = new FootballJourney();

        List<Team> teamsList = new ArrayList<>(teams);
        Collections.rotate(teamsList, -1);

        if (list.size() == teamsList.size()) {
            return list;
        }

        FootballGame footballGame = new FootballGame();
        for (Team team: teamsList) {

            //Si no hay equipo local lo añadimos
            if (footballGame.getTeam1() == null) {
                footballGame.setTeam1(team);
                continue;
            }

            //Si no tenemos equipo visitante lo añadimos
            if (footballGame.getTeam2() == null && !footballGame.getTeam1().equals(team)) {
                footballGame.setTeam2(team);
            }

            //Si ninguno de los dos equipos está en la jornada y el partido tampoco está en la lista de jornadas lo guardamos
            if (!footballJourney.getFootballGameList().contains(footballGame) ||
                    !footballJourney.isTeamInJourney(footballGame.getTeam1())
                    && !footballJourney.isTeamInJourney(footballGame.getTeam2())) {

                footballJourney.getFootballGameList().add(footballGame);
                footballGame = new FootballGame(); //para inicializarlo de nuevo

                //si la jornada está completa la añadimos a la lista
                if (footballJourney.getFootballGameList().size() == teamsList.size() / 2) {
                    list.add(footballJourney);
                    footballJourney = new FootballJourney();
                }
            }

            //Si ya tenemos los dos equipos añadidos comprobamos si están en la jornada, si estan en otra jornada, si como visitantes o como local
//            if (footballGame.getTeam2() == null && !footballGame.getTeam1().equals(team)) {
//                footballGame.setTeam2(team);
//
//                //Si esta el partido en la jornada volvemos a llamar a la funcion
//                if (footballJourney.getFootballGameList().contains(footballGame)) {
//                    footballGame.setTeam1(null);
//                    footballGame.setTeam2(null);
//                    Collections.rotate(teams, -1); //Cambiamos el orden para que no sea siempre el mismo
//                    order(teams);
//                }
//
//                //Si alguno de los equipos ya estan asignados en esta jornada no pueden jugar volvemos a llamar a la función
//                footballJourney.getFootballGameList().forEach(game -> {
//                    //Si el equipo ya esta en la jornada como local o visitante volvemos a llamar a la función
//                    if (footballGame.getTeam1() == game.getTeam1() || footballGame.getTeam1() == game.getTeam2()){
//                        Collections.rotate(teams, -1); //Cambiamos el orden para que no sea siempre el mismo
//                        order(teams);
//                    }
//
//                    //Si el equipo ya esta en la jornada como local o visitante volvemos a llamar a la función
//                    if (footballGame.getTeam2() == game.getTeam1() || footballGame.getTeam1() == game.getTeam2()){
//                        Collections.rotate(teams, -1); //Cambiamos el orden para que no sea siempre el mismo
//                        order(teams);
//                    }
//                });
//
//                //aquí llega si esta correcto y sin ningún fallo de salida
//                footballJourney.getFootballGameList().add(footballGame);
//            }

        }

        //Collections.rotate(teamsList, -1); //Cambiamos el orden para que no sea siempre el mismo
        order(teamsList);

        return list;
    }

    //todo Hay que ordenar los partidos por jornadas. Para este ejemplo tenemos 6 equipos y cada jornada tiene 3 partidos
    public List<FootballGame> orderFootballJourneys(List<FootballGame> footballJourneys) {
        List<FootballGame> list = new ArrayList<>();


        return null;
    }

    /**
     * Método para ordenar los equipos en jornadas de liga y crear el calendario
     * @param teamsList Lista de equipos que juegan la liga
     * @return Lista de jornadas de liga que forman el calendario
     */
    private static List<List<FootballGame>> generateCalendar(List<Team> teamsList) {
        //Estos son los equipos. Desordenamos la lista para que sea más real y aleatorio el sorteo
        List<Team> teamsUnordered = new ArrayList<>(teamsList);
        Collections.shuffle(teamsUnordered); //shuffle desordena los valores del ArrayList

        //En esta lista vamos a guardar todos los partidos de la 1ª y 2ª vuelta que luego tenemos que ordenar
        List<FootballGame> allFootballGames = new ArrayList<>();

        for (int i = 0; i < teamsUnordered.size(); i++) {
            //Vamos moviendo en 1 la posición de los equipos para que pase el siguiente
            Collections.rotate(teamsUnordered, -1);

            //Aquí cogemos el equipo que vamos a enfrentar con el resto. Cojo el 0 porque siempre va a ser el siguiente con rotate
            Team team = teamsUnordered.get(0);
            for (int j = 0; j < teamsUnordered.size() -1 ; j++) {
                FootballGame footballGame = new FootballGame();
                footballGame.setTeam1(team);
                footballGame.setTeam2(teamsUnordered.get(j + 1));

                //Jornada a la inversa para la siguiente vuelta
                FootballGame inverseGame = new FootballGame(footballGame.getTeam2(), footballGame.getTeam1());

                if (!allFootballGames.contains(footballGame))
                    allFootballGames.add(footballGame);

                if (!allFootballGames.contains(inverseGame))
                    allFootballGames.add(inverseGame);

            }
        }

        //TODO pasar esta parte a un método aparte
        List<FootballGame> firstRound = new ArrayList<>();
        List<FootballGame> secondRound = new ArrayList<>();

        for (int i = 0; i < allFootballGames.size(); i++) {
            //Para añadirlo tenemos que controlar que no esté el partido
            //Que si se ha enfrentado a él como local no puede tampoco como visitante en la misma jornada ni en la misma vuelta

            //Los dos partidos, el de local y el de visitante
            FootballGame footballGame = allFootballGames.get(i);
            FootballGame inverseGame = new FootballGame(footballGame.getTeam2(), footballGame.getTeam1());

            //Si no está el partido que juega como local ni el que juega de visitante en la lista
            if (!firstRound.contains(footballGame) && !firstRound.contains(inverseGame)) {

                //hay que controlar el footballGame para ver el si por ejemplo el equipo1 (local) si jugo de local ahora lo tiene que hacer de visitante
                Team team1 = footballGame.getTeam1();

                //Lista con las jornadas donde juega el equipo para obtener la última que jugó
                List<FootballGame> lastFootballGameList = firstRound.stream().filter( game ->
                            game.getTeam1().getName().equals(team1.getName()) ||
                            game.getTeam2().getName().equals(team1.getName()))
                        .collect(Collectors.toList());

                //último partido que jugó el equipo
                FootballGame lastFootballGame = lastFootballGameList.isEmpty() ? new FootballGame() : lastFootballGameList.get(lastFootballGameList.size() -1);

                //Si el último partido jugo de local esta juega de visitante
                if (lastFootballGame.getTeam1() != null && lastFootballGame.getTeam1().equals(team1)) {
                    firstRound.add(inverseGame);
                    secondRound.add(footballGame);
                } else {
                    firstRound.add(footballGame);
                    secondRound.add(inverseGame);
                }
            }
        }

        //Necesitamos el número de equipos para ver cuantos partidos tenemos por jornada. Lo divido entre 2 para el total de partidos de la jornada
        List<FootballJourney> journeyList = new ArrayList<>();

        //El primer bucle es para crear las jornadas y el segundo para ir añadiendo los equipos los partidos
        for (int i = 0; i < teamsList.size() / 2; i++) {
            //Jornada de liga
            FootballJourney journey = new FootballJourney();

            for (FootballGame game : firstRound) {
                //Lista de partidos de la jornada que al entrar estará vacia
                List<FootballGame> footballGameList = journey.getFootballGameList();

                //Partido que obtenemos de la lista de la primera vuelta
                FootballGame footballGame = game;

                //Si en la lista de partidos de la jornada no está el partido hay que comprobar que tampoco este ninguno de los dos equipos
                if (!footballGameList.contains(game) && !journey.isTeamInJourney(game.getTeam1()) && !journey.isTeamInJourney(game.getTeam2())) {

                    //Añadimos el partido a la jornada
                    footballGameList.add(game);

//                    AtomicBoolean inFootballGame = new AtomicBoolean(false);
//
//                    //recoremos la lista de partidos de la jornada para ver si está el partido o alguno de los equipos ya ha jugado
//                    footballGameList.forEach(gameJourney -> {
//                        if (footballGame.getTeam1().equals(gameJourney.getTeam1()) && footballGame.getTeam2().equals(gameJourney.getTeam2())) {
//                            inFootballGame.set(true);
//                        }
//                    });

                    //Si la variable inFootballGame sigue en false es que no esta el partido, ni ha jugado ningún equipo y entoces lo añadimos

                }

                //Si ya se han completado los partidos que no siga con este bucle y pase a la siguiente
                if (footballGameList.size() == teamsList.size() / 2)
                    break;

            }

            //Eliminamos los partidos que ya hemos añadido al calendario para que no se repitan
            journey.getFootballGameList().forEach(firstRound::remove);

            journeyList.add(journey);
        }

        //Ahora ya podemos crear las jornadas y añadirlas al calendario
        //Lista para guardar las jornadas de futbol
        List<List<FootballGame>> calendar = new ArrayList<>();
        calendar.add(firstRound);
        calendar.add(secondRound);

//        for (int i = 0; i < teamsUnordered.size(); i++) {
//            //Vamos moviendo en 1 la posición de los equipos para que pase el siguiente
//            Collections.rotate(teamsUnordered, -1);
//            //Aquí cogemos el equipo que vamos a enfrentar con el resto. Cojo el 0 porque siempre va a ser el siguiente con rotate
//            Team team = teamsUnordered.get(0);
//            for (int j = 0; j < teamsUnordered.size() -1 ; j++) {
//                FootballJourney journey = new FootballJourney();
//                journey.setTeam1(team);
//                journey.setTeam2(teamsUnordered.get(j + 1));
//
//                //Jornada a la inversa para la siguiente vuelta
//                FootballJourney inverseJourney = new FootballJourney(journey.getTeam2(), journey.getTeam1());
//
//                if (!allFootballGames.contains(journey))
//                    allFootballGames.add(journey);
//
//                if (!allFootballGames.contains(inverseJourney))
//                    allFootballGames.add(inverseJourney);
//
//            }
//        }

        /*La lógica de esta ordenación es ir desplazando en uno la posición de los equipos en el array. Con
        * esto conseguimos que se vaya cambiando y la jornada que juega en casa luego la siguiente juega fuera.
        * Para esto utilizamos rotate donde pasamos el array y -1 para que desplace en 1 cada posición. Si le pasamo
        * -2 pues desplaza dos posiciones y así sucesivamente. Podemos ver un ejemplo en:
        * https://www.flipandroid.com/cmo-mover-el-primer-elemento-del-arraylist-a-la-ltima-posicin.html*/
        List<Team> allTeams = new ArrayList<>();
//        for (int i = 0; i < (teamsList.size() / 2) ; i++) {
//            List<Team> teams = new ArrayList<>(teamsUnordered);
//            //Le vamos cambiando el valor con la i
//            Collections.rotate(teams, -(i + 1));
//            allTeams.addAll(teams);
//        }
//
//
//        FootballJourney journey = null;
//        for (int i = 0; i < allTeams.size() -1; i++) {
//            //Si es par lo metemos como Local y si no pues como visitante
//            if (i % 2 == 0) {
//                journey = new FootballJourney();
//                journey.setTeam1(allTeams.get(i));
//            } else {
//                journey.setTeam2(allTeams.get(i));
//                //calendar.add(journey);
//            }
//        }

        //TODO si son impares hay que quitar uno que descansa cada jornada
        //TODO tiene que ser compatible con cualquier número de equipos
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
 * Clase para representar una jornada de futbol. Cada jornada tiene una lista de partidos de futbol
 */
class FootballJourney {
    private List<FootballGame> footballGameList;

    public FootballJourney() {
        footballGameList = new ArrayList<>();
    }

    public FootballJourney(List<FootballGame> footballGameList) {
        this.footballGameList = footballGameList;
    }

    public boolean isTeamInJourney(Team team) {

        AtomicBoolean inJourney = new AtomicBoolean(false);
        footballGameList.forEach(game -> {
            if (game.getTeam1().equals(team))
                inJourney.set(true);

            if (game.getTeam2().equals(team))
                inJourney.set(true);
        });

        return inJourney.get();
    }

    public List<FootballGame> getFootballGameList() {
        return footballGameList;
    }

    public void setFootballGameList(List<FootballGame> footballGameList) {
        this.footballGameList = footballGameList;
    }
}

/**
 * Clase para representar un partido de liga. Está compuesta por dos equipos que se enfrentan entre sí
 *
 */
class FootballGame {
    private Team team1;
    private Team team2;

    public FootballGame() {}

    public FootballGame(Team team1) {
        this.team1 = team1;
    }

    public FootballGame(Team team1, Team team2) {
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
        FootballGame game = (FootballGame) obj;
        return team1.equals(game.team1) && team2.equals(game.team2);
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
