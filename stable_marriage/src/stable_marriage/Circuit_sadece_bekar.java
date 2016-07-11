package stable_marriage;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Circuit_sadece_bekar{

	public int N;
	public int[][][] preference = new int[N][N][2];
	public int prefere[][] = new int[2 * N][N];
	public String blocking_pairs = "";

	public Circuit_sadece_bekar(int[][][] preference, int prefere[][], int N, String blocking_pairs) {
		this.preference = preference;
		this.prefere = prefere;
		this.N = N;
		this.blocking_pairs = blocking_pairs;

	}

	public int[] prepare_tercih_array(  )
	{
		int i, j, k, temp = 0;
		int[] Array_teklif_sirasi = new int[2 * N * N];
		Random generator = new Random();
		for (i = 0; i < N; i++) 
		{
			for (j = 0; j < 2 * N; j++) 
			{
				while (true) 
				{
					temp = generator.nextInt(2*N) + 1;
					for (k = 0; k < 2 * N; k++) 
					{
						if (Array_teklif_sirasi[i * 2*N + k] == temp)
							break;
					}
					if (k == 2 * N) {
						break;
					}
				}
				Array_teklif_sirasi[i * N*2 + j] = temp;

			}
		}
				for(i=0; i< 2*N*N; i++)
		{
			Array_teklif_sirasi[i]=Array_teklif_sirasi[i]-1;
		}
		/*
				Array_teklif_sirasi=new int[]{
				5, 3, 4, 2, 0, 1 ,   2, 3, 5, 1 ,0 ,4   , 5 ,4 ,0, 1, 3, 2
				  };
				*/
		/*
	 Array_teklif_sirasi=new int[]{
			 16,6,14,0,9,10,12,3,7,13,2,8,11,5,4,15,1,17,
			 11,16,13,8,14,1,12,9,2,15,17,4,3,5,0,10,7,6,
			 4,8,2,14,3,13,16,12,7,1,17,9,15,0,11,10,5,6,
			 15,0,9,3,7,14,17,16,1,12,13,11,5,6,4,8,2,10,
			 5,10,16,12,13,1,17,2,4,15,9,3,7,11,0,8,14,6,
			 3,8,7,5,13,12,6,11,0,10,15,17,4,9,1,14,2,16,
			 15,9,0,8,17,4,12,5,3,14,7,11,16,2,13,10,6,1,
			 6,0,5,9,17,15,7,4,10,3,2,13,8,16,14,1,11,12,
			 14,8,17,16,4,10,6,3,2,5,12,11,15,13,9,7,1,0,
			  };
	 */
	  System.out.println();
		for(i=0;i< 2 * N * N;i++)
			System.out.printf("%d ",Array_teklif_sirasi[i]);
		System.out.println();
	  
	  return Array_teklif_sirasi;
	}
	
	public int[][] hazirla_prefere(  )
	{
	int i,j;
 int prefere_temp[][] = new int[2 * N][N];
		 
		 
		 for(i=0;i< 2*N; i++)
		 {
			 for(j=0; j< N; j ++)
			 {
				
						 if(i<N)
						 {
							 prefere_temp[i][j]=  prefere[i][j]+N;
						 }
						 else
						 {
							 prefere_temp[i][j]=  prefere[i][j];
						 }
			 }
		 }
		
		return prefere_temp;
	}
	
	public void linklist_incele(LinkedList<int[]> linkedlist,int[] bekarlar ,int[] evliler, int prefere_temp[][] ,int[] Array_teklif_sirasi )
	{   int flag =0; int x_i,x,y,y_es,py_es,pyx,x_es,px_es;
	int[] teklif = new int[2]; 
		ListIterator<int[]> listIterator = linkedlist.listIterator();
		
		while(listIterator.hasNext())
		{   flag=0; //eğer teklif yapılırsa oteklifi linklistten sileceğiz
		    teklif = new int[2];
		    teklif = listIterator.next();
		    x=teklif[0];
		    x_i= teklif[1]; //x'in etmediği teklifin sırası
		    if(bekarlar[x] == -1)//eğer x boşsa
		    {
		    	flag=1;
		    	y=prefere_temp[x][x_i]; //x'in x_i. sıradaki tercihi
		    	if(bekarlar[y] == -1) //eğer y boşsa 
		    	{
		    		//xy eş olur
		    		evliler[x]=y;
					evliler[y]=x;
					bekarlar[x]=1;
					bekarlar[y]=1;
		    	}
		    	else
		    	{
		    		y_es= evliler[y]; //y'nin eşi
		    		if(y<N) //y erkek ise 
					{
						py_es=preference[y][evliler[y]-N][0];
						pyx= preference[y][x-N][0];
					}
					else //y kadın ise
					{
						py_es=preference[evliler[y]][y-N][1];
						pyx= preference[x][y-N][1];
					}
		    		if(py_es>pyx)
		    		{
		    			//xy eş olur, y'nin eşi boşta kalır
		    			evliler[y_es]=-1;
						bekarlar[y_es]=-1;
						evliler[x]=y;
						evliler[y]=x;
						bekarlar[x]=1;
						bekarlar[y]=1;
		    		}
		    		
		    	}
		    	
		    }
		    /*
		    else if(bekarlar[x]!=-1 && evliler[x] != prefere_temp[x][x_i])
		    {
		    	x_es= evliler[x];
		    	 if(x<N) //x erkek ise 
					{
						px_es=preference[x][evliler[x]-N][0];
						 
					}
				 else //x kadın ise
					{
						px_es=preference[evliler[x]][x-N][1];								 
					}
		    	 if(px_es>x_i)
		    	 {
		    		 flag=1;
		    		 y= prefere_temp[x][x_i];
		    		 
		    		 if(bekarlar[y] == -1)
		    		 {
		    			//xy eş olur, x'in eşi boşta kalır 
		    			    evliler[x_es]=-1;
							bekarlar[x_es]=-1;
				    		evliler[x]=y;
							evliler[y]=x;
							bekarlar[x]=1;
							bekarlar[y]=1;
		    		 }
		    		 else
		    		 {
		    			 y_es=evliler[y];
						 if(y<N) //y erkek ise 
							{
								py_es=preference[y][evliler[y]-N][0];
								pyx= preference[y][x-N][0];
							}
							else //y kadın ise
							{
								py_es=preference[evliler[y]][y-N][1];
								pyx= preference[x][y-N][1];
							}
						 if(py_es > pyx)
						 {
							    evliler[y_es]=-1;
							    bekarlar[y_es]=-1;
							    evliler[x_es]=-1;
								bekarlar[x_es]=-1;
								evliler[x]=y;
								evliler[y]=x;
								bekarlar[x]=1;
								bekarlar[y]=1;
						 }
		    			 
		    		 }
		    	 }
		    		 
		    	
		    	
		    }
		    */
		    if(flag ==1 )
		    {
		    	listIterator.remove();
		    }
		    flag=0;
		    
		}
	}
	
	boolean is_stable(int[] evliler,int prefere_temp[][]){
		int wPartner[] = new int [N];
		int manPartner[] = new int [N];
		int MANprefer[][]= new int [N][N];
		int WOMANprefer[][]= new int [N][N];
		int i,j;
		int flag =0;
		boolean result;
		
		for(i =0; i< 2*N;i++)
		{
			if(evliler[i] == -1)
			{
				flag =1;
				break;
			}
				
		}
		
if(flag ==0)
			
		{
		for(i=0; i< 2*N; i++)
		{
			if(i< N) 
			manPartner[i]= evliler[i]-N;
			else
			{
				wPartner[i-N]=evliler[i];
			}
			
		}
		
		for(i=0; i< 2*N;i++)
		{
			for(j=0; j<N; j++)
			{
				
				if(i< N )
				{
					MANprefer[i][j]=prefere_temp[i][j];
				}
				else
				{
					WOMANprefer[i-N][j]=prefere_temp[i][j];
					
				}		
				

			}

		}
		 Unstability u = new Unstability (blocking_pairs,N);
		 result=u.unstable(manPartner,wPartner, MANprefer, WOMANprefer);
		}
else
{
	result = true;
}
		 return result;
	}
	
	public void Circuled_algorithm() {
		int i, j, k, temp = 0; boolean result;
		int x,y,y_es,py_es,pyx,x_es,px_es;
		int[] Array_teklif_sirasi = new int[2 * N * N];
		int[] bekarlar = new int[2*N];
		int[] evliler = new int[2*N];
		int prefere_temp[][] = new int[2 * N][N];
		int[] teklif = new int[2]; //link liste atılacak teklifler objesi
		LinkedList<int[]> linkedlist = new LinkedList<int[]>();
		Array_teklif_sirasi=prepare_tercih_array();
		prefere_temp=hazirla_prefere();
		
		for(i=0;i< 2*N; i++)
		{
			evliler[i]=-1;
			bekarlar[i]=-1;
		}
		
		for(i=0;i< N; i++)
		{
			for(j=0;j< 2*N;j++)
			{
				x= Array_teklif_sirasi[i*N*2+j];
				if(bekarlar[x]==-1) //eğer x boştaysa
				{
					y= prefere_temp[x][i]; //x in i.sıradaki tercihi
					if(bekarlar[y] ==-1)
					{ //xy eş olur
						evliler[x]=y;
						evliler[y]=x;
						bekarlar[x]=1;
						bekarlar[y]=1;
					}
					else
					{
						y_es=evliler[y];
						if(y<N) //y erkek ise 
						{
							py_es=preference[y][evliler[y]-N][0];
							pyx= preference[y][x-N][0];
						}
						else //y kadın ise
						{
							py_es=preference[evliler[y]][y-N][1];
							pyx= preference[x][y-N][1];
						}
						if(py_es>pyx)
						{
							evliler[y_es]=-1;
							bekarlar[y_es]=-1;
							evliler[x]=y;
							evliler[y]=x;
							bekarlar[x]=1;
							bekarlar[y]=1;
							
						}
					}
				}
				else if(bekarlar[x]!=-1 && evliler[x] != prefere_temp[x][i]) //x evliyse ve şu anki eşi, x'in i.tercihi değilse
				{
						 x_es= evliler[x];
						  
						 if(x<N) //x erkek ise 
							{
								px_es=preference[x][evliler[x]-N][0];
								 
							}
						 else //x kadın ise
							{
								px_es=preference[evliler[x]][x-N][1];								 
							}	
						 
						 if(i>px_es)
						 {
							 //bu kişiyi ve teklifi link liste eklicez
							 teklif = new int[2]; 
							 teklif[0]=x;
							 teklif[1] = i;
							 linkedlist.add(teklif);
							 
						 }
						 else
						 {
							 y=  prefere_temp[x][i];
							 if(bekarlar[y] == -1) //eğer y boştaysa 
							 {
								    evliler[x_es]=-1;
									bekarlar[x_es]=-1;
									evliler[x]=y;
									evliler[y]=x;
									bekarlar[x]=1;
									bekarlar[y]=1;
							 }
							 else
							 {
								 y_es=evliler[y];
								 if(y<N) //y erkek ise 
									{
										py_es=preference[y][evliler[y]-N][0];
										pyx= preference[y][x-N][0];
									}
									else //y kadın ise
									{
										py_es=preference[evliler[y]][y-N][1];
										pyx= preference[x][y-N][1];
									}
								 if(py_es>pyx)
								 {      evliler[y_es]=-1;
									    bekarlar[y_es]=-1;
									    evliler[x_es]=-1;
										bekarlar[x_es]=-1;
										evliler[x]=y;
										evliler[y]=x;
										bekarlar[x]=1;
										bekarlar[y]=1;
								 }
							 }
						 }
						 
				}
			}//ilk for bitti
			
			linklist_incele(linkedlist,bekarlar,evliler,prefere_temp,Array_teklif_sirasi);	
			  
			
			
		}
		
		
		try {
		for(i=0;i< N; i++)
		System.out.printf("\nman: %d  woman: %d tercihleri: ( %d , %d )\n",i+1,evliler[i]-N+1,preference[i][evliler[i]-N][0],preference[i][evliler[i]-N][1]);
		}
		catch(Exception e)
		{
			System.out.printf("\n :( :( :( henüz eşleşmemiş kişiler var\n");
			Preference_Matrix.numClicks_circuled_sadece_bekar --;		
		}
		finally
		{
			
				result=is_stable(evliler,prefere_temp);
		}
	
		if(result == false)
		{
			System.out.println("\n\nCirculed_sadece_bekar Algortihm solution is stable. :)))))))))\n\n");
			
			
			///tekrarlı oynama için hesaplar
			
			double worstcase1 = 0;
			double bestcase1= N;
			for(int uu =0; uu < N ;uu++)
			{
				if(worstcase1 < preference[uu][evliler[uu]-N][0])
					worstcase1 = preference[uu][evliler[uu]-N][0];
				if(bestcase1 > preference[uu][evliler[uu]-N][0])
					bestcase1 = preference[uu][evliler[uu]-N][0];			 
			}
			
		
			
			String result_st="";
			result_st 	= result_st.concat(String.format("ilk kolon, en iyi : %.1f , ",bestcase1));
			result_st 	= result_st.concat(String.format("en kötü : %.1f \n",worstcase1));
			
			double worstcase2 = 0;
			double bestcase2= N;
			for(int uu =0; uu < N ;uu++)
			{
				if(worstcase2 < preference[uu][evliler[uu]-N][1])
					worstcase2 =  preference[uu][evliler[uu]-N][1];
				if(bestcase2 > preference[uu][evliler[uu]-N][1])
					bestcase2 = preference[uu][evliler[uu]-N][1];			 
			}
			result_st 	= result_st.concat(String.format("en iyi : %.1f , ",bestcase2));
			result_st 	= result_st.concat(String.format("en kötü : %.1f \n",worstcase2));
			
			
			double  aritmetik_ortalama1=0;
			
			for(int uu=0;uu<N;uu++)
				aritmetik_ortalama1 = aritmetik_ortalama1 + preference[uu][evliler[uu]-N][0];
			aritmetik_ortalama1= aritmetik_ortalama1 /N;	
			result_st 	= result_st.concat(String.format("aritmetik ortalama: ( %.2f , ",aritmetik_ortalama1));			
				
				
						
			double  aritmetik_ortalama2=0;		
		    for(int uu=0;uu<N;uu++)
				aritmetik_ortalama2 = aritmetik_ortalama2 + preference[uu][evliler[uu]-N][1];
			aritmetik_ortalama2= aritmetik_ortalama2 /N;
			result_st 	= result_st.concat(String.format(" %.2f ) \n",aritmetik_ortalama2));

			
			
			
			double  standart_sapma1=0;
			for(int uu=0;uu<N;uu++)
				standart_sapma1 = standart_sapma1 + (preference[uu][evliler[uu]-N][0] - aritmetik_ortalama1) * (preference[uu][evliler[uu]-N][0] - aritmetik_ortalama1);
			standart_sapma1 = standart_sapma1 /N;
			standart_sapma1 = Math.sqrt(standart_sapma1);
			result_st 	= result_st.concat(String.format(" standart sapma: ( %.2f , ",standart_sapma1));
			
			
			double standart_sapma2 =0;
			for(int uu=0;uu<N;uu++)
				standart_sapma2 = standart_sapma2 + (preference[uu][evliler[uu]-N][1] - aritmetik_ortalama2) * (preference[uu][evliler[uu]-N][1] - aritmetik_ortalama2);
			standart_sapma2 = standart_sapma2 /N;
			standart_sapma2 = Math.sqrt(standart_sapma2);
			result_st 	= result_st.concat(String.format(" %.2f )\n ",standart_sapma2));
			
			
			
			//farkların ortalaması
			double fark_ortalama=0;
			for(int uu =0; uu< N ;uu++)
				fark_ortalama= fark_ortalama+ Math.abs(preference[uu][evliler[uu]-N][0] - preference[uu][evliler[uu]-N][1]);
			fark_ortalama = fark_ortalama/N;
			result_st 	= result_st.concat(String.format(" fark ortalamasi:  %.2f \n ",fark_ortalama));
			
			
			
		 
			double sum_of_difference =0;
			 
			for(int ii = 0; ii< N ; ii++)
			{
				sum_of_difference= sum_of_difference + Math.abs(preference[ii][evliler[ii]-N][0] - preference[ii][evliler[ii]-N][1]);
		 
				
			}
	 
			result_st 	= result_st.concat(String.format(" SEM toplamı ortalamasi:  %.2f \n ",sum_of_difference));
			
			//circuled_sadece_bekar
			
			double circuled_sadece_bekar_sum =0;
			for(int ii = 0; ii< N ; ii ++)
			{
				circuled_sadece_bekar_sum= circuled_sadece_bekar_sum + Math.abs(preference[ii][evliler[ii]-N][0] + preference[ii][evliler[ii]-N][1]);
				 
				
			}
			
			Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [0][0]=Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [0][0]+ (double) (worstcase1) ;
			Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [1][0]=Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [1][0]+(double) (worstcase2) ;
			Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [0][1]=Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [0][1]+ (double) (bestcase1) ;
			Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [1][1]=Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [1][1]+(double) (bestcase2 );
			Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [0][2]=Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [0][2]+(double) (aritmetik_ortalama1 );
			Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [1][2]=Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [1][2]+(double) (aritmetik_ortalama2) ;
			Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [0][3]=Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [0][3]+ (double)(standart_sapma1) ;
			Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [1][3]=Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [1][3]+ (double) (standart_sapma2 );
			Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [0][4]=Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [0][4]+ (double) (fark_ortalama) ;
			Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [1][4]=Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [1][4]+ (double)(sum_of_difference) ;
			Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [0][5]=Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [0][5]+ (double) (circuled_sadece_bekar_sum) ;
			
		for(int uu =0; uu< 2; uu++)
			for(int yy=0;yy<Preference_Matrix.sonuc_matis_boy ; yy++)
				Preference_Matrix.sonuc_circuled_sadece_bekar   [uu][yy] = Preference_Matrix.sonuc_ortalamalari_circuled_sadece_bekar  [uu][yy]/ Preference_Matrix.numClicks_circuled_sadece_bekar ;

		System.out.printf(" \n\nCIRCULED_sadece_bekar Sonuc: \n\n");
		for(int uu = 0; uu< Preference_Matrix.sonuc_matis_boy; uu++)
		{
			System.out.printf("( %.2f, %.2f)\n", Preference_Matrix.sonuc_circuled_sadece_bekar   [0][uu],Preference_Matrix.sonuc_circuled_sadece_bekar [1][uu]);
		}

		 
		}
		else
		{
			System.out.println("\n\nCirculed_sadece_bekar solution is unstable.\n\n");
			
		}
			
	}

}
