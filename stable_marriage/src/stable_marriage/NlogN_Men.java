package stable_marriage;

 
import java.util.LinkedList;
import java.util.ListIterator;

public class NlogN_Men {
	  
	public static LinkedList<Object[]> linkedlist_sinirlamasiz = new LinkedList<Object[]>();
	public static LinkedList<Object[]> linkedlist_sinirlamali = new LinkedList<Object[]>();

	 public int N ;
	    public   int[][][] preference =  new int[N][N][2]; 
	    public int prefere[][] =  new int[2*N][N]; 
	    public String blocking_pairs="";
	    public int flag_continue;
	    int terciharalýgý[][]=new int[2*N][3]	; 
	    
	public NlogN_Men(int[][][] preference,  int prefere[][],int N , String blocking_pairs, int flag_continue,    int terciharalýgý[][])
	{
		this.preference = preference;
		this.prefere =prefere;
		this.N = N;
		this.blocking_pairs =blocking_pairs; 
		this.flag_continue =  flag_continue;
		this.terciharalýgý=terciharalýgý; 
	}
	
	
	public int nlogngaleshapley(int[][] temp_prefere, int click_number ,int son_flag, int flag_cins)
	{
		int i,j,freeCount,m,w,m1; String result;
		
		 
		int MANprefer[][]= new int [N][N];
		int WOMANprefer[][]= new int [N][N];
		int wPartner[] = new int [N];
		int manPartner[] = new int [N];
		int mFree[] = new int [N];
		boolean unstability = false;
		flag_continue=0;
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
			manPartner[i]=-1;
		}

		freeCount = N;

	
		while (freeCount > 0)
		{
			 

			for (m = 0; m < N; m++)
			{
				if (mFree[m] == 0)
					break;
			}
			
			int[] teklif_sayacý= new int[N]; 
			for (  i = 0; i < N && mFree[m] == 0  && teklif_sayacý[m] <N; i++)
			{
				w = temp_prefere[m][i];
               
				
				if(preference[m][w][1]<=terciharalýgý[w+N][2]) /// burda kadýn tercih aralýðý sýnýrlanýyor
				{
				if (wPartner[w] == -1 )
				{
					wPartner[w] = m;
					mFree[m] = 1;
					freeCount--;
				}

				else   
				{
					 
					m1 = wPartner[w];

					 
					if (Preference_Matrix.wPrefersM1OverM(temp_prefere, w, m, m1) == 0 )
					{
						wPartner[w] = m;
						mFree[m] = 1;
						mFree[m1] = 0; 
					}
				}  

								
				}
				teklif_sayacý[m]++;
				
				 
				
			} 
		
			 if(teklif_sayacý[m] == N && mFree[m] == 0)
			
			break;
				
		}  


		   
		for (  i = 0; i < N; i++)        
		{
			 
			if( wPartner[i] != -1)
			manPartner[ wPartner[i]]= i;
			else
			{
				System.out.printf("\neslesme yok\n");
				flag_continue = 5000;
			}

		}
		
		if (flag_continue != 5000)
		{	Unstability u = new Unstability (blocking_pairs,N);
		 unstability=u.unstable(manPartner,wPartner, MANprefer, WOMANprefer);
		}  
			//ara deðerleri saklamak
			
