package volume;
import ij.*;

/**
 * This class is the generic type for all 4D kernels.
 *
 * (c) 1999-2002 Michael Abramoff. All rights reserved.
 * @author: Michael Abramoff
 *
 * Small print:
 * Permission to use, copy, modify and distribute this version of this software or any parts
 * of it and its documentation or any parts of it ("the software"), for any purpose is
 * hereby granted, provided that the above copyright notice and this permission notice
 * appear intact in all copies of the software and that you do not sell the software,
 * or include the software in a commercial package.
 * The release of this software into the public domain does not imply any obligation
 * on the part of the author to release future versions into the public domain.
 * The author is free to make upgraded or improved versions of the software available
 * for a fee or commercially only.
 * Commercial licensing of the software is available by contacting the author.
 * THE SOFTWARE IS PROVIDED "AS IS" AND WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS, IMPLIED OR OTHERWISE, INCLUDING WITHOUT LIMITATION, ANY
 * WARRANTY OF MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE.
 */
public abstract class Kernel4D extends Kernel
{
    public double [][][][] k;             // Kernel for filtering.
    public String toString()
    {
          String s="";
          double sum = 0;

          for (int l=0; l <= halfwidth*2; l++)
          {
                for (int j=0; j <= halfwidth*2; j++)
                {
                      for (int i=0; i <= halfwidth*2; i++)
                      {
                            for (int h=0; h <= halfwidth*2; h++)
                            {
                              s+=IJ.d2s(k[l][j][i][h],4)+"\t";
                              sum += k[l][j][i][h];
                            }
                            s += "\n";
                      }
                      s += "z "+j+"\n";
                }
                s+="t "+l+"\n";
          }
          s+= "\nsum: " + IJ.d2s(sum, 4);
          return s;
    }
}
