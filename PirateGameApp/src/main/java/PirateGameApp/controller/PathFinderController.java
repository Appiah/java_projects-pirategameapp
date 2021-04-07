package PirateGameApp.controller;

import PirateGameApp.service.FindPiratePath;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

@Controller("/findPath")
public class PathFinderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PathFinderController.class);

    private FindPiratePath findPiratePath;

    @Inject
    public void setBasicInjections(
        FindPiratePath findPiratePath
    ){
        this.findPiratePath = findPiratePath;
    }

    @Get(uri="/", produces="text/json")
    public HttpResponse<String> findPiratePathUsing_StartPositions_XY_TargetPositions_XY_Get(
            int startXPosition,
            int startYPosition,
            int targetXPosition,
            int targetYPosition
    ) {

        LOGGER.info(
                "Parameters : \n" +
                 "startXPosition : {}, " +
                "startYPosition : {}, " +
                "targetXPosition : {}, " +
                "targetYPosition : {}",

                startXPosition,
                startYPosition,
                targetXPosition,
                targetYPosition
        );

        return findPiratePath.findPiratePathUsing_StartPositionXY_and_TargetPositionXY(
                startXPosition,
                startYPosition,
                targetXPosition,
                targetYPosition
        );

    }



}