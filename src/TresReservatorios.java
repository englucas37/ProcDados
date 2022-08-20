import java.util.*;

public class TresReservatorios {

	public static void main(String[] args) {

		final float gravidade = 9.81F;
		Scanner sc = new Scanner(System.in);
		double z1, z2, z3, Q1B, Q2B, Q3B, L1, L2, L3, f1, f2, f3, D1, D2, D3, deltaH1B, zB;
		System.out.println("Aplicação 05: Problema dos três reservatórios!");
		System.out.println();
		System.out.print("Informe a cota do reservatório 1 em m: ");
		z1 = sc.nextDouble();
		System.out.print("Informe a cota do reservatório 2 em m: ");
		z2 = sc.nextDouble();
		System.out.print("Informe a cota do reservatório 3 em m: ");
		z3 = sc.nextDouble();
		System.out.print("Informe o comprimento do trecho 1-B em m: ");
		L1 = sc.nextDouble();
		System.out.print("Informe o comprimento do trecho 2-B em m: ");
		L2 = sc.nextDouble();
		System.out.print("Informe o comprimento do trecho 3-B em m: ");
		L3 = sc.nextDouble();
		System.out.print("Informe o fator de atrito do trecho 1-B: ");
		f1 = sc.nextDouble();
		System.out.print("Informe o fator de atrito do trecho 2-B: ");
		f2 = sc.nextDouble();
		System.out.print("Informe o fator de atrito do trecho 3-B: ");
		f3 = sc.nextDouble();
		System.out.print("Informe o diâmetro do trecho 1-B em mm: ");
		D1 = sc.nextDouble();
		System.out.print("Informe o diâmetro do trecho 2-B em mm: ");
		D2 = sc.nextDouble();
		System.out.print("Informe o diâmetro do trecho 3-B em mm: ");
		D3 = sc.nextDouble();
		Q1B = Math.pow(((z1 - z2) * Math.pow(Math.PI, 2) * gravidade * Math.pow(D1 / 1000, 5)) / (8 * f1 * L1), 0.5);
		Q3B = Math.pow(((z2 - z3) * Math.pow(Math.PI, 2) * gravidade * Math.pow(D3 / 1000, 5)) / (8 * f3 * L3), 0.5);
		if (Q1B == Q3B) {
			System.out.println("O reservatório 2 não abastece e nem é abastecido!");
			System.out.printf("A vazão no trecho 1-B vale: %.4f%n", Q1B);
			System.out.printf("A vazão no trecho 2-B vale: 0%n");
			System.out.printf("A vazão no trecho 3-B vale: %.4f%n", Q3B);
			deltaH1B = (8 * f1 * L1 * Math.pow(Q1B, 2)) / (Math.pow(Math.PI, 2) * gravidade * Math.pow((D1 / 1000), 5));
			zB = z1 - deltaH1B;
			System.out.printf("A cota do ponto B vale: %.2f%n", zB);
		} else if (Q3B > Q1B) {
			Q1B = 0;
			Q2B = 0;
			Q3B = 0;
			zB = z3;
			do {
				zB = zB + 0.00001;
				Q1B = Math.pow(
						gravidade * Math.pow(Math.PI, 2) * Math.pow(D1 / 1000, 5) * Math.abs(zB - z1) / (8 * f1 * L1),
						0.5);
				Q2B = Math.pow(
						gravidade * Math.pow(Math.PI, 2) * Math.pow(D2 / 1000, 5) * Math.abs(zB - z2) / (8 * f2 * L2),
						0.5);
				Q3B = Math.pow(
						gravidade * Math.pow(Math.PI, 2) * Math.pow(D3 / 1000, 5) * Math.abs(zB - z3) / (8 * f3 * L3),
						0.5);
			} while (Q3B <= Q1B + Q2B);
			System.out.println("O reservatório 2 abastece!");
			System.out.printf("A vazão no trecho 1-B vale: %.5f m³/s%n", Q1B);
			System.out.printf("A vazão no trecho 2-B vale: %.5f m³/s%n", Q2B);
			System.out.printf("A vazão no trecho 3-B vale: %.5f m³/s%n", Q3B);
			System.out.printf("A cota do ponto B vale: %.2f m%n", zB);
		} else if (Q1B > Q3B) {
			Q1B = 0;
			Q2B = 0;
			Q3B = 0;
			zB = z2;
			do {
				zB = zB + 0.00001;
				Q1B = Math.pow(
						gravidade * Math.pow(Math.PI, 2) * Math.pow(D1 / 1000, 5) * Math.abs(zB - z1) / (8 * f1 * L1),
						0.5);
				Q2B = Math.pow(
						gravidade * Math.pow(Math.PI, 2) * Math.pow(D2 / 1000, 5) * Math.abs(zB - z2) / (8 * f2 * L2),
						0.5);
				Q3B = Math.pow(
						gravidade * Math.pow(Math.PI, 2) * Math.pow(D3 / 1000, 5) * Math.abs(zB - z3) / (8 * f3 * L3),
						0.5);
			} while (Q1B >= Q2B + Q3B);
			System.out.println("O reservatório 2 é abastecido!");
			System.out.printf("A vazão no trecho 1-B vale: %.5f m³/s%n", Q1B);
			System.out.printf("A vazão no trecho 2-B vale: %.5f m³/s%n", Q2B);
			System.out.printf("A vazão no trecho 3-B vale: %.5f m³/s%n", Q3B);
			System.out.printf("A cota do ponto B vale: %.2f m%n", zB);
		}
		sc.close();
	}
}
