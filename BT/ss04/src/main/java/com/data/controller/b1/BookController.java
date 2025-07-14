package com.data.controller.b1;

import com.data.entity.b1.Book;
import com.data.service.b1.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public String books(Model model,
                        @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                        @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                        @RequestParam(value = "sort", required = false, defaultValue = "id") String sort,
                        @RequestParam(value = "direction", required = false, defaultValue = "desc") String direction,
                        @RequestParam(value = "title", required = false, defaultValue = "") String title ){
        model.addAttribute("books", bookService.getBooks(page, size, sort, direction, title));
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("sort", sort);
        model.addAttribute("direction", direction);
        model.addAttribute("title", title);
        model.addAttribute("totalPages", bookService.getBooks(page, size, sort, direction, title).getTotalPages());
        return "b1/books";
    }

    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "b1/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book, Model model) {
        if (bookService.saveBook(book)) {
            return "redirect:/book";
        } else {
            model.addAttribute("error", "Failed to add book");
            return "b1/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            model.addAttribute("book", book);
            return "b1/edit";
        } else {
            return "redirect:/book";
        }
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, @ModelAttribute Book book, Model model) {
        book.setId(id);
        if (bookService.updateBook(book)) {
            return "redirect:/book";
        } else {
            model.addAttribute("error", "Failed to update book");
            return "b1/edit";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            if (bookService.deleteBook(book)) {
                return "redirect:/book";
            } else {
                model.addAttribute("error", "Failed to delete book");
            }
        } else {
            model.addAttribute("error", "Book not found");
        }
        return "redirect:/book";
    }
}
