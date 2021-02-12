package guru.springframework.webfluxrest.bootstrap;

import guru.springframework.webfluxrest.domain.Category;
import guru.springframework.webfluxrest.domain.Vendor;
import guru.springframework.webfluxrest.repositories.CategoryRepository;
import guru.springframework.webfluxrest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;

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
        longCategories();
        loadVendors();
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

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Categories Loaded = " + categoryRepository.count() );
    }

    private void loadVendors() {
        Vendor vendor1 = new Vendor();
        vendor1.setName("Western Tasty Fruits Ltd.");
        Vendor vendor2 = new Vendor();
        vendor2.setName("Exotic Fruits Company");
        Vendor vendor3 = new Vendor();
        vendor3.setName("Home Fruits");
        Vendor vendor4 = new Vendor();
        vendor4.setName("Fun Fresh Fruits Ltd.");
        Vendor vendor5 = new Vendor();
        vendor5.setName("Nuts for Nuts Company");

        vendorRepository.save(vendor1);
        vendorRepository.save(vendor2);
        vendorRepository.save(vendor3);
        vendorRepository.save(vendor4);
        vendorRepository.save(vendor5);

        System.out.println("Vendors Loaded = " + vendorRepository.count());
    }
}
