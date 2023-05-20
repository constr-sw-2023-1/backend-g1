package constsw.grupoum.courses;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import constsw.grupoum.courses.adapter.entity.mongo.BookMongo;
import constsw.grupoum.courses.adapter.repository.mongo.BookRepositoryMongo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Component
@Log4j2
public class CommandLineAppStartupRunner implements CommandLineRunner{

  private final BookRepositoryMongo bookRepositoryMongo;
  private final ObjectMapper objectMapper;

  @Override
  public void run(String... args) throws Exception {
    try {
      log.info("Começou a popular");

      File file = new File("src/main/resources/books.json");

      JsonNode jsonArray = objectMapper.readValue(file, JsonNode.class);
      String jsonArrayAsString = objectMapper.writeValueAsString(jsonArray);

      List<BookMongo> booksList = objectMapper.readValue(jsonArrayAsString, new TypeReference<List<BookMongo>>() {});

      for(BookMongo book : booksList){
        Optional<BookMongo> result = bookRepositoryMongo.findById(book.getIsbn13());
        if(result.isEmpty()){
          bookRepositoryMongo.insert(book);
        }
      }

      log.info("Terminou de popular");
    } catch (Exception e) {
      throw new Exception("Erro ao popular o banco de dados");
    }
  }
  
}
