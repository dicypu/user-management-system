import { useState, useEffect } from 'react';
import axios from 'axios';
import UserForm from './components/UserForm';
import UserList from './components/UserList';

const API_BASE_URL = 'http://localhost:8085/api/users';

export default function App() {
    const [users, setUsers] = useState([]);
    const [editingUser, setEditingUser] = useState(null);
    const [loading, setLoading] = useState(false);
    const [errorMsg, setErrorMsg] = useState('');
    const [successMsg, setSuccessMsg] = useState('');

    // 1. GET: Kullanıcı Listesini Tazeleyen Fonksiyon
    const fetchUsers = async () => {
        setLoading(true);
        setErrorMsg('');
        try {
            const response = await axios.get(API_BASE_URL);
            setUsers(response.data);
        } catch (err) {
            setErrorMsg('Kullanıcı listesi yüklenemedi. Backend servisinin ayakta olduğundan emin olun.');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchUsers();
    }, []);

    // 2. POST / PUT: Ekleme veya Güncelleme İşlemi
    const handleFormSubmit = async (formData) => {
        setLoading(true);
        setErrorMsg('');
        setSuccessMsg('');

        try {
            if (editingUser) {
                // PUT İsteği: Güncelleme
                await axios.put(`${API_BASE_URL}/${editingUser.id}`, formData);
                setSuccessMsg(`#${editingUser.id} numaralı kullanıcı başarıyla güncellendi.`);
                setEditingUser(null);
            } else {
                // POST İsteği: Yeni Kayıt
                await axios.post(API_BASE_URL, formData);
                setSuccessMsg('Yeni kullanıcı başarıyla veritabanına kaydedildi.');
            }

            // Her işlemden sonra listeyi otomatik yenile
            await fetchUsers();
        } catch (err) {
            if (err.response && err.response.data) {
                const backendHata = typeof err.response.data === 'object'
                    ? JSON.stringify(err.response.data)
                    : err.response.data;
                setErrorMsg(`İşlem Başarısız (HTTP ${err.response.status}): ${backendHata}`);
            } else {
                setErrorMsg('Sunucuya erişilemiyor.');
            }
        } finally {
            setLoading(false);
        }
    };

    // 3. DELETE: Kullanıcı Silme İşlemi
    const handleDeleteUser = async (id) => {
        setLoading(true);
        setErrorMsg('');
        setSuccessMsg('');

        try {
            await axios.delete(`${API_BASE_URL}/${id}`);
            setSuccessMsg(`#${id} numaralı kullanıcı veritabanından silindi.`);

            // Eğer silinen kullanıcı o an düzenleme modundaysa modu kapat
            if (editingUser && editingUser.id === id) {
                setEditingUser(null);
            }

            // Listeyi otomatik yenile
            await fetchUsers();
        } catch (err) {
            setErrorMsg(`Silme işlemi başarısız oldu: ${err.message}`);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div style={{ minHeight: '100vh', backgroundColor: '#0b0f17', padding: '40px 20px', color: '#f8fafc' }}>
            <div style={{ maxWidth: '1000px', margin: '0 auto' }}>

                {/* Başlık Alanı */}
                <header style={{ borderBottom: '1px solid #1e293b', paddingBottom: '20px', marginBottom: '28px' }}>
                    <h1 style={{ margin: 0, color: '#f8fafc', fontSize: '28px', fontWeight: '700', letterSpacing: '-0.5px' }}>
                        THINX Kullanıcı Yönetim Paneli
                    </h1>
                    <p style={{ margin: '8px 0 0 0', color: '#94a3b8', fontSize: '15px' }}>
                        Gün 13: Modüler CRUD Mimarisi (Bileşen Ayrımı, PUT Güncelleme, Confirm ile DELETE)
                    </p>
                </header>

                {/* Başarı Bildirimi */}
                {successMsg && (
                    <div style={{
                        padding: '14px 18px',
                        backgroundColor: 'rgba(16, 185, 129, 0.2)',
                        border: '1px solid #10b981',
                        color: '#6ee7b7',
                        borderRadius: '8px',
                        marginBottom: '20px',
                        fontSize: '14px',
                        fontWeight: '500'
                    }}>
                        {successMsg}
                    </div>
                )}

                {/* Hata Bildirimi */}
                {errorMsg && (
                    <div style={{
                        padding: '14px 18px',
                        backgroundColor: 'rgba(127, 29, 29, 0.25)',
                        border: '1px solid #ef4444',
                        color: '#fca5a5',
                        borderRadius: '8px',
                        marginBottom: '20px',
                        fontSize: '14px',
                        fontWeight: '500'
                    }}>
                        {errorMsg}
                    </div>
                )}

                {/* 1. Form Bileşeni */}
                <UserForm
                    onSubmit={handleFormSubmit}
                    editingUser={editingUser}
                    onCancelEdit={() => setEditingUser(null)}
                    loading={loading}
                />

                {/* 2. Liste / Tablo Bileşeni */}
                <UserList
                    users={users}
                    loading={loading}
                    onEdit={(user) => setEditingUser(user)}
                    onDelete={handleDeleteUser}
                    onRefresh={fetchUsers}
                />

            </div>
        </div>
    );
}