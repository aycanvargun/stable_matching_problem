package stable_marriage;
import java.util.LinkedList;
import java.util.ListIterator;
public class NlogN_Women {
	 public int N ;
	    public   int[][][] preference =  new int[N][N][2]; 
	    public int prefere[][] =  new int[2*N][N]; 
	    public String blocking_pairs="";
	    public int flag_continue;
	    int terciharalýgý[][]=new int[2*N][3]	; 
	    public static LinkedList<Object> linkedlist_woman = new LinkedList<Object>();
	    public NlogN_Women(int[][][] preference,  int prefere[][],int N , String blocking_pairs, int flag_continue,    int terciharalýgý[][])
		{
			this.preference = preference;
			this.prefere =prefere;
			this.N = N;
			this.blocking_pairs =blocking_pairs; 
			this.flag_continue =  flag_continue;
			this.terciharalýgý=terciharalýgý; 
		}
	
	public int nlognwomengaleshapley(int[][] temp_prefere,  int click_number,int son_flag , int flag_cins)
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
               
				
				if(preference[w][m][0]<=terciharalýgý[w][2]) /// burda erkek tercih aralýðý sýnýrlanýyor
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

		//printf("Man   Woman\n");  
		for (  i = 0; i < N; i++)        
		{
			//System.out.printf("%d       %d\n",  wPartner[i] ,i  );
			if( wPartner[i] != -1)
			manPartner[ wPartner[i]]= i;
			else
			{
				//System.out.printf("\neslesme yok\n");
				flag_continue = 5000;
			}

		}

		if (flag_continue != 5000)
		{	Unstability u = new Unstability (blocking_pairs,N);
		 unstability=u.unstable(manPartner,wPartner, MANprefer, WOMANprefer);
		} 
		 
		//ara deðerleri saklamak
			
			if( unstability == false && flag_cins == 4) // stable bir sýnýrlamalý men op  
			{   result = "nlogn sýnýrlamalý women Optimal ara sonuçlar: \n";
				
			
			if(son_flag ==0 && flag_continue != 5000) //0 flag'i sadece ara deðerlerde var, stable 
			{   
				
				int ara_sonuc[][] = new int[N][4];//ara sonuçlar birikiyor, burda parametreler arttýrýlabilir, hangi problemin ara sonucu, kaçýncý ara sonucu ..vb
				for (  i = 0; i < N; i++)        
					{			
					 ara_sonuc[i][0]=i+1;
				     ara_sonuc[i][1]=manPartner[i]+1;
				     ara_sonuc[i][2]= preference[manPartner[i]][i][1];		 
				     ara_sonuc[i][3]= preference[manPartner[i]][i][0];	 
					
					 result 	= result.concat(String.format("women: %d   men: %d tercihleri: ( %d , %d ) \n",i+1 ,manPartner[i]+1, preference[manPartner[i]][i][1],  preference[manPartner[i]][i][0]));
						 
					}
				
				 quick_sort(ara_sonuc,0, N-1);
				 int temp_1, temp_2;
				
				 for(int ii = 0; ii< N; ii ++)
				 {
					 temp_1 = ara_sonuc[ii][0];
					 ara_sonuc[ii][0]= ara_sonuc[ii][1];
					 ara_sonuc[ii][1]= temp_1;
					 
					 temp_2 = ara_sonuc[ii][3];
					 ara_sonuc[ii][3]= ara_sonuc[ii][2];
					 ara_sonuc[ii][2]= temp_2;
					 
				 }
					
				boolean is_contain = control(ara_sonuc);
				if(is_contain == false)						
					linkedlist_woman.add(ara_sonuc);
					System.out.printf("\n\n%s\n\n",result);
						 	
			}
				
			}
		 
			if( unstability == false &&  flag_cins == 6) // stable bir sýnýrlamasýzmen op  
			{  result = "nlogn sýnýrlamasýz women Optimal ara sonuçlar: \n";
			
				if(son_flag ==0 && flag_continue != 5000) //0 flag'i sadece ara deðerlerde var, stable 
			{
					for (  i = 0; i < N; i++)        
					{			 
						result 	= result.concat(String.format("women: %d   men: %d tercihleri: ( %d , %d ) \n",i+1 ,manPartner[i]+1, preference[manPartner[i]][i][1],  preference[manPartner[i]][i][0]));
						 
					}
					
					System.out.printf("\n\n%s\n\n",result);
						 	
					
			}
				
			}
	
