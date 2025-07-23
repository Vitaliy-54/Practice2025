package RestApiNews.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class CategoriesDto {

    private Long id;

    @NotBlank(message = "Название категории не может быть пустым")
    @Size(max = 100, message = "Название категории должно содержать не более 100 символов")
    private String name;

    // Геттеры и сеттеры

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
