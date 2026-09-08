export default function UserList({ users, loading, onEdit, onDelete, onRefresh }) {
    const handleDeleteClick = (user) => {
        const onay = window.confirm(`${user.ad} ${user.soyad} (#${user.id}) kullanıcısını silmek istediğinize emin misiniz? Bu işlem geri alınamaz.`);
        if (onay) {
            onDelete(user.id);
        }
    };

    return (
        <section style={{
            backgroundColor: '#161f30',
            padding: '24px',
            borderRadius: '12px',
            border: '1px solid #1e293b',
            boxShadow: '0 4px 6px -1px rgba(0, 0, 0, 0.5)'
        }}>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '16px' }}>
                <h2 style={{ margin: 0, color: '#f1f5f9', fontSize: '18px', fontWeight: '600' }}>
                    Kullanıcı Listesi ({users.length})
                </h2>
                <button
                    onClick={onRefresh}
                    disabled={loading}
                    style={{
                        padding: '8px 16px',
                        backgroundColor: '#1e293b',
                        color: '#cbd5e1',
                        border: '1px solid #334155',
                        borderRadius: '6px',
                        cursor: 'pointer',
                        fontWeight: '500',
                        fontSize: '13px'
                    }}
                >
                    {loading ? 'Yenileniyor...' : 'Listeyi Yenile'}
                </button>
            </div>

            {loading && users.length === 0 ? (
                <p style={{ color: '#94a3b8', textAlign: 'center', padding: '24px 0' }}>Oracle XE üzerinden veriler çekiliyor...</p>
            ) : (
                <div style={{ overflowX: 'auto' }}>
                    <table style={{ width: '100%', borderCollapse: 'collapse', textAlign: 'left', fontSize: '14px' }}>
                        <thead>
                        <tr style={{ backgroundColor: '#0b0f17', borderBottom: '1px solid #334155' }}>
                            <th style={{ padding: '12px', color: '#94a3b8', fontWeight: '600' }}>ID</th>
                            <th style={{ padding: '12px', color: '#94a3b8', fontWeight: '600' }}>Ad Soyad</th>
                            <th style={{ padding: '12px', color: '#94a3b8', fontWeight: '600' }}>E-posta</th>
                            <th style={{ padding: '12px', color: '#94a3b8', fontWeight: '600' }}>Telefon</th>
                            <th style={{ padding: '12px', color: '#94a3b8', fontWeight: '600' }}>Durum</th>
                            <th style={{ padding: '12px', color: '#94a3b8', fontWeight: '600', textAlign: 'right' }}>İşlemler</th>
                        </tr>
                        </thead>
                        <tbody>
                        {users.map((user) => (
                            <tr key={user.id} style={{ borderBottom: '1px solid #1e293b' }}>
                                <td style={{ padding: '14px 12px', color: '#64748b' }}>#{user.id}</td>
                                <td style={{ padding: '14px 12px', fontWeight: '500', color: '#f8fafc' }}>{user.ad} {user.soyad}</td>
                                <td style={{ padding: '14px 12px', color: '#cbd5e1' }}>{user.email}</td>
                                <td style={{ padding: '14px 12px', color: '#94a3b8' }}>{user.telefon || '-'}</td>
                                <td style={{ padding: '14px 12px' }}>
                    <span style={{
                        padding: '4px 10px',
                        borderRadius: '9999px',
                        fontSize: '12px',
                        fontWeight: '600',
                        backgroundColor: user.durum ? 'rgba(16, 185, 129, 0.2)' : 'rgba(239, 68, 68, 0.2)',
                        color: user.durum ? '#34d399' : '#f87171',
                        border: user.durum ? '1px solid rgba(16, 185, 129, 0.4)' : '1px solid rgba(239, 68, 68, 0.4)'
                    }}>
                      {user.durum ? 'AKTİF' : 'PASİF'}
                    </span>
                                </td>
                                <td style={{ padding: '14px 12px', textAlign: 'right' }}>
                                    <button
                                        onClick={() => onEdit(user)}
                                        style={{
                                            padding: '6px 10px',
                                            backgroundColor: '#eab308',
                                            color: '#000000',
                                            border: 'none',
                                            borderRadius: '4px',
                                            fontWeight: '600',
                                            fontSize: '12px',
                                            cursor: 'pointer',
                                            marginRight: '8px'
                                        }}
                                    >
                                        Düzenle
                                    </button>
                                    <button
                                        onClick={() => handleDeleteClick(user)}
                                        style={{
                                            padding: '6px 10px',
                                            backgroundColor: '#ef4444',
                                            color: '#ffffff',
                                            border: 'none',
                                            borderRadius: '4px',
                                            fontWeight: '600',
                                            fontSize: '12px',
                                            cursor: 'pointer'
                                        }}
                                    >
                                        Sil
                                    </button>
                                </td>
                            </tr>
                        ))}
                        {users.length === 0 && (
                            <tr>
                                <td colSpan="6" style={{ padding: '32px', textAlign: 'center', color: '#64748b' }}>
                                    Veritabanında kayıtlı kullanıcı bulunamadı.
                                </td>
                            </tr>
                        )}
                        </tbody>
                    </table>
                </div>
            )}
        </section>
    );
}