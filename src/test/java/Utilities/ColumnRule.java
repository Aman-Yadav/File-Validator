package Utilities;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class ColumnRule {

    private boolean unique;
    private boolean nonNull;
    private boolean booleanType;
    private List<String> allowedValues;
    private Integer fixedLength;

    public boolean isUnique() {
        return unique;
    }

    public boolean isNonNull() {
        return nonNull; }

    public boolean isBooleanType() {
        return booleanType;
    }
    public List<String> getAllowedValues() {
        return allowedValues;
    }
    public Integer getFixedLength() {
        return fixedLength;
    }

}
