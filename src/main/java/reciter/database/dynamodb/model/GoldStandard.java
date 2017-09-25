package reciter.database.dynamodb.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@DynamoDBTable(tableName = "GoldStandard")
@Data
@AllArgsConstructor
@DynamoDBDocument
public class GoldStandard {
    private final String uid;
    private final List<Long> knownPmids;
    private final List<Long> rejectedPmids;

    @DynamoDBHashKey(attributeName = "uid")
    public String getUid() {
        return uid;
    }

    @DynamoDBAttribute(attributeName = "knownpmids")
    public List<Long> getKnownPmids() {
        return knownPmids;
    }

    @DynamoDBAttribute(attributeName = "rejectedpmids")
    public List<Long> getRejectedPmids() {
        return rejectedPmids;
    }
}
