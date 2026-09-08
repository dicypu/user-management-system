import { useState, useEffect } from 'react';
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8085/api/users';

export default function App() {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(false);
    const [errorMsg, setErrorMsg] = useState('');

    const [formData, setFormData] = useState({
        ad: '',
        soyad: '',
        email: '',
        telefon: ''
    });

    const fetchUsers = async () => {
        setLoading(true);
        setErrorMsg('');
        try {
            const response = await axios.get(API_BASE_URL);
            setUsers(response.data);
        } catch (err) {
            setErrorMsg('Kullanıcı listesi yüklenemedi. Backend servisinin (8085) ayakta olduğundan emin olun.');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchUsers();
    }, []);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData(prev => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setErrorMsg('');

        try {
            const response = await axios.post(API_BASE_URL, formData);
            setUsers(prev => [...prev, response.data]);
            setFormData({ ad: '', soyad: '', email: '', telefon: '' });
        } catch (err) {
            if (err.response && err.response.data) {
                const backendHata = typeof err.response.data === 'object'
                    ? JSON.stringify(err.response.data)
                    : err.response.data;
                setErrorMsg(`Kayıt Başarısız (HTTP ${err.response.status}): ${backendHata}`);
            } else {
                setErrorMsg('Sunucuya erişilemiyor (Bağlantı reddedildi veya CORS engeli).');
            }
        }
    };

    // Karanlık Mod Input Stili
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
        <div style={{ minHeight: '100vh', backgroundColor: '#0b0f17', padding: '40px 20px', color: '#f8fafc' }}>
            <div style={{ maxWidth: '900px', margin: '0 auto' }}>

                {/* Başlık Alanı */}
                <header style={{ borderBottom: '1px solid #1e293b', paddingBottom: '20px', marginBottom: '28px' }}>
                    <h1 style={{ margin: 0, color: '#f8fafc', fontSize: '28px', fontWeight: '700', letterSpacing: '-0.5px' }}>
                        THINX Kullanıcı Yönetim Paneli
                    </h1>
                    <p style={{ margin: '8px 0 0 0', color: '#94a3b8', fontSize: '15px' }}>
                        Gün 12: Controlled Component, Axios, useEffect ve CORS Entegrasyonu
                    </p>
                </header>

                {/* Hata Bildirimi (Karanlık Kırmızı Panel) */}
                {errorMsg && (
                    <div style={{
                        padding: '14px 18px',
                        backgroundColor: 'rgba(127, 29, 29, 0.25)',
                        border: '1px solid #ef4444',
                        color: '#fca5a5',
                        borderRadius: '8px',
                        marginBottom: '24px',
                        fontSize: '14px',
                        fontWeight: '500'
                    }}>
                        {errorMsg}
                    </div>
                )}

                {/* Yeni Kullanıcı Form Kartı */}
                <section style={{
                    backgroundColor: '#161f30',
                    padding: '24px',
                    borderRadius: '12px',
                    border: '1px solid #1e293b',
                    boxShadow: '0 4px 6px -1px rgba(0, 0, 0, 0.5)',
                    marginBottom: '32px'
                }}>
                    <h2 style={{ margin: '0 0 20px 0', color: '#f1f5f9', fontSize: '18px', fontWeight: '600' }}>
                        Yeni Kullanıcı Kaydı
                    </h2>
                    <form onSubmit={handleSubmit} style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(200px, 1fr))', gap: '16px' }}>
                        <div>
                            <label style={{ display: 'block', marginBottom: '6px', fontSize: '13px', color: '#94a3b8' }}>Ad *</label>
                            <input
                                type="text"
                                name="ad"
                                placeholder="Örn: Emirhan"
                                value={formData.ad}
                                onChange={handleInputChange}
                                required
                                style={inputStyle}
                            />
                        </div>
                        <div>
                            <label style={{ display: 'block', marginBottom: '6px', fontSize: '13px', color: '#94a3b8' }}>Soyad *</label>
                            <input
                                type="text"
                                name="soyad"
                                placeholder="Örn: Yavuz"
                                value={formData.soyad}
                                onChange={handleInputChange}
                                required
                                style={inputStyle}
                            />
                        </div>
                        <div>
                            <label style={{ display: 'block', marginBottom: '6px', fontSize: '13px', color: '#94a3b8' }}>E-posta *</label>
                            <input
                                type="email"
                                name="email"
                                placeholder="ornek@thinx.com"
                                value={formData.email}
                                onChange={handleInputChange}
                                required
                                style={inputStyle}
                            />
                        </div>
                        <div>
                            <label style={{ display: 'block', marginBottom: '6px', fontSize: '13px', color: '#94a3b8' }}>Telefon</label>
                            <input
                                type="text"
                                name="telefon"
                                placeholder="+905551234567"
                                value={formData.telefon}
                                onChange={handleInputChange}
                                style={inputStyle}
                            />
                        </div>
                        <button
                            type="submit"
                            style={{
                                gridColumn: '1 / -1',
                                padding: '12px',
                                backgroundColor: '#0284c7',
                                color: '#ffffff',
                                border: 'none',
                                borderRadius: '8px',
                                fontWeight: '600',
                                fontSize: '15px',
                                cursor: 'pointer',
                                marginTop: '8px'
                            }}
                        >
                            Veritabanına Kaydet (POST)
                        </button>
                    </form>
                </section>

                {/* Tablo Kartı */}
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
                            onClick={fetchUsers}
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
                                    </tr>
                                ))}
                                {users.length === 0 && (
                                    <tr>
                                        <td colSpan="5" style={{ padding: '32px', textAlign: 'center', color: '#64748b' }}>
                                            Veritabanında henüz kayıtlı kullanıcı bulunmuyor.
                                        </td>
                                    </tr>
                                )}
                                </tbody>
                            </table>
                        </div>
                    )}
                </section>

            </div>
        </div>
    );
}