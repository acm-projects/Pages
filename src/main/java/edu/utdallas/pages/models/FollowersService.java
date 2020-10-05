package edu.utdallas.pages.models;

import edu.utdallas.pages.Database;
import org.springframework.stereotype.Service;

@Service("FollowersService")
public class FollowersService implements IFollowersService {

    private final IDataSource dataSource;

    public FollowersService(IDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void followUser(String follower, String following) {
        Database.query(dataSource,dataSource.getQuery("FOLLOW"),follower,following);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void unFollowUser(String follower, String following) {
        Database.query(dataSource,dataSource.getQuery("UNFOLLOW"),follower,following);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFollowing(String follower, String following) {
        return Database.exists(dataSource,dataSource.getQuery("IS_FOLLOWING"),follower,following);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String retrieveFollowers(String member) {
        String[] column = {"follower"};
        return Database.retrieveAsJsonArr(dataSource,column,column,dataSource.getQuery("RETRIEVE_FOLLOWERS"),member);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String retrieveFollowing(String member) {
        String[] column = {"following"};
        return Database.retrieveAsJsonArr(dataSource,column,column,dataSource.getQuery("RETRIEVE_FOLLOWING"), member);
    }

}
