package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author ivan = new Author("Ivan", "Ivanov");
        Book book = new Book("Java Essentials", "1247754346");

        ivan.getBooks().add(book);
        book.getAuthors().add(ivan);

        authorRepository.save(ivan);
        bookRepository.save(book);

        Author peter = new Author("Peter", "Petrov");
        Book bookOfPeter = new Book("Spring book", "983544346");

        peter.getBooks().add(bookOfPeter);
        bookOfPeter.getAuthors().add(peter);

        authorRepository.save(peter);
        bookRepository.save(bookOfPeter);

        Publisher publisher = new Publisher();

        publisher.setName("georgi");
        publisher.setAddressLine("Sofiq");


        book.setPublisher(publisher);
        publisher.getBooks().add(book);

        bookOfPeter.setPublisher(publisher);
        publisher.getBooks().add(bookOfPeter);
        publisherRepository.save(publisher);

        System.out.println("Number of book: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
        System.out.println("Publisher: " + publisher.getName());
        System.out.println("Publisher Number of books: " + publisher.getBooks().size());

    }
}
