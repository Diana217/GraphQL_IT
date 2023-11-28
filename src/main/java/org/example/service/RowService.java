package org.example.service;

import java.util.List;
import org.example.DBManager;
import org.example.component.Row;
import org.example.component.Table;
import org.springframework.stereotype.Service;

@Service
public class RowService {
  public List<Row> getRows(Table table){
    return table.rows;
  }
}
