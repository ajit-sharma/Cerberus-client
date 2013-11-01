/*
 * Copyright (c) 2005, 2008, Oracle and/or its affiliates. All rights reserved.
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


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import sw.cerberus.util.HprofConverter;
import sw.com.sun.tools.hat.internal.model.ReachableExcludesImpl;
import sw.com.sun.tools.hat.internal.model.Snapshot;
import sw.com.sun.tools.hat.internal.server.QueryListener;

/**
 *
 * @author      Bill Foote
 */

public class Main {

    private static String VERSION_STRING = "jhat version 2.0";
	private static String filename = "dump";
	private static String extension = ".hprof";
	private static String convfilename = "conv-dump";
    //
    // Convert s to a boolean.  If it's invalid, abort the program.
    //
    private static boolean booleanValue(String s) {
        if ("true".equalsIgnoreCase(s)) {
            return true;
        } else if ("false".equalsIgnoreCase(s)) {
            return false;
        } else {
           // usage("Boolean value must be true or false");
            return false;       // Never happens
        }
    }

    public static void main(String[] args) {
    	
        boolean parseonly = false;
     //   int portNumber = 7006;
        boolean callStack = true;
        
        boolean calculateRefs = false; //true로 하면 에러가 나서 바
        
        String baselineDump = null;
        String excludeFileName = null;
        int debugLevel = 0; 
        
        
        //이상하다.. 아래 두개 명령 수행의 순서가 바뀌면 예외가 발생한다..//
        //.hprof 파일에 대한 변환작업 수행 ///////////////////////
        HprofConverter hconvert = new HprofConverter();
        hconvert.convert();
        ///////////////////////////////////////////////////
      
        
        String fileName = convfilename+extension;
        Snapshot model = null;
        File excludeFile = null;
        if (excludeFileName != null) {
            excludeFile = new File(excludeFileName);
            if (!excludeFile.exists()) {
                System.out.println("Exclude file " + excludeFile
                                    + " does not exist.  Aborting.");
                System.exit(1);
            }
        }

        System.out.println("Reading from " + fileName + "...");
        try {
            model = sw.com.sun.tools.hat.internal.parser.Reader.readFile(fileName, callStack, debugLevel);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        System.out.println("Snapshot read, resolving...");
        model.resolve(calculateRefs);
        System.out.println("Snapshot resolved.");

        if (excludeFile != null) {
            model.setReachableExcludes(new ReachableExcludesImpl(excludeFile));
        }

        if (baselineDump != null) {
            System.out.println("Reading baseline snapshot...");
            Snapshot baseline = null;
            try {
                baseline = sw.com.sun.tools.hat.internal.parser.Reader.readFile(baselineDump, false,
                                                      debugLevel);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.exit(1);
            } catch (RuntimeException ex) {
                ex.printStackTrace();
                System.exit(1);
            }
            baseline.resolve(false);
            System.out.println("Discovering new objects...");
            model.markNewRelativeTo(baseline);
            baseline = null;    // Guard against conservative GC
        }
        
        if ( debugLevel == 2 ) {
            System.out.println("No server, -debug 2 was used.");
            System.exit(0);
        }

        if (parseonly) {
            // do not start web server.
            System.out.println("-parseonly is true, exiting..");
            System.exit(0);
        }    
        
        
        //HPROF 분석 시작  
        
        QueryListener listener = new QueryListener();
        listener.setModel(model);
        Thread t = new Thread(listener, "Query Listener");
        t.setPriority(Thread.NORM_PRIORITY+1);
        t.start();        
        
       
        System.out.println("Parsing Completed");
    }
}
