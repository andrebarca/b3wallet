package com.andrebarca.services;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.andrebarca.models.Operacao;
import com.andrebarca.models.TipoOperacao;
import com.andrebarca.repositories.OperacaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author andre
 */
@RestController
public class OperacaoService {

	@Autowired
	OperacaoRepository repository;

	@RequestMapping(value = "/api/operacoes/save", method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Operacao operacao) throws JsonProcessingException {
		Operacao savedObj = repository.save(operacao);
		return new ResponseEntity<>(savedObj, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/operacoes/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable long id) {
		boolean removed = false;
		try {
			repository.deleteById(id);
			removed = true;
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(removed, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/operacoes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> list() {
		Iterable<Operacao> operacoes = this.repository.findAll(Sort.by(Direction.ASC, "papel.codigo")
				.and(Sort.by(Direction.ASC, "dataOperacao")).and(Sort.by(Direction.ASC, "tipoOperacao")));
		return new ResponseEntity<>(operacoes, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/operacoes/tipos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listOptionsTipoOperacao() {
		return new ResponseEntity<>(TipoOperacao.getPropertyList(), HttpStatus.OK);
	}

	@RequestMapping(value = "/api/operacoes/meses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> listOptionsDataOperacao() {
		SimpleDateFormat valueFormat = new SimpleDateFormat("yyyy-MM");
		SimpleDateFormat descriptionFormat = new SimpleDateFormat("MM/yyyy");
		Set<Map<String, String>> dateList = new HashSet<>();
		this.repository.listAllDataOperacao().forEach(date -> {
			Map<String, String> dateMap = new HashMap<String, String>();
			dateMap.put("value", valueFormat.format(date));
			dateMap.put("description", descriptionFormat.format(date));
			dateList.add(dateMap);
		});
		return new ResponseEntity<>(dateList, HttpStatus.OK);
	}
}
