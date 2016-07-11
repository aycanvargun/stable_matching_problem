package stable_marriage;

public class Probablity_Algorithm {
	
	  public int N ;
	    public   int[][][] preference =  new int[N][N][2]; 
	    public int prefere[][] =  new int[2*N][N]; 
	    public String blocking_pairs="";
	    public int flag_continue;
	    public int bozan[] = new int[3] ;
	    
	    
		public Probablity_Algorithm(  int N ,int[][][] preference , int prefere[][],String blocking_pairs, int flag_continue,    int bozan[]  )
		{
			this.N=N;
			
			this.prefere= prefere;
			this.preference= preference;
			this.blocking_pairs= blocking_pairs;
			this.flag_continue= flag_continue;
			this.bozan=bozan;
		}
	public void probablity_algorithm( int[][] temp_prefer_prob)
	{   boolean unstability=false;
		int i,j; double carpim; int k=0; int temp; int a,b; int[] flag = new int[2*N]; 
	
		double[][]  sirali_olasilik = new double[N*N][3];
		double[][]  tut = new double[N][4];
         int[][] temp_prefer2=new int[N*2][N];
		 int[][] MANprefer= new int[N][N];
		 int[][] WOMANprefer = new int[N][N];
		 
		 int[] manPartner= new int[N];
		 int[] wPartner = new int[N];
		 
	 
		 
		 for(i=0;i<2*N;i++)
		{
			 
			flag[i]=0;

		}

 



		for(i=0; i< N;i++)
		{
			for(j=0; j<N; j++)
			{
				MANprefer[i][temp_prefer_prob[i][j]]=j;
				WOMANprefer[i][temp_prefer_prob[i+N][j]]=j;
				temp_prefer2[i][j]= temp_prefer_prob[i][j];
				temp_prefer2[i+N][j]= temp_prefer_prob[i+N][j];

			}

		}

		//kadýn tercih matrisinin transfozunu alýyorum. olasýlýklarý çarpacapým çnkü
		for(i=0; i<N; i++)
		{
			for(j=1; j<N; j++)
			{

				if (i<j )
				{
					temp= WOMANprefer[j][i];
					WOMANprefer[j][i]=WOMANprefer[i][j];
					WOMANprefer[i][j]= temp;

				}

			}

		}
/*

		System.out.printf("\n\nOLASILIK ALGORITMASINDA MEN TERCÝH MATRISI  \n");


		for(i=0; i< N;i++)
		{
			for(j=0; j<N; j++)
			{
				System.out.printf("%d ",MANprefer[i][j]+1);	 

			}
			System.out.printf("\n");
		}


		System.out.printf("\n\nOLASILIK ALGORITMASINDA WOMEN TERCÝH MATRISI TRANSPOZU   \n");

		for(i=0; i< N;i++)
		{
			for(j=0; j<N; j++)
			{
				System.out.printf("%d ",WOMANprefer[i][j]+1);	 

			}
			System.out.printf("\n");
		}

*/

		k=0;

		for(i=0; i< N;i++)
		{
			for(j=0; j<N; j++)
			{
				carpim= (MANprefer[i][j]+1) * (WOMANprefer[i][j]+1) ;
			
				sirali_olasilik[k][0] = (double) 1 / carpim;
				sirali_olasilik[k][1] = i;
				sirali_olasilik[k][2] = j;

				k++;
			}

		}
		 
		sirali_olasilik=quick_sort(sirali_olasilik, 0, N * N - 1  ) ;


		
/*
		System.out.printf("\n\nSIRALI OLASILIK\n");

		for(j=0; j<N*N; j++)
		{
			System.out.printf("%.3f   (%.3f , %.3f) \n",sirali_olasilik[j][0], sirali_olasilik[j][1],sirali_olasilik[j][2]);	 

		}
		
		*/
        boolean kontrol ; 
        int sira=0;
		k=0;
	 
	    i=(N * N)-1;
	    int free = 0;
	    int buyukflag =N* N-1;
	     
		while (true) {
			while (i >= 0) 
			{
				a = (int) sirali_olasilik[i][1];
				b = (int) sirali_olasilik[i][2];
				carpim = sirali_olasilik[i][0];
				sira = i;

				if (flag[a] == 0 && flag[N + b] == 0) {

					kontrol = kontrolet(tut, k, a, b, MANprefer, WOMANprefer);
					if (kontrol == true) // stabilite bozulmamýs
					{

						tut[k][0] = a;
						tut[k][1] = b;
						tut[k][2] = carpim;
						tut[k][3] = sira; // sirali olasýlýkta kacýncý sýrada
											// olduðunu saklior
						k++;
						flag[a] = 1;
						flag[N + b] = 1;

						// hicbisey yapma
						// bi sonraki asamaya gecsin
						i--;
					} else // stabilite bozulmus
					{
						// stable'lýk bozulmus demek
						// burada gecmisi bozup islem yapacaðýz

						if (k > 0) {

							flag[(int) tut[k - 1][0]] = 0; // k-1 de kim varsa
															// onu sil
							flag[(int) (N + tut[k - 1][1])] = 0;

							sira = (int) tut[k - 1][3]; // sýralý listede
														// kacýncý sýrada ise o
														// sýranýn bir fazlasýný
														// bul
							tut[k - 1][0] = 0;
							tut[k - 1][1] = 0;
							tut[k - 1][2] = 0;
							tut[k - 1][3] = 0;
							k--;
							i = sira - 1;
							// artýk algoritma bir fazlasýndan devam eder
						} else {
							i = sira - 1;

						}
					}

				} 
				else 
				{
					i--;
				}

			}
			free = 0;
			for (int x = 0; x < N; x++) 
			{
				if (tut[x][2] == 0)
					free++;

			}
			
			if (free == 0) 
			{
				break;
			} 
			else
			{
				if (free * 2 < N) // azýcýk bos cýft kalmýs
				{
					// en son eklenen çifti bulurum ve onu bozarým

					while (free > 0) {
						int x;
						for (x = 0; x < N; x++) {
							if (tut[x][2] == 0)
								break;
						}
						// en son eklenen çift olasýlýðý 0 olandan bir
						// öncekidir.

						flag[(int) tut[x - 1][0]] = 0; // k-1 de kim varsa onu
														// sil
						flag[(int) (N + tut[x - 1][1])] = 0;

						sira = (int) tut[x - 1][3]; // sýralý listede kacýncý
													// sýrada ise o sýranýn bir
													// fazlasýný bul
						tut[x - 1][0] = 0;
						tut[x - 1][1] = 0;
						tut[x - 1][2] = 0;
						tut[x - 1][3] = 0;

						i = sira - 1;
						k = x - 1;
						// artýk algoritma bir fazlasýndan devam eder
						free--;

					}
				} else // cokfazla bos cýfT kalmýs baslangýcý degistirip bastan
						// baslayalým
				{
					buyukflag--;
					i = (int) buyukflag;

					if (buyukflag < N * 2)
						break;

				}
			}
		}

		

		for(i=0; i< N;i++)
		{
			for(j=0; j<N; j++)
			{
				MANprefer[i][j]=temp_prefer2[i][j];
				WOMANprefer[i][j]=temp_prefer2[i+N][j];


			}

		}

		 

		for (  i = 0; i < N; i++)        
		{

			manPartner[(int)tut[i][0]] = (int)  tut[i][1];
			wPartner[(int)tut[i][1]]= (int)  tut[i][0];


		}

		

		 
		if (flag_continue != 5000)
		{	Unstability u = new Unstability (blocking_pairs,N);
		 unstability=u.unstable(manPartner,wPartner, MANprefer, WOMANprefer);
		}
					
					 if(unstability == false)
						{
						 					 
						 
							System.out.println("\n\nProbablity Algortihm solution is stable. :)))))))))\n\n");
							
							for(int uu = 0; uu < N ; uu ++)
							{
								System.out.printf("man: %d   woman: %d tercihleri: ( %d , %d ) \n", uu+1, manPartner[uu]+1, preference[uu][manPartner[uu]][0], preference[uu][manPartner[uu]][1]);
							}
							///tekrarlý oynama için hesaplar
							Preference_Matrix.numClicks_probablity  ++;
							double worstcase1 = 0;
							double bestcase1= N;
							for(int uu =0; uu < N ;uu++)
							{
								if(worstcase1 < preference[uu][manPartner[uu]][0])
									worstcase1 = preference[uu][manPartner[uu]][0];
								if(bestcase1 > preference[uu][manPartner[uu]][0])
									bestcase1 = preference[uu][manPartner[uu]][0];			 
							}
							
							
							double worstcase2 = 0;
							double bestcase2= N;
							for(int uu =0; uu < N ;uu++)
							{
								if(worstcase2 < preference[uu][manPartner[uu]][1])
									worstcase2 =  preference[uu][manPartner[uu]][1];
								if(bestcase2 > preference[uu][manPartner[uu]][1])
									bestcase2 = preference[uu][manPartner[uu]][1];			 
							}
							
							double  aritmetik_ortalama1=0;
							
							for(int uu=0;uu<N;uu++)
								aritmetik_ortalama1 = aritmetik_ortalama1 + preference[uu][manPartner[uu]][0];
							aritmetik_ortalama1= aritmetik_ortalama1 /N;	
										
							double  aritmetik_ortalama2=0;		
						    for(int uu=0;uu<N;uu++)
								aritmetik_ortalama2 = aritmetik_ortalama2 + preference[uu][manPartner[uu]][1];
							aritmetik_ortalama2= aritmetik_ortalama2 /N;
							
							double  standart_sapma1=0;
							for(int uu=0;uu<N;uu++)
								standart_sapma1 = standart_sapma1 + (preference[uu][manPartner[uu]][0] - aritmetik_ortalama1) * (preference[uu][manPartner[uu]][0] - aritmetik_ortalama1);
							standart_sapma1 = standart_sapma1 /N;
							standart_sapma1 = Math.sqrt(standart_sapma1);
							
							
							double standart_sapma2 =0;
							for(int uu=0;uu<N;uu++)
								standart_sapma2 = standart_sapma2 + (preference[uu][manPartner[uu]][1] - aritmetik_ortalama2) * (preference[uu][manPartner[uu]][1] - aritmetik_ortalama2);
							standart_sapma2 = standart_sapma2 /N;
							standart_sapma2 = Math.sqrt(standart_sapma2);
							
							
							//farklarýn ortalamasý
							double fark_ortalama=0;
							for(int uu =0; uu< N ;uu++)
								fark_ortalama= fark_ortalama+ Math.abs(preference[uu][manPartner[uu]][0] - preference[uu][manPartner[uu]][1]);
							fark_ortalama = fark_ortalama/N;
							
							
						 //SEM
							double sum_of_difference =0;
							 
							for(int ii = 0; ii< N ; ii++)
							{
								sum_of_difference= sum_of_difference + Math.abs(preference[ii][manPartner[ii]][0] - preference[ii][manPartner[ii]][1]);
						 
								
							}
					 
							//EGALITAREAN
							
							double egalitarean_sum =0;
							for(int ii = 0; ii< N ; ii ++)
							{
								egalitarean_sum= egalitarean_sum + Math.abs(preference[ii][manPartner[ii]][0] + preference[ii][manPartner[ii]][1]);
								 
								
							}
							
							Preference_Matrix.sonuc_ortalamalari_probablity  [0][0]=Preference_Matrix.sonuc_ortalamalari_probablity  [0][0]+ (double) (worstcase1) ;
							Preference_Matrix.sonuc_ortalamalari_probablity  [1][0]=Preference_Matrix.sonuc_ortalamalari_probablity  [1][0]+(double) (worstcase2) ;
							Preference_Matrix.sonuc_ortalamalari_probablity  [0][1]=Preference_Matrix.sonuc_ortalamalari_probablity  [0][1]+ (double) (bestcase1) ;
							Preference_Matrix.sonuc_ortalamalari_probablity  [1][1]=Preference_Matrix.sonuc_ortalamalari_probablity  [1][1]+(double) (bestcase2 );
							Preference_Matrix.sonuc_ortalamalari_probablity  [0][2]=Preference_Matrix.sonuc_ortalamalari_probablity  [0][2]+(double) (aritmetik_ortalama1 );
							Preference_Matrix.sonuc_ortalamalari_probablity  [1][2]=Preference_Matrix.sonuc_ortalamalari_probablity  [1][2]+(double) (aritmetik_ortalama2) ;
							Preference_Matrix.sonuc_ortalamalari_probablity  [0][3]=Preference_Matrix.sonuc_ortalamalari_probablity  [0][3]+ (double)(standart_sapma1) ;
							Preference_Matrix.sonuc_ortalamalari_probablity  [1][3]=Preference_Matrix.sonuc_ortalamalari_probablity  [1][3]+ (double) (standart_sapma2 );
							Preference_Matrix.sonuc_ortalamalari_probablity  [0][4]=Preference_Matrix.sonuc_ortalamalari_probablity  [0][4]+ (double) (fark_ortalama) ;
							Preference_Matrix.sonuc_ortalamalari_probablity  [1][4]=Preference_Matrix.sonuc_ortalamalari_probablity  [1][4]+ (double)(sum_of_difference) ;
							Preference_Matrix.sonuc_ortalamalari_probablity  [0][5]=Preference_Matrix.sonuc_ortalamalari_probablity  [0][5]+ (double) (egalitarean_sum) ;
							
						for(int uu =0; uu< 2; uu++)
							for(int yy=0;yy<Preference_Matrix.sonuc_matis_boy ; yy++)
								Preference_Matrix.sonuc_probablity   [uu][yy] = Preference_Matrix.sonuc_ortalamalari_probablity  [uu][yy]/ Preference_Matrix.numClicks_probablity  ;

						System.out.printf(" \n\nPROBABLITY Sonuc: \n\n");
						for(int uu = 0; uu< Preference_Matrix.sonuc_matis_boy; uu++)
						{
							System.out.printf("( %.2f, %.2f)\n", Preference_Matrix.sonuc_probablity[0][uu],Preference_Matrix.sonuc_probablity[1][uu]);
						}

						 
						}
						else
						{
							System.out.printf(String.format("\nProbablity Solution is unstable.\n"));
							  
							
						}
		 
		 
		 
		 
				//	 matris_deneme();



	}
	
	
	public double [][] quick_sort(double[][]  a,int first,int last)
	{
		int j,i,pivot;
		double temp, temp_a, temp_b;
		if(first<last){
			pivot=first;
			i=first;
			j=last;

			while(i<j){
				while(a[i][0] <= a[pivot][0] && i<last)
					i++;
				while(a[j][0]>a[pivot][0])
					j--;
				if(i<j){
					temp=a[i][0];
					a[i][0]=a[j][0];
					a[j][0]=temp;

					temp_a=a[i][1];
					a[i][1]=a[j][1];
					a[j][1]=temp_a;

					temp_b=a[i][2];
					a[i][2]=a[j][2];
					a[j][2]=temp_b;



				}
			}

			temp=a[pivot][0];
			a[pivot][0]=a[j][0];
			a[j][0]=temp;

			temp_a=a[pivot][1];
			a[pivot][1]=a[j][1];
			a[j][1]=temp_a;

			temp_b=a[pivot][2];
			a[pivot][2]=a[j][2];
			a[j][2]=temp_b;



			quick_sort(a,first,j-1);
			quick_sort(a,j+1,last);

		}
		
		return a; 
	}
	
