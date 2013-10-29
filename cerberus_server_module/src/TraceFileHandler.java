import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import sw.gson.trace.ChildFactory;
import sw.gson.trace.ChildNode;
import sw.gson.trace.ChildNode2;
import sw.gson.trace.GsonTrace;
import sw.gson.trace.IndexFactory;
import sw.gson.trace.IndexNode;
import sw.gson.trace.IndexNode2;
import sw.gson.trace.MainClass;
import sw.gson.trace.MainTrace;
import sw.gson.trace.MainTree;
import sw.gson.trace.MethodForClass;
import sw.gson.trace.ParentFactory;
import sw.gson.trace.ParentNode;
import sw.gson.trace.ParentNode2;
import sw.gson.trace.exclNode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TraceFileHandler {

	private String filename = "dmtrace";
	private String extension = ".trace";
	private String conv_extension = ".txt";
    private PrintWriter out;
    private String line;
    private String[] words;
    private StringBuilder sentence = new StringBuilder();
	private int separator;
	private MainTrace Mtrace ;
	private int wordcnt;
	//private MethodForTree MNode;
	private Vector<String> strvec;
	private boolean another;
	private MethodForClass MethodObj;
	private MainClass ClassObj;
	private GsonTrace MGson;
	private Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
			.create();
	private ParentFactory Parent;
	private ParentFactory Parent2;
	private ChildFactory Child2;
	private ChildFactory Child;
	private ChildFactory excl;
	private IndexFactory Index;
	private IndexFactory Index2;
	private MainTree Node;
	
	public void TraceFileHandler() {

	}

	//변환을 위한 파일이 있는지 확인한다. 
	private boolean filechecker() {
		try {
			FileReader filereader = new FileReader(filename+extension);
			return true;
		} catch (FileNotFoundException e) {
			System.out.println(" Not Exist");
			e.printStackTrace();

			return false;
		}
	}

	
	//.trace파일을 텍스트의 형태로 변환하여 저장한다. 
	public void convert() {	
	
		if (filechecker()) {
			
			try {
				MGson = new GsonTrace();
				//MGson.initMaintrace();
				
				Mtrace = new MainTrace();
//				MNode = new MethodForTree();
				MethodObj = new MethodForClass();
				ClassObj = new MainClass();
				
			//	Node = new MainTree();
				Parent = new ParentNode();
				Child = new ChildNode();
				Index = new IndexNode();
				Parent2 = new ParentNode2();
				Child2 = new ChildNode2();
				Index2 = new IndexNode2();
				excl = new exclNode();
				
				strvec  = new Vector<String>();
				File f1 = new File(filename+conv_extension);
				out = new PrintWriter(new FileWriter(f1, false)); 
				Process process = Runtime.getRuntime().exec("./dmtracedump " + filename + extension);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						process.getInputStream()));
				Scanner scanner = new Scanner(br);
				scanner.useDelimiter(System.getProperty("line.separator"));
				separator = 0;
				while (scanner.hasNext())
				{				
					line = scanner.next();
					
					if(line.compareTo("======================================================================") == 0)
					{					
						separator++;
						//대충 어림잡은 것으로 Usec이 나온 다음라인 부터 파싱하도록 추후 수정 필요!!! 
						if (separator == 1)
							for (int i = 0; i < 6; i++)
								line = scanner.next();
						else if (separator == 2)
							for (int i = 0; i < 5; i++)
								line = scanner.next();
						else if (separator == 3)
							for (int i = 0; i < 5; i++)
								line = scanner.next();
						
					} else {
						words = line.split(" ");
						wordcnt = 0;
						for (int i = 0; i < words.length; i++)
						{
							
							// Parsing Part 1
							//Exclusive elapsed times for each method, not including time spent in
							//children, sorted by exclusive time.							
							if(!words[i].isEmpty() && separator == 1)
							{
								wordcnt++;
								if(wordcnt == 1)
									Mtrace.setSec(Integer.parseInt(words[i]));
								else if(wordcnt == 2)
									Mtrace.setSelf(Double.parseDouble(words[i]));
								else if(wordcnt == 3)
									Mtrace.setTotal(Double.parseDouble(words[i]));
								else
								{
									sentence.append(words[i]+" ");
								}									
							}
							
							// Parsing Part 2
							//Inclusive elapsed times for each method and its parents and children,
							//sorted by inclusive time.
							else if(!words[i].isEmpty() && separator == 2) {
								if(words[i].compareTo("----------------------------------------------------") == 0) {
									wordcnt = 0;
									another = false;
									
									
									//주석은 제거해야 PART2가 출력된다!!
									if(Node != null)
										MGson.setTree(Node);
									Node = new MainTree();
									Parent = new ParentNode();
									Child = new ChildNode();
									Index = new IndexNode();
									Parent2 = new ParentNode2();
									Child2 = new ChildNode2();
									Index2 = new IndexNode2();
									excl = new exclNode();
								} 
								else if(words[i].compareTo("+++++++++++++++++++++++++") == 0) {
									wordcnt = 0;
									another = true;									
//									System.out.println("+++++++++++++++++++++++++");
								}
								else {									
									strvec.add(words[i]);
								}//else if "----...---"		
							}//end else if part2
							
							// Parsing Part 3
							else if(!words[i].isEmpty() && separator == 3) {
								if(words[i].compareTo("---------------------------------------------") == 0)
								{
									System.out.println();
								}
								else
								{
									strvec.add(words[i]);
								}
							}
						}//end for ( printing line )
						
						
						
						if(separator == 1)
						{
							Mtrace.setName(sentence.toString());
							
							// 주석을 제거해야 JSON이 찍힌다!!!!!!!!!!!!!!!!!!!!!
							MGson.setMaintrace(Mtrace);
							Mtrace = new MainTrace();
						}
						
						// Parsing Part 2
						//Inclusive elapsed times for each method and its parents and children,
						//sorted by inclusive time.
						else if(separator == 2)
						{
							if(strvec.size() == 6) // ParentNode or ChildNode or Main
							{
								if(strvec.firstElement().contains("["))//MAIN
								{
									Node.ascendWhois();
									Index.setMain(strvec.get(0),strvec.get(1),strvec.get(2),strvec.get(3),strvec.get(4),strvec.get(5));
									Node.setIndex(Index);
									Node.ascendWhois();
								}
								else//ParentNode or ChildNode
								{
									if(Node.getWhois() == 0)//PARENT
									{
										Parent.setParent(strvec.get(0),strvec.get(1),strvec.get(2),strvec.get(3),strvec.get(4),strvec.get(5));
										Node.setParent(Parent);
									}
									
									else if(Node.getWhois() == 2)//CHILD
									{
										Child.setChild(strvec.get(0),strvec.get(1),strvec.get(2),strvec.get(3),strvec.get(4),strvec.get(5));
										Node.setChild(Child);
									}
								}
							}	//
							else if(strvec.size() == 5) // MAIN or A-ParentNode or A-ChildNode
							{
								if (another) {
									if (Node.getWhois() == 0)// A-PARENT
									{
										Parent2.setParent2(strvec.get(0),strvec.get(1),strvec.get(2),strvec.get(3),strvec.get(4));
										Node.setParent(Parent2);
									}

									else if (Node.getWhois() == 2)// A-CHILD
									{
										Child2.setChild2(strvec.get(0),strvec.get(1),strvec.get(2),strvec.get(3),strvec.get(4));
										Node.setChild(Child2);
									}
								}
								else
								{
									if(strvec.firstElement().contains("["))//MAIN
									{
										Node.ascendWhois();
										Index2.setMain(strvec.get(0),strvec.get(1),strvec.get(2),strvec.get(3),strvec.get(4));
										Node.ascendWhois();
									}
									else//ParentNode or ChildNode
									{
										if(Node.getWhois() == 0)//PARENT??????????????
										{
											Parent2.setParent2(strvec.get(0),strvec.get(1),strvec.get(2),strvec.get(3),strvec.get(4));
											Node.setParent(Parent2);
										}
										
										else if(Node.getWhois() == 2)//CHILD????????????????
										{
//											System.out.println(strvec.toString());
//											System.out.print("[CHILED] ");
										}
									}
								}
								
							}
							else if(strvec.size() == 3)//EXCL
							{
								excl.setMain(strvec.get(0),strvec.get(1),strvec.get(2));
								Node.setChild(excl);
							}
							
							
						}//end if of part 2
						else if (separator ==3)
						{
							if(strvec.size() == 5)
							{
								ClassObj.setClass(Integer.parseInt(strvec.get(0)), Double.parseDouble(strvec.get(1)), Double.parseDouble(strvec.get(2)), strvec.get(3), strvec.get(4));
							}
							else if(strvec.size() == 8)
							{
								MethodObj.setMethod(Integer.parseInt(strvec.get(0)), Integer.parseInt(strvec.get(1)), Double.parseDouble(strvec.get(2)),
										Double.parseDouble(strvec.get(3)), strvec.get(4) , strvec.get(5) + strvec.get(6) + strvec.get(7));
								ClassObj.setMethod(MethodObj);
							}		
							MGson.setClassMethod(ClassObj);
							ClassObj = new MainClass();
							
						}
						sentence.delete(0, sentence.length());
						strvec.clear();
						
					}
					
				}//end while - printing a line completed
				scanner.close();
				br.close();
				
				String jsonString = gson.toJson(MGson);
				System.out.println(jsonString);
				
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1); //명령어가 없으면 종료.
			}
		}
	}
}
