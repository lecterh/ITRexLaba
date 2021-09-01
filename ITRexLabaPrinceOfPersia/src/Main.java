public class Main {

    private static int routeCalculate() {

        AlgorithmLee algorithmLee = new AlgorithmLee(3, 3);
        char[][][] levels = new char[][][]{{{'1', '.', '.'},
                {'0', '0', '.'},
                {'.', '.', '.'}},

                {{'0', '0', '0'},
                        {'.', '.', '0'},
                        {'.', '0', '0'}},

                {{'0', '0', '0'},
                        {'0', '.', '.'},
                        {'0', '.', '2'}}};

        int[] princePos = findPoint(levels[0], '1');
        int[] princessPos = findPoint(levels[2], '2');
        int[][] allWayOuts = getAllWayOuts(levels);
        int[][] allPoints = getAllPoints(princePos, princessPos, allWayOuts);
        int sum = 0;

        for (int i = 0; i < levels.length; i++) {
            if (allPoints[i][0] < allPoints[i + 1][0]) {
                sum += getShortestDestination(allPoints[i], allPoints[i + 1], levels[i], algorithmLee);
            } else if (allPoints[i][0] > allPoints[i + 1][0]) {
                sum += getShortestDestination(allPoints[i + 1], allPoints[i], levels[i], algorithmLee);
            } else if (allPoints[i][1] < allPoints[i + 1][1]) {
                sum += getShortestDestination(allPoints[i], allPoints[i + 1], levels[i], algorithmLee);
            } else {
                sum += getShortestDestination(allPoints[i + 1], allPoints[i], levels[i], algorithmLee);
            }
        }

        sum += 3 -1;

        int result = sum * 5;

        System.out.println("result is " + result);

        return result;
    }

    private static int[] findPoint(char[][] currentLevel, char element) {
        for (int m = 0; m < currentLevel.length; m++) {
            for (int n = 0; n < currentLevel[m].length; n++) {
                if (currentLevel[m][n] == element) {
                    return new int[]{m, n};
                }
            }
        }

        throw new RuntimeException("WayOut has not been found");
    }

    private static int[][] getAllPoints(int[] princePos, int[] princessPos, int[][] allWayOuts) {
        int[][] result = new int[allWayOuts.length + 2][2];
        result[0] = princePos;
        System.arraycopy(allWayOuts, 0, result, 1, result.length - 2);
        result[result.length - 1] = princessPos;

        return result;
    }

    private static int[][] getAllWayOuts(char[][][] levels) {
        int[][] result = new int[levels.length - 1][2];
        for (int h = 0; h < levels.length - 1; h++) {
            result[h] = findWayOut(levels[h], levels[h + 1]);
        }

        return result;
    }

    private static int[] findWayOut(final char[][] currentLevel, final char[][] nextLevel) {
        for (int m = 0; m < currentLevel.length; m++) {
            for (int n = 0; n < currentLevel[m].length; n++) {
                if (currentLevel[m][n] == '.' && currentLevel[m][n] == nextLevel[m][n]) {
                    return new int[]{m, n};
                }
            }
        }

        throw new RuntimeException("WayOut has not been found");
    }

    private static int getShortestDestination(int[] startPoint, int[] endPoint, char[][] level, final AlgorithmLee algorithmLee) {
        return algorithmLee.bfs(level, startPoint[0], startPoint[1], endPoint[0], endPoint[1]);
    }

    public static void main(String[] args) {
        routeCalculate();
    }

}