	boolean kontrolet(double[][] tut, int k, int a, int b, int[][] MANprefer, int[][] WOMANprefer)
	{
		boolean kontrol =true; int i; int x = -1; int y = -1;  
		for(i =0; i< k; i ++)// tam k'nýn yeri bos, oncesi dolu
		{
			x = (int) tut[i][0]; // erkek
			y= (int) tut[i][1]; //kadýn
		
			if( WOMANprefer[a][y] < WOMANprefer[x][y] && MANprefer[a][y] < MANprefer[a][b])
			{
				kontrol = false;
				break;
			}
			if(WOMANprefer[x][b]< WOMANprefer[a][b] && MANprefer[x][b] < MANprefer[x][y])
			{
				kontrol = false;
				break;
			}
			
		}
		
		
		if(kontrol == false )
		{
			bozan[0] = x ;
			bozan[1] = y ;	
			bozan[2] = i;
		}
		
		return kontrol;
		
	}
	
	
	public void matris_deneme()
	{
		
		Object[][] path_matris = new Object [21][21];
		
		path_matris[0][0]="xxx";
		
		
		path_matris[0][20]="1,5";
		path_matris[0][1]="2,2";
		path_matris[0][2]="2,3";
		path_matris[0][3]="2,7";
		path_matris[0][4]="3,1";
		path_matris[0][5]="3,2";
		path_matris[0][6]="4,1";
		path_matris[0][7]="4,2";
		path_matris[0][8]="4,3";
		path_matris[0][9]="5,4";
		path_matris[0][10]="5,6";
		path_matris[0][11]="6,3";
		path_matris[0][12]="6,4";
		path_matris[0][13]="6,6";
		path_matris[0][14]="6,7";
		path_matris[0][15]="7,8";
		path_matris[0][16]="8,3";
		path_matris[0][17]="8,4";
		path_matris[0][18]="8,6";
		path_matris[0][19]="8,7";
		
		path_matris[20][0]="1,5";
		path_matris[1][0]="2,2";
		path_matris[2][0]="2,3";
		path_matris[3][0]="2,7";
		path_matris[4][0]="3,1";
		path_matris[5][0]="3,2";
		path_matris[6][0]="4,1";
		path_matris[7][0]="4,2";
		path_matris[8][0]="4,3";
		path_matris[9][0]="5,4";
		path_matris[10][0]="5,6";
		path_matris[11][0]="6,3";
		path_matris[12][0]="6,4";
		path_matris[13][0]="6,6";
		path_matris[14][0]="6,7";
		path_matris[15][0]="7,8";
		path_matris[16][0]="8,3";
		path_matris[17][0]="8,4";
		path_matris[18][0]="8,6";
		path_matris[19][0]="8,7";
		
	 
		
		String[] p1_st;
		String[] p2_st;
		int[] p1 = new int [2];
		int[] p2 = new int [2];
		
	  
		for(int ii =1; ii< 21; ii ++)
		{
			for(int jj =1; jj< 21 ; jj ++)
				
			{
				if (ii == jj )
				{	path_matris[ii][jj] = -1;
				continue;
				}   
				p1_st = (String[]) ((String) path_matris[ii][0]).split(",");
				p2_st = (String[]) ((String) path_matris[0][jj]).split(",");
				
				p1[0] = Integer.parseInt(p1_st[0])-1;
				p1[1] = Integer.parseInt(p1_st[1])-1;
				p2[0] = Integer.parseInt(p2_st[0])-1;
				p2[1] = Integer.parseInt(p2_st[1])-1;
				
			 
				
				System.out.println("\n"); 
				if(p1[0]  == p2[0] || p1[1] == p2[1])
				{
					path_matris[ii][jj] = 1;
					path_matris[jj][ii] = 1;
				}
				else
				{
					if (( (preference[p1[0]][p2[1]][0]< preference[p1[0]][p1[1]][0]) && (preference[p1[0]][p2[1]][1]<preference [p2[0]][p2[1]][1]) ) || 
							( (preference[p2[0]][p1[1]][0]< preference[p2[0]][p2[1]][0]) && (preference[p2[0]][p1[1]][1]< preference[p1[0]][p1[1]][1]) ) )
						{
							path_matris[ii][jj] = 1;
						}
						else
						{//0 stabiliteyi bozmuyor
							path_matris[ii][jj] =0; 
						}
						
				}
				
				
				
				
			}
		}
		
for(int ii =0; ii< 21 ; ii ++ )
{
	for(int jj = 0; jj< 21 ; jj ++)
		{
		
		if(ii == 0 || jj == 0)
		{
			System.out.printf("%s  ", path_matris[ii][jj] );	
		}
		else
		{
			System.out.printf("%d    ", path_matris[ii][jj] );	
		}
			
		}
	System.out.println("\n");
}

		System.out.println("\n");
	}
	
}


