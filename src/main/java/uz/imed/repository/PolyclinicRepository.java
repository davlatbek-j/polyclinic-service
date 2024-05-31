package uz.imed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.imed.entity.Polyclinic;

public interface PolyclinicRepository extends JpaRepository<Polyclinic,Long>
{
    @Query(value = "SELECT polyclinic_id FROM polyclinic_patients_phone WHERE patients_phone = ?1", nativeQuery = true)
    Long existsByPhone(String phone);

    Polyclinic findByNumber(Integer number);
}
