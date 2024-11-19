package com.hms.dao;

import java.io.IOException;
import java.util.Map;
import java.util.Collection;

public interface Dao<item> {
    public Map<Integer, item> read(String filename) throws IOException;
    public void save(String filename, Collection<item> list) throws IOException;
}
