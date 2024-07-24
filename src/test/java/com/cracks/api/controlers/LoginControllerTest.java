package com.cracks.api.controlers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;


    static String token;
    @Test
    public void testLogin() throws Exception {
        // Crear archivos multipart
        MockMultipartFile namePart = new MockMultipartFile("name", "", "text/plain", "diego".getBytes());
        MockMultipartFile pwdPart = new MockMultipartFile("pwd", "", "text/plain",
                "CIuQ9Prx1hUAA0hBBAglkUumPLQlg2Rz/Wcew7UTF5ZKobBwgpgaxujehRH/6niXu0CZe1tTWetv\nv5cniT2vpqX1/6YyN3qRdX0bIMgIbNxUFD2jZD16YxZa542fLc/6Xb9uuEqY/4KOj8mCnSOPvQ+/\nSlKrjdUk6t3i5gn7GhQF15bdDh4R6X3gXyBe8TWS9vkiKHVNF9i4muj3a7C/A8KxH5+9PJVgX62k\nBR2t8CcRyL+sfC/IL56Qto1VsUkiF6X1oIwM1bCtGRWQfZLt+3xWgSWPJMLAUd5wqhjNJDfekJ/n\nZmn/sd7CwGWZXmZHIhxfwKbc6EzUhmeIVtvrug=="
                        .getBytes());

        // Realizar la solicitud POST
        MvcResult r = mockMvc.perform(MockMvcRequestBuilders.multipart("/login2")
                .file(namePart)
                .file(pwdPart)
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .accept(MediaType.ALL))
                .andReturn();

        System.out.println("\n\n**LOGIN***"+r.toString()+"\n\n******\n");
        token=r.toString();
        // .andExpect(status().isOk()); // Ajusta esto según el estado esperado
    }
    @Test
    public void testSayHello2() throws Exception {
        System.out.println("\n\n*****token: "+token);
    }
    @Test
    public void testSayHello() throws Exception {
        // Realizar la solicitud GET y verificar la respuesta
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/aa")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk()) // Verifica que el estado sea 200 OK
                // .andExpect(content().string("hola"))
                .andReturn(); // Verifica que el contenido de la respuesta sea "hola"
        String responseContent = result.getResponse().getContentAsString();

        System.out.println("********" + responseContent);
        assertThat(responseContent).isEqualTo("hola");

    }
}
