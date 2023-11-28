package org.example.service;

import java.util.List;
import org.example.DBManager;
import org.example.component.Column;
import org.example.component.Table;
import org.springframework.stereotype.Service;

@Service
public class ColumnService {

  public List<Column> getColumns(Table table){
    return table.columns;
  }
}
