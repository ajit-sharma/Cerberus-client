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

import sw.gson.trace.MainTrace;
import sw.gson.trace.MethodNode;

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
	private MethodNode MNode;
	private Vector<String> strvec;
	private boolean another;
    
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
				Mtrace = new MainTrace();
				MNode = new MethodNode();
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
								//Mtrace.setAll(Integer.parseInt(words[0]), Double.parseDouble(words[1]), Double.parseDouble(words[3]));										
							}
							
							// Parsing Part 2
							//Inclusive elapsed times for each method and its parents and children,
							//sorted by inclusive time.
							
							else if(!words[i].isEmpty() && separator == 2) {
								if(words[i].compareTo("----------------------------------------------------") == 0) {
									wordcnt = 0;
									another = false;
									MNode = new MethodNode();
									System.out.println();
									System.out.println();
								} 
								else if(words[i].compareTo("+++++++++++++++++++++++++") == 0) {
									
									wordcnt = 0;
									another = true;									
									System.out.println("+++++++++++++++++++++++++");
								
								}
								else {									
									
									strvec.add(words[i]);
									
									
								}//else if "----...---"					
								
							
							}//end else if part2
							
						}//end for ( printing line )
						
						
						
						if(separator == 1)
							Mtrace.setName(sentence.toString());		
						
						// Parsing Part 2
						//Inclusive elapsed times for each method and its parents and children,
						//sorted by inclusive time.
						else if(separator == 2)
						{
							//System.out.println(strvec.size());
							if(strvec.size() == 6) // Parent or Child or Main
							{
								if(strvec.firstElement().contains("["))//MAIN
								{
									MNode.ascendWhois();
									MNode.setMain(strvec.get(0),strvec.get(1),strvec.get(2),strvec.get(3),strvec.get(4),strvec.get(5));
									System.out.println(strvec.toString());
									//System.out.print(strvec.firstElement());
									MNode.ascendWhois();
								}
								else//Parent or Child
								{
									if(MNode.getWhois() == 0)//PARENT
									{
										MNode.setParent(strvec.get(0),strvec.get(1),strvec.get(2),strvec.get(3),strvec.get(4),strvec.get(5));
										System.out.println(strvec.toString());
//										System.out.print("[PARENT] ");
									}
									
									else if(MNode.getWhois() == 2)//CHILD
									{
										MNode.setParent(strvec.get(0),strvec.get(1),strvec.get(2),strvec.get(3),strvec.get(4),strvec.get(5));
										System.out.println(strvec.toString());
//										System.out.print("[CHILED] ");
									}
								}
							}	//
							else if(strvec.size() == 5) // MAIN or A-Parent or A-Child
							{
								if (another) {
									if (MNode.getWhois() == 0)// A-PARENT
									{
										MNode.setaParent(strvec.get(0),strvec.get(1),strvec.get(2),strvec.get(3),strvec.get(4));
										System.out.println(strvec.toString());
										//System.out.println(strvec.toString());
									}

									else if (MNode.getWhois() == 2)// A-CHILD
									{
										MNode.setaParent(strvec.get(0),strvec.get(1),strvec.get(2),strvec.get(3),strvec.get(4));
										System.out.println(strvec.toString());
//										System.out.print("[A-CHILD] ");
									}
								}
								else
								{
									if(strvec.firstElement().contains("["))//MAIN
									{
										MNode.ascendWhois();
										MNode.setMain(strvec.get(0),strvec.get(1),strvec.get(2),strvec.get(3),strvec.get(4));
										//System.out.print(strvec.firstElement());
//										System.out.print("[main] ");
										System.out.println(strvec.toString());
										MNode.ascendWhois();
									}
									else//Parent or Child
									{
										if(MNode.getWhois() == 0)//PARENT??????????????
										{
											System.out.println(strvec.toString());
//											System.out.print("[PARENT] ");
										}
										
										else if(MNode.getWhois() == 2)//CHILD????????????????
										{
											System.out.println(strvec.toString());
//											System.out.print("[CHILED] ");
										}
									}
								}
								
							}
							else if(strvec.size() == 3)//EXCL
							{
								MNode.setExcl(strvec.get(0),strvec.get(1),strvec.get(2));
								System.out.println(strvec.toString());
							}
							MNode.setMethodname(sentence.toString());
							if(MNode.getWhois() == 1)
								MNode.ascendWhois();
							//System.out.println();
						}
						
						
						sentence.delete(0, sentence.length());
						strvec.clear();
						//System.out.print(sentence.toString());	
						
						//if(separator == 1)
							//System.out.println();
						
					}
					// out.println(line);
					//System.out.println(scanner.next());
//					if(separator == 2)
//						System.out.println();				
					
				}//end while - printing a line completed
				scanner.close();
				br.close();

			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1); //명령어가 없으면 종료.
			}
		}
	}
}
