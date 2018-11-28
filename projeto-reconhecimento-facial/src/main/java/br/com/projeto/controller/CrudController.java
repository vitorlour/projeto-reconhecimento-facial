package br.com.projeto.controller;

//@Controller
public class CrudController {/*

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private PessoaProcessor pessoaProcessador;

	@GetMapping("/")
	public ModelAndView findAll() {

		ModelAndView mv = new ModelAndView("/inicio");

		mv.addObject("pessoa", pessoaService.encontrarTodos());

		return mv;

	}

	@GetMapping("/save")
	public ModelAndView add(Pessoa pessoa) {

		ModelAndView mv = new ModelAndView("/salvar");
		mv.addObject("pessoa", pessoa);

		return mv;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {

		return add(pessoaService.encontrarPorId(id));
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Pessoa pessoa) {

		pessoaService.excluir(pessoa);

		return findAll();
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Pessoa pessoa, BindingResult result,
			@RequestParam("fileUpload") MultipartFile fileUpload) throws IOException {

		if (result.hasErrors()) {
			return add(pessoa);
		}
		try {

			pessoaProcessador.salvarPessoa(fileUpload, pessoa);
				

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return findAll();
	}

*/}
