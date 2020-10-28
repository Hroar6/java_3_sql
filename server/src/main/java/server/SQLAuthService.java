package server;

import java.util.ArrayList;
import java.util.List;

public class SQLAuthService implements AuthService {
    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        return SQLHandler.getNicknameByLoginAndPassword(login, password);
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        return SQLHandler.registration(login, password, nickname);
    }

    @Override
    public boolean changeNick(String nickname, String newNick) {
        return SQLHandler.changeNick(nickname, newNick);
    }
}
