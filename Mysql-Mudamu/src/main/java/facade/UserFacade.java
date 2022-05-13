package facade;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.jersey.api.NotFoundException;

import dao.UserDao;
import dto.User;

public class UserFacade {
	UserDao daoItem;

    public UserFacade() {
    	daoItem = new UserDao();
    }

    public void insertItem(User user) {
    	daoItem.load(user);
    }

    public User load(int id) {
        return daoItem.getObject(id);
    }

    public User load(String username) {
    	return daoItem.getObject(username);
    }
    
    public List loadAllItems() {
        try {
			return daoItem.loadAll();
		} catch (SQLException e) {
			Logger l = Logger.getLogger(e.getMessage());
            l.log(Level.SEVERE, "context", e);
		}
		return null;
    }

    public void saveUser(User user) {
        if (user.getIDPaciente() == 0) {
        	try {
				daoItem.create(user);
			} catch (NotFoundException | SQLException e) {
				Logger l = Logger.getLogger(e.getMessage());
	            l.log(Level.SEVERE, "context", e);
			}
        } else {
        	try {
				daoItem.save(user);
			} catch (NotFoundException e) {
				Logger l = Logger.getLogger(e.getMessage());
	            l.log(Level.SEVERE, "context", e);
			} catch (SQLException e) {
				Logger l = Logger.getLogger(e.getMessage());
	            l.log(Level.SEVERE, "context", e);
			}
        }
    }

    public void deleteCdItem(Integer id) {
         try {
			daoItem.delete(id);
		} catch (NotFoundException | SQLException e) {
			Logger l = Logger.getLogger(e.getMessage());
            l.log(Level.SEVERE, "context", e);
		}
    }
}
