package com.vladmihalcea.book.hpjp.hibernate.equality;

import com.vladmihalcea.book.hpjp.hibernate.identifier.Identifiable;
import org.hibernate.annotations.NaturalId;
import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author Vlad Mihalcea
 */
public class NaturalIdEqualityTest extends AbstractEqualityCheckTest {

    @Override
    protected Class<?>[] entities() {
        return new Class[] {
            Post.class
        };
    }

    @Test
    public void testEquality() {
        Post post = new Post();
        post.setTitle("High-PerformanceJava Persistence");
        post.setIsbn("123-456-7890");

        assertEqualityConstraints(Post.class, post);
    }

    @Entity(name = "Post")
    @Table(name = "post")
    public static class Post implements Identifiable<Long> {

        @Id
        @GeneratedValue
        private Long id;

        private String title;

        @NaturalId
        private String isbn;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Post)) return false;
            Post post = (Post) o;
            return Objects.equals(getIsbn(), post.getIsbn());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getIsbn());
        }

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

        public String getIsbn() {
            return isbn;
        }

        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }
    }
}
