package org.example.controller;

import org.example.model.Author;
import org.example.model.Book;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;


@Controller
public class BookController {

    @QueryMapping
    public Book bookById(@Argument("id") String id) {
        return Book.getById(id);
    }

    @SchemaMapping(field = "author")
    public Author getAuthor(Book book) {
        return Author.getById(book.authorId());
    }

    @MutationMapping
    public Book addBook(
            @Argument("id") String id,
            @Argument("name") String name,
            @Argument("pageCount") int pageCount,
            @Argument("authorId") String authorId
    ) {
        // Создаем новую книгу и сохраняем её
        Book newBook = new Book(id, name, pageCount, authorId);
        Book.save(newBook); // Предполагается, что есть метод save() для сохранения книги
        return newBook;
    }
}
