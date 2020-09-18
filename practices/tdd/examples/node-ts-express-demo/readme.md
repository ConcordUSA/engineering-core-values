# TDD Example
> Basic express app written in Typescript using Jest to test

## Usage

1. run `npm i`
2. run `npm test:watch` in terminal window (keep open)
3. edit tests/src and watch the magic

Other uses: `npm start` - to serve the app

## Organization

- `/src` - source files
- `/tests/unit` - unit tests
- `/tests/integration` - integration tests
- `/tests/e2e` - end-to-end tests

Each subfolder (`/tests/unit` etc...) should mirror the `/src` folder

-----
## Concepts

1. **Dependency injection:** Compose your modules as functions that receive depencencies. This allows you to write tests that give that function mock dependencies.
2. **Types of tests:** Unit, Integration, End-to-end

## Unit tests

Testing a component in **isolation** (w/out external resources: file system, db, apis, etc...)

- Small (< 10 lines of code)
- Descriptive naming
- Single responsibility
- Excecuted in isolation

## Integration tests

Testing a component in **parallel** (w/ external resources: file system, db, apis, etc...)

## Credits
Here's a few articles/examples that influenced this example:

1. [NodeJS/TS/Express example](https://blog.logrocket.com/typescript-with-node-js-and-express/)
2. [Testing Node/TS example](https://medium.com/@admin_86118/testing-typescript-node-with-jest-6bf5db18119c)
3. [Dependency Injection example](https://www.youtube.com/watch?v=_HhzbIll4_E)