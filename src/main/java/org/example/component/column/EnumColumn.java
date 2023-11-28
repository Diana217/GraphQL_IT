package org.example.component.column;

import org.example.component.Column;

public class EnumColumn extends Column {

    public EnumColumn(String name) {
        super(name);
        this.type = ColumnType.ENUM.name();
    }

    @Override
    public boolean validate(String data) {
        String[] buf = (data != null) ? data.split(",") : null;
        if (buf != null && buf.length == 0) {
            return false;
        }
        return true;
    }
}