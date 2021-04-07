package PirateGameApp.service;

import io.micronaut.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.*;


@Singleton
public class FindPiratePath {

    private static final Logger LOGGER = LoggerFactory.getLogger(FindPiratePath.class);

    public HttpResponse<String> findPiratePathUsing_StartPositionXY_and_TargetPositionXY(
             int startXPosition,
             int startYPosition,
             int targetXPosition,
             int targetYPosition
    ) {

        int map_x_w = 4, map_x_h = 4;

        int[] trueStartPosition = getTruePoint_A_B(startXPosition, startYPosition);
        int[] trueTargetPosition = getTruePoint_A_B(targetXPosition, targetYPosition);

        LOGGER.info("Given Targets from : { {},{} } to : { {},{} }", startXPosition, startYPosition, targetXPosition, targetYPosition);
        LOGGER.info("Move from array position : {} to : {}", trueStartPosition, trueTargetPosition);

        int [] x_y_up = {trueStartPosition[0]-1, trueStartPosition[1]};
        int [] x_y_right = {trueStartPosition[0], trueStartPosition[1]+1};

        //if (trueStartPosition : [3,3] -> notFound
        //if (trueStartPosition : x = 0  And trueTargetPosition :
        //start row = 0, ie. x=0 :

        if(  okToMoveBool(x_y_right, map_x_h, map_x_w) | okToMoveBool(x_y_up, map_x_h, map_x_w) ){

            if(movedUp(trueStartPosition, x_y_up) | movedRight(trueStartPosition, x_y_right)) {

                niceMoveMessage(trueStartPosition, trueTargetPosition);

                if (Arrays.equals(trueStartPosition, trueTargetPosition) | movedNowhere(trueStartPosition, trueTargetPosition)) {
                    System.out.println("Travelling is not needed. Start == Target");

                    return HttpResponse.notFound();
                } else {

                    if(null != SaveGameMap.map_x) {

                        int totalPathSum = move_from_start_to_target(SaveGameMap.map_x, trueStartPosition, trueTargetPosition);

                        System.out.println("Travelling from : " + Arrays.toString(trueStartPosition) + ", to : " + Arrays.toString(trueTargetPosition));
                        System.out.println("Path travelled : " + Arrays.deepToString(path.toArray()));
                        System.out.println("Game Path travelled : " + Arrays.deepToString(game_path.toArray()));
                        System.out.println("Total Path Value : " + Integer.toString(totalPathSum));

                        //if(game_path.contains(getGamePoint_A_B(trueTargetPosition[0], trueTargetPosition[1]))) {
                        if(
                                Arrays.equals(path.get(0), trueStartPosition)
                                && Arrays.equals(path.get(path.size()-1), trueTargetPosition)
                        ){

                            /*GameMapResponseDto gameMapResponseDto = GameMapResponseDto.builder()
                                    .path(game_path)
                                    .coins(totalPathSum)
                                    .build();*/

                            //return HttpResponse.ok(gameMapResponseDto.toString());
                            return HttpResponse.ok("{\"path\" : " + Arrays.deepToString(game_path.toArray()) + ", \"coins\" : " + totalPathSum + "}");

                        }else{
                            LOGGER.warn("Destination is not in the paths. Opposite directional scenario.");
                            return HttpResponse.notFound();
                        }

                    }else{

                        LOGGER.warn("Please submit a map via the POST endpoint.");
                        return HttpResponse.ok("Please submit a map via the POST endpoint.");
                        //return HttpResponse.notFound();
                    }
                }

            }else {
                LOGGER.info("### pirate must move up or move right only only ###");

                warnPlayerAboutWrongDirection(trueStartPosition, trueTargetPosition);

                return HttpResponse.notFound();
            }
        }else {
        LOGGER.info("### pirate has attempted to move in an illegal direction ###");

        warnPlayerAboutWrongDirection(trueStartPosition, trueTargetPosition);

        return HttpResponse.notFound();
    }

    }

    public static void niceMoveMessage(int [] trueStartPosition, int [] trueTargetPosition){
        if(movedUp(trueStartPosition, trueTargetPosition)){
            LOGGER.info("Nice move upwards :)");
        }

        if(movedRight(trueStartPosition, trueTargetPosition)){
            LOGGER.info("Nice move right :)");
        }
    }

