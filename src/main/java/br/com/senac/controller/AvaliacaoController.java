package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.entity.Avaliacao;
import br.com.senac.service.AlunoService;
import br.com.senac.service.AvaliacaoService;
import br.com.senac.service.CursoService;

@Controller
@RequestMapping("avaliacoes")
public class AvaliacaoController {
	@Autowired
	AvaliacaoService avaliacaoService;
	@Autowired
	AlunoService alunoService;
	@Autowired
	CursoService cursoService;
	
	@GetMapping("/adiciona")
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("avaliacoes/inserirNota");
		mv.addObject("alunos", alunoService.selectAll());
		mv.addObject("cursos", cursoService.selectAll());
		mv.addObject("avaliacao", new Avaliacao());
		return mv;
	}
	
	@GetMapping("/save")
	public ModelAndView save(@ModelAttribute("avaliacao") Avaliacao avaliacao) {
		avaliacao.getAlunoCurso().setAluno(alunoService.select(avaliacao.getAlunoCurso().getAluno().getId()));
		avaliacao.getAlunoCurso().setCurso(cursoService.select(avaliacao.getAlunoCurso().getCurso().getId()));
		avaliacaoService.insert(avaliacao);
		ModelAndView mv = new ModelAndView("avaliacoes/listarNotas");
		mv.addObject("avaliacoes", avaliacaoService.selectAll());
		return mv;
	}
}
