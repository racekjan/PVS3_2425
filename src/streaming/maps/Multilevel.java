package streaming.maps;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Multilevel {
    static class TestResult{
        String studentName, subject;
        double score;
        int timeSpent;

        public TestResult(String studentName, String subject, double score, int timeSpent) {
            this.studentName = studentName;
            this.subject = subject;
            this.score = score;
            this.timeSpent = timeSpent;
        }

        public String getStudentName() {
            return studentName;
        }

        public String getSubject() {
            return subject;
        }

        public double getScore() {
            return score;
        }

        public int getTimeSpent() {
            return timeSpent;
        }
    }
    static double getAverageScore(List<TestResult> results, String name){
        return results.stream()
                .filter(test -> test.getStudentName().equals(name))
                .mapToDouble(TestResult::getScore)
                .average()
                .orElse(0);
    }

    public static void main(String[] args) throws IOException {
        List<TestResult> results = Files.lines(Paths.get("data\\students.csv"))
                .skip(1)
                .map(line -> line.split(","))
                .map(splitLine -> new TestResult(
                        splitLine[0],
                        splitLine[1],
                        Double.parseDouble(splitLine[2]),
                        Integer.parseInt(splitLine[3])
                )).toList();

        //mapa: student: {predmet} - {skore}
        Map<String, Map<String, List<Double>>> grouped = results.stream()
                .collect(Collectors.groupingBy(
                        TestResult::getStudentName,//prvni mapa, prvni klic
                        Collectors.groupingBy(
                                TestResult::getSubject,
                                Collectors.mapping(TestResult::getScore, Collectors.toList())
                        )
                ));

        grouped.forEach((k,v)-> {
            System.out.println(k);
            v.forEach((subjects, scores) -> {
                System.out.println("\t" + subjects);
                scores.forEach(score -> System.out.println("\t\t" + score));
            });
        });
    }

}

