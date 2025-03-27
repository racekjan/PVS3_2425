package streaming.maps.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookTaskBetter {
    static class Book{
        private int bookID;
        private String title;
        private String author;
        private List<Chapter> chapters = new ArrayList<>();
        public Book(int bookID, String title, String author) {
            this.bookID = bookID;
            this.title = title;
            this.author = author;
        }

        public int getBookID() {
            return bookID;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public List<Chapter> getChapters() {
            return chapters;
        }
        public int getTotalPages(){
            return chapters.stream()
                    .mapToInt(Chapter::getPages)//namapuje pocet stranek kapitoly
                    .sum();//secte je
        }
    }
    static class Chapter{
        private int bookID;
        private String title;
        private int pages;

        public Chapter(int bookID, String title, int pages) {
            this.bookID = bookID;
            this.title = title;
            this.pages = pages;
        }

        public int getBookID() {
            return bookID;
        }

        public void setBookID(int bookID) {
            this.bookID = bookID;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }
    }

    static void getAuthorPages(List<Book> books, String name){
        int total = books.stream()
                .filter(book -> book.getAuthor().equals(name))//vybere vsechny knizky, ktere maji stejneho autora
                .mapToInt(Book::getTotalPages)//namapuje autora a celkovy pocet stran knihy
                .sum();//secte stranky vsech knih
        System.out.println("Autor " + name + " napsal celkem " + total + " stran");
    }

    public static void main(String[] args) throws IOException {
        //parsing
        List<Book> books = Files.lines(Paths.get("src/streaming/maps/test/books.csv"))
                .skip(1)
                .map(line -> line.split(",",3))
                .map(parsed -> new Book(
                        Integer.parseInt(parsed[0]),
                        parsed[1],
                        parsed[2]
                )).toList();
        List<Chapter> chapters = Files.lines(Paths.get("src/streaming/maps/test/chapters.csv"))
                .skip(1)
                .map(line -> line.split(",",4))
                .map(split -> new Chapter(
                        Integer.parseInt(split[1]),
                        split[2],
                        Integer.parseInt(split[3])
                )).toList();

        //System.out.println(chapters.size());
        //System.out.println(books.size());

        Map<Integer, Book> bookMap = books.stream()
                .collect(Collectors.toMap(Book::getBookID, b-> b));

        chapters.forEach(chapter -> {
            Book book = bookMap.get(chapter.getBookID());//sebrani vsech kapitol od jedne knizky
            if (book != null){
                book.getChapters().add(chapter);
            }
        });

        books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.counting()))
                .forEach((author, count) -> System.out.println(author + ": " + count + " books"));

        books.stream()
                .sorted(Comparator.comparingInt(Book::getTotalPages).reversed())//seradi knizky podle stranek od nejvetsich
                .limit(5)//vybere prvnich 5
                .forEach(b -> System.out.println(b.getTitle() + " " + b.getTotalPages()));
    }


}
