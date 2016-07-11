package stable_marriage;
 

 
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
 
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;


 

public class Preference_Matrix {
	public  final static int sonuc_matis_boy =7;
	public  final static int N =3;
	public String blocking_pairs="";	
	private JFrame frame;
 
    public   int[][][] preference =  new int[N][N][2]; 
    public int prefere[][] =  new int[2*N][N]; 
    static int terciharalýgý[][]=new int[2*N][3]	; 
    public int bozan[] = new int[3] ;
    public int flag_continue;
	JTextArea textArea = new JTextArea();
	
    public static int numClicks_stable_marriage_men_optimal = 0;
    public static int numClicks_stable_marriage_women_optimal = 0;
    public static int numClicks_nlognsinirlamalý_men =0;
    public static int numClicks_nlognsinirlamalý_women =0;
    public static int numClicks_nlognsinirlamasiz_men =0;
    public static int numClicks_nlognsinirlamasiz_women =0;
    public static int numClicks_maybelatter =0;
    public static int numClicks_equitable =0;
    public static int numClicks_Backwards_GS =0;    
    public static int numClicks_probablity =0;
    public static int numClicks_circuled =0;
    public static int numClicks_circuled_sadece_bekar =0;
    public static int numClicks_circuled_kaldigi_yerden =0;
    public static int sayac_sinirlamasiz;
    public static int sayac_sinirlamali;
    
    public static double[][] sonuc_ortalamalari_men_op = new double[2][sonuc_matis_boy]; //1 worst 2 best 3 arit. ort 4 std sapma 5 fark ort
    public  static double[][] sonuc_ortalamalari_women_op = new double[2][sonuc_matis_boy]; //1 worst 2 best 3 arit. ort 4 std sapma 5 fark ort
    public  static double[][] sonuc_ortalamalari_sýnýrlamalý_men_op = new double[2][sonuc_matis_boy]; //1 worst 2 best 3 arit. ort 4 std sapma 5 fark ort
    public  static double[][] sonuc_ortalamalari_sýnýrlamalý_women_op = new double[2][sonuc_matis_boy]; //1 worst 2 best 3 arit. ort 4 std sapma 5 fark ort
    public  static double[][] sonuc_ortalamalari_sýnýrlamasiz_men_op = new double[2][sonuc_matis_boy]; //1 worst 2 best 3 arit. ort 4 std sapma 5 fark ort
    public  static double[][] sonuc_ortalamalari_sýnýrlamasiz_women_op = new double[2][sonuc_matis_boy]; //1 worst 2 best 3 arit. ort 4 std sapma 5 fark ort
    public  static double[][] sonuc_ortalamalari_maybelatter = new double[2][sonuc_matis_boy]; //1 worst 2 best 3 arit. ort 4 std sapma 5 fark ort
    public static double[][] sonuc_ortalamalari_equitable = new double[2][sonuc_matis_boy]; //1 worst 2 best 3 arit. ort 4 std sapma 5 fark ort
    public static double[][] sonuc_ortalamalari_Backwards_GS = new double[2][sonuc_matis_boy]; //1 worst 2 best 3 arit. ort 4 std sapma 5 fark ort
    public static double[][] sonuc_ortalamalari_aradeger_sinirlamali = new double[2][sonuc_matis_boy]; //1 worst 2 best 3 arit. ort 4 std sapma 5 fark ort
    public static double[][] sonuc_ortalamalari_aradeger_sinirlamasiz = new double[2][sonuc_matis_boy]; //1 worst 2 best 3 arit. ort 4 std sapma 5 fark ort
    public static double[][] sonuc_ortalamalari_probablity = new double[2][sonuc_matis_boy]; //1 worst 2 best 3 arit. ort 4 std sapma 5 fark ort
    public static double[][] sonuc_ortalamalari_circuled = new double[2][sonuc_matis_boy]; //1 worst 2 best 3 arit. ort 4 std sapma 5 fark ort
    public static double[][] sonuc_ortalamalari_circuled_sadece_bekar = new double[2][sonuc_matis_boy]; //1 worst 2 best 3 arit. ort 4 std sapma 5 fark ort
    public static double[][] sonuc_ortalamalari_circuled_kaldigi_yerden = new double[2][sonuc_matis_boy]; //1 worst 2 best 3 arit. ort 4 std sapma 5 fark ort
    
