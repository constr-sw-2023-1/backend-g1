package constsw.grupoum.courses.application.mapper;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import constsw.grupoum.courses.application.exception.ApplicationException;
import constsw.grupoum.courses.application.exception.InvalidQueryException;
import constsw.grupoum.courses.application.exception.InvalidQueryOperatorException;
import constsw.grupoum.courses.domain.vo.QueryParam;
import constsw.grupoum.courses.domain.vo.enumeration.Operator;

@Component
public class QueryMapper {

    Pattern findAllOperatorField = Pattern.compile("(\\{)(.*)(\\})");

    public Collection<QueryParam> mapToCollectionOfQueryParam(Map<String, String> queryParams)
            throws ApplicationException {

        return queryParams.entrySet()
                .stream()
                .map(queryParam -> {
                    Operator operator = getOperator(queryParam.getValue());
                    String value = getValue(queryParam.getValue());

                    if (operator.equals(Operator.LIKE) && value.length() < 3)
                        throw new InvalidQueryException("Like's operations must be have more than three characters");

                    return new QueryParam(operator, queryParam.getKey(), value);
                })
                .collect(Collectors.toList());

    }

    private Operator getOperator(String value) {
        Matcher matcher = findAllOperatorField.matcher(value);
        return matcher.find() ? convertToOperator(matcher.group()) : Operator.EQUALS;
    }

    private Operator convertToOperator(String value) {
        switch (value) {
            case "{neq}":
                return Operator.NOT_EQUALS;
            case "{gt}":
                return Operator.GREATER_THAN;
            case "{gteq}":
                return Operator.GREATER_THAN_EQAULS;
            case "{lt}":
                return Operator.LESS_THAN;
            case "{lteq}":
                return Operator.LESS_THAN_EQUALS;
            case "{like}":
                return Operator.LIKE;
        }

        throw new InvalidQueryOperatorException(String.format("Invalid query operator: %s", value));
    }

    private String getValue(String value) {
        Matcher matcher = findAllOperatorField.matcher(value);
        return matcher.find() ? value.replace(matcher.group(), "") : value;
    }

}
