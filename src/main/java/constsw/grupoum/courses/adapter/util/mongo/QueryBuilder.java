package constsw.grupoum.courses.adapter.util.mongo;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class QueryBuilder {

    private Collection<Criteria> criterias;

    public QueryBuilder() {
        criterias = new ArrayList<Criteria>();
    }

    public QueryBuilder eq(String atribute, Object value) {
        criterias.add(Criteria.where(atribute).is(value));
        return this;
    }

    public QueryBuilder ne(String atribute, Object value) {
        criterias.add(Criteria.where(atribute).ne(value));
        return this;
    }

    public QueryBuilder gt(String atribute, Object value) {
        criterias.add(Criteria.where(atribute).gt(value));
        return this;
    }

    public QueryBuilder gte(String atribute, Object value) {
        criterias.add(Criteria.where(atribute).gte(value));
        return this;
    }

    public QueryBuilder lt(String atribute, Object value) {
        criterias.add(Criteria.where(atribute).lt(value));
        return this;
    }

    public QueryBuilder lte(String atribute, Object value) {
        criterias.add(Criteria.where(atribute).lte(value));
        return this;
    }

    public QueryBuilder regex(String atribute, String pattern) {
        criterias.add(Criteria.where(atribute).regex(pattern, "i"));
        return this;
    }

    public Query build() {
        return new Query(new Criteria().andOperator(criterias));
    }

}
