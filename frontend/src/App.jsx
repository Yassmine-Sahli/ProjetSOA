import { useState, useEffect } from 'react';
import PersonCard from './components/PersonCard';
import PersonModal from './components/PersonModal';
import { personService } from './services/api';

function App() {
  const [persons, setPersons] = useState([]);
  const [loading, setLoading] = useState(true);
  const [searchQuery, setSearchQuery] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [editingPerson, setEditingPerson] = useState(null);
  const [error, setError] = useState(null);
  const [alert, setAlert] = useState(null); // { message, type }

  useEffect(() => {
    loadPersons();
  }, []);

  const showAlert = (message, type = 'success') => {
    setAlert({ message, type });
    setTimeout(() => setAlert(null), 3000);
  };

  const loadPersons = async () => {
    setLoading(true);
    setError(null);
    try {
      const data = await personService.getAll();
      setPersons(data);
    } catch (err) {
      console.error(err);
      setError('Impossible de charger les personnes. Vérifiez que le serveur est démarré.');
    } finally {
      setLoading(false);
    }
  };

  const handleSearch = (e) => {
    setSearchQuery(e.target.value.toLowerCase());
  };

  const filteredPersons = persons.filter((person) =>
    person.name.toLowerCase().includes(searchQuery) ||
    person.id.toString().includes(searchQuery)
  );

  const handleAdd = () => {
    setEditingPerson(null);
    setIsModalOpen(true);
  };

  const handleEdit = (person) => {
    setEditingPerson(person);
    setIsModalOpen(true);
  };

  const handleDelete = async (id) => {
    if (!window.confirm('Êtes-vous sûr de vouloir supprimer cette personne ?')) return;

    try {
      await personService.delete(id);
      showAlert('Personne supprimée avec succès!', 'success');
      await loadPersons();
    } catch (err) {
      showAlert('Erreur lors de la suppression', 'error');
    }
  };

  const handleSubmit = async (person) => {
    try {
      if (person.id) {
        await personService.update(person);
        showAlert('Personne modifiée avec succès!', 'success');
      } else {
        await personService.create(person);
        showAlert('Personne ajoutée avec succès!', 'success');
      }
      setIsModalOpen(false);
      loadPersons();
    } catch (err) {
      showAlert('Erreur lors de l\'opération', 'error');
    }
  };

  return (
    <div className="container">
      <header>
        <h1>Gestion des Personnes</h1>
        <p>Application Frontend - Backend REST JAX-RS</p>
      </header>

      <div className="content">
        {alert && (
          <div className={`alert ${alert.type === 'success' ? 'alert-success' : 'alert-error'}`}>
            {alert.message}
          </div>
        )}

        <div className="action-bar">
          <div className="search-box">
            <input
              type="text"
              placeholder="🔍 Rechercher par nom ou ID..."
              value={searchQuery}
              onChange={handleSearch}
            />
          </div>
          <button className="btn btn-primary" onClick={handleAdd}>
            ➕ Ajouter une personne
          </button>
          <button className="btn btn-success" onClick={loadPersons}>
            🔄 Actualiser
          </button>
        </div>

        <div id="personsContainer">
          {loading ? (
            <div className="loading">Chargement des données</div>
          ) : error ? (
            <div className="empty-state">
              <h3>⚠️ Erreur</h3>
              <p>{error}</p>
            </div>
          ) : filteredPersons.length === 0 ? (
            <div className="empty-state">
              <h3>📭 Aucune personne trouvée</h3>
              <p>Commencez par ajouter une nouvelle personne</p>
            </div>
          ) : (
            <div className="persons-grid">
              {filteredPersons.map((person) => (
                <PersonCard
                  key={person.id}
                  person={person}
                  onEdit={handleEdit}
                  onDelete={handleDelete}
                />
              ))}
            </div>
          )}
        </div>
      </div>

      <PersonModal
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        onSubmit={handleSubmit}
        person={editingPerson}
      />
    </div>
  );
}

export default App;
