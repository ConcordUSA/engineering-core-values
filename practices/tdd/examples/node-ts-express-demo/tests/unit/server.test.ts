import server from '../../src/server'

describe('server', () => {
    it('should hello', () => {
        const app = server({});
        expect(app).not.toBeNull()
    });
});