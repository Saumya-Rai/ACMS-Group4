package acms;

import java.sql.Time;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.*;

public class Build {
	
	public static void main(String[] args) throws JSONException, IOException{
		
		Forest F = new Forest();
		
		/*pull from db and populate F.forest*/
		/*sample*/
		System.out.println("sample run");
		
		JSONObject temp = new JSONObject();
		Time t1 = new Time(12, 0, 0);
		Time t2 = new Time(14, 0, 0);
		temp = F.createNode("A", "B", "C", "D", t1, t2, 5.4);
		int index = F.getIndexOfTree("A");
		
		System.out.println("adding to forest");
		
		if(index == -1)
		{
			JSONArray tree = new JSONArray();
			tree.put(temp);
			F.forest.add(tree);
		}
		else
		{
			F.forest.get(index).put(temp);
		}
		
		
		/*write F.forest into json file*/
		JSONObject forestObj = new JSONObject();
		forestObj.put("forest", F.forest);
		
		System.out.println("writing into file");
		
		FileWriter fileWriter = new FileWriter("F:\\sample.json");
		fileWriter.write(forestObj.toString());
        fileWriter.close();
		
	}

}
