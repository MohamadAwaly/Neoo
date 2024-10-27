package be.neoo.services;

import be.neoo.connection.EMF;
import be.neoo.dto.BrandDto;
import be.neoo.entities.Brand;
import be.neoo.repository.BrandRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService {
    private ModelMapper modelMapper;
    private BrandRepository brandRepository;

    public BrandService(ModelMapper modelMapper, BrandRepository brandRepository) {
        this.modelMapper = modelMapper;
        this.brandRepository = brandRepository;
    }

    /**
     * add new brand
     * @param brandDto
     * @return
     */
    public BrandDto save(BrandDto brandDto) {
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();
        Brand brand = new Brand();
        BrandDto bdrandDto = new BrandDto();
        try {
            trans.begin();
            brand = brandRepository.save(em, modelMapper.map(brandDto, Brand.class));
            bdrandDto = modelMapper.map(brand, BrandDto.class);
            trans.commit();
        }catch (Exception e) {
            trans.rollback();
        } finally {
            if (trans.isActive()) {
                trans.rollback();
            }
            em.close();
        }
        return bdrandDto;
    }

    /**
     * List brand
     * @return
     */
    public List<BrandDto> getBrands() {
        EntityManager em = EMF.getEM();
        List<Brand> brands = new ArrayList<>();
        List<BrandDto> brandDtos = new ArrayList<>();
        brands = brandRepository.getBrands(em);
        brands.forEach(brand -> brandDtos.add(modelMapper.map(brand, BrandDto.class)));
        return brandDtos;

    }

    public BrandDto update (int id, BrandDto brandDto) {
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();
        Brand brand = modelMapper.map(brandDto, Brand.class);
        try {
            trans.begin();
            brand = brandRepository.update(em, id , brand);
            trans.commit();
            brandDto = modelMapper.map(brand, BrandDto.class);
        }catch (Exception e) {
            trans.rollback();
        } finally {
            if (trans.isActive()) {
                trans.rollback();
            }
            em.close();
        }
        return brandDto;
    }
}
