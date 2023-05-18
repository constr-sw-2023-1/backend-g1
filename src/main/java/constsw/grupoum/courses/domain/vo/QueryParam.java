package constsw.grupoum.courses.domain.vo;

import constsw.grupoum.courses.domain.vo.enumeration.Operator;

public record QueryParam(Operator operator, String resourceName, String value) {

}