    public static void warnPlayerAboutWrongDirection(int [] trueStartPosition, int [] trueTargetPosition){

        if(movedNowhere(trueStartPosition, trueTargetPosition)){
            LOGGER.warn("Zero movement is not allowed");
        }

        if(movedLeft(trueStartPosition, trueTargetPosition)){
            LOGGER.warn("Left movement not allowed");
        }

        if(movedDown(trueStartPosition, trueTargetPosition)){
            LOGGER.warn("Down movement not allowed");
        }

        if(movedLeft_DiagonalUp(trueStartPosition, trueTargetPosition)){
            LOGGER.warn("Left Diagonal Up movement not allowed");
        }

        if(movedLeft_DiagonalDown(trueStartPosition, trueTargetPosition)){
            LOGGER.warn("Left Diagonal Down movement not allowed");
        }

        if(movedRight_DiagonalUp(trueStartPosition, trueTargetPosition)){
            LOGGER.warn("Right Diagonal Up movement not allowed");
        }

        if(movedRight_DiagonalDown(trueStartPosition, trueTargetPosition)){
            LOGGER.warn("Right Diagonal Down movement not allowed");
        }
    }

    public static boolean movedNowhere(int [] trueStartPosition, int [] trueTargetPosition){
        return ( trueStartPosition[0] - trueTargetPosition[0] == 0
                &&  trueStartPosition[1] - trueTargetPosition[1] == 0
        );
    }

    public static boolean movedUp(int [] trueStartPosition, int [] trueTargetPosition){
        return ( trueStartPosition[0] - trueTargetPosition[0]== 1
                && trueStartPosition[1] - trueTargetPosition[1] == 0
        );
    }

    public static boolean movedRight(int [] trueStartPosition, int [] trueTargetPosition){
        return ( trueStartPosition[0] - trueTargetPosition[0] == 0
                && trueStartPosition[1] - trueTargetPosition[1] == -1
        );
    }

    public static boolean movedDown(int [] trueStartPosition, int [] trueTargetPosition){
        return ( trueStartPosition[0] - trueTargetPosition[0]== -1
                && trueStartPosition[1] - trueTargetPosition[1] == 0
        );
    }

    public static boolean movedLeft(int [] trueStartPosition, int [] trueTargetPosition){
        return ( trueStartPosition[0] - trueTargetPosition[0] == 0
                && trueStartPosition[1] - trueTargetPosition[1] == 1
        );
    }

    public static boolean movedRight_DiagonalUp(int [] trueStartPosition, int [] trueTargetPosition){
        return ( trueStartPosition[0] - trueTargetPosition[0] == 1
                &&  trueStartPosition[1] - trueTargetPosition[1] == -1
        );
    }

    public static boolean movedRight_DiagonalDown(int [] trueStartPosition, int [] trueTargetPosition){
        return ( trueStartPosition[0] - trueTargetPosition[0] == -1
                && trueStartPosition[1] - trueTargetPosition[1] == -1
        );
    }

    public static boolean movedLeft_DiagonalUp(int [] trueStartPosition, int [] trueTargetPosition){
        return( trueStartPosition[0] - trueTargetPosition[0] == 1
                && trueStartPosition[1] - trueTargetPosition[1] == 1
        );
    }

    public static boolean movedLeft_DiagonalDown(int [] trueStartPosition, int [] trueTargetPosition){
        return ( trueStartPosition[0] - trueTargetPosition[0] == -1
                && trueStartPosition[1] - trueTargetPosition[1] == 1
        );
    }

    public static int [] getTruePoint_A_B(int givenTarget_x, int givenTarget_y) {

        int arr_size = 4;

        int [] true_position = new int [2];

        //P(x,y) -> arr[a][b]

        //b = x
        int b = givenTarget_x;

        //a = inverseOf | y - 3 |

        //a = -(y - 3), for y > 0;   a = (3 - y), for y > 0

        //a = (y + 3), for y < 0;

        int a;
        if(givenTarget_y > 0) {
            a = (arr_size-1) - givenTarget_y;
        }else {
            a = givenTarget_y + (arr_size-1);
        }

        true_position[0] = a;
        true_position[1] = b;

        return true_position;

    }


    public static int getTruePoint_A(int [] givenTarget_x_y) {
        int [] true_position = getTruePoint_A_B(givenTarget_x_y[0], givenTarget_x_y[1]);
        return true_position[0];
    }

    public static int getTruePoint_B(int [] givenTarget_x_y) {
        int [] true_position = getTruePoint_A_B(givenTarget_x_y[0], givenTarget_x_y[1]);
        return true_position[1];
    }

    public static int [] getGamePoint_A_B(int givenTrue_x, int givenTrue_y) {

        int arr_size = 4;

        int [] game_position = new int [2];

        //P(x,y) -> arr[a][b]

        //a = y
        int a = givenTrue_y;

        //a = inverseOf | y - 3 |

        //a = -(y - 3), for y > 0;   a = (3 - y), for y > 0

        //a = (y + 3), for y < 0;

        int b;
        if(givenTrue_x > 0) {
            b =  (arr_size-1) - givenTrue_x;
        }else {
            b = givenTrue_x + (arr_size-1);
        }

        game_position[0] = a;
        game_position[1] = b;

        return game_position;

    }

