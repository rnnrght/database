/**

Copyright (C) SYSTAP, LLC 2006-2007.  All rights reserved.

Contact:
     SYSTAP, LLC
     4501 Tower Road
     Greensboro, NC 27410
     licenses@bigdata.com

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; version 2 of the License.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/
package com.bigdata.rdf.spo;

import java.util.Comparator;

import com.bigdata.rdf.model.StatementEnum;

/**
 * Imposes s:p:o ordering based on termIds only (ignores {@link StatementEnum}).
 * 
 * @author <a href="mailto:thompsonbry@users.sourceforge.net">Bryan Thompson</a>
 * @version $Id$
 */
public class SPOComparator<T extends ISPO> implements Comparator<T> {

    public static final transient Comparator<ISPO> INSTANCE = new SPOComparator<ISPO>();
    
    private SPOComparator() {
        
    }
    
    public int compare(ISPO stmt1, ISPO stmt2) {

        if (stmt1 == stmt2)
            return 0;

        /*
         * Note: logic avoids possible overflow of [long] by not computing the
         * difference between two longs.
         */
        int ret;
        
        ret = stmt1.s() < stmt2.s() ? -1 : stmt1.s() > stmt2.s() ? 1 : 0;
        
        if( ret == 0 ) {
        
            ret = stmt1.p() < stmt2.p() ? -1 : stmt1.p() > stmt2.p() ? 1 : 0;
            
            if( ret == 0 ) {
                
                ret = stmt1.o() < stmt2.o() ? -1 : stmt1.o() > stmt2.o() ? 1 : 0;
                
            }
            
        }

        return ret;
        
    }
    
}
