/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.dal.sql;

import hr.movieTheater.customsExceptions.InvalidUserTypeException;
import hr.movieTheater.dal.Repository;
import hr.movieTheater.dal.sql.DataSourceSingleton;
import hr.movieTheater.dal.sql.model.GenreFromDataBase;
import hr.movieTheater.dal.sql.model.MovieFromDataBase;
import hr.movieTheater.dal.sql.model.MovieGenreFromDataBase;
import hr.movieTheater.dal.sql.model.MoviePersonFromDataBase;
import hr.movieTheater.dal.sql.model.PersonDataFromDataBase;
import hr.movieTheater.model.Actor;
import hr.movieTheater.model.Director;
import hr.movieTheater.model.Genre;
import hr.movieTheater.model.Movie;
import hr.movieTheater.model.Person;
import hr.movieTheater.model.User;
import hr.movieTheater.model.UserType;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.sql.DataSource;


public class SQLRepository implements Repository {

    //----------Movie FromDataBaseCol----------
    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE = "Title";
    private static final String ORIGINAL_TITLE = "OriginalTitle";
    private static final String DESCRIPTION = "Description";
    private static final String DURATION = "Duration";
    private static final String PUBLISHED_DATE = "PublishedDate";
    private static final String START_DATE = "Startdate";
    private static final String PICTURE_PATH = "PicturePath";
  
    //----------MoviePerson FromDataBaseCol----------
    private static final String ID_MOVIE_PERSON = "IDMoviePerson";
    private static final String PERSON_DATA_ID = "PersonDataID";
    private static final String MOVIE_ID = "MovieID";
    private static final String MOVIE_ROLE_ID = "MovieRoleID";
    
    //----------PersonData FromDataBaseCol----------
    private static final String ID_PERSON_DATA = "IDPersonData";
    private static final String FIRST_NAME = "FirstName";
    private static final String MIDDLE_NAME = "MiddleName";
    private static final String LAST_NAME = "LastName";
    
    //----------MovieRole FromDataBaseCol----------
    private static final String ID_MOVIE_ROLE = "IDMovieRole";
    private static final String NAME = "Name";
    
    //----------MovieGenre FromDataBaseCol----------
    private static final String ID_MOVIE_GENRE = "IDMovieGenre";
    private static final String GENRE_ID = "GenreID";
    //private static final String MOVIE_ID = "MovieID"; -> already exsist line 45
    
    //----------Genre FromDataBaseCol----------
    private static final String ID_GENRE = "IDGenre";
    //private static final String NAME = "Name"; -> already exsist line 55
    
    
    //----------ClientUser FromDataBaseCol----------
    //private static final String PERSON_DATA_ID = "PersonDataId"; -> already exsist line 50
    private static final String ID_USER = "IDClientUser";
    private static final String USER_ROLE_ID = "UserRoleID";
    private static final String EMAIL = "Email"; 
    private static final String PASWORD = "Password";
    private static final String ADD_DATE = "AddDate";
    //----------Pricedures----------
    //----------Get Procedures------
    private static final String GET_MOVIE = "{ CALL getMovie (?)}";
    private static final String GET_MOVIES = "{ CALL getMovies }";
    //private static final String GET_GENRE = "{ CALL getGenre (?)}";
    private static final String GET_GENRES = "{ CALL getGenres }";
    //private static final String GET_MOVIE_GENRE = "{ CALL getMovieGenre (?)}";
    private static final String GET_MOVIE_GENRES = "{ CALL getMovieGenres }";
    //private static final String GET_MOVIE_ROLE = "{ CALL getMovieRole (?)}";
    //private static final String GET_MOVIE_ROLES = "{ CALL getMovieRoles }";
    //private static final String GET_MOVIE_PERSON_FILTERED_BY_MOVIE = "{ CALL getMoviePersonFilteredByMovie (?)}";
    private static final String GET_MOVIE_PERSON_FILTERED_BY_PERSON_DATA = "{ CALL getMoviePersonFilteredByPersonData (?)}";
    private static final String GET_MOVIE_PERSONS = "{ CALL getMoviePersons }";
    private static final String GET_PERSON_DATA = "{ CALL getPersonData (?)}";
    private static final String GET_PERSONS_DATA = "{ CALL getPersonsData }";
    private static final String GET_USER = "{ CALL getClientUser (?) }";
    private static final String GET_USERS = "{ CALL getClientUsers }";
    
