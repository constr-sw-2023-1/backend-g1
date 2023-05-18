package constsw.grupoum.courses.domain.vo.enumeration;

public enum Operator {

    EQUALS(""),
    NOT_EQUALS("neq"),
    GREATER_THAN("gt");

    private String operator;

    private Operator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
