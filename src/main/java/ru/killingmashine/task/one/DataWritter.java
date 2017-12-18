package ru.killingmashine.task.one;

import java.util.Collection;
import java.util.List;

public interface DataWritter {
    void writeData(List<String> list);
    void clearFile(String fileName);
}
