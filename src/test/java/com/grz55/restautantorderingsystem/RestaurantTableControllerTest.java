package com.grz55.restautantorderingsystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantTableController.class)
public class RestaurantTableControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestaurantTableService restaurantTableService;

    @Test
    public void shouldReturnAllRestaurantTables() throws Exception {
        when(restaurantTableService.findAll()).thenReturn(Arrays.asList(new RestaurantTable(1), new RestaurantTable(2)));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/tables").accept(MediaType.APPLICATION_JSON);

        //MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void shouldReturnOneRestaurantTable() throws Exception {
        RestaurantTable restaurantTable = new RestaurantTable(1);
        restaurantTable.setId(1L);
        Optional<RestaurantTable> restaurantTableOptional = Optional.of(restaurantTable);
        when(restaurantTableService.findById(1L)).thenReturn(restaurantTableOptional);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/tables/{id}", 1L).accept(MediaType.APPLICATION_JSON);

        //MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(1)))
                .andExpect(jsonPath("@.id", is(1)));
    }

    @Test//(expected = RestaurantTableNotFoundException.class)
    public void shouldThrowRestaurantTableNotFoundExceptionWhenGetsById() throws Exception {
        Optional<RestaurantTable> restaurantTableOptional = Optional.empty();
        when(restaurantTableService.findById(1L)).thenReturn(restaurantTableOptional);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/tables/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON);

        //MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound());
    }


    @Test
    public void shouldReturnImUsedStatusWhenRestaurantTableExceptionThrown() throws Exception {
        RestaurantTable restaurantTable = new RestaurantTable(1);
        restaurantTable.setId(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String exampleRestaurantTableJson = objectMapper.writeValueAsString(restaurantTable);

        when(restaurantTableService.save(1)).thenThrow(new RestaurantTableFoundException(1));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/tables")
                .accept(MediaType.APPLICATION_JSON).content(exampleRestaurantTableJson)
                .contentType(MediaType.APPLICATION_JSON);

        //MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isImUsed());
    }


    @Test
    public void shouldAddRestaurantTable() throws Exception {
        RestaurantTable restaurantTable = new RestaurantTable(2);
        ObjectMapper objectMapper = new ObjectMapper();
        String exampleRestaurantTableJson = objectMapper.writeValueAsString(restaurantTable);
        when(restaurantTableService.save(2)).thenReturn(restaurantTable);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/tables")
                .accept(MediaType.APPLICATION_JSON).content(exampleRestaurantTableJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test//(expected = RestaurantTableNotFoundException.class)
    public void shouldThrowRestaurantTableNotFoundExceptionWhenDeletes() throws Exception {
        Optional<RestaurantTable> restaurantTableOptional = Optional.empty();
        when(restaurantTableService.findById(1L)).thenReturn(restaurantTableOptional);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/tables/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON);

        //MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        this.mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound());
    }

}
