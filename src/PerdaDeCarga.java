import java.util.*;

public class PerdaDeCarga {

	public static void main(String[] args) {
		final float gravidade = 9.81F;
		double vazao, diametro, rugosidade, comprimento, viscosidade, reynolds, f, perdaDeCarga, temperatura, finicial,
				erro, vazaoInicial;
		int codigo;
		Scanner sc = new Scanner(System.in);
		System.out.println("Aplicação 04: Cálculo da perda de carga, do diâmetro e da vazão!");
		System.out.println();
		System.out.println("O que você quer calcular?");
		System.out.println("Digite 1 para perda de carga");
		System.out.println("Digite 2 para diâmetro");
		System.out.println("Digite 3 para vazão");
		codigo = sc.nextInt();
		if (codigo == 1) {
			System.out.print("Informe a vazão em m³/s: ");
			vazao = sc.nextDouble();
			System.out.print("Informe o diâmetro em mm: ");
			diametro = sc.nextDouble();
			System.out.print("Informe a rugosidade em mm (de acordo com o tipo de tubo): ");
			rugosidade = sc.nextDouble();
			System.out.print("Informe o comprimento em m: ");
			comprimento = sc.nextDouble();
			System.out.print("Informe a temperatura do fluido em °C: ");
			temperatura = sc.nextDouble();
			viscosidade = (gravidade * (0.000181 / (1 + 0.03368 * temperatura + 0.000221 * Math.pow(temperatura, 2))))
					/ 1000;
			reynolds = (4 * vazao) / (Math.PI * (diametro / 1000) * viscosidade);
			f = Math.pow((Math.pow(64 / reynolds, 8) + 9.5 * Math.pow(Math.log(
					rugosidade / (3.7 * diametro) + 5.74 / (Math.pow(reynolds, 0.9)) - Math.pow(2500 / reynolds, 6)),
					-16)), 0.125);
			perdaDeCarga = (8 * f * comprimento * Math.pow(vazao, 2))
					/ ((Math.pow(Math.PI, 2) * gravidade * Math.pow(diametro / 1000, 5)));
			System.out.printf("A perda de carga na tubulação vale: %.2f m%n ", perdaDeCarga);
		} else if (codigo == 2) {
			System.out.print("Informe a vazão em m³/s: ");
			vazao = sc.nextDouble();
			System.out.print("Informe a perda de carga em m: ");
			perdaDeCarga = sc.nextDouble();
			System.out.print("Informe a rugosidade em mm (de acordo com o tipo de tubo): ");
			rugosidade = sc.nextDouble();
			System.out.print("Informe o comprimento em m: ");
			comprimento = sc.nextDouble();
			System.out.print("Informe a temperatura do fluido em °C: ");
			temperatura = sc.nextDouble();
			viscosidade = (gravidade * (0.000181 / (1 + 0.03368 * temperatura + 0.000221 * Math.pow(temperatura, 2))))
					/ 1000;
			finicial = 0.02;
			f = 0;
			do {
				diametro = 1000 * Math.pow((8 * finicial * comprimento * Math.pow(vazao, 2)
						/ (Math.pow(Math.PI, 2) * gravidade * perdaDeCarga)), 0.2);
				reynolds = (4 * vazao) / (Math.PI * (diametro / 1000) * viscosidade);
				f = Math.pow((Math.pow(64 / reynolds, 8) + 9.5 * Math.pow(Math.log(rugosidade / (3.7 * diametro)
						+ 5.74 / (Math.pow(reynolds, 0.9)) - Math.pow(2500 / reynolds, 6)), -16)), 0.125);
				diametro = 1000 * Math.pow(
						(8 * f * comprimento * Math.pow(vazao, 2) / (Math.pow(Math.PI, 2) * gravidade * perdaDeCarga)),
						0.2);
				f = Math.pow((Math.pow(64 / reynolds, 8) + 9.5 * Math.pow(Math.log(rugosidade / (3.7 * diametro)
						+ 5.74 / (Math.pow(reynolds, 0.9)) - Math.pow(2500 / reynolds, 6)), -16)), 0.125);
				erro = Math.abs(finicial - f);
				finicial = f;
			} while (erro >= 0.001);
			System.out.printf("O diâmetro vale: %.2f mm%n", diametro);
		} else if (codigo == 3) {
			System.out.print("Informe o diâmetro em mm: ");
			diametro = sc.nextDouble();
			System.out.print("Informe a perda de carga em m: ");
			perdaDeCarga = sc.nextDouble();
			System.out.print("Informe a rugosidade em mm (de acordo com o tipo de tubo): ");
			rugosidade = sc.nextDouble();
			System.out.print("Informe o comprimento em m: ");
			comprimento = sc.nextDouble();
			System.out.print("Informe a temperatura do fluido em °C: ");
			temperatura = sc.nextDouble();
			viscosidade = (gravidade * (0.000181 / (1 + 0.03368 * temperatura + 0.000221 * Math.pow(temperatura, 2))))
					/ 1000;
			vazaoInicial = 1;
			finicial = 0.01;
			do {
				reynolds = (4 * vazaoInicial) / (Math.PI * (diametro / 1000) * viscosidade);
				f = Math.pow((Math.pow(64 / reynolds, 8) + 9.5 * Math.pow(Math.log(rugosidade / (3.7 * diametro)
						+ 5.74 / (Math.pow(reynolds, 0.9)) - Math.pow(2500 / reynolds, 6)), -16)), 0.125);
				vazao = Math.pow((perdaDeCarga * Math.pow(Math.PI, 2) * gravidade * Math.pow(diametro / 1000, 5))
						/ (8 * f * comprimento), 0.5);
				reynolds = (4 * vazao) / (Math.PI * (diametro / 1000) * viscosidade);
				f = Math.pow((Math.pow(64 / reynolds, 8) + 9.5 * Math.pow(Math.log(rugosidade / (3.7 * diametro)
						+ 5.74 / (Math.pow(reynolds, 0.9)) - Math.pow(2500 / reynolds, 6)), -16)), 0.125);
				vazao = Math.pow((perdaDeCarga * Math.pow(Math.PI, 2) * gravidade * Math.pow(diametro / 1000, 5))
						/ (8 * f * comprimento), 0.5);
				erro = Math.abs(vazao - vazaoInicial);
				vazaoInicial = vazao;
			} while (erro <= 0.0000001);
			System.out.printf("A vazão vale: %.4f m³/s%n", vazao);
		}
		sc.close();

	}

}