    public  static double[][] sonuc_men_op = new double[2][sonuc_matis_boy];
    public  static double[][] sonuc_women_op = new double[2][sonuc_matis_boy];
    public  static double[][] sonuc_sinirlamali_men_op = new double[2][sonuc_matis_boy];
    public  static double[][] sonuc_sinirlamali_women_op = new double[2][sonuc_matis_boy];
    public static  double[][] sonuc_sinirlamasiz_men_op = new double[2][sonuc_matis_boy];
    public   static double[][] sonuc_sinirlamasiz_women_op = new double[2][sonuc_matis_boy];
    public  static double[][] sonuc_maybelatter = new double[2][sonuc_matis_boy];
    public static double[][] sonuc_equitable = new double[2][sonuc_matis_boy];
    public static double[][] sonuc_Backwards_GS = new double[2][sonuc_matis_boy];
    public static double[][] sonuc_ara_deger_sinirlamali = new double[2][sonuc_matis_boy];
    public static double[][] sonuc_ara_deger_sinirlamasiz = new double[2][sonuc_matis_boy];
    public static double[][] sonuc_probablity = new double[2][sonuc_matis_boy];
    public static double[][] sonuc_circuled = new double[2][sonuc_matis_boy];
    public static double[][] sonuc_circuled_sadece_bekar = new double[2][sonuc_matis_boy];
    public static double[][] sonuc_circuled_kaldigi_yerden = new double[2][sonuc_matis_boy];
    
    
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				   
					Preference_Matrix window = new Preference_Matrix();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
 
    public Preference_Matrix() {
	
		initialize();
		Preference_Matrix.numClicks_probablity =0;
	}

	public void set_preference_matrix_dosyadan()
	 {
		 
		int i,j, m1,n1,m2,n2=0;
	 
		String columnNames[] = new String[N+1];
		 
		String dataValues[][] = new String[N+1][N+1];
			
		 String content = null;
		   File file = new File("D:\\workspace\\stable_marriage\\tercihmatrisi.txt"); //for ex foo.txt
		   try {
		       FileReader reader = new FileReader(file);
		       char[] chars = new char[(int) file.length()];
		       reader.read(chars);
		       content = new String(chars);
		       reader.close();
		   } catch (IOException e) {
		       e.printStackTrace();
		   }
		i=0; m1=0; n1=0; m2=0; n2 =0;
	 
		while ( i<content.toCharArray().length)
		{
			 
			int x = Character.getNumericValue(content.charAt(i));
			x--;
			
			if(x <=N && x>= 0)
				
			{
				
				if(m1 < N )
				{		
					
					    prefere[m1][x] = n1; //shapley de kullanýlacak matris
					    
						preference[m1][n1][0] = x; //Pow da kullanýlacak matris
					
						n1++;						 
						n1 = n1 % N;
						if(n1 % N ==0 )
						{
							m1++;
						}	
														
				}
				else
				{
					 prefere[m1][x] = n2;
				   
					m2=m1-N;	
					preference[n2][ m2][1] = x;
					
					n2++;						 
					n2 = n2 % N;
					if(n2 % N ==0 )
					{
						m1++;
					}
					   
						 
				}
			
				
			}
		   i++;
	    }
		
		
		
		for(i=0; i< N+1 ;i++)
		{
			if(i == 0) 
				dataValues[0][0]= String.format("     ",i );
			else
			{
			dataValues[i][0]= String.format("%d.man",i );
			dataValues[0][i]=  String.format("%d.woman",i );
			}
			columnNames[i]= "";
		}
				
		for(i=0; i<N;i++)
		{
			for(j=0; j < N; j ++)
			{
				if(dataValues[i+1][j+1] ==null)
				{
					dataValues[i+1][j+1]=  String.format(" ( %d, %d ) ",preference[i][j][0], preference[i][j][1] );
					
				}
								
			}
					
			
		}
					
		
		String datavalue_st = "";
		
		for(int ii =0; ii< N+1; ii ++)
		{
			for(int jj =0; jj<N+1; jj++)				
		{datavalue_st = datavalue_st.concat(" "+ dataValues[ii][jj]);}
			datavalue_st  = datavalue_st .concat("\n");
		}
	 
		
		textArea.setText(datavalue_st);
	 
		 
	 }
	 
