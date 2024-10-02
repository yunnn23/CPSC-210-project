package persistence;

import org.json.JSONObject;

// Read and write a JSON object
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
