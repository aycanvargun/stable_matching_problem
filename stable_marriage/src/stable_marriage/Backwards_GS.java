package stable_marriage;

public class Backwards_GS {
	public int N = 4;
	public int[][][] preference = new int[N][N][2];
	public int prefere[][] = new int[2 * N][N];

	public String blocking_pairs = "";

	public Backwards_GS(int[][][] preference, int prefere[][], int N, String blocking_pairs) {
		this.preference = preference;
		this.prefere = prefere;
		this.N = N;
		this.blocking_pairs = blocking_pairs;
	}
	
	public boolean bekar_kaldi_mi(int[] fiancee)
	{
		boolean sonuc= false;
		int i;
		for(i =0; i< N; i++)
		{
			if(fiancee[i]<0) //eþi 0'dan küçük birisi varsa hala bekar demektir, bekarlar vardýr
			{
				sonuc = true;
				break;
			}
		}
		return sonuc;
	}

	public int[] calis(int[] fiancee, int[][] prefere_temp) {
		
		
		////

while (bekar_kaldi_mi(fiancee)== true)
{

	//while(teklif_edecek_var_mi(fiancee,prefere_temp)==true) STABLE ÇIKMAZ
	{
		
	}
}
		////
		return fiancee;
	}

	public int[] solve() {

		int[] fiancee = new int[2 * N];

		int[] order = new int[2 * N];
		int[][] prefere_temp = new int[2 * N][N];
		for (int order_ = 0; order_ < 2 * N; order_++) {
			order[order_] = order_; // erkek optimal olacak bu sýrlama ile
			fiancee[order_] = -1;
		}

		for (int d = 0; d < 2 * N; d++) {

			for (int dd = 0; dd < N; dd++) {
				prefere_temp[d][dd] = prefere[d][dd];

			}

		}

		for (int d = 0; d < N; d++) {

			for (int dd = 0; dd < N; dd++) {
				prefere_temp[d][dd] = prefere_temp[d][dd] + N;

			}

		}

		////////////////

		fiancee = calis(fiancee, prefere_temp);

		//////////////

		/// STATITY KONTROLÜ ÝÇÝN

		/// stability kontrol için

		int MANprefer[][] = new int[N][N];
		int WOMANprefer[][] = new int[N][N];

		for (int i = 0; i < 2 * N; i++) {
			for (int j = 0; j < N; j++) {

				if (i < N) {
					MANprefer[i][j] = prefere[i][j];
				} else {
					WOMANprefer[i - N][j] = prefere[i][j];

				}

			}

		}

		int availableM[] = new int[N];
		int result[] = new int[N];

		for (int i = 0; i < 2 * N; i++) {
			if (i < N) {
				availableM[i] = fiancee[i] - N;
			} else {
				result[i - N] = fiancee[i];

			}

		}

		Unstability u = new Unstability(blocking_pairs, N);

		boolean unstability = u.unstable(availableM, result, MANprefer, WOMANprefer);

		if (unstability == false) {
			System.out.println("\n\nBACKWARDS GS Algortihm solution is stable. :)))))))))\n\n");

			for (int uu = 0; uu < N; uu++) {
				System.out.printf("man: %d   woman: %d tercihleri: ( %d , %d ) \n", uu + 1, fiancee[uu] + 1 - N,
						preference[uu][fiancee[uu] - N][0], preference[uu][fiancee[uu] - N][1]);
			}
			/// tekrarlý oynama için hesaplar

			double worstcase1 = 0;
			double bestcase1 = N;
			for (int uu = 0; uu < N; uu++) {
				if (worstcase1 < preference[uu][fiancee[uu] - N][0])
					worstcase1 = preference[uu][fiancee[uu] - N][0];
				if (bestcase1 > preference[uu][fiancee[uu] - N][0])
					bestcase1 = preference[uu][fiancee[uu] - N][0];
			}

			String result_st = "";
			result_st = result_st.concat(String.format("ilk kolon, en iyi : %.1f , ", bestcase1));
			result_st = result_st.concat(String.format("en kötü : %.1f \n", worstcase1));

			double worstcase2 = 0;
			double bestcase2 = N;
			for (int uu = 0; uu < N; uu++) {
				if (worstcase2 < preference[uu][fiancee[uu] - N][1])
					worstcase2 = preference[uu][fiancee[uu] - N][1];
				if (bestcase2 > preference[uu][fiancee[uu] - N][1])
					bestcase2 = preference[uu][fiancee[uu] - N][1];
			}
			result_st = result_st.concat(String.format("en iyi : %.1f , ", bestcase2));
			result_st = result_st.concat(String.format("en kötü : %.1f \n", worstcase2));

			double aritmetik_ortalama1 = 0;

			for (int uu = 0; uu < N; uu++)
				aritmetik_ortalama1 = aritmetik_ortalama1 + preference[uu][fiancee[uu] - N][0];
			aritmetik_ortalama1 = aritmetik_ortalama1 / N;
			result_st = result_st.concat(String.format("aritmetik ortalama: ( %.2f , ", aritmetik_ortalama1));

			double aritmetik_ortalama2 = 0;
			for (int uu = 0; uu < N; uu++)
				aritmetik_ortalama2 = aritmetik_ortalama2 + preference[uu][fiancee[uu] - N][1];
			aritmetik_ortalama2 = aritmetik_ortalama2 / N;
			result_st = result_st.concat(String.format(" %.2f ) \n", aritmetik_ortalama2));

			double standart_sapma1 = 0;
			for (int uu = 0; uu < N; uu++)
				standart_sapma1 = standart_sapma1 + (preference[uu][fiancee[uu] - N][0] - aritmetik_ortalama1)
						* (preference[uu][fiancee[uu] - N][0] - aritmetik_ortalama1);
			standart_sapma1 = standart_sapma1 / N;
			standart_sapma1 = Math.sqrt(standart_sapma1);
			result_st = result_st.concat(String.format(" standart sapma: ( %.2f , ", standart_sapma1));

			double standart_sapma2 = 0;
			for (int uu = 0; uu < N; uu++)
				standart_sapma2 = standart_sapma2 + (preference[uu][fiancee[uu] - N][1] - aritmetik_ortalama2)
						* (preference[uu][fiancee[uu] - N][1] - aritmetik_ortalama2);
			standart_sapma2 = standart_sapma2 / N;
			standart_sapma2 = Math.sqrt(standart_sapma2);
			result_st = result_st.concat(String.format(" %.2f )\n ", standart_sapma2));

			// farklarýn ortalamasý
			double fark_ortalama = 0;
			for (int uu = 0; uu < N; uu++)
				fark_ortalama = fark_ortalama
						+ Math.abs(preference[uu][fiancee[uu] - N][0] - preference[uu][fiancee[uu] - N][1]);
			fark_ortalama = fark_ortalama / N;
			result_st = result_st.concat(String.format(" fark ortalamasi:  %.2f \n ", fark_ortalama));

			// SEM
			double sum_of_difference = 0;

			for (int ii = 0; ii < N; ii++) {
				sum_of_difference = sum_of_difference
						+ Math.abs(preference[ii][fiancee[ii] - N][0] - preference[ii][fiancee[ii] - N][1]);

			}

			result_st = result_st.concat(String.format(" SEM toplamý ortalamasi:  %.2f \n ", sum_of_difference));

			// EGALITAREAN

			double BACKWARDS_GS_sum = 0;
			for (int ii = 0; ii < N; ii++) {
				BACKWARDS_GS_sum = BACKWARDS_GS_sum
						+ Math.abs(preference[ii][fiancee[ii] - N][0] + preference[ii][fiancee[ii] - N][1]);

			}

			Preference_Matrix.sonuc_ortalamalari_Backwards_GS[0][0] = Preference_Matrix.sonuc_ortalamalari_Backwards_GS[0][0]
					+ (double) (worstcase1);
			Preference_Matrix.sonuc_ortalamalari_Backwards_GS[1][0] = Preference_Matrix.sonuc_ortalamalari_Backwards_GS[1][0]
					+ (double) (worstcase2);
			Preference_Matrix.sonuc_ortalamalari_Backwards_GS[0][1] = Preference_Matrix.sonuc_ortalamalari_Backwards_GS[0][1]
					+ (double) (bestcase1);
			Preference_Matrix.sonuc_ortalamalari_Backwards_GS[1][1] = Preference_Matrix.sonuc_ortalamalari_Backwards_GS[1][1]
					+ (double) (bestcase2);
			Preference_Matrix.sonuc_ortalamalari_Backwards_GS[0][2] = Preference_Matrix.sonuc_ortalamalari_Backwards_GS[0][2]
					+ (double) (aritmetik_ortalama1);
			Preference_Matrix.sonuc_ortalamalari_Backwards_GS[1][2] = Preference_Matrix.sonuc_ortalamalari_Backwards_GS[1][2]
					+ (double) (aritmetik_ortalama2);
			Preference_Matrix.sonuc_ortalamalari_Backwards_GS[0][3] = Preference_Matrix.sonuc_ortalamalari_Backwards_GS[0][3]
					+ (double) (standart_sapma1);
			Preference_Matrix.sonuc_ortalamalari_Backwards_GS[1][3] = Preference_Matrix.sonuc_ortalamalari_Backwards_GS[1][3]
					+ (double) (standart_sapma2);
			Preference_Matrix.sonuc_ortalamalari_Backwards_GS[0][4] = Preference_Matrix.sonuc_ortalamalari_Backwards_GS[0][4]
					+ (double) (fark_ortalama);
			Preference_Matrix.sonuc_ortalamalari_Backwards_GS[1][4] = Preference_Matrix.sonuc_ortalamalari_Backwards_GS[1][4]
					+ (double) (sum_of_difference);
			Preference_Matrix.sonuc_ortalamalari_Backwards_GS[0][5] = Preference_Matrix.sonuc_ortalamalari_Backwards_GS[0][5]
					+ (double) (BACKWARDS_GS_sum);

			for (int uu = 0; uu < 2; uu++)
				for (int yy = 0; yy < Preference_Matrix.sonuc_matis_boy; yy++)
					Preference_Matrix.sonuc_Backwards_GS[uu][yy] = Preference_Matrix.sonuc_ortalamalari_Backwards_GS[uu][yy]
							/ Preference_Matrix.numClicks_Backwards_GS;

			System.out.printf(" \n\nBackwards GS Sonuc: \n\n");
			for (int uu = 0; uu < Preference_Matrix.sonuc_matis_boy; uu++) {
				System.out.printf("( %.2f, %.2f)\n", Preference_Matrix.sonuc_Backwards_GS[0][uu],
						Preference_Matrix.sonuc_Backwards_GS[1][uu]);
			}

		} else {
			System.out.println("\n\nBackwards GS solution is unstable.\n\n");

		}
		///////
		return fiancee;
	}

}
