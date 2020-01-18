/**
Copyright (c) 2015, J. M. Dieterich and B. Hartke
              2017, J. M. Dieterich and B. Hartke
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.

    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.

    * All advertising materials mentioning features or use of this software
      must display the following acknowledgement:

      This product includes software of the ogolem.org project developed by
      J. M. Dieterich and B. Hartke (Christian-Albrechts-University Kiel, Germany)
      and contributors.

    * Neither the name of the ogolem.org project, the University of Kiel
      nor the names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE AUTHOR(S) ''AS IS'' AND ANY
EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.ogolem.properties;

/**
 * The dipole moment property (i.e., the full vector representation of the Dipole class).
 * @author Johannes Dieterich
 * @version 2017-12-15
 */
public class DipoleMoment extends VectorProperty {
    
    private static final long serialVersionUID = (long) 20171215;
    public static final double MAXABSDIPOLE = 1000;
    
    
    public DipoleMoment(final double[] dipoleMoment){
        super(dipoleMoment, false); // no norming wanted ATM
        if(dipoleMoment == null || dipoleMoment.length != 3){throw new RuntimeException("Dipole moment in constructor must be non-null and of cartesian type.");}
    }
    
    private DipoleMoment(final DipoleMoment orig){
        super(orig);
    }
    
    @Override
    public DipoleMoment clone(){
        return new DipoleMoment(this);
    }
    
    @Override
    public boolean makeSensible(){
        
        boolean ret = false;
        for(int i = 0; i < 3; i++){
            if(Double.isInfinite(this.data[i]) || Double.isNaN(this.data[i]) || Math.abs(this.data[i]) >= MAXABSDIPOLE){
                this.data[i] = -1000.0;
                ret = true;
            }
        }
        return ret;
    }
    
    @Override
    public String printableProperty(){
        return "" + this.data[0] + "\t" + this.data[1] + "\t" + this.data[2];
    }
    
    @Override
    public String name() {
        return "DIPOLE MOMENT";
    }
    
    public double[] getDipoleMoment(){
        return this.data;
    }

    @Override
    protected boolean ensureCorrectProperty(Property p) {
        return (p instanceof DipoleMoment);
    }
}
