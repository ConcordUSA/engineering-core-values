import supertest, { SuperTest } from "supertest";
import server from "../../../src/server";

describe("todos", () => {
  let request: SuperTest<any>;

  it("should return 200", async () => {
    const client = {
      get: () => Promise.resolve({ data: "something" }),
    };
    const app = server(client);
    request = supertest(app);

    const resp = await request.get("/todos")
    expect(resp.status).toBe(200)
  });

  it("should return 500 for a server error", async () => {
    const client = {
      get: () => Promise.reject(new Error('error')),
    };
    const app = server(client);
    request = supertest(app);
    
    const resp = await request.get("/todos")
    expect(resp.status).toBe(500)
  });
});
