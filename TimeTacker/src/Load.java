import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Load implements Visitor{

    private JSONObject jsonTree;

    public Load(String fileName){
        try{
            InputStream string = new FileInputStream(fileName);
            JSONTokener JSON = new JSONTokener(string);
            this.jsonTree = new JSONObject(JSON);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }


    @Override
    public void visitTask(Task task) {
        task.setName(jsonTree.getString("name"));
        task.setTotalTime(jsonTree.getLong("duration"));

        task.setTotalTime(jsonTree.getLong("totalTime"));

        if (jsonTree.get("initialTime") == JSONObject.NULL) task.setInitialTime(null);
        else task.setInitialTime(LocalDateTime.parse(jsonTree.getString("initialTime")));

        if (jsonTree.get("finalTime") == JSONObject.NULL) task.setFinalTime(null);
        else task.setFinalTime(LocalDateTime.parse(jsonTree.getString("finalTime")));

        task.setActive(jsonTree.getBoolean("active"));

        ArrayList<Object> aux = new ArrayList<>();

        for (int i = 0; i<jsonTree.getJSONArray("intervals").length(); i++){
            aux.add(jsonTree.getJSONArray("intervals").get(i));
        }

        task.setIntervals(aux);






    }

    @Override
    public void visitProject(Project project) {

    }

    @Override
    public void visitInterval(Interval interval) {

    }
}
