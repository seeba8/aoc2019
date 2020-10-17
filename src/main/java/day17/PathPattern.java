package day17;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PathPattern {
    static final int MAX_LENGTH = 20;
    String path;
    String mainRoutine = "";
    List<String> patterns = new ArrayList<>();

    static String[] getSteps(String path) {
        Pattern p = Pattern.compile("([A-Z],\\d+)");
        Matcher matcher = p.matcher(path);
        List<String> res = new ArrayList<>();
        while (matcher.find()) {
            res.add(matcher.group(1));
        }
        return res.toArray(new String[0]);

    }

    protected void splitIntoPatterns() {
        Queue<String> segments = new LinkedList<>();
        segments.add(this.path);
        mainRoutine = path;
        while (!segments.isEmpty()) {
            String segment = segments.poll();
            String pattern = findPattern(segment);
            if (!pattern.isEmpty()) {
                segments.addAll(Arrays.asList(segment.split(pattern)));
                patterns.add(pattern);
                mainRoutine = mainRoutine.replace(pattern, Character.toString((char) (64 + patterns.size())));
                Queue<String> newSegments = new LinkedList<>();
                while (!segments.isEmpty()) {
                    newSegments.addAll(Arrays.stream(segments.poll().split(pattern)).map(s -> {
                        if (s.endsWith(",")) {
                            s = s.substring(0, s.length() - 1);
                        }
                        if (s.startsWith(",")) {
                            s = s.substring(1);
                        }
                        return s;
                    }).collect(Collectors.toList()));
                }
                segments = newSegments;
            }
        }
    }

    String findPattern(String path) {
        if (path.length() < MAX_LENGTH) {
            return path;
        }
        String[] steps = getSteps(path);
        int endIndex = steps.length;
        while (endIndex != 0) {
            String pattern = Arrays.stream(steps).limit(endIndex).collect(Collectors.joining(","));
            //if(pattern.endsWith(",")) pattern = pattern.substring(0, pattern.length() - 1);
            if (path.indexOf(pattern, 1) > -1) {
                return pattern;
            }
            endIndex--;
        }
        return "";
    }

    String applyPattern() {
        StringBuilder sb = new StringBuilder();
        for (String subRoutine : mainRoutine.split(",")) {
            sb.append(patterns.get(-65 + (int) subRoutine.charAt(0))).append(",");
        }
        return sb.toString().substring(0, sb.length() - 1);
    }

}
