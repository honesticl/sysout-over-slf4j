/*
 * Copyright (c) 2004-2005 SLF4J.ORG
 *
 * All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute, and/or sell copies of  the Software, and to permit persons
 * to whom  the Software is furnished  to do so, provided  that the above
 * copyright notice(s) and this permission notice appear in all copies of
 * the  Software and  that both  the above  copyright notice(s)  and this
 * permission notice appear in supporting documentation.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR  A PARTICULAR PURPOSE AND NONINFRINGEMENT
 * OF  THIRD PARTY  RIGHTS. IN  NO EVENT  SHALL THE  COPYRIGHT  HOLDER OR
 * HOLDERS  INCLUDED IN  THIS  NOTICE BE  LIABLE  FOR ANY  CLAIM, OR  ANY
 * SPECIAL INDIRECT  OR CONSEQUENTIAL DAMAGES, OR  ANY DAMAGES WHATSOEVER
 * RESULTING FROM LOSS  OF USE, DATA OR PROFITS, WHETHER  IN AN ACTION OF
 * CONTRACT, NEGLIGENCE  OR OTHER TORTIOUS  ACTION, ARISING OUT OF  OR IN
 * CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 *
 * Except as  contained in  this notice, the  name of a  copyright holder
 * shall not be used in advertising or otherwise to promote the sale, use
 * or other dealings in this Software without prior written authorization
 * of the copyright holder.
 *
 */
package org.slf4j;

// WARNING
// WARNING Modifications MUST be made to the original file found at
// WARNING $SLF4J_HOME/src/filtered-java/org/slf4j/MarkerFactory.java
// WARNING


/**
 * A static MarkerFactory bound to a specific {@link IMarkerFactory} instance at
 * at compile time. 
 * 
 * @author Ceki Gulcu
 */
public class MarkerFactory {
  static IMarkerFactory markerFactory;

  // 
  // WARNING Do not modify copies but the original at
  //         $SLF4J_HOME/src/filtered-java/org/slf4j/
  //
  static {
    String markerFactoryClassStr = "org.slf4j.impl.@MARKER_FACTORY_IMPL_PREFIX@MarkerFactory";
    try {
      markerFactory = new org.slf4j.impl.@MARKER_FACTORY_IMPL_PREFIX@MarkerFactory();
     } catch (Exception e) {
       // we should never get here
       Util.reportFailure(
           "Could not instantiate instance of class [" + markerFactoryClassStr + "]",
           e);
     }
      
  }
  
  /**
   * Return a Marker instnace as specified by the name parameter using the 
   * previously bound  {@link IMakerFactory marker factory instance}.
   * @param name The name of the Marker.
   * @return marker
   */
  public static Marker getMarker(String name) {
    return markerFactory.getMarker(name);
  }
}