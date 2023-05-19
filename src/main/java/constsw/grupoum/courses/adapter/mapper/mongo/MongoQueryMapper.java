package constsw.grupoum.courses.adapter.mapper.mongo;

import java.util.Collection;
import java.util.regex.Pattern;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import constsw.grupoum.courses.adapter.util.mongo.QueryBuilder;
import constsw.grupoum.courses.domain.vo.QueryParam;

@Component
public class MongoQueryMapper {

    Pattern like = Pattern.compile("^%|%$");

    public Query toQuery(Collection<QueryParam> queries) {

        QueryBuilder queryBuilder = new QueryBuilder();

        for (QueryParam queryParam : queries) {
            switch (queryParam.operator()) {
                case EQUALS:
                    queryBuilder.eq(queryParam.atribute(), queryParam.value());
                    break;
                case NOT_EQUALS:
                    queryBuilder.ne(queryParam.atribute(), queryParam.value());
                    break;
                case GREATER_THAN:
                    queryBuilder.gt(queryParam.atribute(), queryParam.value());
                    break;
                case GREATER_THAN_EQAULS:
                    queryBuilder.gte(queryParam.atribute(), queryParam.value());
                    break;
                case LESS_THAN:
                    queryBuilder.lt(queryParam.atribute(), queryParam.value());
                    break;
                case LESS_THAN_EQUALS:
                    queryBuilder.lte(queryParam.atribute(), queryParam.value());
                    break;
                case LIKE:
                    queryBuilder.regex(queryParam.atribute(), toRegexMongo(queryParam.value().toString()));
                    break;
            }
        }

        return queryBuilder.build();
    }

    private String toRegexMongo(String pattern) {
        return pattern.replaceAll("^%", "^(.*)").replaceAll("%$", "(.*)\\$");
    }

}
