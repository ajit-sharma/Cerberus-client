package flurry_log_parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FileReading {
	private String filename;// = "PowerTrace1382413409905.log";
	private ArrayList<FlurryObject> result  = new ArrayList<FlurryObject>();
	private String parent = "N/V";
	private String tmp_parent = "N/V";
	private int parentindex = -1;
	private FlurryTree parentobj;
	private FlurryTree childobj;
	private FlurryTree ancestorobj;
	private FlurryTree ftree;
	private FlurryTree root;
	private FlurryTree tmp_ftree;
	
	private Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
			.create();
	private FlurryTree maintree;
	
	public FileReading(String filename) {
		this.filename = filename;
	}

	public ArrayList<FlurryObject> readfile(String _filename) {
		try {
			this.filename = _filename;
			String data = "";
			BufferedReader in = new BufferedReader(new FileReader(this.filename));
			maintree = new FlurryTree();
			ftree = new FlurryTree();
			root = new FlurryTree();
			parentobj = new FlurryTree();
			childobj = new FlurryTree();
			while ((data = in.readLine()) != null) {
				String[] tmparr = data.split(",");
					if (tmparr.length > 5  && tmparr.length != 9) {					
						
						tmp_ftree = new FlurryTree();
						tmp_ftree.setIndex(Integer.parseInt(tmparr[2]));
						tmp_ftree.setEvent(tmparr[3]);		

						//not first, decrease, not end
						//to handling unspecified last element
						if( tmparr[3].compareTo("End Session") != 0
								&& parentindex != -1 
								&& parentobj.getIndex()+1 != Integer.parseInt(tmparr[2]) 
								&& parentobj.getEvent().compareTo("End Session") != 0)
						{

							if(parentobj.search(parentindex+1, "End Session"))
							{
								parentobj.getChild(parentindex+1, "End Session").increaseCnt();
							}
							else
							{
								parentobj.setChild(new FlurryTree(parentindex+1, "End Session"));
							}
							parentobj = parentobj.getChild(parentindex+1, "End Session");
						}
						
						//not null obj and if threre is a child
						if( parentobj != null && parentobj.search(Integer.parseInt(tmparr[2]), tmparr[3]))
						{				
							// 부모가 이미 가지고 있는 또 다른 노드를 가져와서 카운트를 늘린다 
							childobj = parentobj.getChild(Integer.parseInt(tmparr[2]), tmparr[3]);
							childobj.increaseCnt();
							
							// 현재 노드가 1번인데 0번째 노드가 명시되어 있지 않은 경우 
							if (Integer.parseInt(tmparr[2]) == 1)
							{
								//현재 노드가 1번인데 
								if(parentindex != 0)
								{
									//0번째 기본 노드가 생략이 되어 있으므로 추가해준다 
									root.increaseCnt();	
								}	
							} 
							
							// 카운트를 늘린 기존에 추가한 동일한 노드로 현재(앞으로 부모가 될)노드를 설정한다 
							parentobj = parentobj.getChild(childobj.getIndex(), childobj.getEvent());
						}

						else {
//							System.out.println(parentobj.toString() + "    " + tmp_ftree.toString() );

							if (parentindex == -1
									&& Integer.parseInt(tmparr[2]) == 0)// First [0]
							{
								parentobj.setRoot(tmp_ftree);
								parentobj = root;
							} else if (parentindex == -1 && Integer.parseInt(tmparr[2]) == 1)// First [1]
							{
								root = parentobj.setRoot(new FlurryTree(0, "Start Session"));
								parentobj = root;
								parentobj.setChild(tmp_ftree);
								parentobj = parentobj.getChild(tmp_ftree.getIndex(), tmp_ftree.getEvent());
								
							} 
							
							
							else if (Integer.parseInt(tmparr[2]) == 0)// [0]
							{
								root.increaseCnt();	
								parentobj = root;
							}
							else if (Integer.parseInt(tmparr[2]) == 1)// [1]
							{
								if(parentindex != 0)// 2-[1], 3-[1] ...
								{
									root.increaseCnt();	
									parentobj = root;
								}	
								
								if(parentobj.search(tmp_ftree.getIndex(), tmp_ftree.getEvent()))// 0 - [1]
									parentobj.getChild(tmp_ftree.getIndex(), tmp_ftree.getEvent()).increaseCnt();
								else
									parentobj.setChild(tmp_ftree);		
								
								parentobj = parentobj.getChild(tmp_ftree.getIndex(), tmp_ftree.getEvent());
							} else// 각 구간의 중간 노드들 (ex 1-[2]-3, 2-[3]-4, ...)
							{
								parentobj.setChild(tmp_ftree);
								parentobj = parentobj.getChild(tmp_ftree.getIndex(), tmp_ftree.getEvent());
							}
						}
						parent = tmparr[3];
						parentindex = Integer.parseInt(tmparr[2]);						
					}
					

			}
			if( parentindex != -1 && parentobj.getIndex()+1 != parentindex && parentobj.getEvent().compareTo("End Session") != 0)
			{

				if(parentobj.search(parentindex+1, "End Session"))
				{
					parentobj.getChild(parentindex+1, "End Session").increaseCnt();
				}
				else
				{
					parentobj.setChild(new FlurryTree(parentindex+1, "End Session"));
				}
				parentobj = parentobj.getChild(parentindex+1, "End Session");
			}
			
			String jsonString = gson.toJson(root);
//			System.out.println(jsonString);
			BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".json"));
			writer.write(jsonString);
			System.out.println("Writing " + filename + ".json completed!");
			writer.close();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
