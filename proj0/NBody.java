public class NBody {

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = Double.parseDouble(args[3]);
        double scale = Double.parseDouble(args[4]);
        int N = Integer.parseInt(args[5]);
        Input in = new Input(filename);
        Body[] bodies = in.readBodies(N);
        StdDraw.setScale(scale, scale);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (int t = 0; t < T; t += dt) {
            double[] xForces = new double[N];
            double[] yForces = new double[N];
            for (int i = 0; i < N; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i < N; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < N; i++) {
                bodies[i].draw();
            }
            StdDraw.show(10);
        }
        System.out.println("Done");
    }

    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int N = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int N = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[N];
        for (int i = 0; i < N; i++) {
            double xPos = in.readDouble();
            double yPos = in.readDouble();
            double xVel = in.readDouble();
            double yVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(xPos, yPos, xVel, yVel, mass, imgFileName);
        }
        return planets;
    }
}
