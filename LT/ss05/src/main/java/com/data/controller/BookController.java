package com.data.controller;

import com.data.model.dto.response.DataResponse;
import com.data.model.entity.Book;
import com.data.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Book>>> getBooks() {
        return new ResponseEntity<>(new DataResponse<>(bookService.getBooks(), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<Book>> getBookById(@PathVariable Long id) {
        return new ResponseEntity<>(new DataResponse<>(bookService.getBookById(id), HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResponse<Book>> insertBook(Book book) {
        return new ResponseEntity<>(new DataResponse<>(bookService.insertBook(book), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DataResponse<Book>> updateBook(@RequestParam Long id, @RequestBody Book book) {
        return new ResponseEntity<>(new DataResponse<>(bookService.updateBook(id, book), HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<DataResponse<Boolean>> deleteBook(@RequestParam Long id) {
        return new ResponseEntity<>(new DataResponse<>(bookService.deleteBook(id), HttpStatus.NO_CONTENT), HttpStatus.NO_CONTENT);
    }
}
