/* FixedCharAtom.java
 * =========================================================================
 * This file is part of the JMathTeX Library - http://jmathtex.sourceforge.net
 * 
 * Copyright (C) 2004-2007 Universiteit Gent
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * A copy of the GNU General Public License can be found in the file
 * LICENSE.txt provided with the source distribution of this program (see
 * the META-INF directory in the source jar). This license can also be
 * found on the GNU website at http://www.gnu.org/licenses/gpl.html.
 * 
 * If you did not receive a copy of the GNU General Public License along
 * with this program, contact the lead developer, or write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 * 
 */

package be.ugent.caagt.jmathtex.atom;

import be.ugent.caagt.jmathtex.Char;
import be.ugent.caagt.jmathtex.CharFont;
import be.ugent.caagt.jmathtex.SimpleXmlWriter;
import be.ugent.caagt.jmathtex.TeXEnvironment;
import be.ugent.caagt.jmathtex.TeXFont;
import be.ugent.caagt.jmathtex.box.Box;
import be.ugent.caagt.jmathtex.box.CharBox;

/**
 * 表示固定的字符 (不依赖于字型, text style)
 * An atom representing a fixed character (not depending on a text style).
 */
class FixedCharAtom extends CharSymbol {

   private final CharFont cf; // 相当于 char, fontid

   public FixedCharAtom(CharFont c) {
      cf = c;
   }

   public CharFont getCharFont(TeXFont tf) {
      return cf;
   }

   public Box createBox(TeXEnvironment env) {
      TeXFont tf = env.getTeXFont();
      Char c = tf.getChar2(cf, env.getStyle());
      return new CharBox(c);
      
      // 对比: CharAtom 的 createBox():
      // TeXFont tf = env.getTeXFont();
      // Char ch = getChar(tf, env.getStyle()); --> 这里依赖 textStyle 的值得到不同字体.
      // return new CharBox(ch);
   }

   @Override
   public String toString() {
	   return "FixedCharAtom{cf=" + cf + "}";
   }
   
   public void dump() {
	   System.out.println(toString());
   }

   /**
    * 输出 
    *   <FixedCharAtom ccode='97' fontId='1' />
    */
   public void toXml(SimpleXmlWriter sxw, Object hint) {
	   sxw.appendRaw("<FixedCharAtom ").attribute("ccode", (int)(cf.c))
	   	.blank().attribute("fontId", cf.fontId)
	   	.appendRaw(">").ln();
	   
	   super.superToXml(sxw);
	   
	   sxw.endElement("FixedCharAtom").ln();
   }
}
