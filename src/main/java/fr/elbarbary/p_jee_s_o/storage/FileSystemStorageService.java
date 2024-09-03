package fr.elbarbary.p_jee_s_o.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import fr.elbarbary.p_jee_s_o.storage.exceptions.StorageException;
import fr.elbarbary.p_jee_s_o.storage.exceptions.StorageFileNotFoundException;

@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;
	
	@Autowired
	public FileSystemStorageService(StorageProperties properties) {
        
        if(properties.getLocation().trim().length() == 0){
            throw new StorageException("File upload location can not be Empty."); 
        }

		this.rootLocation = Paths.get(properties.getLocation());
	}
	
	@Override
	public String store(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}
			// Cela permet de générer un UUID pour compléter le nom du fichier et de ce fait, rendre le nom unique.
			String randomUUID = UUID.randomUUID().toString();
			String originalFileName = file.getOriginalFilename();
			int indexOfOriginalName = originalFileName.indexOf(".");
			String finalName = "";
			if(indexOfOriginalName < 1) {
				finalName = originalFileName;
			} else {
				String onlyName = originalFileName.substring(0, indexOfOriginalName);
				String onlyExt = originalFileName.substring(indexOfOriginalName, originalFileName.length());
				finalName = String.join("_", onlyName, randomUUID + onlyExt);
			}
			
			while(!Files.notExists(Path.of(String.join("_", file.getOriginalFilename(),randomUUID))))
			{
				randomUUID = UUID.randomUUID().toString();
			}
			
			Path destinationFile = this.rootLocation.resolve(Paths.get(finalName)).normalize().toAbsolutePath();
			if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				// This is a security check
				throw new StorageException(
						"Cannot store file outside current directory.");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,StandardCopyOption.REPLACE_EXISTING);
			}
			return finalName;
		}
		catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1)
				.filter(path -> !path.equals(this.rootLocation))
				.map(this.rootLocation::relativize);
		}
		catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}
	}
	
	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}
	
	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new StorageFileNotFoundException(
						"Could not read file: " + filename);

			}
		}
		catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		}
		catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
	

}
