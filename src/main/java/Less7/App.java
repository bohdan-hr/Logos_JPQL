package Less7;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import Less7.entity.Comment;
import Less7.entity.Post;
import Less7.entity.Product;
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
//    	Long count = em.createQuery("SELECT count(c.id) FROM Comment c", Long.class).getSingleResult();
//    	System.out.println("Count " + count);
//    	
//    	Long sum = em.createQuery("SELECT sum(c.id) FROM Comment c", Long.class).getSingleResult();
//    	System.out.println("Sum " + sum);
//    	
//    	Double avg = em.createQuery("SELECT avg(c.id) FROM Comment c", Double.class).getSingleResult();
//    	System.out.println("AVG " + avg);
//    	
//    	Integer max = em.createQuery("SELECT max(c.id) FROM Comment c", Integer.class).getSingleResult();
//    	System.out.println("Max " + max);
//    	Integer min = em.createQuery("SELECT min(c.id) FROM Comment c", Integer.class).getSingleResult();
//    	System.out.println("Min " + min);
    	
    	//addTags(em);
    	//addPost(em);
    	//addComment(em);
    	
    	
//    	Post post = em.createQuery("SELECT p FROM Post p RIGHT JOIN FETCH p.product pp WHERE p.id = :id", Post.class)
//    			.setParameter("id", 9).getSingleResult();
//    	System.out.println(post);
//    	System.out.println(post.getProduct());
    	
    	
    	// 1) Вивесті всі взачення з таблиці Comment
		// 2) Вивесті всі взачення з таблиці Product де  id > 80;
		// 3) Вивесті всі взачення з таблиці Post через лайк  де тітл закінчується на 8;
    	// 4) Вивесті всі взачення з таблиці Product на проміжку між 10 і 25
    	
//    	CriteriaBuilder cb = em.getCriteriaBuilder();
//    	CriteriaQuery<Post> query = cb.createQuery(Post.class);
//    	Root<Post> root = query.from(Post.class);// FROM Post 
//    	query.select(root);//SELECT p FROM Post p
    	
    	//1
    	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<Comment> queryComment = cb.createQuery(Comment.class);
    	Root<Comment> rootComment = queryComment.from(Comment.class);
    	queryComment.select(rootComment);
    	
//    	List<Comment> comments = em.createQuery(queryComment).getResultList();
//    	comments.forEach(c -> System.out.println(c));
    	
    	
    	//2
    	CriteriaQuery<Product> queryProduct = cb.createQuery(Product.class);
    	Root<Product> rootProduct = queryProduct.from(Product.class);
    	queryProduct.select(rootProduct);
    	Expression<Integer> idProductExp = rootProduct.get("id");
    	Predicate idProductPredicate = cb.greaterThan(idProductExp, 80);
    	queryProduct.where(idProductPredicate);
    	
//    	List<Product> products = em.createQuery(queryProduct).getResultList();
//    	products.forEach(p -> System.out.println(p));
    	
    	//3
    	CriteriaQuery<Post> queryPost = cb.createQuery(Post.class);
    	Root<Post> rootPost = queryPost.from(Post.class);
    	queryPost.select(rootPost);
    	Join<Post, Product> postJoinProduct = rootPost.join("product");
    	Expression<String> productNameExp = postJoinProduct.get("name");
    	Predicate productNamePredicate = cb.like(productNameExp, "%8");
    	queryPost.where(productNamePredicate);
    	
//    	List<Post> posts = em.createQuery(queryPost).getResultList();
//    	posts.forEach(p -> System.out.println(p));
    	
    	//4
    	Predicate productPredicate = cb.between(idProductExp, 10, 25);
    	queryProduct.where(productPredicate);
    	
//    	List<Product> products2 = em.createQuery(queryProduct).getResultList();
//   	products2.forEach(p -> System.out.println(p));
    	
    	
    	//5
    	//Predicate productPredicate = cb.(idProductExp, 10, 25);
    	
    	
    	//1-1
//    	List<Comment> comments = em.createQuery("SELECT c FROM Comment c", Comment.class).getResultList();
//    	comments.forEach(c -> System.out.println(c));
    	
    	//2-2
    	
    	//List<Product> productss = em.createQuery("SELECT p FROM Product p WHERE p.id > :post_id",  Product.class)
    	//	.setParameter("post_id", 80).getResultList();
    	//productss.forEach(p -> System.out.println(p));
    	
    	//3-3
    	//List<Post> posts2 = em.createQuery("SELECT p FROM Post p WHERE p.title LIKE :post_title", Post.class)
    	//		.setParameter("post_title", "%8").getResultList(); 
    	//posts2.forEach(p -> System.out.println(p));
    	
    	//4-4
    	List<Product> posts = em.createQuery("SELECT p FROM Product p WHERE p.id > :product_id AND p.id < :product_id2",  Product.class)
    	   		.setParameter("product_id", 10).setParameter("product_id2", 25).getResultList();
    	    posts.forEach(p -> System.out.println(p));
    	
    	//5-5
    	    
//    	Expression<Integer> idExp = root.get("id");// id
//    	Predicate idPredicate = cb.greaterThan(idExp, 85);// id > 85
//    	query.where(idPredicate);//SELECT p FROM Post p WHERE id >85
//    	
//    	Expression<Integer> idExp = root.get("id");
//    	Predicate idMin = cb.greaterThanOrEqualTo(idExp, 40);// >40
//    	Predicate idMax = cb.lessThan(idExp, 60);// <60
//    	Predicate all = cb.and(idMin,idMax);
    	//query.where(all);
    	//query.where(idMin,idMax);
    	
//    	Join<Post, Product> postJoinProduct = root.join("product");
//    	Expression<BigDecimal> priceExp = postJoinProduct.get("price");
//    	Predicate pricePredicate = cb.between(priceExp, new BigDecimal("30.00"), new BigDecimal("50.00"));
    	//query.where(pricePredicate);
    	
//    	Expression<String> productNameExp = postJoinProduct.get("name");
//    	Predicate productNamePredicate = cb.like(productNameExp, "%4");
//    	
//    	Expression<Integer> productNameExp2 = postJoinProduct.get("id");
//    	Predicate productNamePredicate2 = cb.greaterThan(productNameExp2, 10);
    	
    	//query.where(productNamePredicate, productNamePredicate2);
    	
//    	Predicate all2 = cb.and(productNamePredicate,productNamePredicate2);
    	//query.where(all2);
    	
    	
    	
    	
//    	List<Post> posts = em.createQuery(query).getResultList();
//    	posts.forEach(p -> System.out.println(p));
    	
    	
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
    		
    		Product product = new Product();
    		product.setName("Product name #"+i);
    		product.setDescription("Decsription #"+ i);
    		product.setPrice(new BigDecimal(i + 10 + ".00"));
    		product.setInStock(15+i);
    		
    		post.setProduct(product);
    		
    		
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
