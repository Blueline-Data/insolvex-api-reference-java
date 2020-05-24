package de.insolvex.api.reference;

import de.insolvex.client.ApiClient;
import de.insolvex.client.ApiException;
import de.insolvex.client.api.ContingentApi;
import de.insolvex.client.api.UnlimitedApi;
import de.insolvex.client.api.model.*;
import org.junit.Before;
import org.junit.Test;

public class ApiTest {

    ApiClient client;

    @Before
    public void setUp() {
        client = Client.getClient();
        client.setDebugging(true);
    }

    @Test
    /**
     * This test performs a name search and creates notification requests for all cases which are returned.
     * Additionally, a notification request is created for the name to ensure that notifications are generated
     * also for new cases with this name.
     */
    public void nameSearchNotificationRequestTest() throws ApiException {
        ContingentApi contingentApi = new ContingentApi(client);
        UnlimitedApi unlimitedApi = new UnlimitedApi(client);
        // This test can be customized by setting an environment variable
        String nameSearchQuery = System.getenv("INSOLVEX_TEST_NAME_QUERY");
        if (nameSearchQuery == null) {
            nameSearchQuery = "Air Berlin";
        }
        // Prepare request parameters
        Body3 nameSearchBody = new Body3();
        nameSearchBody.setName(nameSearchQuery);
        // Perform API request
        InlineResponse2002 response = contingentApi.nameSearch(nameSearchBody);
        for (CourtCaseOverview courtCase : response.getCases()) {
            System.out.println("Fetched case: " + courtCase);
            // For each court case, create a notification request
            String reqId = "Court Case " + courtCase.getCourt() + ":" + courtCase.getAktenzeichen();
            // ensure that existing notification request with same id is deleted
            try {
                unlimitedApi.deleteNotificationRequest(new Body2().reqId(reqId));
            } catch (ApiException e) {
                // The deletion call may fail when the test runs for the first time
            }
            unlimitedApi.createNotificationRequest(new Body1().reqId(reqId).insolvexId(courtCase.getInsolvexId()));
        }
        // Create a notification request for the name
        String reqId = "customer-4711";
        try {
            unlimitedApi.deleteNotificationRequest(new Body2().reqId(reqId));
        } catch (ApiException e) {
            // The deletion call may fail when the test runs for the first time
        }
        unlimitedApi.createNotificationRequest(new Body1().reqId(reqId).nameString(nameSearchQuery));
    }

}
