package streaming.maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SongProcessing {
    static class Track{
        String name;
        int duration;

        public Track(String name, int duration) {
            this.name = name;
            this.duration = duration;
        }

        public String getName() {
            return name;
        }

        public int getDuration() {
            return duration;
        }
    }

    static class Album{
        String name;
        List<Track> songs;
        int id;

        public Album(String name, List<Track> song) {
            this.name = name;
            this.songs = song;
        }

        public int getId() {
            return id;
        }

        public Album (String name, int id){
            this.name = name;
            this.id = id;
            this.songs = new ArrayList<>();
        }
        void addSong(Track toAdd){
            songs.add(toAdd);
        }

        public String getName() {
            return name;
        }

        public List<Track> getSongs() {
            return songs;
        }
    }

    static class Band{
        String name;
        List<Album> albums;
        int id;


        public Band(String name,List<Album> albums) {
            this.name = name;
            this.albums = albums;
        }


        public int getId() {
            return id;
        }

        public Band(String name,int id) {
            this.name = name;
            this.id = id;
            this.albums = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        public List<Album> getAlbums() {
            return albums;
        }

        void addAlbum(Album toAdd){
            albums.add(toAdd);
        }
    }

    public static void main(String[] args) throws IOException {
        //load & parse
        //vsechny prvni vyzaduji ID, abych mohl chain linkovat
        List<Band> bands = Files.lines(Paths.get("data\\artists.csv"))
                .skip(1)
                .map(line -> line.split(";"))
                .map(splitLine -> new Band(
                        splitLine[1],
                        Integer.parseInt(splitLine[0])
                )).toList();
    }
}
