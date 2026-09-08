export default function UserCard({ ad, soyad, email, telefon, durum }) {
    return (
        <div style={{
            border: '1px solid #cbd5e1',
            borderRadius: '8px',
            padding: '16px',
            margin: '12px 0',
            backgroundColor: '#ffffff',
            boxShadow: '0 1px 3px rgba(0,0,0,0.1)',
            maxWidth: '380px'
        }}>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '8px' }}>
                <h3 style={{ margin: 0, color: '#0f172a', fontSize: '18px' }}>
                    {ad} {soyad}
                </h3>
                <span style={{
                    padding: '4px 10px',
                    borderRadius: '9999px',
                    fontSize: '12px',
                    fontWeight: '600',
                    backgroundColor: durum ? '#dcfce7' : '#fee2e2',
                    color: durum ? '#15803d' : '#b91c1c'
                }}>
          {durum ? 'Aktif' : 'Pasif'}
        </span>
            </div>

            <p style={{ margin: '4px 0', color: '#475569', fontSize: '14px' }}>
                <strong>E-posta:</strong> {email}
            </p>
            <p style={{ margin: '4px 0', color: '#475569', fontSize: '14px' }}>
                <strong>Telefon:</strong> {telefon || 'Belirtilmedi'}
            </p>
        </div>
    );
}