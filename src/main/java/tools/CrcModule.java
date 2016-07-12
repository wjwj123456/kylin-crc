/**
* @author       lpt14
* @version      V1.0
*/
package tools;

import java.io.IOException;

/**
 * TODO: （类描述）
 *
 * @author lpt14
 * @since 2016年7月12日
 * @see
 */
public class CrcModule {
	int[][] matrix;
	int defectsNum = 0;
	double MtCh, MhCh;

	public static void main(String[] args) throws IOException {
		int[][] m = { { 0, 0, 1, 0, 1 }, { 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0 }, { 1, 0, 0, 1, 1 }, { 0, 1, 1, 1, 0 },
				{ 1, 0, 0, 0, 0 }, { 1, 1, 1, 1, 0 }, { 1, 1, 0, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 1, 0, 0, 0 },
				{ 0, 1, 0, 0, 0 }, { 1, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1 }, { 0, 0, 0, 1, 0 },
				{ 0, 0, 0, 0, 1 } };
		CrcModule cc = new CrcModule(m);

		double n = cc.getMhCH();
		System.out.println("HChao:" + n);

		n = cc.getMtCH();
		System.out.println("TChao:" + n);

		int n1 = cc.getDefectsNum();
		System.out.println("d:" + n1);
	}

	public CrcModule(int[][] matrix) {
		this.matrix = matrix;
		chao(matrix);
	}

	public void chao(int[][] matrix) {
		int reviewersNum = matrix[0].length;

		int[] f = new int[reviewersNum + 1];
		for (int i = 0; i <= reviewersNum; i++) {
			f[i] = 0;
		}
		int[] Z = new int[reviewersNum + 1];

		for (int i = 0; i < matrix.length; i++) {
			int c = 0;
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 1)
					c++;
			}
			f[c]++;
			if (c != 0)
				defectsNum++;

			if (c == 1) {
				for (int j = 0; j < reviewersNum; j++) {
					if (matrix[i][j] == 1)
						Z[j + 1]++;
				}
			}
		}

		HChao(defectsNum, f);
		TChao(defectsNum, reviewersNum, f, Z);
	}

	public void HChao(int d, int[] f) {
		MhCh = d + (double) f[1] * f[1] / (double) (2 * f[2]);
	}

	public void TChao(int d, int t, int[] f, int[] Z) {
		int z = 0;
		for (int i = 1; i <= t; i++) {
			z += (Z[i] * Z[i]);
		}
		MtCh = d + (double) (f[1] * f[1] - z) / (double) (2 * (f[2] + 1));
	}

	public double getMhCH() {
		return MhCh;
	}

	public double getMtCH() {
		return MtCh;
	}

	public int getDefectsNum() {
		return defectsNum;
	}
}
