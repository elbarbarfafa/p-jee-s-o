package fr.elbarbary.p_jee_s_o.storage;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	
	void init();

	/**
	 * Sauvegarde d'un fichier
	 * @param file : le fichier sous format MultipartFile
	 * @return Le chemin d'accès à ce fichier
	 */
	String store(MultipartFile file);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();
		
}
