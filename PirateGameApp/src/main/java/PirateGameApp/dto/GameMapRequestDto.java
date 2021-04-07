package PirateGameApp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.*;

@Data
@Builder
@Value
@Getter
public class GameMapRequestDto {

    /*
    [
        [{"type": "coin", "amount": 1}, {"type": "coin", "amount": 2}, {"type": "coin", "amount": 3}, {"type": "coin", "amount": 4}],

        [{"type": "coin", "amount": 5}, {"type": "coin", "amount": 6}, {"type": "coin", "amount": 7}, {"type": "coin", "amount": 8}],

        [{"type": "coin", "amount": 9}, {"type": "coin", "amount": 10}, {"type": "coin", "amount": 11}, {"type": "coin", "amount": 12}],

        [{"type": "coin", "amount": 13}, {"type": "coin", "amount": 14}, {"type": "coin", "amount": 15}, {"type": "coin", "amount": 16}]
    ]
    */

    @JsonProperty("game_map")
    List<List<TypeAndAmount>> game_map_typeAndAmountList;

    @JsonProperty("typeAndAmount")
    private TypeAndAmount typeAndAmount;

    @Data
    public static class TypeAndAmount {

        //@ValidEnumCode(targetEnum = NodeType.class, message = "{ValidEnumCode.NodeType}")
        @JsonProperty("type") private String type;

        @JsonProperty("amount") private int amount;

    }

}
