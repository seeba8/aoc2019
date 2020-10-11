package day17;

import day5.AdvancedIntcodeComputer;
import utils.Point;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ASCII extends AdvancedIntcodeComputer {
    List<String> scaffoldView = new ArrayList<>();
    Point<Integer> robot;

    public ASCII(long[] programme) {
        super(programme, 0L);
        scaffoldView.add("");
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        long[] programme = utils.Input.getLongArrayFromSingleLine("day17.txt", ",");
        ASCII ascii = new ASCII(programme);
        ascii.run();

    }

    public String[] getScaffoldView() {
        return this.scaffoldView.toArray(new String[]{});
    }

    public void setScaffoldView(String[] scaffoldView) {
        this.scaffoldView = Arrays.asList(scaffoldView);
    }

    @Override
    protected long getInput() {
        return 0;
    }

    @Override
    protected void output(long value) {
        char output = (char) value;
        int currentIndex = scaffoldView.size() - 1;
        switch (output) {
            case '#':
            case '.':
            case '<':
            case '>':
            case '^':
            case 'v':
                scaffoldView.set(currentIndex, scaffoldView.get(currentIndex) + output);
                break;
            case '\n':
                scaffoldView.add("");
        }
    }
}
