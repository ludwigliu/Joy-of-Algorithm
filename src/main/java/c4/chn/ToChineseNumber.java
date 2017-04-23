package c4.chn;

import java.util.Stack;
/**
 * This is the work created by Ludwig Liu, it's licensed by GPLv3.
 * Please goto http://www.gnu.org/licenses/gpl.html to get a copy of it.
 * Should you have any questions please send an email to me. Have fun! ^_^
 * Initial Developer: Ludwig Liu
 * Email: ludwigliu@live.com
 */

/**
 * Name: ToChineseNumber
 * Specification: Convert Arabic numbers to Chinese numbers.
 *
 * Created by ludwig on 22/04/2017.
 *
 * History:
 * ----------------------------------------------
 * |   Date  |   Name   |  Note
 * | 22/04/2017 |  ludwig |  Initial version 1.0
 * ----------------------------------------------
 */
public class ToChineseNumber {

    public static void main(String[] args) {
        ToChineseNumber conv = new ToChineseNumber();
        System.out.println(conv.convert(429496729));
    }

    public String convert(int arabicNumber) {
        Stack<String> chnSections = new Stack<String>();
        String chnNumber = "";
        boolean needZero = false;
        String temp = "";
        int unitPos = 0;

        while( arabicNumber > 0 ) {
            int section = arabicNumber % 10000;
            if( needZero ) {
                chnSections.push(ChineseNumberChars.CHN_NUMBER_CHARS[0]);
            }
            temp = convertSection(section) + ( ( section == 0 ) ?
                                                    ChineseNumberChars.CHN_SECTION_UNIT[0] :
                                                    ChineseNumberChars.CHN_SECTION_UNIT[unitPos] );
            chnSections.push(temp);
            // K1
            // if current section less than 1000 but not 0, there should be a ZERO before this section
            needZero = ( section < 1000 ) && ( section > 0 );

            arabicNumber = arabicNumber / 10000;
            unitPos++;
        }

        while( !chnSections.empty() ) chnNumber += chnSections.pop();

        return chnNumber;
    }

    // Convert each section to Chinese number
    public String convertSection(int section) {
        String chnSectionNumber = "";
        Stack<String> sectionNumber = new Stack<String>();
        int pos = 0;
        boolean needZero = false;

        while( section > 0 ) {
            int v = section % 10;
            if( v == 0 ) {
                if( needZero ) {
                    sectionNumber.push(ChineseNumberChars.CHN_NUMBER_CHARS[v]);
                    needZero = false; // multiple 0 only append one zero
                }
            } else {
                // K2
                // if current position is not 0, assume that the higher position will have a 0
                // and only if it does have a 0, add it.
                needZero = true;
                sectionNumber.push(ChineseNumberChars.CHN_NUMBER_CHARS[v] + ChineseNumberChars.CHN_SECTION_CHARS[pos]);
            }

            section = section / 10;
            pos++;
        }

        while( !sectionNumber.empty() ) chnSectionNumber += sectionNumber.pop();

        return chnSectionNumber;
    }
}
