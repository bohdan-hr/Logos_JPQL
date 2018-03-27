package Less7.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import Less7.entity.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@Getter @Setter
public class Comment extends BaseEntity{
	
	@Column(name = "comment", length = 200)
	private String comment;
	
	@Column(name = "author", length = 100)
	private String author;
	
	@ManyToOne 
	@JoinColumn(name="post_id")
	private Post post;

	@Override
	public String toString() {
		return "Comment [comment=" + comment + ", author=" + author + ", getId()=" + getId() + "]";
	}

}