	public void set_preference_matrix()
	{
		int i,j,k=0;int flag =0; int temp =0;
		String columnNames[] = new String[N+1];
		Random randomGenerator = new Random();

	 
		String dataValues[][] = new String[N+1][N+1];
		
		 
		
	 
		for(i=0;i<2*N;i++)
		{
			for (j=0;j<N;j++)
			{

				
				while(flag ==0)
				{
					temp = randomGenerator.nextInt(N);
				 
					flag =1;
					for(k=0;k<j;k++)
					{	if(prefere[i][k] == temp )
					{ 
						flag =0;
						break;
					}
					else
					{
						flag=1;
					}


					}



				}
				
				prefere[i][j] = temp ;
				 
				flag=0;
				
				
				
				
			 
				
				
		 
			}
		}
		
		/*
		prefere = new int [][]{{5 ,3 ,1 ,4 ,2 ,6 ,8 ,7 },
				{8 ,2 ,4 ,5 ,3 ,7 ,1 ,6 },
				{5 ,8 ,1 ,4 ,2 ,3 ,6 ,7 },
				{8 ,4 ,3 ,2 ,5 ,6 ,1 ,7 },
				{6 ,5 ,4 ,8 ,1 ,7 ,2 ,3} ,
				{7 ,4 ,2 ,5 ,6 ,8 ,1 ,3 },
				{8 ,5 ,6 ,3 ,7 ,2 ,1 ,4 },
				{4 ,7 ,1 ,3 ,5 ,8 ,2 ,6 },
				{4 ,7 ,3 ,8 ,1 ,5 ,2 ,6 },
					{	5 ,3 ,4 ,2 ,1 ,8 ,6 ,7 },
					{	6 ,8 ,2 ,4 ,3 ,7 ,5 ,1 },
					{	5 ,6 ,8 ,3 ,4 ,7 ,1 ,2 },
				{	1 ,3 ,5 ,2 ,8 ,6 ,4 ,7 },
						{	8 ,6 ,2 ,5 ,1 ,7 ,4 ,3} ,
					{	2 ,5 ,8 ,3 ,6 ,4 ,7 ,1 },
							{	7 ,5 ,4 ,1 ,6 ,2 ,8 ,3 }};
		*/
	
		/*
		for(int ii =0 ; ii<N*2; ii++)
	{for(int jj = 0; jj< N ; jj++)
		{prefere[ii][jj]  = prefere[ii][jj]  -1;}}
		*/
		for(i=0;i<N;i++)
		{
			for(j=0;j<N;j++)
			{
				
				preference[i][ prefere[i][j]][0] = j;
			}
			
		}
		
		
		for(i=0;i<N;i++)
		{
			for(j=0;j<N;j++)
			{
				temp= prefere[i+N][j];
				preference[temp][i][1] = j;
			}
			
		}
		
		
		
		


////
	
		for(i=0; i< N+1 ;i++)
		{
			if(i == 0) 
				dataValues[0][0]= String.format("     ",i );
			else
			{
			dataValues[i][0]= String.format("%d.man",i );
			dataValues[0][i]=  String.format("%d.woman",i );
			}
			columnNames[i]= "";
		}
		
		
		for(i=0; i<N;i++)
		{
			for(j=0; j < N; j ++)
			{
				if(dataValues[i+1][j+1] ==null)
				{
					dataValues[i+1][j+1]=  String.format(" ( %d, %d ) ",preference[i][j][0], preference[i][j][1] );
					
				}
				
				
			}
			
			
			
		}
					
String datavalue_st = "";
		
		for(int ii =0; ii< N+1; ii ++)
		{
			for(int jj =0; jj<N+1; jj++)				
		{datavalue_st = datavalue_st.concat(" "+ dataValues[ii][jj]);}
			datavalue_st  = datavalue_st .concat("\n");
		}
	 
		
		textArea.setText(datavalue_st);
		
		 
	 System.out.printf("%s\n",datavalue_st);
		
	}
		
	public static int wPrefersM1OverM(int[][]  prefer, int w, int m, int m1)
	{int i; int return_value=-1;
	 
	for (  i = 0; i < N; i++)
	{
		 
		if (prefer[N+w][i] == m1)
			{return_value= 1;
			break; }

		 
		if (prefer[N+w][i] == m)
			{return_value= 0;
			break; }
	}
	return return_value;
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 1031, 629);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		  JButton btnSonrakiAdm = new JButton("rastgele doldur");
		btnSonrakiAdm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		 
				 
