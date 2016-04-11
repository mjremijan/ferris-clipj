package org.ferris.clipj.window.history;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class HistoryEvent {
    private String newHistoryItem;

    public HistoryEvent(String newHistoryItem) {
        this.newHistoryItem = newHistoryItem;
    }

    public String getNewHistoryItem() {
        return newHistoryItem;
    }
}
