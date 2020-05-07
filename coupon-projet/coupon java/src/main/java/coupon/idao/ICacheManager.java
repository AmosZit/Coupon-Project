package coupon.idao;

public interface ICacheManager {

	public void put(Object key, Object value);

	public Object get(Object key);

}
