package guru.springframework.webfluxrest.contollers;

import guru.springframework.webfluxrest.domain.Category;
import guru.springframework.webfluxrest.domain.Vendor;
import guru.springframework.webfluxrest.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.mock;

public class VendorControllerTest {

    WebTestClient webTestClient;
    VendorRepository vendorRepository;
    VendorController vendorController;

    @Before
    public void setUp(){
        vendorRepository = mock(VendorRepository.class);
        vendorController = new VendorController(vendorRepository);
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    public void list() {
        BDDMockito.given(vendorRepository.findAll())
                .willReturn(Flux.just(Vendor.builder().firstName("Foo").build(),
                        Vendor.builder().firstName("Barr").build()));

        webTestClient.get().uri("/api/v1/vendors")
                .exchange()
                .expectBodyList(Category.class).hasSize(2);
    }

    @Test
    public void getById() {
        BDDMockito.given(vendorRepository.findById("id"))
                .willReturn(Mono.just(Vendor.builder().firstName("Foo").build()));

        webTestClient.get().uri("/api/v1/vendors/id")
                .exchange()
                .expectBody(Category.class);
    }
}