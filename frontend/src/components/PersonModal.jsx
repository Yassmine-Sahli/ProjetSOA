import { useState, useEffect } from 'react';

export default function PersonModal({ isOpen, onClose, onSubmit, person }) {
    const [formData, setFormData] = useState({
        name: '',
        age: '',
    });

    useEffect(() => {
        if (person) {
            setFormData({ name: person.name, age: person.age });
        } else {
            setFormData({ name: '', age: '' });
        }
    }, [person, isOpen]);

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit({
            ...formData,
            id: person?.id,
        });
    };

    if (!isOpen) return null;

    return (
        <div className={`modal ${isOpen ? 'active' : ''}`}>
            <div className="modal-content">
                <h2>{person ? 'Modifier une personne' : 'Ajouter une personne'}</h2>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>Nom complet *</label>
                        <input
                            type="text"
                            required
                            placeholder="Ex: Ahmed Ben Ali"
                            value={formData.name}
                            onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                        />
                    </div>

                    <div className="form-group">
                        <label>Âge *</label>
                        <input
                            type="number"
                            required
                            placeholder="Ex: 25"
                            min="1"
                            max="150"
                            value={formData.age}
                            onChange={(e) => setFormData({ ...formData, age: parseInt(e.target.value) || '' })}
                        />
                    </div>

                    <div className="form-actions">
                        <button type="button" className="btn btn-danger" onClick={onClose}>
                            ❌ Annuler
                        </button>
                        <button type="submit" className="btn btn-success">
                            ✅ Enregistrer
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}
