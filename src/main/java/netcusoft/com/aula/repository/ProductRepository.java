package netcusoft.com.aula.repository;

import netcusoft.com.aula.model.entity.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel,Long> {

   public Optional<ProductModel> findByUuid(UUID uuid);
   public Boolean existsByUuid(UUID uuid);


}
