package constsw.grupoum.courses;

import java.io.File;
import java.util.Collection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import constsw.grupoum.courses.adapter.entity.mongo.BookMongo;
import constsw.grupoum.courses.adapter.repository.mongo.BookRepositoryMongo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

  private final BookRepositoryMongo bookRepositoryMongo;

  private final ObjectMapper objectMapper;

  @Override
  public void run(String... args) throws Exception {
    try {

      File books = new File("src/main/resources/books.json");
      if (books.isFile()) {

        log.info("Começou a popular");

        objectMapper.readValue(new File("src/main/resources/books.json"),
            new TypeReference<Collection<BookMongo>>() {
            })
            .stream()
            .forEach(book -> {
              if (!bookRepositoryMongo.findById(book.getIsbn13()).isPresent()) {
                bookRepositoryMongo.insert(book);
              }
            });

        log.info("Terminou de popular");

      }

    } catch (Exception e) {
      log.info("Erro ao popular o banco de dados");
    }
  }

}