			if( unstability == false && flag_cins == 3) // stable bir sýnýrlamalý men op  
			{   result = "nlogn sýnýrlamalý men Optimal ara sonuçlar: \n";
				if(son_flag ==0 && flag_continue != 5000) //0 flag'i sadece ara deðerlerde var, stable 
			{       int ara_sonuc[][] = new int[N][4];//ara sonuçlar birikiyor, burda parametreler arttýrýlabilir, hangi problemin ara sonucu, kaçýncý ara sonucu ..vb
			       int[][][] preference_subjectif =  new int[N][N][2]; 
			       
			      for(int uu =0; uu< N ; uu++)
			      {
			    	  for(int vv=0; vv< N; vv++)
			    	  {
			    		  preference_subjectif[uu][vv][0]=preference[uu][vv][0];
			    		  preference_subjectif[uu][vv][1]=preference[uu][vv][1];
			    		  
			    	  }
			      }
			       
			       
					for (  i = 0; i < N; i++)        
					{			 ara_sonuc[i][0]=i+1;
							     ara_sonuc[i][1]=manPartner[i]+1;
							     ara_sonuc[i][2]=preference[i][manPartner[i]][0];		 
							     ara_sonuc[i][3]= preference[i][manPartner[i]][1];	 
							     
							  result 	= result.concat(String.format("man: %d   woman: %d tercihleri: ( %d , %d ) \n",i+1 ,manPartner[i]+1, preference[i][manPartner[i]][0],  preference[i][manPartner[i]][1]));
						 
					}
					
					boolean is_contain = control(ara_sonuc,3);
					if(is_contain == false)						
					{
						Object object[] = new Object[3];
						 object[0] = ara_sonuc;
						 object[1] = Preference_Matrix.sayac_sinirlamali;
						 object[2] = preference_subjectif;
						 linkedlist_sinirlamali.add(object);
						 ara_sonu_ortalamalari_sinirlamali(0,0);
					}
					 
					
					System.out.printf("\n\n%s\n\n",result);
						 	
			}
				
			}
		 
			if( unstability == false &&  flag_cins == 5) // stable bir sýnýrlamasýzmen op  
			{  result = "nlogn sýnýrlamasýz men Optimal ara sonuçlar: \n";
			
				if(son_flag ==0 && flag_continue != 5000) //0 flag'i sadece ara deðerlerde var, stable 
			{    int ara_sonuc[][] = new int[N][4];//ara sonuçlar birikiyor, burda parametreler arttýrýlabilir, hangi problemin ara sonucu, kaçýncý ara sonucu ..vb
			int[][][] preference_subjectif =  new int[N][N][2]; 
		       
		      for(int uu =0; uu< N ; uu++)
		      {
		    	  for(int vv=0; vv< N; vv++)
		    	  {
		    		  preference_subjectif[uu][vv][0]=preference[uu][vv][0];
		    		  preference_subjectif[uu][vv][1]=preference[uu][vv][1];
		    		  
		    	  }
		      }
					for (  i = 0; i < N; i++)        
					{
					 ara_sonuc[i][0]=i+1;
				     ara_sonuc[i][1]=manPartner[i]+1;
				     ara_sonuc[i][2]=preference[i][manPartner[i]][0];		 
				     ara_sonuc[i][3]= preference[i][manPartner[i]][1];	 
				
			 
							  result 	= result.concat(String.format("man: %d   woman: %d tercihleri: ( %d , %d ) \n",i+1 ,manPartner[i]+1, preference[i][manPartner[i]][0],  preference[i][manPartner[i]][1]));
						 
					}
					
					boolean is_contain = control(ara_sonuc,5);
					if(is_contain == false)	
					{
					 
						Object object[] = new Object[3];
						 object[0] = ara_sonuc;
						 object[1] = Preference_Matrix.sayac_sinirlamasiz;
						 object[2] = preference_subjectif;
						 linkedlist_sinirlamasiz.add(object);
						 ara_sonu_ortalamalari_sinirlamasiz(0,0);	
					}
					System.out.printf("\n\n%s\n\n",result);
						 	
					
			}
				
			}
		 
			  result = "nlogn men Optimal solution : \n";
		 
		if(flag_continue ==0 && son_flag == 1)
				{	
		for (  i = 0; i < N; i++)        
		{			 
				  result 	= result.concat(String.format("man: %d   woman: %d tercihleri: ( %d , %d ) \n",i+1 ,manPartner[i]+1, preference[i][manPartner[i]][0],  preference[i][manPartner[i]][1]));
			 
		}
			 
		
			
		}
		