    //----------Create Procedures------
    private static final String CREATE_MOVIE = "{ CALL createMovie (?, ?, ?, ?, ?, ?, ?, ?)}";
    private static final String CREATE_GENRE = "{ CALL createGenre (?, ?)}";
    private static final String CREATE_MOVIE_GENRE = "{ CALL createMovieGenre (?, ?, ?)}";
    private static final String CREATE_MOVIE_PERSON = "{ CALL createMoviePerson (?, ?, ?, ?)}";
    //private static final String CREATE_MOVIE_ROLE = "{ CALL createMovieRole (?, ?)}";
    private static final String CREATE_PERSON_DATA = "{ CALL createPersonData (?, ?, ?, ?)}";
    private static final String CREATE_USER = "{ CALL createClientUser (?, ?, ?, ?, ?, ?)}";
    
    //----------Upadte Procedures------
    private static final String UPDATE_MOVIE = "{ CALL updateMovie (?, ?, ?, ?, ?, ?, ?, ?)}";
//    private static final String UPDATE_GENRE = "{ CALL updateGenre (?, ?)}";
//    private static final String UPDATE_MOVIE_GENRE = "{ CALL updateMovieGenre (?, ?, ?)}";
//    private static final String UPDATE_MOVIE_PERSON = "{ CALL updateMoviePerson (?, ?, ?, ?)}";
//    private static final String UPDATE_MOVIE_ROLE = "{ CALL updateMovieRole (?, ?)}";
    private static final String UPDATE_PERSON_DATA = "{ CALL updatePersonData (?, ?, ?, ?)}";
    private static final String UPDATE_USER = "{ CALL updateClientUser (?, ?, ?, ?, ?)}";
   
    //----------Delete Procedures------
    private static final String DELETE_MOVIE_GENRE = "{ CALL deleteMovieGenre ( ? ) }";
    private static final String DELETE_MOVIE_PERSON = "{ CALL deleteMoviePersonFilteredByMovie ( ? ) }";
    private static final String DELETE_MOVIE_PERSON_FILTERED_BY_PERSON_ID = "{ CALL deleteMoviePersonFilteredByPersonData ( ? ) }";
    private static final String DELETE_MOVIE_DATA = "{ CALL deleteMovieData }";
    private static final String DELETE_MOVIE = "{ CALL deleteMovie ( ? ) }";
    private static final String DELETE_PERSON_DATA = "{ CALL deletePersonData ( ? ) }";
    
    
    //----------Special Procedures------
    private static final String WHAT_ROLE_PEROSN_HAS = "{ CALL whatRoleIsPerson ( ?, ? ) }";
    
    
    
    
    
    
    @Override
    public void createMovie(Movie movie) throws SQLException {
        
        int idMovie = createMovieInDataBase(movie);
        createMovieGenreInDataBase(movie, idMovie);
        createMoviePersonInDataBase(movie, idMovie);
         
    }

