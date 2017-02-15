package component;

import edu.hm.ba.kongo.shop.ordering.service.gen.domain.OrderingItem_;
import edu.hm.ba.kongo.shop.ordering.service.gen.rest.search.OrderingItem_SearchController;
import edu.hm.ba.kongo.shop.ordering.service.gen.services.businessactions.TestDatenBusinessActionService;
import edu.hm.ba.kongo.shop.ordering.service.rest.OrderingItem_Repository;
import integration.OrderingServiceBaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import utils.TestAuthenticationProvider;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Fabian on 13.02.2017.
 */
public class OrderingItem_SearchControllerTest extends OrderingServiceBaseTest {

    @Autowired
    private OrderingItem_SearchController searchController;
    @Autowired
    private TestDatenBusinessActionService testDatenBusinessActionService;
    @Autowired
    private OrderingItem_Repository repository;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void createTestData(){
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(TestAuthenticationProvider.getTestAuthenticationWithAll());
        testDatenBusinessActionService.testdatenErzeugen();
    }

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void clearDB(){
        repository.deleteAll();
    }

    @Test
    public void beanExists(){
        assertNotNull(searchController);
    }

    @Test
    public void findFullTextFuzzyTest() throws Exception {        
        mockMvc.perform(get("/orderingItems/search/findFullTextFuzzy").param("q", "2010-10-10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("$._embedded.orderingItems[0].cart").value("123"));
    }
}