		if( flag_cins == 3) // sýnýrlamalý men op (benimki)
		{
			if(son_flag ==1) //1 flag'i sadece en sonuncusu hesaplanmasýn diye, ara çalýþmaalrda bu flag 0
		{
				
				System.out.printf(" \n\nSINIRLAMALI MEN OP Sonuc: \n\n");
				
				System.out.printf("\n\n%s\n\n",result);
				
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
				worstcase2 = preference[uu][manPartner[uu]][1];
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
			
			
				double aritmetik_ortalama2=0;
				for(int uu=0;uu<N;uu++)
					aritmetik_ortalama2 = aritmetik_ortalama2 + preference[uu][manPartner[uu]][1];
				aritmetik_ortalama2  = aritmetik_ortalama2 /N;
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
			//	System.out.printf(" \n\nSINIRLAMALI MEN OP result: %s \n\n",result);
				Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[0][0]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[0][0]+ (double) (worstcase1) ;
				Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[1][0]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[1][0]+(double) (worstcase2) ;
				Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[0][1]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[0][1]+ (double) (bestcase1) ;
				Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[1][1]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[1][1]+(double) (bestcase2 );
				Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[0][2]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[0][2]+(double) (aritmetik_ortalama1 );
				Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[1][2]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[1][2]+(double) (aritmetik_ortalama2) ;
				Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[0][3]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[0][3]+ (double)(standart_sapma1) ;
				Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[1][3]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[1][3]+ (double) (standart_sapma2 );
				Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[0][4]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[0][4]+ (double) (fark_ortalama) ;
				Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[1][4]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[1][4]+ (double)(sum_of_difference) ;
				Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[0][5]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[0][5]+ (double) (egalitarean_sum) ;
				
			for(int uu =0; uu< 2; uu++)
				for(int yy=0;yy<Preference_Matrix.sonuc_matis_boy ; yy++)
					Preference_Matrix.sonuc_sinirlamali_men_op [uu][yy] = Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_men_op[uu][yy]/click_number;

			
			System.out.printf(" \n\nSINIRLAMALI MEN OP Sonuc: \n\n");
			for(int uu = 0; uu< Preference_Matrix.sonuc_matis_boy; uu++)
			{
				System.out.printf("( %.2f, %.2f)\n", Preference_Matrix.sonuc_sinirlamali_men_op [0][uu],Preference_Matrix.sonuc_sinirlamali_men_op [1][uu]);
			}
				
	}	}
		if(flag_cins == 5)
		{
			
			if(son_flag ==1) //1 flag'i sadece en sonuncusu hesaplanmasýn diye, ara çalýþmaalrda bu flag 0
			{
				
	System.out.printf(" \n\nSINIRLAMASIZ MEN OP Sonuc: \n\n");
				
				System.out.printf("\n\n%s\n\n",result);
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
					worstcase2 = preference[uu][manPartner[uu]][1];
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
				
				
					double aritmetik_ortalama2=0;
					for(int uu=0;uu<N;uu++)
						aritmetik_ortalama2 = aritmetik_ortalama2 + preference[uu][manPartner[uu]][1];
					aritmetik_ortalama2  = aritmetik_ortalama2 /N;
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
				//	System.out.printf(" \n\nSINIRLAMASIZ MEN OP result: %s \n\n",result);
					Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [0][0]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op  [0][0]+ (double) (worstcase1) ;
					Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [1][0]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [1][0]+(double) (worstcase2) ;
					Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [0][1]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [0][1]+ (double) (bestcase1) ;
					Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [1][1]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [1][1]+(double) (bestcase2 );
					Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [0][2]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [0][2]+(double) (aritmetik_ortalama1 );
					Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [1][2]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [1][2]+(double) (aritmetik_ortalama2) ;
					Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [0][3]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [0][3]+ (double)(standart_sapma1) ;
					Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [1][3]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [1][3]+ (double) (standart_sapma2 );
					Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [0][4]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [0][4]+ (double) (fark_ortalama) ;
					Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [1][4]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [1][4]+ (double)(sum_of_difference) ;
					Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [0][5]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [0][5]+ (double) (egalitarean_sum) ;
					
				for(int uu =0; uu< 2; uu++)
					for(int yy=0;yy<Preference_Matrix.sonuc_matis_boy ; yy++)
						Preference_Matrix.sonuc_sinirlamasiz_men_op [uu][yy] = Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_men_op [uu][yy]/click_number;

				
				System.out.printf(" \n\nSINIRLAMASIZ MEN OP Sonuc: \n\n");
				for(int uu = 0; uu< Preference_Matrix.sonuc_matis_boy; uu++)
				{
					System.out.printf("( %.2f, %.2f)\n", Preference_Matrix.sonuc_sinirlamasiz_men_op [0][uu],Preference_Matrix.sonuc_sinirlamasiz_men_op [1][uu]);
				}
				
				
		}
			
			
		}
		
		
		
				
				
