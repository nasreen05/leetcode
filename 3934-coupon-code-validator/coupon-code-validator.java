import java.util.*;

class Solution {
    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {

        // business line priority
        Map<String, Integer> priority = new HashMap<>();
        priority.put("electronics", 0);
        priority.put("grocery", 1);
        priority.put("pharmacy", 2);
        priority.put("restaurant", 3);

        // store valid coupons as (businessLine, code)
        List<String[]> validCoupons = new ArrayList<>();

        for (int i = 0; i < code.length; i++) {

            // condition 1: isActive must be true
            if (!isActive[i]) continue;

            // condition 2: businessLine must be valid
            if (!priority.containsKey(businessLine[i])) continue;

            // condition 3: code must be non-empty and valid
            if (code[i].length() == 0) continue;
            if (!code[i].matches("[a-zA-Z0-9_]+")) continue;

            validCoupons.add(new String[]{businessLine[i], code[i]});
        }

        // sort based on businessLine priority, then code
        Collections.sort(validCoupons, (a, b) -> {
            int cmp = priority.get(a[0]) - priority.get(b[0]);
            if (cmp != 0) return cmp;
            return a[1].compareTo(b[1]);
        });

        // extract only codes
        List<String> result = new ArrayList<>();
        for (String[] coupon : validCoupons) {
            result.add(coupon[1]);
        }

        return result;
    }
}
