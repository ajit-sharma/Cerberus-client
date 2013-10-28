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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import sw.com.sun.tools.hat.internal.model.Snapshot;
import sw.com.sun.tools.hat.internal.oql.OQLEngine;
import sw.gson.Classes;
import sw.gson.MainObject;
import sw.gson.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HttpReader implements Runnable {

	private static String filename = "Heap-Analysis";
	private MainObject mainobject = new MainObject();

	private Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls()
			.create();
	private Writer writer = new Writer();

	private Socket socket;
	private PrintWriter out;
	private Snapshot snapshot;
	private OQLEngine engine;
	private Map<String, String> ClassId = new HashMap<String, String>();
	
	
	
	public HttpReader(Socket s, Snapshot snapshot, OQLEngine engine) {
		this.socket = s;
		this.snapshot = snapshot;
		this.engine = engine;
	}

	public HttpReader(Snapshot snapshot, OQLEngine engine) {
		this.snapshot = snapshot;
		this.engine = engine;
	}

	public void run() {
		InputStream in = null;
		try {
			// in = new BufferedInputStream(socket.getInputStream());
			// out = new PrintWriter(new BufferedWriter(
			// new OutputStreamWriter(
			// socket.getOutputStream())));
			File f1 = new File(".tmp");
			// File f2 = new File("histo.html");
			out = new PrintWriter(new FileWriter(f1, false));
			// out2 = new PrintWriter(new FileWriter(f2, false));
			// //out3 = new OutputStream();
			// //ostream = new OutputStreamWriter(out3);
			// out = new PrintWriter(ostream);
			// out2 = new PrintWriter(ostream);

			out.println("HTTP/1.0 200 OK");
			out.println("Cache-Control: no-cache");
			out.println("Pragma: no-cache");
			out.println();
			// if (in.read() != 'G' || in.read() != 'E'
			// || in.read() != 'T' || in.read() != ' ') {
			// outputError("Protocol error");
			// }
			int data;
			StringBuffer queryBuf = new StringBuffer();
			// while ((data = in.read()) != -1 && data != ' ') {
			// char ch = (char) data;
			// queryBuf.append(ch);
			// }
			String query = queryBuf.toString();
			query = java.net.URLDecoder.decode(query, "UTF-8");
			QueryHandler handler = null;

			//

			if (snapshot == null) {
				outputError("The heap snapshot is still being read.");
				return;
			} else if (query.equals("/")) {
				handler = new AllClassesQuery(true, engine != null);
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
				handler = new AllClassesQuery(false, engine != null);
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

			handler = new AllClassesQuery(true, engine != null);
			handler.setUrlStart("");
			handler.setQuery("");
			handler.setOutput(out);
			handler.setSnapshot(snapshot);
			mainobject = handler.run(mainobject);

			handler = new HistogramQuery();
			handler.initIdMap(); // classid를 담기 위한 Map을 초기화 한다.
			handler.setUrlStart("../");
			handler.setQuery("/histo/");
			handler.setOutput(out);
			handler.setSnapshot(snapshot);
			mainobject = handler.run(mainobject);

			ClassId = handler.getIdMap();
			Iterator<String> iterator = ClassId.keySet().iterator();

			// 히스토그램에서 가장 높은 순위에 랭크된 클래스 부터 시작해서 해당 클래스의 인스턴스정보를 가져온다.
			System.out.println("Writing " + ClassId.size());
			int cnt = 0;
			while (iterator.hasNext()) {				
				String key = (String) iterator.next();
				
				System.out.println(cnt++ + "/" + ClassId.size() + "	"
						+ ClassId.get(key) + "	" + key);
				handler = new InstancesQuery(true);
				handler.setUrlStart("../");
				handler.setQuery(key);
				handler.setOutput(out);
				handler.setSnapshot(snapshot);
				mainobject = handler.run(mainobject);
				// System.out.println(cnt++ +"/" + ClassId.size() + "	" +
				// ClassId.get(key) + "	" + key);
			}

			String jsonString = gson.toJson(mainobject);
			System.out.println(jsonString);
			writer.write(filename, jsonString);

			// if (handler != null) {
			// handler.setOutput(out);
			// handler.setSnapshot(snapshot);
			// handler.run();
			// } else {
			// outputError("Query '" + query + "' not implemented");
			// }

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

}
