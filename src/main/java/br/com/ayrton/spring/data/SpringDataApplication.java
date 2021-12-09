package br.com.ayrton.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ayrton.spring.data.service.CargoService;
import br.com.ayrton.spring.data.service.UnidadeTrabalhoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private final CargoService cargoService;
	
	private final UnidadeTrabalhoService unidadeService;

	private Boolean system = true;

	public SpringDataApplication(CargoService cargoService,UnidadeTrabalhoService unidadeService) {
		this.cargoService = cargoService;
		this.unidadeService = unidadeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("Qual ação você quer executar: ");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Unidade");
			System.out.println("3 - Funcionario");
			
			int action = scanner.nextInt();
			
			if (action == 1) {
				cargoService.inicial(scanner);
			}else if(action == 2) {
				unidadeService.inicial(scanner);
			}else {
				system = false;
				System.out.println("Programa Finalizado");
			}

		}

	}

}
