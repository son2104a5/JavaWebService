package com.data.service.b1;

import com.data.entity.b1.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Page<Book> getBooks(int page, int size, String sort, String direction, String title);
    Book getBookById(Long id);
    boolean deleteBook(Book book);
    boolean saveBook(Book book);
    boolean updateBook(Book book);
}
