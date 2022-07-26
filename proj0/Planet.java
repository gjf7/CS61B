public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    private boolean equals(Planet p) {
        return (xxPos == p.xxPos && yyPos == p.yyPos && xxVel == p.xxVel && yyVel == p.yyVel && mass == p.mass && imgFileName == p.imgFileName);
    }

    public double calcDistance(Planet p) {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance;
    }

    public double calcForceExertedBy(Planet p) {
        double G = 6.67e-11;
        double distance = this.calcDistance(p);
        double force = G * this.mass * p.mass / (distance * distance);
        return force;
    }

    public double calcForceExertedByX(Planet p) {
        double force = this.calcForceExertedBy(p);
        double dx = p.xxPos - this.xxPos;
        double forceX = force * dx / this.calcDistance(p);
        return forceX;
    }

    public double calcForceExertedByY(Planet p) {
        double force = this.calcForceExertedBy(p);
        double dy = p.yyPos - this.yyPos;
        double forceY = force * dy / this.calcDistance(p);
        return forceY;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceX = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) {
                netForceX += this.calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceY = 0;
        for (Planet p : allPlanets) {
            if (!this.equals(p)) {
                netForceY += this.calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        double ax = fX / this.mass;
        double ay = fY / this.mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }
}
