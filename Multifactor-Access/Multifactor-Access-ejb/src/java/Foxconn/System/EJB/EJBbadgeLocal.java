
package Foxconn.System.EJB;

import Warnings.Alert;
import Foxconn.System.ENTITY.Badge;

import java.util.List;
import javax.ejb.Local;


/**
 *
 * @author Zacarias_Mercado
 */
@Local
public interface EJBbadgeLocal{
     Alert ADD(Badge badge);
     Alert EDIT(Badge badge);
     Alert REMOVE(Badge badge);
     Integer searchForID(int id);
     List<Badge> BadgeList();

    boolean validateBadgeEJB(String tagId);
}
