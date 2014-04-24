
A simple "PDF Shrinker" implemented in Java using iText.

https://github.com/hossman/pdf-shrinker


This code is based on a quick hack rmuir posted here in Solr's Jira to deal
with a bug in Confluence's PDF generation:

   https://issues.apache.org/jira/browse/SOLR-5819

Building:

  mvn clean package


Usage:  

   java -jar pdf-shrinker.jar big.pdf > small.pdf


NOTE: This code is Licensed under the ASL 2.0, but the produced jar includes (via shade)
the code from iText which is releaesd under the AGPL..

   https://github.com/itext/itextpdf/blob/master/README.md