			  result = "nlogn women Optimal solution : \n";
		 
			if(flag_continue ==0 && son_flag == 1)//women optimal hesaplanmýs
						{
			  for (  i = 0; i < N; i++)        
				{   
						  result 	= result.concat(String.format("woman: %d   man: %d tercihleri: ( %d , %d ) \n",i+1 ,manPartner[i]+1, preference[manPartner[i]][i][1],  preference[manPartner[i]][i][0]));
						 
				}
			  
			  
			  }
			  if(flag_cins == 4)
			  {
				 
				  if(son_flag==1)
				  {
					  
					   System.out.printf("\n\nSýnýrlamalý women optimal Sonuç: \n\n",result);
				  System.out.printf("\n\n%s\n\n",result);
				  double worstcase1 = 0;
					double bestcase1= N;
					for(int uu =0; uu < N ;uu++)
					{
						if(worstcase1 < preference[manPartner[uu]][uu][1])
							worstcase1 = preference[manPartner[uu]][uu][1];
						if(bestcase1 > preference[manPartner[uu]][uu][1])
							bestcase1 = preference[manPartner[uu]][uu][1];			 
					}
					result 	= result.concat(String.format("ilk kolon, en iyi : %.1f , ",bestcase1));
					result 	= result.concat(String.format("en kötü : %.1f \n",worstcase1));
					
					double worstcase2 = 0;
					double bestcase2= N;
					for(int uu =0; uu < N ;uu++)
					{
						if(worstcase2 < preference[manPartner[uu]][uu][0])
							worstcase2 = preference[manPartner[uu]][uu][0];
						if(bestcase2 > preference[manPartner[uu]][uu][0])
							bestcase2 = preference[manPartner[uu]][uu][0];			 
					}
					result 	= result.concat(String.format("en iyi : %.1f , ",bestcase2));
					result 	= result.concat(String.format("en kötü : %.1f \n",worstcase2));
					
				  
										
						double  aritmetik_ortalama1=0;
						for(int uu=0;uu<N;uu++)
							aritmetik_ortalama1 = aritmetik_ortalama1 + preference[manPartner[uu]][uu][1];
						aritmetik_ortalama1= aritmetik_ortalama1 /N;	
						 result 	= result.concat(String.format("aritmetik ortalama: ( %.2f , ",aritmetik_ortalama1));				 
										
						
							double aritmetik_ortalama2=0;
							for(int uu=0;uu<N;uu++)
								aritmetik_ortalama2 = aritmetik_ortalama2 + preference[manPartner[uu]][uu][0];
							aritmetik_ortalama2 = aritmetik_ortalama2 /N;
							result 	= result.concat(String.format(" %.2f ) \n",aritmetik_ortalama2));
							
							 double  standart_sapma1=0;
								for(int uu=0;uu<N;uu++)
									standart_sapma1 = standart_sapma1 + (preference[manPartner[uu]][uu][1] - aritmetik_ortalama1) * (preference[manPartner[uu]][uu][1] - aritmetik_ortalama1);
								standart_sapma1 = standart_sapma1 /N;
								standart_sapma1 = Math.sqrt(standart_sapma1);
								result 	= result.concat(String.format(" standart sapma: ( %.2f , ",standart_sapma1));
								
						
							double standart_sapma2=0;
							for(int uu=0;uu<N;uu++)
								standart_sapma2 = standart_sapma2 + (preference[manPartner[uu]][uu][0] - aritmetik_ortalama2) * (preference[manPartner[uu]][uu][0] - aritmetik_ortalama2);
							standart_sapma2 = standart_sapma2 /N;
							standart_sapma2 = Math.sqrt(standart_sapma2);
							result 	= result.concat(String.format(" %.2f )\n ",standart_sapma2));
									
							double fark_ortalama=0;
							for(int uu =0; uu< N ;uu++)
								fark_ortalama= fark_ortalama+ Math.abs(preference[manPartner[uu]][uu][0] - preference[manPartner[uu]][uu][1]);
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
							
						//	System.out.printf(" \n\nSINIRLAMALI WOMEN OP result: %s \n\n",result);
							//sadece en sonuncusunu toplamasý lazým hepsini topluyor
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[0][0]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[0][0]+ (double) (worstcase1) ;
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[1][0]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[1][0]+(double) (worstcase2) ;
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[0][1]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[0][1]+ (double) (bestcase1) ;
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[1][1]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[1][1]+(double) (bestcase2 );
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[0][2]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[0][2]+(double) (aritmetik_ortalama1 );
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[1][2]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[1][2]+(double) (aritmetik_ortalama2) ;
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[0][3]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[0][3]+ (double)(standart_sapma1) ;
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[1][3]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[1][3]+ (double) (standart_sapma2 );
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[0][4]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[0][4]+ (double) (fark_ortalama) ;
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[1][4]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[1][4]+ (double)(sum_of_difference) ;
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[0][5]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[0][5]+ (double) (egalitarean_sum) ;
							
						for(int uu =0; uu< 2; uu++)
							for(int yy=0;yy<Preference_Matrix.sonuc_matis_boy ; yy++)
								Preference_Matrix.sonuc_sinirlamali_women_op [uu][yy] = Preference_Matrix.sonuc_ortalamalari_sýnýrlamalý_women_op[uu][yy]/click_number;

						
						System.out.printf(" \n\nSINIRLAMALI WOMEN OP  Sonuc: \n\n");
						for(int uu = 0; uu< Preference_Matrix.sonuc_matis_boy; uu++)
						{
							System.out.printf("( %.2f, %.2f)\n", Preference_Matrix.sonuc_sinirlamali_women_op [0][uu],Preference_Matrix.sonuc_sinirlamali_women_op [1][uu]);
						}
							
							
							
				  }		 
			  }
			  
