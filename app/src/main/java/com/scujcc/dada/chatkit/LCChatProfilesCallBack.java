package com.scujcc.dada.chatkit;


import java.util.List;

/**
 * Created by wli on 16/2/2.
 */
public interface LCChatProfilesCallBack {
  public void done(List<LCChatKitUser> userList, Exception exception);
}
