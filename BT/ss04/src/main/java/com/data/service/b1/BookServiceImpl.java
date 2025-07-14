package com.data.service.b1;

import com.data.entity.b1.Book;
import com.data.repository.b1.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Page<Book> getBooks(int page, int size, String sort, String direction, String title) {
        Pageable pageable = PageRequest.of(page, size,
                direction.equalsIgnoreCase("asc") ? Sort.by(sort).ascending() :
                        Sort.by(sort).descending());
        if (title != null && !title.isEmpty()) {
            return bookRepository.findByTitleContainingIgnoreCase(title, pageable);
        }
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteBook(Book book) {
        try {
            bookRepository.delete(book);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean saveBook(Book book) {
        try {
            bookRepository.save(book);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateBook(Book book) {
        try {
            if (bookRepository.existsById(book.getId())) {
                bookRepository.save(book);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
