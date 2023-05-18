package constsw.grupoum.courses.application.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Component;

import constsw.grupoum.courses.domain.vo.QueryParam;

@Component
public class QueryMapper {

    public Collection<QueryParam> mapToCollectionOfQueryParam(Map<String, String> queryParams) {

        return new ArrayList<QueryParam>() {

        };

    }

}
