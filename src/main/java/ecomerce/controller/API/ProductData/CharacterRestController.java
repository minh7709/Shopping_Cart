package ecomerce.controller.API.ProductData;
import ecomerce.dto.response.CharacterResponse;
import ecomerce.entity.CharacterEntity;
import ecomerce.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ecomerce.dto.response.ApiResponse;
import java.util.List;

@RestController
@RequestMapping("/api/product-data")
@RequiredArgsConstructor

public class CharacterRestController {
    private final CharacterRepository characterRepository;
    @GetMapping("/characters")
    public ApiResponse<List<CharacterResponse>> getAllCharacters() {
        List<CharacterEntity> characters = characterRepository.findAll();
        List<CharacterResponse> response = characters.stream().map(CharacterResponse::toDTO).toList();
        return ApiResponse.success(response);
    }
}