    //# The following methods are the inverse  or mirror (reflection) movement by the player
    public static boolean movedNowhere_Hi(int [] trueStartPosition, int [] trueTargetPosition){
        return ( (getTruePoint_A(trueStartPosition) - getTruePoint_A(trueTargetPosition) == 0 )
                && ( getTruePoint_B(trueStartPosition) - getTruePoint_B(trueTargetPosition) == 0 )
        );
    }

    public static boolean movedUp_Hi(int [] trueStartPosition, int [] trueTargetPosition){
        return ( getTruePoint_A(trueStartPosition) - getTruePoint_A(trueTargetPosition) == 1
                && getTruePoint_B(trueStartPosition) - getTruePoint_B(trueTargetPosition) == 0
        );
    }

    public static boolean movedRight_Hi(int [] trueStartPosition, int [] trueTargetPosition){
        return ( getTruePoint_A(trueStartPosition) - getTruePoint_A(trueTargetPosition) == 0
                && getTruePoint_B(trueStartPosition) - getTruePoint_B(trueTargetPosition) == -1
        );
    }

    public static boolean movedRight_DiagonalUp_Hi(int [] trueStartPosition, int [] trueTargetPosition){
        return ( getTruePoint_A(trueStartPosition) - getTruePoint_A(trueTargetPosition) == 1
                &&  getTruePoint_B(trueStartPosition) - getTruePoint_B(trueTargetPosition) == -1
        );
    }

    public static boolean movedRight_DiagonalDown_Hi(int [] trueStartPosition, int [] trueTargetPosition){
        return ( getTruePoint_A(trueStartPosition) - getTruePoint_A(trueTargetPosition) == -1
                && getTruePoint_B(trueStartPosition) - getTruePoint_B(trueTargetPosition) == -1
        );
    }

    public static boolean movedLeft_DiagonalUp_Hi(int [] trueStartPosition, int [] trueTargetPosition){
        return( getTruePoint_A(trueStartPosition) - getTruePoint_A(trueTargetPosition) == 1
                && getTruePoint_B(trueStartPosition) - getTruePoint_B(trueTargetPosition) == 1
        );
    }

    public static boolean movedLeft_DiagonalDown_Hi(int [] trueStartPosition, int [] trueTargetPosition){
        return ( getTruePoint_A(trueStartPosition) - getTruePoint_A(trueTargetPosition) == -1
                && getTruePoint_B(trueStartPosition) - getTruePoint_B(trueTargetPosition) == 1
        );
    }

    public void totalMoves_from_start_to_target(int [] trueStartPosition, int [] trueTargetPosition){

        int arr_size_x = 4;
        int arr_size_y = 4;

        int [][] arr = new int [arr_size_x][arr_size_y];

        int totalNumOfRightMovements = 0;

        int x0 = trueStartPosition[0];
        int y0 = trueStartPosition[1];

        int x1 = trueTargetPosition[0];
        int y1 = trueTargetPosition[1];

        LOGGER.info("Possible moves are : ");

        boolean destination_reached = false;

        while(!destination_reached){
            if(x0 == x1 && y0 == y1){
                destination_reached = true;
                System.out.println("We have arrived at the destination 0");
            }else{
                //move right
                y0++;
                if(x0 == x1 && y0 == y1){
                    totalNumOfRightMovements++;
                    destination_reached = true;
                    System.out.println("We have arrived at the destination 1");
                }else{
                    //move up
                    x0--;
                    if(x0 == x1 && y0 == y1){
                        destination_reached = true;
                        System.out.println("We have arrived at the destination 2");
                    }
                }
            }
        }
        System.out.println("Stop travelling. We have arrived already.");

        //int box = 1;//load , injection,
        for(int x=0; x<arr.length; x++){
            for(int y=0; y<arr.length; y++){



            }
        }

    }

    static List<int []> path, game_path;
    public static int move_from_start_to_target(int [][] map_x, int [] trueStartPosition, int [] trueTargetPosition){

        int rows = map_x.length;

        if(rows!=0){

            int cols = map_x[0].length;

            //get the x and y values of from_here
            int x = trueStartPosition[0];
            int y = trueStartPosition[1];

            //get the quantitative value at x and y
            int q_val = map_x[x][y];

            //note that, the starting point is actually the values inside of from_here, ie. x and y

            //Start a list to store the selected paths
            path = new ArrayList<int []>();
            game_path = new ArrayList<int []>();

            //add the source array, ie. the from_here array
            path.add(trueStartPosition);
            game_path.add(getGamePoint_A_B(trueStartPosition[0], trueStartPosition[1]));

            System.out.println("path X : "+Arrays.deepToString(path.toArray()));

            if(Arrays.equals(trueStartPosition, trueTargetPosition)) {
                return q_val;
            }else {
                //int q_val_right_or_up = q_val;
                return move_addPath_n_sumVal(map_x, trueStartPosition, trueTargetPosition, rows, cols, q_val/*, q_val_right_or_up*/);
            }
        }else {
            return 0;
        }

    }

