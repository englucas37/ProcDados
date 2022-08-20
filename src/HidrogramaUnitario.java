import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class HidrogramaUnitario {

	public static void main(String[] args) {
		
		try {

			// Criação do leitor (Scanner)
			Scanner sc = new Scanner(System.in);

			// Criação do diretório

			File diretorio = new File("c:\\hidrologia");
			System.out.println("Aplicação 13: Hidrograma Unitário!");
			if (diretorio.exists()) {
				System.out.println("Diretório já existente");
			} else {
				System.out.println("Diretório inexistente");
				diretorio.mkdir();
				System.out.println("Diretório criado com sucesso");
			}

			// Criação dos escritores do arquivo

			FileWriter arq = new FileWriter("c:\\hidrologia\\HIDROGRAMA.txt");
			FileWriter arq2 = new FileWriter("c:\\hidrologia\\HIETOGRAMAS.txt");

			// Criação das variáveis de registro de vazão e precipitação

			int horasVazao, horasPrecipitacao;
			double vazao, precipitacao;

			// Número de horas a serem registradas no hidrograma

			System.out.println("Quantas horas de registro para o hidrograma?");
			horasVazao = sc.nextInt();

			// Gravação do título do arquivo .txt do hidrograma

			PrintWriter gravar = new PrintWriter(arq);
			gravar.println("Tempo (h); Vazão (m³/s)");

			// Gravação das vazões no hidrograma

			for (int i = 0; i < horasVazao; i++) {
				int horas1 = i + 1;
				System.out.println("Qual a vazão da " + (i + 1) + "ª hora?");
				vazao = sc.nextDouble();
				gravar.println(horas1 + ";" + vazao);
			}

			// Número de horas a serem registradas no hietograma

			System.out.println("Quantas horas de registro para o hietograma?");
			horasPrecipitacao = sc.nextInt();

			// Gravação do título do arquivo .txt do hietograma

			PrintWriter gravar2 = new PrintWriter(arq2);
			gravar2.println("Tempo (h); Precipitação (mm)");

			// Gravação das precipitações no hietograma

			for (int i = 0; i < horasPrecipitacao; i++) {
				int horas1 = i + 1;
				System.out.println("Qual a precipitação da " + (i + 1) + "ª hora?");
				precipitacao = sc.nextDouble();
				gravar2.println(horas1 + ";" + precipitacao);
			}

			sc.close();
			gravar.close();
			gravar2.close();

		} catch (Exception e) {
			System.out.println("Ocorreu um erro durante a leitura");
		}

		try {

			// Criação da estrutura de leitura do arquivo .txt do hidrograma

			Scanner in = new Scanner(new FileReader("C:/hidrologia/HIDROGRAMA.txt"));
			String line = in.nextLine();
			ArrayList<Double> vazoes = new ArrayList<>();
			line = in.nextLine();

			for (int i = 0; in.hasNextLine();) {
				String[] vazoesLinha = line.split(";");
				vazoes.add(Double.parseDouble(vazoesLinha[i + 1]));
				line = in.nextLine();
			}

			String[] vazoesLinha = line.split(";");
			vazoes.add(Double.parseDouble(vazoesLinha[1]));
			
			System.out.println("O hidrograma criado foi: " + vazoes);

			double somaVazoes = 0;

			// Somatório das vazões

			for (int i = 0; i < vazoes.size(); i++) {
				somaVazoes += vazoes.get(i);
			}

			// Cálculo da precipitação efetiva

			double areaBacia = 35;
			double precipitacaoEfetiva = somaVazoes * 3600 / (areaBacia * 1000);
			ArrayList<Double> HU = new ArrayList<>();

			// Cálculo do hidrograma unitário

			for (int i = 0; i < vazoes.size(); i++) {
				HU.add(vazoes.get(i) / precipitacaoEfetiva);
			}

			in.close();

			// Criação da estrutura de leitura do arquivo .txt do hietograma

			Scanner in2 = new Scanner(new FileReader("C:/hidrologia/HIETOGRAMAS.txt"));
			String line2 = in2.nextLine();
			ArrayList<Double> precipitacoes = new ArrayList<>();
			line2 = in2.nextLine();

			for (int i = 0; in2.hasNextLine();) {
				String[] precipitacoesLinha = line2.split(";");
				precipitacoes.add(Double.parseDouble(precipitacoesLinha[i + 1]));
				line2 = in2.nextLine();
			}

			String[] precipitacoesLinha = line2.split(";");
			precipitacoes.add(Double.parseDouble(precipitacoesLinha[1]));
			
			System.out.println("O hietograma criado foi: " + precipitacoes);

			in2.close();

			// Cálculo das vazões do hidrograma unitário

			ArrayList<Double> hidrogramaUnitario = new ArrayList<>();

			ArrayList<Double> termo1 = new ArrayList<>();
			ArrayList<Double> termo2 = new ArrayList<>();

			double precipitacao1 = precipitacoes.get(0);
			double termoNulo1 = 0;
			double termoNulo2 = 0;
			double precipitacao2 = precipitacoes.get(1);

			termo1.add(precipitacao1 * HU.get(0));

			for (int i = 1; i < HU.size(); i++) {
				termo1.add(precipitacao1 * HU.get(i));
			}
			termo1.add(termoNulo1);

			int cont = 0;

			termo2.add(termoNulo2);

			for (int i = 2; i <= HU.size(); i++) {
				termo2.add(precipitacao2 * HU.get(i - 2));
				cont++;
			}

			termo2.add(precipitacao2 * HU.get(cont));

			for (int i = 0; i < termo1.size(); i++) {
				hidrogramaUnitario.add(termo1.get(i) + termo2.get(i));
			}

			// Criação do escritor do arquivo de resultados

			FileWriter arq3 = new FileWriter("c:\\hidrologia\\RESULTADOS.txt");

			// Gravação do título do arquivo .txt dos resultados

			PrintWriter gravar3 = new PrintWriter(arq3);
			gravar3.println("Tempo (h); Hidrograma Unitário (m³/s); Vazão (m³/s)");

			HU.add(null);

			// Gravação dos valores dos resultados encontrados

			for (int i = 0; i < hidrogramaUnitario.size(); i++) {
				int horas1 = i + 1;
				gravar3.println(horas1 + ";" + HU.get(i) + ";" + hidrogramaUnitario.get(i));
			}

			System.out.println("O hidrograma unitário criado foi: " + hidrogramaUnitario);
			
			gravar3.close();
			
			System.out.println("Arquivo criado com sucesso!");

		} catch (Exception e) {
			System.out.println("Ocorreu um erro durante a leitura");
		}

	}
}
