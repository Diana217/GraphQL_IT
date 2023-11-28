package org.example.component;


public abstract class Column {
  public String name;
  public String type;

  public Column(String name){
    this.name = name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public abstract boolean validate(String data);
}
