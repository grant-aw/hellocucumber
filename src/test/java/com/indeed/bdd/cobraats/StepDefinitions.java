package com.indeed.bdd.cobraats;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

  private static WebTarget webTarget = null;
  private static String jobId = null;
  private static String applicantName = null;

  @Given("{string} login to cobra-ats hosted at {string} with {string}")
  public void login_to_cobra_ats_hosted_at_with(String username, String host, String password) {

    ClientConfig cookieConfig = new ClientConfig();
    cookieConfig.connectorProvider(new ApacheConnectorProvider());
    cookieConfig.property(ApacheClientProperties.DISABLE_COOKIES, false);


    Client client = ClientBuilder.newClient(cookieConfig);
    webTarget = client.target("http://" + host);

    Response csrfResponse = webTarget.path("csrf")
        .request(MediaType.APPLICATION_JSON)
        .get();


    assertEquals(Response.Status.OK, csrfResponse.getStatusInfo().toEnum());
    JsonObject jsonCrsfResponse = csrfResponse.readEntity(JsonObject.class);


    String crsfToken = jsonCrsfResponse.getString("token");
    System.out.println(crsfToken);


    Response loginResponse = webTarget.path("account").path("sign-in")
        .request()
        .accept(MediaType.WILDCARD_TYPE)
        .header("X-Csrf-Token", crsfToken)
        .post(Entity.form(new Form()
            .param("username", username)
            .param("password", password)));

    assertEquals(Response.Status.OK, loginResponse.getStatusInfo().toEnum());
  }

  @When("there is jobs under him\\/her")
  public void there_is_jobs_under_him_her() {
    Response jobResponse = webTarget.path("api").path("jobs").path("all")
      .request(MediaType.APPLICATION_JSON)
      .get();

    assertEquals(Response.Status.OK, jobResponse.getStatusInfo().toEnum());
    JsonArray jsonJobResponse = jobResponse.readEntity(JsonArray.class);

    System.out.println(jsonJobResponse.toString());
    assertTrue(jsonJobResponse.size() > 0);
  }


  @Then("at least there is one application")
  public void at_least_there_is_one_application() {
    Response applicationsResponse = webTarget.path("api").path("applications")
        .queryParam("page", 0)
        .queryParam("size", 10)
        .request(MediaType.APPLICATION_JSON)
        .get();

      assertEquals(Response.Status.OK, applicationsResponse.getStatusInfo().toEnum());
      JsonArray jsonApplicationsResponse =
          applicationsResponse.readEntity(JsonObject.class).getJsonArray("data");

      System.out.println(jsonApplicationsResponse.toString());
      assertTrue(jsonApplicationsResponse.size() > 0);
  }

  @When("there is job titled {string}")
  public void there_is_job_titled(String title) {
    Response jobsResponse = webTarget.path("api").path("jobs")
        .queryParam("search", title)
        .request(MediaType.APPLICATION_JSON)
        .get();

      assertEquals(Response.Status.OK, jobsResponse.getStatusInfo().toEnum());
      JsonArray jsonJobsResponse =
          jobsResponse.readEntity(JsonObject.class).getJsonArray("data");

      System.out.println(jsonJobsResponse.toString());
      assertTrue(jsonJobsResponse.size() > 0);

      jobId = String.valueOf(jsonJobsResponse.getJsonObject(0).getInt("jobId"));
  }

  @Then("there is one application for this job")
  public void there_is_one_application_for_this_job() {
    Response applicationsResponse = webTarget.path("api").path("applications")
        .queryParam("page", 0)
        .queryParam("size", 10)
        .queryParam("jobIds", jobId)
        .request(MediaType.APPLICATION_JSON)
        .get();

      assertEquals(Response.Status.OK, applicationsResponse.getStatusInfo().toEnum());
      JsonArray jsonApplicationsResponse =
            applicationsResponse.readEntity(JsonObject.class).getJsonArray("data");

      assertTrue(jsonApplicationsResponse.size() > 0);

      applicantName =
          jsonApplicationsResponse.getJsonObject(0).getString("firstName") +
          " " +
          jsonApplicationsResponse.getJsonObject(0).getString("lastName");
  }

  @Then("this applicant named {string}")
  public void this_applicant_named(String applicantName) {
    assertEquals(applicantName, StepDefinitions.this.applicantName);
  }
}
