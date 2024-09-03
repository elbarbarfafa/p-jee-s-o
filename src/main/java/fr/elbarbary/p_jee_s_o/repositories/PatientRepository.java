package fr.elbarbary.p_jee_s_o.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.elbarbary.p_jee_s_o.entities.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

	Page<Patient> findAll(Pageable pageable);
	
	Page<Patient> findByNomOrNumeroSecuriteSociale(String nom, String numeroSecuriteSociale, Pageable pageable);
	
}