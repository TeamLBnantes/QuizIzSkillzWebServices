package fr.dawan.formation.AppQCMMono.Persistence;

import java.util.List;

import fr.dawan.formation.AppQCMMono.Enum.Status;
import fr.dawan.formation.AppQCMMono.Models.Designer;
import fr.dawan.formation.AppQCMMono.Models.MCQ;
import fr.dawan.formation.AppQCMMono.Models.Question;
import fr.dawan.formation.AppQCMMono.Models.Theme;
import fr.dawan.formation.AppQCMMono.Persistence.Interfaces.DAOMCQInterface;

public class MCQDAO extends GenericDAO<MCQ> implements DAOMCQInterface {

	public MCQDAO(String persistenceUnitName) {
		super(persistenceUnitName);
	}
	
	
	@Override
	public List<MCQ> searchByTheme(Theme theme) {
		
		String requete = "select f from "  
				+ MCQ.class.getName() 
				+ " where f.theme = :theme";

		
		// JPQL (ou HQL)
		return super.entityManager
				.createQuery(requete, MCQ.class)
				.setParameter("theme", theme)
				.getResultList();
		
	}

	@Override
	public List<MCQ> searchByStatus(Status status) {
		String requete = "select f from "  
				+ MCQ.class.getName() 
				+ " f where f.status = :status";
		
		// JPQL (ou HQL)
		return super.entityManager
				.createQuery(requete, MCQ.class)
				.setParameter("status", status)
				.getResultList();
	}

	@Override
	public List<MCQ> searchByKWBody(String kw) {
		// TODO Auto-generated method stub
		String requete = "select f from "  
				+ MCQ.class.getName() 
				+ " f where f.body LIKE :body";
		
		// JPQL (ou HQL)
		return super.entityManager
				.createQuery(requete, MCQ.class)
				.setParameter("body", "%"+kw+"%")
				.getResultList();
	}

	@Override
	public List<MCQ> searchByDesigner(Designer designer) {
		// TODO Auto-generated method stub
		String requete = "select f from "  
				+ MCQ.class.getName() 
				+ " f where f.designer = :designer";
		
		// JPQL (ou HQL)
		return super.entityManager
				.createQuery(requete, MCQ.class)
				.setParameter("designer", designer)
				.getResultList();
	}
//extends GenericDAO<Question> implements DAOQuestionInterface
}