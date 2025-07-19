package RestApiNews.dto;

import java.sql.Date;
import java.util.List;

public class NewsDto {
    private Long id;
    private String title;
    private String content;
    private Date publishDate;
    private String source;
    private String link;
    private List<String> categories; // добавили категории

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getPublishDate() { return publishDate; }
    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }

    public List<String> getCategories() { return categories; }
    public void setCategories(List<String> categories) { this.categories = categories; }
}
