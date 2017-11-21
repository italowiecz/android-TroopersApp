package com.italo.troopersapp.util;

import com.italo.troopersapp.R;
import com.italo.troopersapp.model.Affiliation;

/**
 * Created by italo on 18/11/2017.
 */

public class ResourceUtil {
    public static int getResourceBasedOnAffiliation(Affiliation affiliation) {
        switch (affiliation) {
            case GALACTIC_EMPIRE:
                return R.drawable.galactic_empire;
            case GALACTIC_REPUBLIC:
                return R.drawable.galactic_republic;
            case FIRST_ORDER:
                return R.drawable.first_order;
            default:
                return 0;
        }
    }
}
