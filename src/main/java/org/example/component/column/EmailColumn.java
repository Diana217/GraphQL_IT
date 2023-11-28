package org.example.component.column;

import org.example.component.Column;

import java.util.regex.Pattern;

public class EmailColumn extends Column {

    private static final String PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(PATTERN);
    public EmailColumn(String name) {
        super(name);
        this.type = ColumnType.EMAIL.name();
    }

    @Override
    public boolean validate(String data) {
        if (data == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(data).matches();
    }
}