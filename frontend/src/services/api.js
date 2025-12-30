const API_BASE_URL = 'http://localhost:8091/REST/api/users';

export const personService = {
    getAll: async () => {
        const response = await fetch(API_BASE_URL);
        if (!response.ok) throw new Error('Failed to fetch persons');
        return response.json();
    },

    getById: async (id) => {
        const response = await fetch(`${API_BASE_URL}/${id}`);
        if (!response.ok) throw new Error('Failed to fetch person');
        return response.json();
    },

    create: async (person) => {
        const response = await fetch(API_BASE_URL, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(person),
        });
        if (!response.ok) throw new Error('Failed to create person');
        return true;
    },

    update: async (person) => {
        const response = await fetch(API_BASE_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(person),
        });
        if (!response.ok) throw new Error('Failed to update person');
        return true;
    },

    delete: async (id) => {
        const response = await fetch(`${API_BASE_URL}/${id}`, {
            method: 'DELETE',
        });
        if (!response.ok) throw new Error('Failed to delete person');
        return true;
    },
};