		 if(unstability == true || flag_continue == 5000)
		 { result 	= result.concat(String.format("\nSolution is unstable.\n"));
		   result =result.concat(	blocking_pairs);
		 }
		 else
			 result 	= result.concat(String.format("\nSolution is stable.\n"));
		
		
	
			 
		 
		  return flag_continue;
	}
	public boolean control(int  ara_sonuc[][], int flag)
	{
		boolean is_contain =false;
	 
		int compare[][] = new int[N][4]; 
		Object object[] = new Object[3];
		ListIterator<Object[]> itr;
		if(flag == 3)
		{
			 itr = linkedlist_sinirlamali.listIterator();
		}
		else
		{
			 itr = linkedlist_sinirlamasiz.listIterator();
		}
	   
	    
	    while(itr.hasNext())
	    {
	    	object = itr.next();
	    	compare =	(int[][]) object[0];
	    	 for(int i =0; i< N ; i++)
				 if( compare[i][0] == ara_sonuc[i][0] && compare[i][1] == ara_sonuc[i][1] && compare[i][2] == ara_sonuc[i][2] && compare[i][3] == ara_sonuc[i][3] )
				 
				 { 	 
					 is_contain = true;
					 
				 }
				 else
				 {
					 is_contain = false;
					 break;
				 }
	    	  
	    	 if(is_contain == true)
	    	 {
	    		 break;
	    		 
	    	 }
	    }
	    
	    
	 
		
			 
	 
		return is_contain;
		 
	}
	
	
	
	public void ara_sonu_ortalamalari_sinirlamasiz(int secenek, int flag)

	{
		 
		 

		double worstcase1 = 0;
		double bestcase1 = N;

		double worstcase2 = 0;
		double bestcase2 = N;

		double aritmetik_ortalama1 = 0;

		double aritmetik_ortalama2 = 0;

		double standart_sapma1 = 0;

		double standart_sapma2 = 0;

		double fark_ortalama = 0;
		int count=0;
 
		Object object[] = new Object[3];
		ListIterator<Object[]> itr;
 
		Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [0][0]=0 ;
		Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [1][0]=0;
		Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [0][1]=0 ;
		Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [1][1]=0;
		Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [0][2]=0;
		Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [1][2]=0 ;
		Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [0][3]=0;
		Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [1][3]=0;
		Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [0][4]=0;
		Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [1][4]=0 ;
		Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [0][5]=0;
		
			 itr = linkedlist_sinirlamasiz.listIterator();
	    int flag_no;
		int ara_sonuc[][] = new int[N][4];
		int[][][] preference_subjectif =  new int[N][N][2]; 
	    while(itr.hasNext())
	    {   
	    	object = itr.next();
	    	ara_sonuc =	(int[][]) object[0];
	    	flag_no= (int) object[1];
	    	preference_subjectif = (int[][][]) object[2] ;
	    	  /////////
	    	if(flag_no == 0)
	    	{
	    	int[] manPartner = new int[N];
		    for(int uu = 0 ; uu< N ; uu++)
		    {
		    	manPartner[uu]= ara_sonuc[uu][1]-1;
		    }
		      worstcase1 = 0;
			  bestcase1= N;
			for(int uu =0; uu < N ;uu++)
			{
				if(worstcase1 < preference_subjectif[uu][manPartner[uu]][0])
					worstcase1 = preference_subjectif[uu][manPartner[uu]][0];
				if(bestcase1 > preference_subjectif[uu][manPartner[uu]][0])
					bestcase1 = preference_subjectif[uu][manPartner[uu]][0];			 
			}
			  
			  worstcase2 = 0;
			  bestcase2= N;
			for(int uu =0; uu < N ;uu++)
			{
				if(worstcase2 < preference_subjectif[uu][manPartner[uu]][1])
					worstcase2 = preference_subjectif[uu][manPartner[uu]][1];
				if(bestcase2 > preference_subjectif[uu][manPartner[uu]][1])
					bestcase2 = preference_subjectif[uu][manPartner[uu]][1];			 
			}
			 
			
				   aritmetik_ortalama1=0;
				for(int uu=0;uu<N;uu++)
					aritmetik_ortalama1 = aritmetik_ortalama1 + preference_subjectif[uu][manPartner[uu]][0];
				aritmetik_ortalama1= aritmetik_ortalama1 /N;	
				 
				
					  aritmetik_ortalama2=0;
					for(int uu=0;uu<N;uu++)
						aritmetik_ortalama2 = aritmetik_ortalama2 + preference_subjectif[uu][manPartner[uu]][1];
					aritmetik_ortalama2  = aritmetik_ortalama2 /N;
					 
					    standart_sapma1=0;
						for(int uu=0;uu<N;uu++)
							standart_sapma1 = standart_sapma1 + (preference_subjectif[uu][manPartner[uu]][0] - aritmetik_ortalama1) * (preference_subjectif[uu][manPartner[uu]][0] - aritmetik_ortalama1);
						standart_sapma1 = standart_sapma1 /N;
						standart_sapma1 = Math.sqrt(standart_sapma1);
						 
					 
						
					  standart_sapma2 =0;
					for(int uu=0;uu<N;uu++)
						standart_sapma2 = standart_sapma2 + (preference_subjectif[uu][manPartner[uu]][1] - aritmetik_ortalama2) * (preference_subjectif[uu][manPartner[uu]][1] - aritmetik_ortalama2);
					standart_sapma2 = standart_sapma2 /N;
					standart_sapma2 = Math.sqrt(standart_sapma2);
					 	
					
					
					  fark_ortalama=0;
					for(int uu =0; uu< N ;uu++)
						fark_ortalama= fark_ortalama+ Math.abs(preference_subjectif[uu][manPartner[uu]][0] - preference_subjectif[uu][manPartner[uu]][1]);
					fark_ortalama = fark_ortalama/N;
					 
					
					
					 //SEM
						double sum_of_difference =0;
						 
						for(int ii = 0; ii< N ; ii++)
						{
							sum_of_difference= sum_of_difference + Math.abs(preference_subjectif[ii][manPartner[ii]][0] - preference_subjectif[ii][manPartner[ii]][1]);
					 
							
						}
				 
						 	
						//EGALITAREAN
						
						double egalitarean_sum =0;
						for(int ii = 0; ii< N ; ii ++)
						{
							egalitarean_sum= egalitarean_sum + Math.abs(preference_subjectif[ii][manPartner[ii]][0] + preference_subjectif[ii][manPartner[ii]][1]);
							 
							
						}	
				//	System.out.printf(" \n\nSINIRLAMASIZ MEN OP result: %s \n\n",result);
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [0][0]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz  [0][0]+ (double) (worstcase1) ;
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [1][0]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [1][0]+(double) (worstcase2) ;
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [0][1]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [0][1]+ (double) (bestcase1) ;
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [1][1]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [1][1]+(double) (bestcase2 );
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [0][2]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [0][2]+(double) (aritmetik_ortalama1 );
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [1][2]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [1][2]+(double) (aritmetik_ortalama2) ;
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [0][3]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [0][3]+ (double)(standart_sapma1) ;
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [1][3]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [1][3]+ (double) (standart_sapma2 );
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [0][4]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [0][4]+ (double) (fark_ortalama) ;
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [1][4]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [1][4]+ (double)(sum_of_difference) ;
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [0][5]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [0][5]+ (double) (egalitarean_sum) ;
					
				
	    	//////////////
	    	
				 count++;
	    }
	    }
	    
	    for(int uu =0; uu< 2; uu++)
			for(int yy=0;yy<Preference_Matrix.sonuc_matis_boy ; yy++)
				Preference_Matrix.sonuc_ara_deger_sinirlamasiz [uu][yy] = Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamasiz [uu][yy]/count;

		
		System.out.printf(" \n\nARA DEGERLER SINIRLAMASIZ MEN OP Sonuc: \n\n");
		for(int uu = 0; uu< Preference_Matrix.sonuc_matis_boy; uu++)
		{
			System.out.printf("( %.2f, %.2f)\n", Preference_Matrix.sonuc_ara_deger_sinirlamasiz [0][uu],Preference_Matrix.sonuc_ara_deger_sinirlamasiz [1][uu]);
		}
			
	    
	    
	    
	    
	}
	
	

	public void ara_sonu_ortalamalari_sinirlamali(int secenek, int flag)

	{
		 
		 

		double worstcase1 = 0;
		double bestcase1 = N;

		double worstcase2 = 0;
		double bestcase2 = N;

		double aritmetik_ortalama1 = 0;

		double aritmetik_ortalama2 = 0;

		double standart_sapma1 = 0;

		double standart_sapma2 = 0;

		double fark_ortalama = 0;
		int count=0;
 
		Object object[] = new Object[3];
		ListIterator<Object[]> itr;
 
	 
			 itr = linkedlist_sinirlamali.listIterator();
			    Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [0][0]=0 ;
				Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [1][0]=0 ;
				Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [0][1]=0 ;
				Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [1][1]=0;
				Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [0][2]=0;
				Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [1][2]=0 ;
				Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [0][3]=0 ;
				Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [1][3]=0;
				Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [0][4]=0 ;
				Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [1][4]=0 ;
				Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [0][5]=0 ;
				
			   int 	flag_no;
				int[][][] preference_subjectif =  new int[N][N][2];  
		int ara_sonuc[][] = new int[N][4];
	    while(itr.hasNext())
	    {  
	    	object = itr.next();
	    	ara_sonuc =	(int[][]) object[0];
	    	flag_no= (int) object[1];
	    	preference_subjectif = (int[][][])object[2];
	    	  /////////
	    	if(	flag_no == 0)
	    	{
	    	int[] manPartner = new int[N];
		    for(int uu = 0 ; uu< N ; uu++)
		    {
		    	manPartner[uu]= ara_sonuc[uu][1]-1;
		    }
		      worstcase1 = 0;
			  bestcase1= N;
			for(int uu =0; uu < N ;uu++)
			{
				if(worstcase1 < preference_subjectif[uu][manPartner[uu]][0])
					worstcase1 = preference_subjectif[uu][manPartner[uu]][0];
				if(bestcase1 > preference_subjectif[uu][manPartner[uu]][0])
					bestcase1 = preference_subjectif[uu][manPartner[uu]][0];			 
			}
			 
			
			  worstcase2 = 0;
			  bestcase2= N;
			for(int uu =0; uu < N ;uu++)
			{
				if(worstcase2 < preference_subjectif[uu][manPartner[uu]][1])
					worstcase2 = preference_subjectif[uu][manPartner[uu]][1];
				if(bestcase2 > preference_subjectif[uu][manPartner[uu]][1])
					bestcase2 = preference_subjectif[uu][manPartner[uu]][1];			 
			}
			 
			
			 	   aritmetik_ortalama1=0;
				for(int uu=0;uu<N;uu++)
					aritmetik_ortalama1 = aritmetik_ortalama1 + preference_subjectif[uu][manPartner[uu]][0];
				aritmetik_ortalama1= aritmetik_ortalama1 /N;	
				 
					  aritmetik_ortalama2=0;
					for(int uu=0;uu<N;uu++)
						aritmetik_ortalama2 = aritmetik_ortalama2 + preference_subjectif[uu][manPartner[uu]][1];
					aritmetik_ortalama2  = aritmetik_ortalama2 /N;
					 
					
					standart_sapma1=0;
						for(int uu=0;uu<N;uu++)
							standart_sapma1 = standart_sapma1 + (preference_subjectif[uu][manPartner[uu]][0] - aritmetik_ortalama1) * (preference_subjectif[uu][manPartner[uu]][0] - aritmetik_ortalama1);
						standart_sapma1 = standart_sapma1 /N;
						standart_sapma1 = Math.sqrt(standart_sapma1);
						 
					 
						
					  standart_sapma2 =0;
					for(int uu=0;uu<N;uu++)
						standart_sapma2 = standart_sapma2 + (preference_subjectif[uu][manPartner[uu]][1] - aritmetik_ortalama2) * (preference_subjectif[uu][manPartner[uu]][1] - aritmetik_ortalama2);
					standart_sapma2 = standart_sapma2 /N;
					standart_sapma2 = Math.sqrt(standart_sapma2);
					 	
					  fark_ortalama=0;
					for(int uu =0; uu< N ;uu++)
						fark_ortalama= fark_ortalama+ Math.abs(preference_subjectif[uu][manPartner[uu]][0] - preference_subjectif[uu][manPartner[uu]][1]);
					fark_ortalama = fark_ortalama/N;
					 
					
					//SEM
						double sum_of_difference =0;
						 
						for(int ii = 0; ii< N ; ii++)
						{
							sum_of_difference= sum_of_difference + Math.abs(preference_subjectif[ii][manPartner[ii]][0] - preference_subjectif[ii][manPartner[ii]][1]);
					 
							
						}
				 
						 	//EGALITAREAN
						
						double egalitarean_sum =0;
						for(int ii = 0; ii< N ; ii ++)
						{
							egalitarean_sum= egalitarean_sum + Math.abs(preference_subjectif[ii][manPartner[ii]][0] + preference_subjectif[ii][manPartner[ii]][1]);
							 
							
						}	
				//	System.out.printf(" \n\nSINIRLAMASIZ MEN OP result: %s \n\n",result);
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [0][0]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali  [0][0]+ (double) (worstcase1) ;
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [1][0]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [1][0]+(double) (worstcase2) ;
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [0][1]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [0][1]+ (double) (bestcase1) ;
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [1][1]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [1][1]+(double) (bestcase2 );
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [0][2]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [0][2]+(double) (aritmetik_ortalama1 );
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [1][2]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [1][2]+(double) (aritmetik_ortalama2) ;
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [0][3]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [0][3]+ (double)(standart_sapma1) ;
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [1][3]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [1][3]+ (double) (standart_sapma2 );
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [0][4]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [0][4]+ (double) (fark_ortalama) ;
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [1][4]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [1][4]+ (double)(sum_of_difference) ;
					Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [0][5]=Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [0][5]+ (double) (egalitarean_sum) ;
					
				
				
				
					
	    	//////////////
					 count++;
	    	
	    }	
	    }
	    
	    
	    for(int uu =0; uu< 2; uu++)
			for(int yy=0;yy<Preference_Matrix.sonuc_matis_boy ; yy++)
				Preference_Matrix.sonuc_ara_deger_sinirlamali [uu][yy] = Preference_Matrix.sonuc_ortalamalari_aradeger_sinirlamali [uu][yy]/count;

	    System.out.printf(" \n\nARA DEGERLER SINIRLAMALI MEN OP Sonuc: \n\n");
		for(int uu = 0; uu< Preference_Matrix.sonuc_matis_boy; uu++)
		{
			System.out.printf("count %d , ara deger: ( %.2f, %.2f)\n", count, Preference_Matrix.sonuc_ara_deger_sinirlamali [0][uu],Preference_Matrix.sonuc_ara_deger_sinirlamali [1][uu]);
		}
	    
	    
	}
}  