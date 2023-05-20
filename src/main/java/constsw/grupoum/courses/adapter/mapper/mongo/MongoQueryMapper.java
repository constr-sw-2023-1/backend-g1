package constsw.grupoum.courses.adapter.mapper.mongo;

import java.util.Collection;

import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import constsw.grupoum.courses.adapter.exception.InvalidAtributeException;
import constsw.grupoum.courses.adapter.util.ReflectionUtils;
import constsw.grupoum.courses.adapter.util.mongo.QueryBuilder;
import constsw.grupoum.courses.domain.vo.QueryParam;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MongoQueryMapper {

    private final ReflectionUtils reflectionUtils;

    public Query toQuery(Class<?> clazz, Collection<QueryParam> queries) throws InvalidAtributeException {

        QueryBuilder queryBuilder = new QueryBuilder();

        for (QueryParam queryParam : queries) {

            Object value = convertValue(clazz, queryParam.value(), queryParam.atribute());

            switch (queryParam.operator()) {
                case EQUALS:
                    queryBuilder.eq(queryParam.atribute(), value);
                    break;
                case NOT_EQUALS:
                    queryBuilder.ne(queryParam.atribute(), value);
                    break;
                case GREATER_THAN:
                    queryBuilder.gt(queryParam.atribute(), value);
                    break;
                case GREATER_THAN_EQAULS:
                    queryBuilder.gte(queryParam.atribute(), value);
                    break;
                case LESS_THAN:
                    queryBuilder.lt(queryParam.atribute(), value);
                    break;
                case LESS_THAN_EQUALS:
                    queryBuilder.lte(queryParam.atribute(), value);
                    break;
                case LIKE:
                    queryBuilder.regex(queryParam.atribute(), toRegexMongo(value.toString()));
                    break;
            }
        }

        return queryBuilder.build();
    }

    private Object convertValue(Class<?> clazz, String value, String atribute) throws InvalidAtributeException {
        return ConvertUtils.convert(value,
                reflectionUtils.getComplexFieldClass(clazz, atribute));
    }

    private String toRegexMongo(String pattern) {
        return pattern.replaceAll("^%", "^(.*)").replaceAll("%$", "(.*)\\$");
    }

}
