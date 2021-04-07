package PirateGameApp.service;

import PirateGameApp.dto.GameMapRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.Arrays;

@Singleton
public class SaveGameMap {

    private static final Logger LOGGER = LoggerFactory.getLogger(SaveGameMap.class);

    public GameMapRequestDto saveGameMapForUse(/*@Valid*/ GameMapRequestDto gameMapRequestDto){

        //String type = GameMapRequestDto.GameMap

        LOGGER.info("GameMapRequestDto : {}", gameMapRequestDto.toString());

        //Get the first item :
        String type_at_pos_0 = gameMapRequestDto.getGame_map_typeAndAmountList().get(0).get(0).getType();
        int coin_value_at_pos_0 = gameMapRequestDto.getGame_map_typeAndAmountList().get(0).get(0).getAmount();

        LOGGER.info("The first -> type : {}, amount : {}", type_at_pos_0, coin_value_at_pos_0);

        GameMapRequestDto gameMapRequestDto_X = GameMapRequestDto.builder()
                .game_map_typeAndAmountList(
                        gameMapRequestDto.getGame_map_typeAndAmountList()
                )
                .build();

        String loadCoinsAndDoGridDisplay_str = loadCoinsAndDoGridDisplay( gameMapRequestDto_X );
        LOGGER.info("loadCoinsAndDoGridDisplay : {}", loadCoinsAndDoGridDisplay_str);

        return gameMapRequestDto_X;

    }

    public static int [][] map_x;
    public String loadCoinsAndDoGridDisplay(GameMapRequestDto gameMapRequestDto_X){
        int arr_size_x = 4;
        int arr_size_y = 4;

        map_x = new int [arr_size_x][arr_size_y];

        //int box = 1;//load , injection,
        for(int x=0; x<map_x.length; x++){
            for(int y=0; y<map_x.length; y++){
                int node_val = gameMapRequestDto_X.getGame_map_typeAndAmountList().get(x).get(y).getAmount();
                //arr[x][y] = box;
                map_x[x][y] = node_val;
                String output = Integer.toString(map_x[x][y]);

                if( y!=0 && y%(arr_size_y-1)==0 ){
                    output = output + "\n";
                }else{
                    output = output + ", ";
                }
                System.out.print(output);
                //box++;
            }
        }

        System.out.println("Live array for action : "+ Arrays.deepToString(map_x));

        return Arrays.toString(map_x);

    }

}
