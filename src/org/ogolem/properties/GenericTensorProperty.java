/**
Copyright (c) 2018, J. M. Dieterich and B. Hartke
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
 * A generic tensor property, i.e., a bare bones implementation.
 * @author Johannes Dieterich
 * @version 2018-01-02
 */
public class GenericTensorProperty extends TensorProperty {

    private static final long serialVersionUID = (long) 20180102;
    
    private final long id;
    
    public GenericTensorProperty(final double[][][] data, final boolean normDifferences, final long id){
        super(data, normDifferences);
        this.id = id;
    }
    
    private GenericTensorProperty(final GenericTensorProperty orig){
        super(orig);
        this.id = orig.id;
    }
    
    @Override
    public GenericTensorProperty clone() {
        return new GenericTensorProperty(this);
    }

    @Override
    protected boolean ensureCorrectProperty(Property p) {
        if(!(p instanceof GenericTensorProperty)) {return false;}
        
        final GenericTensorProperty gp = (GenericTensorProperty) p;
        return (gp.id == id);
    }

    @Override
    public boolean makeSensible() {
        
        if(data == null){return false;}
        
        boolean manip = false;
        for(int i = 0; i < data.length; i++){
            for(int j = 0; i < data[i].length; j++){
                for(int k = 0; k < data[i][j].length; k++){
                    if(Double.isInfinite(data[i][j][k]) || Double.isNaN(data[i][j][k])){
                        this.data[i][j][k] = 0.0;
                        manip = true;
                    }
                }
            }
        }
        
        return manip;
    }

    @Override
    public String printableProperty() {
        
        if(data == null){return "NULL'D";}
        
        String s = "";
        for(int i = 0; i < data.length; i++){
            for(int j = 0; i < data[i].length; j++){
                for(int k = 0; i < data[i][j].length; k++){
                    s += "\t" + data[i][j][k];
                }
                s += "\n";
            }
            s += "\n";
        }
        
        return s;
    }

    @Override
    public String name() {
        return "GENERICTENSOR" + id;
    }
}