			  if(flag_cins == 6)
			  {  
				  if(son_flag==1)
				  {
					  System.out.printf("\n\nSýnýrlamasýz women optimal Sonuç: \n\n",result);
			     System.out.printf("\n\n%s\n\n",result);
				  double worstcase1 = 0;
					double bestcase1= N;
					for(int uu =0; uu < N ;uu++)
					{
						if(worstcase1 < preference[manPartner[uu]][uu][1])
							worstcase1 = preference[manPartner[uu]][uu][1];
						if(bestcase1 > preference[manPartner[uu]][uu][1])
							bestcase1 = preference[manPartner[uu]][uu][1];			 
					}
					result 	= result.concat(String.format("ilk kolon, en iyi : %.1f , ",bestcase1));
					result 	= result.concat(String.format("en kötü : %.1f \n",worstcase1));
					
					double worstcase2 = 0;
					double bestcase2= N;
					for(int uu =0; uu < N ;uu++)
					{
						if(worstcase2 < preference[manPartner[uu]][uu][0])
							worstcase2 = preference[manPartner[uu]][uu][0];
						if(bestcase2 > preference[manPartner[uu]][uu][0])
							bestcase2 = preference[manPartner[uu]][uu][0];			 
					}
					result 	= result.concat(String.format("en iyi : %.1f , ",bestcase2));
					result 	= result.concat(String.format("en kötü : %.1f \n",worstcase2));
					
				  
										
						double  aritmetik_ortalama1=0;
						for(int uu=0;uu<N;uu++)
							aritmetik_ortalama1 = aritmetik_ortalama1 + preference[manPartner[uu]][uu][1];
						aritmetik_ortalama1= aritmetik_ortalama1 /N;	
						 result 	= result.concat(String.format("aritmetik ortalama: ( %.2f , ",aritmetik_ortalama1));				 
										
						
							double aritmetik_ortalama2=0;
							for(int uu=0;uu<N;uu++)
								aritmetik_ortalama2 = aritmetik_ortalama2 + preference[manPartner[uu]][uu][0];
							aritmetik_ortalama2 = aritmetik_ortalama2 /N;
							result 	= result.concat(String.format(" %.2f ) \n",aritmetik_ortalama2));
							
							 double  standart_sapma1=0;
								for(int uu=0;uu<N;uu++)
									standart_sapma1 = standart_sapma1 + (preference[manPartner[uu]][uu][1] - aritmetik_ortalama1) * (preference[manPartner[uu]][uu][1] - aritmetik_ortalama1);
								standart_sapma1 = standart_sapma1 /N;
								standart_sapma1 = Math.sqrt(standart_sapma1);
								result 	= result.concat(String.format(" standart sapma: ( %.2f , ",standart_sapma1));
								
						
							double standart_sapma2=0;
							for(int uu=0;uu<N;uu++)
								standart_sapma2 = standart_sapma2 + (preference[manPartner[uu]][uu][0] - aritmetik_ortalama2) * (preference[manPartner[uu]][uu][0] - aritmetik_ortalama2);
							standart_sapma2 = standart_sapma2 /N;
							standart_sapma2 = Math.sqrt(standart_sapma2);
							result 	= result.concat(String.format(" %.2f )\n ",standart_sapma2));
									
							double fark_ortalama=0;
							for(int uu =0; uu< N ;uu++)
								fark_ortalama= fark_ortalama+ Math.abs(preference[manPartner[uu]][uu][0] - preference[manPartner[uu]][uu][1]);
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

						//	System.out.printf(" \n\nSINIRLAMASIZ WOMEN OP result: %s \n\n",result);
							//sadece en sonuncusunu toplamasý lazým hepsini topluyor
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [0][0]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [0][0]+ (double) (worstcase1) ;
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [1][0]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [1][0]+(double) (worstcase2) ;
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [0][1]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [0][1]+ (double) (bestcase1) ;
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [1][1]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [1][1]+(double) (bestcase2 );
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [0][2]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [0][2]+(double) (aritmetik_ortalama1 );
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [1][2]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [1][2]+(double) (aritmetik_ortalama2) ;
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [0][3]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [0][3]+ (double)(standart_sapma1) ;
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [1][3]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [1][3]+ (double) (standart_sapma2 );
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [0][4]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [0][4]+ (double) (fark_ortalama) ;
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [1][4]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [1][4]+ (double)(sum_of_difference) ;
							Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [0][5]=Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [0][5]+ (double) (egalitarean_sum) ;
							
						for(int uu =0; uu< 2; uu++)
							for(int yy=0;yy<Preference_Matrix.sonuc_matis_boy ; yy++)
								Preference_Matrix.sonuc_sinirlamasiz_women_op [uu][yy] = Preference_Matrix.sonuc_ortalamalari_sýnýrlamasiz_women_op [uu][yy]/click_number;

						
						System.out.printf(" \n\nSINIRLAMASIZ  WOMEN OP Sonuc: \n\n");
						for(int uu = 0; uu< Preference_Matrix.sonuc_matis_boy; uu++)
						{
							System.out.printf("( %.2f, %.2f)\n", Preference_Matrix.sonuc_sinirlamasiz_women_op [0][uu],Preference_Matrix.sonuc_sinirlamasiz_women_op [1][uu]);
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
	
	public boolean control(int  ara_sonuc[][])
	{
		boolean is_contain =false;
	 
		int compare[][] = new int[N][4]; 
		
	    ListIterator<Object> itr = linkedlist_woman.listIterator();
	    
	    while(itr.hasNext())
	    {
	    	
	    	compare =	(int[][]) itr.next();
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
			 
	    }
	    
	    
	 
		
			 
	 
		return is_contain;
		 
	}
	
	public void quick_sort(int[][]  a,int first,int last)
	{
		int j,i,pivot;
		int temp, temp_a, temp_b, temp_c;
		if(first<last){
			pivot=first;
			i=first;
			j=last;

			while(i<j){
				while(a[i][1] <= a[pivot][1] && i<last)
					i++;
				while(a[j][1]>a[pivot][1])
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
					
					temp_c=a[i][3];
					a[i][3]=a[j][3];
					a[j][3]=temp_c;



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

			temp_c=a[pivot][3];
			a[pivot][3]=a[j][3];
			a[j][3]=temp_c;

			quick_sort(a,first,j-1);
			quick_sort(a,j+1,last);

		}
		
	 
	}

}
 
