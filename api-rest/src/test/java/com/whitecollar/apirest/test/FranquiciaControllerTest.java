package com.whitecollar.apirest.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whitecollar.apirest.model.entity.Botiga;
import com.whitecollar.apirest.model.entity.Quadre;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FranquiciaControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;
	
	@BeforeEach
	public void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
			
	@Test
	@DisplayName("POST: Crear botiga")
	public void testCrearBotiga() throws Exception {
		Botiga botiga = new Botiga();
		botiga.setNomBotiga("Galeria");
		botiga.setCapacitat(100);
		
		ObjectMapper mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(botiga);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/shops/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJson.getBytes()))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@DisplayName("POST: Crear botiga sense nom")
	public void testCrearBotigaSenseNom() throws Exception {
		Botiga botiga = new Botiga();
		botiga.setNomBotiga("");
		botiga.setCapacitat(100);
		
		ObjectMapper mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(botiga);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/shops/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJson.getBytes()))
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	@DisplayName("POST: Crear botiga sense capacitat")
	public void testCrearBotigaSenseCapacitat() throws Exception {
		Botiga botiga = new Botiga();
		botiga.setNomBotiga("Galeria");
		botiga.setCapacitat(0);
		
		ObjectMapper mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(botiga);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/shops/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJson.getBytes()))
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	@DisplayName("GET: Llistar botigues")
	public void testLlistarBotigues() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/shops/")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
    // Afegir quadre: li donarem el nom del quadre i el del autor (POST /shops/{ID}/pictures)

	@Test
	@DisplayName("POST: Afegir quadre")
	public void testAfegirQuadre() throws Exception {
		Quadre quadre = new Quadre();
		quadre.setNomQuadre("La Gioconda");
		quadre.setNomAutor("Leonardo da Vinci");
		quadre.setPreu(95000);
		
		ObjectMapper mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(quadre);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/shops/{botigaID}/pictures", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJson.getBytes()))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
    // Llistar els quadres de la botiga (GET /shops/{ID}/pictures)

	@Test
	@DisplayName("GET: Llistar quadres")
	public void testLlistarQuadres() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/shops/{botigaID}/pictures", 1)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
    // Incendiar quadres: per si ve la policia, es poden eliminar tots els quadres de la botiga sense deixar rastre (DELETE /shops/{ID}/pictures)
	
	@Test
	@DisplayName("DELETE: Incendiar quadres")
	public void testIncendiarQuadres() throws Exception {
		Botiga botiga = new Botiga();
		botiga.setNomBotiga("Galeria");
		botiga.setCapacitat(100);
		botiga.setBotigaID(1);
		
		ObjectMapper mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(botiga);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/shops/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(userJson.getBytes()));
		
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/shops/{botigaID}/pictures", 1)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	@DisplayName("DELETE: Incendiar quadres botiga not found")
	public void testIncendiarQuadresBotigaNotFound() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/shops/{botigaID}/pictures", -1)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
}
