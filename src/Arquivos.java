import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Arquivos {

	public static void main(String[] args) {

		try {
			Scanner sc = new Scanner(System.in);
			File diretorio = new File("c:\\hidrologia");
			System.out.println("Aplicação 12: Arquivos!");
			if (diretorio.exists()) {
				System.out.println("Diretório já existente");
			} else {
				System.out.println("Diretório inexistente");
				diretorio.mkdir();
				System.out.println("Diretório criado com sucesso");
			}

			FileWriter arq = new FileWriter("c:\\hidrologia\\pluviometria.txt");
			
			int periodo, ano;
			double precipitacao;
			
			System.out.print("Quantos anos de registro?");
			periodo = sc.nextInt();
			
			PrintWriter gravar = new PrintWriter(arq);
			gravar.println("Ano;Precipitação (mm)");

			for (int i = 0; i < periodo; i++) {
				System.out.println("Qual o " + (i + 1) + "° ano de registro? ");
				ano = sc.nextInt();
				System.out.println("Qual a precipitação registrada? ");
				precipitacao = sc.nextDouble();
				gravar.println(ano + ";" + precipitacao);
			}

			sc.close();
			gravar.close();
		} catch (Exception e) {
			System.out.println("Ocorreu um erro durante a leitura");
		}
		Path caminho = Paths.get("C:/hidrologia/pluviometria.txt");
		try {
			byte [] texto = Files.readAllBytes(caminho);
			String leitura = new String (texto);
			System.out.println("O arquivo criado foi: ");
			System.out.println(leitura);
		} catch (Exception e) {
			System.out.println("Ocorreu um erro durante a leitura");
		}
	}
}