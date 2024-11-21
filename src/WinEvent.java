import java.util.Arrays;

class WinEvent extends Event {

	void execute() {

        int k;
        int currLetter = 0;
        int speed = 0;
        long spinTime = System.currentTimeMillis();
        double A = 0, B = 0;
        double[] z = new double[1760];
        char[] b = new char[1760];
        System.out.print("\u001b[2J"); //clears the screen dont really need


        while (true) {
            Arrays.fill(b, ' ');
            Arrays.fill(z, 0);

            for (double j = 0; j < 6.28; j += 0.07) {
                for (double i = 0; i < 6.28; i += 0.02) {
                    double c = Math.sin(i),
                            d = Math.cos(j),
                            e = Math.sin(A),
                            f = Math.sin(j),
                            g = Math.cos(A),
                            h = d + 2,
                            D = 1 / (c * h * e + f * g + 5),
                            l = Math.cos(i),
                            m = Math.cos(B),
                            n = Math.sin(B),
                            t = c * h * g - f * e;

                    int x = (int) (40 + 30 * D * (l * h * m - t * n));
                    int y = (int) (12 + 15 * D * (l * h * n + t * m));
                    int o = x + 80 * y;

                    if (y > 0 && y < 22 && x > 0 && x < 80 && D > z[o]) {
                        z[o] = D;
                        b[o] = "0WINNER".charAt(currLetter);

                                          }
                }
            }

            System.out.print("\u001b[H");

            for (k = 0; k < 1760; k++) {
                System.out.print(k % 80 > 0 ? b[k] : '\n');
            }

            A += 0.04;
            B += 0.02;

            if (speed % 15 == 0) {
                currLetter = (currLetter + 1) % 7;
            }
            speed++;

            if (System.currentTimeMillis() - spinTime > 12150) {
                  break;
            }

            try {
                Thread.sleep(100);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("");
            System.out.println("");
            System.out.println("=================================WINNER===================================");
        }
    }
}
