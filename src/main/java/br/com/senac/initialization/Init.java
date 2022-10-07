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
		
		Curso c1 = new Curso();
		c1.setNome("Java");
		cursoService.insert(c1);
		
		Curso c2 = new Curso();
		c2.setNome("Python");
		cursoService.insert(c2);
		
		Curso c3 = new Curso();
		c3.setNome("NodeJS");
		cursoService.insert(c3);
		
		List<Curso> listaCursos1 = new ArrayList<>();
		listaCursos1.add(c1);
		listaCursos1.add(c2);
		List<Curso> listaCursos2 = new ArrayList<>();
		listaCursos2.add(c2);
		listaCursos2.add(c3);
		List<Curso> listaCursos3 = new ArrayList<>();
		listaCursos3.add(c3);
		listaCursos3.add(c1);
		
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
		
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Rogerio");
		aluno1.setTurma(t3);
		
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Alfredo");
		aluno2.setTurma(t1);
		
		Aluno aluno3 = new Aluno();
		aluno3.setNome("Juleide");
		aluno3.setTurma(t2);
		
		alunoService.insert(aluno1);
		alunoService.insert(aluno2);
		alunoService.insert(aluno3);
		
		alunoService.insert(aluno1);
		alunoService.insert(aluno2);
		alunoService.insert(aluno3);
		
		System.out.println(alunoService.select(1).getTurma().getNome());
		System.out.println(alunoService.select(2).getTurma().getNome());
		System.out.println(alunoService.select(3).getTurma().getNome());
	}
}
