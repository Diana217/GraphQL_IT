package org.example;

import graphql.GraphQL;
import graphql.com.google.common.collect.Table;
import graphql.execution.AsyncExecutionStrategy;
import org.example.resolver.ColumnResolver;
import org.example.resolver.RowResolver;
import org.example.resolver.TableResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;

@Configuration
public class GraphQLConfig {

  private final ColumnResolver columnResolver;
  private final RowResolver rowResolver;
  private final TableResolver tableResolver;

  public GraphQLConfig(ColumnResolver columnResolver,
      RowResolver rowResolver, TableResolver tableResolver) {
    this.columnResolver = columnResolver;
    this.rowResolver = rowResolver;
    this.tableResolver = tableResolver;
  }

  @Bean
  public GraphQLSchema getGraphQLSchema() {
    return new GraphQLSchemaGenerator()
        .withBasePackages("com.example.graphql.demo.models")
        .withOperationsFromSingletons(columnResolver, rowResolver, tableResolver)
        .generate();
  }

  @Bean
  public GraphQL getGraphQL(GraphQLSchema graphQLSchema) {
    return GraphQL.newGraphQL(graphQLSchema)
        .queryExecutionStrategy(new AsyncExecutionStrategy())
        .instrumentation(new CustomTracingInstrumentation())
        .build();
  }
}

