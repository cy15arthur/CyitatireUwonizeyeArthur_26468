package auca.ac.rw.library_book_management;

public class Book {
  private Long id;
  private String title;
  private String author;
  private String isbn;
  private int publicationYear;

  public Book() {
  }

    public Book(String author, Long id, String isbn, int publicationYear, String title) {
        this.author = author;
        this.id = id;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.title = title;
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

    public String getAuthor() {
      return author;
    }

    public void setAuthor(String author) {
      this.author = author;
    }

    public String getIsbn() {
      return isbn;
    }

    public void setIsbn(String isbn) {
      this.isbn = isbn;
    }

    public int getPublicationYear() {
      return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
      this.publicationYear = publicationYear;
    }
  
  
}
