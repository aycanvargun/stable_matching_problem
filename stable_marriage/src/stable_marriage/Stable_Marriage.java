package stable_marriage;

public class Stable_Marriage {
	    public int N ;
	    public   int[][][] preference =  new int[N][N][2]; 
	    public int prefere[][] =  new int[2*N][N]; 
	    public String blocking_pairs="";
	    public int flag_continue;
	public Stable_Marriage(  int N ,int[][][] preference , int prefere[][],String blocking_pairs, int flag_continue)
	{
		this.N=N;
		
		this.prefere= prefere;
		this.preference= preference;
		this.blocking_pairs= blocking_pairs;
		this.flag_continue= flag_continue;
	}

	public void stable_marriage(int[][] temp_prefere , int flag, int flag_cins)
	{
		int i,j,freeCount,m,w,m1; String result;
		
		 
		int MANprefer[][]= new int [N][N];
		int WOMANprefer[][]= new int [N][N];
		int wPartner[] = new int [N];
		int manPartner[] = new int [N];
		int mFree[] = new int [N];
		boolean unstability = false;
		
		for(i=0; i< 2*N;i++)
		{
			for(j=0; j<N; j++)
			{
				
				if(i< N )
				{
					MANprefer[i][j]=temp_prefere[i][j];
				}
				else
				{
					WOMANprefer[i-N][j]=temp_prefere[i][j];
					
				}		
				

			}

		}
		
		
		for (i =0; i<N ;i++)

		{
			wPartner[i] =-1;
			mFree[i]=0;
		}

		freeCount = N;

		 
		while (freeCount > 0)
		{
			 

			for (m = 0; m < N; m++)
			{
				if (mFree[m] == 0)
					break;
			}
			 
			for (  i = 0; i < N && mFree[m] == 0  ; i++)
			{
				w = temp_prefere[m][i];

				 
				if (wPartner[w] == -1)
				{
					wPartner[w] = m;
					mFree[m] = 1;
					freeCount--;
				}

				else   
				{
					 
					m1 = wPartner[w];

					 
					if (Preference_Matrix.wPrefersM1OverM(temp_prefere, w, m, m1) == 0)
					{
						wPartner[w] = m;
						mFree[m] = 1;
						mFree[m1] = 0;
					}
				}  				
			 
				
			}  
		}  
	 


		 
		for (  i = 0; i < N; i++)        
		{
			 
			manPartner[ wPartner[i]]= i;


		}
		
		if (flag_continue != 5000)
		{	Unstability u = new Unstability (blocking_pairs,N);
		 unstability=u.unstable(manPartner,wPartner, MANprefer, WOMANprefer);
		}
		
		if(flag ==0 )
			  result = "Men optimal stable solution : \n";
		else
			  result = "Women optimal stable solution : \n";
			
			
		for (  i = 0; i < N; i++)        
		{ 
		
			
			if(flag ==0 )//men optimal hesaplanmýs
				{
				Preference_Matrix.terciharalýgý[i][0] =  preference[i][manPartner[i]][0];
				Preference_Matrix.terciharalýgý[manPartner[i]+N][1] =  preference[i][manPartner[i]][1];
				  result 	= result.concat(String.format("man: %d   woman: %d tercihleri: ( %d , %d ) \n",i+1 ,manPartner[i]+1, preference[i][manPartner[i]][0],  preference[i][manPartner[i]][1]));
				
				}
			else
				{
				int ll;
				//women optimal hesaplanmýs
				for( ll=0; ll< N; ll++)
				{	
					if(manPartner[ll] == i)
				{break;
				}	
					}
				Preference_Matrix.terciharalýgý[manPartner[ll]][1] =  preference[manPartner[ll]][ll][0];
				Preference_Matrix.terciharalýgý[ll+N][0] =  preference[manPartner[ll]][ll][1];	
					result 	= result.concat(String.format("man: %d   woman: %d tercihleri: ( %d , %d ) \n", manPartner[ll]+1, ll+1,  preference[manPartner[ll]][ll][0], preference[manPartner[ll]][ll][1]));
				   
				
				
				}


		}
		
		if(flag_cins  == 1 )//yani men optimalse
		{
			System.out.printf("\n\nMen optimal Sonuc: \n\n");
	    	System.out.printf("\n\n%s\n\n",result);
			
			if(flag==0)
			{
				double worstcase1 = 0;
				double bestcase1= N;
				for(int uu =0; uu < N ;uu++)
				{
					if(worstcase1 < preference[uu][manPartner[uu]][0])
						worstcase1 = preference[uu][manPartner[uu]][0];
					if(bestcase1 > preference[uu][manPartner[uu]][0])
						bestcase1 = preference[uu][manPartner[uu]][0];			 
				}
				result 	= result.concat(String.format("ilk kolon, en iyi : %.1f , ",bestcase1));
				result 	= result.concat(String.format("en kötü : %.1f \n",worstcase1));
				
				double worstcase2 = 0;
				double bestcase2= N;
				for(int uu =0; uu < N ;uu++)
				{
					if(worstcase2 < preference[uu][manPartner[uu]][1])
						worstcase2 =  preference[uu][manPartner[uu]][1];
					if(bestcase2 > preference[uu][manPartner[uu]][1])
						bestcase2 = preference[uu][manPartner[uu]][1];			 
				}
				result 	= result.concat(String.format("en iyi : %.1f , ",bestcase2));
				result 	= result.concat(String.format("en kötü : %.1f \n",worstcase2));
				
				
				double  aritmetik_ortalama1=0;
				
				for(int uu=0;uu<N;uu++)
					aritmetik_ortalama1 = aritmetik_ortalama1 + preference[uu][manPartner[uu]][0];
				aritmetik_ortalama1= aritmetik_ortalama1 /N;	
				result 	= result.concat(String.format("aritmetik ortalama: ( %.2f , ",aritmetik_ortalama1));			
					
				
							
				double  aritmetik_ortalama2=0;		
			    for(int uu=0;uu<N;uu++)
					aritmetik_ortalama2 = aritmetik_ortalama2 + preference[uu][manPartner[uu]][1];
				aritmetik_ortalama2= aritmetik_ortalama2 /N;
				result 	= result.concat(String.format(" %.2f ) \n",aritmetik_ortalama2));

				
				
				
				double  standart_sapma1=0;
				for(int uu=0;uu<N;uu++)
					standart_sapma1 = standart_sapma1 + (preference[uu][manPartner[uu]][0] - aritmetik_ortalama1) * (preference[uu][manPartner[uu]][0] - aritmetik_ortalama1);
				standart_sapma1 = standart_sapma1 /N;
				standart_sapma1 = Math.sqrt(standart_sapma1);
				result 	= result.concat(String.format(" standart sapma: ( %.2f , ",standart_sapma1));
				
				
				double standart_sapma2 =0;
				for(int uu=0;uu<N;uu++)
					standart_sapma2 = standart_sapma2 + (preference[uu][manPartner[uu]][1] - aritmetik_ortalama2) * (preference[uu][manPartner[uu]][1] - aritmetik_ortalama2);
				standart_sapma2 = standart_sapma2 /N;
				standart_sapma2 = Math.sqrt(standart_sapma2);
				result 	= result.concat(String.format(" %.2f )\n ",standart_sapma2));
				
				//farklarýn ortalamasý
				double fark_ortalama=0;
				for(int uu =0; uu< N ;uu++)
					fark_ortalama= fark_ortalama+ Math.abs(preference[uu][manPartner[uu]][0] - preference[uu][manPartner[uu]][1]);
				fark_ortalama = fark_ortalama/N;
				result 	= result.concat(String.format(" fark ortalamasi:  %.2f \n ",fark_ortalama));
				
				
				//SEM
				double sum_of_difference =0;
				 
				for(int ii = 0; ii< N ; ii++)
				{
					sum_of_difference= sum_of_difference + Math.abs(preference[ii][manPartner[ii]][0] - preference[ii][manPartner[ii]][1]);
			 
					
				}
		 
				result 	= result.concat(String.format(" SEM toplamý ortalamasi:  %.2f \n ",sum_of_difference));
				
				//EGALITAREAN
				
				double egalitarean_sum =0;
				for(int ii = 0; ii< N ; ii ++)
				{
					egalitarean_sum= egalitarean_sum + Math.abs(preference[ii][manPartner[ii]][0] + preference[ii][manPartner[ii]][1]);
					 
					
				}	
				//System.out.printf(" \n\nMEN OPTIMAL result: %s \n\n",result);
				
				Preference_Matrix.sonuc_ortalamalari_men_op[0][0]=Preference_Matrix.sonuc_ortalamalari_men_op[0][0]+ (double) (worstcase1) ;
				Preference_Matrix.sonuc_ortalamalari_men_op[1][0]=Preference_Matrix.sonuc_ortalamalari_men_op[1][0]+(double) (worstcase2) ;
				Preference_Matrix.sonuc_ortalamalari_men_op[0][1]=Preference_Matrix.sonuc_ortalamalari_men_op[0][1]+ (double) (bestcase1) ;
				Preference_Matrix.sonuc_ortalamalari_men_op[1][1]=Preference_Matrix.sonuc_ortalamalari_men_op[1][1]+(double) (bestcase2 );
				Preference_Matrix.sonuc_ortalamalari_men_op[0][2]=Preference_Matrix.sonuc_ortalamalari_men_op[0][2]+(double) (aritmetik_ortalama1 );
				Preference_Matrix.sonuc_ortalamalari_men_op[1][2]=Preference_Matrix.sonuc_ortalamalari_men_op[1][2]+(double) (aritmetik_ortalama2) ;
				Preference_Matrix.sonuc_ortalamalari_men_op[0][3]=Preference_Matrix.sonuc_ortalamalari_men_op[0][3]+ (double)(standart_sapma1) ;
				Preference_Matrix.sonuc_ortalamalari_men_op[1][3]=Preference_Matrix.sonuc_ortalamalari_men_op[1][3]+ (double) (standart_sapma2 );
				Preference_Matrix.sonuc_ortalamalari_men_op[0][4]=Preference_Matrix.sonuc_ortalamalari_men_op[0][4]+ (double) (fark_ortalama) ;
				Preference_Matrix.sonuc_ortalamalari_men_op[1][4]=Preference_Matrix.sonuc_ortalamalari_men_op[1][4]+ (double)(sum_of_difference) ;
				Preference_Matrix.sonuc_ortalamalari_men_op[0][5]=Preference_Matrix.sonuc_ortalamalari_men_op[0][5]+ (double) (egalitarean_sum) ;
				
			for(int uu =0; uu< 2; uu++)
				for(int yy=0;yy<Preference_Matrix.sonuc_matis_boy ; yy++)
					Preference_Matrix.sonuc_men_op [uu][yy] = Preference_Matrix.sonuc_ortalamalari_men_op[uu][yy]/Preference_Matrix.numClicks_stable_marriage_men_optimal;

			System.out.printf(" \n\nMEN OPTIMAL Sonuc: \n\n");
			for(int uu = 0; uu< Preference_Matrix.sonuc_matis_boy; uu++)
			{
				System.out.printf("( %.2f, %.2f)\n", Preference_Matrix.sonuc_men_op [0][uu],Preference_Matrix.sonuc_men_op [1][uu]);
			}
			
			}
		
		}
		
		
		
		
		if( flag_cins == 2)
		{
			System.out.printf("\n\nWomen optimal Sonuc: \n\n");
	    	System.out.printf("\n\n%s\n\n",result);
		if(flag==1)
		{
			
			double worstcase1 = 0;
			double bestcase1= N;
			for(int uu =0; uu < N ;uu++)
			{
				if(worstcase1 < preference[manPartner[uu]][uu][0])
					worstcase1 = preference[manPartner[uu]][uu][0];
				if(bestcase1 > preference[manPartner[uu]][uu][0])
					bestcase1 = preference[manPartner[uu]][uu][0];			 
			}
			result 	= result.concat(String.format("ilk kolon, en iyi : %.1f , ",bestcase1));
			result 	= result.concat(String.format("en kötü : %.1f \n",worstcase1));
			
			double worstcase2 = 0;
			double bestcase2= N;
			for(int uu =0; uu < N ;uu++)
			{
				if(worstcase2 < preference[manPartner[uu]][uu][1])
					worstcase2 = preference[manPartner[uu]][uu][1];
				if(bestcase2 > preference[manPartner[uu]][uu][1])
					bestcase2 = preference[manPartner[uu]][uu][1];			 
			}
			result 	= result.concat(String.format("en iyi : %.1f , ",bestcase2));
			result 	= result.concat(String.format("en kötü : %.1f \n",worstcase2));
			
			
			
			
			
			double  aritmetik_ortalama1=0;
			for(int uu=0;uu<N;uu++)
				aritmetik_ortalama1 = aritmetik_ortalama1 + preference[manPartner[uu]][uu][0];
			aritmetik_ortalama1= aritmetik_ortalama1 /N;	
			 result 	= result.concat(String.format("aritmetik ortalama: ( %.2f , ",aritmetik_ortalama1));
			 
			
				double  aritmetik_ortalama2=0;
				for(int uu=0;uu<N;uu++)
					aritmetik_ortalama2 = aritmetik_ortalama2 + preference[manPartner[uu]][uu][1];
				aritmetik_ortalama2 = aritmetik_ortalama2 /N;
				result 	= result.concat(String.format(" %.2f ) \n",aritmetik_ortalama2));
				
				 double  standart_sapma1=0;
					for(int uu=0;uu<N;uu++)
						standart_sapma1 = standart_sapma1 + ( preference[manPartner[uu]][uu][0] - aritmetik_ortalama1) * ( preference[manPartner[uu]][uu][0] - aritmetik_ortalama1);
					standart_sapma1 = standart_sapma1 /N;
					standart_sapma1 = Math.sqrt(standart_sapma1);
					result 	= result.concat(String.format(" standart sapma: ( %.2f , ",standart_sapma1));
				
				
				double standart_sapma2=0;
				for(int uu=0;uu<N;uu++)
					standart_sapma2 = standart_sapma2 + (preference[manPartner[uu]][uu][1] - aritmetik_ortalama2) * (preference[manPartner[uu]][uu][1] - aritmetik_ortalama2);
				standart_sapma2 = standart_sapma2 /N;
				standart_sapma2 = Math.sqrt(standart_sapma2);
				result 	= result.concat(String.format(" %.2f )\n ",standart_sapma2));
				
				double fark_ortalama=0;
				for(int uu =0; uu< N ;uu++)
					fark_ortalama= fark_ortalama+ Math.abs(preference[manPartner[uu]][uu][1] - preference[manPartner[uu]][uu][0]);
				fark_ortalama = fark_ortalama/N;
				result 	= result.concat(String.format(" fark ortalamasi:  %.2f \n ",fark_ortalama));
				
				
				//SEM
				double sum_of_difference =0;
				 
				for(int ii = 0; ii< N ; ii++)
				{
					sum_of_difference= sum_of_difference + Math.abs(preference[manPartner[ii]][ii][1] - preference[manPartner[ii]][ii][0]);
			 
					
				}
		 
				result 	= result.concat(String.format(" SEM toplamý ortalamasi:  %.2f \n ",sum_of_difference));
				
				//EGALITAREAN
				
				double egalitarean_sum =0;
				for(int ii = 0; ii< N ; ii ++)
				{
					egalitarean_sum= egalitarean_sum + Math.abs(preference[manPartner[ii]][ii][1] + preference[manPartner[ii]][ii][0]);
					 
					
				}	
			//	System.out.printf(" \n\nWOMEN OPTIMAL result: %s \n\n",result);
				
				
				Preference_Matrix.sonuc_ortalamalari_women_op[0][0]=Preference_Matrix.sonuc_ortalamalari_women_op[0][0]+ (double) (worstcase1) ;
				Preference_Matrix.sonuc_ortalamalari_women_op[1][0]=Preference_Matrix.sonuc_ortalamalari_women_op[1][0]+(double) (worstcase2) ;
				Preference_Matrix.sonuc_ortalamalari_women_op[0][1]=Preference_Matrix.sonuc_ortalamalari_women_op[0][1]+ (double) (bestcase1) ;
				Preference_Matrix.sonuc_ortalamalari_women_op[1][1]=Preference_Matrix.sonuc_ortalamalari_women_op[1][1]+(double) (bestcase2 );
				Preference_Matrix.sonuc_ortalamalari_women_op[0][2]=Preference_Matrix.sonuc_ortalamalari_women_op[0][2]+(double) (aritmetik_ortalama1 );
				Preference_Matrix.sonuc_ortalamalari_women_op[1][2]=Preference_Matrix.sonuc_ortalamalari_women_op[1][2]+(double) (aritmetik_ortalama2) ;
				Preference_Matrix.sonuc_ortalamalari_women_op[0][3]=Preference_Matrix.sonuc_ortalamalari_women_op[0][3]+ (double)(standart_sapma1) ;
				Preference_Matrix.sonuc_ortalamalari_women_op[1][3]=Preference_Matrix.sonuc_ortalamalari_women_op[1][3]+ (double) (standart_sapma2 );
				Preference_Matrix.sonuc_ortalamalari_women_op[0][4]=Preference_Matrix.sonuc_ortalamalari_women_op[0][4]+ (double) (fark_ortalama) ;
				Preference_Matrix.sonuc_ortalamalari_women_op[1][4]=Preference_Matrix.sonuc_ortalamalari_women_op[1][4]+ (double)(sum_of_difference) ;
				Preference_Matrix.sonuc_ortalamalari_women_op[0][5]=Preference_Matrix.sonuc_ortalamalari_women_op[0][5]+ (double) (egalitarean_sum) ;
				
			for(int uu =0; uu< 2; uu++)
				for(int yy=0;yy<Preference_Matrix.sonuc_matis_boy ; yy++)
					Preference_Matrix.sonuc_women_op [uu][yy] = Preference_Matrix.sonuc_ortalamalari_women_op[uu][yy]/Preference_Matrix.numClicks_stable_marriage_women_optimal;
		
			System.out.printf(" \n\nWOMEN OPTIMAL Sonuc: \n\n");
			for(int uu = 0; uu< Preference_Matrix.sonuc_matis_boy; uu++)
			{
				System.out.printf("( %.2f, %.2f)\n", Preference_Matrix.sonuc_women_op [0][uu],Preference_Matrix.sonuc_women_op [1][uu]);
			}		
				
			
		}
		
	
		}		
				
				
		 if(unstability == true)
		 { result 	= result.concat(String.format("\nSolution is unstable.\n"));
		   result =result.concat(	blocking_pairs);
		 }
		 else
			 result 	= result.concat(String.format("\nSolution is stable.\n"));
		
		
	
	
	}
}
