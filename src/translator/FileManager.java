/*
 * The MIT License
 *
 * Copyright 2016 vishnukumaar.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package translator;

import com.memetix.mst.language.Language;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author vishnukumaar
 */
public class FileManager {
    
    void translateFile(String inputFilePath,Language languageToBeTranslatedInto) throws IOException{

        BingTranslator bingTranslator = new BingTranslator();
        File dir = new File(".");
        
        String dest = dir.getCanonicalPath() + File.separator + languageToBeTranslatedInto+".json";

        /*Format of json produced by sketch i18n 
         *{
         * "english": "english" 
         *}
         */
        
        //create pattern to extract value 
        Pattern pattern = Pattern.compile(": \".*\"");
        
        try( BufferedReader br = new BufferedReader(new java.io.FileReader(inputFilePath));
             FileWriter fstream = new FileWriter(dest);
             BufferedWriter out = new BufferedWriter(fstream);
            )
        {
                String currentLine;
                out.write("{");
                
                while( (currentLine = br.readLine()) != null ){
                    
                    //extract value from key-value pair
                    Matcher matcher = pattern.matcher(currentLine);
                    if(matcher.find()){
                        String matchedString =  matcher.group(0);
                        String stringToBeTranslated = matchedString.substring(3, matchedString.length()- 1);
                        
                        //Uncomment to view progress(strings which are being translated)
                        //System.out.println(stringToBeTranslated);

                        out.newLine();
                        String stringToBeWritten="";
                        
                        //sketch i18n does not work if the last keyvalue pair has an extra comma 
                        //hence the comma will be added if not last line
                        if(currentLine.endsWith("\",")){
                            stringToBeWritten = "  \""+ stringToBeTranslated + "\": \""+ 
                                                bingTranslator.translate(stringToBeTranslated, languageToBeTranslatedInto)
                                                +"\",";
                        }
                        //comma will not be added if last line
                        else{
                            stringToBeWritten = "  \""+ stringToBeTranslated + "\": \""+ 
                                                bingTranslator.translate(stringToBeTranslated, languageToBeTranslatedInto)
                                                +"\"";
                        }
                        out.write(stringToBeWritten);
                    }
                }
                out.newLine();
                out.write("}");
                    
                out.close();
        }catch(IOException e) {
            e.printStackTrace();
        }
    
        System.out.println(languageToBeTranslatedInto+".json Translated Successfully");

    }
}
