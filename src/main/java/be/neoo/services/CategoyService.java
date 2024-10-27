package be.neoo.services;

import be.neoo.connection.EMF;
import be.neoo.dto.CategoryDto;
import be.neoo.entities.Category;
import be.neoo.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoyService {
    private ModelMapper modelMapper;
    private CategoryRepository categoryRepository;

    public CategoyService(ModelMapper modelMapper, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
    }

    public CategoryDto save (CategoryDto categoryDto) {
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();
        Category category = new Category();
        CategoryDto bdCategory = new CategoryDto();
        try {
            trans.begin();
            category = categoryRepository.save(em, modelMapper.map(categoryDto, Category.class));
            bdCategory = modelMapper.map(category, CategoryDto.class);
            trans.commit();
        }catch (Exception e) {
            trans.rollback();
        } finally {
            if (trans.isActive()) {
                trans.rollback();
            }
            em.close();
        }
        return bdCategory;
    }

    public List<CategoryDto> getCategories() {
        EntityManager em = EMF.getEM();
        List<Category> categories = new ArrayList<>();
        List<CategoryDto> categoriesDto = new ArrayList<>();
        categories = categoryRepository.getCategories(em);
        categories.forEach(c -> categoriesDto.add(modelMapper.map(c, CategoryDto.class)));
        return categoriesDto;
    }

    public CategoryDto update (int id, CategoryDto categoryDto) {
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();
        Category category = modelMapper.map(categoryDto, Category.class);
        try {
            trans.begin();
            category = categoryRepository.update(em, id, category);
            trans.commit();
            categoryDto = modelMapper.map(category, CategoryDto.class);
        }catch (Exception e) {
            trans.rollback();
        } finally {
            if (trans.isActive()) {
                trans.rollback();
            }
            em.close();
        }
        return categoryDto;
    }
}
