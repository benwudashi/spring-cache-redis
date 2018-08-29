package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SimpleBookRepository implements BookRepository {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    @Override
    @Cacheable("books + #isbn")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        logger.info("getByIsbn...................");
        return new Book(isbn, "Some book");
    }

    // Don't do this at home
    private void simulateSlowService() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
