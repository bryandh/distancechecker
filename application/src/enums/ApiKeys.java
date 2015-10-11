package enums;

public enum ApiKeys {
	GoogleDistanceMatrix("AIzaSyBx5jOZiJyxHa-GN2wSfyQgVXZ_V4LoGOs");
	
	private Object value;
	
	private ApiKeys(Object value){
		this.value = value;
	}
	
	public Object getValue(){
		return value;
	}
}