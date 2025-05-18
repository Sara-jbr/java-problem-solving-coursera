package org.coursera.duke.solvingproblems.module3;

import edu.duke.URLResource;

public class PrintYoutubeLinks {
    public void printYoutubeLinks() {
        URLResource resource = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");

        for (String word : resource.words()) {
            String wordLower = word.toLowerCase();
            if (wordLower.contains("youtube.com")) {
                int start = word.indexOf("\"");
                int end = word.indexOf("\"", start + 1);
                if (start != -1 && end != -1) {
                    System.out.println(word.substring(start + 1, end));
                }
            }
        }
    }

    public static void main(String[] args) {
        PrintYoutubeLinks p = new PrintYoutubeLinks();
        p.printYoutubeLinks();
    }
}