					 set_preference_matrix();
				
			}
		});
		
		
		
		JButton btnDosyadan = new JButton("dosyadan doldur");
		btnDosyadan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 set_preference_matrix_dosyadan();
				
			}
		});
		
		JButton btnShapleyMenOptimal = new JButton("Shapley men optimal");
		btnShapleyMenOptimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int temp_preference[][]= new int [N*2][N]; int i,j;
				blocking_pairs ="";
				for(i=0;i<2*N ;i++)
				{
					for(j=0;j<N;j++)
					{
						temp_preference[i][j]=prefere[i][j];
						
					}
					
				}
				numClicks_stable_marriage_men_optimal++;
				
				Stable_Marriage s = new Stable_Marriage( N , preference ,  prefere ,  blocking_pairs,   flag_continue);
				s.stable_marriage(temp_preference,0,1);
			
			}
		});
		
		JButton btnShapleyWomenOptimal = new JButton("Shapley women optimal");
		btnShapleyWomenOptimal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i,j;				
				blocking_pairs ="";
				 
				int temp_preference[][]= new int [N*2][N];	
				
				

for(i=0; i<N ;i++)
{
	for(j=0; j<N; j++)
	{
		temp_preference [i][j]= prefere[i+N][j];
		temp_preference [i+N][j]= prefere[i][j];
	}
}  			
				
                numClicks_stable_marriage_women_optimal++;
                Stable_Marriage s = new Stable_Marriage( N , preference ,  prefere ,  blocking_pairs,   flag_continue);
				
				s.stable_marriage(temp_preference,1,2);
			}
		});
		
		JButton btnProbablityAlgorithm = new JButton("probablity algorithm");
		btnProbablityAlgorithm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                  int temp_preference[][]= new int [N*2][N]; int i,j;
              	blocking_pairs ="";
				for(i=0;i<2*N ;i++)
				{
					for(j=0;j<N;j++)
					{
						temp_preference[i][j]=prefere[i][j];
						
					}
					
				}
				
				
				  Probablity_Algorithm p= new Probablity_Algorithm(   N , preference ,  prefere, blocking_pairs,  flag_continue,  bozan );
				  
				p.probablity_algorithm(temp_preference );
				
			}
		});
		
		JButton sinirlamaliNnlognAlgoritma = new JButton("s\u0131n\u0131rlamal\u0131 nlogn men alg");
		sinirlamaliNnlognAlgoritma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sayac_sinirlamali  =0;
				 
			
				int temp_preference[][]= new int [N*2][N]; int i,j;
				blocking_pairs ="";
				for(i=0;i<2*N ;i++)
				{
					for(j=0;j<N;j++)
					{
						temp_preference[i][j]=prefere[i][j];
						
					}
					
				}
				Stable_Marriage s = new Stable_Marriage( N , preference ,  prefere ,  blocking_pairs,   flag_continue);
				
				s.stable_marriage(temp_preference,0,10);//10 kodu nlogn de stable marriage önemsemesin diye
			 
			 			
				blocking_pairs ="";
				 
				 
				
				

