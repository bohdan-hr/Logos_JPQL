package Less7;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import Less7.entity.Comment;
import Less7.entity.Post;
import Less7.entity.Tag;
import Less7.entity.enums.Status;

public class App 
{
    public static void main( String[] args )
    {
        
    	
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");
    	EntityManager em = factory.createEntityManager();
    	em.getTransaction().begin();
    	
    	//List<Comment> comments = em.createQuery("SELECT c FROM Comment c", Comment.class).getResultList();
    	//comments.forEach(c -> System.out.println(c));
    	
    	Comment commentById = em.createQuery("SELECT c FROM Comment c WHERE c.id = :comm_id",  Comment.class)
    			.setParameter("comm_id", 40).getSingleResult();
    	
    	System.out.println(commentById);
    	
    	//addTags(em);
    	//addPost(em);
    	//addComment(em);
    	
    	em.getTransaction().commit();

    	em.close();
    	factory.close();
    }
    
    private static void addTags(EntityManager em) {
    	List<String> tags = new ArrayList<String>();
    	
    	tags.add("Java");
    	tags.add("SQL");
    	tags.add("MySQL");
    	tags.add("JPA");
    	tags.add("ORM");
    	tags.add("STS");
    	tags.add("Eclipse");
    for (int i = 0; i < tags.size(); i++) {
    	Tag tag = new Tag();
    	tag.setName(tags.get(i));
    	em.persist(tag);
		
	}
    }
    private static void addPost(EntityManager em) {
    	
    	for (int i = 1; i <= 100; i++) {
			
    		Post post = new Post();
    		post.setTitle("Post Title #" +i);
    		post.setContent("Post content #"+i);
    		if(i%2==0) {
    			post.setStutus(Status.DRAFT);
    		}else{
    			post.setStutus(Status.PUBLISH);
    		}
    		em.persist(post);
    		List<Tag> tags = em.createQuery("SELECT t FROM Tag t", Tag.class).getResultList();
    		post.setTags(tags);
    		
		}
    	
    }
    
    private static void addComment (EntityManager em) {
    	for (int i = 1; i <= 100; i++) {
    		Post post =em.createQuery("SELECT p FROM Post p WHERE p.id = :id", Post.class).setParameter("id", i).getSingleResult();
    		
    		Comment comment = new Comment();
    		comment.setAuthor("Autor # "+i);
    		comment.setComment("Comment #" +i);
    		comment.setPost(post);
    		em.persist(comment);
    	}
    }
}
