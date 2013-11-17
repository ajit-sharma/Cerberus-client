package sw.cerberus.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import sw.cerberus.gson.trace.ChildFactory;
import sw.cerberus.gson.trace.ChildNode;
import sw.cerberus.gson.trace.ChildNode2;
import sw.cerberus.gson.trace.GsonTrace;
import sw.cerberus.gson.trace.IndexFactory;
import sw.cerberus.gson.trace.IndexNode;
import sw.cerberus.gson.trace.IndexNode2;
import sw.cerberus.gson.trace.MainClass;
import sw.cerberus.gson.trace.MainMethod;
import sw.cerberus.gson.trace.MainTrace;
import sw.cerberus.gson.trace.MainTree;
import sw.cerberus.gson.trace.NodeInfo;
import sw.cerberus.gson.trace.ParentFactory;
import sw.cerberus.gson.trace.ParentNode;
import sw.cerberus.gson.trace.ParentNode2;
import sw.cerberus.gson.trace.RecursiveNodes;
import sw.cerberus.gson.trace.SubMethod;
import sw.cerberus.gson.trace.UserPkgMain;
import sw.cerberus.gson.trace.exclNode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TraceFileHandler {

	private String filename = "dmtrace";
	private String usrfilename = "usr_dmtrace";
	private String usr_tree_filename = "usr_tree_dmtrace";
	private String extension = ".trace";
	private String conv_extension = ".txt";
    private PrintWriter out;
    private String line;
    private String[] words;
    private StringBuilder sentence = new StringBuilder();
	private int separator;
	private MainTrace Mtrace ;
	private int wordcnt;
	private Vector<String> strvec;
	private boolean another;
	private SubMethod MethodObj;
	private MainClass ClassObj;
	private MainMethod MainMethodObj;
	private NodeInfo nodeinfo;
	private GsonTrace MGson;
	private GsonTrace usrMGson;
	private Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
	private Gson usrgson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
	private ParentFactory Parent;
	private ParentFactory Parent2;
	private ChildFactory Child2;
	private ChildFactory Child;
	private ChildFactory excl;
	private IndexFactory Index;
	private IndexFactory Index2;
	private MainTree Node;
	private sw.cerberus.gson.hprof.Writer writer;
	private UserPackageHandler pkghdlr;
	private int for_i = 0;
	private String[] tmp;
	private String[] tmp2;
	private String[] tmp3;
	private String[] tmp4;
	private StringBuilder tmp5;
	private GsonTrace tempMGson;
	private String Path;
	
	
	public TraceFileHandler(String _path) {
		this.filename = _path+filename;
		this.usrfilename = _path+usrfilename;
		this.usr_tree_filename = _path+usr_tree_filename;
	}

	//변환을 위한 파일이 있는지 확인한다. 
	private boolean filechecker() {
		try {
			FileReader filereader = new FileReader(filename+extension);
			filereader.close();
			return true;
		} catch (Exception e) {
			System.out.println("File Not Exist");
			e.printStackTrace();
			return false;
		}
	}

	
	//.trace파일을 텍스트의 형태로 변환하여 저장한다. 
	public void convert(UserPkgMain usrpkgmain) {	
	
		
		if (filechecker()) {			
			try {						
				pkghdlr = new UserPackageHandler(usrpkgmain);
				pkghdlr.removeCerberus();
				
				this.nodeinfo = new NodeInfo();
				tempMGson = new GsonTrace();
				MGson = new GsonTrace();
				usrMGson = new GsonTrace();
				Mtrace = new MainTrace();
				MethodObj = new SubMethod();
				ClassObj = new MainClass();
				MainMethodObj = new MainMethod();
				Parent = new ParentNode();
				Child = new ChildNode();
				Index = new IndexNode();
				Parent2 = new ParentNode2();
				Child2 = new ChildNode2();
				Index2 = new IndexNode2();
				excl = new exclNode();
				writer = new sw.cerberus.gson.hprof.Writer();
				
				strvec  = new Vector<String>();
				File f1 = new File(filename+conv_extension);
				out = new PrintWriter(new FileWriter(f1, false)); 
				String currentdir = System.getProperty("user.dir");
				Process process = Runtime.getRuntime().exec(currentdir + "/public/dmtracedump " + filename + extension);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						process.getInputStream()));
				Scanner scanner = new Scanner(br);
				scanner.useDelimiter(System.getProperty("line.separator"));
				separator = 0;
				
				
				
				while (scanner.hasNext())
				{				
					line = scanner.next();
					//System.out.println(line);
					
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
						else if (separator == 4)
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
									{
//										System.out.println(Node.getIndexClass());
//										System.out.println(Node.getWhois());
//										System.out.println(Node.getIndex());
//										System.out.println(Node.getIndexNode());
										// To filtering platform method
//										this.nodeinfo.setNode(Node);
//										tempMGson.setTree(Node);
										doFiltering2();
										MGson.setTree(Node);										
									}
									
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
								}
								else {									
									strvec.add(words[i]);
								}//else if "----...---"		
							}//end else if part2
							
							// Parsing Part 3
							else if(!words[i].isEmpty() && separator == 3) {
								if(Node != null)
								{
									// To filtering platform method
									doFiltering2();
									
									MGson.setTree(Node);
								}
									
								
								if(words[i].compareTo("---------------------------------------------") == 0)
								{
									//System.out.println();
								}
								else
								{
									strvec.add(words[i]);
								}
							}
							
							else if(!words[i].isEmpty() && separator == 4) {
								if(words[i].compareTo("---------------------------------------------") == 0)
								{
									//System.out.println();
								}
								else
								{
									strvec.add(words[i]);
								}
							}
						}//end for ( printing line )
						
						
						//Part 1
						if(separator == 1)
						{
							Mtrace.setName(sentence.toString());	
							
							// 주석을 제거해야 JSON이 찍힌다!!!!!!!!!!!!!!!!!!!!!
							if (Mtrace != null) {

								// To filtering platform method
								doFiltering();
								MGson.setMaintrace(Mtrace);
								Mtrace = new MainTrace();
							}
							
							
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
									
								}
								else//ParentNode or ChildNode
								{
									if(Node.getWhois() == 0)//PARENT
									{
										Parent.setParent(strvec.get(0),strvec.get(1),strvec.get(2),strvec.get(3),strvec.get(4),strvec.get(5));
										Node.setParent(Parent);
										Parent = new ParentNode();
									}
									
									else if(Node.getWhois() == 2)//CHILD
									{
										Child.setChild(strvec.get(0),strvec.get(1),strvec.get(2),strvec.get(3),strvec.get(4),strvec.get(5));
										Node.setChild(Child);
										Child = new ChildNode();
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
										Parent2 = new ParentNode2();
									}

									else if (Node.getWhois() == 2)// A-CHILD
									{
										Child2.setChild2(strvec.get(0),strvec.get(1),strvec.get(2),strvec.get(3),strvec.get(4));
										Node.setChild(Child2);
										Child2 = new ChildNode2();
									}
								}
								else
								{
									if(strvec.firstElement().contains("["))//MAIN
									{
										Node.ascendWhois();
										Index2.setMain2(strvec.get(0),strvec.get(1),strvec.get(2),strvec.get(3),strvec.get(4));
										
									}
									else//ParentNode or ChildNode
									{
										if(Node.getWhois() == 0)//PARENT??????????????
										{
											Parent.setParent3(strvec.get(0),strvec.get(1),strvec.get(2),strvec.get(3),strvec.get(4));
											Node.setParent(Parent);
											Parent = new ParentNode();
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
								if(Index.getMethodName().compareTo("N/N") != 0)
								{
									Index.setExcl(strvec.get(0), strvec.get(2));
									Node.setIndex(Index);
									Index = new IndexNode();
									Node.ascendWhois();									
								}
								else
								{
									Index2.setExcl(strvec.get(0), strvec.get(2));
									Node.setIndex(Index2);
									Node.ascendWhois();
									Index2 = new IndexNode2();
									
								}
//								excl.setMain(strvec.get(0),strvec.get(1),strvec.get(2));
//								Node.setChild(excl);
//								excl = new exclNode();
							}
							
							
						}//end if of part 2
						else if (separator ==3)
						{
							if (strvec.size() == 5) {
								if (ClassObj != null) {

									// To filtering platform method
									doFiltering3();

									MGson.setClassDetail(ClassObj);
									ClassObj = new MainClass();
								}
								ClassObj.setClass(Integer.parseInt(strvec.get(0)), Double.parseDouble(strvec.get(1)), Double.parseDouble(strvec.get(2)), strvec.get(3), strvec.get(4));
							}
							else if(strvec.size() == 8)
							{
								MethodObj.setMethod(Integer.parseInt(strvec.get(0)), Integer.parseInt(strvec.get(1)), Double.parseDouble(strvec.get(2)),
										Double.parseDouble(strvec.get(3)), strvec.get(4) , strvec.get(5) + strvec.get(6) + strvec.get(7));
								ClassObj.setMethod(MethodObj);
								MethodObj = new SubMethod();
							}
						}
						
						else if (separator ==4)
						{
							//Add Part 3's last element
							if (ClassObj != null) {
								
								//To filtering platform method
								doFiltering3();
								
								MGson.setClassDetail(ClassObj);
								ClassObj = new MainClass();
							}
							
							if (strvec.size() == 5) {
								if (!MainMethodObj.isEmpty()) {
									
									// To filtering platform method
									//doFiltering3();
									
									MGson.setMethodDetail(MainMethodObj);
									MainMethodObj = new MainMethod();
								}
								MainMethodObj.setMethod(Integer.parseInt(strvec.get(0)), Double.parseDouble(strvec.get(1)), Double.parseDouble(strvec.get(2)), strvec.get(3), strvec.get(4));
							}
							else if(strvec.size() == 8)
							{
								MethodObj.setMethod(Integer.parseInt(strvec.get(0)), Integer.parseInt(strvec.get(1)), Double.parseDouble(strvec.get(2)),
										Double.parseDouble(strvec.get(3)), strvec.get(4) , strvec.get(5) + strvec.get(6) + strvec.get(7));
								MainMethodObj.setSubMethod(MethodObj);
								MethodObj = new SubMethod();
							}
						}
						sentence.delete(0, sentence.length());
						strvec.clear();
					}
					
				}//end while - printing a line completed
				
				//part 4의 마지막 원소를 출력하기 위함 
				if (!MainMethodObj.isEmpty()) {
					
					// To filtering platform method
					//doFiltering3();
					
					MGson.setMethodDetail(MainMethodObj);
					MainMethodObj = new MainMethod();
				}
				scanner.close();
				br.close();
				
