import java.util.*;

// The AStarAlgorithm class calculates the optimal road trip path from the starting county 
// to the destination county while minimizing distance traveled and COVID exposure along 
// the route. 
public class AStarAlgorithm {

    private static County startCounty;
    private static County endCounty;

    // The set of counties to be evaluated
    private static Queue<County> open;

    // The set of counties already evaluated 
    private static List<County> closed;

    // Takes the current county, an adjacent county, and a cost (distance to travel from 
    // current county to adjacent county) as parameters. Determines if this particular path 
    // is less costly (less number of miles to travel) than all previous paths. It this path 
    // is less costly, updates this path to be the most optimal path. If this path is not 
    // less costly, does not make any changes to optimal path. 
    private static void checkAndUpdateCost(County currentCounty, County adjCounty, double cost) {
        if (adjCounty == null || closed.contains(adjCounty)) {
            return;
        }
        double adjCounty_final_cost = adjCounty.getHeuristicCost() + cost;
        boolean inOpen = open.contains(adjCounty);
        if (!inOpen || adjCounty_final_cost < adjCounty.getFinalCost()) {
            adjCounty.setFinalCost(adjCounty_final_cost);
            adjCounty.setParent(currentCounty);
            if (!inOpen) {
                open.add(adjCounty);
            }
        }
    }

    // Traces optimal path from starting county to destination county. 
    public static void AStar() {
        open.add(startCounty);
        County currentCounty = open.remove();
        while (currentCounty != null) {
            closed.add(currentCounty);
            // If destination county is reached, exit loop
            if (currentCounty.equals(endCounty)) {
                return;
            }
            List<County> adjCounties = currentCounty.getAdjCounties();
            for (int i = 0; i < adjCounties.size(); i++) {
                County adjCounty = adjCounties.get(i);
                double countyDistance = distance(currentCounty.getLatitude(), 
                adjCounty.getLatitude(), currentCounty.getLongitude(), adjCounty.getLongitude(), adjCounty.getCovidPercentage());
                checkAndUpdateCost(currentCounty, adjCounty, currentCounty.getFinalCost() + countyDistance);
            }
            currentCounty = open.remove();
        }
    }

    // Takes a starting county, destination county, and a list of all counties as parameters. Prints 
    // the optimal county-by-county path from the starting county to the destination county while 
    // minimizing distance traveled and COVID exposure along the route. 
    public static void optimize(County newStartCounty, County newEndCounty, List<County> allCounties) {
        closed = new ArrayList<County>();
        open = new LinkedList<County>();
        startCounty = newStartCounty;
        endCounty = newEndCounty;
        for (int i = 0; i < allCounties.size(); i++) {
            County currentCounty = allCounties.get(i);
            // Calculates the distance from the current county to the destination county
            double distancetoDest = distance(currentCounty.getLatitude(), 
            endCounty.getLatitude(), currentCounty.getLongitude(), endCounty.getLongitude(), endCounty.getCovidPercentage());
            currentCounty.setHeuristicCost(distancetoDest);
        }
        AStar();
        System.out.println();
        if (closed.contains(endCounty)) {
            County currentCounty = endCounty;
            Stack<County> countyOrder = new Stack<County>();
            countyOrder.push(currentCounty);
            // Retraces the optimal path from the desination county to the starting county
            double totalCovid = 0.0;
            double counter = 0.0;
            while (currentCounty.getParent() != null) {
                currentCounty = currentCounty.getParent();
                totalCovid +=  currentCounty.getCovidPercentage();
                counter += 1;
                countyOrder.push(currentCounty);
            }
            System.out.println("Path: ");
            System.out.println();
            System.out.print(countyOrder.pop());
            while (!countyOrder.isEmpty()) {
                System.out.print(" -> " + countyOrder.pop());
            }
            System.out.println();
            System.out.println("Average COVID Percentage of Trip: " + Math.round(totalCovid / counter * 100.0) / 100.0 + "%");
        } else {
            System.out.println("No possible path");
        }
        System.out.println();
    }

    // Takes a starting county's center point (latitude, longitude) and destination county's center
    // point (latitude, longitude) as parameters. Returns the distance, in miles, between them.
    // General algorithm taken from https://dzone.com/articles/distance-calculation-using-3 and 
    // then modified.
    public static double distance(double lat1, double lon1, double lat2, double lon2, double covid) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) 
        * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist += (covid * 30);
        return dist;
    }
    
    // Takes a degree value as a parameter. Returns the radian value equal to the degree value.
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    
    // Takes a radian value as a parameter. Returns the degree value equal to the radian value.
    private static double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

}
