package Less7;

import java.util.ArrayList;
import java.util.Arrays;
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
    	
    	
    	//Comment commentById = em.createQuery("SELECT c FROM Comment c WHERE c.id = :comm_id",  Comment.class)
    	//		.setParameter("comm_id", 40).getSingleResult();
    	//System.out.println(commentById);
    	
    	
    	//List<Post> posts = em.createQuery("SELECT p FROM Post p WHERE p.id > :post_id",  Post.class)
    	//		.setParameter("post_id", 50).getResultList();
    	//posts.forEach(p -> System.out.println(p));
    	
    	
    	//List<Post> posts = em.createQuery("SELECT p FROM Post p WHERE p.id IN (:post_ids)", Post.class)
    	//		.setParameter("post_ids", Arrays.asList(2,56,62,3,88,95)).getResultList();
    	//posts.forEach(p -> System.out.println(p));
    	
    	
    	//List<Post> posts = em.createQuery("SELECT p FROM Post p WHERE p.title LIKE :post_title", Post.class)
    	//		.setParameter("post_title", "%8").getResultList(); 
    	//posts.forEach(p -> System.out.println(p));
    	
    	
    	//List<Post> posts2 = em.createQuery("SELECT p FROM Post p WHERE p.title LIKE :post_title", Post.class)
    	//		.setParameter("post_title", "%8_").getResultList(); 
    	//posts2.forEach(p -> System.out.println(p));
    	
    	
    	//List<Post> posts2 = em.createQuery("SELECT p FROM Post p WHERE p.id BETWEEN :first AND :last", Post.class)
    	//		.setParameter("first", 76).setParameter("last", 82).getResultList(); 
    	//posts2.forEach(p -> System.out.println(p));
    	
    	//Agreg function
    	Long count = em.createQuery("SELECT count(c.id) FROM Comment c", Long.class).getSingleResult();
    	System.out.println("Count " + count);
    	
    	Long sum = em.createQuery("SELECT sum(c.id) FROM Comment c", Long.class).getSingleResult();
    	System.out.println("Sum " + sum);
    	
    	Double avg = em.createQuery("SELECT avg(c.id) FROM Comment c", Double.class).getSingleResult();
    	System.out.println("AVG " + avg);
    	
    	Integer max = em.createQuery("SELECT max(c.id) FROM Comment c", Integer.class).getSingleResult();
    	System.out.println("Max " + max);
    	Integer min = em.createQuery("SELECT min(c.id) FROM Comment c", Integer.class).getSingleResult();
    	System.out.println("Min " + min);
    	
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
