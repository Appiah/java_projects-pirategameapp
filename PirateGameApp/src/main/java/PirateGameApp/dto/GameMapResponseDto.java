package PirateGameApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.netty.handler.codec.http.FullHttpResponse;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@Builder
@Value
@Getter
@Setter
public class GameMapResponseDto {

    @JsonProperty("path") private List<int []> path;

    @JsonProperty("coins") int coins;


}
