package org.example;

import org.example.component.Column;
import org.example.component.Row;
import org.example.component.Table;
import org.example.component.column.*;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

  private static DBManager instance;

  private DBManager() {
  }

  public static DBManager getInstance() {
    if (instance == null) {
      instance = new DBManager();
    }
    return instance;
  }

  public static Table table1, table2, table3;

  public static void initTables() {
    table1 = new Table("Table1");
    table1.addColumn(new IntegerColumn("column1"));
    table1.addColumn(new RealColumn("column2"));
    table1.addColumn(new StringColumn("column3"));
    table1.addColumn(new CharColumn("column4"));
    table1.addColumn(new EnumColumn("column5"));
    table1.addColumn(new EmailColumn("column6"));

    Row row11 = new Row();
    row11.values.add("1");
    row11.values.add("2.0");
    row11.values.add("kj");
    row11.values.add("o");
    row11.values.add("k,j");
    row11.values.add("email1@gmail.com");
    table1.addRow(row11);

    Row row12 = new Row();
    row12.values.add("3");
    row12.values.add("4.0");
    row12.values.add("ol");
    row12.values.add("p");
    row12.values.add("o,l");
    row12.values.add("email2@gmail.com");
    table1.addRow(row12);


    table2 = new Table("Table2");
    table2.addColumn(new IntegerColumn("column1"));
    table2.addColumn(new RealColumn("column22"));
    table2.addColumn(new StringColumn("column23"));
    table2.addColumn(new CharColumn("column24"));
    table2.addColumn(new EnumColumn("column25"));
    table2.addColumn(new EmailColumn("column26"));

    Row row21 = new Row();
    row21.values.add("1");
    row21.values.add("5.0");
    row21.values.add("rt");
    row21.values.add("e");
    row21.values.add("rt");
    row21.values.add("email3@gmail.com");
    table2.addRow(row21);

    Row row22 = new Row();
    row22.values.add("6");
    row22.values.add("7.0");
    row22.values.add("ts");
    row22.values.add("w");
    row22.values.add("t,s");
    row22.values.add("email4@gmail.com");
    table2.addRow(row22);
  }

  public static void unionOfTables() {
    table3 = new Table("Union");
    if (table1 != null && table2 != null) {
      Column col1 = findColumn();
      Column col2 = findColumn(col1.getName());

      table3.setColumns(unionColumns(table1.getColumns(), table2.getColumns()));

      int ind1 = table1.getColumns().indexOf(col1);
      int ind2 = table2.getColumns().indexOf(col2);

      boolean isEnum = false;

      if (col1.getType().equals(ColumnType.ENUM.name())) {
        isEnum = true;
      }

      if (ind1 != -1 && ind2 != -1) {
        for (Row row1 : table1.getRows()) {
          String value1 = row1.getValues().get(ind1);

          for (Row row2 : table2.getRows()) {
            String value2 = row2.getValues().get(ind2);

            if (isEnum) {
              String[] enum1 = ((String) value1).split(",");
              String[] enum2 = ((String) value2).split(",");

              if (containsAll(enum1, enum2) && containsAll(enum2, enum1)) {
                Row newRow = new Row();
                newRow.values = new ArrayList<>(row1.getValues());
                newRow.getValues().addAll(row2.getValues());
                table3.getRows().add(newRow);
              }
            } else if (value1.equals(value2)) {
              Row newRow = new Row();
              newRow.values = new ArrayList<>(row1.getValues());
              newRow.getValues().addAll(row2.getValues());
              table3.getRows().add(newRow);
            }
          }
        }
        table3 = deleteColumn(table3, col1.getName());
      }
    }
  }

  private static Column findColumn() {
    for (Column column1 : table1.getColumns()) {
      for (Column column2 : table2.getColumns()) {
        if (column1.getName().equals(column2.getName()) && column1.getType().equals(column2.getType())) {
          return column1;
        }
      }
    }
    return null;
  }

  private static Column findColumn(String name) {
    for (Column column2 : table2.getColumns()) {
      if (column2.getName().equals(name)) {
        return column2;
      }
    }
    return null;
  }

  private static List<Column> unionColumns(List<Column> columns1, List<Column> columns2) {
    List<Column> unionColumns = new ArrayList<>(columns1);
    unionColumns.addAll(columns2);
    return unionColumns;
  }

  private static Table deleteColumn(Table table, String columnName){
    int ind = -1;
    for (int i = table.getColumns().size() - 1; i >= 0; i--) {
      if(columnName.equals(table.getColumns().get(i).getName())){
        table.getColumns().remove(i);
        ind = i;
        break;
      }
    }
    for (int i = 0; i < table.getRows().size(); i++) {
      if(ind != -1) table.getRows().get(i).getValues().remove(ind);
    }
    return table;
  }

  private static boolean containsAll(String[] array1, String[] array2) {
    for (String value : array1) {
      if (!contains(array2, value)) {
        return false;
      }
    }
    return true;
  }

  private static boolean contains(String[] array, String value) {
    for (String item : array) {
      if (item.equals(value)) {
        return true;
      }
    }
    return false;
  }
}
