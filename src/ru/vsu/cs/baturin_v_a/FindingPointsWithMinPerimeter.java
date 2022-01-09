package ru.vsu.cs.baturin_v_a;

public class FindingPointsWithMinPerimeter {
    public int[][] findResult(int[][] array) {
        if (areSameRowsExist(array)) {
            return findPointsWithMinPerimeter(array);
        } else {
            System.out.println("Error, there are same points in input array");
            return array;
        }
    }

    private int[][] findPointsWithMinPerimeter(int[][] array) {
        double minPerimeter = 1.7e+308;
        int[][] result = new int[3][2];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                for (int k = 0; k < array.length; k++) {
                    if ((i != j) && (i != k) && (j != k)) {
                        double a = Math.sqrt(Math.pow(array[i][0] - array[j][0], 2) + Math.pow(array[i][1] - array[j][1], 2));
                        double b = Math.sqrt(Math.pow(array[i][0] - array[k][0], 2) + Math.pow(array[i][1] - array[k][1], 2));
                        double c = Math.sqrt(Math.pow(array[k][0] - array[j][0], 2) + Math.pow(array[k][1] - array[j][1], 2));

                        if (isTriangleExist(a, b, c)) {
                            double perimeter = a + b + c;

                            if (perimeter < minPerimeter) {
                                int[] firstPoint = new int[]{array[i][0], array[i][1]};
                                int[] secondPoint = new int[]{array[j][0], array[j][1]};
                                int[] thirdPoint = new int[]{array[k][0], array[k][1]};

                                result = new int[][]{firstPoint, secondPoint, thirdPoint};

                                minPerimeter = perimeter;
                            }
                        }
                    }
                }
            }
        }
        if ((result[0][0] == 0)
                && (result[0][1] == 0)
                && (result[1][0] == 0)
                && (result[1][1] == 0)
                && (result[2][0] == 0)
                && (result[2][1] == 0)) {
            System.out.println("Not enough valid points");
        }
        return result;
    }

    private boolean isTriangleExist(double a, double b, double c) {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }

    private boolean areSameRowsExist(int[][] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                boolean sameRows = true;
                for (int k = 0; k < 2; k++) {
                    if (!(array[i][k] == array[j][k])) {
                        sameRows = false;
                    }
                }
                if (sameRows) {
                    System.out.println("Rows " + (i + 1) + " and " + (j + 1) + " are equal!");
                    return false;
                }
            }
        }
        return true;
    }
}


