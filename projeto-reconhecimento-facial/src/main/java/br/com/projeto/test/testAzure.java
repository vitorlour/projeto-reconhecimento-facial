package br.com.projeto.test;

import java.io.IOException;

public class testAzure{
	
	final static String subscriptionKey = "037e2c8cbbcb4896b70d08a72b294eb7";
	final static String baseUrl = "https://brazilsouth.api.cognitive.microsoft.com/face/v1.0";
	final static String caminho1 = "";
	final static String caminho2 = "C:\\detection3.jpg";
	final static String caminho4 = "C:\\brasil-eleicoes-lula-2.jpg";
	final static String FACE_ID_KEY = "faceId";
	
	

	public static void main (String [] args) throws IOException {
		
		/**
		*  1 - Criar person group
		*/
			
//		PersonGroupServiceImpl personGroupService = new PersonGroupServiceImpl();	
		
//	boolean personGroupCriar = personGroupService.criarGrupo("1","Projeto Integrado", "Sistemas de Informação");

	 //   if(personGroupCriar) {
	//    	System.out.println("Grupo criado com sucesso !");
	  //  }
		
		/**
		*  2 - deletar grupo
		*  
		*/
		
	//	ResponseEntity<String> DeleteGroupById = personGroupService.deletarGroupPorId("10");
		
	//	System.out.println(DeleteGroupById.toString());
		
		/**
		*  3 - List de grupos
		*/
		
	//	String  personGroupListarGrupos = personGroupService.listGroup();
		
	//	System.out.println(personGroupListarGrupos.toString());
		
		/**
		*  4 - list grupo por id
		*/
		
	//	ResponseEntity<String>  personGroupGetPorId = personGroupService.listGroupPorId("7");
		
	//	System.out.println(personGroupGetPorId.toString());
	
		/**
		*  5 - criar pessoa no grupo
		*/
		
		/*PersonGroupPersonServiceImpl personGroupPersonService = new PersonGroupPersonServiceImpl();	
	
		
		String  personGroupPersonCriarPessoa = personGroupPersonService.criarPessoaGroup
																("1", "Vitor Lourenco", "Cadastrada 20/10/2018");
	
		System.out.println(personGroupPersonCriarPessoa.substring(13, 49));*/
		
		
		/**
		*  6 - Salvar Face no grupo
		*/
		
	//	ResponseEntity<String>  personGroupPersonSalvarFace = personGroupPersonService.SalvarFaceGroup
	//														("7", "47cc80d8-f2c5-4efe-9efb-7a11467216dd", caminho2);
		
	//	System.out.print(personGroupPersonSalvarFace);
		
		/**
		*  7 - Get Face rentagle -  reconhece a face
		*/
			
		
//	 FaceServiceImpl faceServiceImpl = new FaceServiceImpl();
		
	//	FaceRectangle faceRectangle = faceServiceImpl.getFaceDetectionFaceRectangleForUrl(caminho2);
		
	
	//	System.out.println(faceRectangle.toString());
		
		/**
		*  8 - Get FaceId - reconhece a face
		*/
			
		//String testt = faceServiceImpl.getFaceDetection(caminho4).getJSONObject(0).getString(Constantes.FACE_ID);
			
	//	System.out.println(testt.toString());
			
		/**
		*  9 - verificar as face
		*/
		
	 //   FaceMatch faceMatch = faceServiceImpl.getFaceMatchFromResponse("faceIdSalvo", "Novo FaceId");
															
	//	 FaceMatch faceMatch = faceServiceImpl.getFaceMatchFromResponse("82fb178d-35bc-4620-8979-2077557a8797", "7161aa1c-a137-46a8-a101-3a6e92611ef2");													
			
	//    System.out.println(faceMatch.toString());
		
		/**
		*  10 - train person group
		*/
		
	//	boolean train = personGroupService.trainGroup("1");
	    
	//	if(train) {
	 //   	System.out.println("Submit com sucesso TRAIN GROUP !");
	  //  }
		
		/**
		*  11 - Get Pessoa grupo
		*/
		 
		//ResponseEntity<String>  personGroupPersonGetPessoaGroup = personGroupPersonService.getPessoaGroup
		//																("7", "47cc80d8-f2c5-4efe-9efb-7a11467216dd");
		
		//System.out.println(personGroupPersonGetPessoaGroup.toString());
		
		/**
		*  11 - Get face pessoa grupo 
		*/
		
	//	ResponseEntity<String>  personGroupPersonGetFacePessoaGroup = personGroupPersonService.getFacePessoaGroup
	//																	("7", "47cc80d8-f2c5-4efe-9efb-7a11467216dd","9bb40093-649a-487b-8063-85dfd0ad5eed");
		
	//	System.out.println(personGroupPersonGetFacePessoaGroup.toString());
		
		/**
		*  12 - deletar pessoa grupo
		*/
		
	//	ResponseEntity<String>  personGroupPersonDeletarPessoaGroup = personGroupPersonService.deletarPessoaGroup
	//																	("7", "47cc80d8-f2c5-4efe-9efb-7a11467216dd");
		
	//	System.out.println(personGroupPersonDeletarPessoaGroup.toString());
		
		/**
		*  13 - Identificar Pessoa no grupo
		*/
		
	  /* Candidates[]  identificarPessoa =   faceServiceImpl.getIdentificacaoFace("1", caminho4);
	   
	 	if (identificarPessoa != null) {
		   for (Candidates candidates : identificarPessoa) {
			   System.out.println(candidates.getPersonId());
		   }
	 	}   
	   
	   
	   
	   if(identificarPessoa != null) {
		   
		   System.out.println(identificarPessoa.toString());
	   }*/
	 
				
	}

}
