package RestApiNews.dto;

public class NewsCategoriesDto {

    private Long id;
    private NewsIdWrapper news;
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
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}
