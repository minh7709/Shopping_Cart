package ecomerce.dto.response;

import lombok.Builder;
import lombok.Data;
import ecomerce.entity.CharacterEntity;
@Data
@Builder
public class CharacterResponse {
    private Integer id;
    private String name;
    public static CharacterResponse toDTO(CharacterEntity character) {
        return CharacterResponse.builder()
                .id(character.getId())
                .name(character.getName())
                .build();
    }
    public static CharacterEntity toEntity(CharacterResponse characterResponse) {
        CharacterEntity character = new CharacterEntity();
        character.setId(characterResponse.getId());
        character.setName(characterResponse.getName());
        return character;
    }
}

