package acms;

import java.sql.Time;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class Forest {
	
	ArrayList<JSONArray> forest;
	
	Forest()
	{
		forest = new ArrayList<JSONArray>();
	}
	
	public JSONArray createTree()
	{
		JSONArray tree=new JSONArray();
		forest.add(tree);
		return tree;
	}
	public void addNode(int index, JSONObject node)
	{
		JSONArray tempTree = forest.get(index);
		tempTree.put(node);
	}
	public int getIndexOfTree(String callsign)throws JSONException
	{
		for(int i=0;i<forest.size();i++)
		{
			JSONArray tempTree = forest.get(i);
			JSONObject node = (JSONObject)tempTree.get(0);
			if(node.getString("callSign") == callsign)
				return i;	
		}
		return -1;
	}
	public JSONObject createNode(String cs, String fn, String src, String dest, Time sd, Time sa, double ad)throws JSONException
	{
		JSONObject node = new JSONObject();
		node = new JSONObject();
		node.put("callSign",cs);
		node.put("flightNo",fn);
		node.put("source",src);
		node.put("destination",dest);
		node.put("sch_dep",sd);
		node.put("sch_arr",sa);
		node.put("avg_delay",ad);
		return node;
	}
}
