import express from 'express'
import todos from './routes/todos'
import {AxiosInstance} from 'axios'

export default (client: any):express.Application => {
    const app = express();
    app.get('/todos', todos(client))
    return app
}