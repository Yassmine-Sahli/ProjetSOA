
export default function PersonCard({ person, onEdit, onDelete }) {
    const getInitials = (name) => {
        return name
            .split(' ')
            .map((word) => word[0])
            .join('')
            .toUpperCase()
            .substring(0, 2);
    };

    return (
        <div className="person-card">
            <div className="avatar">{getInitials(person.name)}</div>
            <span className="id-badge">ID: {person.id}</span>
            <h3>{person.name}</h3>
            <p className="age">🎂 {person.age} ans</p>
            <div className="actions">
                <button className="btn btn-warning" onClick={() => onEdit(person)}>
                    ✏️ Modifier
                </button>
                <button className="btn btn-danger" onClick={() => onDelete(person.id)}>
                    🗑️ Supprimer
                </button>
            </div>
        </div>
    );
}
