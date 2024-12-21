package com.storeonline.product.domain.models;

import java.util.Objects;

public class ProductCategory {
	private int categoryId;
	private String name;
	private boolean enabled;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoryId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductCategory other = (ProductCategory) obj;
		return categoryId == other.categoryId;
	}

	public static class ProductCategoryBuilder {
		private int categoryId;
		private String name;
		private boolean enabled;

		private ProductCategoryBuilder() {
		}

		public ProductCategoryBuilder categoryId(int categoryId) {
			this.categoryId = categoryId;
			return this;
		}

		public ProductCategoryBuilder name(String name) {
			this.name = name;
			return this;
		}

		public ProductCategoryBuilder enabled(boolean enabled) {
			this.enabled = enabled;
			return this;
		}

		public ProductCategory build() {
			ProductCategory productCategory = new ProductCategory();
			productCategory.categoryId = categoryId;
			productCategory.name = name;
			productCategory.enabled = enabled;
			return productCategory;
		}
	}
	public static ProductCategoryBuilder builder() {
		return new ProductCategoryBuilder();
	}
}