    private static int move_addPath_n_sumVal(int[][] map_x, int[] from_here, int[] to_there, int rows, int cols, int q_val/*, int q_val_right_or_up*/) {

        boolean arrived=false;
        while(!arrived){

            int [] x_y_up = {from_here[0]-1, from_here[1]};
            int q_val_up = 0;
            //q_val_up += q_val_right_or_up;

            int [] x_y_right = {from_here[0], from_here[1]+1};
            int q_val_right = 0;
            //q_val_right += q_val_right_or_up;

            boolean okTM_R = okToMoveBool(x_y_right, rows, cols);
            boolean okTM_Up = okToMoveBool(x_y_up, rows, cols);

            if(okTM_Up) {
                q_val_up = map_x[x_y_up[0]][ x_y_up[1]];
                System.out.println("ok to move up");
            }

            if(okTM_R) {
                q_val_right = map_x[x_y_right[0]][x_y_right[1]];
                System.out.println("ok to move right");
            }

            if(okTM_Up | okTM_R) {

                if(q_val_up > q_val_right){//continue towards up

                    System.out.println("move ^ Up");

                    //store this path
                    path.add(x_y_up);
                    game_path.add(getGamePoint_A_B(x_y_up[0], x_y_up[1]));
                    System.out.println("path Up : "+Arrays.deepToString(path.toArray()));

                    q_val += q_val_up;

                    //check if we have arrived at our destination
                    if(Arrays.equals(x_y_up, to_there)) {//we have arrived stop all processes
                        arrived = true;
                        return q_val;
                    }else{//use x_y_up as the next source to move from
                        return move_addPath_n_sumVal(map_x, x_y_up, to_there, rows, cols, q_val);
                    }

                }else{//continue towards right

                    if(q_val_up < q_val_right){//continue towards up

                        System.out.println("move > Right");

                        //store this path
                        path.add(x_y_right);
                        game_path.add(getGamePoint_A_B(x_y_right[0], x_y_right[1]));
                        System.out.println("path R : "+Arrays.deepToString(path.toArray()));

                        q_val += q_val_right;

                        //use x_y_right as the next source to move from
                        //check if we have arrived at our destination
                        if(Arrays.equals(x_y_right, to_there)) {//we have arrived stop all processes
                            arrived = true;
                            return q_val;
                        }else{//use x_y_right as the next source to move from
                            return move_addPath_n_sumVal(map_x, x_y_right, to_there, rows, cols, q_val);
                        }

                    }else{//they are equal : what next ?

                        if(okTM_R){
                            //store this path
                            path.add(x_y_right);
                            game_path.add(getGamePoint_A_B(x_y_right[0], x_y_right[1]));
                            System.out.println("path R* : "+Arrays.deepToString(path.toArray()));

                            q_val += q_val_right;

                            //use x_y_right as the next source to move from
                            //check if we have arrived at our destination
                            if(Arrays.equals(x_y_right, to_there)) {//we have arrived stop all processes
                                arrived = true;
                                return q_val;
                            }else{//use x_y_right as the next source to move from
                                return move_addPath_n_sumVal(map_x, x_y_right, to_there, rows, cols, q_val);
                            }
                        }

                        if(okTM_Up){
                            //store this path
                            path.add(x_y_up);
                            game_path.add(getGamePoint_A_B(x_y_up[0], x_y_up[1]));
                            System.out.println("path Up* : "+Arrays.deepToString(path.toArray()));

                            q_val += q_val_up;

                            //use x_y_right as the next source to move from
                            //check if we have arrived at our destination
                            if(Arrays.equals(x_y_up, to_there)) {//we have arrived stop all processes
                                arrived = true;
                                return q_val;
                            }else{//use x_y_right as the next source to move from
                                return move_addPath_n_sumVal(map_x, x_y_up, to_there, rows, cols, q_val);
                            }
                        }

                    }

                }

            }else {
                return q_val;
            }

        }//END OF while
        return q_val;
    }

    public static boolean okToMoveBool (int [] xy_arr, int rows, int cols) {
        return xy_arr[0] >= 0
                && xy_arr[1] >= 0
                && xy_arr[0] < cols
                && xy_arr[1] < rows;
    }

    /*ArrayList<int []> pc = new ArrayList<int[]>();

        for(int x = 0; x< 7; x++){

                int[] path_child = new int[2];

                path_child[0] = startXPosition;
                path_child[1] = startYPosition;

                pc.add(x, path_child);

                startXPosition++;
                startYPosition++;

        }*/

}
