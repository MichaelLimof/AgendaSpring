package com.evento.ibm.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.evento.ibm.Model.Contato;
import com.evento.ibm.Model.Telefone;
import com.evento.ibm.Repository.ContatoRepository;
import com.evento.ibm.Repository.TelefoneRepository;

@Controller
public class ContatoController {

	@Autowired
	private ContatoRepository cr;

	@Autowired
	private TelefoneRepository tr;

	@RequestMapping(value = "/cadastrarContato", method = RequestMethod.GET)
	public String form() {
		return "cadastro/formCadastro";
	}

	@RequestMapping(value = "/cadastrarContato", method = RequestMethod.POST)
	public String form(@Valid Contato contato, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarContato";
		}
		cr.save(contato);
		attributes.addFlashAttribute("mensagem", "Contato Adicionado!");
		return "redirect:/cadastrarContato";
	}

//	@RequestMapping("/")
//	public ModelAndView Empresas() {
//		ModelAndView mv = new ModelAndView("index");
//		Iterable<Cadastro> cadastros = er.findAll();
//		mv.addObject("cadastros",cadastros);
//		return mv;
//		
//	}
	@RequestMapping("/cadastros")
	public ModelAndView listaContato() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Contato> contatos = cr.findAll();
		mv.addObject("contatos", contatos);
		return mv;

	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
	public ModelAndView detalhesContato(@PathVariable("codigo") long codigo) {
		Contato contato = cr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("cadastro/detalhes");
		mv.addObject("contato", contato);
		Iterable<Telefone> telefones = tr.findByContato(contato);
		mv.addObject("telefones", telefones);
		return mv;

	}
	@RequestMapping("/deletar")
	public String deletarContato(long codigo) {
		Contato contato = cr.findByCodigo(codigo);
		cr.delete(contato);
		return "redirect:/cadastros";
	}
	
	@RequestMapping(value = "/{codigo}", method = RequestMethod.POST)
	public String saveTelefone(@PathVariable("codigo") long codigo, @Valid Telefone telefone,
			BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{codigo}";
		}
		Contato contato = cr.findByCodigo(codigo);
		telefone.setContato(contato);
		tr.save(telefone);
		attributes.addFlashAttribute("mensagem", "Telefone Adicionado!");
		return "redirect:/{codigo}";

	}

}
