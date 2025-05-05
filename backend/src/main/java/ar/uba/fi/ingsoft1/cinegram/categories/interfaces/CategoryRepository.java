package ar.uba.fi.ingsoft1.cinegram.categories.interfaces;

import ar.uba.fi.ingsoft1.cinegram.categories.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
