package doppio.db;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import doppio.apps.authentication.model.Profile;
import doppio.apps.authentication.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class UserDB implements DBSet<User> {
    static Logger logger = LogManager.getLogger(UserDB.class);

    GsonBuilder builder;

    public UserDB() {
        builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.serializeNulls();
        ExclusionStrategy strategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                if (f.getDeclaringClass().equals(Profile.class) && !f.getName().equals("id")) {
                    return true;
                }
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        };
        builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        builder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        builder.addSerializationExclusionStrategy(strategy);
    }

    @Override
    public User get(int id) {
        for (User user : all()) {
            if (user.getId() == id)
                return  user;
        }
        return null;
    }

    @Override
    public LinkedList<User> all() {
        LinkedList<User> users = new LinkedList<>();
        File file = new File("src/main/resources/users/");
        Gson gson = builder.create();
        for (String s : file.list()) {
            try {
                JsonReader reader = new JsonReader(new FileReader("src/main/resources/users/" + s));
                users.add(gson.fromJson(reader, User.class));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    @Override
    public int add(User user) {
        int id;
        if (user.getId() != -1)
            id = user.getId();
        else
            id = nextId();
        user.setId(id);
        Gson gson = builder.create();
        String json = gson.toJson(user);

        logger.trace("add user" + json);

        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/users/" + id + ".txt");
            fileWriter.write(json);

            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void remove(int id) {
        logger.trace("remove user " + id);

        File f = new File("src/main/resources/users/" + id + ".txt");
        f.delete();
    }

    @Override
    public void clear() {
        File file = new File("src/main/resources/users/");
        for (String s : file.list()) {
            File f = new File("src/main/resources/users/" + s);
            f.delete();
        }
    }

    @Override
    public void update(User user) {
        logger.trace("update user " + user.getId());

        remove(user.getId());
        add(user);
    }

    @Override
    public int nextId() {
        for (int i = 0; ; i++) {
            boolean isUsed = false;
            for (User user : all()) {
                if (user.getId() == i)
                    isUsed = true;
            }
            if (!isUsed)
                return i;
        }
    }
}
