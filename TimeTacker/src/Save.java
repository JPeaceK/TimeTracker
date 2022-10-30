
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import org.json.JSONArray;

public class Save implements Visitor{

    private FileWriter textEditor;
    private JSONObject jsonTree;
    private Project root;


    public Save(String name, Project root){
        try{
            this.textEditor = new FileWriter(name);
            this.root = root;
            this.root.acceptVisitor(this);
        }
        catch (IOException e){ throw new RuntimeException(e); }
    }

    //S'ha de guardar tot l'arbre, per aixo volem que s'accepti ROOT
    @Override
    public void visitTask(Task task) {
        JSONObject json = task.getJSON();
        JSONArray intervals = new JSONArray();

        for (Interval interval : task.getIntervals()){
            if (interval != null){
                interval.acceptVisitor(this);
                intervals.put(this.jsonTree);
            }
        }
        json.put("intervals", intervals);
        this.jsonTree = json;
    }

    @Override
    public void visitProject(Project project) {
        JSONObject json = project.getJSON();
        JSONArray activities = new JSONArray();

        for (Activity activity : project.getActivities()){
            if (activity != null){
                activity.acceptVisitor(this);
                activities.put(this.jsonTree);
            }
        }
        json.put("activities", activities);
        this.jsonTree = json;

        //Si estem a root, ja podem guardar el JSON
        if (project.getFather() == null) this.writeDocument();
    }

    @Override
    public void visitInterval(Interval interval) {
        this.jsonTree = interval.getJSON();
    }

    public void writeDocument(){
        try{
            this.textEditor.write(this.jsonTree.toString());
            this.textEditor.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
