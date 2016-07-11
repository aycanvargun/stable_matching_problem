package stable_marriage;

public class Unstability {
	public String blocking_pairs="";	
	  public int N ;
	  
	public Unstability( String blocking_pairs,int N)
	{
		this.blocking_pairs =   blocking_pairs;
		this.N = N;
	}
	int rankmen(int men, int women, int[][] MANprefer, int[][] WOMANprefer )
	{
		int i;
		for (i = 0; i < N && MANprefer[men][i] != women; i++);
		return i;
	}

	int rankwomen(int women, int men,  int[][] MANprefer, int[][] WOMANprefer )
	{
		int i;
		for (i = 0; i < N && WOMANprefer[women][i] != men; i++);
		return i;
	}

	int covet(int man1, int wife2, int[] manPartner, int[] wPartner, int[][] MANprefer, int[][] WOMANprefer)
	{ 
		if (rankmen(man1, wife2 , MANprefer, WOMANprefer) < rankmen(man1, manPartner[man1], MANprefer, WOMANprefer) &&
			rankwomen(wife2, man1, MANprefer, WOMANprefer) < rankwomen(wife2, wPartner[wife2], MANprefer, WOMANprefer)) {
				{
					System.out.printf("%d-%d cifti olusur, %d-%d ve %d-%d bozulur\n",man1, wife2, man1,manPartner[man1], wPartner[wife2], wife2 );
					blocking_pairs = blocking_pairs.concat(String.format("%d-%d cifti olusur, %d-%d ve %d-%d bozulur\n",man1, wife2, man1,manPartner[man1], wPartner[wife2], wife2 ));
					
					return 1;

				}

		}else
			return 0;
	}

	public int thy_neighbors_wife(int man1, int man2, int[] manPartner, int[] wPartner, int[][] MANprefer, int[][] WOMANprefer )
	{	 
		int result;
		result = covet(man1, manPartner[man2],manPartner,wPartner, MANprefer, WOMANprefer ) + covet(man2, manPartner[man1],manPartner,wPartner, MANprefer, WOMANprefer );
		return result;
	}

	boolean unstable(int[] manPartner, int[] wPartner,int[][] MANprefer, int[][] WOMANprefer)
	{
		int i, j; 
		boolean bad = false;
		boolean bos_var = false;
		for(i=0; i< N; i++)
		{
			if(manPartner[i]<0 || wPartner[i] < 0 )
				bos_var = true;
		}
		
		if(bos_var == false)
		{
			for (i = 0; i < N; i++) {
			for (j = i + 1; j < N; j++)
				if (thy_neighbors_wife(i, j,manPartner,wPartner, MANprefer, WOMANprefer) == 1) bad = true;
		}
		}
		else
		{
			bad = true;
		}
		
		return bad;
	}
}
