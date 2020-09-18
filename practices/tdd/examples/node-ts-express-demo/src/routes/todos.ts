import express from 'express'
// import TodosService from '../services/todos'
import { AxiosInstance } from 'axios'


export default (client:AxiosInstance) => {
    return async (req: express.Request,res:express.Response) => {
        try {
            const {data} = await client.get('https://jsonplaceholder.typicode.com/todos')
            res.send(data)            
        } catch (error) {
            res.status(500).send()
        }
    }
}

