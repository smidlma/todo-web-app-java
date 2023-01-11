package ch.cern.todo.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TaskCategory {
    @Id
    @GeneratedValue
    private Long categoryId;

    @Column(unique = true, length = 100, nullable = false)
    private String categoryName;

    @Column(length = 500)
    private String categoryDescription;

    public TaskCategory() {
    }

    public TaskCategory(String categoryName, String categoryDescription) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (categoryId ^ (categoryId >>> 32));
        result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
        result = prime * result + ((categoryDescription == null) ? 0 : categoryDescription.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TaskCategory other = (TaskCategory) obj;
        if (categoryId != other.categoryId)
            return false;
        if (categoryName == null) {
            if (other.categoryName != null)
                return false;
        } else if (!categoryName.equals(other.categoryName))
            return false;
        if (categoryDescription == null) {
            if (other.categoryDescription != null)
                return false;
        } else if (!categoryDescription.equals(other.categoryDescription))
            return false;
        return true;
    }

}
