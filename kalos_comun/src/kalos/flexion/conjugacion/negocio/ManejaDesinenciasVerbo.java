package kalos.flexion.conjugacion.negocio;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kalos.B.E;
import kalos.B.P;
import kalos.B.Z;
import kalos.B._;
import kalos.B.j;
import kalos.B.p;
import kalos.E.E.z;
import kalos.G.I;
import kalos.K.k;
import kalos.enumeraciones.FuerteDebil;
import kalos.enumeraciones.Modo;
import kalos.enumeraciones.Persona;
import kalos.enumeraciones.Tiempo;
import kalos.enumeraciones.Voz;

public class ManejaDesinenciasVerbo
{
  private List<D>[][][][][][] B = new List[7][4][6][7][2][32];
  _A[] A = { new _A(1, Z.D, p.C, j.M, _.H, 1), new _A(1, Z.D, p.C, j.M, _.H, 2), new _A(1, Z.D, p.C, j.M, _.B, 1), new _A(1, Z.D, p.C, j.M, _.B, 2), new _A(1, Z.D, p.C, j.M, _.A, 1), new _A(1, Z.D, p.C, j.M, _.A, 2), new _A(1, Z.D, p.C, j.M, _.C, 1), new _A(1, Z.D, p.C, j.M, _.C, 2), new _A(1, Z.D, p.C, j.M, _.F, 1), new _A(1, Z.D, p.C, j.M, _.F, 2), new _A(1, Z.D, p.A, j.O, _.B, 1), new _A(1, Z.D, p.A, j.O, _.B, 2), new _A(1, Z.D, p.A, j.O, _.D, 1), new _A(1, Z.D, p.A, j.O, _.D, 2), new _A(1, Z.D, p.A, j.O, _.F, 1), new _A(1, Z.D, p.A, j.O, _.F, 2), new _A(1, Z.D, p.E, j.Q, _.F, 1), new _A(1, Z.D, p.E, j.Q, _.F, 2), new _A(1, Z.D, p.E, j.O, _.F, 1), new _A(1, Z.D, p.E, j.O, _.F, 2), new _A(1, Z.D, p.E, j.L, _.F, 1), new _A(1, Z.D, p.E, j.L, _.F, 2), new _A(1, Z.A, p.E, j.Q, _.F, 1), new _A(1, Z.A, p.E, j.Q, _.F, 2), new _A(1, Z.A, p.E, j.O, _.F, 1), new _A(1, Z.A, p.E, j.O, _.F, 2), new _A(1, Z.A, p.E, j.L, _.H, 1), new _A(1, Z.A, p.E, j.L, _.H, 2), new _A(1, Z.C, p.E, j.O, _.H, 1), new _A(1, Z.C, p.E, j.O, _.H, 2) };
  
  public Desinencia[] getDesinencias(int paramInt, Voz paramZ, Modo paramp, Tiempo paramj, FuerteDebil paramP, Persona param_)
  {
    int i = Z.getInt(paramZ);
    int j = p.getInt(paramp);
    int k = j.getInt(paramj);
    int m = P.getInt(paramP);
    int n = _.getInt(param_);
    List localList = this.B[paramInt][i][j][k][m][n];
    if (localList == null) {
      return null;
    }
    try
    {
      return (D[])localList.toArray(new D[0]);
    }
    catch (Exception localException)
    {
      System.out.println("error al tratar de obtener la desinencia ***********");
      System.out.println("juego=" + paramInt);
      System.out.println("voz=" + paramZ);
      System.out.println("modo=" + paramp);
      System.out.println("tiempo=" + paramj);
      System.out.println("persona=" + param_);
      System.out.println("***************************************************");
      throw new RuntimeException("error en ManejaDesinencias.getDesinencias()");
    }
  }
  
  private void A(int paramInt1, Z paramZ, p paramp, j paramj, P paramP, _ param_, String paramString, int paramInt2, E paramE)
  {
    int i = Z.getInt(paramZ);
    int j = p.getInt(paramp);
    int k = j.getInt(paramj);
    int m = P.getInt(paramP);
    int n = _.getInt(param_);
    try
    {
      if (this.B[paramInt1][i][j][k][m][n] == null) {
        this.temaComido[paramInt1][i][j][k][m][n] = new ArrayList();
      }
      this.temaComido[paramInt1][i][j][k][m][n].add(new D(paramString, paramInt2, paramE, paramInt1));
    }
    catch (Exception localException)
    {
      System.out.println("error al tratar de obtener la desinencia ***********");
      System.out.println("juego=" + paramInt1);
      System.out.println("voz=" + paramZ);
      System.out.println("modo=" + paramp);
      System.out.println("tiempo=" + paramj);
      System.out.println("persona=" + param_);
      System.out.println("***************************************************");
      throw new RuntimeException("error en ManejaDesinencias.setDesinencias()");
    }
  }
  
  public F(boolean paramBoolean, z paramz)
  {
    List localList;
    if (!paramBoolean) {
      localList = paramz.seleccionaRestringidas();
    } else {
      localList = paramz.seleccionaTodas();
    }
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      k localk = (k)localIterator.next();
      A(localk.getJuego(), localk.getVoz(), localk.getModo(), localk.getTiempo(), localk.getFuerte(), localk.getPersona(), I.strBetaACompleto(localk.getDesinencia()), localk.getSilaba(), localk.getAcento());
    }
  }
  
  class _A
  {
    public int E;
    public j G;
    public _ D;
    public int B;
    public Z  C;
    public p F;
    
    public _A(int paramInt1, Z paramZ, p paramp, j paramj, _ param_, int paramInt2)
    {
      this.E = paramInt1;
      this.C = paramZ;
      this.F = paramp;
      this.G = paramj;
      this.D = param_;
      this.B = paramInt2;
    }
  }
}
