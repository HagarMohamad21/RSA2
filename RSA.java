/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsa_encyption;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hagarmohamad75
 */
import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
/**
 *
 * @author hagarmohamad75
 */
public class RSA {
    public static BigInteger p;
    public  static BigInteger q;
    public  static BigInteger n;
   public  static BigInteger phi;
   public  static BigInteger e;
   public  static BigInteger d;
    private int  bitlength = 512;
    private Random     r;
public RSA(){
 r = new Random();
        p = BigInteger.probablePrime(bitlength, r);
        q = BigInteger.probablePrime(bitlength, r);
        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitlength , r);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) //as long as gcd >1 and e is still smaller than phi
        {
            e.add(BigInteger.ONE);
        }
        d = e.modInverse(phi);
    }
 public RSA(BigInteger e, BigInteger d, BigInteger n)
    {
        this.e = e;
        this.d = d;
        this.n = n;
    }
 public static BigInteger [] extendedEuclid (BigInteger a, BigInteger N){
    BigInteger [] ans = new BigInteger[3];
    BigInteger ax, yN;
    
    if (N.equals(BigInteger.ZERO)) {
	ans[0] = a;
	ans[1] = BigInteger.ONE;
	ans[2] = BigInteger.ZERO;
	return ans;
    }

    ans = extendedEuclid (N, a.mod (N));
    ax = ans[1];
    yN = ans[2];
    ans[1] = yN;
    BigInteger temp = a.divide(N);
    temp = yN.multiply(temp);
    ans[2] = ax.subtract(temp);
    return ans;
}
 public static BigInteger crt(BigInteger key, BigInteger p, BigInteger q, BigInteger m){
  BigInteger dp, dq, qInverse, m1, m2, h;

   dp = key.mod(p.subtract(BigInteger.ONE));
   dq = key.mod(q.subtract(BigInteger.ONE));
   qInverse =q.modInverse(p);
   
   m1 = m.modPow(dp,p);
   m2 = m.modPow(dq,q);
   h = qInverse.multiply(m1.subtract(m2)).mod(p);
   m = m2.add(h.multiply(q));

   return m;
}
}
