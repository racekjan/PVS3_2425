package streaming.maps.test2;

import streaming.maps.SongProcessing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class lekyTest {
    static class Med{
        String name;
        int pieces;
        String pharmacy;
        int month;
        boolean paidByInsurence;

        public Med(String name, int pieces, String pharmacy, int month, boolean paidByInsurence) {
            this.name = name;
            this.pieces = pieces;
            this.pharmacy = pharmacy;
            this.month = month;
            this.paidByInsurence = paidByInsurence;
        }

        public String getName() {
            return name;
        }

        public int getPieces() {
            return pieces;
        }

        public String getPharmacy() {
            return pharmacy;
        }

        public int getMonth() {
            return month;
        }

        public boolean isPaidByInsurence() {
            return paidByInsurence;
        }

        @Override
        public String toString() {
            return name + "," + pieces + "pieces";
        }
    }

    public static void main(String[] args) throws IOException {
        List<Med> medicaments = Files.lines(Paths.get("src\\streaming\\maps\\test2\\meds_sales.csv"))
                .skip(1)
                .map(line -> line.split(","))
                .map(splitLine -> new Med(
                        splitLine[0],
                        Integer.parseInt(splitLine[1]),
                        splitLine[2],
                        Integer.parseInt(splitLine[3]),
                        Boolean.parseBoolean(splitLine[4])
                )).toList();

        long unique = medicaments.stream()
                        .map(med -> med.name)
                        .distinct()
                        .count();

        System.out.println(medicaments.size());
        System.out.println(unique);
        Map<String, Long> pharmCount = medicaments.stream()
                    .filter(med -> med.isPaidByInsurence())
                    .collect(Collectors.groupingBy((Med::getPharmacy), Collectors.counting()));
        System.out.println(pharmCount);
        Map<String, Integer> pharmPieces = medicaments.stream()
                .collect(Collectors.groupingBy((Med::getPharmacy),Collectors.summingInt(Med::getPieces)));
        System.out.println(pharmPieces);

        System.out.println(soldIn(medicaments, 1 ,10));

        Map<String, Integer> medCategroies = medicaments.stream()
                .collect(Collectors.groupingBy(m-> {
                    if (m.getMonth() >= 1 && m.getMonth() <=3) return "Q1";
                    if (m.getMonth() >= 4 && m.getMonth() <=6) return "Q2";
                    if (m.getMonth() >= 7 && m.getMonth() <=9) return "Q3";
                    return "Q4";
                }, Collectors.summingInt(Med::getPieces)));
        System.out.println(medCategroies);


    }
    static int soldIn(List<Med> sales, int from, int to){
        return sales.stream()
                .filter(med -> med.month > from && med.month < to).mapToInt(Med::getPieces).sum();
    }
}
