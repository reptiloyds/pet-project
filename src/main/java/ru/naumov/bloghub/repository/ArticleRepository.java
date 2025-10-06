package ru.naumov.bloghub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.naumov.bloghub.model.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
