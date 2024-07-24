package fr.elbarbary.p_jee_s_o.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.elbarbary.p_jee_s_o.entities.Medecin;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, String>  {
	
	
}