    @Override
    public void createMovies(List<Movie> movies) throws SQLException {
        int idMovie;
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE))
        {
            
            for (Movie movie : movies) {
                stmt.setString(1, movie.getTitle());
                stmt.setString(2, movie.getOriginalTitle());
                stmt.setString(3, movie.getDescription());
                stmt.setInt(4, movie.getDuration());
                stmt.setString(5, movie.getPublishedDate().format(Movie.DATE_FORMAT));
                stmt.setString(6, movie.getStartDate().format(Movie.DATE_FORMAT));
                stmt.setString(7, movie.getPicturePath());
                stmt.registerOutParameter(8, Types.INTEGER);

                stmt.executeUpdate();
                idMovie = stmt.getInt(8);
                
                createMovieGenreInDataBase(movie, idMovie);
                createMoviePersonInDataBase(movie, idMovie);
            }
        }
        
    }

    @Override
    public Optional<Movie> getMovie(int id) throws SQLException {
        Optional<Movie> movie;
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_MOVIE) ) 
        {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    movie = Optional.of(new Movie(
                            rs.getInt(ID_MOVIE), 
                            rs.getString(TITLE), 
                            rs.getString(ORIGINAL_TITLE), 
                            rs.getString(DESCRIPTION),
                            LocalDate.parse(rs.getString(PUBLISHED_DATE), Movie.DATE_FORMAT), 
                            LocalDate.parse(rs.getString(START_DATE), Movie.DATE_FORMAT), 
                            rs.getInt(DURATION), 
                            rs.getString(PICTURE_PATH), 
                            createGenreInAppForMovie(id), 
                            createActorsInAppForMovie(id), 
                            createDirectorsInAppForMovie(id)
                    ));
                }
                else{
                    movie = Optional.empty();
                }
            } 
        }
        return movie;
    }

    @Override
    public List<Movie> getMovies(boolean allData) throws SQLException {
        List<Movie> movies = new ArrayList<>();
        List<MovieFromDataBase> moviesFromDataBase = getMoviesFromDataBase();
        if (allData) {
            for (MovieFromDataBase m : moviesFromDataBase) {
                movies.add(new Movie(
                    m.getIdMovie(), 
                    m.getTitle(), 
                    m.getOriginalTitle(), 
                    m.getDescription(), 
                    m.getPublisheddate(), 
                    m.getStartDate(), 
                    m.getDuration(),  
                    m.getPicturePath(), 
                    createGenreInAppForMovie(m.getIdMovie()),
                    createActorsInAppForMovie(m.getIdMovie()), 
                    createDirectorsInAppForMovie(m.getIdMovie())
                ));
            }
        }
        else{
            for (MovieFromDataBase m : moviesFromDataBase) {
                movies.add(new Movie(
                    m.getIdMovie(), 
                    m.getTitle(), 
                    m.getOriginalTitle(), 
                    m.getDescription(), 
                    m.getPublisheddate(), 
                    m.getStartDate(), 
                    m.getDuration(),  
                    m.getPicturePath(), 
                    null, 
                    null, 
                    null
                ));
            }
        }
        return movies;
    }
    
    @Override
    public List<Movie> getMoviesFilteredByPerson(Person person) throws SQLException {
        List<Movie> movies = new ArrayList<>();
        Optional<Movie> movie;
        int id = person.getId();
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_MOVIE_PERSON_FILTERED_BY_PERSON_DATA) ) 
        {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                
                while (rs.next()) 
                {
                    movie = getMovie(rs.getInt(MOVIE_ID));
                    movies.add(movie.get());
                }            
            } 
        }
        return movies;
    }
    
    @Override
    public void updateMovie(Movie movie) throws SQLException {
        updateMovieInDataBase(movie);
        updateMovieGenreInDataBase(movie);
        updateMoviePersonInDataBase(movie);
        
    }
    
    @Override
    public void deleteMovie(int id) throws SQLException {

        deleteMoviPersonInDataBaseForMovieId(id);
        deleteMoviGenreInDataBaseForMovieId(id);
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE) ) 
        {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } 
    }
    
    @Override
    public List<Person> getPersons() throws SQLException {
        List<Person> persons = new ArrayList<>();
        List<PersonDataFromDataBase> personsFromDataBase = getPersonDataFromDataBase();
        
        personsFromDataBase.forEach(p -> persons.add(new Person(
                p.getIdPersonData(), 
                p.getFirstName(), 
                p.getMiddleName(), 
                p.getLastName()
        )));
    
        return persons;
    }
    
    @Override
    public Optional<Person> getPerson(int id) throws SQLException {
        Optional<Person> person;
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_PERSON_DATA))
        {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    person = Optional.of( new Person(
                        rs.getInt(ID_PERSON_DATA), 
                        rs.getString(FIRST_NAME), 
                        rs.getString(MIDDLE_NAME), 
                        rs.getString(LAST_NAME)
                    ));
                }
                else{
                    person = Optional.empty();
                }
            }
        }
        return person;
    }
    
    @Override
    public int createPerson(Person person) throws SQLException {
        int idPerson =  createPersonDataInDataBase(person);
        return idPerson;
    }
    
    @Override
    public void updatePerson(Person person) throws SQLException {
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_PERSON_DATA))
        {
            stmt.setInt(1, person.getId());
            stmt.setString(2, person.getFirstName());
            stmt.setString(3, person.getMiddleName());
            stmt.setString(4, person.getLastName());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void deletePerson(int id) throws SQLException {
        deleteMoviPersonInDataBaseForPersonId(id);
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_PERSON_DATA) ) 
        {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } 
        
    }
    
    @Override
    public Optional<Integer> whatRolePersonHas(int idPerson) throws SQLException {
//      if person is actor, value is 1
//	if person is director, value is -1
//	if person is actor and director, value is 0
//      if person is not actor or director, value is 123
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        Optional<Integer> value;
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(WHAT_ROLE_PEROSN_HAS))
        {
            stmt.setInt(1, idPerson);
            stmt.registerOutParameter(2, Types.INTEGER);
            
            stmt.executeUpdate();
            value = Optional.of(Integer.valueOf(stmt.getInt(2)));
        }
        if (value.get() == Integer.valueOf(123)) {
            value = Optional.empty();
        }
        return value;
    }
    
    @Override
    public List<Genre> getGenres() throws SQLException {
        List<Genre> genres = new ArrayList<>();
        List<GenreFromDataBase> genreFromDataBase = getGenreFromDataBase();
        
        genreFromDataBase.forEach(g -> genres.add(new Genre(
                g.getIdGenre(), 
                g.getName()
        )));
        
        return genres;
    }

    @Override
    public void createGenre(Genre genre) throws SQLException {
        createGenreInDataBase(genre.getName());
    }
    
    @Override
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_USERS);
                ResultSet rs = stmt.executeQuery()) 
        {
                while (rs.next()) {
                    users.add(new User(
                            rs.getInt(ID_USER), 
                            UserType.getUserType(rs.getInt(USER_ROLE_ID)), 
                            rs.getString(EMAIL), 
                            rs.getString(PASWORD), 
                            LocalDate.parse(rs.getString(ADD_DATE), User.DATE_FORMAT)
                    ));
                } 
        }
        
        return users;
    }

    @Override
    public Optional<User> getUser(String email) throws SQLException {
        Optional<User> user = Optional.empty();
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_USER) ) 
        {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    
                    user = Optional.of(new User(
                            getPerson(rs.getInt(PERSON_DATA_ID)), 
                            rs.getInt(ID_USER), 
                            UserType.getUserType(rs.getInt(USER_ROLE_ID)), 
                            rs.getString(EMAIL), 
                            rs.getString(PASWORD), 
                            LocalDate.parse(rs.getString(ADD_DATE), User.DATE_FORMAT)
                    ));
                }
            } 
        }
        return user;
    }

    @Override
    public int createUser(User user) throws SQLException, InvalidUserTypeException {
        int idPersonData = createPerson(new Person(
                user.getId(), 
                user.getFirstName(), 
                user.getMiddleName(), 
                user.getLastName()
        ));
        int idUser;
        
        if (!UserType.getUserTypeId(user.getUserType().get()).isPresent()) {
            throw new InvalidUserTypeException("No user type is given");
        }
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_USER))
        {
                stmt.setInt(1, idPersonData);
                stmt.setInt(2, UserType.getUserTypeId(user.getUserType().get()).get());
                stmt.setString(3, user.getEmail());
                stmt.setString(4, user.getPasword());
                stmt.setString(5, LocalDate.now().format(User.DATE_FORMAT));
                
                stmt.registerOutParameter(6, Types.INTEGER);

                stmt.executeUpdate();
                idUser = stmt.getInt(6);    
        }
        return idUser;
    }

    @Override
    public void updateUser(User user) throws SQLException, InvalidUserTypeException {
        updatePerson(new Person(
                user.getId(), 
                user.getFirstName(), 
                user.getMiddleName(), 
                user.getLastName()
        ));
        
        if (!UserType.getUserTypeId(user.getUserType().get()).isPresent()) {
            throw new InvalidUserTypeException("No user type is given");
        }
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_USER))
        {
                stmt.setInt(1, user.getIdClient());
                stmt.setInt(2, user.getId());
                stmt.setInt(3, UserType.getUserTypeId(user.getUserType().get()).get());
                stmt.setString(4, user.getEmail());
                stmt.setString(5, user.getPasword());
                
                
                stmt.executeUpdate();  
        }
    }

    @Override
    public Optional<User> isPaswordOk(String email, String psw) throws SQLException {
        Optional<User> user = getUser(email);
        
        if (!user.isPresent() || !user.get().getPasword().equals(psw)) {
            user = Optional.empty();
        }
        return user;
//        return Optional.of(new User(10, "josip", "", "Šadek", 10, 1, "Josip.sadek@gmail.com", "psw", LocalDate.now()));
    }
    
    @Override
    public void deleteMovieData() throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(DELETE_MOVIE_DATA))
        {
            stmt.executeUpdate();  
        }
    }
    
    
    //---------------getting data from database
    
    private List<MovieFromDataBase> getMoviesFromDataBase() throws SQLException{
        List<MovieFromDataBase> movies = new ArrayList<>();
        DataSource dataSource =  DataSourceSingleton.getInstance();
        try( Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_MOVIES);
                ResultSet rs = stmt.executeQuery())
        {
            while(rs.next())
            {
                movies.add(new MovieFromDataBase(
                        rs.getInt(ID_MOVIE), 
                        rs.getString(TITLE), 
                        rs.getString(ORIGINAL_TITLE), 
                        rs.getString(DESCRIPTION), 
                        rs.getInt(DURATION), 
                        LocalDate.parse(rs.getString(PUBLISHED_DATE), Movie.DATE_FORMAT), 
                        LocalDate.parse(rs.getString(START_DATE), Movie.DATE_FORMAT),
                        rs.getString(PICTURE_PATH)
                ));
            }
        }
        return movies;
    }
    
    private List<MoviePersonFromDataBase> getMoviePersonsFromDataBase() throws SQLException{
        List<MoviePersonFromDataBase> persons = new ArrayList<>();
        DataSource dataSource =  DataSourceSingleton.getInstance();
        try( Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_MOVIE_PERSONS);
                ResultSet rs = stmt.executeQuery())
        {
            while(rs.next())
            {
                persons.add(new MoviePersonFromDataBase(
                        rs.getInt(ID_MOVIE_PERSON),
                        rs.getInt(PERSON_DATA_ID),
                        rs.getInt(MOVIE_ROLE_ID),
                        rs.getInt(MOVIE_ID)
                ));
            }
        }
        return persons;
    }
    
    private List<PersonDataFromDataBase> getPersonDataFromDataBase() throws SQLException{
        List<PersonDataFromDataBase> personData = new ArrayList<>();
        DataSource dataSource =  DataSourceSingleton.getInstance();
        try( Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_PERSONS_DATA);
                ResultSet rs = stmt.executeQuery())
        {
            while(rs.next())
            {
                personData.add(new PersonDataFromDataBase(
                        rs.getInt(ID_PERSON_DATA),
                        rs.getString(FIRST_NAME),
                        rs.getString(MIDDLE_NAME),
                        rs.getString(LAST_NAME)
                ));
            }
        }
        return personData;
    }  
    
    private List<MovieGenreFromDataBase> getMovieGenreFromDataBase() throws SQLException{
        List<MovieGenreFromDataBase> movieGenre = new ArrayList<>();
        DataSource dataSource =  DataSourceSingleton.getInstance();
        try( Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_MOVIE_GENRES);
                ResultSet rs = stmt.executeQuery())
        {
            while(rs.next())
            {
                movieGenre.add(new MovieGenreFromDataBase(
                        rs.getInt(ID_MOVIE_GENRE),
                        rs.getInt(GENRE_ID),
                        rs.getInt(MOVIE_ID)
                ));
            }
        }
        return movieGenre;
    } 

    private List<GenreFromDataBase> getGenreFromDataBase() throws SQLException{
        List<GenreFromDataBase> genre = new ArrayList<>();
        DataSource dataSource =  DataSourceSingleton.getInstance();
        try( Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(GET_GENRES);
                ResultSet rs = stmt.executeQuery())
        {
            while(rs.next())
            {
                genre.add(new GenreFromDataBase(
                        rs.getInt(ID_GENRE),
                        rs.getString(NAME)
                ));
            }
        }
        return genre;
    }

    //-------------------- adapting data --------------------------------
    
    private List<Genre> createGenreInAppForMovie(int idMovie) throws SQLException 
    {

        List<MovieGenreFromDataBase> movieGenreFromDataBase = getMovieGenreFromDataBase();
        List<GenreFromDataBase> genreFromDataBase = getGenreFromDataBase();

        List<Genre> genres = new ArrayList<>();
        
        List<MovieGenreFromDataBase> filteredMovieGenres = movieGenreFromDataBase
                .stream()
                .filter(e -> e.getMovieID() == idMovie)
                .collect(Collectors.toList()
        );
        
        filteredMovieGenres.forEach(g ->
                genreFromDataBase
                .stream()
                .filter(f -> f.getIdGenre() == g.getGenreID())
                .collect(Collectors.toList())
                .forEach(m -> genres.add( new Genre(m.getIdGenre(), m.getName()) ))
        );
        return genres;
    }

    private List<Actor> createActorsInAppForMovie(int idMovie) throws SQLException 
    {        
        List<MoviePersonFromDataBase> moviePersons = getMoviePersonsFromDataBase();
        List<PersonDataFromDataBase> personData = getPersonDataFromDataBase();
        
        List<Actor> actors = new ArrayList<>();
        List<MoviePersonFromDataBase> personCollection = moviePersons
                .stream()
                .filter(m -> m.getMovieID() == idMovie && m.getMovieRoleID() == 2 )
                .collect(Collectors.toList());
                
        personCollection.forEach(p -> {
            actors.add(new Actor(
                    p.getPersonDataID(), 
                    personData.stream()
                            .filter(e -> e.getIdPersonData() == p.getPersonDataID())
                            .collect(Collectors.toList())
                            .get(0)
                            .getFirstName(),
                    personData.stream()
                            .filter(e -> e.getIdPersonData() == p.getPersonDataID())
                            .collect(Collectors.toList())
                            .get(0)
                            .getMiddleName(),
                    personData.stream()
                            .filter(e -> e.getIdPersonData() == p.getPersonDataID())
                            .collect(Collectors.toList())
                            .get(0)
                            .getLastName())
            );
        });
        return actors;
    }

    private List<Director> createDirectorsInAppForMovie(int idMovie) throws SQLException 
    {
        List<MoviePersonFromDataBase> moviePersons = getMoviePersonsFromDataBase();
        List<PersonDataFromDataBase> personData = getPersonDataFromDataBase();
        
        List<Director> directors = new ArrayList<>();
        
        
        List<MoviePersonFromDataBase> personCollection = moviePersons
                .stream()
                .filter(m -> m.getMovieID() == idMovie && 
                        m.getMovieRoleID() == 1 )
                .collect(Collectors.toList());
                
        personCollection.forEach(p -> 
            directors.add(new Director(
                    p.getPersonDataID(), 
                    personData.stream()
                            .filter(e -> e.getIdPersonData() == p.getPersonDataID())
                            .collect(Collectors.toList())
                            .get(0)
                            .getFirstName(), 
                    personData.stream()
                            .filter(e -> e.getIdPersonData() == p.getPersonDataID())
                            .collect(Collectors.toList())
                            .get(0)
                            .getMiddleName(), 
                    personData.stream()
                            .filter(e -> e.getIdPersonData() == p.getPersonDataID())
                            .collect(Collectors.toList())
                            .get(0)
                            .getLastName())
            )
        );
        
        return directors;
    }
    
    //--------------------------creating data in the database ---------------------------

    private int createMovieInDataBase(Movie movie) throws SQLException {
        int id;
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE))
        {
            
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getOriginalTitle());
            stmt.setString(3, movie.getDescription());
            stmt.setInt(4, movie.getDuration());
            stmt.setString(5, movie.getPublishedDate().format(Movie.DATE_FORMAT));
            stmt.setString(6, movie.getStartDate().format(Movie.DATE_FORMAT));
            stmt.setString(7, movie.getPicturePath());
            stmt.registerOutParameter(8, Types.INTEGER);
            
            stmt.executeUpdate();
            id = stmt.getInt(8);
        }
        return id;
    }

    private void createMovieGenreInDataBase(Movie movie, int idMovie) throws SQLException {
        List<Genre> genres = movie.getGenre();
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE_GENRE))
        {
            for (Genre genre : genres) {
                stmt.setInt(1, createGenreInDataBase(genre.getName()));
                stmt.setInt(2, idMovie);
                stmt.registerOutParameter(3, Types.INTEGER);
                
                stmt.executeUpdate();
            }  
        }
        
    }

    private int createGenreInDataBase(String genre) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        try ( Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_GENRE)) 
        {
            stmt.setString(1, genre);
            stmt.registerOutParameter(2, Types.INTEGER);
            
            stmt.executeUpdate();
            return stmt.getInt(2);
        }
    }
         
    private void createMoviePersonInDataBase(Movie movie, int idMovie) throws SQLException {
        
        List<Actor> actors = movie.getActors();
        List<Director> directors = movie.getDirectors();
        
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE_PERSON))
        {
            
            if (actors != null) {
                for (Actor actor : actors) {
                    stmt.setInt(1, createPersonDataInDataBase(actor));
                    stmt.setInt(2, 2);
                    stmt.setInt(3, idMovie);
                    stmt.registerOutParameter(4, Types.INTEGER);

                    stmt.executeUpdate();
                }
            }
            
            if (directors != null) {
                for (Director director : directors) {
                    stmt.setInt(1, createPersonDataInDataBase(director));
                    stmt.setInt(2, 1);
                    stmt.setInt(3, idMovie);
                    stmt.registerOutParameter(4, Types.INTEGER);
                    
                    stmt.executeUpdate();
                } 
            }
        } 
    }

    private int createPersonDataInDataBase(Person person) throws SQLException {
        DataSource dataSource = DataSourceSingleton.getInstance();
        int idPersonData;
        try ( Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_PERSON_DATA)) 
        {
            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getMiddleName());
            stmt.setString(3, person.getLastName());
            stmt.registerOutParameter(4, Types.INTEGER);
            
            stmt.executeUpdate();
            idPersonData = stmt.getInt(4);
        }
        return idPersonData;
    }
    
    
    //--------------------------updateing data in the database ---------------------------

    private void updateMovieInDataBase(Movie movie) throws SQLException {
        
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall(UPDATE_MOVIE))
        {
            
            stmt.setInt(1, movie.getId());
            stmt.setString(2, movie.getTitle());
            stmt.setString(3, movie.getOriginalTitle());
            stmt.setString(4, movie.getDescription());
            stmt.setInt(5, movie.getDuration());
            stmt.setString(6, movie.getPublishedDate().format(Movie.DATE_FORMAT));
            stmt.setString(7, movie.getStartDate().format(Movie.DATE_FORMAT));
            stmt.setString(8, movie.getPicturePath());
            
            stmt.executeUpdate();
        }
    }

    private void updateMovieGenreInDataBase(Movie movie) throws SQLException {
        deleteMoviGenreInDataBaseForMovieId(movie.getId());
        createMovieGenreInDataBase(movie, movie.getId());
    }
    
    private void updateMoviePersonInDataBase(Movie movie) throws SQLException {
        deleteMoviPersonInDataBaseForMovieId(movie.getId());
        createMoviePersonInDataBase(movie, movie.getId());
        
    }
    
    
    //--------------------------deleting data---------------------------------------------------

    private void deleteMoviGenreInDataBaseForMovieId(int id) throws SQLException {
            DataSource dataSource = DataSourceSingleton.getInstance();
            
            try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE_GENRE))
            {
                stmt.setInt(1, id);
                stmt.executeUpdate();
               
            }
    }
    
    private void deleteMoviPersonInDataBaseForMovieId(int id) throws SQLException {
            DataSource dataSource = DataSourceSingleton.getInstance();
            
            try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE_PERSON))
            {
                stmt.setInt(1, id);
                stmt.executeUpdate();
               
            }
    }

    private void deleteMoviPersonInDataBaseForPersonId(int id) throws SQLException {
            DataSource dataSource = DataSourceSingleton.getInstance();
            
            try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE_PERSON_FILTERED_BY_PERSON_ID))
            {
                stmt.setInt(1, id);
                stmt.executeUpdate();
               
            }
    }
}
