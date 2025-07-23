package RestApiNews.dto;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

public class NewsDto {
    private Long id;

    @NotBlank(message = "Заголовок не может быть пустым")
    @Size(min = 5, max = 255, message = "Заголовок должен содержать от 5 до 255 символов")
    private String title;

    @NotBlank(message = "Содержимое не может быть пустым")
    @Size(min = 10, message = "Содержимое должно содержать минимум 10 символов")
    private String content;

    @NotNull(message = "Дата публикации обязательна")
    @PastOrPresent(message = "Дата публикации не может быть в будущем")
    private LocalDate publishDate;  // Изменили на LocalDate

    private List<String> categories;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}