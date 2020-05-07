package coupon.enums;

import java.util.HashMap;
import java.util.Map;

public enum Category {

	FOOD(1), ELECTRICITY(2), DRESS(3), FURNITURE(4);

	 private long value;
	    private static Map<Object, Object> map = new HashMap<Object, Object>();

	    private Category(int value) {
	        this.value = value;
	    }

	    static {
	        for (Category pageType : Category.values()) {
	            map.put(pageType.value, pageType);
	        }
	    }

	    public static Category valueOf(long pageType) {
	        return (Category) map.get(pageType);
	    }

	    public long getValue() {
	        return value;
	    }
	}