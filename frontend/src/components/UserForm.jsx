import {useEffect, useState} from 'react';

const initialFormState = {
    ad: '',
    soyad: '',
    email: '',
    telefon: ''
};

export default function UserForm({onSubmit, editingUser, onCancelEdit, loading}) {
    const [formData, setFormData] = useState(initialFormState);

    // Düzenlenecek kullanıcı değiştiğinde formu doldur veya sıfırla
    useEffect(() => {
        if (editingUser) {
            setFormData({
                ad: editingUser.ad || '',
                soyad: editingUser.soyad || '',
                email: editingUser.email || '',
                telefon: editingUser.telefon || ''
            });
        } else {
            setFormData(initialFormState);
        }
    }, [editingUser]);

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData((prev) => ({...prev, [name]: value}));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        onSubmit(formData);
    };

    const inputStyle = {
        padding: '12px 14px',
        backgroundColor: '#0b0f17',
        color: '#f8fafc',
        border: '1px solid #334155',
        borderRadius: '8px',
        fontSize: '14px',
        outline: 'none',
        width: '100%'
    };

    return (
        <section style={{
            backgroundColor: '#161f30',
            padding: '24px',
            borderRadius: '12px',
            border: '1px solid #1e293b',
            boxShadow: '0 4px 6px -1px rgba(0, 0, 0, 0.5)',
            marginBottom: '32px'
        }}>
            <div style={{display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '20px'}}>
                <h2 style={{margin: 0, color: '#f1f5f9', fontSize: '18px', fontWeight: '600'}}>
                    {editingUser ? `Kullanıcı Düzenle (#${editingUser.id})` : 'Yeni Kullanıcı Kaydı'}
                </h2>
                {editingUser && (
                    <button
                        type="button"
                        onClick={onCancelEdit}
                        style={{
                            padding: '6px 12px',
                            backgroundColor: '#334155',
                            color: '#cbd5e1',
                            border: 'none',
                            borderRadius: '6px',
                            cursor: 'pointer',
                            fontSize: '12px'
                        }}
                    >
                        Düzenlemeyi İptal Et
                    </button>
                )}
            </div>

            <form onSubmit={handleSubmit}
                  style={{display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(200px, 1fr))', gap: '16px'}}>
                <div>
                    <label style={{display: 'block', marginBottom: '6px', fontSize: '13px', color: '#94a3b8'}}>Ad
                        *</label>
                    <input
                        type="text"
                        name="ad"
                        placeholder="Örn: Emirhan"
                        value={formData.ad}
                        onChange={handleChange}
                        required
                        style={inputStyle}
                    />
                </div>
                <div>
                    <label style={{display: 'block', marginBottom: '6px', fontSize: '13px', color: '#94a3b8'}}>Soyad
                        *</label>
                    <input
                        type="text"
                        name="soyad"
                        placeholder="Örn: Yavuz"
                        value={formData.soyad}
                        onChange={handleChange}
                        required
                        style={inputStyle}
                    />
                </div>
                <div>
                    <label style={{display: 'block', marginBottom: '6px', fontSize: '13px', color: '#94a3b8'}}>E-posta
                        *</label>
                    <input
                        type="email"
                        name="email"
                        placeholder="ornek@thinx.com"
                        value={formData.email}
                        onChange={handleChange}
                        required
                        style={inputStyle}
                    />
                </div>
                <div>
                    <label style={{
                        display: 'block',
                        marginBottom: '6px',
                        fontSize: '13px',
                        color: '#94a3b8'
                    }}>Telefon</label>
                    <input
                        type="text"
                        name="telefon"
                        placeholder="+905551234567"
                        value={formData.telefon}
                        onChange={handleChange}
                        style={inputStyle}
                    />
                </div>

                <button
                    type="submit"
                    disabled={loading}
                    style={{
                        gridColumn: '1 / -1',
                        padding: '12px',
                        backgroundColor: editingUser ? '#eab308' : '#0284c7',
                        color: editingUser ? '#000000' : '#ffffff',
                        border: 'none',
                        borderRadius: '8px',
                        fontWeight: '600',
                        fontSize: '15px',
                        cursor: loading ? 'not-allowed' : 'pointer',
                        marginTop: '8px'
                    }}
                >
                    {loading ? 'İşleniyor...' : editingUser ? 'Kullanıcıyı Güncelle (PUT)' : 'Veritabanına Kaydet (POST)'}
                </button>
            </form>
        </section>
    );
}