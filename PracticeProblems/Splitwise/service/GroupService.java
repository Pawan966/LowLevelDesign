package Splitwise.service;

import Splitwise.models.Group;
import Splitwise.models.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GroupService {
    private final Map<String, Group> groups;

    public GroupService() {
        this.groups = new ConcurrentHashMap<>();
    }

    public void createGroup(String groupId, String name, String description) {
        groups.put(groupId, new Group(groupId, name, description));
    }

    public Group getGroup(String groupId) {
        return groups.get(groupId);
    }

    public boolean containsGroup(String groupId) {
        return groups.containsKey(groupId);
    }

    public void addUserToGroup(String groupId, User user) {
        Group group = getGroup(groupId);
        if(group != null && user != null) {
            group.addMember(user);
        }
    }
}
