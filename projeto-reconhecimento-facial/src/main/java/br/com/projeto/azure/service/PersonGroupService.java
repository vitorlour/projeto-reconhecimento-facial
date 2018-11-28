/**
 * 
 */
package br.com.projeto.azure.service;

/**
 * @author vitor
 *
 */
public interface PersonGroupService {
	
	void criarGrupo(String personGroupId, String nome);
		
	void trainGroup(String personGroupId);

}
