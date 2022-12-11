package Milestone3;

public class IdCreator {
  private static IdCreator idCreator;
  private static int id;

  private IdCreator(){
    id = -1;
  }

  public static int getId() {
    if (idCreator == null){
      idCreator = new IdCreator();
    }
    id++;
    return id;
  }
}
