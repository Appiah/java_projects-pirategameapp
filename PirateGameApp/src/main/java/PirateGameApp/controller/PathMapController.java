package PirateGameApp.controller;

import PirateGameApp.service.SaveGameMap;
import PirateGameApp.dto.GameMapRequestDto;
import io.micronaut.http.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Controller("/map")
public class PathMapController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PathMapController.class);

    private SaveGameMap saveGameMap;

    @Inject
    public void setBasicMapInjection(
            SaveGameMap saveGameMap
    ){
        this.saveGameMap = saveGameMap;
    }

    @Post
    public GameMapRequestDto saveGameMap_Post(
            @Body GameMapRequestDto gameMapRequestDto
    ){

        LOGGER.info("The JSON Data Passed to .../map : "+gameMapRequestDto.toString());

        return saveGameMap.saveGameMapForUse(gameMapRequestDto);

    }


}