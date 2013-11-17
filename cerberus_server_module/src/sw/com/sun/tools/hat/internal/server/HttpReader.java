/*
 * Copyright (c) 1997, 2008, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

/*
 * The Original Code is HAT. The Initial Developer of the
 * Original Code is Bill Foote, with contributions from others
 * at JavaSoft/Sun.
 */

package sw.com.sun.tools.hat.internal.server;

/**
 * Reads a single HTTP query from a socket, and starts up a QueryHandler
 * to server it.
 *
 * @author      Bill Foote
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import sw.cerberus.gson.hprof.AllInstance;
import sw.cerberus.gson.hprof.HistoObject;
import sw.cerberus.gson.hprof.HistoObjectForJson;
import sw.cerberus.gson.hprof.MainObject;
import sw.cerberus.gson.hprof.RefObject;
import sw.cerberus.gson.hprof.Writer;
import sw.com.sun.tools.hat.internal.model.Snapshot;
import sw.com.sun.tools.hat.internal.oql.OQLEngine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HttpReader implements Runnable {

	
	private static String filename_histo = "histo_hprof";
	private static String filename_class = "class_hprof";
	private static String filename_sort = "sorted_instances";
	private MainObject mainobject = new MainObject();
	private MainObject temp_mainobject = new MainObject();
	private ArrayList<MainObject> objectlist = new ArrayList<MainObject>();
	private ArrayList<HistoObjectForJson> histolist = new ArrayList<HistoObjectForJson>();

	private ArrayList<HistoObject> bef_histo = new ArrayList<HistoObject>();
	private ArrayList<HistoObject> aft_histo = new ArrayList<HistoObject>();
	private Map<String,HistoObject> befHistomap = new HashMap<String,HistoObject>();
	
	private Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
			.create();
	private Gson instane_gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
			.create();
	private Writer writer = new Writer();

	private Socket socket;
	private PrintWriter out;

	private Snapshot temp_snapshot;
	private Snapshot snapshot;
	private OQLEngine engine;
	private Snapshot subsnapshot;
	private OQLEngine subengine;
	
	private Map<String, String> ClassId = new HashMap<String, String>();
    private ArrayList<AllInstance> instance_list = new ArrayList<AllInstance>();
    private ArrayList<AllInstance> Top_instance_list = new ArrayList<AllInstance>();
	public String Path;
	private String Path2;

	private long sumedSize = 1;
	private ArrayList<HistoObjectForJson> temp_histoobject;
	
	public HttpReader(Socket s, Snapshot snapshot, OQLEngine engine) {
		this.socket = s;
		this.snapshot = snapshot;
		this.engine = engine;
	}

	public HttpReader(Snapshot snapshot, OQLEngine engine) {
		this.snapshot = snapshot;
		this.engine = engine;
	}
	
	public HttpReader(Snapshot snapshot, OQLEngine engine, Snapshot subsnapshot, OQLEngine subengine, String _path, String _path2) {
		this.snapshot = snapshot;
		this.engine = engine;
		this.subsnapshot = subsnapshot;
		this.subengine = subengine;
		this.Path = _path;
		this.Path2 = _path2;
		this.filename_class = _path + filename_class;
		this.filename_histo = _path + filename_histo;
		this.filename_sort = _path + filename_sort;
	}

	public void run() {
		System.out.println("httpreader is running!");
		InputStream in = null;
		try {

			File f1 = new File("tmp.html");
			out = new PrintWriter(new FileWriter(f1, false));

			out.println("HTTP/1.0 200 OK");
			out.println("Cache-Control: no-cache");
			out.println("Pragma: no-cache");
			out.println();
			
			int data;
			StringBuffer queryBuf = new StringBuffer();
			String query = queryBuf.toString();
			query = java.net.URLDecoder.decode(query, "UTF-8");
			QueryHandler handler = null;

			if (snapshot == null) {
				outputError("The heap snapshot is still being read.");
				return;
			} else if (query.equals("/")) {
				handler = new AllClassesQuery(true, engine != null, Path);
				handler.setUrlStart("");
				handler.setQuery("");
			} else if (query.startsWith("/oql/")) {
				if (engine != null) {
					handler = new OQLQuery(engine);
					handler.setUrlStart("");
					handler.setQuery(query.substring(5));
				}
			} else if (query.startsWith("/oqlhelp/")) {
				if (engine != null) {
					handler = new OQLHelp();
					handler.setUrlStart("");
					handler.setQuery("");
				}
			} else if (query.equals("/allClassesWithPlatform/")) {
				handler = new AllClassesQuery(false, engine != null, Path);
				handler.setUrlStart("../");
				handler.setQuery("");
			} else if (query.equals("/showRoots/")) {
				handler = new AllRootsQuery();
				handler.setUrlStart("../");
				handler.setQuery("");
			} else if (query.equals("/showInstanceCounts/includePlatform/")) {
				handler = new InstancesCountQuery(false);
				handler.setUrlStart("../../");
				handler.setQuery("");
			} else if (query.equals("/showInstanceCounts/")) {
				handler = new InstancesCountQuery(true);
				handler.setUrlStart("../");
				handler.setQuery("");
			} else if (query.startsWith("/instances/")) {
				handler = new InstancesQuery(false);
				handler.setUrlStart("../");
				handler.setQuery(query.substring(11));
			} else if (query.startsWith("/newInstances/")) {
				handler = new InstancesQuery(false, true);
				handler.setUrlStart("../");
				handler.setQuery(query.substring(14));
			} else if (query.startsWith("/allInstances/")) {
				handler = new InstancesQuery(true);
				handler.setUrlStart("../");
				handler.setQuery(query.substring(14));
			} else if (query.startsWith("/allNewInstances/")) {
				handler = new InstancesQuery(true, true);
				handler.setUrlStart("../");
				handler.setQuery(query.substring(17));
			} else if (query.startsWith("/object/")) {
				handler = new ObjectQuery();
				handler.setUrlStart("../");
				handler.setQuery(query.substring(8));
			} else if (query.startsWith("/class/")) {
				handler = new ClassQuery();
				handler.setUrlStart("../");
				handler.setQuery(query.substring(7));
			} else if (query.startsWith("/roots/")) {
				handler = new RootsQuery(false);
				handler.setUrlStart("../");
				handler.setQuery(query.substring(7));
			} else if (query.startsWith("/allRoots/")) {
				handler = new RootsQuery(true);
				handler.setUrlStart("../");
				handler.setQuery(query.substring(10));
			} else if (query.startsWith("/reachableFrom/")) {
				handler = new ReachableQuery();
				handler.setUrlStart("../");
				handler.setQuery(query.substring(15));
			} else if (query.startsWith("/rootStack/")) {
				handler = new RootStackQuery();
				handler.setUrlStart("../");
				handler.setQuery(query.substring(11));
			} else if (query.startsWith("/histo/")) {
				handler = new HistogramQuery();
				handler.setUrlStart("../");
				handler.setQuery(query.substring(7));
			} else if (query.startsWith("/refsByType/")) {
				handler = new RefsByTypeQuery();
				handler.setUrlStart("../");
				handler.setQuery(query.substring(12));
			} else if (query.startsWith("/finalizerSummary/")) {
				handler = new FinalizerSummaryQuery();
				handler.setUrlStart("../");
				handler.setQuery("");
			} else if (query.startsWith("/finalizerObjects/")) {
				handler = new FinalizerObjectsQuery();
				handler.setUrlStart("../");
				handler.setQuery("");
			}
			handler = new InstancesCountQuery(false);
			handler.setUrlStart("../../");
			handler.setQuery("/showInstanceCounts/includePlatform/");
			handler.setOutput(out);
			handler.setSnapshot(snapshot);
			mainobject = handler.run(mainobject);
			this.sumedSize  = handler.sumedSize;

			
			handler = new AllClassesQuery(true, engine != null, Path2);
			handler.setUrlStart("");
			handler.setQuery("");
			handler.setOutput(out);
			handler.setSnapshot(snapshot);
			mainobject = handler.run(mainobject);

			handler = new HistogramQuery();
			handler.initIdMap(); // classid를 담기 위한 Map을 초기화 한다.
			handler.initbefHisto();
			handler.setUrlStart("../");
			handler.setQuery("/histo/");
			handler.setOutput(out);
			handler.setSnapshot(snapshot);
			mainobject = handler.run(mainobject);

			
			befHistomap = handler.getbefHisto();
//			String jsonString = gson.toJson(mainobject);
//			writer.write(filename_histo, jsonString);
			writer = new Writer();
			gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
			mainobject = new MainObject();
			
			
			ClassId = handler.getIdMap();
			Iterator<String> iterator = ClassId.keySet().iterator();

			// 히스토그램에서 가장 높은 순위에 랭크된 클래스 부터 시작해서 해당 클래스의 인스턴스정보를 가져온다.
			System.out.println("Processing " + ClassId.size() + " Classes");
			
			
			int cnt = 0;
			while (iterator.hasNext()) {				
				String key = (String) iterator.next();
				
				handler = new InstancesQuery(true);
				handler.setUrlStart("../");
				handler.setQuery(key);
				handler.setOutput(out);
				handler.setSnapshot(snapshot);
				mainobject = handler.run(mainobject);			
				
				Iterator<AllInstance> iterator2 = handler.getInstanceList().iterator();
				while(iterator2.hasNext()) // put all instances per class in the local instance list
				{
					AllInstance temp_inst = iterator2.next();
					instance_list.add(temp_inst);
				}
			}
			

			
			
			
			Collections.sort(instance_list, new Compare());
			
			int inst_cnt = 0;
			for (AllInstance temp : instance_list) {
				inst_cnt++;
				
				handler = new ObjectQuery();
				handler.setUrlStart("../");
				handler.setQuery(temp.getId());
				handler.setOutput(out);
				handler.setSnapshot(snapshot);
				handler.run(new MainObject());			
				
				temp.setRef(handler.reference_list);
				temp.setleaksuspect(sumedSize);
				Top_instance_list.add(temp);
				
				handler.reference_list = new ArrayList<RefObject>();
                handler.reference_list_id = new ArrayList<String>();
                handler.reference_list_id_chk = new ArrayList<String>();
				if(inst_cnt>10)
					break;
//				System.out.println(temp.getSize());
			}
			
			String instance_jsonstring = instane_gson.toJson(Top_instance_list);
			//System.out.println(Top_instance_list);
			writer.write(filename_sort, instance_jsonstring);			
			
//			jsonString = gson.toJson(mainobject);
//			writer.write(filename_class, jsonString);
			writer = new Writer();
			gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
					.create();
			mainobject = new MainObject();

			
			
//			Iterator<Snapshot> iterator0 = subsnapshot.iterator();
//			int iter0 = 0;
			
			
//			while(iterator0.hasNext())
//			{
//				
//				temp_snapshot = iterator0.next();
				temp_snapshot = subsnapshot;
				
				
				handler = new HistogramQuery();
				handler.iscompare = true;
				handler.initIdMap(); // classid를 담기 위한 Map을 초기화 한다.
				handler.setbefHisto(befHistomap);
				handler.setUrlStart("../");
				handler.setQuery("/histo/");
				handler.setOutput(out);
				handler.setSnapshot(temp_snapshot);
				histolist = handler.run(temp_mainobject).getHisto();
				
				temp_histoobject = new ArrayList<HistoObjectForJson>();
				Iterator<HistoObjectForJson> iterator0_1 = histolist.iterator();
				int iter0_1 = 0;				
				while(iterator0_1.hasNext())
				{
					if(iter0_1>20)
						break;
					temp_histoobject.add(iterator0_1.next());	
					iter0_1++;
				}
				
				//objectlist.add(temp_mainobject);
								
				String jsonString = gson.toJson(temp_histoobject);
				writer.write(filename_histo, jsonString);
				writer = new Writer();
				gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();					
				
//			}
			
			
			
			
			
			System.out.println("End");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ignored) {
			}
		}
	}

	private void outputError(String msg) {
		out.println();
		out.println("<html><body bgcolor=\"#ffffff\">");
		out.println(msg);
		out.println("</body></html>");
	}

	
	static class Compare implements Comparator<AllInstance>
    {

		@Override
		public int compare(AllInstance arg0, AllInstance arg1) {
			// TODO Auto-generated method stub
			return arg0.getSize() > arg1.getSize() ? -1 : arg0.getSize() < arg1.getSize() ? 1:0;
		}
    	
    }

}
