package com.data.service;

import com.data.model.entity.Book;
import com.data.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return null;
    }

    @Override
    public Book insertBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return bookRepository.save(book);
    }

    @Override
    public boolean deleteBook(Long id) {
        bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.deleteById(id);
        return true;
    }
}
