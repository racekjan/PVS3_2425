package streaming.maps.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookTask {
    static class Book{
        private int bookId;
        private String title;
        private String author;
        private List<Chapter> chapters = new ArrayList<>();
        private int totalPages;

        public Book(int bookId, String title, String author, List<Chapter> chapters, int totalPages) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.chapters = chapters;
            this.totalPages = totalPages;
        }

        @Override
        public String toString() {
            return title + " - " + author;
        }

        public int getTotalPages() {
            return totalPages;
        }

        /*public int getTotalPages() {
            // TODO: 26.03.2025 Dodelat, bude se hodit
            return 0;
        }*/

        public void setChapters(List<Chapter> chapters) {
            this.chapters = chapters;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getBookId() {
            return bookId;
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
    }

    static class Chapter {
        // TODO: 26.03.2025 Dodelat vse potrebne
        private int chapterID;
        private int bookID;
        private String title;
        private int pages;

        public Chapter(int chapterID, int bookID, String title, int pages) {
            this.chapterID = chapterID;
            this.bookID = bookID;
            this.title = title;
            this.pages = pages;
        }

        @Override
        public String toString() {
            return title;
        }

        public int getChapterID() {
            return chapterID;
        }

        public int getBookID() {
            return bookID;
        }

        public String getTitle() {
            return title;
        }

        public int getPages() {
            return pages;
        }
    }

    static ArrayList<Book> loadBooks(String path) throws IOException {//cteni ze souboru - dulezite!!
        List<String> lines = Files.readAllLines(Paths.get(path));
        ArrayList<Book> books = new ArrayList<>();
        String[] params;
        for (String line:lines) {
            if (line.contains("book")){

            } else {
                params = line.split(",");
                books.add(new Book(
                        Integer.parseInt(params[0]),
                        params[1],
                        params[2],
                        new ArrayList<>(),
                        0
                ));
            }
        }
        return books;
    }
    static ArrayList<Chapter> loadChapters(String path) throws IOException {//cteni ze souboru - dulezite!!
        List<String> lines = Files.readAllLines(Paths.get(path));
        ArrayList<Chapter> chapters = new ArrayList<>();
        String[] params;
        for (String line:lines) {
            if (line.contains("chapter")){

            } else {
                params = line.split(",");
                chapters.add(new Chapter(
                        Integer.parseInt(params[0]),
                        Integer.parseInt(params[1]),
                        params[2],
                        Integer.parseInt(params[3])
                ));
            }
        }
        return chapters;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Book> books = loadBooks("src\\streaming\\maps\\test\\books.csv");
        ArrayList<Chapter> chapters = loadChapters("src\\streaming\\maps\\test\\chapters.csv");
        System.out.println("Nacteno: " + books.size() + "filmu a " + chapters.size() + "chapters");
        Map<String,Long> authorsWithBooks = books.stream()
                .collect(Collectors.groupingBy(
                        Book::getAuthor,
                        Collectors.counting()
                ));
        System.out.println(authorsWithBooks);

        Map<Integer,List<Integer>> pageMap = chapters.stream()
                .collect(Collectors.groupingBy(
                        Chapter::getBookID,
                        Collectors.mapping(Chapter::getPages, Collectors.toList())
                ));
        books.forEach(book ->{
            List<Integer> bookPages = pageMap.get(book.getBookId());
            int total = bookPages.stream()
                    .mapToInt(Integer::intValue)
                    .sum();
            book.setTotalPages(total);
        });
        List<Book> sorted = books.stream()
                .sorted(Comparator.comparingInt(Book::getTotalPages).reversed())
                .toList();
        System.out.println(pageMap);
        for (int i = 0; i < 5; i++) {
            System.out.println(sorted.get(i).title + ": " + sorted.get(i).getTotalPages());
        }
    }
}
