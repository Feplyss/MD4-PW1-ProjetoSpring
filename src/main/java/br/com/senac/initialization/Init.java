package br.com.senac.initialization;

import java.util.ArrayList;
import java.util.List;

//import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.senac.entity.Aluno;
import br.com.senac.entity.Curso;
import br.com.senac.entity.Endereco;
import br.com.senac.entity.Turma;
import br.com.senac.repository.ProfessorRepository;
//import br.com.senac.repository.AlunoRepository;
import br.com.senac.service.AlunoService;
import br.com.senac.service.CursoService;
import br.com.senac.service.ProfessorService;
import br.com.senac.service.TurmaService;

@Component
public class Init implements ApplicationListener<ContextRefreshedEvent>{
	@Autowired
	AlunoService alunoService;
	@Autowired
	ProfessorService professorService;
	@Autowired
	CursoService cursoService;
	@Autowired
	TurmaService turmaService;
	@Autowired
	ProfessorRepository professorRepository;
	
	//@Autowired
	//AlunoRepository repo;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		//Cursos
		Curso c1 = new Curso();
		c1.setNome("Java");
		cursoService.insert(c1);
		
		Curso c2 = new Curso();
		c2.setNome("Python");
		cursoService.insert(c2);
		
		Curso c3 = new Curso();
		c3.setNome("NodeJS");
		cursoService.insert(c3);
		
		//Listas de curso
		List<Curso> listaCursos1 = new ArrayList<>();
		listaCursos1.add(c1);
		listaCursos1.add(c2);
		List<Curso> listaCursos2 = new ArrayList<>();
		listaCursos2.add(c2);
		listaCursos2.add(c3);
		List<Curso> listaCursos3 = new ArrayList<>();
		listaCursos3.add(c3);
		listaCursos3.add(c1);
		
		//Turmas
		Turma t1 = new Turma();
		t1.setNome("ADS2021.1");
		t1.setCursos(listaCursos1);
		turmaService.insert(t1);
		
		Turma t2 = new Turma();
		t2.setNome("ADS2021.2");
		t2.setCursos(listaCursos2);
		turmaService.insert(t2);
		
		Turma t3 = new Turma();
		t3.setNome("ADS2022.1");
		t3.setCursos(listaCursos3);
		turmaService.insert(t3);
		
		//Alunos
		Aluno a1 = new Aluno();
		a1.setNome("Rogerio");
		a1.setTurma(t3);
		alunoService.insert(a1);
		
		Aluno a2 = new Aluno();
		a2.setNome("Alfredo");
		a2.setTurma(t1);
		alunoService.insert(a2);
		
		Aluno a3 = new Aluno();
		a3.setNome("Juleide");
		a3.setTurma(t2);
		alunoService.insert(a3);
		
		//Enderecos
		Endereco e1 = new Endereco();
		e1.setBairro("Pilares");
		e1.setCep("123456789");
		e1.setNumero(110);
		e1.setRua("Rua Leopolda");
		e1.setComplemento("Casa 123");
		e1.setAluno(a3);
		
		Endereco e2 = new Endereco();
		e2.setBairro("Inhauma");
		e2.setCep("234567891");
		e2.setNumero(120);
		e2.setRua("Rua Garrincho");
		e2.setComplemento("Casa 456");
		e2.setAluno(a1);
		
		Endereco e3 = new Endereco();
		e3.setBairro("Madureira");
		e3.setCep("345678912");
		e3.setNumero(130);
		e3.setRua("Rua Seu Jose");
		e3.setComplemento("Casa 789");
		e3.setAluno(a2);
		
		Endereco e4 = new Endereco();
		e4.setBairro("Copacabana");
		e4.setCep("456789123");
		e4.setNumero(140);
		e4.setRua("Rua Clemente");
		e4.setComplemento("Casa 147");
		e4.setAluno(a1);
		
		//Listar turmas
//		List<Turma> listaTurmas = turmaService.selectAll();
//		for(Turma turma : listaTurmas) {
//			System.out.println(turma.getNome());
//			for(Aluno aluno : turma.getAlunos()) {
//				System.out.println(aluno.getNome());
//			};
//		}
		
		List<Turma> listaTurmas = turmaService.selectAlunos(3);
		for(Turma turma : listaTurmas) {
			for(Aluno aluno : turma.getAlunos()) {
				System.out.println(aluno.getNome());
			}
		}
		Aluno al1 = alunoService.selectByName("Rogerio");
		
		al1.getEnderecos().forEach((e) -> System.out.println(e.getRua() + ", " + e.getNumero()));
	}
}
