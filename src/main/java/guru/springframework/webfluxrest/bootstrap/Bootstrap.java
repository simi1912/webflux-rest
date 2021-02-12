package guru.springframework.webfluxrest.bootstrap;

import guru.springframework.webfluxrest.domain.Category;
import guru.springframework.webfluxrest.domain.Vendor;
import guru.springframework.webfluxrest.repositories.CategoryRepository;
import guru.springframework.webfluxrest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository,
                     VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) {

        System.out.println("Bootstrap invoked...");

//        categoryRepository.deleteAll().block();
//        vendorRepository.deleteAll().block();

        if(categoryRepository.count().block() == 0){
            System.out.println("#### LOADING DATA ON BOOTSTRAP ####");

            longCategories();
            loadVendors();
        } else {
            System.out.println("Data already loaded...-");
        }
    }

    private void longCategories() {
        Category fruits = new Category();
        fruits.setDescription("Fruits");

        Category dried = new Category();
        dried.setDescription("Dried");

        Category fresh = new Category();
        fresh.setDescription("Fresh");

        Category exotic = new Category();
        exotic.setDescription("Exotic");

        Category nuts = new Category();
        nuts.setDescription("Nuts");

        categoryRepository.save(fruits).block();
        categoryRepository.save(dried).block();
        categoryRepository.save(fresh).block();
        categoryRepository.save(exotic).block();
        categoryRepository.save(nuts).block();

        System.out.println("Categories Loaded = " + categoryRepository.count().block());
    }

    private void loadVendors() {
        Vendor vendor1 = Vendor.builder()
                .firstName("Joe")
                .lastName("Buck").build();

        Vendor vendor2 = Vendor.builder()
                .firstName("Michael")
                .lastName("Weston").build();

        Vendor vendor3 = Vendor.builder()
                .firstName("Jessie")
                .lastName("Waters").build();;

        Vendor vendor4 = Vendor.builder()
                .firstName("Bill")
                .lastName("Neri").build();

        Vendor vendor5 = Vendor.builder()
                .firstName("Jimmy")
                .lastName("Buffet").build();

        vendorRepository.save(vendor1).block();
        vendorRepository.save(vendor2).block();
        vendorRepository.save(vendor3).block();
        vendorRepository.save(vendor4).block();
        vendorRepository.save(vendor5).block();

        System.out.println("Vendors Loaded = " + vendorRepository.count().block());
    }
}