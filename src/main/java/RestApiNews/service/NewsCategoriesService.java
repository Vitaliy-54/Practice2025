package RestApiNews.service;

import RestApiNews.entity.News;
import RestApiNews.entity.Categories;
import RestApiNews.entity.NewsCategories;

public interface NewsCategoriesService extends Service<NewsCategories> {
    boolean existsByNewsAndCategories(News news, Categories categories);
}
