package RestApiNews.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class NewsCategoriesDto {

    private Long id;

    @NotNull(message = "Новость обязательна")
    @Valid
    private NewsIdWrapper news;

    @NotNull(message = "Категория обязательна")
    @Valid
    private CategoriesIdWrapper categories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NewsIdWrapper getNews() {
        return news;
    }

    public void setNews(NewsIdWrapper news) {
        this.news = news;
    }

    public CategoriesIdWrapper getCategories() {
        return categories;
    }

    public void setCategories(CategoriesIdWrapper categories) {
        this.categories = categories;
    }

    // Вложенный класс для news
    public static class NewsIdWrapper {

        @NotNull(message = "ID новости обязателен")
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }

    // Вложенный класс для categories
    public static class CategoriesIdWrapper {

        @NotNull(message = "ID категории обязателен")
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}
