/**
Copyright (c) 2014, J. M. Dieterich
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
package org.ogolem.locopt.numalhelpers;

import numal.AP_linemin_method;
import org.ogolem.generic.ContinuousProblem;
import org.ogolem.generic.GenericBackend;

/**
 * A simple wrapper for the NUMAL AP_linemin_method
 * @author Johannes Dieterich
 * @version 2014-12-16
 */
public class NumalGradientWrapper<E,T extends ContinuousProblem<E>> implements AP_linemin_method{

    private final GenericBackend<E,T> back;
    private final double[] p;
    private final double[] g;
    private int iter = 0;

    public NumalGradientWrapper(final GenericBackend<E,T> back, final double[] p){
        this.back = back;
        this.p = p;
        this.g = new double[p.length];
    }

    @Override
    public double funct(final int length, final double[] paramsNumal, final double[] gradient){

        iter++;
        System.arraycopy(paramsNumal, 1, p, 0, length);
        final double fitness = back.gradient(p, g, iter);
        System.arraycopy(g, 0, gradient, 1, length);

        return fitness;
    }
}
