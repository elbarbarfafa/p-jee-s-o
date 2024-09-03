package fr.elbarbary.p_jee_s_o.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.elbarbary.p_jee_s_o.entities.Prescription;
import fr.elbarbary.p_jee_s_o.entities.PrescriptionKey;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, PrescriptionKey> {

	Page<Prescription> findByConsultationNumero(Integer id, Pageable pageable);
	
	List<Prescription> findByConsultationNumero(Integer id);
	
	//List<Prescription> findBy
	
}