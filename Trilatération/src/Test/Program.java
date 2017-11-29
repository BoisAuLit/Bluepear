package Test;

public class Program {

	public static void main(String[] args) {
		Point A = new Point(-6, 6, 3);
		Point B = new Point(-9, 1, 3);
		Point C = new Point(-4, -1, Math.sqrt(8));
		
		System.out.println("La personne se trouve en X = " + M(A, B, C).getX() + " et Y = " + M(A, B, C).getY());
	}

	public static double d(Point A, Point B)
	{
		double Xa = A.getX(), Ya = A.getY();
		double Xb = B.getX(), Yb = B.getY();
		
		if (Ya == Yb)
			return 0;
		else
			return (Xa - Xb) / (Ya - Yb);
	}
	
	public static double e(Point A, Point B)
	{
		double Xa = A.getX(), Ya = A.getY(), da = A.getD();
		double Xb = B.getX(), Yb = B.getY(), db = B.getD();
		
		if (Ya == Yb)
            return 0;
        return (Xa * Xa - Xb * Xb + Ya * Ya - Yb * Yb + db * db - da * da) / (2 * Ya - 2 * Yb);
	}
	
	public static double a(Point A, Point B)
	{
		return d(A, B) * d(A, B) + 1;
	}
	
	public static double b(Point A, Point B)
    {
		double Xa = A.getX(), Ya = A.getY();
		
        return 2 * d(A, B) * Ya - 2 * Xa - 2 * d(A, B) * e(A, B);
    }

    public static double c(Point A, Point B)
    {
    	double Xa = A.getX(), Ya = A.getY(), da = A.getD();
		
    	return Xa * Xa + Ya * Ya + e(A, B) * e(A, B) - 2 * e(A, B) * Ya - da * da;
    }

    public static double D(Point A, Point B)
    {
    	double d = b(A, B) * b(A, B) - 4 * a(A, B) * c(A, B);;
    	while (d < 0)
    	{
    		A.setD(A.getD() * 1.01);
    		B.setD(B.getD() * 1.01);
    		d = b(A, B) * b(A, B) - 4 * a(A, B) * c(A, B);

    	}
        return d;
    }
    
    public static Point I1(Point A, Point B)
    {
        double x = (-b(A, B) - Math.sqrt(D(A, B))) / (2 * a(A, B));
        double y = e(A, B) - d(A, B) * x;
        Point I1 = new Point(x,y);
        return I1;
    }
    
    public static Point I2(Point A, Point B)
    {
        double x = (-b(A, B) + Math.sqrt(D(A, B))) / (2 * a(A, B));
        double y = e(A, B) - d(A, B) * x;
        Point I2 = new Point(x,y);
        return I2;
    }
    
    public static double distance(Point A, Point B)
    {
    	double Xa = A.getX(), Ya = A.getY();
		double Xb = B.getX(), Yb = B.getY();
		
    	return Math.sqrt((Xb - Xa) * (Xb - Xa) + (Yb - Ya) * (Yb - Ya));
    }
    
    public static int indice_minimum(double[] tab)
    {
    	double min = tab[0];
    	int indice = 0;
    	for(int i=0;i<tab.length;i++)
    	{
    		if(tab[i] < min)
    		{
    			indice = i;
    			min = tab[i];
    		}
    	}
    	
    	return indice;
    }
    
    public static Point M(Point A, Point B, Point C)
    {
    	Point pts[] = { I1(A,B), I2(A,B), I1(B,C), I2(B,C), I1(C,A), I2(C,A) };
    	double tri[] = new double[8];
    	int ind_pts[][] = new int[8][3];
    	for(int i=0;i<2;i++)
    	{
    		for(int j=0;j<2;j++)
    		{
    			for(int k=0;k<2;k++)
    			{
    				ind_pts[i * 4 + j * 2 + k * 1][0]= i;
    				ind_pts[i * 4 + j * 2 + k * 1][1] = j + 2;
    				ind_pts[i * 4 + j * 2 + k * 1][2] = k + 4;
    				tri[i * 4 + j * 2 + k * 1] = distance(pts[i], pts[j + 2]) + distance(pts[j + 2],pts[k + 4]) + distance(pts[k + 4], pts[i]);
    			}
    		}
    	}
    	int ind = indice_minimum(tri);
    	Point pts_finaux[] = { pts[ind_pts[ind][0]], pts[ind_pts[ind][1]], pts[ind_pts[ind][2]] };
    	
    	Point M = new Point((pts_finaux[0].getX() + pts_finaux[1].getX() + pts_finaux[2].getX()) / 3, (pts_finaux[0].getY() + pts_finaux[1].getY() + pts_finaux[2].getY()) / 3);
    	return M;
    }
}
