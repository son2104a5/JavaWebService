package com.data.repo;

import com.data.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    public void save(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        } else {
            em.merge(product);
        }
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public void delete(Product product) {
        if (em.contains(product)) {
            em.remove(product);
        } else {
            Product managedProduct = findById(product.getId());
            if (managedProduct != null) {
                em.remove(managedProduct);
            }
        }
    }
}