for(i=0; i<N ;i++)
{
	for(j=0; j<N; j++)
	{
		temp_preference [i][j]= prefere[i+N][j];
		temp_preference [i+N][j]= prefere[i][j];
	}
}  			
				
  s = new Stable_Marriage( N , preference ,  prefere ,  blocking_pairs,   flag_continue);

				s.stable_marriage(temp_preference,1,10);
			
			//tempprefereyi menoptimale uygun hale getirelim
			for(i=0;i<2*N ;i++)
			{
				for(j=0;j<N;j++)
				{
					temp_preference[i][j]=prefere[i][j];
					
				}
				
			}
			
			
			for(i =0; i< 2*N; i++)
			{
				terciharalýgý[i][2]=  (int) Math.ceil(( (double) terciharalýgý[i][0] + (double) terciharalýgý[i][1])/2);
			}
				
			    flag_continue =0;
				
			    int left[]=new int[2*N]	; 
			    int right[]=new int[2*N]	; 
			    int middle[]=new int[2*N]	; 
			    
			    for(int pp=0; pp< 2*N; pp++)
			    {
			    	left[pp]= terciharalýgý[pp][0] ;
			    	right[pp]= terciharalýgý[pp][1] ;
			    	middle[pp]= terciharalýgý[pp][2] ;
			    	
			    }
			    numClicks_nlognsinirlamalý_men++;
			    int flag_finish=0;
			    while(flag_finish == 0)
			    {  	NlogN_Men n = new NlogN_Men( preference,  prefere, N ,  blocking_pairs,  flag_continue,     terciharalýgý);
			    	flag_continue= n.nlogngaleshapley(temp_preference,numClicks_nlognsinirlamalý_men,0,3); //5000: eslesme yok, 0: var
				if(flag_continue == 0) //eþleþme var, sýnýrý öteliyor
				{
					for(i =0; i< 2*N; i++)
					{						
						 
				    	right[i]= middle[i];	
					
					}
					for(i =0; i< 2*N; i++)
					{			
										    
				    	terciharalýgý[i][0]=left[i]  ;
				    	terciharalýgý[i][1]=right[i]  ;
					
					}
					for(i =0; i< 2*N; i++)
					{
						
						middle[i]=  (int) Math.ceil(( (double) terciharalýgý[i][0] + (double) terciharalýgý[i][1])/2);				    
						terciharalýgý[i][2]= middle[i];		
					
					}
					
				}
				else
				{
					for(i =0; i< 2*N; i++)
					{						
						 
				    	left[i]= middle[i];
				    	 
					}
					for(i =0; i< 2*N; i++)
					{				
						 
				    	terciharalýgý[i][0]=left[i]  ;
				    	terciharalýgý[i][1]=right[i]  ;
				    
					}
					for(i =0; i< 2*N; i++)
					{							 
						middle[i]=  (int) Math.ceil(( (double) terciharalýgý[i][0] + (double) terciharalýgý[i][1])/2);				    
						terciharalýgý[i][2]= middle[i];		
					
					}
				 
				}
				
				
				
				int yy=0;
				for( yy =0; yy< 2 * N ; yy++)
			{
				if(terciharalýgý[yy][2] != terciharalýgý[yy][1])
				{
					
					break;
				}
				
			}
			if(yy== 2*N)
				{
				  n = new NlogN_Men( preference,  prefere, N ,  blocking_pairs,  flag_continue,     terciharalýgý);
				flag_continue= n.nlogngaleshapley(temp_preference,numClicks_nlognsinirlamalý_men,1,3); //son bi kere çalýþ
				
				flag_finish=1;
				}
			sayac_sinirlamali++;
			    }
			    
			}
			
			
		
		});
		
		JButton sinirlamasizNlognAlgoritma = new JButton("s\u0131n\u0131rlamasiz  nlogn menop");
		sinirlamasizNlognAlgoritma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sayac_sinirlamasiz=0;
	 
					 
						int temp_preference[][]= new int [N*2][N]; int i,j;
						blocking_pairs ="";
						
						
					
					
					//tempprefereyi menoptimale uygun hale getirelim
					for(i=0;i<2*N ;i++)
					{
						for(j=0;j<N;j++)
						{
							temp_preference[i][j]=prefere[i][j];
							
						}
						
					}
					
					
					for(i =0; i< 2*N; i++)
					{
						terciharalýgý[i][0]=  0;
						terciharalýgý[i][1]=  N-1;
						terciharalýgý[i][2]=   (int) Math.ceil(( (double) (N-1))/2);			;
					}
						
					    flag_continue =0;
						
					    int left[]=new int[2*N]	; 
					    int right[]=new int[2*N]	; 
					    int middle[]=new int[2*N]	; 
					    
					    for(int pp=0; pp< 2*N; pp++)
					    {
					    	left[pp]= terciharalýgý[pp][0] ;
					    	right[pp]= terciharalýgý[pp][1] ;
					    	middle[pp]= terciharalýgý[pp][2] ;
					    	
					    }
					    numClicks_nlognsinirlamasiz_men++;
					    int flag_finish=0;
					    while(flag_finish == 0)
					    {	NlogN_Men n = new NlogN_Men( preference,  prefere, N ,  blocking_pairs,  flag_continue,     terciharalýgý);
					    	flag_continue= n.nlogngaleshapley(temp_preference,numClicks_nlognsinirlamasiz_men,0,5); //5000: eslesme yok, 0: var
						if(flag_continue == 0)
						{
							for(i =0; i< 2*N; i++)
							{						
								 
						    	right[i]= middle[i];	
							
							}
							for(i =0; i< 2*N; i++)
							{			
												    
						    	terciharalýgý[i][0]=left[i]  ;
						    	terciharalýgý[i][1]=right[i]  ;
							
							}
							for(i =0; i< 2*N; i++)
							{
								
								middle[i]=  (int) Math.ceil(( (double) terciharalýgý[i][0] + (double) terciharalýgý[i][1])/2);				    
								terciharalýgý[i][2]= middle[i];		
							
							}
							
						}
						else
						{
							for(i =0; i< 2*N; i++)
							{						
								 
						    	left[i]= middle[i];
						    	 
							}
							for(i =0; i< 2*N; i++)
							{				
								 
						    	terciharalýgý[i][0]=left[i]  ;
						    	terciharalýgý[i][1]=right[i]  ;
						    
							}
							for(i =0; i< 2*N; i++)
							{	
								middle[i]=(int) Math.ceil(( (double) terciharalýgý[i][0] + (double) terciharalýgý[i][1])/2);
								terciharalýgý[i][2]= middle[i];		 
							}
						 
						}
						
						
						
						int yy=0;
						for( yy =0; yy< 2 * N ; yy++)
					{
						if(terciharalýgý[yy][2] != terciharalýgý[yy][1])
						{
							break;
						}
						
					}
					if(yy== 2*N)
						
						{
						  n = new NlogN_Men( preference,  prefere, N ,  blocking_pairs,  flag_continue,     terciharalýgý);
						flag_continue= n.nlogngaleshapley(temp_preference,numClicks_nlognsinirlamasiz_men,1,5); //son bi kere çalýþ
						flag_finish=1;
						}
					sayac_sinirlamasiz ++;
					    }
				
			}
		});
		
		JButton sinirlamasizNlognWomen = new JButton("sinirlamasiz nlogn women op");
		sinirlamasizNlognWomen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		//////////women optimal calýstýr
								 
								int temp_preference[][]= new int [N*2][N]; int i,j;
								blocking_pairs ="";
								
								
							
							
							//tempprefereyi womenoptimale uygun hale getirelim
								for(i=0; i<N ;i++)
								{
									for(j=0; j<N; j++)
									{
										temp_preference [i][j]= prefere[i+N][j];
										temp_preference [i+N][j]= prefere[i][j];
									}
								}  
							
							
							for(i =0; i< 2*N; i++)
							{
								terciharalýgý[i][0]=  0;
								terciharalýgý[i][1]=  N-1;
								terciharalýgý[i][2]=   (int) Math.ceil(( (double) (N-1))/2);			;
							}
								
							    flag_continue =0;
								
							    int left[]=new int[2*N]	; 
							    int right[]=new int[2*N]	; 
							    int middle[]=new int[2*N]	; 
							    
							    for(int pp=0; pp< 2*N; pp++)
							    {
							    	left[pp]= terciharalýgý[pp][0] ;
							    	right[pp]= terciharalýgý[pp][1] ;
							    	middle[pp]= terciharalýgý[pp][2] ;
							    	
							    }
							    
							    numClicks_nlognsinirlamasiz_women++;
							    int flag_finish=0;
							    while(flag_finish == 0)
							    {  NlogN_Women n = new NlogN_Women( preference,  prefere, N ,  blocking_pairs,  flag_continue,     terciharalýgý);
						    	
							    	flag_continue= n.nlognwomengaleshapley(temp_preference, numClicks_nlognsinirlamasiz_women,0,6); //5000: eslesme yok, 0: var
								if(flag_continue == 0)
								{
									for(i =0; i< 2*N; i++)
									{						
										 
								    	right[i]= middle[i];	
									
									}
									for(i =0; i< 2*N; i++)
									{			
														    
								    	terciharalýgý[i][0]=left[i]  ;
								    	terciharalýgý[i][1]=right[i]  ;
									
									}
									for(i =0; i< 2*N; i++)
									{
										
										middle[i]=  (int) Math.ceil(( (double) terciharalýgý[i][0] + (double) terciharalýgý[i][1])/2);				    
										terciharalýgý[i][2]= middle[i];		
									
									}
									
								}
								else
								{
									for(i =0; i< 2*N; i++)
									{						
										 
								    	left[i]= middle[i];
								    	 
									}
									for(i =0; i< 2*N; i++)
									{				
										 
								    	terciharalýgý[i][0]=left[i]  ;
								    	terciharalýgý[i][1]=right[i]  ;
								    
									}
									for(i =0; i< 2*N; i++)
									{	
										middle[i]=(int) Math.ceil(( (double) terciharalýgý[i][0] + (double) terciharalýgý[i][1])/2);
										terciharalýgý[i][2]= middle[i];		 
									}
								 
								}
								
								
								
								int yy=0;
								for( yy =0; yy< 2 * N ; yy++)
							{
								if(terciharalýgý[yy][2] != terciharalýgý[yy][1])
								{
									break;
								}
								
							}
							if(yy== 2*N)
								
								{
								  n = new NlogN_Women( preference,  prefere, N ,  blocking_pairs,  flag_continue,     terciharalýgý);
						    	
								flag_continue= n.nlognwomengaleshapley(temp_preference, numClicks_nlognsinirlamasiz_women,1,6); //son bi kere çalýþ
								flag_finish=1;
								}
							    }
						
				
				
			}
		});
		
		JButton sinirlamaliNlogNWomen = new JButton("s\u0131n\u0131rlamal\u0131 nlog n women alg");
		sinirlamaliNlogNWomen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		 
					 
						int temp_preference[][]= new int [N*2][N]; int i,j;
						blocking_pairs ="";
						for(i=0;i<2*N ;i++)
						{
							for(j=0;j<N;j++)
							{
								temp_preference[i][j]=prefere[i][j];
								
							}
							
						}
						Stable_Marriage s = new Stable_Marriage( N , preference ,  prefere ,  blocking_pairs,   flag_continue);
						
						s.stable_marriage(temp_preference,0,10); 
					 			
						blocking_pairs =""; 
						

		for(i=0; i<N ;i++)
		{
			for(j=0; j<N; j++)
			{
				temp_preference [i][j]= prefere[i+N][j];
				temp_preference [i+N][j]= prefere[i][j];
			}
		}  			
						
		  s = new Stable_Marriage( N , preference ,  prefere ,  blocking_pairs,   flag_continue);
		
						s.stable_marriage(temp_preference,1,10);
					
					 
						//tempprefereyi womenoptimale uygun hale getirelim
						for(i=0; i<N ;i++)
						{
							for(j=0; j<N; j++)
							{
								temp_preference [i][j]= prefere[i+N][j];
								temp_preference [i+N][j]= prefere[i][j];
							}
						}  
					
					
					for(i =0; i< 2*N; i++)
					{
						terciharalýgý[i][2]=  (int) Math.ceil(( (double) terciharalýgý[i][0] + (double) terciharalýgý[i][1])/2);
					}
						
					    flag_continue =0;
						
					    int left[]=new int[2*N]	; 
					    int right[]=new int[2*N]	; 
					    int middle[]=new int[2*N]	; 
					    
					    for(int pp=0; pp< 2*N; pp++)
					    {
					    	left[pp]= terciharalýgý[pp][0] ;
					    	right[pp]= terciharalýgý[pp][1] ;
					    	middle[pp]= terciharalýgý[pp][2] ;
					    	
					    }
					    numClicks_nlognsinirlamalý_women++;
					    int flag_finish=0;
					    while(flag_finish == 0)
					    {NlogN_Women n = new NlogN_Women( preference,  prefere, N ,  blocking_pairs,  flag_continue,     terciharalýgý);
				    	
					    	flag_continue= n.nlognwomengaleshapley(temp_preference, numClicks_nlognsinirlamalý_women,0,4); //5000: eslesme yok, 0: var
						if(flag_continue == 0)
						{
							for(i =0; i< 2*N; i++)
							{						
								 
						    	right[i]= middle[i];	
							
							}
							for(i =0; i< 2*N; i++)
							{			
												    
						    	terciharalýgý[i][0]=left[i]  ;
						    	terciharalýgý[i][1]=right[i]  ;
							
							}
							for(i =0; i< 2*N; i++)
							{
								
								middle[i]=  (int) Math.ceil(( (double) terciharalýgý[i][0] + (double) terciharalýgý[i][1])/2);				    
								terciharalýgý[i][2]= middle[i];		
							
							}
							
						}
						else
						{
							for(i =0; i< 2*N; i++)
							{						
								 
						    	left[i]= middle[i];
						    	 
							}
							for(i =0; i< 2*N; i++)
							{				
								 
						    	terciharalýgý[i][0]=left[i]  ;
						    	terciharalýgý[i][1]=right[i]  ;
						    
							}
							for(i =0; i< 2*N; i++)
							{							 
								middle[i]=  (int) Math.ceil(( (double) terciharalýgý[i][0] + (double) terciharalýgý[i][1])/2);				    
								terciharalýgý[i][2]= middle[i];		
							
							}
						 
						}
						
						
						
						int yy=0;
						for( yy =0; yy< 2 * N ; yy++)
					{
						if(terciharalýgý[yy][2] != terciharalýgý[yy][1])
						{
							break;
						}
						
					}
					if(yy== 2*N)
						{
						  n = new NlogN_Women( preference,  prefere, N ,  blocking_pairs,  flag_continue,     terciharalýgý);
				    	
						flag_continue= n.nlognwomengaleshapley(temp_preference,numClicks_nlognsinirlamalý_women,1,4); //son bi kere çalýþ
						
						flag_finish=1;
						}
					    }
				
			}
		});
		
		JButton btnMaybeLatter = new JButton("Maybe Latter");
		btnMaybeLatter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				numClicks_maybelatter ++;
				Maybe_Latter m = new Maybe_Latter(preference,prefere, N,blocking_pairs); 
				m.maybeLater();
				 
			}
		});
		
		JButton btnEquitable = new JButton("Equitable");
		btnEquitable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numClicks_equitable ++;
				Equitable equitable = new Equitable( preference,prefere, N,blocking_pairs);
				equitable.solve() ;
			}
		});
		
		JButton btnCirculedAlgorithm = new JButton("Circuled Algorithm");
		btnCirculedAlgorithm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				numClicks_circuled ++;
				Circuled circuled = new Circuled( preference,prefere, N,blocking_pairs);
				circuled.Circuled_algorithm();				
			}
		});
		
		JButton button = new JButton("Circuled Algorithm_sadece_bekar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				numClicks_circuled_sadece_bekar ++;
				Circuit_sadece_bekar circuled = new Circuit_sadece_bekar( preference,prefere, N,blocking_pairs);
				circuled.Circuled_algorithm();	
			}
		});
		
		JButton button_1 = new JButton("Circuled_kaldigi_yerden Algorithm");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numClicks_circuled_kaldigi_yerden ++;
				Circuled_kaldigi_yerden circuled = new Circuled_kaldigi_yerden( preference,prefere, N,blocking_pairs);
				circuled.Circuled_algorithm();	
			}
		});
		
		JButton btnBasw = new JButton("Backwards GS");
		btnBasw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				numClicks_Backwards_GS ++;
				Backwards_GS backwards_GS = new Backwards_GS( preference,prefere, N,blocking_pairs);
				backwards_GS.solve();	
			
			}
		});
		
	
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(285)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnMaybeLatter, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnEquitable, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnProbablityAlgorithm, 0, 0, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(btnShapleyMenOptimal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(sinirlamaliNnlognAlgoritma, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(sinirlamasizNlognAlgoritma, GroupLayout.PREFERRED_SIZE, 145, Short.MAX_VALUE)
										.addComponent(button_1, 0, 0, Short.MAX_VALUE)
										.addComponent(button, 0, 0, Short.MAX_VALUE)
										.addComponent(btnCirculedAlgorithm, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(73)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addComponent(btnShapleyWomenOptimal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(sinirlamaliNlogNWomen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(sinirlamasizNlognWomen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(btnSonrakiAdm, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnDosyadan, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)))
								.addComponent(btnBasw)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 733, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(258, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addGap(66)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnCirculedAlgorithm)
							.addGap(6)
							.addComponent(button)
							.addGap(5))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnDosyadan)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_1)
						.addComponent(btnSonrakiAdm, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(sinirlamasizNlognAlgoritma)
						.addComponent(sinirlamasizNlognWomen))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(sinirlamaliNnlognAlgoritma)
						.addComponent(sinirlamaliNlogNWomen))
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnShapleyMenOptimal)
						.addComponent(btnShapleyWomenOptimal))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnMaybeLatter)
						.addComponent(btnEquitable)
						.addComponent(btnProbablityAlgorithm))
					.addGap(18)
					.addComponent(btnBasw)
					.addContainerGap(66, Short.MAX_VALUE))
		);
		
		
		 
		
		frame.getContentPane().setLayout(groupLayout);
		
	}
}
