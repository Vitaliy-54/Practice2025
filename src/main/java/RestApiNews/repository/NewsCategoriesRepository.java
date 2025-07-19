package RestApiNews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import RestApiNews.entity.NewsCategories;
import RestApiNews.entity.News;
import RestApiNews.entity.Categories;

public interface NewsCategoriesRepository extends JpaRepository<NewsCategories, Long> {

    boolean existsByNewsAndCategories(News news, Categories categories);

}

