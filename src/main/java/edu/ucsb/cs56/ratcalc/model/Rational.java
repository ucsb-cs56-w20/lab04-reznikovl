/** A class to represent a rational number
  with a numerator and denominator

  @author P. Conrad for CS56 F16

*/
package edu.ucsb.cs56.ratcalc.model;

public class Rational {

	private int num;
	private int denom;

	/** 
	  greatest common divisor of a and b
	  @param a first number
	  @param b second number
	  @return gcd of a and b
	  */
	public static int gcd(int a, int b) {
		if (a==0)
			return b;
		else if (b==0)
			return a;
		else
			return gcd(b%a, a);
	}
	/** 
	  default rational constructor, creating 1
	  */
	public Rational() {
		this.num = 1;
		this.denom = 1;
	}

	/** 
	  Constructs a rational
	  @param num numerator
	  @param denom denominator
	  */
	public Rational(int num, int denom) {
		if (denom== 0) {
			throw new IllegalArgumentException("denominator may not be zero");
		}
		this.num = num;
		this.denom = denom;
		if (num != 0) {
			int gcd = Rational.gcd(num,denom);
			this.num /= gcd;
			this.denom /= gcd;
		}
		if (this.denom < 0) {
			this.denom *= -1;
			this.num *= -1;
		}
	}

	public String toString() {
		if (denom == 1 || num == 0)
			return "" + num;
		return num + "/" + denom;
	}

	public int getNumerator() { return this.num; }
	public int getDenominator() { return this.denom; }

	/** 
	  multiplies the current rational with another rational
	  @param r the rational to be multiplied with
	  @return the product of the rational and another rational
	  */
	public Rational times(Rational r) {
		return new Rational(this.num * r.num,
				this.denom * r.denom);
	}


	/** 
	  @param a first number to be multiplied
	  @param b second number to be multiplied
	  @return product of a and b
	  */
	public static Rational product(Rational a, Rational b) {
		return new Rational(a.num * b.num,
				a.denom * b.denom);
	}

	/** 
	  lowest common multiplied of a and b
	  @param a first number
	  @param b second number
	  @return lcm of a and b
	  */
	public static int lcm(int a, int b) {
		return Math.abs(a*b / gcd(a,b));
	}

	/** 
	  sums with another rational
	  @param r number to be added
	  @return sum of the two numbers
	  */
	public Rational plus(Rational r) {
		return sum(this, r);
	}

	/** 
	  sum of a and b
	  @param a first number
	  @param b second number
	  @return sum of a and b
	  */
	public static Rational sum(Rational a, Rational b) {
		int lcm = lcm(a.denom, b.denom);
		int num1 = a.num*(lcm/a.denom);
		int num2 = b.num*(lcm/b.denom);
		return new Rational(num1 + num2, lcm);
	}

	/** 
	  difference of a and b
	  @param a first number
	  @param b second number
	  @return difference of a and b
	  */
	public static Rational difference(Rational a, Rational b) {
		return sum(a, new Rational(-b.num, b.denom));
	}

	/** 
	  difference with another rational
	  @param r number to be subtracted
	  @return differences of the two numbers
	  */
	public Rational minus(Rational r) {
		return difference(this, r);
	}

	/** 
	  
	  @return reciprocal of the current rational
	  */
	public Rational reciprocalOf() {
		if (num == 0) {
			throw new java.lang.ArithmeticException("divide by zero");
		}
		return new Rational(denom, num);
	}

	/** 
	  quotient of a and b
	  @param a first number
	  @param b second number
	  @return quotient of a and b
	  */
	public static Rational quotient(Rational a, Rational b) {
		return product(a, b.reciprocalOf());
	}

	/** 
	  divides current number by a rational
	  @param r number to be divided by
	  @return quotient of a and b
	  */
	public Rational dividedBy(Rational r) {
		return quotient(this, r);
	}


	/** 
	  For testing getters.  
	  @param args unused
	  */

	public static void main (String [] args) {
		Rational r = new Rational(5,7);
		System.out.println("r.getNumerator()=" + r.getNumerator());
		System.out.println("r.getDenominator()=" + r.getDenominator());
	}


}
