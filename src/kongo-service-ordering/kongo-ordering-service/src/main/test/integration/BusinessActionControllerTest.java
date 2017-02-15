package integration;

import edu.hm.ba.kongo.shop.ordering.service.gen.businessActionParams.CancelOrder_BusinessActionParameters;
import edu.hm.ba.kongo.shop.ordering.service.gen.businessActionParams.OrderCart_BusinessActionParameters;
import edu.hm.ba.kongo.shop.ordering.service.gen.businessActionParams.SendInvoice_BusinessActionParameters;
import edu.hm.ba.kongo.shop.ordering.service.gen.controller.businessactions.BusinessActionController;
import edu.hm.ba.kongo.shop.ordering.service.gen.services.businessactions.TestDatenBusinessActionService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import utils.TestAuthenticationProvider;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Integration Tests for the BusinnesActionController. Checks
 */
public class BusinessActionControllerTest extends OrderingServiceBaseTest {

    @Autowired
    BusinessActionController businessActionController;

    @Test
    public void beanExists(){
        assertNotNull(businessActionController);
    }

    @Test
    public void getMethodActionsTest(){
        List<Link> methodActions = businessActionController.getMethodActions();
        Set<String> collect = methodActions.stream().map(Link::getRel).collect(Collectors.toSet());
        assertTrue(collect.containsAll(Arrays.asList("testdatenErzeugen", "orderCart", "sendInvoice", "cancelOrder")));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void orderCartTest(){
        OrderCart_BusinessActionParameters parameters = new OrderCart_BusinessActionParameters();
        parameters.setCartID("1234-5678");
        businessActionController.orderCart(null, null, parameters);
    }

    @Test(expected = AccessDeniedException.class)
    public void orderCartAuthenticationTest(){
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(TestAuthenticationProvider.getTestAuthenticationWithSpecific(AuthorityUtils.createAuthorityList("none")));

        OrderCart_BusinessActionParameters parameters = new OrderCart_BusinessActionParameters();
        parameters.setCartID("1234-5678");
        businessActionController.orderCart(null, null, parameters);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void sendInvoiceTest(){
        SendInvoice_BusinessActionParameters parameters = new SendInvoice_BusinessActionParameters();
        parameters.setOrderID("1234-5678");
        businessActionController.sendInvoice(null, null, parameters);
    }

    @Test(expected = AccessDeniedException.class)
    public void sendInvoiceAuthenticationTest(){
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(TestAuthenticationProvider.getTestAuthenticationWithSpecific(AuthorityUtils.createAuthorityList("none")));

        SendInvoice_BusinessActionParameters parameters = new SendInvoice_BusinessActionParameters();
        parameters.setOrderID("1234-5678");
        businessActionController.sendInvoice(null, null, parameters);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void cancelOrderTest(){
        CancelOrder_BusinessActionParameters parameters = new CancelOrder_BusinessActionParameters();
        parameters.setOrderID("1234-5678");
        businessActionController.cancelOrder(null, null, parameters);
    }

    @Test(expected = AccessDeniedException.class)
    public void cancelOrderAuthenticationTest(){
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(TestAuthenticationProvider.getTestAuthenticationWithSpecific(AuthorityUtils.createAuthorityList("none")));

        CancelOrder_BusinessActionParameters parameters = new CancelOrder_BusinessActionParameters();
        parameters.setOrderID("1234-5678");
        businessActionController.cancelOrder(null, null, parameters);
    }
}
