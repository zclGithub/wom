package com.wyl.wom.bss.bss;

import com.wyl.wom.data.AbstractMessage;

public abstract class SupperAction {
     public abstract AbstractMessage executeObject(AbstractMessage msg);

     public abstract AbstractMessage executeJson(String json);
}
