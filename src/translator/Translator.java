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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vishnukumaar
 */
public class Translator {

    /**
     * @param args the command line arguments
     * 1st parameter is name of the file(path inclusive) to be translated
     * 2nd parameter is the language the file should be translated into
     */
    public static void main(String[] args) {
    
        try {
            Language languageToBeTranslatedInto = Language.ENGLISH;
            if(args[1].toLowerCase().contains("fr")){
                languageToBeTranslatedInto = Language.FRENCH;
            }else if(args[1].toLowerCase().contains("es")){
                languageToBeTranslatedInto = Language.SPANISH;
            }else if(args[1].toLowerCase().contains("de")){
                languageToBeTranslatedInto = Language.GERMAN;
            }else if(args[1].toLowerCase().contains("pt")){
                languageToBeTranslatedInto = Language.PORTUGUESE;
            }

            new FileManager().translateFile(args[0],languageToBeTranslatedInto);

            } catch (IOException ex) {
                Logger.getLogger(Translator.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
