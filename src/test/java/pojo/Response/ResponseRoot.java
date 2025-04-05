package pojo.Response;

import java.util.List;

public class ResponseRoot {

    private List<ResponseEntity> responseEntity;

    public ResponseRoot(List<ResponseEntity> responseEntity) {
        this.responseEntity = responseEntity;
    }

    public List<ResponseEntity> getEntity() {
        return responseEntity;
    }

    public void setEntity(List<ResponseEntity> responseEntity) {
        this.responseEntity = responseEntity;
    }
}
