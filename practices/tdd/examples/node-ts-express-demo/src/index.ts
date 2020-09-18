import server from './server'
import * as client from 'axios'
const PORT = 8000;
const app = server(client);


app.listen(PORT, () => {
  console.log(`⚡️[server]: Server is running at http://localhost:${PORT}`);
});