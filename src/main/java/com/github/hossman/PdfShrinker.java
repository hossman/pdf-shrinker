// Licensed to the Apache Software Foundation (ASF) under one or more
// contributor license agreements.  See the NOTICE file distributed with
// this work for additional information regarding copyright ownership.
// The ASF licenses this file to You under the Apache License, Version 2.0
// (the "License"); you may not use this file except in compliance with
// the License.  You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.github.hossman;

import com.itextpdf.text.pdf.*;
import com.itextpdf.text.*;

import java.util.List;
import java.util.HashMap;

public final class PdfShrinker {
    public static void main(String args[]) throws Exception {
        if (1 != args.length) {
            System.err.println("Run this app with a single command line PDF filename");
            System.err.println("The specified file will be read, and a shrunk version written to stdout");
            System.err.println("ie:   java -jar pdf-shrinker.jar big.pdf > small.pdf");
            System.exit(-1);
        }

        Document document = new Document();
        PdfSmartCopy copy = new PdfSmartCopy(document, System.out);
        copy.setCompressionLevel(9);
        copy.setFullCompression();        
        document.open();
        PdfReader reader = new PdfReader(args[0]);
        List<HashMap<String, Object>> bookmarks = SimpleBookmark.getBookmark(reader);
        int pages = reader.getNumberOfPages();
        for (int i = 0; i < pages; i++) {
            PdfImportedPage page = copy.getImportedPage(reader, i+1);
            copy.addPage(page);
        }
        copy.freeReader(reader);
        reader.close();
        copy.setOutlines(bookmarks);
        document.close();
    }
}
