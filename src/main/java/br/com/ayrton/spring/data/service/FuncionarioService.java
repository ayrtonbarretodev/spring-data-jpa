package br.com.ayrton.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.ayrton.spring.data.orm.Cargo;
import br.com.ayrton.spring.data.orm.Funcionario;
import br.com.ayrton.spring.data.orm.Unidade;
import br.com.ayrton.spring.data.repository.CargoRepository;
import br.com.ayrton.spring.data.repository.FuncionarioRepository;
import br.com.ayrton.spring.data.repository.UnidadeTrabalhoRepository;

@Service
public class FuncionarioService {

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private final CargoRepository cargoRepository;
	private final FuncionarioRepository funcionarioRepository;
	private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

	public FuncionarioService(CargoRepository cargoRepository, FuncionarioRepository funcionarioRepository,
			UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
		this.cargoRepository = cargoRepository;
		this.funcionarioRepository = funcionarioRepository;
		this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
	}
	
	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("Qual ação deseja executar: ");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");

			int acaoUsuario = scanner.nextInt();

			switch (acaoUsuario) {
			case 1:
				salvar(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar();
				break;
			case 4:
				deletar(scanner);
				break;
			default:
				system = false;
				break;
			}
		}

	}

	private void deletar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		funcionarioRepository.deleteById(id);
		System.out.println("Deletado");
	}

	private void visualizar() {
		System.out.println("Lista de Funcionários");
		Iterable<Funcionario> funcionarios =  funcionarioRepository.findAll();
		funcionarios.forEach(funcionario -> System.out.println(funcionario));		
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Digite seu nome:");
		String nome = scanner.next();
		System.out.println("Digite o CPF:");
		String cpf = scanner.next();
		System.out.println("Digite o salário:");
		Double salario = scanner.nextDouble();
		System.out.println("Digite a data de contração:");
		String dataContratacao = scanner.next();
		System.out.println("Digite o cargo ID:");
		Integer cargoId = scanner.nextInt();
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao,formatter));
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		
		funcionarioRepository.save(funcionario);
        System.out.println("Alterado");
	}

	private void salvar(Scanner scanner) {
		System.out.println("Digite seu nome:");
		String nome = scanner.next();
		System.out.println("Digite o CPF:");
		String cpf = scanner.next();
		System.out.println("Digite o salário:");
		Double salario = scanner.nextDouble();
		System.out.println("Digite a data de contração:");
		String dataContratacao = scanner.next();
		System.out.println("Digite o cargo ID:");
		Integer cargoId = scanner.nextInt();
		
		List<Unidade> unidades = unidade(scanner);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(nome);
		funcionario.setCpf(cpf);
		funcionario.setSalario(salario);
		funcionario.setDataContratacao(LocalDate.parse(dataContratacao,formatter));
		Optional<Cargo> cargo = cargoRepository.findById(cargoId);
		funcionario.setCargo(cargo.get());
		funcionario.setUnidadeTrabalhos(unidades);
		
		funcionarioRepository.save(funcionario);
		System.out.println("Funcionário Salvo");
	}
	
	private List<Unidade> unidade(Scanner scanner) {
        Boolean isTrue = true;
        List<Unidade> unidades = new ArrayList<>();
        while (isTrue) {
            System.out.println("Digite o unidadeId (Para sair digite 0)");
            Integer unidadeId = scanner.nextInt();

            if(unidadeId != 0) {
                Optional<Unidade> unidade = unidadeTrabalhoRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }

        return unidades;
	}
}
