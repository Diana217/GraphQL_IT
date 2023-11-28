package org.example.resolver;

import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.example.component.Table;
import org.example.service.TableService;
import org.springframework.stereotype.Service;


@Service
public class TableResolver {

  private final TableService tableService;

  public TableResolver(TableService tableService) {
    this.tableService = tableService;
  }

  @GraphQLQuery(name = "tables")
  public CompletableFuture<List<Table>> getTables() {
    return CompletableFuture.supplyAsync(tableService::getTables);
  }
}
