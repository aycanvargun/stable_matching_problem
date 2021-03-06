package stable_marriage;

public class Maybe_Latter {
	
	 public int N =4;
	    public   int[][][] preference =  new int[N][N][2]; 
	    public int prefere[][] =  new int[2*N][N]; 
	    public String blocking_pairs="";	
public Maybe_Latter(int[][][] preference,  int prefere[][],int N , String blocking_pairs)
{
	this.preference = preference;
	this.prefere =prefere;
	this.N = N;
	this.blocking_pairs =blocking_pairs; 
}
	
	public int[] maybeLater() {

		 
		int[] availableM = new int[N];
		int[] result = new int[N];
		
		for(int initial =0; initial< N ;initial++)
			{
			result[initial] =-1;
			availableM[initial]=-1;
			}
		for(int k = 0; k < N; k++)
		{
			
			for (int m = 0; m < N; m++)
			{
				if (availableM[m] != -1)
					continue;
				for (int s = m, next=0; next<=k; next++)
				{
					int rankbul;
					for( rankbul =0;rankbul <N ;rankbul ++ )
						if(preference[s][rankbul][0] == next)
							break;
					int w = rankbul;				
					
					
					if (preference[s][w][1] <= k && result[w] == -1) 
					{
						availableM[s] = w; 
						
						result[w] = s; 
						break; ///???
					}
					else if(preference[s][w][1] <= k && preference[s][w][1] < preference[result[w]][w][1])
					{ 
						availableM[s] = w; 
						availableM[result[w]] = -1;
						
						int t = result[w]; 
						result[w] = s; 
						s = t; 
						next=0;
					}
				}

			}
		}
		
		///stability kontrol i�in
		
		int MANprefer[][]= new int [N][N];
		int WOMANprefer[][]= new int [N][N];

for(int i=0; i< 2*N;i++)
		{
			for(int j=0; j<N; j++)
			{
				
				if(i< N )
				{
					MANprefer[i][j]=prefere[i][j];
				}
				else
				{
					WOMANprefer[i-N][j]=prefere[i][j];
					
				}
				

			}

		}

	Unstability u = new Unstability (blocking_pairs,N);
	boolean unstability=u.unstable(availableM,result, MANprefer, WOMANprefer);

 
		
		if(unstability == false)
		{
			///tekrarl� oynama i�in hesaplar
			System.out.println("\n\nMaybe latter solution is stable. :)))))))\n\n");
			
			for(int uu = 0; uu < N ; uu ++)
			{
				System.out.printf("man: %d   woman: %d tercihleri: ( %d , %d ) \n", uu+1, availableM[uu]+1, preference[uu][availableM[uu]][0], preference[uu][availableM[uu]][1]);
			}
			
			
			double worstcase1 = 0;
			double bestcase1= N;
			for(int uu =0; uu < N ;uu++)
			{
				if(worstcase1 < preference[uu][availableM[uu]][0])
					worstcase1 = preference[uu][availableM[uu]][0];
				if(bestcase1 > preference[uu][availableM[uu]][0])
					bestcase1 = preference[uu][availableM[uu]][0];			 
			}
			
			String result_st="";
			result_st 	= result_st.concat(String.format("ilk kolon, en iyi : %.1f , ",bestcase1));
			result_st 	= result_st.concat(String.format("en k�t� : %.1f \n",worstcase1));
			
			double worstcase2 = 0;
			double bestcase2= N;
			for(int uu =0; uu < N ;uu++)
			{
				if(worstcase2 < preference[uu][availableM[uu]][1])
					worstcase2 =  preference[uu][availableM[uu]][1];
				if(bestcase2 > preference[uu][availableM[uu]][1])
					bestcase2 = preference[uu][availableM[uu]][1];			 
			}
			result_st 	= result_st.concat(String.format("en iyi : %.1f , ",bestcase2));
			result_st 	= result_st.concat(String.format("en k�t� : %.1f \n",worstcase2));
			
			
			double  aritmetik_ortalama1=0;
			
			for(int uu=0;uu<N;uu++)
				aritmetik_ortalama1 = aritmetik_ortalama1 + preference[uu][availableM[uu]][0];
			aritmetik_ortalama1= aritmetik_ortalama1 /N;	
			result_st 	= result_st.concat(String.format("aritmetik ortalama: ( %.2f , ",aritmetik_ortalama1));			
				
			
						
			double  aritmetik_ortalama2=0;		
		    for(int uu=0;uu<N;uu++)
				aritmetik_ortalama2 = aritmetik_ortalama2 + preference[uu][availableM[uu]][1];
			aritmetik_ortalama2= aritmetik_ortalama2 /N;
			result_st 	= result_st.concat(String.format(" %.2f ) \n",aritmetik_ortalama2));

			
			
			
			double  standart_sapma1=0;
			for(int uu=0;uu<N;uu++)
				standart_sapma1 = standart_sapma1 + (preference[uu][availableM[uu]][0] - aritmetik_ortalama1) * (preference[uu][availableM[uu]][0] - aritmetik_ortalama1);
			standart_sapma1 = standart_sapma1 /N;
			standart_sapma1 = Math.sqrt(standart_sapma1);
			result_st 	= result_st.concat(String.format(" standart sapma: ( %.2f , ",standart_sapma1));
			
			
			double standart_sapma2 =0;
			for(int uu=0;uu<N;uu++)
				standart_sapma2 = standart_sapma2 + (preference[uu][availableM[uu]][1] - aritmetik_ortalama2) * (preference[uu][availableM[uu]][1] - aritmetik_ortalama2);
			standart_sapma2 = standart_sapma2 /N;
			standart_sapma2 = Math.sqrt(standart_sapma2);
			result_st 	= result_st.concat(String.format(" %.2f )\n ",standart_sapma2));
			
			//farklar�n ortalamas�
			double fark_ortalama=0;
			for(int uu =0; uu< N ;uu++)
				fark_ortalama= fark_ortalama+ Math.abs(preference[uu][availableM[uu]][0] - preference[uu][availableM[uu]][1]);
			fark_ortalama = fark_ortalama/N;
			result_st 	= result_st.concat(String.format(" fark ortalamasi:  %.2f \n ",fark_ortalama));
			
			 //SEM
			double sum_of_difference =0;
			 
			for(int ii = 0; ii< N ; ii++)
			{
				sum_of_difference= sum_of_difference + Math.abs(preference[ii][availableM[ii]][0] - preference[ii][availableM[ii]][1]);
		 
				
			}
	 
			result_st 	= result_st.concat(String.format(" SEM toplam� ortalamasi:  %.2f \n ",sum_of_difference));
			
			//EGALITAREAN
			
			double egalitarean_sum =0;
			for(int ii = 0; ii< N ; ii ++)
			{
				egalitarean_sum= egalitarean_sum + Math.abs(preference[ii][availableM[ii]][0] + preference[ii][availableM[ii]][1]);
				 
				
			}
			
			
			
			//System.out.printf(" \n\nMEN OPTIMAL result: %s \n\n",result);
			
			Preference_Matrix.sonuc_ortalamalari_maybelatter [0][0]=Preference_Matrix.sonuc_ortalamalari_maybelatter [0][0]+ (double) (worstcase1) ;
			Preference_Matrix.sonuc_ortalamalari_maybelatter [1][0]=Preference_Matrix.sonuc_ortalamalari_maybelatter [1][0]+(double) (worstcase2) ;
			Preference_Matrix.sonuc_ortalamalari_maybelatter [0][1]=Preference_Matrix.sonuc_ortalamalari_maybelatter [0][1]+ (double) (bestcase1) ;
			Preference_Matrix.sonuc_ortalamalari_maybelatter [1][1]=Preference_Matrix.sonuc_ortalamalari_maybelatter [1][1]+(double) (bestcase2 );
			Preference_Matrix.sonuc_ortalamalari_maybelatter [0][2]=Preference_Matrix.sonuc_ortalamalari_maybelatter [0][2]+(double) (aritmetik_ortalama1 );
			Preference_Matrix.sonuc_ortalamalari_maybelatter [1][2]=Preference_Matrix.sonuc_ortalamalari_maybelatter [1][2]+(double) (aritmetik_ortalama2) ;
			Preference_Matrix.sonuc_ortalamalari_maybelatter [0][3]=Preference_Matrix.sonuc_ortalamalari_maybelatter [0][3]+ (double)(standart_sapma1) ;
			Preference_Matrix.sonuc_ortalamalari_maybelatter [1][3]=Preference_Matrix.sonuc_ortalamalari_maybelatter [1][3]+ (double) (standart_sapma2 );
			Preference_Matrix.sonuc_ortalamalari_maybelatter [0][4]=Preference_Matrix.sonuc_ortalamalari_maybelatter [0][4]+ (double) (fark_ortalama) ;
			Preference_Matrix.sonuc_ortalamalari_maybelatter [1][4]=Preference_Matrix.sonuc_ortalamalari_maybelatter [1][4]+ (double)(sum_of_difference) ;
			Preference_Matrix.sonuc_ortalamalari_maybelatter  [0][5]=Preference_Matrix.sonuc_ortalamalari_maybelatter  [0][5]+ (double) (egalitarean_sum) ;
			
			
		for(int uu =0; uu< 2; uu++)
			for(int yy=0;yy<Preference_Matrix.sonuc_matis_boy ; yy++)
				Preference_Matrix.sonuc_maybelatter  [uu][yy] = Preference_Matrix.sonuc_ortalamalari_maybelatter [uu][yy]/ Preference_Matrix.numClicks_maybelatter;

		System.out.printf(" \n\nMAYBE LATTER Sonuc: \n\n");
		for(int uu = 0; uu< Preference_Matrix.sonuc_matis_boy; uu++)
		{
			System.out.printf("( %.2f, %.2f)\n", Preference_Matrix.sonuc_maybelatter  [0][uu],Preference_Matrix.sonuc_maybelatter  [1][uu]);
		}
		
			 
			
		}
		else
			System.out.println("\n\nMaybe latter solution is unstable.\n\n");
		
		
		
		return result;
	}
}