//				String jsonString = gson.toJson(MGson);
//				writer.write(filename, jsonString);
				
				
//				String usrjsonString = gson.toJson(usrMGson);
//				writer.write(usrfilename, usrjsonString);
				
				RecursiveNodes node = new RecursiveNodes();
				node.setinitNode(this.nodeinfo);
//				node.setNode(this.nodeinfo, nodeinfo.getMin());
				
				String user_tree_jsonString = gson.toJson(node);
//				System.out.println(user_tree_jsonString);
				writer.write(usr_tree_filename, user_tree_jsonString);
				
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1); //명령어가 없으면 종료.
			}
		}
	}
	
	void doFiltering()
	{
		tmp = sentence.toString().split(" ");
		if(tmp.length == 3)
		{
			tmp2 = tmp[1].split("[.]"); //split method name
			tmp3 = tmp2[0].split("[$]"); // split untitled index
			tmp4 = tmp3[0].split("/");// split class name
			
			tmp5 = new StringBuilder();
			for(for_i = 0; for_i < tmp4.length-1 ; for_i++)//reassemble package name with .(dot)
			{
				if(for_i != tmp4.length-2)
					tmp5.append(tmp4[for_i]+".");
				else
					tmp5.append(tmp4[for_i]);
			}
			if(pkghdlr.chkName(tmp5.toString()))
			{
				usrMGson.setMaintrace(Mtrace);
				//System.out.print(tmp5.toString());
//				System.out.println(sentence.toString());
			}								
		}
	}
	
	void doFiltering2()
	{
		
		tmp = Node.getIndexClass().split(" ");
		
		if(tmp.length > 1)
		{
			
			tmp2 = tmp[0].split("[.]"); //split method name
			tmp3 = tmp2[0].split("[$]"); // split untitled index
			tmp4 = tmp3[0].split("/");// split class name
			
			StringBuilder tmp5 = new StringBuilder();
			for(for_i = 0; for_i < tmp4.length-1 ; for_i++)//reassemble package name with .(dot)
			{
				if(for_i != tmp4.length-2)
					tmp5.append(tmp4[for_i]+".");
				else
					tmp5.append(tmp4[for_i]);
			}
			if(pkghdlr.chkName(tmp5.toString()))
			{
				this.nodeinfo.setNode(Node);
				tempMGson.setTree(Node);
				System.out.println(tmp5.toString());
				//System.out.println(sentence.toString());
			}								
		}
	}
	
	void doFiltering3()
	{
		// To filtering platform method

		tmp = ClassObj.getClsName().split(" ");
		if (tmp.length > 0) {

			tmp2 = tmp[0].split("[.]"); // split
										// method
										// name
			tmp3 = tmp2[0].split("[$]"); // split
											// untitled
											// index
			tmp4 = tmp3[0].split("/");// split
										// class
										// name

			StringBuilder tmp5 = new StringBuilder();
			for (for_i = 0; for_i < tmp4.length - 1; for_i++)// reassemble
																// package
																// name
																// with
																// .(dot)
			{
				if (for_i != tmp4.length - 2)
					tmp5.append(tmp4[for_i] + ".");
				else
					tmp5.append(tmp4[for_i]);
			}
			if (pkghdlr.chkName(tmp5.toString())) {
				usrMGson.setClassDetail(ClassObj);
			}

		}
	}
}


