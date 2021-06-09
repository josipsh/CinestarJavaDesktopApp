/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.movieTheater.parsers.rss;

import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.utils.FileUtils;
import hr.movieTheater.DirectoryInformationHandler;
import hr.movieTheater.model.Actor;
import hr.movieTheater.model.Director;
import hr.movieTheater.model.Genre;
import hr.movieTheater.model.Movie;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author dnlbe
 */
public class MovieParser {

    private static final String RSS_URL = "https://www.blitz-cinestar.hr/rss.aspx?najava=1";
    private static final int TIMEOUT = 10000;
    private static final String REQUEST_METHOD = "GET";
    private static final String EXT = ".jpg";
    private static final String DIR = DirectoryInformationHandler.getAssetDirectory();

    private static final DateTimeFormatter DATE_FORMATTER_PUBLISHED_DATE = DateTimeFormatter.RFC_1123_DATE_TIME; // dateTime format in the rss feed for "pubDate"
    private static final DateTimeFormatter DATE_FORMATTER_START_DATE = DateTimeFormatter.ofPattern("d.M.y");  // dateTime format in the rss feed for "pocetak"
    private static final Random RANDOM = new Random();

    public static List<Movie> parse() throws IOException, XMLStreamException {
        List<Movie> movies = new ArrayList<>();
        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL, TIMEOUT, REQUEST_METHOD);
        XMLEventReader reader = ParserFactory.createStaxParser(con.getInputStream());

        Optional<TagType> tagType = Optional.empty();
        Movie movie = null;
        StartElement startElement = null;
        
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    startElement = event.asStartElement();
                    String qName = startElement.getName().getLocalPart();
                    tagType = TagType.from(qName);
                    break;
                case XMLStreamConstants.CHARACTERS:
                    if (tagType.isPresent()) {
                        Characters characters = event.asCharacters();
                        String data = characters.getData().trim();
                        switch (tagType.get()) {
                            case ITEM:
                                movie = new Movie();
                                movies.add(movie);
                                break;
                            case TITLE:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setTitle(data);
                                }
                                break;
                            case ORIGNAZIV:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setOriginalTitle(data);
                                }
                                break;
                            case PUB_DATE:
                                if (movie != null && !data.isEmpty()) {
                                    LocalDate publishedDate;
                                    try {
                                        publishedDate = LocalDateTime.parse(data, DATE_FORMATTER_PUBLISHED_DATE).toLocalDate();
                                    } catch (DateTimeParseException ex) {
                                        publishedDate = LocalDate.MIN;
                                    }
                                    
                                    movie.setPublishedDate(publishedDate);
                                }
                                break;
                            case DESCRIPTION:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setDescription(cleanDescription(data));
                                }
                                break;
                            case READATELJ:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setDirectors(createDirectorsFromString(data));
                                }
                                break;
                            case GLUMCI:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setActors(createActorsFromString(data));
                                }
                                break;
                            case TRAJANJE:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setDuration(Integer.parseInt(data));
                                }
                                break;
                            case ZANR:
                                if (movie != null && !data.isEmpty()) {
                                    movie.setGenre(createGenresFromString(data));
                                }
                                break;
                            case PLAKAT:
                                if (movie != null && !data.isEmpty()) {
                                    handlePicture(movie, data);
                                }
                                break;
                            case POCETAK:
                                if (movie != null && !data.isEmpty()) {
                                    LocalDate startDate = LocalDate.parse(data, DATE_FORMATTER_START_DATE);
                                    movie.setStartDate(startDate);
                                }
                                break;

                        }
                    }
                    break;
            }
        }
        return movies;
    }

    private static void handlePicture(Movie movie, String pictureUrl) throws IOException {

        String ext = pictureUrl.substring(pictureUrl.lastIndexOf("."));
        if (ext.length() > 4) {
            ext = EXT;
        }
        String pictureName = Math.abs(RANDOM.nextInt()) + ext;
        String localPicturePath = DIR + File.separator + pictureName;
        
        pictureUrl = pictureUrl.replace("http", "https");
        
        FileUtils.copyFromUrl(pictureUrl, localPicturePath);
        movie.setPicturePath(localPicturePath);
    }
    
    private static List<Director> createDirectorsFromString(String data) {
        List<Director> directors = new ArrayList<>();
        String[] listOfDirectors = data.split(",");
        
        for (String listOfDirector : listOfDirectors) {
            List<String> fullName = separateFullName(listOfDirector);
            
            if (fullName.size() <= 2) {
                directors.add(new Director( 
                    -1,
                    fullName.get(0),
                    "",
                    fullName.get(1)
                    
                ));
            }
            else if (fullName.size() == 3){
                directors.add(new Director( 
                    -1,
                    fullName.get(0), 
                    fullName.get(1),
                    fullName.get(2)
                ));
            }
            else{
                directors.add(new Director(
                    -1,
                    fullName.get(0), 
                    "",
                    ""
                )); 
            }
        }
        
        return directors;
    }

    private static List<Actor> createActorsFromString(String data) {
        List<Actor> actors = new ArrayList<>();
        String[] listOfActors = data.split(",");
        
        for (String listOfActor : listOfActors) {

            List<String> fullName = separateFullName(listOfActor);

            if (fullName.size() == 2) {
                actors.add(new Actor(
                    -1,
                    fullName.get(0),
                    "",
                    fullName.get(1)
                   
                ));
            }
            else if (fullName.size() == 3){
                actors.add(new Actor(
                    -1,
                    fullName.get(0), 
                    fullName.get(1),
                    fullName.get(2)
                ));
            }
            else{
                actors.add(new Actor(
                    -1,
                    fullName.get(0), 
                    "",
                    ""
                )); 
            }
        }

        return actors;
    }

    private static List<Genre> createGenresFromString(String data) {
        List<Genre> genres = new ArrayList<>();
        String[] listOfGenres = data.split(",");
        
        for (String listOfGenre : listOfGenres) {
            genres.add(new Genre(-1, listOfGenre.trim()));
        }
        
        return genres;
    }

    private static List<String> separateFullName(String line) {
        String[] names = line.trim().split(" ");
        List<String> fullName = new ArrayList<>();
        
        for (String name : names) {
            String[] namePart = name.trim().split("-");
            if (namePart.length == 1) {
                fullName.add(name);
            }
            else{
                for (String d : namePart) {
                    fullName.add(d);
                }
            }
        }
        return fullName;
    }

    private static String cleanDescription(String data) {
        String[] splitedData = data.split(">");
        String[] clinedData = splitedData[1].split("<");
        
        return clinedData[0];
    }


    private enum TagType {

        ITEM("item"), 
        TITLE("title"), 
        ORIGNAZIV("orignaziv"), 
        PUB_DATE("pubDate"),
        DESCRIPTION("description"),
        READATELJ("redatelj"),
        GLUMCI("glumci"),
        TRAJANJE("trajanje"),
        ZANR("zanr"),
        PLAKAT("plakat"),
        POCETAK("pocetak");
        

        private final String name;

        private TagType(String name) {
            this.name = name;
        }

        private static Optional<TagType> from(String name) {
            
            for (TagType value : values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }
    }

}
