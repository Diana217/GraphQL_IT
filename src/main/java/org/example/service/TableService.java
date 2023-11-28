package org.example.service;

import java.util.ArrayList;
import java.util.List;
import org.example.DBManager;
import org.example.component.Table;
import org.springframework.stereotype.Service;

@Service
public class TableService {

  public List<Table> getTables(){
    List<Table> list = new ArrayList<>();
    list.add(DBManager.table1);
    list.add(DBManager.table2);
    list.add(DBManager.table3);
    return list;
  }
}
