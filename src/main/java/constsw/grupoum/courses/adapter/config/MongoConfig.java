package constsw.grupoum.courses.adapter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "constsw.grupoum.courses.adapter.repository.mongo")
public class MongoConfig {

}
