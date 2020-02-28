package fr.dawan.formation.AppQCMMono.Services;

import java.time.LocalDateTime;

import javax.persistence.NoResultException;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import fr.dawan.formation.AppQCMMono.Models.Designer;
import fr.dawan.formation.AppQCMMono.Models.User;
import fr.dawan.formation.AppQCMMono.Models.objectBooleanString;
import fr.dawan.formation.AppQCMMono.Persistence.Constantes;
import fr.dawan.formation.AppQCMMono.Persistence.UserDAO;

@Service
public class UserService {

	

	public boolean controlLogin(String email, String password) {
		UserDAO userDao = new UserDAO(Constantes.PERSISTENCE_UNIT_NAME);
		User userLogin = null;
		
		try {
			userLogin = userDao.searchByEmail(email);
		} catch (NoResultException ex) {
			System.out.println("Utilisateur non trouvé");
		}
	
		userDao.close();
		if (userLogin != null) {
			

			//if (password.equals(userLogin.getPassword())) {
			if (BCrypt.checkpw(password, userLogin.getPassword())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
		 
	}
	
	public User searchByEmail(String email) {
		UserDAO userDao = new UserDAO(Constantes.PERSISTENCE_UNIT_NAME);
		User user=userDao.searchByEmail(email);
		userDao.close();
		return user;
	}

	public objectBooleanString createUser(User user) {
		UserDAO userDao = new UserDAO(Constantes.PERSISTENCE_UNIT_NAME);
		objectBooleanString objReturn=new objectBooleanString();
		objReturn.setAnswer(false);
		if (userDao.searchByEmail(user.getEmail())==null) {
			String pwd=BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			user.setPassword(pwd);
			user.setSignInDate(LocalDateTime.now());
			userDao.saveOrUpdate(user);
			objReturn.setAnswer(true);
		
		}else {
			objReturn.setComment("l'utilisateur existe déjà");
		}
		userDao.close();
		return objReturn;
	}

	public void saveOrUpdate(User user) {
		UserDAO userDao = new UserDAO(Constantes.PERSISTENCE_UNIT_NAME);
		userDao.saveOrUpdate(user);
		userDao.close();
	}

	public void createUserDesigner(User user, Designer designer) {
		// TODO empecher le lien vers la creation de designer pour un user qui en est deja un !
		//unicité du designer est géré via jpa, joincolum dans la classe designer
		//unique = true
		
		user.setDesigner(designer);
		designer.setUser(user);
		designer.setDateStatus(LocalDateTime.now());
		saveOrUpdate(user);
		// je n'écrit pas le designer car j'ai mis une cascade sur le mappedBy de User
		
		
	}
	
	public User findById(int id) {
		UserDAO userDao = new UserDAO(Constantes.PERSISTENCE_UNIT_NAME);
		User userFound =new User();
		userFound =userDao.findById(User.class, id);
		userDao.close();

		return userFound;
	}
	
	
	
	

	
}