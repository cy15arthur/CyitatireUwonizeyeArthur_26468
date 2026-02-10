package auca.ac.rw.library_book_management;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/books")
public class BookController {
  List<Book> books =new ArrayList<>();
  public BookController() {
  books.add(new Book("Author 1", 1L, "ISBN001", 2020, "Math Book"));
  books.add(new Book("Author 2", 2L, "ISBN002", 2021, "Physics Book"));   
  books.add(new Book("Author 3", 3L, "ISBN003", 2019, "Chemistry Book"));  
  
  }
 @GetMapping
  public List<Book> getAllBooks() {
    return books;
  }
  @GetMapping("/{id}")
  public Book getbookById(@PathVariable long id) {
    for (Book book: books){
      if(id==book.getId()){
       return book;
      }
    }
   return null;
  }
@GetMapping("/search")
public List<Book> searchBookByTitle(@RequestParam String title){
  List<Book> booksfound=new ArrayList<>();
  for(Book book: books){
    if(book.getTitle().toLowerCase().contains(title.toLowerCase())){
      booksfound.add(book);
    }
  }
  return booksfound;
}
@PostMapping
public Book addBook(@RequestBody Book newBook) {
    books.add(newBook);
    return newBook;
}

@DeleteMapping("/{id}")
public String deleteBook(@PathVariable Long id) {
    Book bookToDelete = null;

   
    for (Book book : books) {
       
        if (book.getId().equals(id)) { 
            bookToDelete = book;
            break; 
        }
    }

    if (bookToDelete != null) {
        books.remove(bookToDelete);
        return "Book with ID " + id + " has been deleted";
    } else {
        return "Book not found";
    }
}
}