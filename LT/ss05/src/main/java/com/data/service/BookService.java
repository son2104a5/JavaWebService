package com.data.service;

import com.data.model.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getBooks();
    Book getBookById(Long id);
    Book insertBook(Book book);
    Book updateBook(Long id, Book book);
    boolean deleteBook(Long id);

}
